# AssembleManager

为完成对代码库svn或git进行管理，代码库特定版本的获取出来然后进行编译，然后再做后续操作，
功能尚未完成先做了第一部分就是从命令行获取svn的日志，并在界面上显示出来 

# 这个工程的目标是希望用统一使用web的方式控制服务器提交编译任务


代码使用VSCode在Mac上开发

OS:Mac OS
IDE: VSCode
Java + SpringBoot

随着需要编译的代码和平台越来越多，东西越多，越需要一个比较统一的平台对最终代码进行编译，通常这么一个平台可能是需要公司有专人进行维护的，这里尝试做一个简化的版本，看看能不能用起来。

cn.properties中

yourcompany.title是web显示的标题

yourcompany.dbpath是数据保存的目录，数据文件叫做db.xml

目前使用xml保存数据，没有数据库支持，感兴趣的同学可以修改AMDataBaseImpl.java或者继承AMDataBase.java来访问自己的数据库

application.properties

里的有关数据库的内容是没用的可以去掉

spring.jpa.*

spring.datasource.*

MacSVNAMItemImpl.java 继承自 AMItem.java
是mac下从命令行获取svn结果的实现，可以修改成其他平台下获取svn/git的实现

现在做到的功能:

工程名称与svn路径对应关系的管理
显示特定工程的svn日志信息
下一步要做的:

服务器根据特定的代码版本信息从svn获取到特定目录进行编译
编译后内容的展示，编译失败日志的展示
支持Windows/linux/mac/等操作系统的编译环境

