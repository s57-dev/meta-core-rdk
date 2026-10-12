SUMMARY = "ENTServices AppGateway Thunder plugins (slim)"
HOMEPAGE = "https://github.com/rdkcentral/entservices-appgateway"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=9adde9d5cb6e9c095d3e3abf0e9500f1"

PV = "1.6.0.0"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-appgateway;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native wpeframework-clientlibraries entservices-apis"
RDEPENDS:${PN} += "wpeframework entservices-apis"

inherit cmake pkgconfig

# Omit appgatewaycommon — needs networkmanager-plugin (not in Core qemu BOM yet).
PACKAGECONFIG ??= "appgateway appnotifications appactions telemetrysupport"
PACKAGECONFIG[appgateway] = "-DPLUGIN_APPGATEWAY=ON,-DPLUGIN_APPGATEWAY=OFF"
PACKAGECONFIG[appnotifications] = "-DPLUGIN_APPNOTIFICATIONS=ON,-DPLUGIN_APPNOTIFICATIONS=OFF"
PACKAGECONFIG[appactions] = "-DPLUGIN_APPACTIONS=ON,-DPLUGIN_APPACTIONS=OFF"
PACKAGECONFIG[telemetrysupport] = "-DBUILD_ENABLE_TELEMETRY_LOGGING=ON,,telemetry,telemetry"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
    -DDISABLE_SECURITY_TOKEN=ON \
    -DRDKAPPMANAGERS_PATH= \
"

TARGET_LDFLAGS += " -Wl,--no-as-needed -ltelemetry_msgsender -Wl,--as-needed"

do_install:append() {
    install -d ${D}${sysconfdir}
    echo "APP_GATEWAY_PV = \"${PV}\"" > ${D}${sysconfdir}/appgatewayversion.txt
    echo "APP_GATEWAY_SHA = \"${SRCREV}\"" >> ${D}${sysconfdir}/appgatewayversion.txt
}

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/wpeframework/plugins/*.so ${sysconfdir}/appgatewayversion.txt"
INSANE_SKIP:${PN} += "dev-so"
