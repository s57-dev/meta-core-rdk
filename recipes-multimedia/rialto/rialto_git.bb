SUMMARY = "Rialto media IPC (client + server)"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=327e572d11c37963726ba0b02d30cf2c"

require rialto_revision.inc

PV = "${RIALTO_VERSION}"
PR = "${RIALTO_PR}"

SRCREV = "${RIALTO_SRCREV}"
SRC_URI = "${CORE_GITHUB_ROOT}/rialto;${CORE_GITHUB_SRC_URI_SUFFIX} \
           file://0001-link-rdkgstreamerutilsplatform.patch \
"

DEPENDS = "openssl jsoncpp protobuf protobuf-native \
           virtual/vendor-rdk-gstreamer-utils-platform \
           opencdm-stub"

S = "${WORKDIR}/git"

inherit pkgconfig cmake features_check

REQUIRED_DISTRO_FEATURES = "enable_rialto"

EXTRA_OECMAKE += "-DRIALTO_BUILD_TYPE=Debug"

PACKAGES =+ "${PN}-client ${PN}-server ${PN}-servermanager-lib ${PN}-servermanager \
             ${PN}-client-dev ${PN}-server-dev ${PN}-servermanager-lib-dev ${PN}-servermanager-dev"

# Slim qemu: server only (skip servermanager/mongoose until AppManagers needs it).
PACKAGECONFIG ??= "server"
PACKAGECONFIG[server] = "\
    -DENABLE_SERVER=ON,-DENABLE_SERVER=OFF, \
    wpeframework-clientlibraries gstreamer1.0 gstreamer1.0-plugins-base glib-2.0 \
    virtual/vendor-rdk-gstreamer-utils-platform rdk-gstreamer-utils"
PACKAGECONFIG[servermanager] = "-DENABLE_SERVER_MANAGER=ON,-DENABLE_SERVER_MANAGER=OFF,mongoose,"

RDEPENDS:${PN}-server += "rdk-gstreamer-utils-platform-qemu rdk-gstreamer-utils"
RDEPENDS:${PN}-servermanager += "${PN}-server"
RDEPENDS:${PN}-servermanager-lib += "rdk-gstreamer-utils-platform-qemu rdk-gstreamer-utils"

FILES:${PN}-client += "${libdir}/libRialtoClient.so.*"
FILES:${PN}-server += "${bindir}/RialtoServer"
FILES:${PN}-servermanager += "${bindir}/RialtoServerManagerSim"
FILES:${PN}-servermanager-lib += "${libdir}/libRialtoServerManager.so.*"
FILES:${PN}-client-dev += "${libdir}/libRialtoClient.so"
FILES:${PN}-servermanager-lib-dev += "${includedir}/rialto ${libdir}/libRialtoServerManager.so"

DEBIAN_NOAUTONAME:${PN} = "1"
DEBIAN_NOAUTONAME:${PN}-dev = "1"
DEBIAN_NOAUTONAME:${PN}-client = "1"
DEBIAN_NOAUTONAME:${PN}-server = "1"
DEBIAN_NOAUTONAME:${PN}-servermanager = "1"
DEBIAN_NOAUTONAME:${PN}-servermanager-lib = "1"
DEBIAN_NOAUTONAME:${PN}-client-dev = "1"
DEBIAN_NOAUTONAME:${PN}-server-dev = "1"
DEBIAN_NOAUTONAME:${PN}-servermanager-dev = "1"
DEBIAN_NOAUTONAME:${PN}-servermanager-lib-dev = "1"
