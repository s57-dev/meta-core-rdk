SUMMARY = "RDK Core image for QEMU (32-bit ARM)"
DESCRIPTION = "Minimal bootable Core image. Phase 1 installs a systemd \
baseline plus packagegroup-core-rdk (strict Core-V BOM)."

LICENSE = "MIT"

inherit core-image

IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-rdk \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

# QEMU-friendly rootfs formats
IMAGE_FSTYPES = "ext4 tar.bz2"

# Allow empty root password for development
EXTRA_IMAGE_FEATURES += "debug-tweaks"
