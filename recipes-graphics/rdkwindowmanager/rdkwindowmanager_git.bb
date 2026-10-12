SUMMARY = "RDK Window Manager"
DESCRIPTION = "BOM rdk-window-manager. PN matches RDKE (rdkwindowmanager)."
HOMEPAGE = "https://github.com/rdkcentral/rdk-window-manager"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=5a302d945c4b4ee2960872c471d41400"

PV = "1.4.0"

SRC_URI = "${CORE_GITHUB_ROOT}/rdk-window-manager;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "westeros wayland essos virtual/egl rapidjson jpeg libpng curl \
           libsoup-2.4 boost libsyswrapper"

inherit cmake pkgconfig

EXTRA_OECMAKE += " \
    -DRDK_WINDOW_MANAGER_BUILD_APP=OFF \
    -DRDK_WINDOW_MANAGER_BUILD_TEST_APP=OFF \
    -DRDK_WINDOW_MANAGER_VNC_SERVER=OFF \
"

do_install() {
    install -d ${D}${libdir}
    install -d ${D}${libdir}/plugins/westeros
    install -d ${D}${includedir}
    install -d ${D}${includedir}/rdkwindowmanager/include
    install -d ${D}${bindir}

    install -m 755 ${B}/librdkwindowmanager.so ${D}${libdir}/

    if [ -d ${B}/extensions/firebolt_shell ]; then
       install -m 755 ${B}/extensions/firebolt_shell/librdkwmextfireboltshell.so ${D}${libdir}/
       install -m 755 ${B}/extensions/firebolt_shell/libwstplugin_rdkwmfireboltshell.so ${D}${libdir}/plugins/westeros/
       install -m 644 ${S}/extensions/firebolt_shell/include/firebolt_shell_protocol_client.h ${D}${includedir}
    fi
    if [ -d ${B}/extensions/firebolt_surface ]; then
       install -m 755 ${B}/extensions/firebolt_surface/librdkwmextfireboltsurface.so ${D}${libdir}/
       install -m 755 ${B}/extensions/firebolt_surface/libwstplugin_rdkwmfireboltsurface.so ${D}${libdir}/plugins/westeros/
       install -m 644 ${S}/extensions/firebolt_surface/include/firebolt_surface_protocol_client.h ${D}${includedir}
    fi
    if [ -d ${B}/extensions/firebolt_wm ]; then
       install -m 755 ${B}/extensions/firebolt_wm/librdkwmextfireboltwm.so ${D}${libdir}/
       install -m 755 ${B}/extensions/firebolt_wm/libwstplugin_rdkwmfireboltwm.so ${D}${libdir}/plugins/westeros/
       install -m 644 ${S}/extensions/firebolt_wm/include/firebolt_wm_protocol_client.h ${D}${includedir}
    fi

    if [ -f ${B}/rdkwindowmanager ]; then
       install -m 0755 ${B}/rdkwindowmanager ${D}${bindir}/
    fi

    install -m 644 ${S}/include/*.h ${D}${includedir}/rdkwindowmanager/include/
}

INSANE_SKIP:${PN} += "dev-so installed-vs-shipped"
SOLIBS = ".so"
FILES_SOLIBSDEV = ""

FILES:${PN} += "${libdir}/librdkwindowmanager.so"
FILES:${PN} += "${libdir}/librdkwmext*.so"
FILES:${PN} += "${libdir}/plugins/westeros/*.so"
FILES:${PN} += "${bindir}/rdkwindowmanager"
