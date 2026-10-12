SUMMARY = "ENTServices PowerManager Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-powermanager"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=be650d9617f9f9d24bcaccf78a97b28b"

PV = "1.4.8"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-powermanager;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "power-manager-headers deepsleep-manager-headers \
           wpeframework wpeframework-tools-native wpeframework-clientlibraries"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "powermanager telemetrysupport"
PACKAGECONFIG[powermanager] = "\
    -DPLUGIN_POWERMANAGER=ON,-DPLUGIN_POWERMANAGER=OFF, \
    iarmbus iarmmgrs virtual/vendor-deepsleepmgr-hal virtual/vendor-pwrmgr-hal \
    virtual/mfrlib entservices-apis entservices-helpers, \
    virtual/mfrlib entservices-apis entservices-helpers"
PACKAGECONFIG[telemetrysupport] = "-DBUILD_ENABLE_TELEMETRY_LOGGING=ON,,telemetry,telemetry"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
    -DSECAPI_LIB=sec_api \
    -DDISABLE_SECURITY_TOKEN=ON \
    -DENABLE_RFC_MANAGER=ON \
    -DBUILD_ENABLE_THERMAL_PROTECTION=ON \
"

CXXFLAGS:append = " -I${STAGING_INCDIR}/wdmp-c \
                    -I${STAGING_INCDIR}/rdk/halif/power-manager \
                    -I${STAGING_INCDIR}/rdk/halif/deepsleep-manager \
                    -I${STAGING_INCDIR}/WPEFramework/powercontroller \
                    -DRFC_ENABLED \
                    -DNET_DEFINED_INTERFACES_ONLY \
                    -DNET_NO_LINK_LOCAL_ANNOUNCE \
                    -DPLATCO_BOOTTO_STANDBY \
                    -DOFFLINE_MAINT_REBOOT \
                    -Wall -Wno-error -Wno-deprecated-declarations"
TARGET_LDFLAGS += " -Wl,--no-as-needed -ltelemetry_msgsender -Wl,--as-needed"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
INSANE_SKIP:${PN} += "libdir staticdev dev-so"
