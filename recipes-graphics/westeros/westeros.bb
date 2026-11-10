SUMMARY = "Westeros Wayland compositor"

include westeros.inc

SRC_URI = "${WESTEROS_URI} \
           file://westeros-smoke.sh \
           file://0001-westeros-seat-v5-for-wpe.patch \
"

PACKAGECONFIG ??= "incapp inctest increndergl incsbprotocol xdgv4"
PACKAGECONFIG[incapp] = "--enable-app=yes"
PACKAGECONFIG[inctest] = "--enable-test=yes"
PACKAGECONFIG[incplayer] = "--enable-player=yes"
PACKAGECONFIG[increndergl] = "--enable-rendergl=yes"
PACKAGECONFIG[incsbprotocol] = "--enable-sbprotocol=yes"
PACKAGECONFIG[incldbprotocol] = "--enable-ldbprotocol=yes"
PACKAGECONFIG[xdgv4] = "--enable-xdgv4=yes"
PACKAGECONFIG[xdgv5] = "--enable-xdgv5=yes"
PACKAGECONFIG[xdgstable] = "--enable-xdgstable=yes"
PACKAGECONFIG[modules] = "--enable-modules=yes,,virtual/westeros-soc"
PACKAGECONFIG[explicit-sync] = "--enable-lexpsyncprotocol=yes, , wayland-protocols"

S = "${WORKDIR}/git"

DEPENDS = "wayland libxkbcommon westeros-simplebuffer westeros-simpleshell virtual/westeros-soc \
           wayland-native gstreamer1.0 gstreamer1.0-plugins-base virtual/egl glib-2.0"

RDEPENDS:${PN} = "xkeyboard-config westeros-soc-drm"

REQUIRED_DISTRO_FEATURES += "wayland"

inherit autotools pkgconfig features_check systemd

EXTRA_OECONF += "--disable-essos --enable-embedded"

do_compile:prepend() {
   export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
   oe_runmake -C ${S}/protocol
   oe_runmake -C ${S}/linux-dmabuf/protocol
   oe_runmake -C ${S}/linux-expsync/protocol
}

do_install:append() {
    install -m 0644 ${S}/*.h ${D}${includedir}/

    install -d ${D}${bindir}
    install -m 0755 ${S}/systemd/westeros-init ${D}${bindir}/westeros-init
    install -m 0755 ${WORKDIR}/westeros-smoke.sh ${D}${bindir}/westeros-smoke.sh
    sed -i -e "s#/usr/lib#${libdir}#g" ${D}${bindir}/westeros-init

    install -d ${D}${sysconfdir}/default
    install -m 0644 ${S}/systemd/westeros-env ${D}${sysconfdir}/default/westeros-env

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/systemd/westeros.service ${D}${systemd_system_unitdir}/westeros.service
}

SYSTEMD_SERVICE:${PN} = "westeros.service"
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

FILES:${PN} += "${sysconfdir}/default/westeros-env \
                ${bindir}/westeros-smoke.sh \
                ${systemd_system_unitdir}/westeros.service"
FILES:${PN}-dev += "${includedir}/*.h"

# Optional QEMU/virtio-gpu mode/env workarounds (1280x720, getty, auto-enable).
# Empty on real targets; set via MACHINEOVERRIDES qemuall (e.g. qemuarm).
WESTEROS_QEMU_INC ?= ""
WESTEROS_QEMU_INC:qemuall = "westeros-qemu.inc"
include ${WESTEROS_QEMU_INC}
