# meta-lightdeckdx-lis  
## The LightDeckDx LIS Server Yocto layer installs a gRPC-based LIS server for use by LightDeck instrument software. These instructions descibe the changes necessary when adding the layer to an existing Poky build configuration  

### In the Poky `build/conf/` directory make the following changes:  

#### I. bblayers.conf  
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

#### II. local.conf  
##### Update the local.conf to add lightdeckdx-lis to the list of packages to include in the final image  
---
`IMAGE_INSTALL_append = " lightdeckdx-lis"`

### In each of the dependent meta layers listed in bblayers.conf, update the layer series compatibility:  

#### III. layer.conf  
##### Ensure both `hardknott` and `gatesgarth` Poky versions are specified  
---
`LAYERSERIES_COMPAT_xxx-layer = " hardknott gatesgarth"`
 

