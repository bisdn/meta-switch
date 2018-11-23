# Copyright (C) 2018 Tobias Jungel <tobias.jungel@bisdn.de>
# Released under the MIT license (see COPYING.MIT for the terms)

require baseboxd.inc
inherit autotools

SRCREV = "42a8d4372c3a91849c6a173a827666095f5e9513"

# install service and sysconfig
do_install_append() {
   # add directories
   install -d ${D}${sysconfdir}/default \
              ${D}${systemd_unitdir}/system

   # install service file and config
   install -m 0644 ${S}/pkg/systemd/sysconfig.template ${D}${sysconfdir}/default/baseboxd
   install -m 0644 ${S}/pkg/systemd/baseboxd.service ${D}${systemd_unitdir}/system

   # update service file
   sed -i -e 's,/etc/sysconfig/baseboxd,/etc/default/baseboxd,g' \
          -e 's,/sbin/baseboxd,${sbindir}/baseboxd,g' \
          ${D}${systemd_unitdir}/system/baseboxd.service
}