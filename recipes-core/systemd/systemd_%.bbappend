# use systemd to manage networks
# See http://www.freedesktop.org/software/systemd/man/systemd.network.html
PACKAGECONFIG_append = " coredump networkd resolved"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
    file://enp.network \
    file://20-network-io.conf \
"

FILES_${PN} += "\
    ${sysconfdir}/systemd/network/* \
    ${sysconfdir}/sysctl.d/20-network-io.conf \
    "

do_install_append() {
   # systemd-networkd
   install -d ${D}${sysconfdir}/systemd/network/
   install -m 0644 ${WORKDIR}/*.network ${D}${sysconfdir}/systemd/network/

   # systemd-sysctl
   install -d ${D}${sysconfdir}/sysctl.d/
   install -m 0644 ${WORKDIR}/20-network-io.conf ${D}${sysconfdir}/sysctl.d/
}

USERADD_PARAM_${PN} += "--system --home /dev/null systemd-journal-gateway"
