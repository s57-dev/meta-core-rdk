SUMMARY = "Minimal readline-style line editing library"
DESCRIPTION = "linenoise library used by rbus CLI and related tools."
HOMEPAGE = "https://github.com/antirez/linenoise"
SECTION = "libs"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=faa55ac8cbebebcb4a84fe1ea2879578"

PV = "1.0.0+git${SRCPV}"

SRC_URI = "git://github.com/antirez/linenoise.git;protocol=https;nobranch=1 \
           file://001-linenoise-history.patch \
           file://002-linenoise-cmakelists.patch \
           "
# SRCREV from conf/include/core-v-versions.inc
S = "${WORKDIR}/git"

inherit cmake pkgconfig
