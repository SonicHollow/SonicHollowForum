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
3. In order to connect the database, use the *application.properties* in WeChat group. For testing, use the schema "testRig" on the server.

## Updates

### 3.29 Login Branch

New Post Detail Page. Update the template page ***templates.html***. You can use the template for any "short" pages. Add a post button and a selector (most recent, most views and most comments) on the post list page. Update the navigations of the pages. Update the Color Scheme of the login page.

### 3.27 Login Branch

New footer and new color scheme on index page. Update and fix some bugs of the navigation bar. Add a new PostList page. Add a **filter** for page permissions (users cannot access myInfo, postList and home page without signing in, cannot access login page if already signed in). Remember to add the new navigation bar and footer onto every page!

**Note:**

There are two html files for footers:

1. For a "**short**" page, which doesn't need "scrolling down", the position of footer is **<u>absolute/fixed</u>**, which means the footer will <u>always</u> display on the page. In this case, use ***footer.html***. You can refer to *index.html* and *login.html* for the insertion details.
2. For a "**long**" page, which needs "scrolling down", the position of footer is <u>**relative**</u>, which means the footer will display <u>only if</u> you reach the end of the page. In this case, use ***footer_long.html***. You can refer to *postList.html* for the insertion details.

### 3.20 Login Branch

Create a new branch "login", including the completed login function (frontend and backend), not including the web pages in version 1. Other files remain the same.

**Differences**
1. No html5, js, css in version 1. Only login-related files.
2. New LoginController and PageController.
3. New *pom.xml*, *application.properties* and *application-dev.properties*.
4. Only one SpringApplication class.

### 3.12 Main Branch

Update the project framework with registration function (no frontend)

## Useful Websites

[Git Tutorials](https://www.runoob.com/git/git-basic-operations.html)

[Git Video Tutorials](https://www.bilibili.com/video/BV1vy4y1s7k6)

[Bootstrap CSS Framework - Full Course for Beginners](https://www.youtube.com/watch?v=-qfEOE4vtxE)

[Bootstrap 5 & Material Design 2.0 UI KIT](https://github.com/mdbootstrap/mdb-ui-kit)

[Codepen -- Beautiful Code Templates for Frontend](https://codepen.io)

[HTML Color Picker](https://www.runoob.com/tags/html-colorpicker.html)

[SCSS to CSS Compiler](https://www.cssportal.com/scss-to-css/)

[SASS to CSS Compiler](https://www.sassmeister.com/)

[IDEA Server Deployment](https://blog.csdn.net/weixin_43711783/article/details/90218226?spm=1001.2101.3001.6661.1&utm_medium=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_antiscanv2&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-2%7Edefault%7ECTRLIST%7ERate-1.pc_relevant_antiscanv2&utm_relevant_index=1)

