SUMMARY = "IARMMGRS (Core slim: utils + sysmgr + mfr)"
HOMEPAGE = "https://github.com/rdkcentral/iarmmgrs"
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=83a31d934b0cc2ab2d44a329445b4366"

PV = "1.1.21"

SRC_URI = "${CORE_GITHUB_ROOT}/iarmmgrs;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

# Slim Core set: skip dsmgr (ermgr/essos) for now; keep sysmgr + mfr.
DEPENDS = "curl yajl dbus iarmbus rdk-logger openssl systemd libsyswrapper \
           rfc libunpriv boost c-ares \
           iarmmgrs-hal-headers deepsleep-manager-headers power-manager-headers \
           wpeframework-clientlibraries virtual/mfrlib \
           virtual/vendor-pwrmgr-hal virtual/vendor-deepsleepmgr-hal \
           safec-common-wrapper telemetry"
RDEPENDS:${PN} = "iarmbus rfc virtual/mfrlib"

inherit pkgconfig systemd

EXTRA_OECONF = "--enable-yocto"
ASNEEDED = ""

INCLUDE_DIRS = " \
    -I${S}/mfr/include \
    -I${S}/sysmgr/include \
    -I${S}/utils \
    -I${STAGING_INCDIR}/wdmp-c \
    -I${STAGING_INCDIR}/rdk/iarmbus \
    -I${STAGING_INCDIR}/rdk/halif/power-manager \
    -I${STAGING_INCDIR}/rdk/halif/deepsleep-manager \
    -I${STAGING_INCDIR}/rdk/iarmmgrs-hal \
    -I${STAGING_INCDIR}/WPEFramework/powercontroller \
    -I${STAGING_INCDIR}/glib-2.0 \
    -I${STAGING_LIBDIR}/glib-2.0/include \
"

CFLAGS += "-std=c++11 -fPIC -D_REENTRANT -Wall ${INCLUDE_DIRS}"
CFLAGS += "-DENABLE_SD_NOTIFY -DUSE_YAJL2 -DSAFEC_DUMMY_API"
CFLAGS += "-D_ENABLE_RESET_LOGIC -D_ENABLE_WAKEUP_KEY"
CFLAGS += "-DENABLE_DEEP_SLEEP -DENABLE_DEEPSLEEP_WAKEUP_EVT"
CFLAGS += "-D_DISABLE_RESET_SEQUENCE -D_DISABLE_SCHD_REBOOT_AT_DEEPSLEEP"
CFLAGS += "-DPLATCO_BOOTTO_STANDBY -DUSE_WAKEUP_TIMER_EVT"
CFLAGS:remove = "-Werror"

LDFLAGS += " -lpthread -lglib-2.0 -ldbus-1 -lIARMBus -lsystemd -lsecure_wrapper \
             -lprivilege -ldl -lWPEFrameworkPowerController -ltelemetry_msgsender \
             -lRDKMfrLib -liarmmgrs-power-hal -liarmmgrs-deepsleep-hal"

EXTRA_OEMAKE += "-e MAKEFLAGS="

do_configure() {
    # Autotools layout exists but Core builds selected subdirs via make -C.
    if [ -f ${S}/configure ]; then
        oe_runconf
    fi
}

do_compile() {
    oe_runmake -B -C ${S}/utils/
    LDFLAGS="${LDFLAGS}" CFLAGS="-DENABLE_SD_NOTIFY ${CFLAGS}" oe_runmake -B -C ${S}/sysmgr/

    export COMCAST_PLATFORM=XI4
    export CFLAGS="${CFLAGS} -DENABLE_SD_NOTIFY -DRDK_MFRLIB_NAME='\"libRDKMfrLib.so.0\"'"
    export LDFLAGS="${LDFLAGS} -lRDKMfrLib -L${S}/utils -liarmUtils -lsystemd -ldl"
    oe_runmake -B -C ${S}/mfr
}

do_install() {
    install -d ${D}${bindir} ${D}${libdir} ${D}${systemd_unitdir}/system
    install -d ${D}${includedir}/rdk/iarmmgrs/sysmgr
    install -d ${D}${includedir}/rdk/iarmmgrs/mfr
    install -d ${D}${includedir}/rdk/iarmmgrs/receiver

    install -m 0644 ${S}/sysmgr/include/*.h ${D}${includedir}/rdk/iarmmgrs/sysmgr/
    install -m 0644 ${S}/mfr/include/*.h ${D}${includedir}/rdk/iarmmgrs/mfr/
    install -m 0644 ${S}/receiver/include/*.h ${D}${includedir}/rdk/iarmmgrs/receiver/

    install -m 0755 ${S}/sysmgr/*Main ${D}${bindir}
    install -m 0755 ${S}/mfr/*Main ${D}${bindir}

    install -m 0755 ${S}/utils/libiarmUtils.so ${D}${libdir}/libiarmUtils.so.0.0.0
    ln -sf libiarmUtils.so.0.0.0 ${D}${libdir}/libiarmUtils.so

    install -m 0644 ${S}/conf/sysmgr.service ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/conf/mfrmgr.service ${D}${systemd_unitdir}/system
}

SYSTEMD_SERVICE:${PN} = "sysmgr.service mfrmgr.service"

FILES:${PN} += "${systemd_unitdir}/system/*.service ${libdir}/*"
FILES_SOLIBSDEV = ""
SOLIBS = ".so"
INSANE_SKIP:${PN} += "dev-so"
