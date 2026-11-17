# QEMU/virtio-gpu tweaks when MACHINEOVERRIDES includes qemuall (see westeros.bb).

SRC_URI:append = " file://0002-westeros-evdev-abs-pointer-for-qemu-tablet.patch"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

do_install:append() {
    sed -i \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_GL_DEBUG=${WESTEROS_GL_DEBUG:-1}' \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_DRM_CARD=${WESTEROS_DRM_CARD:-/dev/dri/card0}' \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_GL_USE_PREFERRED_MODE=${WESTEROS_GL_USE_PREFERRED_MODE:-1}' \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_GL_GRAPHICS_MAX_SIZE=${WESTEROS_GL_GRAPHICS_MAX_SIZE:-1280x720}' \
        -e '/^exec \/usr\/bin\/westeros/i export WESTEROS_GL_USER_PREFERRED_SIZE=${WESTEROS_GL_USER_PREFERRED_SIZE:-1280x720}' \
        -e '/^exec \/usr\/bin\/westeros/i export LD_PRELOAD=${LD_PRELOAD:-/usr/lib/libwesteros_gl.so.0}' \
        ${D}${bindir}/westeros-init
    sed -i -e "s#/usr/lib#${libdir}#g" ${D}${bindir}/westeros-init

    if [ -f ${D}${sysconfdir}/default/westeros-env ]; then
        printf '%s\n' \
            'WESTEROS_DRM_CARD=/dev/dri/card0' \
            'LD_PRELOAD=/usr/lib/libwesteros_gl.so.0.0.0' \
            'WESTEROS_GL_USE_PREFERRED_MODE=1' \
            'WESTEROS_GL_GRAPHICS_MAX_SIZE=1280x720' \
            'WESTEROS_GL_USER_PREFERRED_SIZE=1280x720' \
            >> ${D}${sysconfdir}/default/westeros-env
    fi

    # Unbind PS/2 mouse so only usb-tablet feeds Westeros.
    cat > ${D}${bindir}/westeros-unbind-psmouse << 'EOF'
#!/bin/sh
for s in /sys/bus/serio/drivers/psmouse/serio*; do
    [ -e "$s" ] || continue
    echo -n "$(basename "$s")" > /sys/bus/serio/drivers/psmouse/unbind
done
EOF
    chmod 0755 ${D}${bindir}/westeros-unbind-psmouse

    sed -i \
        -e '/^\[Service\]/a ExecStartPre=-/bin/systemctl stop getty@tty1.service' \
        -e '/^\[Service\]/a ExecStartPre=-/usr/bin/westeros-unbind-psmouse' \
        ${D}${systemd_system_unitdir}/westeros.service
}
