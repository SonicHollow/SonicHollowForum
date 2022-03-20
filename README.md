# SonicHollowForum
This is the official repository for CPT202 Software Engineering Group Project *Group B1*.

## Guideline

To start, install [git](https://git-scm.com/book/zh/v2/%E8%B5%B7%E6%AD%A5-%E5%AE%89%E8%A3%85-Git) on your PC. 

To clone this repository on your PC, run the command in git:

`git clone git@github.com:SonicHollow/SonicHollowForum.git`

Open this project in IDEA and start coding!

File > New > Project from Existing Sources

Notes: 

1. All entities involving **user generated content** should inherent the class BaseEntity.
2. To solve the possible maven dependency problem when importing the project, you can update your settings.xml ([Where is it?](https://www.cnblogs.com/Small-sunshine/p/11640576.html))  to [this one](https://pan.baidu.com/s/1_0r18fd85cthx7hef4n3cA?pwd=o3kv) (You don't have to). 
3. In order to connect the database, use the *application.properties* in WeChat group. Use the schema "testRig" for testing.

## Updates

#### 3.20

Create a new branch "login", including the completed login function (database queries, verification code and cookie), not including the web pages in version 1. Other files remain the same.

**Differences:**
1. No html5, js, css in version 1. Only login-related files.
2. New LoginController and PageController.
3. New *pom.xml*, *application.properties* and *application-dev.properties*.
4. Only one SpringApplication class.

**Todo**

1. Login testing (database queries, verification code API and cookies).
2. Integrate the registration function and develop the frontend.

#### 3.12

Update the project framework with registration function (no frontend)

## Useful Websites

[Git Tutorials](https://www.runoob.com/git/git-basic-operations.html)

[Bootstrap CSS Framework - Full Course for Beginners](https://www.youtube.com/watch?v=-qfEOE4vtxE)

[Bootstrap 5 & Material Design 2.0 UI KIT](https://github.com/mdbootstrap/mdb-ui-kit)

[Codepen -- Beautiful Code Templates for Frontend](https://codepen.io)

