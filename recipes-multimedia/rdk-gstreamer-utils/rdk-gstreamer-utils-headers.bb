SUMMARY = "RDK GStreamer utils headers (HALIF)"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

ALLOW_EMPTY:${PN} = "1"
PV = "2.0.3"

SRC_URI = "${CORE_GITHUB_ROOT}/gstreamer-netflix-platform;${CORE_GITHUB_SRC_URI_SUFFIX}"
SRCREV = "f7d68a8a077989e1dd55428e1026813afcbe211f"
S = "${WORKDIR}/git"

inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

RDEPENDS:${PN}-dev = ""

do_install() {
    install -d ${D}${includedir}
    install -m 0644 ${S}/rdk_gstreamer_utils.h ${D}${includedir}/
    install -m 0644 ${S}/rdk_gstreamer_utils_soc.h ${D}${includedir}/
}
