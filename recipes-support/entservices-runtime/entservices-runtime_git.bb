SUMMARY = "ENTServices Runtime Thunder plugin (PerformanceMetrics only)"
HOMEPAGE = "https://github.com/rdkcentral/entservices-runtime"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dc6e390ad71aef79d0c2caf3cde03a19"

PV = "1.2.3"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-runtime;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "performancemetrics telemetrysupport"
PACKAGECONFIG[performancemetrics] = "\
    -DPLUGIN_PERFORMANCEMETRICS=ON \
    -DPLUGIN_PERFORMANCEMETRICS_LOGGER_IMPLEMENTATION=SYSLOG \
    -DPLUGIN_PERFORMANCEMETRICS_WEBKITBROWSER_CLASSNAME=ON \
    ,-DPLUGIN_PERFORMANCEMETRICS=OFF,entservices-apis,"
PACKAGECONFIG[telemetrysupport] = "-DBUILD_ENABLE_TELEMETRY_LOGGING=ON,,telemetry,telemetry"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
    -DSECAPI_LIB=sec_api \
"

CXXFLAGS:append = " -I${STAGING_INCDIR}/wdmp-c \
                    -DRFC_ENABLED \
                    -DNET_DEFINED_INTERFACES_ONLY \
                    -DNET_NO_LINK_LOCAL_ANNOUNCE \
                    -Wall -Wno-error -Wno-deprecated-declarations"
TARGET_LDFLAGS += " -Wl,--no-as-needed -ltelemetry_msgsender -Wl,--as-needed"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
INSANE_SKIP:${PN} += "libdir staticdev dev-so"
