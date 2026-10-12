SUMMARY = "QEMU no-op Deepsleep Manager HAL (virtual/vendor-deepsleepmgr-hal)"
SECTION = "libs"
DESCRIPTION = "Minimal stub implementing rdk-halif-deepsleep_manager so \
iarmmgrs / PowerManager can link on qemuarm without vendor SoC code."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PROVIDES = "virtual/deepsleep-hal virtual/vendor-deepsleep-hal virtual/vendor-deepsleepmgr-hal"
RPROVIDES:${PN} = "virtual/deepsleep-hal virtual/vendor-deepsleep-hal virtual/vendor-deepsleepmgr-hal"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "deepsleep-manager-headers"

SRC_URI = "file://plat-deepsleep-stub.c \
           file://CMakeLists.txt \
"

S = "${WORKDIR}"

inherit cmake

FILES:${PN} = "${libdir}/libiarmmgrs-deepsleep-hal.so.*"
FILES:${PN} += "${libdir}/libiarmmgrs-deepsleep-hal.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"
