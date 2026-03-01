FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive

# Install base dependencies + Java 21 (Audiveris 5.9 needs it)
RUN apt-get update && apt-get install -y --no-install-recommends \
    wget \
    gnupg \
    software-properties-common \
    python3 \
    python3-pip \
    xvfb \
    tesseract-ocr \
    tesseract-ocr-eng \
    libfreetype6 \
    libfontconfig1 \
    libheif-dev \
    && rm -rf /var/lib/apt/lists/*

# Install FastAPI + OpenCV + numpy + HEIC support for image preprocessing
RUN pip3 install --no-cache-dir fastapi uvicorn python-multipart Pillow numpy opencv-python-headless pillow-heif

# Download and install Audiveris 5.9.0 .deb (ubuntu22.04)
# Extract manually to avoid postinst xdg-desktop-menu failure in Docker
RUN wget -q https://github.com/Audiveris/audiveris/releases/download/5.9.0/Audiveris-5.9.0-ubuntu22.04-x86_64.deb -O /tmp/audiveris.deb \
    && apt-get update \
    && apt-get install -y --no-install-recommends libasound2 libxi6 libxtst6 \
    && dpkg-deb -x /tmp/audiveris.deb / \
    && rm /tmp/audiveris.deb \
    && rm -rf /var/lib/apt/lists/*

# Install Tesseract language data for OCR
RUN mkdir -p /root/.config/AudiverisLtd/audiveris/tessdata && \
    wget -q https://github.com/tesseract-ocr/tessdata/raw/main/eng.traineddata \
    -O /root/.config/AudiverisLtd/audiveris/tessdata/eng.traineddata && \
    wget -q https://github.com/tesseract-ocr/tessdata/raw/main/deu.traineddata \
    -O /root/.config/AudiverisLtd/audiveris/tessdata/deu.traineddata && \
    wget -q https://github.com/tesseract-ocr/tessdata/raw/main/fra.traineddata \
    -O /root/.config/AudiverisLtd/audiveris/tessdata/fra.traineddata && \
    wget -q https://github.com/tesseract-ocr/tessdata/raw/main/ita.traineddata \
    -O /root/.config/AudiverisLtd/audiveris/tessdata/ita.traineddata

# Verify Audiveris is installed and find executables
RUN find /opt -name "audiveris" -o -name "Audiveris" | head -10 && \
    ls -la /opt/audiveris/ 2>/dev/null || ls -la /opt/ | grep -i audiveris

# Create working directories
RUN mkdir -p /app /tmp/audiveris-work /tmp/audiveris-output

COPY preprocess.py /app/preprocess.py
COPY server.py /app/server.py

WORKDIR /app

EXPOSE 8000

# Use xvfb-run since Audiveris needs a display even in batch mode
# 2 workers so one request doesn't block the next
CMD ["uvicorn", "server:app", "--host", "0.0.0.0", "--port", "8000", "--workers", "2"]
