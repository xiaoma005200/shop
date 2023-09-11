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









