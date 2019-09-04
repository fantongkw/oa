#办公自动化系统
此项目是基于JAVA开发的办公自动化系统，该项目使用Spring Boot开发

此项目包含：
 * 全体通知模块
 * 部门管理模块
 * 员工管理模块
 * 角色管理模块
 * 私人聊天模块
 * 定制日程模块
 * 邮件系统模块（待开发，资金有限）

##上手指南
以下指南将帮助你在本地机器上安装和运行该项目

###安装前提
 名称  | 版本  
 ---- | ----- 
 JDK  | 1.8 及以上
 Maven  | 3.3 及以上
 Tomcat  | 8.0 及以上
 MySQL  | 8.0 及以上
 Redis  | 3.0 及以上
 
    注: windows系统需安装Docker，下载Redis镜像

###快速部署
1.  clone 项目到本地
2.  在任意 Java IDE 中导入项目
3.  数据库脚本在项目的 resources 目录下，在 MySQL 中执行数据库脚本
4.  数据库配置在项目的 resources 目录下的application.yaml文件中
5.  在任意 Java IDE 中启动项目

##技术栈

###后端技术栈
 * Spring Boot
 * Spring Security
 * Spring Session
 * MySQL
 * MyBatis
 * Redis
 * Thymeleaf

###前端技术栈
 * Vue
 * Jquery
 * Bootstrapdash
 
##License
Copyright 2019 fantongkw

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.