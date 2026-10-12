SUMMARY = "Host/native tooling for Thunder (WPEFramework)"
HOMEPAGE = "https://github.com/rdkcentral/ThunderTools"
SECTION = "devel"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c3349dc67b2f8c28fc99b300eb39e3cc"

SRC_URI = "git://github.com/rdkcentral/ThunderTools.git;protocol=https;branch=R4_4-RDK"
S = "${WORKDIR}/git"

PV = "4.4.7"

DEPENDS = "python3-native python3-jsonref-native"

inherit cmake pkgconfig python3native

EXTRA_OECMAKE += "-DCMAKE_SYSROOT=${STAGING_DIR_HOST}"

FILES:${PN} += "${datadir}/*/Modules/*.cmake"

BBCLASSEXTEND = "native nativesdk"
