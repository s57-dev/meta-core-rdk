SUMMARY = "ENTServices helpers common shared library"
HOMEPAGE = "https://github.com/rdkcentral/entservices-helpers"
SECTION = "libs"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=175792518e4ac015ab6696d16c4f607e"

PV = "1.0.6"

SRC_URI = "${CORE_GITHUB_ROOT}/entservices-helpers;${CORE_GITHUB_SRC_URI_SUFFIX}"
S = "${WORKDIR}/git"

DEPENDS = "wpeframework wpeframework-tools-native"
RDEPENDS:${PN} += "wpeframework"

inherit cmake pkgconfig

PACKAGECONFIG ??= "helpers"
PACKAGECONFIG[helpers] = "-DPLUGIN_HELPERS=ON,-DPLUGIN_HELPERS=OFF,entservices-apis iarmbus devicesettings virtual/vendor-devicesettings-hal,entservices-apis devicesettings"

EXTRA_OECMAKE += " \
    -DBUILD_REFERENCE=${SRCREV} \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_THUNDER_R4=ON \
"

CXXFLAGS:append = " -I${STAGING_INCDIR}/rdk/ds \
                    -I${STAGING_INCDIR}/rdk/ds-hal \
                    -I${STAGING_INCDIR}/rdk/halif/ds-hal \
                    -I${STAGING_INCDIR}/rdk/iarmbus \
                    -DUSE_THUNDER_COMMUNICATION"

FILES_SOLIBSDEV = ""
FILES:${PN} += " \
    ${libdir}/wpeframework/plugins/*.so \
    ${libdir}/*.so \
    ${includedir}/wpeframework/helpers/* \
    ${libdir}/cmake/WPEFrameworkHelpers/* \
"
INSANE_SKIP:${PN} += "libdir staticdev dev-so"
