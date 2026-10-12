SUMMARY = "Chrony control shared library"
HOMEPAGE = "https://github.com/rdkcentral/time-utils"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "1.1.0"

SRC_URI = "${CORE_GITHUB_ROOT}/time-utils;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git/libchronyctl"

DEPENDS = "chrony"
RDEPENDS:${PN} = "chrony"

inherit autotools pkgconfig

do_install:append() {
    rm -f ${D}${libdir}/libchronyctl.la
}

FILES:${PN} += "${libdir}/libchronyctl.so.*"
FILES:${PN}-dev += "${libdir}/libchronyctl.so \
                    ${includedir}/libchronyctl.h \
                    ${includedir}/addressing.h \
                    ${includedir}/candm.h"
