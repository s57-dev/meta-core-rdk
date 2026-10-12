SUMMARY = "ENTServices Infrastructure Thunder plugins (Analytics only)"
HOMEPAGE = "https://github.com/rdkcentral/entservices-infra"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9adde9d5cb6e9c095d3e3abf0e9500f1"

PV = "3.24.5"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-infra;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native wpeframework-clientlibraries entservices-apis"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

# Omit RDKShell/NativeJS/WebBridge (graphics / heavy). Analytics is headers-light.
PACKAGECONFIG ??= "analytics sceneset telemetrysupport"
PACKAGECONFIG[analytics] = "-DPLUGIN_ANALYTICS=ON,-DPLUGIN_ANALYTICS=OFF,entservices-apis,entservices-apis"
PACKAGECONFIG[sceneset] = "-DPLUGIN_SCENESET=ON,-DPLUGIN_SCENESET=OFF,entservices-apis,entservices-apis"
PACKAGECONFIG[telemetrysupport] = "-DBUILD_ENABLE_TELEMETRY_LOGGING=ON,,telemetry,telemetry"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
    -DSECAPI_LIB=sec_api \
    -DDISABLE_SECURITY_TOKEN=ON \
"

CXXFLAGS:append = " -I${STAGING_INCDIR}/wdmp-c \
                    -DRFC_ENABLED \
                    -DNET_DEFINED_INTERFACES_ONLY \
                    -DNET_NO_LINK_LOCAL_ANNOUNCE \
                    -Wall -Wno-error -Wno-deprecated-declarations"
TARGET_LDFLAGS += " -Wl,--no-as-needed -ltelemetry_msgsender -Wl,--as-needed"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/* ${includedir}/WPEFramework/interfaces/analytics/*"
INSANE_SKIP:${PN} += "libdir staticdev dev-so"
