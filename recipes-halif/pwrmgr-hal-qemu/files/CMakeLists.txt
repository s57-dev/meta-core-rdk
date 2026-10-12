cmake_minimum_required(VERSION 3.10)
project(pwrmgr-hal-qemu C)

add_library(iarmmgrs-power-hal SHARED plat-power-stub.c)
set_target_properties(iarmmgrs-power-hal PROPERTIES
    VERSION 0.0.0
    SOVERSION 0
    OUTPUT_NAME iarmmgrs-power-hal
)

install(TARGETS iarmmgrs-power-hal
    LIBRARY DESTINATION lib
    ARCHIVE DESTINATION lib
)
