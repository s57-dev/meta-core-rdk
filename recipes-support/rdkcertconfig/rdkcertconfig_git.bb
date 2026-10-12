SUMMARY = "Certificate selector/locator library"
HOMEPAGE = "https://github.com/rdkcentral/rdk-cert-config"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

SRC_URI = "${CORE_GITHUB_ROOT}/rdk-cert-config;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"
B = "${WORKDIR}/git"

PV = "1.0.5"

DEPENDS = "libsyswrapper"

inherit autotools pkgconfig

CFLAGS:append = " -DCONFIG_ERROR_ENABLED -Wall -Werror"
CXXFLAGS:append = " -Wall -Werror"
TARGET_CC_ARCH += "${LDFLAGS}"

EXTRA_OECONF += "--enable-rdklogger"

# BOM proposed PN is rdk-cert-config; keep RDKE PN for DEPENDS compatibility.
PROVIDES += "rdk-cert-config"
