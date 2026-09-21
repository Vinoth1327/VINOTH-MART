cmake_minimum_required(VERSION 3.20)

project(VinothMartBackend)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(Drogon CONFIG REQUIRED)
find_package(unofficial-sqlite3 CONFIG REQUIRED)

add_executable(VinothMartBackend
    main.cpp
)

target_link_libraries(VinothMartBackend
    PRIVATE
    Drogon::Drogon
    unofficial::sqlite3::sqlite3
)