# meta-lightdeckdx-lis  

The LightDeckDx LIS Server `meta-lightdeckdx-lis` layer adds a gRPC-based LIS server to the Yocto image build for use by the LightDeckDx instrument software. This layer is used to interface a LightDeckDx instrument to an external LIS host. These instructions descibe the changes necessary for adding the `meta-lightdeckdx-lis` layer to an existing Poky build configuration.  

Prerequisites:
* .NET 5.0 SDK installed on build machine  (https://dotnet.microsoft.com/download/dotnet/5.0)
 ---

#### I. In the Poky `build/conf/` directory make the following changes  

* bblayers.conf  
##### Update bblayers.conf to include the dependencies for the LIS server  
  
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

`IMAGE_INSTALL_append = " lightdeckdx-lis"`  

---
#### II. Update Poky layer series compatibility in `conf/layer.conf` of each meta layer configured in `bblayers.conf` from step I above 
* layer.conf  
##### Ensure both `hardknott` and `gatesgarth` Poky versions are specified 
`LAYERSERIES_COMPAT_xxx-layer = " hardknott gatesgarth"`  

---
#### III. Package output  
`lightdeckdx-lis-1.0-r0.armv7vet2hf_neon.rpm`  

---
#### IV. Running  
A script named `lightdeckdx-lis`, located in `/usr/bin/` can be used to start the LIS server. A `systemd` service should be configured to call this script at module boot.  

Application NOTE: If both the gRPC C++ client code and gRPC LIS server are running on the module, you can connect using a Unix Domain Socket instead of the default TCP-based connection. To enable a UDS-based connection, follow the example at (https://github.com/grpc/grpc-dotnet/blob/master/examples/Transporter/Server/Program.cs) for for gRPC LIS server host and (https://github.com/Danie1/grpc/blob/example-helloworld-uds/examples/cpp/helloworld_uds/greeter_client.cc) for the C++ gRPC client connection. Use the same UDS name for both client and server.
