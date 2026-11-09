# QEMU/virtio-gpu workarounds for Westeros. Included only when MACHINEOVERRIDES
# contains qemuall (see westeros.bb). Keep target-specific mode/env out of the
# portable compositor recipe.

# usb-tablet uses ABS_X/ABS_Y; stock Westeros only handled REL motion.
SRC_URI:append = " file://0002-westeros-evdev-abs-pointer-for-qemu-tablet.patch"

# Auto-start compositor for qemu demos; stop getty@tty1 so DRM/KMS is free.
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append() {
    # Match kas QB_GRAPHICS 1280x720 + virtio-gpu preferred-size support.
    sed -i \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_GL_DEBUG=${WESTEROS_GL_DEBUG:-1}' \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_DRM_CARD=${WESTEROS_DRM_CARD:-/dev/dri/card0}' \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_GL_USE_PREFERRED_MODE=${WESTEROS_GL_USE_PREFERRED_MODE:-1}' \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_GL_GRAPHICS_MAX_SIZE=${WESTEROS_GL_GRAPHICS_MAX_SIZE:-1280x720}' \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_GL_USER_PREFERRED_SIZE=${WESTEROS_GL_USER_PREFERRED_SIZE:-1280x720}' \
        -e '/^exec \/usr\/bin\/westeros/i export LD_PRELOAD=${LD_PRELOAD:-/usr/lib/libwesteros_gl.so.0}' \
        ${D}${bindir}/westeros-init
    sed -i -e "s#/usr/lib#${libdir}#g" ${D}${bindir}/westeros-init

    # KEY=VALUE only (EnvironmentFile); same knobs as meta-rpi-qemu westeros.bbappend.
    if [ -f ${D}${sysconfdir}/default/westeros-env ]; then
        printf '%s\n' \
            'WESTEROS_DRM_CARD=/dev/dri/card0' \
            'LD_PRELOAD=/usr/lib/libwesteros_gl.so.0.0.0' \
            'WESTEROS_GL_USE_PREFERRED_MODE=1' \
            'WESTEROS_GL_GRAPHICS_MAX_SIZE=1280x720' \
            'WESTEROS_GL_USER_PREFERRED_SIZE=1280x720' \
            >> ${D}${sysconfdir}/default/westeros-env
    fi

    sed -i \
        -e '/^\[Service\]/a ExecStartPre=-/bin/systemctl stop getty@tty1.service' \
        ${D}${systemd_system_unitdir}/westeros.service
}
