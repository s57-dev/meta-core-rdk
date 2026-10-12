SUMMARY = "ENTServices Cryptography Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-cryptography"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e0be4633b6bc8df42eadad87f3e18a3a"

PV = "1.0.5"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-cryptography;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native wpeframework-clientlibraries entservices-apis"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "cryptography telemetrysupport"
PACKAGECONFIG[cryptography] = "-DPLUGIN_CRYPTOGRAPHY=ON,-DPLUGIN_CRYPTOGRAPHY=OFF,entservices-apis wpeframework-clientlibraries,entservices-apis wpeframework-clientlibraries"
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
