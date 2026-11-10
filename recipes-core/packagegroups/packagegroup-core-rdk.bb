SUMMARY = "RDK Core middleware package set (strict Core-V BOM)"
DESCRIPTION = "Packages corresponding to middleware entries in \
core-v-components.json."

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

ALLOW_EMPTY:${PN} = "1"

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
    ${PREFERRED_PROVIDER_virtual/vendor-devicesettings-hal} \
    devicesettings \
    ${PREFERRED_PROVIDER_virtual/vendor-hdmicec-hal} \
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
    ${PREFERRED_PROVIDER_virtual/vendor-pwrmgr-hal} \
    ${PREFERRED_PROVIDER_virtual/vendor-deepsleepmgr-hal} \
    ${PREFERRED_PROVIDER_virtual/mfrlib} \
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
    wpewebkit-minibrowser \
    wpe-demo-browser \
    wpebackend-fdo \
    liberation-fonts \
    entservices-appmanagers \
    entservices-playerinfo \
    entservices-displayinfo \
    entservices-screencapture \
    ${PREFERRED_PROVIDER_virtual/vendor-systemaudioplatform} \
    entservices-mediaanddrm \
    essos \
    rdkwindowmanager \
    crun \
    dobby \
    dobby-thunderplugin \
    ${PREFERRED_PROVIDER_virtual/vendor-audio-service} \
    rdk-gstreamer-utils-headers \
    ${PREFERRED_PROVIDER_virtual/vendor-rdk-gstreamer-utils-platform} \
    rdk-gstreamer-utils \
    opencdm-stub \
    rialto-client \
    rialto-server \
    rialto-ocdm \
    rialto-gstreamer \
"
