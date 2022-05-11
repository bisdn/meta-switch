SUMMARY = "CSP 7551 cpld module"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=12f884d2ae1ff87c09e5b7ccc2c4ca7e"

inherit module

SRC_URI = " \
	  file://Makefile \
          file://accton_i2c_cpld.c \
	  file://COPYING \
          "

S = "${WORKDIR}"

#KERNEL_MODULE_AUTOLOAD += " accton-csp7551-cpld"
