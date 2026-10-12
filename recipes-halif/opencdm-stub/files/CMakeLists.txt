cmake_minimum_required(VERSION 3.10)
project(opencdm-stub CXX)

find_package(PkgConfig REQUIRED)
pkg_check_modules(GST REQUIRED gstreamer-1.0)

add_library(ocdm SHARED
    open_cdm.cpp
    open_cdm_ext.cpp
    open_cdm_adapter.cpp
    RialtoGStreamerEMEProtectionMetadata.cpp
)

target_include_directories(ocdm
    PUBLIC
        $<BUILD_INTERFACE:${CMAKE_CURRENT_SOURCE_DIR}>
        $<INSTALL_INTERFACE:include>
    PRIVATE
        ${GST_INCLUDE_DIRS}
)
target_link_libraries(ocdm PRIVATE ${GST_LIBRARIES})
set_target_properties(ocdm PROPERTIES VERSION 1.0.0 SOVERSION 1)

install(TARGETS ocdm
    EXPORT ocdmTargets
    LIBRARY DESTINATION lib
)
install(DIRECTORY opencdm DESTINATION include)
install(EXPORT ocdmTargets
    FILE ocdmTargets.cmake
    NAMESPACE ocdm::
    DESTINATION lib/cmake/ocdm
)

include(CMakePackageConfigHelpers)
configure_package_config_file(
    ${CMAKE_CURRENT_SOURCE_DIR}/ocdmConfig.cmake.in
    ${CMAKE_CURRENT_BINARY_DIR}/ocdmConfig.cmake
    INSTALL_DESTINATION lib/cmake/ocdm
)
install(FILES ${CMAKE_CURRENT_BINARY_DIR}/ocdmConfig.cmake
    DESTINATION lib/cmake/ocdm
)

# pkg-config for legacy finders
configure_file(${CMAKE_CURRENT_SOURCE_DIR}/ocdm.pc.in ${CMAKE_CURRENT_BINARY_DIR}/ocdm.pc @ONLY)
install(FILES ${CMAKE_CURRENT_BINARY_DIR}/ocdm.pc DESTINATION lib/pkgconfig)
