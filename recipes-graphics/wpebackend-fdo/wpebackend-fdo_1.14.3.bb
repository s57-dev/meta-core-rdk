SUMMARY = "WPE backend based on a freedesktop.org stack"
HOMEPAGE = "https://github.com/Igalia/WPEBackend-fdo"
SECTION = "libs"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=1f62cef2e3645e3e74eb05fd389d7a66"

# Align with libwpe 1.16 / WPEWebKit 2.46 (newer than poky 1.14.2)
PV = "1.14.3"

SRC_URI = "https://wpewebkit.org/releases/${BPN}-${PV}.tar.xz"
SRC_URI[sha256sum] = "10121842595a850291db3e82f3db0b9984df079022d386ce42c2b8508159dc6c"

DEPENDS = "glib-2.0 libxkbcommon wayland virtual/egl libwpe libepoxy"
DEPENDS:append:class-target = " wayland-native"

inherit meson features_check pkgconfig

REQUIRED_DISTRO_FEATURES = "opengl"

CXXFLAGS += "-Wno-c++11-narrowing"

PROVIDES += "virtual/wpebackend"
RPROVIDES:${PN} += "virtual-wpebackend"
