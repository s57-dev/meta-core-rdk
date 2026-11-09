# QEMU/virtio-gpu DRM patches for westeros-soc-drm. Included only when
# MACHINEOVERRIDES contains qemuall (see westeros-soc-drm.bb).

SRC_URI:append = " \
    file://qemu-user-preferred-size.patch \
    file://0001-westeros-gl-xrgb-fallback-virtio-gpu.patch \
    file://0002-westeros-gl-drmModeAddFB2-use-gbm-format.patch \
"
