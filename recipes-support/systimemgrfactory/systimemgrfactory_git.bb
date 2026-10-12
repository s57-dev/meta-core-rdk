SUMMARY = "System Time Manager factory library"
HOMEPAGE = "https://github.com/rdkcentral/systemtimemgr"
SECTION = "libs"
DESCRIPTION = "Timer factory publishing and subscription interfaces."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://../LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "1.7.0"

SRC_URI = "${CORE_GITHUB_ROOT}/systemtimemgr;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git/systimerfactory"

DEPENDS = "systimemgrinetrface iarmbus iarmmgrs jsonrpc virtual/mfrlib rdk-logger telemetry \
           wpeframework wpeframework-clientlibraries cjson dbus libchronyctl"
RDEPENDS:${PN} += "iarmbus wpeframework virtual/mfrlib cjson libchronyctl"

ASNEEDED = ""

inherit autotools pkgconfig

# Slim qemu: IARM + PowerController plugin; skip WPE VGDRM / DTT (graphics).
EXTRA_OECONF = "--enable-iarm --enable-pwrmgrplugin --enable-t2api=yes --enable-debug \
                --disable-wpevgdrm --disable-dtt"

CXXFLAGS:append = " -I${STAGING_INCDIR}/rdk/iarmbus \
                    -I${STAGING_INCDIR}/rdk/iarmmgrs-hal \
                    -I${STAGING_INCDIR}/WPEFramework \
                    -I${STAGING_INCDIR}/WPEFramework/powercontroller \
                    -Wall -Wno-error"
LDFLAGS:append = " -lWPEFrameworkPowerController -lWPEFrameworkCore -lWPEFrameworkWebSocket \
                   -lRDKMfrLib -lcjson -lchronyctl"
