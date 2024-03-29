SUMMARY = "OPKG package feed configuration"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
PR = "r3"
PACKAGE_ARCH = "${MACHINE_ARCH}"
INHIBIT_DEFAULT_DEPS = "1"

#FEEDNAMEPREFIX ?= "INVALID"
#FEEDURIPREFIX ?= "INVALID"

do_compile() {
	mkdir -p ${S}/${sysconfdir}/opkg/

	archconf=${S}/${sysconfdir}/opkg/arch.conf

	rm -f $archconf
	ipkgarchs="${ALL_MULTILIB_PACKAGE_ARCHS}"
	# we only create feeds for a subset of supported archs
	feedarchs="all ${TUNE_PKGARCH} ${MACHINE_ARCH}"
	priority=1
	for arch in $ipkgarchs; do 
		echo "arch $arch $priority" >> $archconf
		priority=$(expr $priority + 5)
	done

	basefeedconf=${S}/${sysconfdir}/opkg/base-feeds.conf

	rm -f $basefeedconf
	touch $basefeedconf

	for arch in $feedarchs; do
#		echo "src/gz ${FEEDNAMEPREFIX}-$arch ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${FEEDURIPREFIX}$arch" >> $basefeedconf
		echo "src/gz ${FEEDNAMEPREFIX}-$arch ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/$arch" >> $basefeedconf
	done
}


do_install () {
	install -d ${D}${sysconfdir}/opkg
	install -m 0644  ${S}/${sysconfdir}/opkg/* ${D}${sysconfdir}/opkg/
}

FILES:${PN} = "${sysconfdir}/opkg/ "
