模块核心（app 文件夹 src 部分 main 文件夹）部分详解

首先，这次我选择了阿里云的云服务器作为我们的后端数据库。为什么不用之前的华为云了呢？这是因为华为云有预装的MySQL，这就给我们省了很多事了。

1. java 文件夹

   下有四个主要 kotlin 文件，分别为 login.kt, MainActivity.kt, MySQLHelper.kt, RegisterActivity.kt

   1. MySQLHelper.kt
   
      实现与 mysql 数据库的连接，实现注册时插入数据表行的内容，实现登陆时检查 users 数据表的功能
   
      ![2aa651a159b529cb04726acdba018e0](C:/Users/Administrator/Documents/WeChat Files/wxid_argym1a5ini132/FileStorage/Temp/2aa651a159b529cb04726acdba018e0.png)
   
      此处的私密信息我另外发给你们
   
   2. login.kt
   
      指向登陆界面 activity_login.xml；
   
      实现登陆功能，利用 MySQLHelper.kt 中检查 users 数据表来判断是否登陆成功，登陆失败提示："账号密码错误！请重新输入!"，登陆成功直接跳转至 MainActivity.kt；
   
      绑定注册按钮，点击注册按钮时跳转至注册部分 RegisterActivity.kt
   
   3. MainActivity.kt
   
      指向登陆成功的界面 activity_main.xml
   
   4. RegisterActivity.kt
   
      指向注册界面 activity_register.xml；
   
      利用 MySQLHelper.kt 中插入方法实现对用户数据的注册

2. res 文件下 layout 文件夹

   包含三个主要的 xml 文件：activity_login.xml，activity_main.xml，activity_register.xml

不想多说了，自己看吧，你们目前需要优化的也是这些布局，主界面那个不用动，目标是优化到墨刀中的布局样式，注册登陆的后端功能我已经基本完成，以下是目前较为简陋的布局：

![6e8dca211de106d69fa5d5576838585](C:/Users/Administrator/Documents/WeChat Files/wxid_argym1a5ini132/FileStorage/Temp/6e8dca211de106d69fa5d5576838585.png)

![fa9fe3562d37eb145c5679a3d337f5b](C:/Users/Administrator/Documents/WeChat Files/wxid_argym1a5ini132/FileStorage/Temp/fa9fe3562d37eb145c5679a3d337f5b.png)

我会把墨刀中用到的图片背景打包发给你们，做完后快速学习轮播图怎么做，预估下一个part会需要你们做一下轮播图