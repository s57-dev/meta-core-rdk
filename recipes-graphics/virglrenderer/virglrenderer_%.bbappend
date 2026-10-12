# anongit.freedesktop.org git:// fetches often hang; use GitLab HTTPS.
SRC_URI = "git://gitlab.freedesktop.org/virgl/virglrenderer.git;protocol=https;branch=branch-0.9.1 \
           file://0001-meson.build-use-python3-directly-for-python.patch \
           file://cve-2022-0135.patch \
           file://cve-2022-0175.patch \
"
