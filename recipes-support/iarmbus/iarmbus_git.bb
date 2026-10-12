SUMMARY = "IARM-Bus platform-agnostic IPC"
DESCRIPTION = "Allows applications to communicate by sending events or \
invoking remote procedure calls, independent of the underlying OS IPC."
HOMEPAGE = "https://github.com/rdkcentral/iarmbus"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

SRC_URI = "${CORE_GITHUB_ROOT}/iarmbus;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "1.0.7"

DEPENDS = "libxml2 dbus glib-2.0 systemd safec-common-wrapper"

inherit autotools pkgconfig systemd

CFLAGS:append = " -DENABLE_SD_NOTIFY -fPIC -DSAFEC_DUMMY_API"
LDFLAGS:append = " -lsystemd"

do_install:append() {
    install -d ${D}${includedir}/rdk/iarmbus
    install -m 0644 ${S}/core/include/*.h ${D}${includedir}/rdk/iarmbus/
    install -m 0644 ${S}/core/*.h ${D}${includedir}/rdk/iarmbus/

    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/conf/iarmbusd.service ${D}${systemd_unitdir}/system/

    install -d ${D}${libdir}
    install -m 0755 ${S}/conf/iarm-reboot.sh ${D}${libdir}/
    install -m 0755 ${S}/conf/iarm-monitor.sh ${D}${libdir}/
}

SYSTEMD_SERVICE:${PN} = "iarmbusd.service"

FILES:${PN} += " \
    ${systemd_unitdir}/system/iarmbusd.service \
    ${libdir}/iarm-reboot.sh \
    ${libdir}/iarm-monitor.sh \
"
