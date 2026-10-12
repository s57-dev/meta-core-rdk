SUMMARY = "IARMMGRS HAL header definitions"
HOMEPAGE = "https://github.com/rdkcentral/iarmmgrs"
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=83a31d934b0cc2ab2d44a329445b4366"

SRC_URI = "${CORE_GITHUB_ROOT}/iarmmgrs;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "1.1.10"

inherit allarch

DEPENDS = "deepsleep-manager-headers power-manager-headers"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${includedir}/rdk/iarmmgrs-hal
    install -m 0644 ${S}/hal/include/comcastIrKeyCodes.h ${D}${includedir}/rdk/iarmmgrs-hal
    install -m 0644 ${S}/mfr/include/mfrMgr.h ${D}${includedir}/rdk/iarmmgrs-hal
    install -m 0644 ${S}/mfr/include/mfrTypes.h ${D}${includedir}/rdk/iarmmgrs-hal
    install -m 0644 ${S}/mfr/common/include/mfrApi.h ${D}${includedir}/rdk/iarmmgrs-hal
    install -m 0644 ${S}/mfr/include/mfr_wifi_types.h ${D}${includedir}/rdk/iarmmgrs-hal
    install -m 0644 ${S}/mfr/include/mfr_wifi_api.h ${D}${includedir}/rdk/iarmmgrs-hal
    install -m 0644 ${S}/mfr/include/mfr_temperature.h ${D}${includedir}/rdk/iarmmgrs-hal
    install -m 0644 ${S}/sysmgr/include/sysMgr.h ${D}${includedir}/rdk/iarmmgrs-hal
    install -m 0644 ${S}/deviceUpdateMgr/include/deviceUpdateMgr.h ${D}${includedir}/rdk/iarmmgrs-hal
    install -m 0644 ${S}/maintenance/include/maintenanceMGR.h ${D}${includedir}/rdk/iarmmgrs-hal
}

ALLOW_EMPTY:${PN} = "1"
RDEPENDS:${PN}-dev = ""
