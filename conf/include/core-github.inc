# Shared GitHub fetch helpers for Core RDK recipes.
CORE_GITHUB_ROOT ?= "git://github.com/rdkcentral"
CORE_GITHUB_PROTOCOL ?= "https"
CORE_GITHUB_SRC_URI_SUFFIX ?= "protocol=${CORE_GITHUB_PROTOCOL};nobranch=1"

# WPE WebKit ecosystem (BOM URLs under WebPlatformForEmbedded, not rdkcentral).
WPE_GITHUB_ROOT ?= "git://github.com/WebPlatformForEmbedded"
WPE_GITHUB_SRC_URI_SUFFIX ?= "protocol=${CORE_GITHUB_PROTOCOL};nobranch=1"
