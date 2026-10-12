SUMMARY = "ENTServices Tools Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-tools"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0.0"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-tools;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native entservices-helpers iarmbus"
RDEPENDS:${PN} += "wpeframework entservices-helpers"

inherit cmake pkgconfig

PACKAGECONFIG ??= "tools"
PACKAGECONFIG[tools] = "-DPLUGIN_TOOLS=ON,-DPLUGIN_TOOLS=OFF,entservices-helpers iarmbus,entservices-helpers"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
"

CXXFLAGS:append = " -Wall -Wno-error -Wno-deprecated-declarations"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
INSANE_SKIP:${PN} += "libdir staticdev dev-so"
