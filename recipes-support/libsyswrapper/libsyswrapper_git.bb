SUMMARY = "Secure wrapper for system calls"
HOMEPAGE = "https://github.com/rdkcentral/libSyscallWrapper"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

SRC_URI = "${CORE_GITHUB_ROOT}/libSyscallWrapper;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "1.3.3"

DEPENDS = "rdk-logger"

inherit autotools pkgconfig

CFLAGS:append = " -Wall -Werror"
CXXFLAGS:append = " -Wall -Werror"

DEBIAN_NOAUTONAME:${PN} = "1"
