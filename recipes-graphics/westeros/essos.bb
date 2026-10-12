SUMMARY = "Essos EGL/Wayland application helper library"
DESCRIPTION = "Separate from Westeros (--disable-essos). qemuarm builds without \
essosrmgr (resmgr PACKAGECONFIG off — CMF-only source)."

include westeros.inc

LIC_FILES_CHKSUM = "file://${LICENSE_LOCATION};md5=7df5a8706277b586ca000838046993d1"

SRC_URI = "${ESSOS_URI}"

SRCREV_FORMAT = "essos"

S = "${WORKDIR}/git"

# No essosrmgr on Core qemu — enable "free" resmgr path.
DEPENDS = "wayland virtual/egl libxkbcommon westeros"
RDEPENDS:${PN} += "westeros"

REQUIRED_DISTRO_FEATURES += "wayland"

inherit autotools pkgconfig features_check

PACKAGECONFIG ??= "westeros"
PACKAGECONFIG[westeros] = "--disable-essoswesterosfree,--enable-essoswesterosfree,westeros-simpleshell virtual/westeros-soc"
PACKAGECONFIG[resmgr] = "--disable-essosresmgrfree,--enable-essosresmgrfree,essosrmgr"

DEBIAN_NOAUTONAME:${PN} = "1"
DEBIAN_NOAUTONAME:${PN}-dbg = "1"
DEBIAN_NOAUTONAME:${PN}-dev = "1"
DEBIAN_NOAUTONAME:${PN}-staticdev = "1"

PACKAGES =+ "${PN}-examples"
FILES:${PN}-examples += "${bindir}/*"
