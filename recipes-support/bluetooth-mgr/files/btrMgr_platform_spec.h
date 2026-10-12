/* QEMU / Core RDK platform defaults for bluetooth-mgr */
#ifndef BTRMGR_PLATFORM_SPEC_H
#define BTRMGR_PLATFORM_SPEC_H

#ifndef BTRMGR_LE_MODELNUMBER
#define BTRMGR_LE_MODELNUMBER   "qemuarm"
#endif
#ifndef BTRMGR_LE_SWREVISION
#define BTRMGR_LE_SWREVISION    "0.0.0"
#endif
#ifndef BTRMGR_LE_UUID_QR_CODE
#define BTRMGR_LE_UUID_QR_CODE  ""
#endif
#ifndef BTRMGR_LE_MFGRNAME
#define BTRMGR_LE_MFGRNAME      "CoreRDK"
#endif

/* Battery Service Device Information — Firmware Revision String */
#ifndef BTRMGR_UUID_BATTERY_FIRMWARE_REVISION
#define BTRMGR_UUID_BATTERY_FIRMWARE_REVISION "00002a26-0000-1000-8000-00805f9b34fb"
#endif

#endif /* BTRMGR_PLATFORM_SPEC_H */
