# 简介
这个项目收集了一些简单的自定义View，这些View都非常基础，并没有很多复杂的逻辑，适合学习自定义View的新手拿来练手，熟悉canvas对象的一些API操作。

# 每个View简介
## TowSideProgressBar
这是一个向两边滑动的水平进度条，与系统自带的水平进度条从左向右滑动相比，该View是在控件中间处，分别向左右两边滑动的。

## TemperatureProgress
这是一个圆形的进度条，用来模拟一些天气类App显示温度用的圆形View，当你设置好温度的范围之后，会有一小段动画来显示温度，所以才叫做进度条。

![圆形温度进度条](https://raw.githubusercontent.com/JaffarOu/SimpleCustomView/master/PictureInReadMe/temperatureProgress.jpg)

## MergePictureView
这是一个仿微信朋友圈合并图片效果的View，如下图所示，第一张图是我做的效果（这是四个MergePictureView，不是一个），第二张是朋友圈的截图

![图片合并View](https://raw.githubusercontent.com/JaffarOu/SimpleCustomView/master/PictureInReadMe/mergePicture.jpg)
![朋友圈截图](https://raw.githubusercontent.com/JaffarOu/SimpleCustomView/master/PictureInReadMe/pengyouquan.png)

## CLockView
这是一个时钟盘，它可以是静止的也可以是动态的，对外提供方法控制启动/暂停，与很多时钟盘不同的是，这个只有时针分针，“秒”变动效果通过弧线来展示，如下图示静态效果。

![时钟盘](https://raw.githubusercontent.com/JaffarOu/SimpleCustomView/master/PictureInReadMe/clockView.jpg)
