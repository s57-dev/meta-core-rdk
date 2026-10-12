SUMMARY = "ThunderJS — JS client library for Thunder/WPEFramework"
HOMEPAGE = "https://github.com/rdkcentral/ThunderJS"
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=cbffef879caf8b1db2d5173ff472c28c"

SRC_URI = "git://github.com/rdkcentral/ThunderJS.git;protocol=https;branch=master"
S = "${WORKDIR}/git"

PV = "1.0"

DEPENDS = "wpeframework"

do_configure[noexec] = "1"

do_compile() {
    # Prefer ws over isomorphic-ws for embedded node runtimes.
    sed -i 's/isomorphic-ws/ws/g' ${S}/module/thunderJS.js
}

do_install() {
    install -d ${D}${datadir}/thunderjs
    install -m 0644 ${S}/module/thunderJS.js ${D}${datadir}/thunderjs/thunderJS.js
}

FILES:${PN} = "${datadir}/thunderjs/*"
