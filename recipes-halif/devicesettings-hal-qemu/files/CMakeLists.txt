cmake_minimum_required(VERSION 3.10)
project(ds-hal VERSION 0.0.0 LANGUAGES C)

add_library(ds-hal SHARED ds-hal-stub.c)
set_target_properties(ds-hal PROPERTIES
    OUTPUT_NAME ds-hal
    SOVERSION 0
    VERSION 0.0.0
)

include(GNUInstallDirs)
install(TARGETS ds-hal
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
)
