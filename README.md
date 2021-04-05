# meta-lightdeckdx-lis  

The LightDeckDx LIS Server `meta-lightdeckdx-lis` layer adds a gRPC-based LIS server to the Yocto image build for use by the LightDeckDx instrument software. This layer is used to interface a LightDeckDx instrument to an external LIS host. These instructions descibe the changes necessary for adding the `meta-lightdeckdx-lis` layer to an existing Poky build configuration.  

<br>  

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

#### II. Update Poky layer series compatibility in `conf/layer.conf` of each meta layer configured in `bblayers.conf` from step I. above 
* layer.conf  
##### Ensure both `hardknott` and `gatesgarth` Poky versions are specified 
---
`LAYERSERIES_COMPAT_xxx-layer = " hardknott gatesgarth"`  

#### III. Package output:  
`lightdeckdx-lis-1.0-r0.armv7vet2hf_neon.rpm`


 

