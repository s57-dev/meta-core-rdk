SUMMARY = "RDK sysint scripts and systemd units (Core slim)"
HOMEPAGE = "https://github.com/rdkcentral/sysint"
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=f36198fb804ffbe39b5b2c336ceef9f8"

PV = "6.0.6"

SRC_URI = "${CORE_GITHUB_ROOT}/sysint;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

# Script package only — no compile, no crashupload/syslog-ng layer classes.
do_configure[noexec] = "1"
do_compile[noexec] = "1"
CLEANBROKEN = "1"

RDEPENDS:${PN} += "bash busybox"

inherit systemd

do_install() {
    install -d ${D}${base_libdir}/rdk
    install -m 0755 ${S}/lib/rdk/* ${D}${base_libdir}/rdk

    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/rfcdefaults
    install -m 0644 ${S}/etc/*.properties ${D}${sysconfdir} || true
    install -m 0644 ${S}/etc/*.conf ${D}${sysconfdir} || true
    if [ -f ${S}/etc/env_setup.sh ]; then
        install -m 0644 ${S}/etc/env_setup.sh ${D}${sysconfdir}
    fi
    if [ -f ${S}/etc/rfcdefaults/sysint-generic.ini ]; then
        install -m 0755 ${S}/etc/rfcdefaults/sysint-generic.ini ${D}${sysconfdir}/rfcdefaults/sysint-generic.ini
    fi

    install -d ${D}${base_bindir}
    install -d ${D}${systemd_unitdir}/system

    # Core subset of units used by maintenance / network bring-up paths
    for unit in \
        log-rdk-start.service \
        previous-log-backup.service \
        vitalprocess-info.timer \
        vitalprocess-info.service \
        logrotate.service \
        logrotate.timer \
        dump-backup.service \
        disk-threshold-check.service \
        disk-threshold-check.timer \
        iptables.service \
        update-device-details.service \
        ntp-event.service \
        ntp-event.path \
        network-connection-stats.service \
        network-connection-stats.timer \
        NM_Bootstrap.service \
        zram.service \
        notify-network-ready.service \
        network-up.path \
        network-up.target \
        network-up.timer \
        ntp-time-sync.path \
        ntp-time-sync.target \
        ntp-time-sync-event.service \
        ntp-time-sync.timer \
        system-time-set.path \
        system-time-set.target \
        system-time-event.service \
        oops-dump.service; do
        if [ -f ${S}/systemd_units/${unit} ]; then
            install -m 0644 ${S}/systemd_units/${unit} ${D}${systemd_unitdir}/system/${unit}
        fi
    done

    # Drop scripts replaced by other Core components or unused on qemu
    rm -f ${D}${base_libdir}/rdk/deviceInitiatedFWDnld.sh
    rm -f ${D}${base_libdir}/rdk/swupdate_utility.sh
    rm -f ${D}${base_libdir}/rdk/uploadDumps.sh
    rm -f ${D}${base_libdir}/rdk/lighttpd_utility.sh
    rm -f ${D}${base_libdir}/rdk/runPod.sh
    rm -f ${D}${base_libdir}/rdk/runSnmp.sh
    rm -f ${D}${base_libdir}/rdk/runRMFStreamer
    rm -f ${D}${base_libdir}/rdk/runVodClientApp
    rm -f ${D}${base_libdir}/rdk/adddefaultgateway.sh
    rm -f ${D}${base_libdir}/rdk/pNexus.sh
    rm -f ${D}${base_libdir}/rdk/stackCalls.sh
    rm -f ${D}${base_libdir}/rdk/watchdog-starter
    rm -f ${D}${base_libdir}/rdk/zcip.script
    rm -f ${D}${base_libdir}/rdk/emmc_format.sh
    rm -f ${D}${base_libdir}/rdk/hddStatusLogger.sh
    rm -f ${D}${base_libdir}/rdk/htmlDiagPreSetup.sh
    rm -f ${D}${base_libdir}/rdk/rfStatisticsCheck.sh
    rm -f ${D}${base_libdir}/rdk/updateMountFailureCount.sh
    rm -f ${D}${base_libdir}/rdk/reportFailuresToSplunk.sh

    if [ -f ${D}${base_libdir}/rdk/imageFlasher.sh ]; then
        mv ${D}${base_libdir}/rdk/imageFlasher.sh ${D}${base_libdir}/rdk/imageFlasher_generic.sh
    fi
    if [ -f ${D}${base_libdir}/rdk/init-zram.sh ]; then
        mv ${D}${base_libdir}/rdk/init-zram.sh ${D}${base_libdir}/rdk/init-zram_generic.sh
    fi
    if [ -f ${D}${sysconfdir}/common.properties ]; then
        mv ${D}${sysconfdir}/common.properties ${D}${sysconfdir}/common-generic.properties
    fi

    if [ -f ${D}${base_libdir}/rdk/iptables_init_xi ]; then
        mv ${D}${base_libdir}/rdk/iptables_init_xi ${D}${base_libdir}/rdk/iptables_init
    fi

    ln -sf /lib/rdk/rebootSTB.sh ${D}/rebootSTB.sh
    if [ -f ${D}${base_libdir}/rdk/timestamp ]; then
        ln -sf /lib/rdk/timestamp ${D}${base_bindir}/timestamp
    fi

    # Mark maintenance enabled for scripts that key off device-middleware.properties
    if [ ! -f ${D}${sysconfdir}/device-middleware.properties ]; then
        touch ${D}${sysconfdir}/device-middleware.properties
    fi
    echo "ENABLE_MAINTENANCE=true" >> ${D}${sysconfdir}/device-middleware.properties
}

SYSTEMD_SERVICE:${PN} = " \
    log-rdk-start.service \
    previous-log-backup.service \
    vitalprocess-info.timer \
    logrotate.timer \
    dump-backup.service \
    disk-threshold-check.timer \
    iptables.service \
    update-device-details.service \
    oops-dump.service \
    ntp-event.service \
    ntp-event.path \
    network-connection-stats.service \
    network-connection-stats.timer \
    NM_Bootstrap.service \
    zram.service \
    network-up.path \
    network-up.timer \
    ntp-time-sync.path \
    ntp-time-sync-event.service \
    ntp-time-sync.timer \
    system-time-set.path \
    system-time-event.service \
    notify-network-ready.service \
"

# Do not auto-enable the full sysint unit set on qemu (bring-up only via image).
SYSTEMD_AUTO_ENABLE = "disable"

FILES:${PN} += " \
    ${base_libdir}/rdk/* \
    ${systemd_unitdir}/system/* \
    ${sysconfdir}/* \
    ${base_bindir}/timestamp \
    /rebootSTB.sh \
"
