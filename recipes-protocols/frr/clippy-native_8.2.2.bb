require frr.inc

GIT_BRANCH = "stable/8.2"
# commit hash of release tag frr-8.2.2
SRCREV = "79188bf710e92acf42fb5b9b0a2e9593a5ee9b05"

PR = "r0"

inherit native

EXTRA_OECONF  = " \
	       --disable-babeld \
	       --disable-bgp-announce \
	       --disable-bgp-vnc \
	       --disable-bgpd \
	       --disable-doc \
	       --disable-eigrpd \
	       --disable-isisd \
	       --disable-ldpd \
	       --disable-nhrpd \
 	       --disable-ospf6d \
 	       --disable-ospfapi \
 	       --disable-ospfclient \
 	       --disable-ospfd \
 	       --disable-pbrd \
 	       --disable-pimd \
 	       --disable-ripd \
 	       --disable-ripngd \
 	       --disable-silent-rules \
	       --disable-vtysh \
 	       --disable-watchfrr \
	       --disable-zebra \
	       --disable-zeromq \
	       --enable-clippy-only \
	       "

do_compile:prepend:class-native () {
       export PYTHONHOME=${RECIPE_SYSROOT_NATIVE}/usr
       install -m 0755 -d ${WORKDIR}/build/tests/isisd
}

do_install() {
    install -d ${D}${base_libdir}
    install -c -m 755 ${WORKDIR}/build/lib/clippy ${D}${base_libdir}/clippy
}

BBCLASSEXTEND = "native nativesdk"
