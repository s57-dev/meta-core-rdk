SUMMARY = "C library for WebPA Data Model Parser (WDMP)"
HOMEPAGE = "https://github.com/xmidt-org/wdmp-c"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/xmidt-org/wdmp-c.git;protocol=https;nobranch=1 \
           file://wdmp-c.patch \
           "
S = "${WORKDIR}/git"

PV = "1.0.0"

DEPENDS = "cjson cimplog"

inherit pkgconfig cmake

EXTRA_OECMAKE += "-DBUILD_TESTING=OFF -DBUILD_YOCTO=true"
LDFLAGS += "-lm"

# Unversioned .so — keep in the runtime package.
FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/*.so"
