SUMMARY = "ENTServices PlayerInfo Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-playerinfo"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b8674f44703ede95bec14083251335db"

PV = "1.1.4"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-playerinfo;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

# DeviceSettings + GStreamer path (RDKE default). Dolby vendor path stays OFF.
PACKAGECONFIG ??= "playerinfo telemetrysupport"
PACKAGECONFIG[playerinfo] = "\
    -DPLUGIN_PLAYERINFO=ON -DUSE_DEVICESETTINGS=1,-DPLUGIN_PLAYERINFO=OFF, \
    iarmbus iarmmgrs entservices-apis entservices-helpers \
    devicesettings virtual/vendor-devicesettings-hal gstreamer1.0, \
    iarmbus entservices-apis entservices-helpers devicesettings gstreamer1.0"
PACKAGECONFIG[telemetrysupport] = "-DBUILD_ENABLE_TELEMETRY_LOGGING=ON,,telemetry,telemetry"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
    -DSECAPI_LIB=sec_api \
    -DDOLBY_SUPPORT=OFF \
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
