SUMMARY = "A library for applications dealing with netlink sockets"
HOMEPAGE = "http://www.infradead.org/~tgr/libnl/"
SECTION = "libs/network"

PE = "1"
PR = "r2"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "flex-native bison-native"

##https://github.com/thom311/${BPN}/releases/download/${BPN}${@d.getVar('PV').replace('.','_')}/${BP}.tar.gz

SRC_URI = " \
    git://github.com/thom311/${BPN}.git;protocol=https \
    file://support-bridge-multicast-database.patch \
    file://0002-link-bonding-parse-and-expose-bonding-options.patch \
    file://0001-WIP-add-info-slave-data-support.patch \
    file://0002-link-bonding-expose-state-on-enslaved-interfaces.patch \
    file://0001-route-link-print-slave-line.patch \
    file://0002-bonding-dump-slave-line.patch \
    file://0001-route-link-add-missing-compare-for-slave-info.patch \
    file://0002-route-link-bonding-add-missing-io_compare.patch \
    file://0003-route-link-bonding-rename-bsi_mask-to-ce_mask.patch \
    file://0004-route-link-bonding-add-missing-compare-to-slave_ops.patch \
"

# this is actually master:
SRCREV = "7b167ef85f6eb4d7faca349302478b2dc121e309"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend() {
  mkdir -p ${S}/doc/m4
}

FILES_${PN} = "${libdir}/libnl-3.so.* \
               ${libdir}/libnl.so.* \
               ${sysconfdir}"
RREPLACES_${PN} = "libnl2"
RCONFLICTS_${PN} = "libnl2"
FILES_${PN}-dev += "${libdir}/libnl/cli/*/*.la"
FILES_${PN}-staticdev += "${libdir}/libnl/cli/*/*.a"

PACKAGES += "${PN}-cli ${PN}-genl ${PN}-idiag ${PN}-nf ${PN}-route ${PN}-xfrm"
FILES_${PN}-cli   = "${libdir}/libnl-cli-3.so.* \
                     ${libdir}/libnl/cli/*/*.so \
                     ${bindir}/genl-ctrl-list \
                     ${bindir}/idiag-socket-details \
                     ${bindir}/nf-* \
                     ${bindir}/nl-*"
FILES_${PN}-genl  = "${libdir}/libnl-genl-3.so.* \
                     ${libdir}/libnl-genl.so.*"
FILES_${PN}-idiag = "${libdir}/libnl-idiag-3.so.*"
FILES_${PN}-nf    = "${libdir}/libnl-nf-3.so.*"
FILES_${PN}-route = "${libdir}/libnl-route-3.so.*"
FILES_${PN}-xfrm  = "${libdir}/libnl-xfrm-3.so.*"
RREPLACES_${PN}-genl = "libnl-genl2"
RCONFLICTS_${PN}-genl = "libnl-genl2"