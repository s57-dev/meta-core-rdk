# Install private BlueZ headers required by bluetooth-core (A2DP codec defs).
do_install:append() {
    install -d ${D}${includedir}/bluetooth/audio
    install -m 0644 ${S}/profiles/audio/a2dp-codecs.h ${D}${includedir}/bluetooth/audio/
    install -m 0644 ${S}/lib/uuid.h ${D}${includedir}/bluetooth/
}

FILES:${PN}-dev += "${includedir}/bluetooth/audio/a2dp-codecs.h ${includedir}/bluetooth/uuid.h"
