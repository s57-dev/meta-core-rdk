SUMMARY = "RDK GStreamer utils (Netflix platform helpers)"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "2.0.3"

SRC_URI = "${CORE_GITHUB_ROOT}/gstreamer-netflix-platform;${CORE_GITHUB_SRC_URI_SUFFIX}"
SRCREV = "f7d68a8a077989e1dd55428e1026813afcbe211f"
S = "${WORKDIR}/git"

DEPENDS = "gstreamer1.0 gstreamer1.0-plugins-base virtual/vendor-audio-service \
           virtual/vendor-rdk-gstreamer-utils-platform rdk-gstreamer-utils-headers"
RDEPENDS:${PN} += "gstreamer1.0 rdk-gstreamer-utils-platform-qemu"

CXXFLAGS += "-I${STAGING_INCDIR}/glib-2.0 -I${STAGING_INCDIR}/gstreamer-1.0 \
             -I${STAGING_DIR_TARGET}${libdir}/glib-2.0/include"

do_configure[noexec] = "1"

do_compile() {
    oe_runmake -C ${S} -f Makefile LDFLAGS="${LDFLAGS} -Wl,--hash-style=gnu -lrdkgstreamerutilsplatform"
}

do_install() {
    install -d ${D}${libdir}
    install -m 0755 ${S}/librdkgstreamerutils.so ${D}${libdir}
    install -m 0755 ${S}/librdkgstreamerrltclientutils.so ${D}${libdir}
    # Headers live in rdk-gstreamer-utils-headers (avoid sysroot file clash).
}


INSANE_SKIP:${PN} += "dev-so ldflags textrel file-rdeps"
FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/*.so"
