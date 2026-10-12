SUMMARY = "memcr memory hibernate/restore tool"
HOMEPAGE = "https://github.com/LibertyGlobal/memcr"
SECTION = "utils"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=606d8cf603cf7007e6c1fffda5da9ffa"

SRC_URI = "git://github.com/LibertyGlobal/memcr.git;protocol=https;branch=main \
           file://memcr.service \
           file://0001-RDK-47153-Option-to-set-dump-dir-and-compression-per.patch \
           file://0001-RDK-54059-retry-ptrace-seize-on-EPERM.patch \
           "
S = "${WORKDIR}/git"

PV = "1.0.3"

DEPENDS = "util-linux-native lz4 openssl zstd"
RDEPENDS:${PN} = "libcrypto lz4 zstd"

inherit systemd

SYSTEMD_SERVICE:${PN} = "memcr.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_compile() {
    oe_runmake COMPRESS_LZ4=1 COMPRESS_ZSTD=1 CHECKSUM_MD5=1 ENCRYPT=1
}

do_install() {
    install -D -m 0755 ${B}/memcr ${D}${bindir}/memcr
    install -D -m 0755 ${B}/parasite.bin ${D}${bindir}/parasite.bin
    install -D -m 0755 ${B}/memcr-client ${D}${bindir}/memcr-client
    install -d ${D}${libdir}/memcr
    install -m 0644 ${B}/libencrypt.so ${D}${libdir}/memcr/libencrypt.so
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${WORKDIR}/memcr.service ${D}${systemd_unitdir}/system/
}

INSANE_SKIP:${PN} += "ldflags"

FILES:${PN} += " \
    ${bindir}/memcr \
    ${bindir}/parasite.bin \
    ${bindir}/memcr-client \
    ${libdir}/memcr/libencrypt.so \
    ${systemd_unitdir}/system/memcr.service \
"
