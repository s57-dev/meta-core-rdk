SUMMARY = "RDK Deepsleep Manager HALIF header definitions"
HOMEPAGE = "https://github.com/rdkcentral/rdk-halif-deepsleep_manager"
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

SRC_URI = "${CORE_GITHUB_ROOT}/rdk-halif-deepsleep_manager;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "1.0.5"

inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${includedir}/rdk/halif/deepsleep-manager
    install -m 0644 ${S}/include/*.h ${D}${includedir}/rdk/halif/deepsleep-manager/
}

ALLOW_EMPTY:${PN} = "1"
RDEPENDS:${PN}-dev = ""
