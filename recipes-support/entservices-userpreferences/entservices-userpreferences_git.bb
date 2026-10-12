SUMMARY = "ENTServices UserPreferences Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-userpreferences"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dc6e390ad71aef79d0c2caf3cde03a19"

PV = "1.1.1"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-userpreferences;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native iarmmgrs devicesettings"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "userpreferences telemetrysupport"
PACKAGECONFIG[userpreferences] = "-DPLUGIN_USERPREFERENCES=ON,-DPLUGIN_USERPREFERENCES=OFF,glib-2.0,glib-2.0"
PACKAGECONFIG[telemetrysupport] = "-DBUILD_ENABLE_TELEMETRY_LOGGING=ON,,telemetry,telemetry"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
    -DSECAPI_LIB=sec_api \
    -DENABLE_RFC_MANAGER=ON \
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
