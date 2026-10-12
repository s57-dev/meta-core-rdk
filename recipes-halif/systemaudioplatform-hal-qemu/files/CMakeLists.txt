cmake_minimum_required(VERSION 3.10)
project(systemaudioplatform-hal-qemu CXX)

find_package(PkgConfig REQUIRED)
pkg_check_modules(GST REQUIRED gstreamer-1.0 gstreamer-audio-1.0)

add_library(systemaudioplatform SHARED systemaudioplatform-stub.cpp)
target_include_directories(systemaudioplatform PUBLIC
    ${CMAKE_CURRENT_SOURCE_DIR}
    ${GST_INCLUDE_DIRS}
)
target_link_libraries(systemaudioplatform PRIVATE ${GST_LIBRARIES})
set_target_properties(systemaudioplatform PROPERTIES
    VERSION 1.0.0
    SOVERSION 1
    PUBLIC_HEADER systemaudioplatform.h
)

install(TARGETS systemaudioplatform
    LIBRARY DESTINATION lib
    PUBLIC_HEADER DESTINATION include
)
