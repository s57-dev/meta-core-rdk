SUMMARY = "ENTServices DisplayInfo Thunder plugin (LibDRM backend)"
DESCRIPTION = "qemuarm uses the Linux/LibDRM PlatformImplementation. DeviceSettings \
+ displayinfo-soc path is left OFF (no vendor SoC stub yet)."
HOMEPAGE = "https://github.com/rdkcentral/entservices-displayinfo"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=0545acf8134821be0c0b6cb6d4603200"

PV = "1.2.8"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-displayinfo;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

# LibDRM path: do NOT set USE_DEVICESETTINGS (that requires displayinfo-soc).
PACKAGECONFIG ??= "displayinfo telemetrysupport"
PACKAGECONFIG[displayinfo] = "\
    -DPLUGIN_DISPLAYINFO=ON,-DPLUGIN_DISPLAYINFO=OFF, \
    drm entservices-apis entservices-helpers, \
    libdrm entservices-apis entservices-helpers"
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
