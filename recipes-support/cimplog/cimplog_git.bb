SUMMARY = "Simple logging library wrapping rdk-logger"
HOMEPAGE = "https://github.com/xmidt-org/cimplog"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/xmidt-org/cimplog.git;protocol=https;nobranch=1"
S = "${WORKDIR}/git"

PV = "1.0.0"

DEPENDS = "rdk-logger log4c"

inherit pkgconfig cmake

EXTRA_OECMAKE += "-DRDK_LOGGER=ON -DBUILD_TESTING=OFF -DBUILD_YOCTO=true"
