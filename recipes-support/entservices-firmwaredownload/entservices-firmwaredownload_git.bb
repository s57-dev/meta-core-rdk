SUMMARY = "ENTServices FirmwareDownload Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-firmwaredownload"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2a944942e1496af1886903d274dedb13"

PV = "1.0.3"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-firmwaredownload;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "firmwaredownload"
PACKAGECONFIG[firmwaredownload] = "\
    -DPLUGIN_FIRMWAREDOWNLOAD=ON,-DPLUGIN_FIRMWAREDOWNLOAD=OFF, \
    wpeframework-clientlibraries entservices-helpers,"

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
