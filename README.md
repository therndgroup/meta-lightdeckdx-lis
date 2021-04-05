# meta-lightdeckdx-lis  

### The LightDeckDx LIS Server Yocto layer installs a gRPC-based LIS server for use by the LightDeck instrument software. These instructions descibe the changes necessary when adding the layer to an existing Poky build configuration  


#### I. In the Poky `build/conf/` directory make the following changes:  

* bblayers.conf  
##### Update bblayers.conf to include the dependencies for the LIS server  
---  
```
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
```  

* local.conf  
##### Update local.conf to add lightdeckdx-lis to the list of packages to include in the final image  
---
`IMAGE_INSTALL_append = " lightdeckdx-lis"`

#### II. In the `layer.conf` of each meta layer listed in `bblayers.conf`, ensure both `hardknott` and `gatesgarth` Poky versions are specified  
* layer.conf   
---
`LAYERSERIES_COMPAT_xxx-layer = " hardknott gatesgarth"`
 

