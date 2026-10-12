SUMMARY = "ENTServices Monitor Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-monitor"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=be650d9617f9f9d24bcaccf78a97b28b"

PV = "1.1.0"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-monitor;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native entservices-apis"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "telemetrysupport"
PACKAGECONFIG[telemetrysupport] = "-DBUILD_ENABLE_TELEMETRY_LOGGING=ON,,telemetry,telemetry"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DPLUGIN_MONITOR_CLONED_APPS=ON \
"

CXXFLAGS:append = " -Wall -Wno-error -Wno-deprecated-declarations"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/wpeframework/plugins/*.so"
