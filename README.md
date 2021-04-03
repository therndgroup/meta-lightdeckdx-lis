# meta-lightdeckdx-lis
LightDeckDx LIS Server Yocto layer

bblayers.conf
---
BBLAYERS ?= " \
  /home/bcraun/oe/poky/meta \
  /home/bcraun/oe/poky/meta-poky \
  /home/bcraun/oe/poky/meta-yocto-bsp \
  /home/bcraun/oe/poky/meta-oe \
  /home/bcraun/oe/poky/meta-perl \
  /home/bcraun/oe/poky/meta-python \
  /home/bcraun/oe/poky/meta-networking \
  /home/bcraun/oe/poky/meta-security \
  /home/bcraun/oe/poky/meta-lightdeckdx-lis \
  "
  
  local.conf
  ---
  IMAGE_INSTALL_append = " lightdeckdx-lis"
  
  Meta layer dependencies
  ---
  LAYERSERIES_COMPAT_xxx-layer = " hardknott gatesgarth"
 

