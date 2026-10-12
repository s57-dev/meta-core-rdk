SUMMARY = "Device Settings HALIF header definitions"
HOMEPAGE = "https://github.com/rdkcentral/rdk-halif-device_settings"
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

SRC_URI = "${CORE_GITHUB_ROOT}/rdk-halif-device_settings;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "7.0.0"

inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${includedir}/rdk/halif/ds-hal
    install -m 0644 ${S}/include/*.h ${D}${includedir}/rdk/halif/ds-hal/
}

ALLOW_EMPTY:${PN} = "1"
RDEPENDS:${PN}-dev = ""
