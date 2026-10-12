SUMMARY = "No-op audio service for qemuarm"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PROVIDES = "virtual/audio-service virtual/vendor-audio-service"
RPROVIDES:${PN} = "virtual/audio-service virtual/vendor-audio-service"

ALLOW_EMPTY:${PN} = "1"
PACKAGE_ARCH = "${MACHINE_ARCH}"

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_install[noexec] = "1"
