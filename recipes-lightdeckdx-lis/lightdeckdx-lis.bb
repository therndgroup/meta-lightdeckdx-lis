DESCRIPTION = "LIS Server application"
SECTION = "applications"
DEPENDS = ""
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = "file://LICENSE;md5=3450d21fdaa457bdbc47196b6eb5ba07"

GIT_URI = "git://git@github.com/therndgroup/lightdeckdx.git;branch=master;protocol=ssh"
SRC_APPNAME = "lightdeckdx-grpc-lis-server"
TARGET_APPNAME = "lightdeckdx-lis"
INSTALLDIR_BIN = "/bin/${TARGET_APPNAME}"
INSTALLDIR_LIB = "/lib/${TARGET_APPNAME}"
BUILD_CONFIG = "Release"
RUNTIME_ID = "linux-arm"
BUILD_OUTPUT = "/LightDeckDx.GrpcServer/bin/${BUILD_CONFIG}/net5.0/${RUNTIME_ID}/publish/*"
DOTNET_PATH = "/usr/bin"

PN = "${TARGET_APPNAME}"
PV = "1.0"
S = "${WORKDIR}/git"

PACKAGES = "${PN}"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRCREV = "master"
SRC_URI += "${GIT_URI}"
SRC_URI += "file://${TARGET_APPNAME}"


INSANE_SKIP_${PN} += "ldflags already-stripped libdir textrel"
INSANE_SKIP_${PN}-dbg += "libdir"
INSANE_SKIP_${PN} += "staticdev"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

FILES_${PN} = "\
  ${prefix}${INSTALLDIR_BIN} \
  ${prefix}${INSTALLDIR_LIB}/* \
"

DEPENDS = "zlib"
RDEPENDS_${PN} = "icu lttng-tools lttng-ust zlib libgssglue krb5 libgssapi-krb5"
CORE_IMAGE_EXTRA_INSTALL += "libunwind libicu icu openssl"
IMAGE_ROOTFS_EXTRA_SPACE_append += "+ 1000000"

PARALLEL_MAKE = "-j 4"
EXTRA_OEMAKE = "DOTNET_PATH=${DOTNET_PATH} BUILD_CONFIG=${BUILD_CONFIG} RUNTIME_ID=${RUNTIME_ID}"

do_compile() {
  bbnote "Entering gRPC LIS server project directory"
  cd ${S}/${SRC_APPNAME}
  bbnote "Started gRPC LIS server build..."
  oe_runmake publish
  bbnote "Finished gRPC LIS server build"
}

do_install() {
  mkdir -p ${D}${prefix}${INSTALLDIR_LIB}
  mkdir -p ${D}${prefix}${INSTALLDIR_BIN}
  cp --preserve=mode,timestamps -R ${B}/${SRC_APPNAME}${BUILD_OUTPUT} ${D}${prefix}${INSTALLDIR_LIB}
  install -m 0755 ${WORKDIR}/${TARGET_APPNAME} ${D}${prefix}${INSTALLDIR_BIN}
}
