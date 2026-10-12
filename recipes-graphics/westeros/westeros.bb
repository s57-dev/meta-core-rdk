SUMMARY = "Westeros Wayland compositor"

include westeros.inc

SRC_URI = "${WESTEROS_URI} \
           file://westeros-smoke.sh \
"

# inctest builds westeros_test (EGL-only when --enable-embedded, the default).
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

# Upstream configure requires GStreamer (no DISTRO_FEATURE gate).
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

    # DRM/qemuarm: preload SoC GL interposer; use a virtio-gpu-friendly mode.
    sed -i \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_GL_DEBUG=${WESTEROS_GL_DEBUG:-1}' \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_DRM_CARD=${WESTEROS_DRM_CARD:-/dev/dri/card0}' \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_GL_MODE=${WESTEROS_GL_MODE:-1024x768}' \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_GL_USE_PREFERRED_MODE=${WESTEROS_GL_USE_PREFERRED_MODE:-1}' \
        -e '/^exec \/usr\/bin\/westeros/i export LD_PRELOAD=${LD_PRELOAD:-/usr/lib/libwesteros_gl.so.0}' \
        ${D}${bindir}/westeros-init
    sed -i -e "s#/usr/lib#${libdir}#g" ${D}${bindir}/westeros-init

    install -d ${D}${sysconfdir}/default
    install -m 0644 ${S}/systemd/westeros-env ${D}${sysconfdir}/default/westeros-env

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/systemd/westeros.service ${D}${systemd_system_unitdir}/westeros.service
}

SYSTEMD_SERVICE:${PN} = "westeros.service"
# Start on demand for smoke tests; do not race DRM with getty on the same card.
SYSTEMD_AUTO_ENABLE:${PN} = "disable"

FILES:${PN} += "${sysconfdir}/default/westeros-env \
                ${bindir}/westeros-smoke.sh \
                ${systemd_system_unitdir}/westeros.service"
FILES:${PN}-dev += "${includedir}/*.h"
