SUMMARY = "RDK Power Manager HALIF header definitions"
HOMEPAGE = "https://github.com/rdkcentral/rdk-halif-power_manager"
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "${CORE_GITHUB_ROOT}/rdk-halif-power_manager;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "1.0.4"

inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${includedir}/rdk/halif/power-manager
    install -m 0644 ${S}/include/*.h ${D}${includedir}/rdk/halif/power-manager/
}

ALLOW_EMPTY:${PN} = "1"
RDEPENDS:${PN}-dev = ""
