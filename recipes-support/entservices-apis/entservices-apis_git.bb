SUMMARY = "Thunder interface definitions for EntServices"
HOMEPAGE = "https://github.com/rdkcentral/entservices-apis"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=d8927f3331d2b3e321b7dd1925166d25"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-apis;${CORE_GITHUB_SRC_URI_SUFFIX} \
           file://RDKEMW-1007.patch \
           file://entservices-apis-fps-ocdm.patch \
           file://idrm.patch \
           "
S = "${WORKDIR}/git"

PV = "4.2.3"

DEPENDS = "wpeframework wpeframework-tools-native"

inherit python3native cmake pkgconfig

EXTRA_OECMAKE += " \
    -DBUILD_SHARED_LIBS=ON \
    -DBUILD_REFERENCE=${SRCREV} \
    -DCMAKE_SYSROOT=${STAGING_DIR_HOST} \
"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/* ${datadir}/WPEFramework/*"
FILES:${PN}-dev += "${libdir}/cmake/*"
INSANE_SKIP:${PN} += "dev-so"
