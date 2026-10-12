SUMMARY = "RFC (Remote Feature Control) helper applications"
HOMEPAGE = "https://github.com/rdkcentral/rfc"
SECTION = "console/utils"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=ab9fb9cce9e613f50442aa48f62301fc"

SRC_URI = "${CORE_GITHUB_ROOT}/rfc;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

PV = "1.2.8.1"

PACKAGECONFIG ??= "rfctool tr181set"
PACKAGECONFIG[rfctool] = "--enable-rfctool=yes"
PACKAGECONFIG[tr181set] = "--enable-tr181set=yes"

DEPENDS = "cjson curl commonutilities libsyswrapper iarmbus wdmp-c safec-common-wrapper rdk-logger"

inherit autotools pkgconfig

export cjson_CFLAGS = "-I$(PKG_CONFIG_SYSROOT_DIR)${includedir}/cjson"
export cjson_LIBS = "-lcjson"

# librfcapi (needed by rfcMgr) is gated on --enable-tr181set.
EXTRA_OECONF += "--enable-iarmbus=yes"

CFLAGS:append = " -fPIC -DSAFEC_DUMMY_API -Wall -Werror -Wextra -Wno-unused-parameter"
CXXFLAGS:append = " -Wall -Werror"

do_install:append() {
    install -d ${D}${base_libdir}/rdk
    install -d ${D}${sysconfdir}
    install -m 0644 ${S}/rfc.properties ${D}${sysconfdir}/rfc.properties
    install -m 0755 ${S}/getRFC.sh ${D}${base_libdir}/rdk/
    install -m 0755 ${S}/isFeatureEnabled.sh ${D}${base_libdir}/rdk/
}

RDEPENDS:${PN} += "busybox"

FILES:${PN} += "${bindir}/rfctool ${base_libdir}/rdk/* ${sysconfdir}/*"
