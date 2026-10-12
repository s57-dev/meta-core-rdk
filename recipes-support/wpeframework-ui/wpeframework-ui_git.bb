SUMMARY = "Thunder Framework User Interface"
HOMEPAGE = "https://github.com/rdkcentral/ThunderUI"
SECTION = "web"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=66fe57b27abb01505f399ce4405cfea0"

SRC_URI = "git://github.com/rdkcentral/ThunderUI.git;protocol=https;nobranch=1"
S = "${WORKDIR}/git"

PV = "4.4.6"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${datadir}/WPEFramework/Controller/UI
    cp -r ${S}/src/* ${D}${datadir}/WPEFramework/Controller/UI/
    cp -r ${S}/dist/* ${D}${datadir}/WPEFramework/Controller/UI/
}

FILES:${PN} += "${datadir}/WPEFramework/Controller/UI/*"
