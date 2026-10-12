SUMMARY = "ENTServices MediaAndDrm Thunder plugins (TTS only)"
DESCRIPTION = "TextToSpeech only. SystemAudioPlayer and DRM children stay OFF. \
Requires virtual/vendor-systemaudioplatform (qemu stub)."
HOMEPAGE = "https://github.com/rdkcentral/entservices-mediaanddrm"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=16cf2209d4e903e4d5dcd75089d7dfe2"

PV = "1.11.0"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-mediaanddrm;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "texttospeech telemetrysupport"
PACKAGECONFIG[texttospeech] = "\
    -DPLUGIN_TEXTTOSPEECH=ON,-DPLUGIN_TEXTTOSPEECH=OFF, \
    entservices-apis entservices-helpers curl gstreamer1.0 gstreamer1.0-plugins-base \
    virtual/vendor-systemaudioplatform, \
    entservices-apis entservices-helpers curl gstreamer1.0 virtual/vendor-systemaudioplatform"
PACKAGECONFIG[telemetrysupport] = "-DBUILD_ENABLE_TELEMETRY_LOGGING=ON,,telemetry,telemetry"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
    -DSECAPI_LIB=sec_api \
    -DPLUGIN_SYSTEMAUDIOPLAYER=OFF \
"

CXXFLAGS:append = " -I${STAGING_INCDIR}/wdmp-c \
                    -DRFC_ENABLED \
                    -DNET_DEFINED_INTERFACES_ONLY \
                    -DNET_NO_LINK_LOCAL_ANNOUNCE \
                    -Wall -Wno-error -Wno-deprecated-declarations"
TARGET_LDFLAGS += " -Wl,--no-as-needed -ltelemetry_msgsender -lsystemaudioplatform -Wl,--as-needed"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
INSANE_SKIP:${PN} += "libdir staticdev dev-so"
