# RDK middleware expects <cJSON.h> at the include root.
do_install:append() {
    ln -sf cjson/cJSON.h ${D}${includedir}/cJSON.h
}
