SUMMARY = "A fast and lightweight OCI runtime (crun)"
DESCRIPTION = "OCI Container Runtime required by Dobby."
HOMEPAGE = "https://github.com/containers/crun"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

PV = "1.7.2"

SRC_URI = "gitsm://github.com/containers/crun;protocol=https;branch=main"
SRCREV = "0356bf4aff9a133d655dc13b1d9ac9424706cac4"

DEPENDS = "yajl libseccomp libtool libcap"

S = "${WORKDIR}/git"

inherit autotools-brokensep pkgconfig

do_configure:prepend() {
    cd ${S}
    ./autogen.sh
}

EXTRA_OECONF = "--disable-systemd"
