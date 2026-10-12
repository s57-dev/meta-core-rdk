SUMMARY = "ENTServices MaintenanceManager Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-maintenancemanager"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=8156ec4df1d16b2ecb4e72131ff8026c"

PV = "1.16.0"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-maintenancemanager;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native iarmbus iarmmgrs rfc entservices-apis"
RDEPENDS:${PN} += "wpeframework iarmbus rfc sysint rdkfwupgrader entservices-apis"

inherit cmake pkgconfig

PACKAGECONFIG ??= "maintenancemanager telemetrysupport"
PACKAGECONFIG[maintenancemanager] = "\
    -DPLUGIN_MAINTENANCEMANAGER=ON -DDISABLE_DCM_TASK=ON, \
    -DPLUGIN_MAINTENANCEMANAGER=OFF,"
PACKAGECONFIG[telemetrysupport] = "-DBUILD_ENABLE_TELEMETRY_LOGGING=ON,,telemetry,telemetry"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
    -DSECAPI_LIB=sec_api \
    -DDISABLE_SECURITY_TOKEN=ON \
    -DTASK_TIMEOUT=3600 \
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
