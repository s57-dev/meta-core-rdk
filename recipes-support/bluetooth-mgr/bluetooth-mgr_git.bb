SUMMARY = "RDK Bluetooth Manager"
HOMEPAGE = "https://github.com/rdkcentral/bluetooth_mgr"
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0.12"

SRC_URI = "${CORE_GITHUB_ROOT}/bluetooth_mgr;${CORE_GITHUB_SRC_URI_SUFFIX} \
           file://btmgr.conf \
           file://btrMgr_platform_spec.h \
"
S = "${WORKDIR}/git"

DEPENDS = "bluetooth-core cjson glib-2.0 dbus iarmbus rdk-logger rfc \
           commonutilities libsyswrapper safec-common-wrapper \
           wpeframework-clientlibraries \
           gstreamer1.0 gstreamer1.0-plugins-base openssl"
RDEPENDS:${PN} = "bluetooth-core cjson rdk-logger"

inherit autotools pkgconfig systemd

EXTRA_OECONF += " \
    --enable-rpc \
    --enable-rdk-logger=yes \
    --enable-safec=no \
    --enable-gstreamer1=yes \
    --enable-acm=no \
    --enable-ac_rmf=no \
    --enable-autoconnectfeature=yes \
    --enable-rdktv-build=yes \
    --enable-streaming-in=no \
    --enable-systemd-notify \
"

CFLAGS:append = " -fPIC -DSAFEC_DUMMY_API -Wno-stringop-truncation -Wno-error=stringop-truncation"
CPPFLAGS:append = " -I${STAGING_INCDIR}/WPEFramework/powercontroller -I${S}/soc/include"
CXXFLAGS:append = " -I${STAGING_INCDIR}/WPEFramework/powercontroller"

do_configure:prepend() {
    install -d ${S}/soc/include
    install -m 0644 ${WORKDIR}/btrMgr_platform_spec.h ${S}/soc/include/
}

# Upstream hardcodes -Werror in AM_CPPFLAGS
do_configure:append() {
    find ${B} -name Makefile -exec sed -i 's/ -Werror//g; s/-Werror //g' {} +
}

BTMGR_STARTUP_DELAY ?= "3"

do_install:append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/conf/btmgr.service ${D}${systemd_unitdir}/system

    sed -i -- "s/##BTMGR_STARTUP_DELAY##/${BTMGR_STARTUP_DELAY}/" ${WORKDIR}/btmgr.conf
    install -d ${D}${systemd_unitdir}/system/btmgr.service.d
    install -m 0644 ${WORKDIR}/btmgr.conf ${D}${systemd_unitdir}/system/btmgr.service.d/btmgr.conf
}

SYSTEMD_SERVICE:${PN} = "btmgr.service"

FILES:${PN} += "${systemd_unitdir}/system/btmgr.service"
FILES:${PN} += "${systemd_unitdir}/system/btmgr.service.d/btmgr.conf"
