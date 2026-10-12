SUMMARY = "ENTServices Telemetry Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-telemetry"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=2a944942e1496af1886903d274dedb13"

PV = "1.1.4"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-telemetry;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native wpeframework-clientlibraries packager-headers"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "telemetry telemetrysupport"
PACKAGECONFIG[telemetry] = "\
    -DPLUGIN_TELEMETRY=ON,, \
    iarmbus iarmmgrs entservices-apis entservices-helpers rfc rbus, \
    iarmbus entservices-apis entservices-helpers rfc rbus"
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
FILES:${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
INSANE_SKIP:${PN} += "libdir staticdev dev-so"
