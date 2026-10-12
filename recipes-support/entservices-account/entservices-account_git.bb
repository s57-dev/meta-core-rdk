SUMMARY = "ENTServices Account Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-account"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2a944942e1496af1886903d274dedb13"

PV = "1.0.5"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-account;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native entservices-apis"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "account"
PACKAGECONFIG[account] = "-DPLUGIN_ACCOUNT=ON,,entservices-apis entservices-helpers,entservices-apis entservices-helpers"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
"

CXXFLAGS:append = " -Wall -Wno-error -Wno-deprecated-declarations"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
INSANE_SKIP:${PN} += "libdir staticdev dev-so"
