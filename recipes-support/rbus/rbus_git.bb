SUMMARY = "rbus library and daemon"
DESCRIPTION = "RDK Bus (rbus) IPC library, rtrouted daemon, and session manager."
HOMEPAGE = "https://github.com/rdkcentral/rbus"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ed63516ecab9f06e324238dd2b259549"

SRC_URI = "${CORE_GITHUB_ROOT}/rbus.git;protocol=${CORE_GITHUB_PROTOCOL};nobranch=1"
S = "${WORKDIR}/git"

PV = "2.14.0"

DEPENDS = "cjson msgpack-c rdk-logger linenoise"

inherit cmake systemd pkgconfig

# Core Phase 2: no breakpad wrapper classes — keep crash reporting off.
EXTRA_OECMAKE += " \
    -DCMAKE_BUILD_TYPE=Release \
    -DINCLUDE_BREAKPAD=OFF \
    -DMSG_ROUNDTRIP_TIME=ON \
    -DENABLE_RDKLOGGER=ON \
    -DBUILD_RBUS_SAMPLE_APPS=OFF \
    -DBUILD_RBUS_TEST_APPS=OFF \
    -DENABLE_UNIT_TESTING=OFF \
"

CFLAGS:append = " -Wno-format-truncation"
CXXFLAGS:append = " -Wno-format-truncation"

do_install:append() {
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/conf/rbus.service ${D}${systemd_unitdir}/system/
    install -m 0644 ${S}/conf/rbus_session_mgr.service ${D}${systemd_unitdir}/system/
    install -D -m 0644 ${S}/conf/rbus_rdkv.conf \
        ${D}${systemd_unitdir}/system/rbus.service.d/rbus_rdkv.conf
}

FILES:${PN} += "${systemd_unitdir}/system/*"

SYSTEMD_SERVICE:${PN} = "rbus.service rbus_session_mgr.service"
