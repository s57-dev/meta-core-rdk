SUMMARY = "SafeC dummy API header for builds without libsafec"
DESCRIPTION = "Provides safec_lib.h with SAFEC_DUMMY_API shims so Core \
middleware can compile without depending on Cisco Safe C Library."
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

SRC_URI = "file://safec_lib.h"

PV = "1.0"

do_install() {
    install -d ${D}${includedir}
    install -m 0644 ${WORKDIR}/safec_lib.h ${D}${includedir}/
}

# Header-only — keep an empty main package for DEPENDS resolution.
ALLOW_EMPTY:${PN} = "1"
