SUMMARY = "Thunder Framework (WPEFramework)"
HOMEPAGE = "https://github.com/rdkcentral/Thunder"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=85bcfede74b96d9a58c6ea5d4b607e58"

SRC_URI = "git://github.com/rdkcentral/Thunder.git;protocol=https;branch=R4_4-RDK \
           file://wpeframework.service \
           "
S = "${WORKDIR}/git"

PV = "4.4.7"

DEPENDS = "zlib wpeframework-tools-native rfc breakpad-wrapper"

inherit cmake pkgconfig systemd python3native

WPEFRAMEWORK_PERSISTENT_PATH ?= "/opt/persistent/rdkservices"
WPEFRAMEWORK_SYSTEM_PREFIX ?= "OE"
WPEFRAMEWORK_PORT ?= "9998"
WPEFRAMEWORK_BINDING ?= "127.0.0.1"
WPEFRAMEWORK_IDLE_TIME ?= "0"
WPEFRAMEWORK_THREADPOOL_COUNT ?= "8"
WPEFRAMEWORK_EXIT_REASONS ?= "WatchdogExpired"

PACKAGECONFIG ??= "release virtualinput websocket"
PACKAGECONFIG[debug] = "-DCMAKE_BUILD_TYPE=Debug,-DCMAKE_BUILD_TYPE=Release,"
PACKAGECONFIG[release] = "-DCMAKE_BUILD_TYPE=Release,-DCMAKE_BUILD_TYPE=Debug,"
PACKAGECONFIG[virtualinput] = "-DVIRTUALINPUT=ON,-DVIRTUALINPUT=OFF,"
PACKAGECONFIG[websocket] = "-DWEBSOCKET=ON,-DWEBSOCKET=OFF,"

WPEFRAMEWORK_EXTERN_EVENTS ?= "Decryption Location Time Internet Provisioning Security"

EXTRA_OECMAKE += " \
    -DINSTALL_HEADERS_TO_TARGET=ON \
    -DEXTERN_EVENTS='${WPEFRAMEWORK_EXTERN_EVENTS}' \
    -DEXCEPTIONS_ENABLE=ON \
    -DBUILD_SHARED_LIBS=ON \
    -DRPC=ON \
    -DBUILD_REFERENCE=${SRCREV} \
    -DTREE_REFERENCE=${SRCREV} \
    -DPORT=${WPEFRAMEWORK_PORT} \
    -DBINDING=${WPEFRAMEWORK_BINDING} \
    -DENABLED_TRACING_LEVEL=2 \
    -DPERSISTENT_PATH=${WPEFRAMEWORK_PERSISTENT_PATH} \
    -DSYSTEM_PREFIX=${WPEFRAMEWORK_SYSTEM_PREFIX} \
    -DIDLE_TIME=${WPEFRAMEWORK_IDLE_TIME} \
    -DTHREADPOOL_COUNT=${WPEFRAMEWORK_THREADPOOL_COUNT} \
    -DHIDE_NON_EXTERNAL_SYMBOLS=OFF \
    -DEXIT_REASONS=${WPEFRAMEWORK_EXIT_REASONS} \
    -DMESSAGING=ON \
    -DFLUSH_LOGS=ON \
    -DCMAKE_SYSROOT=${STAGING_DIR_HOST} \
    -DAUTHORIZEDEXTENSIONS='MessagingControl;PluginInitializerService;Systemd' \
    -DDISABLEPLUGINAUTOACTIVATION=true \
    -DLEGACY_CONFIG_GENERATOR=OFF \
    -DPOSTMORTEM_PATH=/opt/secure/minidumps \
"

do_install:append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/wpeframework.service \
        ${D}${systemd_unitdir}/system/wpeframework.service
}

SYSTEMD_SERVICE:${PN} = "wpeframework.service"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/*.so ${datadir}/WPEFramework/* ${systemd_unitdir}/system/wpeframework.service"
FILES:${PN}-dev += "${libdir}/cmake/*"
INSANE_SKIP:${PN} += "dev-so"
