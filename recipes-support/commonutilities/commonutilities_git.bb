SUMMARY = "RDK common utilities library"
HOMEPAGE = "https://github.com/rdkcentral/common_utilities"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=24691c8ce48996ecd1102d29eab1216e"

SRC_URI = "${CORE_GITHUB_ROOT}/common_utilities;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "1.5.7"

DEPENDS = "cjson curl rdk-logger rdkcertconfig libsyswrapper safec-common-wrapper"

inherit autotools pkgconfig

# No Cisco safec — dummy API + secure_wrapper only.
CFLAGS:append = " -fPIC -DSAFEC_DUMMY_API -DRDK_LOGGER -Wno-stringop-truncation"
LDFLAGS:append = " -lsecure_wrapper"
