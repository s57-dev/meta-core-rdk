SUMMARY = "C wrapper for Google Breakpad"
HOMEPAGE = "https://github.com/rdkcentral/breakpad_wrapper"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

SRC_URI = "${CORE_GITHUB_ROOT}/breakpad_wrapper;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "1.0.0"

DEPENDS = "breakpad"

inherit autotools

CPPFLAGS:append = " -I${STAGING_INCDIR}/breakpad/"
LDFLAGS += "-lbreakpad_client -lpthread"
CXXFLAGS += "-DMINIDUMP_RDKV"

do_install:append() {
    install -d ${D}${includedir}
    install -m 0644 ${S}/*.h ${D}${includedir}/
}

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/*.so"
INSANE_SKIP:${PN} += "dev-so"
