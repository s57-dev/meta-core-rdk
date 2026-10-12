cmake_minimum_required(VERSION 3.10)
project(rdk-gstreamer-utils-platform-qemu CXX)

find_package(PkgConfig REQUIRED)
pkg_check_modules(GST REQUIRED gstreamer-1.0)

add_library(rdkgstreamerutilsplatform SHARED rdk_gstreamer_utils_soc_stub.cpp)
target_include_directories(rdkgstreamerutilsplatform PRIVATE ${GST_INCLUDE_DIRS})
target_link_libraries(rdkgstreamerutilsplatform PRIVATE ${GST_LIBRARIES})
set_target_properties(rdkgstreamerutilsplatform PROPERTIES VERSION 1.0.0 SOVERSION 1)

install(TARGETS rdkgstreamerutilsplatform LIBRARY DESTINATION lib)
