SUMMARY = "ENTServices USBDevice Thunder plugin"
HOMEPAGE = "https://github.com/rdkcentral/entservices-usbdevice"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

PV = "1.3.1"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-usbdevice;${CORE_GITHUB_SRC_URI_SUFFIX} \
           file://0001-libusb-init-fallback-for-1.0.26.patch \
           "
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native wpeframework-clientlibraries \
           packager-headers boost libusb1"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "usbdevice telemetrysupport"
PACKAGECONFIG[usbdevice] = "-DPLUGIN_USBDEVICE=ON,-DPLUGIN_USBDEVICE=OFF,entservices-helpers,entservices-helpers"
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
