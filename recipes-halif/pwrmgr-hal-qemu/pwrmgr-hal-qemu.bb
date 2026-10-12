SUMMARY = "QEMU no-op Power Manager HAL (virtual/vendor-pwrmgr-hal)"
SECTION = "libs"
DESCRIPTION = "Minimal stub implementing rdk-halif-power_manager so \
iarmmgrs / PowerManager can link on qemuarm without vendor SoC code."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PROVIDES = "virtual/vendor-pwrmgr-hal"
RPROVIDES:${PN} = "virtual/vendor-pwrmgr-hal"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "power-manager-headers"

SRC_URI = "file://plat-power-stub.c \
           file://CMakeLists.txt \
"

S = "${WORKDIR}"

inherit cmake

FILES:${PN} = "${libdir}/libiarmmgrs-power-hal.so.*"
FILES:${PN} += "${libdir}/libiarmmgrs-power-hal.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"
