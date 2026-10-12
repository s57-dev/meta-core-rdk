# SRCREV / version pins for Core-V middleware recipes.
# Keep in sync with authored recipes under recipes-*.

# Foundation
SRCREV:pn-rdk-logger = "6dc321d7a5890ef947402532a990ebfb91839f2b"
SRCREV:pn-libsyswrapper = "69fab9ab4403b7cda68af1d6a095b2cf5749c84f"
SRCREV:pn-libunpriv = "2e6c30c88823b7ff9cebde4d7f4d978118510da0"
SRCREV:pn-iarmbus = "162d44cd413f0982a06537ffefae1db12f5d4abf"
SRCREV:pn-rbus = "5f74c2fbe0019781410be4b1160768adda96d1a4"
SRCREV:pn-linenoise = "97d2850af13c339369093b78abe5265845d78220"

# Wave A — logging / config / telemetry stack
SRCREV:pn-cimplog = "4d96629b8e04230549cd2bee36bc4419a6e5aecc"
SRCREV:pn-wdmp-c = "f9f687b6b4b10c2b72341e792a64334f0a409848"
SRCREV:pn-rdkcertconfig = "cd445dd473a9a82488d553faaf7e60e57a0e102b"
SRCREV:pn-commonutilities = "a46323c0452faad036968f4f8c949741cb17d20f"
SRCREV:pn-rfc = "d6abb36e283363c8577217d1d9f77b20de674cdf"
SRCREV:pn-breakpad-wrapper = "be8cd679e095cd300f77913863724fa5e39a6182"
SRCREV:pn-telemetry = "ff823cd555a2dcdffe439a183a70b1c655df3087"

# Wave B — HALIF headers (full DS/HDMI CEC need vendor HAL stubs)
SRCREV:pn-hdmicecheader = "3768ea0c9ba9012d077e0048cac8359c9351272d"
SRCREV:pn-devicesettings-hal-headers = "4d422e891a794695bab1675d3fa4040348d43f80"

# Thunder / WPEFramework (R4.4-RDK)
SRCREV:pn-wpeframework-tools = "cf40bf92f37bdf93e775b98ee77332db7e5e36d0"
SRCREV:pn-wpeframework-tools-native = "cf40bf92f37bdf93e775b98ee77332db7e5e36d0"
SRCREV:pn-wpeframework = "7c33637bdfd36109ffb556da4e9b58d1443ebd69"
SRCREV:pn-wpeframework-ui = "e1e4255a458b6b913c6d97f33f57ca0f34029488"
SRCREV:pn-entservices-apis = "6cb6f39498518849a8b5df6f7a12f751f48776f3"

# Mid-layer BOM
SRCREV:pn-memcr = "e58cf09b092b5e269acb5e8a3aa311b6d748bfe0"
SRCREV:pn-packagemanager = "38729b8edfc3ddaba0b3625c19bcd2dd1a05b027"
SRCREV:pn-bluetooth-core = "c8d1ca7eb057eff90b7ce342ce532b9cd18a55e7"
SRCREV:pn-entservices-connectivity = "19b70fe4d8ef0cf9a3eccc15d2fc63a30de3abf7"

# ThunderJS
SRCREV:pn-thunderjs = "903f843ebbe9136609fbe348b1e39c306489c0b1"

# Device Settings (HALIF headers already pinned above; HAL is local stub)
SRCREV:pn-devicesettings = "380df69d69d55aa6783054fb2c808110736c5a33"

# HDMI CEC
SRCREV:pn-hdmicec = "7c46960036c15c66727d06b65454273715563c8a"

# Thunder client libraries (PowerController)
SRCREV:pn-wpeframework-clientlibraries = "7e45a65211f9a3f926d3dbd18679ccfcbc9ef559"

# Bluetooth Manager
SRCREV:pn-bluetooth-mgr = "2936e490103870d0449018f6fec0e269402dece0"

# System time interface headers
SRCREV:pn-systimemgrinetrface = "1bf386d4806baec8b6ceeed17288ccf1a22c74a3"

# Thin entservices wave
SRCREV:pn-entservices-helpers = "72a1aa9054e9ea0fdf3dcee25df8eb46c43bf61b"
SRCREV:pn-entservices-monitor = "fdaaa727c8d8c272fc76d610b10a36410fdf7cd8"
SRCREV:pn-entservices-account = "8471e0b2873139bc10b164d68483c29cd0f062c9"
SRCREV:pn-entservices-systemmode = "b04f47b7f5eee2247f7d5ddfd871238e60396b27"
SRCREV:pn-entservices-tools = "97e869b9e994081cd49bf7a0fed11069ef9fa382"
SRCREV:pn-entservices-backupmanager = "ec4e633cf9a84a67c4c5c2fba0918654f32bd74b"

# Packager headers + storage/settings plugins
SRCREV:pn-packager-headers = "ac342de3d3f716291ea9c6b92d670f836ac1e9c7"
SRCREV:pn-entservices-sharedstorage = "9e09f169d3f09263bb60acf89ce7366a225e5940"
SRCREV:pn-entservices-usersettings = "a48d847264214d3e010b72acdba473c55c7f1164"
SRCREV:pn-entservices-runtime = "851bb16d4f4c6890c5a6a879b8586b62c265f09e"

# HALIF power / deepsleep headers
SRCREV:pn-power-manager-headers = "dfdb28730ca05b711fb7b6484eadfedf834a4970"
SRCREV:pn-deepsleep-manager-headers = "c14f0d5cd5381327a1df921e056adbb7eaf4ed8c"

# USB + AppGateway (slim)
SRCREV:pn-entservices-usbdevice = "64b6e99ef12722852e6652f7384147b7383a0934"
SRCREV:pn-entservices-appgateway = "07fd62c9015cd31409f9caa55a9cdc18b0a824cb"
SRCREV:pn-entservices-firmwaredownload = "bdd067bcda9ad4833fa922d97fb27d610d9c9d0b"
SRCREV:pn-entservices-migration = "beb46283c421e5eb1c4ebd0d573413ce7a83ff9c"
SRCREV:pn-entservices-usbmassstorage = "2eed90d558fa11cc5828d1ec839ddb1fa849b566"
SRCREV:pn-entservices-cryptography = "d95807fac4b50ae036aeb1486b0be0dc3142ea93"
SRCREV:pn-iarmmgrs-hal-headers = "422bc8cd41d6ac3c49a7b828159cf09874b467e5"
SRCREV:pn-entservices-infra = "f759768a414572e77bcb29ee43404df3bb481064"
SRCREV:pn-iarmmgrs = "73805678122e92cee5dccfc8a099c26e47107105"
SRCREV:pn-entservices-powermanager = "d0e98b3eec72b635203149cb293e269dbca91bdf"
SRCREV:pn-entservices-firmwareupdate = "ddb4903974478bd5435d90773a200de47c1786c6"
SRCREV:pn-entservices-warehouse = "e65bb6a8b362b586f1b8c8279002059c4f705968"
SRCREV:pn-entservices-deviceinfo = "616a0da6c57012ba7d193edbee340e3afff2e174"
SRCREV:pn-entservices-userpreferences = "31b8421268a2f8cdc3393c80cee106ceed68ee69"
SRCREV:pn-entservices-telemetry = "ae7076588a78ce311e926f97f1708a4a6c89f6e9"
SRCREV:pn-entservices-devicesettings = "66032d32aed342f36b8cb1ab405b566cddf9c998"

# Software update / sysint wave
SRCREV:pn-sysint = "712d3a3dbe9c32c91c26b3ebc853a9f743fda453"
SRCREV:pn-rdkfwupgrader = "c8f18827a32e877527c57c9d62e08bb724d564d8"
SRCREV:pn-entservices-maintenancemanager = "d07176aa349bce937cc59c603220f9ba0af0ec3a"

# System time manager stack
SRCREV:pn-libchronyctl = "cb3c153dcc0919dd680adb496ea3897541aea7c7"
SRCREV:pn-systimemgrfactory = "1bf386d4806baec8b6ceeed17288ccf1a22c74a3"
SRCREV:pn-systimemgr = "1bf386d4806baec8b6ceeed17288ccf1a22c74a3"

# Westeros compositor stack (tag 2.1.2)
SRCREV_westeros:pn-westeros = "f2c5d04631ef0ca5f13a647404a3cc89528fc81c"
SRCREV_westeros:pn-westeros-simplebuffer = "f2c5d04631ef0ca5f13a647404a3cc89528fc81c"
SRCREV_westeros:pn-westeros-simpleshell = "f2c5d04631ef0ca5f13a647404a3cc89528fc81c"
SRCREV_westeros-soc:pn-westeros-soc-drm = "80198a81b6b0ff1440cc5a45dcce33a07aa50b0d"

# WPE WebKit stack (release tarball PVs; WebPlatformForEmbedded, not rdkcentral)
# libwpe 1.16.x + WPEWebKit 2.46.x + wpebackend-fdo 1.14.3 match RDKE / Thunder R4.4.
PREFERRED_VERSION_libwpe ?= "1.16.2"
PREFERRED_VERSION_wpebackend-fdo ?= "1.14.3"
PREFERRED_VERSION_wpewebkit ?= "2.46.7"

# AppManagers (slim: TelemetryMetrics + AppStorageManager; tag 1.1.0.0)
SRCREV:pn-entservices-appmanagers = "6956880380461124a8d03199455b7790095a2f8f"

# PlayerInfo (MediaAndDrm child; tag 1.1.4)
SRCREV:pn-entservices-playerinfo = "a000d1e3dd5b264e32180d694e9f376a47b5e9bb"

# DisplayInfo / ScreenCapture (LibDRM backends for qemuarm)
SRCREV:pn-entservices-displayinfo = "03d5483c08deaa1dc8959db33111f4dca970b7bb"
SRCREV:pn-entservices-screencapture = "6d61278c0fba48693ef153f1b2ee243993060716"

# MediaAndDrm (TTS only; tag 1.11.0)
SRCREV:pn-entservices-mediaanddrm = "63614fce9cc0d34cf18942a6062909519c97cf3e"

# DisplaySettings (DeviceAndDisplay split child; tag 1.8.1)
SRCREV:pn-entservices-displaysettings = "b3b5a58ca1fd1b59f217db36c969aa36c759fd9a"

# Essos / RDK Window Manager / Dobby / Rialto
SRCREV_essos:pn-essos = "e072f3e9a2fe55a4edc4ee5f9e7b6021ce9bf66b"
SRCREV:pn-rdkwindowmanager = "d43ff7a95b0166aa2ceaafc4bac29eb911624f24"
SRCREV:pn-dobby = "c305b90f5cc36c79b910791824eb69a2f79438bd"
SRCREV:pn-dobby-thunderplugin = "c305b90f5cc36c79b910791824eb69a2f79438bd"
SRCREV:pn-crun = "0356bf4aff9a133d655dc13b1d9ac9424706cac4"
SRCREV:pn-rdk-gstreamer-utils = "f7d68a8a077989e1dd55428e1026813afcbe211f"
SRCREV:pn-rdk-gstreamer-utils-headers = "f7d68a8a077989e1dd55428e1026813afcbe211f"

