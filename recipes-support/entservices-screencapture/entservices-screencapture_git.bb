SUMMARY = "ENTServices ScreenCapture Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-screencapture"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7df5a8706277b586ca000838046993d1"

PV = "1.1.5"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-screencapture;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "screencapture telemetrysupport"
PACKAGECONFIG[screencapture] = "\
    -DPLUGIN_SCREENCAPTURE=ON,-DPLUGIN_SCREENCAPTURE=OFF, \
    entservices-apis entservices-helpers curl libpng drm, \
    entservices-apis entservices-helpers curl libpng libdrm"
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
