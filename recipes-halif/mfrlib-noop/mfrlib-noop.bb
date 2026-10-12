SUMMARY = "No-op MFR library (virtual/mfrlib) for Core qemu"
SECTION = "libs"
DESCRIPTION = "Minimal stub implementing mfrMgr APIs from iarmmgrs-hal-headers \
so PowerManager / iarmmgrs dependents can link without vendor MFR code."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "iarmmgrs-hal-headers"

SRC_URI = "file://mfrlib.c"

PROVIDES = "virtual/mfrlib"
RPROVIDES:${PN} = "virtual/mfrlib"

PACKAGE_ARCH = "${MACHINE_ARCH}"

CFLAGS += " \
    -I${STAGING_INCDIR}/rdk/iarmmgrs-hal \
"

SOVER = "0.0.0"
TARGET_CC_ARCH += "${LDFLAGS}"

do_configure[noexec] = "1"

do_compile() {
    ${CC} -shared -fPIC -Wl,-soname,libRDKMfrLib.so.0 ${CFLAGS} \
        ${WORKDIR}/mfrlib.c -o ${B}/libRDKMfrLib.so.${SOVER}
}

do_install() {
    install -d ${D}${libdir}
    install -m 0755 ${B}/libRDKMfrLib.so.${SOVER} ${D}${libdir}
    ln -sf libRDKMfrLib.so.${SOVER} ${D}${libdir}/libRDKMfrLib.so
    ln -sf libRDKMfrLib.so.${SOVER} ${D}${libdir}/libRDKMfrLib.so.0
}

FILES:${PN} = "${libdir}/libRDKMfrLib.so*"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"
