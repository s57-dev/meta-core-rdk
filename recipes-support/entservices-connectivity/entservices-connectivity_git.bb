SUMMARY = "ENTServices Connectivity Thunder plugins (Bluetooth only)"
HOMEPAGE = "https://github.com/rdkcentral/entservices-connectivity"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=be469927b9722d71bc41ecd5e71fe35f"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-connectivity;${CORE_GITHUB_SRC_URI_SUFFIX} \
           file://0001-FindIARMBus-optional-receiverMgr.patch \
           "
S = "${WORKDIR}/git"

PV = "1.7.7"

DEPENDS = "wpeframework wpeframework-tools-native"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

# Network/WiFi plugins need iarmmgrs + netsrvmgr; Bluetooth works with iarmbus + btmgr.
PACKAGECONFIG ??= "bluetoothcontrol telemetrysupport breakpadsupport"
PACKAGECONFIG[breakpadsupport] = ",,breakpad-wrapper,breakpad-wrapper"
PACKAGECONFIG[telemetrysupport] = "-DBUILD_ENABLE_TELEMETRY_LOGGING=ON,,telemetry,telemetry"
PACKAGECONFIG[bluetoothcontrol] = "\
    -DPLUGIN_BLUETOOTH=ON -DPLUGIN_BLUETOOTH_AUTOSTART=true, \
    -DPLUGIN_BLUETOOTH=OFF, \
    entservices-helpers iarmbus bluetooth-mgr, \
    bluez5 iarmbus bluetooth-mgr entservices-helpers"

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
