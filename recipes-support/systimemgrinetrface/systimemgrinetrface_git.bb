SUMMARY = "System Time Manager interface headers"
SECTION = "devel"
DESCRIPTION = "Timer publishing and subscription interface headers."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

SRC_URI = "${CORE_GITHUB_ROOT}/systemtimemgr;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git/interface"

PV = "1.7.0"

inherit autotools pkgconfig

ALLOW_EMPTY:${PN} = "1"
