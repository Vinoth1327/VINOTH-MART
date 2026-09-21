FROM ubuntu:24.04

RUN apt-get update && apt-get install -y \
    g++ \
    cmake \
    git \
    curl \
    doxygen \
    graphviz \
    libjsoncpp-dev \
    uuid-dev \
    zlib1g-dev \
    libssl-dev \
    libsqlite3-dev \
    sqlite3 \
    libbrotli-dev \
    && rm -rf /var/lib/apt/lists/*

RUN git clone --depth 1 https://github.com/drogonframework/drogon.git /tmp/drogon && \
    cd /tmp/drogon && \
    mkdir build && \
    cd build && \
    cmake .. \
        -DCMAKE_BUILD_TYPE=Release \
        -DBUILD_CTL=OFF \
        -DBUILD_TESTING=OFF && \
    make -j2 && \
    make install && \
    ldconfig

WORKDIR /app

COPY . .

RUN mkdir -p build && \
    cd build && \
    cmake .. && \
    cmake --build . --config Release

EXPOSE 8080

CMD ["./build/VinothMartBackend"]