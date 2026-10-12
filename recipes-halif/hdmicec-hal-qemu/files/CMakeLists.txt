cmake_minimum_required(VERSION 3.10)
project(RCECHal VERSION 1.0.0 LANGUAGES C)

add_library(RCECHal SHARED hdmi-cec-hal-stub.c)
set_target_properties(RCECHal PROPERTIES SOVERSION 1 VERSION 1.0.0)

include(GNUInstallDirs)
install(TARGETS RCECHal LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR})
