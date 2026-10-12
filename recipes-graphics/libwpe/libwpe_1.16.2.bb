SUMMARY = "General-purpose library for the WPE WebKit port"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/libwpe"
BUGTRACKER = "https://github.com/WebPlatformForEmbedded/libwpe/issues"
SECTION = "libs"

# Core-V BOM "WPE Client Libraries" → libwpe (WebKit), not Thunder clientlibs.
LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=371a616eb4903c6cb79e9893a5f615cc"

# Align with RDKE OSS / WPEWebKit 2.46 (Thunder R4.4 era).
SRC_URI = "https://wpewebkit.org/releases/${BPN}-${PV}.tar.xz"
SRC_URI[sha256sum] = "960bdd11c3f2cf5bd91569603ed6d2aa42fd4000ed7cac930a804eac367888d7"

DEPENDS = "virtual/egl"

inherit meson features_check pkgconfig

REQUIRED_DISTRO_FEATURES = "opengl"

PACKAGECONFIG ??= "enable-xkb"
PACKAGECONFIG[enable-xkb] = "-Denable-xkb=true,-Denable-xkb=false,libxkbcommon"

PROVIDES += "virtual/libwpe"
