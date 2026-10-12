SUMMARY = "ENTServices FirmwareUpdate Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-firmwareupdate"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

PV = "1.2.6"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-firmwareupdate;${CORE_GITHUB_SRC_URI_SUFFIX} \
           file://0001-FindIARMBus-optional-extras.patch \
           "
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "firmwareupdate"
PACKAGECONFIG[firmwareupdate] = "\
    -DPLUGIN_FIRMWAREUPDATE=ON,-DPLUGIN_FIRMWAREUPDATE=OFF, \
    entservices-helpers iarmmgrs wpeframework-clientlibraries, \
    iarmbus entservices-helpers"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
    -DDISABLE_SECURITY_TOKEN=ON \
"

CXXFLAGS:append = " -I${STAGING_INCDIR}/wdmp-c \
                    -DNET_DEFINED_INTERFACES_ONLY \
                    -DNET_NO_LINK_LOCAL_ANNOUNCE \
                    -Wall -Wno-error -Wno-deprecated-declarations"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
INSANE_SKIP:${PN} += "libdir staticdev dev-so"
