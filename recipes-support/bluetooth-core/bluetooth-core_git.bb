SUMMARY = "RDK Bluetooth core library"
HOMEPAGE = "https://github.com/rdkcentral/bluetooth"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

SRC_URI = "${CORE_GITHUB_ROOT}/bluetooth;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "1.0.11"

DEPENDS = "dbus bluez5 rdk-logger telemetry safec-common-wrapper"
RDEPENDS:${PN} = "dbus bluez5 rdk-logger telemetry"

inherit autotools pkgconfig

CFLAGS:append = " -fPIC -DSAFEC_DUMMY_API -Wno-stringop-truncation -Wno-stringop-overflow -Wno-error=stringop-truncation -Wno-error=stringop-overflow"
CPPFLAGS:append = " -Wno-stringop-truncation -Wno-stringop-overflow -Wno-error=stringop-truncation -Wno-error=stringop-overflow"

# Upstream Makefiles hardcode -Werror in AM_CPPFLAGS; demote for newer GCC.
do_configure:append() {
    find ${B} -name Makefile -exec sed -i 's/ -Werror//g; s/-Werror //g' {} +
}

EXTRA_OECONF += " \
    --enable-unsupportedgamepad=yes \
    --enable-btr-ifce=bluez5 \
    --enable-rdk-logger=yes \
    --enable-safec=no \
"
