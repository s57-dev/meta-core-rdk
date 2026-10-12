SUMMARY = "Dobby Container Manager"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c466d4ab8a68655eb1edf0bf8c1a8fb8"

include dobby.inc

SRC_URI:append = " file://Fix_compile_gcc11.patch \
                   file://Add_config_header_kirkstone.patch \
                   file://dobby.generic.json \
                   file://dobby_start_after_apparmor.patch \
                   file://secure_wrapper.patch \
"

DEPENDS = "systemd libnl dbus jsoncpp boost yajl breakpad-wrapper libsyswrapper libcap \
           autoconf-native automake-native"
RDEPENDS:${PN} = "crun (>= 0.14.1) dobby-thunderplugin"

S = "${WORKDIR}/git"

# Core has no syslog-ng-config-gen / logrotate_config classes — systemd only.
inherit pkgconfig cmake systemd

python do_patch_new() {
    bb.build.exec_func('patch_do_patch', d)
}
addtask do_patch_new after do_configure before do_compile

EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Debug -DBUILD_REFERENCE=${SRCREV} \
                 -DSETTINGS_FILE=DONT_INSTALL_DEVICESETTINGSFILE"

# Slim qemu plugin set (omit gpu/ion/devicemapper from RDKE product append).
PACKAGECONFIG ??= "logging networking ipc storage minidump oomcrash"
PACKAGECONFIG[logging] = "-DPLUGIN_LOGGING=ON,-DPLUGIN_LOGGING=OFF,"
PACKAGECONFIG[networking] = "-DPLUGIN_NETWORKING=ON,-DPLUGIN_NETWORKING=OFF,"
PACKAGECONFIG[ipc] = "-DPLUGIN_IPC=ON,-DPLUGIN_IPC=OFF,"
PACKAGECONFIG[storage] = "-DPLUGIN_STORAGE=ON,-DPLUGIN_STORAGE=OFF,,e2fsprogs"
PACKAGECONFIG[minidump] = "-DPLUGIN_MINIDUMP=ON,-DPLUGIN_MINIDUMP=OFF,"
PACKAGECONFIG[oomcrash] = "-DPLUGIN_OOMCRASH=ON,-DPLUGIN_OOMCRASH=OFF,"
PACKAGECONFIG[gpu] = "-DPLUGIN_GPU=ON,-DPLUGIN_GPU=OFF,"
PACKAGECONFIG[localtime] = "-DPLUGIN_LOCALTIME=ON,-DPLUGIN_LOCALTIME=OFF,"
PACKAGECONFIG[rtscheduling] = "-DPLUGIN_RTSCHEDULING=ON,-DPLUGIN_RTSCHEDULING=OFF,"
PACKAGECONFIG[httpproxy] = "-DPLUGIN_HTTPPROXY=ON,-DPLUGIN_HTTPPROXY=OFF,"
PACKAGECONFIG[thunder] = "-DPLUGIN_THUNDER=ON,-DPLUGIN_THUNDER=OFF,"
PACKAGECONFIG[ionmemory] = "-DPLUGIN_IONMEMORY=ON,-DPLUGIN_IONMEMORY=OFF,"
PACKAGECONFIG[devicemapper] = "-DPLUGIN_DEVICEMAPPER=ON,-DPLUGIN_DEVICEMAPPER=OFF,"

SYSTEMD_SERVICE:${PN} = "dobby.service"

INSANE_SKIP:${PN} += "installed-vs-shipped dev-so"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

do_install:append() {
    install -d ${D}${sysconfdir}
    install -m 0644 ${WORKDIR}/dobby.generic.json ${D}${sysconfdir}/dobby.generic.json
}

FILES:${PN} += "${sysconfdir}/dobby.generic.json"
FILES:${PN} += "${systemd_system_unitdir}/dobby.service"
FILES:${PN} += "${bindir}/DobbyTool"
FILES:${PN} += "${sbindir}/DobbyDaemon"
FILES:${PN} += "${libexecdir}/DobbyInit"
FILES:${PN} += "${libdir}/plugins/dobby/*.so*"
FILES:${PN} += "${libdir}/libethanlog.so*"
FILES:${PN} += "${libdir}/libocispec.so*"
