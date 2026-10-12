SUMMARY = "QEMU no-op Device Settings HAL (virtual/vendor-devicesettings-hal)"
SECTION = "libs"
DESCRIPTION = "Minimal stub implementing the rdk-halif-device_settings API so \
devicesettings and dependents can link on qemuarm without vendor SoC code."

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

PROVIDES = "virtual/devicesettings-hal virtual/vendor-devicesettings-hal"
RPROVIDES:${PN} = "virtual/devicesettings-hal virtual/vendor-devicesettings-hal"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS = "devicesettings-hal-headers"

SRC_URI = "file://ds-hal-stub.c \
           file://CMakeLists.txt \
           file://dsAudioSettings.h \
           file://dsFPDSettings.h \
           file://dsVideoDeviceSettings.h \
           file://dsVideoPortSettings.h \
           file://dsVideoResolutionSettings.h \
"

S = "${WORKDIR}"

inherit cmake

do_install:append() {
    install -d ${D}${includedir}/rdk/halif/ds-hal
    install -m 0644 ${WORKDIR}/dsAudioSettings.h ${D}${includedir}/rdk/halif/ds-hal/
    install -m 0644 ${WORKDIR}/dsFPDSettings.h ${D}${includedir}/rdk/halif/ds-hal/
    install -m 0644 ${WORKDIR}/dsVideoDeviceSettings.h ${D}${includedir}/rdk/halif/ds-hal/
    install -m 0644 ${WORKDIR}/dsVideoPortSettings.h ${D}${includedir}/rdk/halif/ds-hal/
    install -m 0644 ${WORKDIR}/dsVideoResolutionSettings.h ${D}${includedir}/rdk/halif/ds-hal/
    # Also expose under legacy rdk/ds-hal path used by devicesettings includes
    install -d ${D}${includedir}/rdk/ds-hal
    install -m 0644 ${WORKDIR}/dsAudioSettings.h ${D}${includedir}/rdk/ds-hal/
    install -m 0644 ${WORKDIR}/dsFPDSettings.h ${D}${includedir}/rdk/ds-hal/
    install -m 0644 ${WORKDIR}/dsVideoDeviceSettings.h ${D}${includedir}/rdk/ds-hal/
    install -m 0644 ${WORKDIR}/dsVideoPortSettings.h ${D}${includedir}/rdk/ds-hal/
    install -m 0644 ${WORKDIR}/dsVideoResolutionSettings.h ${D}${includedir}/rdk/ds-hal/
    ln -sf libds-hal.so.0 ${D}${libdir}/libdshal.so
}

# Middleware dlopens libds-hal.so.0
FILES:${PN} = "${libdir}/libds-hal.so.* ${libdir}/libds-hal.so ${libdir}/libdshal.so*"
FILES:${PN}-dev += "${includedir}/rdk/halif/ds-hal/*Settings.h ${includedir}/rdk/ds-hal/*Settings.h"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} += "dev-so"
