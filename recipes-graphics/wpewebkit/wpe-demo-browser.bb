SUMMARY = "Auto-start WPE MiniBrowser with HLS.js demo URL"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://wpe-demo-browser.service"

inherit systemd

RDEPENDS:${PN} = "wpewebkit-minibrowser westeros"
SYSTEMD_SERVICE:${PN} = "wpe-demo-browser.service"
SYSTEMD_AUTO_ENABLE:${PN} = "enable"

S = "${WORKDIR}"

do_install() {
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/wpe-demo-browser.service \
        ${D}${systemd_system_unitdir}/wpe-demo-browser.service
}

FILES:${PN} = "${systemd_system_unitdir}/wpe-demo-browser.service"
