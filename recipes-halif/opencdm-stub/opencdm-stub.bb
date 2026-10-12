SUMMARY = "QEMU OpenCDM stub (libocdm + headers for Rialto)"
DESCRIPTION = "Minimal OpenCDM shared library and headers so Rialto can link \
without entservices-opencdmi DRM stack. Not for production DRM."
SECTION = "libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PACKAGE_ARCH = "${MACHINE_ARCH}"

# Provide same virtual names RDKE expects from entservices-opencdmi for packaging.
PROVIDES += "entservices-opencdmi"
RPROVIDES:${PN} += "entservices-opencdmi"

DEPENDS = "gstreamer1.0"

SRC_URI = "file://CMakeLists.txt \
           file://ocdmConfig.cmake.in \
           file://ocdm.pc.in \
           file://open_cdm.cpp \
           file://open_cdm_ext.cpp \
           file://open_cdm_adapter.cpp \
           file://RialtoGStreamerEMEProtectionMetadata.cpp \
           file://RialtoGStreamerEMEProtectionMetadata.h \
           file://opencdm/open_cdm.h \
           file://opencdm/open_cdm_ext.h \
           file://opencdm/open_cdm_adapter.h \
"
S = "${WORKDIR}"

inherit cmake pkgconfig

FILES:${PN} = "${libdir}/libocdm.so.*"
FILES:${PN} += "${libdir}/libocdm.so"
FILES:${PN}-dev = "${includedir}/opencdm ${libdir}/cmake/ocdm ${libdir}/pkgconfig/ocdm.pc"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"
