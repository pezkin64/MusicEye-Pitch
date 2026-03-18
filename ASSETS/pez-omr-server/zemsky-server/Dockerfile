# ── Stage 1: Build fat JAR ────────────────────────────────────────────────────
# Use ARM64 maven image — must match the host architecture
FROM maven:3.9-eclipse-temurin-11 AS builder

WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline -q
COPY src/ src/
RUN mvn package -DskipTests -q
RUN echo "✓ JAR built: $(ls -lh target/zemsky-server-1.0.0.jar)"

# ── Stage 2: Runtime ──────────────────────────────────────────────────────────
# Ubuntu 22.04 ARM64 — same libc ABI the .so was compiled against (Android NDK r21+)
FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive

# Java 11 JRE + runtime libs needed by liblept / libsource-lib
RUN apt-get update && apt-get install -y --no-install-recommends \
    openjdk-11-jre-headless \
    libc6 \
    libstdc++6 \
    libgcc-s1 \
    zlib1g \
    zlib1g-dev \
    libjpeg-turbo8 \
    libpng16-16 \
    && rm -rf /var/lib/apt/lists/*

# Directories
RUN mkdir -p /app /native /assets/templates /assets/nnModels /tmp/zemsky

# Fat JAR
COPY --from=builder /build/target/zemsky-server-1.0.0.jar /app/zemsky-server.jar

# Native .so files (ARM64, from APK split_config.arm64_v8a.apk)
COPY native/libsource-lib.so /native/
COPY native/liblept.so       /native/
COPY native/libjpgt.so       /native/
COPY native/libpngt.so       /native/
COPY native/liblame.so       /native/

# NN models + template images (from APK base assets)
COPY assets/templates/  /assets/templates/
COPY assets/nnModels/   /assets/nnModels/

EXPOSE 8000

ENV ZEMSKY_SO_DIR=/native
ENV ZEMSKY_ASSETS_DIR=/assets
ENV ZEMSKY_TMP_DIR=/tmp/zemsky
ENV ZEMSKY_PORT=8000
ENV LD_LIBRARY_PATH=/native:/usr/lib/aarch64-linux-gnu:/lib/aarch64-linux-gnu

CMD ["java", \
     "-Djava.library.path=/native", \
     "-XX:+UseContainerSupport", \
     "-XX:MaxRAMPercentage=75.0", \
     "-jar", "/app/zemsky-server.jar"]
