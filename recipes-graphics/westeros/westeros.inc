LICENSE ?= "Apache-2.0"

PV = "1.0+gitr${SRCPV}"

# Westeros compositor (tag 2.1.2)
WESTEROS_URI ?= "${CORE_GITHUB_ROOT}/westeros;${CORE_GITHUB_SRC_URI_SUFFIX};name=westeros"
SRCREV_westeros ?= "f2c5d04631ef0ca5f13a647404a3cc89528fc81c"

# Westeros DRM GL SoC backend (tag 2.1.2)
WESTEROS_SOC_URI ?= "${CORE_GITHUB_ROOT}/westeros-gl-drm;${CORE_GITHUB_SRC_URI_SUFFIX};name=westeros-soc"
SRCREV_westeros-soc ?= "80198a81b6b0ff1440cc5a45dcce33a07aa50b0d"

# Essos (separate repo; tag 2.1.2 — keep Westeros --disable-essos)
ESSOS_URI ?= "${CORE_GITHUB_ROOT}/essos;${CORE_GITHUB_SRC_URI_SUFFIX};name=essos"
SRCREV_essos ?= "e072f3e9a2fe55a4edc4ee5f9e7b6021ce9bf66b"

LICENSE_LOCATION ?= "${S}/LICENSE"
LIC_FILES_CHKSUM ?= "file://${LICENSE_LOCATION};md5=8fb65319802b0c15fc9e0835350ffa02"
