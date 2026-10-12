SUMMARY = "RDK Core middleware package set (strict Core-V BOM)"
DESCRIPTION = "Packages corresponding to middleware entries in \
core-v-components.json. Recipes are added incrementally; empty RDEPENDS \
is valid for Phase 1 boot-only images."

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

ALLOW_EMPTY:${PN} = "1"

# Populated as meta-core-rdk recipes are authored. Keep in sync with
# scripts/map-core-v.py output (status=ported).
RDEPENDS:${PN} = " \
    rdk-logger \
    libsyswrapper \
    libunpriv \
    iarmbus \
    rbus \
    cimplog \
    wdmp-c \
    rdkcertconfig \
    commonutilities \
    rfc \
    telemetry \
    hdmicecheader \
    wpeframework \
    wpeframework-ui \
    entservices-apis \
    memcr \
    packagemanager \
    bluetooth-core \
    thunderjs \
    devicesettings-hal-headers \
    devicesettings-hal-qemu \
    devicesettings \
    hdmicec-hal-qemu \
    hdmicec \
    wpeframework-clientlibraries \
    bluetooth-mgr \
    systimemgrinetrface \
    entservices-helpers \
    entservices-monitor \
    entservices-account \
    entservices-systemmode \
    entservices-tools \
    entservices-backupmanager \
    packager-headers \
    entservices-sharedstorage \
    entservices-usersettings \
    entservices-runtime \
    power-manager-headers \
    deepsleep-manager-headers \
    entservices-usbdevice \
    entservices-appgateway \
    entservices-firmwaredownload \
    entservices-migration \
    entservices-usbmassstorage \
    entservices-cryptography \
    iarmmgrs-hal-headers \
    entservices-connectivity \
    entservices-infra \
    pwrmgr-hal-qemu \
    deepsleepmgr-hal-qemu \
    mfrlib-noop \
    iarmmgrs \
    entservices-powermanager \
    entservices-firmwareupdate \
    entservices-warehouse \
    entservices-deviceinfo \
    entservices-userpreferences \
    entservices-telemetry \
    entservices-devicesettings \
    entservices-displaysettings \
    sysint \
    rdkfwupgrader \
    entservices-maintenancemanager \
    libchronyctl \
    systimemgrfactory \
    systimemgr \
    westeros-simplebuffer \
    westeros-simpleshell \
    westeros-soc-drm \
    westeros \
    libwpe \
    wpewebkit \
    wpebackend-fdo \
    entservices-appmanagers \
    entservices-playerinfo \
    entservices-displayinfo \
    entservices-screencapture \
    systemaudioplatform-hal-qemu \
    entservices-mediaanddrm \
    essos \
    rdkwindowmanager \
    crun \
    dobby \
    dobby-thunderplugin \
    audio-service-noop \
    rdk-gstreamer-utils-headers \
    rdk-gstreamer-utils-platform-qemu \
    rdk-gstreamer-utils \
    opencdm-stub \
    rialto-client \
    rialto-server \
    rialto-ocdm \
    rialto-gstreamer \
"
