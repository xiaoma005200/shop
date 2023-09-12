# shop微服务商城系统
微服务商城系统，模拟实现商城系统的所有功能。

# 项目描述
微服务电商系统模拟京东商城的架构和实现，具备商城系统几乎所有的功能，用户可以访问商城，搜索商品，选择商品，加入购物车，查询订单，以及进行订单支付。

# 系统架构
![GZTD@G@F8_J_{@3` X4$I3](https://github.com/xiaoma005200/shop/assets/102530019/785bbfa8-5fe5-4ddb-8209-e2e3932458d7)



# 技术栈
## 前端
* Vue 2.0框架
* Thymeleaf 3.1.x
* BootStrap 3.3.x
* JQuery 3.1.x

## 后端
* SpringBoot 2.3.2.RELEASE
* MySQL 5.1.x 数据库
* Reids 缓存
* RabbitMQ 消息中间件
* ElasticSearch 企业级搜索框架
* FastDFS 分布式文件系统
* SpringCloud Hoxton.SR7
* Spring Cloud Gateway 网关
* Spring Cloud Eureka 注册中心
* Spring Cloud Ribbon/OpenFeign 负载均衡/远程服务调用（Http+JSON）
* Spring Cloud Hystrix 熔断限流机制
* Spring Cloud Config 配置文件管理
* Spring Cloud Bus 消息总线

# 项目模块
* shop-parent 父模块（项目依赖）
* shop-common 公用模块（定义全局配置，接口，实体类，工具类等）
* eureka-server 注册中心模块
* shop-static-resources 静态资源模块（静态资源统一管理）
* shop-gateway 网关模块
* shop-auth-web 用户登录认证模块
* shop-product-cart-web 购物车模块
* shop-product-order-web 订单模块
* shop-product-detail-web 商品详情模块（页面相关）
* shop-product-consumer 商品添加模块（商家调用模块）
* shop-product-provider 商品添加模块（商家调用模块）
* shop-product-search-web 商品查询模块（页面相关）
* shop-search-provider 商品查询模块
* shop-user-consumer 用户登录模块
* shop-user-provider 用户登录模块

# 项目展示
* 登录页
  ![M6U@6CE%L27%9F9IJBKQWIR](https://github.com/xiaoma005200/shop/assets/102530019/a0ae75c2-e3af-4d28-9ad3-8d92a1855f9f)

* 系统主页
  ![2~7~RS~B O}V%MHE$(JZ`8X](https://github.com/xiaoma005200/shop/assets/102530019/c3406e86-9c0d-4fd4-acd5-fccf0c7c7a67)

* 商品详情页
  ![H_N_LBXRAEEP%`K3@LENOHJ](https://github.com/xiaoma005200/shop/assets/102530019/b184a319-9f3a-47c0-946e-1f3feb556990)
  ![35XDDOMQ80 8HZ27@7$%~~X](https://github.com/xiaoma005200/shop/assets/102530019/8a0a100d-2db5-437f-bc81-dc52d7702aad)

* 成功添加购物车页
  ![ACVB%E2$MQH1_XW)CYDPNF2](https://github.com/xiaoma005200/shop/assets/102530019/26152145-7499-45f6-ad65-ce670d44e12b)

* 购物车主页
  ![(JZA`~47A@I% K%63 MGYPC](https://github.com/xiaoma005200/shop/assets/102530019/88f8ef2a-aa19-4b48-8e53-7f664bb1efdd)

* 后台登录页
  ![E`6R$ GD 4@ 60CU)1 J2KQ](https://github.com/xiaoma005200/shop/assets/102530019/c3c0bc24-7bef-449e-a37c-794655899ba9)

* 商品属性管理页
  ![KNPC{OY3X3{_}P{GJM0@VET](https://github.com/xiaoma005200/shop/assets/102530019/5e46bee7-e159-4d4b-ad2c-14c6ee19bd17)

* 商品属性修改页
  ![I7_E86{M8K 3A2`GK LN V](https://github.com/xiaoma005200/shop/assets/102530019/7f6c6c53-23f3-4558-a2c3-5d954dfbc51f)

* 商品spu管理页
  ![3 AYAT8%8GPQDC4B$BSZKYK](https://github.com/xiaoma005200/shop/assets/102530019/0b332cc2-ce14-4899-b124-66ee86050853)

* 添加商品spu页
  ![R{3Y{{VAIAXO%6LE1 @BA8V](https://github.com/xiaoma005200/shop/assets/102530019/8f6648dc-ea26-4dac-aaa0-08d73b748514)

* 添加商品sku页
  ![`SE~@9A}K4_Q)V2}2TBO7T4](https://github.com/xiaoma005200/shop/assets/102530019/f9fb8324-346d-4fa2-b73d-dc79ae2f84a8)




