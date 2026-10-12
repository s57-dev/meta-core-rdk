SUMMARY = "System Time Manager daemon"
HOMEPAGE = "https://github.com/rdkcentral/systemtimemgr"
SECTION = "console/utils"
DESCRIPTION = "System time manager using factory plugins and chrony."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "1.7.0"

SRC_URI = "${CORE_GITHUB_ROOT}/systemtimemgr;${CORE_GITHUB_SRC_URI_SUFFIX} \
           file://systimemgr.conf \
           file://secure.conf \
           "
S = "${WORKDIR}/git"

DEPENDS = "systimemgrinetrface systimemgrfactory rdk-logger libsyswrapper \
           wpeframework wpeframework-clientlibraries telemetry libchronyctl"
RDEPENDS:${PN} += "systimemgrfactory chrony"

inherit autotools pkgconfig systemd

ASNEEDED = ""

EXTRA_OECONF = "--enable-t2api=yes"

CXXFLAGS:append = " -I${STAGING_INCDIR}/WPEFramework \
                    -I${STAGING_INCDIR}/WPEFramework/powercontroller \
                    -Wall -Wno-error"
LDFLAGS:append = " -lWPEFrameworkPowerController -lWPEFrameworkCore \
                   -lWPEFrameworkWebSocket -lsystimerfactory -lchronyctl"

do_install:append() {
    install -d ${D}${systemd_unitdir}/system
    install -d ${D}${systemd_unitdir}/system/systimemgr.service.d
    install -d ${D}${sysconfdir}
    install -m 644 ${S}/systemd_units/systimemgr.service ${D}${systemd_unitdir}/system
    install -m 644 ${WORKDIR}/secure.conf ${D}${systemd_unitdir}/system/systimemgr.service.d
    install -m 644 ${WORKDIR}/systimemgr.conf ${D}${sysconfdir}/
}

SYSTEMD_SERVICE:${PN} = "systimemgr.service"
SYSTEMD_AUTO_ENABLE = "disable"

FILES:${PN}:append = " ${sysconfdir}/systimemgr.conf \
                       ${systemd_unitdir}/system/systimemgr.service \
                       ${systemd_unitdir}/system/systimemgr.service.d/secure.conf"
