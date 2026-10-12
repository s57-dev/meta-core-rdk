SUMMARY = "RDK Telemetry 2.0"
HOMEPAGE = "https://github.com/rdkcentral/telemetry"
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

SRC_URI = "${CORE_GITHUB_ROOT}/telemetry;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "1.9.9"

DEPENDS = "curl cjson glib-2.0 breakpad-wrapper rbus libsyswrapper libunpriv rdk-logger safec-common-wrapper"
RDEPENDS:${PN} += "curl cjson glib-2.0 rbus"

inherit pkgconfig autotools systemd python3native

# Core: no webconfig-framework yet — keep telemetry usable without it.
EXTRA_OECONF += "--without-webconfig-framework"

CFLAGS:append = " -Wall -Werror -Wextra -Wno-stringop-truncation \
                  -DDROP_ROOT_PRIV -DENABLE_MTLS \
                  -fPIC -DSAFEC_DUMMY_API"
CXXFLAGS:append = " -DINCLUDE_BREAKPAD"

LDFLAGS:append = " -lbreakpadwrapper -lpthread -lstdc++ -lsecure_wrapper -lprivilege"

do_install:append() {
    install -d ${D}${includedir}
    install -d ${D}${base_libdir}/rdk
    install -d ${D}${systemd_unitdir}/system
    install -m 0644 ${S}/include/telemetry_busmessage_sender.h ${D}${includedir}/
    install -m 0644 ${S}/include/telemetry2_0.h ${D}${includedir}/
    install -m 0755 ${S}/source/commonlib/t2Shared_api.sh ${D}${base_libdir}/rdk/
    install -m 0644 ${S}/telemetry2_0.service ${D}${systemd_unitdir}/system/
    rm -f ${D}${libdir}/libtelemetry_msgsender.la
    rm -f ${D}${bindir}/t2rbusMethodSimulator
}

SYSTEMD_SERVICE:${PN} = "telemetry2_0.service"

FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"

FILES:${PN} = " \
    ${bindir}/telemetry2_0 \
    ${bindir}/telemetry2_0_client \
    ${systemd_unitdir}/system \
    ${libdir}/*.so* \
    ${base_libdir}/rdk/* \
"
