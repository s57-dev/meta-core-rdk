SUMMARY = "RDK Firmware Upgrader daemon"
HOMEPAGE = "https://github.com/rdkcentral/rdkfwupdater"
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=70514b59ff7b36bbbc30d093c6814d8e"

PV = "1.8.5"

SRC_URI = "${CORE_GITHUB_ROOT}/rdkfwupdater;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "cjson curl rdk-logger rbus rfc telemetry iarmbus iarmmgrs dbus glib-2.0 commonutilities libsyswrapper safec-common-wrapper"
RDEPENDS:${PN} += "bash"

inherit autotools pkgconfig systemd

EXTRA_OECONF = "--enable-rfcapi --enable-t2api"

CFLAGS:append = " -I${STAGING_INCDIR}/wdmp-c \
                  -I${STAGING_INCDIR}/rdk/iarmbus \
                  -I${STAGING_INCDIR}/rdk/iarmmgrs/sysmgr \
                  -I${STAGING_INCDIR}/glib-2.0 \
                  -I${STAGING_LIBDIR}/glib-2.0/include \
                  -DRDK_LOGGER \
                  -Wall -Wno-error"
CXXFLAGS:append = " -Wall -Wno-error"

# Provide both historical PN spellings used across recipes.
RPROVIDES:${PN} += "rdkfwupdater"

FILES:${PN} += "${base_libdir}/rdk/*"
INSANE_SKIP:${PN} += "installed-vs-shipped"
