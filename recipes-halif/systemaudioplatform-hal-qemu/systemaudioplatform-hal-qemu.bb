SUMMARY = "QEMU stub System Audio Platform (virtual/vendor-systemaudioplatform)"
SECTION = "libs"
DESCRIPTION = "Minimal systemaudioplatform for qemuarm using GStreamer fakesink. \
Unblocks entservices-mediaanddrm TextToSpeech without vendor SoC audio."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PROVIDES = "virtual/vendor-systemaudioplatform"
RPROVIDES:${PN} = "virtual/vendor-systemaudioplatform"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base"

SRC_URI = "file://systemaudioplatform-stub.cpp \
           file://systemaudioplatform.h \
           file://CMakeLists.txt \
"

S = "${WORKDIR}"

inherit cmake pkgconfig

FILES:${PN} = "${libdir}/libsystemaudioplatform.so.*"
FILES:${PN} += "${libdir}/libsystemaudioplatform.so"
FILES:${PN}-dev = "${includedir}/systemaudioplatform.h"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"
