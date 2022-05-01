# SonicHollowForum

[![img](https://camo.githubusercontent.com/de3012ca40ba3ef433da8807996576d3cafaccf638909e83b1048b1d8aec3b77/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f537072696e675f426f6f742d4632463446393f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e672d626f6f74)](https://camo.githubusercontent.com/de3012ca40ba3ef433da8807996576d3cafaccf638909e83b1048b1d8aec3b77/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f537072696e675f426f6f742d4632463446393f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e672d626f6f74)[![img](https://camo.githubusercontent.com/506742bb72188756810aa73bf2b2032849399620bf5af3816b68090f24d81a7c/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6170616368655f6d6176656e2d4337314133363f7374796c653d666f722d7468652d6261646765266c6f676f3d6170616368656d6176656e266c6f676f436f6c6f723d7768697465)](https://camo.githubusercontent.com/506742bb72188756810aa73bf2b2032849399620bf5af3816b68090f24d81a7c/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f6170616368655f6d6176656e2d4337314133363f7374796c653d666f722d7468652d6261646765266c6f676f3d6170616368656d6176656e266c6f676f436f6c6f723d7768697465)[![mysql](https://camo.githubusercontent.com/a4a4a017a5d519d7c4ce2a3cd3d2194fb7af4b1ca424850784565007c2acc7d8/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4d7953514c2d3030354338343f7374796c653d666f722d7468652d6261646765266c6f676f3d6d7973716c266c6f676f436f6c6f723d7768697465)](https://camo.githubusercontent.com/a4a4a017a5d519d7c4ce2a3cd3d2194fb7af4b1ca424850784565007c2acc7d8/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4d7953514c2d3030354338343f7374796c653d666f722d7468652d6261646765266c6f676f3d6d7973716c266c6f676f436f6c6f723d7768697465)[![img](https://camo.githubusercontent.com/16c5d674d150e47e77738a333e74716023295715c956aaf84615cef3f50675ed/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f72656469732d2532334444303033312e7376673f267374796c653d666f722d7468652d6261646765266c6f676f3d7265646973266c6f676f436f6c6f723d7768697465)](https://camo.githubusercontent.com/16c5d674d150e47e77738a333e74716023295715c956aaf84615cef3f50675ed/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f72656469732d2532334444303033312e7376673f267374796c653d666f722d7468652d6261646765266c6f676f3d7265646973266c6f676f436f6c6f723d7768697465)![jQuery](https://img.shields.io/badge/jquery-%230769AD.svg?style=for-the-badge&logo=jquery&logoColor=white)![Bootstrap](https://img.shields.io/badge/bootstrap-%23563D7C.svg?style=for-the-badge&logo=bootstrap&logoColor=white)

This is the official repository for *CPT202 Software Engineering Group Project* Group B-1.

The project is now available at [101.132.122.132:8080](http://101.132.122.132:8080). Hope you enjoy :)

## Guideline

To start the project locally, install [git](https://git-scm.com/book/zh/v2/%E8%B5%B7%E6%AD%A5-%E5%AE%89%E8%A3%85-Git) on your PC. 

To clone this repository on your PC, run the command in git:

`git clone git@github.com:SonicHollow/SonicHollowForum.git`

1. Open this project in your IDE (IDEA is recommended) 

   File > New > Project from Existing Sources

2. Configure your local database referring to the sql statements under the *src* foler.

   Create two tables `User` and `Post` and update the database information in *application-dev.properties*.

3. Run the file *SonicHollowForum.java*

Notes: 

1. To run the project (like function related parts) locally, please install Redis on your PC. 
2. To solve the possible maven dependency problem when importing the project, you can update your settings.xml ([Where is it?](https://www.cnblogs.com/Small-sunshine/p/11640576.html)). 

## Package

We also have prepared maven package `forum-0.0.1-SNAPSHOT.war` under the *target* folder for your reference. 

Run the command:

`cd target`

`java -jar forum-0.0.1-SNAPSHOT.war`

For continuous testing, run the command as follows:

1. Use the following command to find the running process (if exists):

   `ps -aux | grep "java -jar forum-0.0.1-SNAPSHOT.war"`

   or use this command:

   `ss -lntpd | grep :8080 (Port for SpringBoot)`

2. End the process with the kill command:

   `kill PID`

3. Run the project continuously:

   `nohup java -jar forum-0.0.1-SNAPSHOT.war & `

## Environment


Springboot 2.6.3 RELEASE

Front-end: Thymeleaf + JQuery + Bootstrap

Database: MySQL + Redis

Back-end: Springboot + Mybatis

## Project Structure

The project structure is as follows:

```
├─.idea
│  └─inspectionProfiles
├─.mvn
│  └─wrapper
└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─sonichollow
    │  │          └─forum
    │  │              ├─controller
    │  │              ├─dto
    │  │              ├─entity
    │  │              ├─filter
    │  │              ├─mapper
    │  │              ├─service
    │  │              │  ├─ex
    │  │              │  └─impl
    │  │              └─util
    │  └─resources
    │      ├─mapper
    │      ├─static
    │      │  ├─css
    │      │  │  ├─backstage
    │      │  │  │  └─datatables
    │      │  │  ├─common
    │      │  │  └─user
    │      │  ├─fonts
    │      │  ├─images
    │      │  └─js
    │      │      ├─common
    │      │      ├─datatables
    │      │      ├─demo
    │      │      └─user
    │      └─templates
    │          ├─common
    │          ├─error
    │          ├─user
    │          └─util
    └─test
        └─java
            └─com
                └─sonichollow
                    └─forum
                        ├─mapper
                        └─service

```




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

