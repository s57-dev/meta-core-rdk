SUMMARY = "Rialto GStreamer sinks plugin"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://COPYING.LGPL;md5=23c2a5e0106b99d75238986559bb5fc6"

require rialto_revision.inc

PV = "${RIALTO_GSTREAMER_VERSION}"
PR = "${RIALTO_GSTREAMER_PR}"

SRCREV = "${RIALTO_GSTREAMER_SRCREV}"
SRC_URI = "${CORE_GITHUB_ROOT}/rialto-gstreamer;${CORE_GITHUB_SRC_URI_SUFFIX}"

DEPENDS = "openssl jsoncpp glib-2.0 gstreamer1.0 gstreamer1.0-plugins-base \
           wpeframework-clientlibraries protobuf protobuf-native rialto rialto-ocdm"

S = "${WORKDIR}/git"

inherit pkgconfig cmake features_check

REQUIRED_DISTRO_FEATURES = "enable_rialto"

FILES:${PN} += "${libdir}/gstreamer-1.0/libgstrialtosinks.so"

DEBIAN_NOAUTONAME:${PN} = "1"
DEBIAN_NOAUTONAME:${PN}-dev = "1"
