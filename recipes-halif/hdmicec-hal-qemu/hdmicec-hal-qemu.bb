SUMMARY = "QEMU no-op HDMI CEC HAL (virtual/vendor-hdmicec-hal)"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PROVIDES = "virtual/hdmicec-hal virtual/vendor-hdmicec-hal"
RPROVIDES:${PN} = "virtual/hdmicec-hal virtual/vendor-hdmicec-hal"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "hdmicecheader"

SRC_URI = "file://hdmi-cec-hal-stub.c \
           file://CMakeLists.txt \
"
S = "${WORKDIR}"

inherit cmake

FILES:${PN} = "${libdir}/libRCECHal.so.*"
FILES:${PN} += "${libdir}/libRCECHal.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"
