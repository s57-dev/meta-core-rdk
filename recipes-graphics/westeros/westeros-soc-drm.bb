require recipes-graphics/westeros/westeros.inc

SUMMARY = "Westeros GL component for DRM platforms"
LICENSE = "Apache-2.0"

LICENSE_LOCATION = "${S}/LICENSE"
LIC_FILES_CHKSUM = "file://${LICENSE_LOCATION};md5=7df5a8706277b586ca000838046993d1"
SRC_URI = "${WESTEROS_SOC_URI}"

S = "${WORKDIR}/git"

DEPENDS = "wayland virtual/egl glib-2.0 libdrm"

PROVIDES = "virtual/westeros-soc"
RPROVIDES:${PN} = "virtual/westeros-soc"

CFLAGS:append = " -I${STAGING_INCDIR}/libdrm"

SECURITY_CFLAGS:remove = "-fpie"
SECURITY_CFLAGS:remove = "-pie"

DEBIAN_NOAUTONAME:${PN} = "1"
DEBIAN_NOAUTONAME:${PN}-dbg = "1"
DEBIAN_NOAUTONAME:${PN}-dev = "1"
DEBIAN_NOAUTONAME:${PN}-staticdev = "1"

inherit autotools pkgconfig
