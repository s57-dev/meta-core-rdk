SUMMARY = "ENTServices DeviceInfo Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-deviceinfo"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=34aa8e5c0a5ec9c81c534b40fb21b5b6"

PV = "1.1.7"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-deviceinfo;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "deviceinfo telemetrysupport"
PACKAGECONFIG[deviceinfo] = "\
    -DPLUGIN_DEVICEINFO=ON,-DPLUGIN_DEVICEINFO=OFF, \
    iarmbus iarmmgrs rfc devicesettings virtual/vendor-devicesettings-hal \
    entservices-apis entservices-helpers, \
    iarmbus rfc devicesettings entservices-apis entservices-helpers"
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
