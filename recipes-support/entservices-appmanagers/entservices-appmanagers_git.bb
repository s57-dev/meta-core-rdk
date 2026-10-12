SUMMARY = "ENTServices AppManagers Thunder plugins (slim)"
DESCRIPTION = "TelemetryMetrics + AppStorageManager + DownloadManager. \
Dobby/Rialto/window-manager plugins stay OFF (deferred Core-V graphics/runtime stack)."
HOMEPAGE = "https://github.com/rdkcentral/entservices-appmanagers"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e78363a23fa72036998d7f40b889d209"

PV = "1.1.0.0"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-appmanagers;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native entservices-apis telemetry"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

# Slim set: Thunder-only plugins. Keep Dobby/Rialto/WM graph off.
PACKAGECONFIG ??= "telemetrymetrics appstoragemanager downloadmanager"
PACKAGECONFIG[telemetrymetrics] = "-DPLUGIN_TELEMETRYMETRICS=ON,-DPLUGIN_TELEMETRYMETRICS=OFF,"
PACKAGECONFIG[appstoragemanager] = "-DPLUGIN_APP_STORAGE_MANAGER=ON,-DPLUGIN_APP_STORAGE_MANAGER=OFF,"
PACKAGECONFIG[downloadmanager] = "\
    -DPLUGIN_DOWNLOADMANAGER=ON -DPLUGIN_DOWNLOADMANAGER_DOWNLOAD_DIR=/opt/CDL/,\
    -DPLUGIN_DOWNLOADMANAGER=OFF,curl,curl"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
    -DDISABLE_SECURITY_TOKEN=ON \
    -DPLUGIN_RUNTIME_MANAGER=OFF \
    -DPLUGIN_RDK_WINDOW_MANAGER=OFF \
    -DPLUGIN_LIFECYCLE_MANAGER=OFF \
    -DPLUGIN_APPMANAGER=OFF \
    -DPLUGIN_PACKAGE_MANAGER=OFF \
    -DPLUGIN_PREINSTALL_MANAGER=OFF \
    -DPLUGIN_VICTIM_SELECTOR=OFF \
    -DAIMANAGERS_TELEMETRY_METRICS_SUPPORT=OFF \
"

CXXFLAGS:append = " -Wall -Wno-error -Wno-deprecated-declarations"
TARGET_LDFLAGS += " -Wl,--no-as-needed -ltelemetry_msgsender -Wl,--as-needed"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/wpeframework/plugins/*.so ${libdir}/*.so ${datadir}/WPEFramework/*"
INSANE_SKIP:${PN} += "libdir staticdev dev-so"
