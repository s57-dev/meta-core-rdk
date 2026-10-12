SUMMARY = "Thunder client libraries (Core slim: PowerController + Cryptography/OpenSSL)"
HOMEPAGE = "https://github.com/rdkcentral/ThunderClientLibraries"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=847677038847363222ffb66cfa6406c2"

PV = "4.4.2"

SRC_URI = "git://github.com/rdkcentral/ThunderClientLibraries.git;protocol=https;branch=R4_4 \
           file://0001-PowerManagerClient-library-implementation.patch \
"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native entservices-apis openssl"

inherit python3native cmake pkgconfig

# PowerController is an RDK patch; Cryptography uses OpenSSL (no SecApi).
PACKAGECONFIG = "powercontroller cryptography"
PACKAGECONFIG[powercontroller] = "-DPOWERCONTROLLER=ON,-DPOWERCONTROLLER=OFF,"
PACKAGECONFIG[cryptography] = "\
    -DCRYPTOGRAPHY=ON -DCRYPTOGRAPHY_IMPLEMENTATION=OpenSSL, \
    -DCRYPTOGRAPHY=OFF,"

EXTRA_OECMAKE += " \
    -DBUILD_SHARED_LIBS=ON \
    -DBUILD_REFERENCE=${SRCREV} \
    -DCMAKE_SYSROOT=${STAGING_DIR_HOST} \
    -DBLUETOOTHAUDIOSINK=OFF \
    -DBLUETOOTHAUDIOSOURCE=OFF \
    -DPROTOCOLS=OFF \
    -DCOMPOSITORCLIENT=OFF \
    -DDEVICEINFO=OFF \
    -DDISPLAYINFO=OFF \
    -DSECURITYAGENT=OFF \
    -DPLAYERINFO=OFF \
    -DPROVISIONPROXY=OFF \
    -DCDMI=OFF \
    -DINSTALL_TESTS=OFF \
"

ASNEEDED = ""

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/*.so ${datadir}/WPEFramework/* ${libdir}/pkgconfig/*.pc"
FILES:${PN}-dev += "${libdir}/cmake/* ${includedir}/WPEFramework/powercontroller/* ${includedir}/WPEFramework/cryptography/*"
INSANE_SKIP:${PN} += "dev-so"
