# Copyright (C) 2018 Tobias Jungel <tobias.jungel@bisdn.de> and Henrike Wissing <henrike.wissing@bisdn.de>
# Released under the MIT license (see COPYING.MIT for the terms)

require minimal.bb

IMAGE_FEATURES += " package-management"

BISDN_SWITCH_IMAGE_EXTRA_INSTALL += "\
    baseboxd \
    baseboxd-tools \
    bridge-utils \
    curl \
    docker-ce \
    ethtool \
    file \
    frr \
    git \
    grpc-cli \
    ipcalc \
    iproute2-ss \
    iptables \
    jq \
    keepalived \
    kernel-module-linux-kernel-bde \
    kernel-module-linux-user-bde \
    less \
    lmsensors-fancontrol \
    lmsensors-pwmconfig \
    lmsensors-sensors \
    lmsensors-sensorsconfconvert \
    lmsensors-sensorsdetect \
    man-db \
    man-pages \
    mstpd \
    ofagent \
    ofdpa \
    ofdpa-grpc \
    ofdpa-tools \
    procps \
    python3 \
    python3-ofdpa \
    python3-pip \
    python3-ryu \
    radvd \
    rsync \
    run-preinsts \
    tcpdump \
    tmux \
    vim \
    "

# dependencies/dependencies.yaml
# Default packages to be installed on all supported OS.
BISDN_SWITCH_IMAGE_EXTRA_INSTALL += "\
    autoconf \
    automake \
    cmake \
    flex \
    gcc \
    libtool \
    make \
    net-tools \
    python3-dev \
    "

# Default packages to be installed for all supported versions of Ubuntu.
BISDN_SWITCH_IMAGE_EXTRA_INSTALL += "\
    aspell \
    bzip2 \
    bzip2-dev \
    bridge-utils \
    expat \
    elfutils \
    elfutils-dev \
    g++ \
    glibc-dev \
    libedit \
    libedit-dev \
    libffi \
    libffi-dev \
    pkgconfig \
    sqlite3 \
    "

# thrift
BISDN_SWITCH_IMAGE_EXTRA_INSTALL += "\
    bison \
    libevent \
    libevent-dev \
    openssl \
    openssl-dev \
    "

# bf_platforms
BISDN_SWITCH_IMAGE_EXTRA_INSTALL += "\
    "

# switch_p4_p16
BISDN_SWITCH_IMAGE_EXTRA_INSTALL += "\
    libnl \
    libnl-dev \
    libxml-simple-perl \
    "

# bf_diags
BISDN_SWITCH_IMAGE_EXTRA_INSTALL += "\
    "

# pi
BISDN_SWITCH_IMAGE_EXTRA_INSTALL += "\
    "

# bisdn convenience
BISDN_SWITCH_IMAGE_EXTRA_INSTALL += "\
    htop \
    tree \
    gptfdisk \
    kernel-devsrc \
    "
IMAGE_LINGUAS = "en-us"

LICENSE = "MIT"
