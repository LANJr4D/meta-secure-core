SUMMARY = "Software stack for TPM2."
DESCRIPTION = "tpm2.0-tss like woah."
SECTION = "security/tpm"

LICENSE = "BSD-2-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=500b2e742befc3da00684d8a1d5fd9da"

DEPENDS += "autoconf-archive pkgconfig"

PV = "1.1.0+git${SRCPV}"

SRC_URI = "\
    git://github.com/01org/TPM2.0-TSS.git \
"
SRCREV = "3fb91634e61c97a0ae732bc5c298b188c23376cf"

S = "${WORKDIR}/git"

inherit autotools pkgconfig

do_configure_prepend() {
    # execute the bootstrap script
    currentdir="$(pwd)"
    cd "${S}"
    ACLOCAL="aclocal --system-acdir=${STAGING_DATADIR}/aclocal" \
        ./bootstrap
    cd "${currentdir}"
}

PACKAGES = "\
    ${PN}-dbg \
    libtss2 \
    libtss2-dev \
    libtss2-staticdev \
    libtss2-doc \
    libtctidevice \
    libtctidevice-dev \
    libtctidevice-staticdev \
    libtctisocket \
    libtctisocket-dev \
    libtctisocket-staticdev \
    libmarshal \
    libmarshal-dev \
    libmarshal-staticdev \
"

FILES_libtss2 = "${libdir}/libsapi.so.*"
FILES_libtss2-dev = "\
    ${includedir}/sapi \
    ${includedir}/tcti/common.h \
    ${libdir}/libsapi.so \
    ${libdir}/pkgconfig/sapi.pc \
"
FILES_libtss2-staticdev = "\
    ${libdir}/libsapi.a \
    ${libdir}/libsapi.la \
"
FILES_libtss2-doc = "\
    ${mandir} \
"
FILES_libtctidevice = "${libdir}/libtcti-device.so.*"
FILES_libtctidevice-dev = "\
    ${includedir}/tcti/tcti_device.h \
    ${libdir}/libtcti-device.so \
    ${libdir}/pkgconfig/tcti-device.pc \
"
FILES_libtctidevice-staticdev = "\
    ${libdir}/libtcti-device.a \
    ${libdir}/libtcti-device.la \
"
FILES_libtctisocket = "${libdir}/libtcti-socket.so.*"
FILES_libtctisocket-dev = "\
    ${includedir}/tcti/tcti_socket.h \
    ${libdir}/libtcti-socket.so \
    ${libdir}/pkgconfig/tcti-socket.pc \
"
FILES_libtctisocket-staticdev = "\
    ${libdir}/libtcti-socket.a \
    ${libdir}/libtcti-socket.la \
"
FILES_libmarshal = "${libdir}/libmarshal.so.*"
FILES_libmarshal-dev = "${libdir}/libmarshal.so"
FILES_libmarshal-staticdev = "\
    ${libdir}/libmarshal.a \
    ${libdir}/libmarshal.la \
"

RDEPENDS_libtss2 += "libmarshal"
RDEPENDS_libtctidevice += "libmarshal"

RRECOMMENDS_${PN} += "\
    kernel-module-tpm-crb \
    kernel-module-tpm-tis \
"
