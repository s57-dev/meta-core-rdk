cmake_minimum_required(VERSION 3.10)
project(deepsleepmgr-hal-qemu C)

add_library(iarmmgrs-deepsleep-hal SHARED plat-deepsleep-stub.c)
set_target_properties(iarmmgrs-deepsleep-hal PROPERTIES
    VERSION 0.0.0
    SOVERSION 0
    OUTPUT_NAME iarmmgrs-deepsleep-hal
)

install(TARGETS iarmmgrs-deepsleep-hal
    LIBRARY DESTINATION lib
    ARCHIVE DESTINATION lib
)
