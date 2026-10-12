SUMMARY = "WPE WebKit — WebKit port for embedded/Wayland (slim qemuarm)"
HOMEPAGE = "https://github.com/WebPlatformForEmbedded/WPEWebKit"
BUGTRACKER = "https://bugs.webkit.org/"
SECTION = "libs"

LICENSE = "BSD-2-Clause & LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Source/WebCore/LICENSE-LGPL-2.1;md5=a778a33ef338abbaf8b8a7c36b6eec80"

# Upstream 2.46.x aligns with RDKE WPE + Thunder R4.4; no Comcast patches.
SRC_URI = "https://wpewebkit.org/releases/${BPN}-${PV}.tar.xz"
SRC_URI[sha256sum] = "cf3e47638595d86de96abdb94db69a836c8aa509fc063be714f52c5a24bb5cd5"

inherit cmake features_check pkgconfig perlnative python3native

REQUIRED_DISTRO_FEATURES = "opengl"

DEPENDS = " \
    libwpe virtual/wpebackend \
    bison-native gperf-native glib-2.0-native libxml2-native ninja-native ruby-native unifdef-native \
    fontconfig freetype glib-2.0 harfbuzz icu jpeg pcre sqlite3 zlib \
    libpng libtasn1 libwebp libxml2 libxslt libepoxy libgcrypt libsoup \
    virtual/egl virtual/libgles2 cairo \
"

export WK_USE_CCACHE = "NO"

# Minimal qemuarm: no proprietary DRM stack, no MiniBrowser/Qt/Thunder CDM.
# Prefer classic libwpe + wpebackend-fdo (works against Westeros as Wayland).
# gst_gl off — gstreamer1.0-plugins-base is built without EGL/GLES in Core.
PACKAGECONFIG ??= " \
    jit dfg-jit unified-builds webcrypto \
    video webaudio mediasource \
    ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'journald', '', d)} \
"

PACKAGECONFIG[jit] = "-DENABLE_JIT=ON,-DENABLE_JIT=OFF,"
PACKAGECONFIG[dfg-jit] = "-DENABLE_DFG_JIT=ON,-DENABLE_DFG_JIT=OFF,"
PACKAGECONFIG[unified-builds] = "-DENABLE_UNIFIED_BUILDS=ON,-DENABLE_UNIFIED_BUILDS=OFF,"
PACKAGECONFIG[webcrypto] = "-DENABLE_WEB_CRYPTO=ON,-DENABLE_WEB_CRYPTO=OFF,libgcrypt libtasn1"
PACKAGECONFIG[video] = "-DENABLE_VIDEO=ON,-DENABLE_VIDEO=OFF,gstreamer1.0 gstreamer1.0-plugins-base"
PACKAGECONFIG[webaudio] = "-DENABLE_WEB_AUDIO=ON,-DENABLE_WEB_AUDIO=OFF,gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good"
PACKAGECONFIG[mediasource] = "-DENABLE_MEDIA_SOURCE=ON,-DENABLE_MEDIA_SOURCE=OFF,gstreamer1.0 gstreamer1.0-plugins-good"
PACKAGECONFIG[journald] = "-DENABLE_JOURNALD_LOG=ON,-DENABLE_JOURNALD_LOG=OFF,"
PACKAGECONFIG[remote-inspector] = "-DENABLE_REMOTE_INSPECTOR=ON,-DENABLE_REMOTE_INSPECTOR=OFF,"

# Javascript JIT is not supported on very old ARM
PACKAGECONFIG:remove:armv4 = "jit dfg-jit"
PACKAGECONFIG:remove:armv5 = "jit dfg-jit"
PACKAGECONFIG:remove:armv6 = "jit dfg-jit"

EXTRA_OECMAKE = " \
    -DPORT=WPE \
    -G Ninja \
    -DCMAKE_BUILD_TYPE=Release \
    -DENABLE_BUBBLEWRAP_SANDBOX=OFF \
    -DENABLE_MINIBROWSER=OFF \
    -DDEVELOPER_MODE=OFF \
    -DENABLE_WPE_QT_API=OFF \
    -DENABLE_WPE_PLATFORM=OFF \
    -DENABLE_THUNDER=OFF \
    -DENABLE_DOCUMENTATION=OFF \
    -DENABLE_INTROSPECTION=OFF \
    -DENABLE_MEDIA_STREAM=OFF \
    -DENABLE_WEB_RTC=OFF \
    -DUSE_CAIRO=ON \
    -DUSE_SKIA=OFF \
    -DUSE_GSTREAMER_GL=OFF \
    -DUSE_GBM=OFF \
    -DUSE_LIBDRM=OFF \
    -DUSE_LCMS=OFF \
    -DUSE_LIBBACKTRACE=OFF \
    -DUSE_SYSPROF_CAPTURE=OFF \
    -DUSE_SYSTEM_SYSPROF_CAPTURE=OFF \
    -DUSE_JPEGXL=OFF \
    -DUSE_AVIF=OFF \
    -DUSE_OPENJPEG=OFF \
    -DUSE_WOFF2=OFF \
    -DUSE_ATK=OFF \
    -DENABLE_WEBDRIVER=OFF \
    -DENABLE_GAMEPAD=OFF \
"

# Cross-build memory pressure on qemuarm link steps
LDFLAGS:append = " -Wl,--no-keep-memory"
FULL_OPTIMIZATION:remove = "-g"

# JSC JIT on ARMv7 prefers Thumb2
ARM_INSTRUCTION_SET:armv7a = "thumb"
ARM_INSTRUCTION_SET:armv7ve = "thumb"

LEAD_SONAME = "libWPEWebKit.so"

PACKAGES =+ "${PN}-web-inspector-plugin"
FILES:${PN} += "${libdir}/wpe-webkit*/injected-bundle/libWPEInjectedBundle.so"
FILES:${PN}-web-inspector-plugin += "${libdir}/libWPEWebInspectorResources.so ${libdir}/wpe-webkit-*/libWPEWebInspectorResources.so"
INSANE_SKIP:${PN}-web-inspector-plugin = "dev-so"

RDEPENDS:${PN} += " \
    libgles2 \
    virtual-wpebackend \
    ${@bb.utils.contains('PACKAGECONFIG', 'remote-inspector', '${PN}-web-inspector-plugin', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'mediasource', 'gstreamer1.0-plugins-good-isomp4', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'webaudio', 'gstreamer1.0-plugins-good-wavparse', '', d)} \
    ${@bb.utils.contains('PACKAGECONFIG', 'video', 'gstreamer1.0-plugins-base-app \
        gstreamer1.0-plugins-base-playback \
        gstreamer1.0-plugins-base-audioconvert \
        gstreamer1.0-plugins-base-audioresample \
        gstreamer1.0-plugins-base-gio \
        gstreamer1.0-plugins-base-volume \
        gstreamer1.0-plugins-base-videoconvert \
        gstreamer1.0-plugins-base-videoscale \
        gstreamer1.0-plugins-base-typefindfunctions \
        gstreamer1.0-plugins-good-audiofx \
        gstreamer1.0-plugins-good-audioparsers \
        gstreamer1.0-plugins-good-autodetect \
        gstreamer1.0-plugins-good-avi \
        gstreamer1.0-plugins-good-deinterlace \
        gstreamer1.0-plugins-good-interleave', '', d)} \
"

RRECOMMENDS:${PN} += "ca-certificates shared-mime-info"
