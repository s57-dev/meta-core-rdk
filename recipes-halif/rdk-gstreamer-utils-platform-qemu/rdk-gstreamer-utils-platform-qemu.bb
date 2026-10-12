SUMMARY = "QEMU stub for virtual/vendor-rdk-gstreamer-utils-platform"
DESCRIPTION = "No-op SoC symbols matching gstreamer-netflix-platform 2.0.3 headers."
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PROVIDES = "virtual/vendor-rdk-gstreamer-utils-platform"
RPROVIDES:${PN} = "virtual/vendor-rdk-gstreamer-utils-platform"
PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base rdk-gstreamer-utils-headers"

SRC_URI = "file://rdk_gstreamer_utils_soc_stub.cpp \
           file://CMakeLists.txt \
"
S = "${WORKDIR}"

inherit cmake pkgconfig

INSANE_SKIP:${PN} += "dev-so"
FILES_SOLIBSDEV = ""
FILES:${PN} = "${libdir}/librdkgstreamerutilsplatform.so.*"
FILES:${PN} += "${libdir}/librdkgstreamerutilsplatform.so"
