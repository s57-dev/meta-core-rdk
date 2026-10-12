SUMMARY = "Cross-platform library for controlling STB platform hardware configuration"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57"

PV = "1.0.41"

SRC_URI = "${CORE_GITHUB_ROOT}/devicesettings;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "json-c glib-2.0 iarmbus rdk-logger virtual/vendor-devicesettings-hal \
           devicesettings-hal-headers safec-common-wrapper rfc wdmp-c telemetry"

PACKAGE_ARCH = "${MACHINE_ARCH}"

INCLUDE_DIRS = " \
    -I${S}/config \
    -I${S}/config/include \
    -I${STAGING_DIR_TARGET}${includedir}/rdk/ds-hal \
    -I${STAGING_DIR_TARGET}${includedir}/rdk/halif/ds-hal \
    -I./include \
    -I${S}/rpc/include \
    -I${S}/ds/include \
    -I${S}/hal/include \
    -I${STAGING_DIR_TARGET}${includedir} \
    -I${STAGING_DIR_TARGET}${includedir}/rdk/iarmbus \
    -I${STAGING_DIR_TARGET}${includedir}/rdk/logger \
    -I${STAGING_DIR_TARGET}${includedir}/glib-2.0 \
    -I${STAGING_DIR_TARGET}${libdir}/glib-2.0/include \
    -I${STAGING_DIR_TARGET}${includedir}/logger \
    -I${STAGING_INCDIR}/wdmp-c \
"

LDFLAGS += "-lrdkloggers -lpthread -lglib-2.0 -L. -lIARMBus -ldl -ltelemetry_msgsender -lrfcapi"
CFLAGS += "-fPIC -D_REENTRANT -Wall ${INCLUDE_DIRS}"
CFLAGS += "-DSAFEC_DUMMY_API -DYOCTO_BUILD -DDS_AUDIO_SETTINGS_PERSISTENCE -DDSMGR_LOGGER_ENABLED"
CFLAGS += "-DRDK_DSHAL_NAME="\""libds-hal.so.0\""""
CFLAGS += "-DHAS_FLASH_PERSISTENT -DHAS_THERMAL_API -DdsFPD_BRIGHTNESS_DEFAULT=100"
CFLAGS += "-DHAS_HDCP_CALLBACK -DENABLE_DEEP_SLEEP -DIGNORE_EDID_LOGIC"
CXXFLAGS += "-DSAFEC_DUMMY_API"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/*.so"
TARGET_CC_ARCH += "${LDFLAGS}"
INSANE_SKIP:${PN} = "ldflags"

do_configure:prepend() {
    rm -f ${S}/Makefile
}

do_compile() {
    rm -rf ${S}/hal
    oe_runmake -B -C ${S}/rpc/cli
    oe_runmake -B -C ${S}/rpc/srv
    export CFLAGS="$CFLAGS -std=c++11"
    oe_runmake -B -C ${S}/ds
}

do_install() {
    install -d ${D}${includedir}/rdk/ds
    install -m 0644 ${S}/ds/include/*.h* ${D}${includedir}/rdk/ds
    install -m 0644 ${S}/ds/*.h* ${D}${includedir}/rdk/ds

    install -d ${D}${includedir}/rdk/ds-rpc
    install -m 0644 ${S}/rpc/include/*.h* ${D}${includedir}/rdk/ds-rpc

    install -d ${D}${libdir}
    for i in ${S}/rpc/cli/*.so ${S}/rpc/srv/*.so ${S}/ds/*.so; do
        install -m 0755 $i ${D}${libdir}
    done
}
