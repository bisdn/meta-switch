SUMMARY = "CSP 7551 sfp module"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

inherit module

SRC_URI = " \
	  file://Makefile \
          file://x86-64-accton-csp7551-sfp.c \
	  file://COPYING \
          "

S = "${WORKDIR}"

#KERNEL_MODULE_AUTOLOAD += " accton-csp7551-sfp"

# Ignore missing symbols from accton-csp7551-cpld (e.g., accton_i2c_cpld_read)
EXTRA_OEMAKE += " KBUILD_MODPOST_WARN=1"
