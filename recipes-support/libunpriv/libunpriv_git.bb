SUMMARY = "libcap privilege-drop helper for RDK"
HOMEPAGE = "https://github.com/rdkcentral/rdk-libunpriv"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

SRC_URI = "${CORE_GITHUB_ROOT}/rdk-libunpriv.git;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "1.0.2"

DEPENDS = "libcap jsoncpp"

inherit autotools pkgconfig

CXXFLAGS:append = " -I${STAGING_INCDIR} -I${STAGING_INCDIR}/jsoncpp"
CFLAGS:append = " -Wall -Werror -Wextra -Wno-unused-parameter"
