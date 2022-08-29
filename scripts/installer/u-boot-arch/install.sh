#!/bin/sh

# Based on:
# https://github.com/opencomputeproject/onie/blob/8f610cc0e9690182d4045f800dbcc4e021a4f843/demo/installer/u-boot-arch/install.sh

#  Copyright (C) 2014,2015 Curt Brune <curt@cumulusnetworks.com>
#  Copyright (C) 2015 david_yang <david_yang@accton.com>
#
#  SPDX-License-Identifier:     GPL-2.0

set -e

BISDN_ENABLE_NOS_MODE=1

create_hw_load_str() {
    echo "cp.b $img_start \$loadaddr $img_sz"
}

platform_get_firmware_type()
{
	echo "u-boot"
}

. ./machine/${onie_platform}/platform.conf

platform_install_bootloader_entry()
{
    local blk_dev=$1
    local bisdn_linux_part=$2
    local bisdn_linux_mnt=$3
    local separator=

    if [ -f fitImage ]; then
	cp fitImage $bisdn_linux_mnt/boot/uImage
    fi

    if [ ! -f $bisdn_linux_mnt/boot/uImage ]; then
        echo "Error: No kernel image in root fs"
        exit 1
    fi

    # Fix broken u-boot environment variables (presumably left by another NOS).
    machine_fixups

    # Work-around to support yocto warrior-style fit node names which used '@'.
    if grep -q "kernel@1" $bisdn_linux_mnt/boot/uImage; then
        separator="@"
    else
        separator="-"
    fi

    # Find GUID of BISDN Linux partition and FIT configuration unit name, then
    # build a u-boot command string for loading and booting the kernel.
    hw_load_str="$(create_hw_load_str $blk_dev $bisdn_linux_part $separator)"

    echo "Updating U-Boot environment variables"
    (cat <<EOF
hw_load $hw_load_str
copy_img echo "Loading BISDN Linux image..." && run hw_load
nos_bootcmd run copy_img && setenv bootargs quiet console=\$consoledev,\$baudrate && bootm \$loadaddr
EOF
    ) > /tmp/env.txt

    fw_setenv -f -s /tmp/env.txt
}
