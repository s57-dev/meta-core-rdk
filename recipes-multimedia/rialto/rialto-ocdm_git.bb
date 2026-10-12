SUMMARY = "Rialto OpenCDM adapter"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=1fa88b316b1ce25ab7d95ae4d854ec8f"

require rialto_revision.inc

PV = "${RIALTO_OCDM_VERSION}"
PR = "${RIALTO_OCDM_PR}"

SRCREV = "${RIALTO_OCDM_SRCREV}"
SRC_URI = "${CORE_GITHUB_ROOT}/rialto-ocdm;${CORE_GITHUB_SRC_URI_SUFFIX}"

DEPENDS = "openssl jsoncpp glib-2.0 gstreamer1.0 gstreamer1.0-plugins-base \
           wpeframework-tools-native wpeframework-clientlibraries \
           protobuf protobuf-native rialto opencdm-stub"

S = "${WORKDIR}/git"

inherit pkgconfig cmake features_check

REQUIRED_DISTRO_FEATURES = "enable_rialto"

DEBIAN_NOAUTONAME:${PN} = "1"
DEBIAN_NOAUTONAME:${PN}-dev = "1"
