SUMMARY = "RDK logging library (log4c-based)"
DESCRIPTION = "Logging interfaces used across RDK Core middleware components."
HOMEPAGE = "https://github.com/rdkcentral/rdk_logger"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

SRC_URI = "${CORE_GITHUB_ROOT}/rdk_logger;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "3.1.0"

DEPENDS = "log4c glib-2.0"

inherit autotools pkgconfig

# No safec in Core Phase 2 — use the dummy API path.
CFLAGS:append = " -fPIC -DSAFEC_DUMMY_API -DLOGMILESTONE"

do_install:append() {
    install -d ${D}${base_libdir}/rdk
    install -m 0755 ${S}/scripts/logMilestone.sh ${D}${base_libdir}/rdk/
}

FILES:${PN} += "${base_libdir}/rdk/"
