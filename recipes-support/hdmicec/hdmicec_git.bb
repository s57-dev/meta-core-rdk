SUMMARY = "RDK HDMI CEC middleware library"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0.11"

SRC_URI = "${CORE_GITHUB_ROOT}/hdmicec;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "glib-2.0 dbus iarmbus devicesettings devicesettings-hal-headers \
           hdmicecheader virtual/vendor-hdmicec-hal telemetry safec-common-wrapper"
RDEPENDS:${PN} = "devicesettings telemetry"

ASNEEDED = ""
ALLOW_EMPTY:${PN} = "1"

inherit autotools pkgconfig

CFLAGS:append = " -fPIC -DSAFEC_DUMMY_API -I${STAGING_INCDIR}/rdk/halif/ds-hal -I${STAGING_INCDIR}/ccec/drivers"
CXXFLAGS:append = " -fPIC -DSAFEC_DUMMY_API -I${STAGING_INCDIR}/rdk/halif/ds-hal -I${STAGING_INCDIR}/ccec/drivers"
