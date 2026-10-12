SUMMARY = "Thunder PackageManager plugin"
HOMEPAGE = "https://github.com/rdkcentral/PackageManager"
SECTION = "plugins"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c03d0e6d700b63b51bf8da6b61dac850"

SRC_URI = "git://github.com/rdkcentral/PackageManager.git;protocol=https;branch=main"
S = "${WORKDIR}/git"

PV = "4.4.1"

DEPENDS = "wpeframework entservices-apis wpeframework-tools-native"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig python3native

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
"

FILES_SOLIBSDEV = ""
FILES:${PN} += "${sysconfdir}/WPEFramework/plugins/* ${libdir}/wpeframework/plugins/*.so"
INSANE_SKIP:${PN} += "libdir staticdev dev-so"
