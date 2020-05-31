

[ ![Download](https://api.bintray.com/packages/vectorzeng/maven/android-assert/images/download.svg?version=0.0.1) ](https://bintray.com/vectorzeng/maven/android-assert/0.0.1/link)

## 简介

>  android-assert是一个非常简单轻量的android断言库。类似于junit的Assert类。
>
> android-assert不是用来写测试用例的，我们可以直接在项目代码中使用他。
>
> 在debug模式下，断言失败将会抛出断言异常 AssertionFailedError，在release模式下，将不会抛出异常。



## 一、什么是断言，什么情况下应该使用androidAssert？

通常断言是用在单元测试，用来校验函数返回的结果的。

但是我们接下来要讨论的并不是单元测试中使用断言，而是在项目业务代码中使用断言。

我们一起来看几个，大家非常熟悉的栗子。这种情况下使用断言会比较合适。



### 例子1，writeFile

```java
/**
 * 我们希望只在子线程中调用writeFile()，主线程中调用可能会导致ui卡顿或者anr。
 *
 * 如果在主线程调用writeFile()，我们打印日志警告开发者
 */
public void writeFile() {
    //主线程，打印日志警告开发者
    if(!ThreadTypeUtil.isSubThread()){
        Log.e(TG, "you should call writeFile at sub thread");
    }
    // write file ...
}
```

当出现有开发人员，尝试在主线程中调用writeFile()方法时，我们通过 **日志警告** ，开发者。但是，日志提示很弱，往往会被开发者忽略掉。

**于是我们改良了下代码：**

```java
/**
 * 我们希望只在子线程中调用writeFile()，主线程中调用可能会导致ui卡顿或者anr。
 *
 * 如果在主线程调用writeFile()，我们打印日志警告开发者
 */
public void writeFile() {
    //debug版本，主线程中调用 writeFile ，直接抛出异常中断程序运行
    if(!ThreadTypeUtil.isSubThread()){
        if(BuildConfig.DEBUG) {
            throw new RuntimeException("you should call writeFile at sub thread");
        }
    }
    // write file ...
}
```

当程序员企图在主线程中调用writeFile()，在debug模式下，我们直接抛出异常，让程序崩溃。以中断他的开发。强制他优化代码。

我们引入断言，**继续改造代码，让代码更简洁漂亮：**

```java
/**
 * 在debug模式，下将直接抛出异常 AssertionFailedError。让开发者
 * 在release模式，不会抛出异常，会正常执行writeFile()函数。
 */
public void writeFile() {
    AndroidAssert.assertSubThread();
    // write file ...
    Log.i(TG, "writeFile...");
}
```

AndroidAssert.assertSubThread()断言为子线程的意思是，断定当前线程一定是子线程，如果不是，那么抛出异常 AssertionFailedError。



**我们继续改良**

只有在debug版本，AndroidAssert 类，才有用；

在release版本的apk上，**能否把 AndroidAssert 相关调用的代码删除？**

或者说打包的时候，把 AndroidAssert 相关的调用的代码 和 AndroidAssert类的代码 全部删除，再打包。

于是我们想到了proguard。

在proguard中添加如下配置即可：

```pro
# -dontoptimize ## 注意注意注意，proguard中配置dontoptimize；将会导致proguard不做代码优化，不会删除AndroidAssert类
-assumenosideeffects class com.it.uncle.lib.util.AndroidAssert
```







### 例子2，updateUI

```java
/**
 * 在release版本，如果在子线程中调用updateUI，我们直接return，不做ui更新操作。
 * 但是在debug版本，如果在子线程中调用updateUI，直接出异常，让开发者发现异常调用并解决。
 */
public void updateUI() {
    boolean isMainThread = ThreadTypeUtil.isMainThread();
    if (!isMainThread) {
        AndroidAssert.fail("updateUI must be called at main thread");
        return;
    }
    //update ui ....
    Log.i(TG, "updateUI...");
}
```



### 例子3，startMainActivity

```java
/**
 * 断言context为非空，如果为null，debug模式下抛出异常 AssertionFailedError
 */
public void startMainActivity(Context context) {
    AndroidAssert.assertNotNull("context must not null", context);
    if (context == null) {
        return;
    }
    //startMainActivity...
    Log.i(TG, "startMainActivity...");
}
```



## 二、集成AndroidAssert库

- gradle引入

  ```gradle
  implementation 'com.ituncle:android-assert:0.0.1'
  ```

- 初始化sdk

```java
//初始化----断言失败时，是否抛出异常
AndroidAssert.enableThrowError(BuildConfig.DEBUG);//我们设置为debug模式下，断言失败才抛出异常
```

- 添加proguard，在开启混淆的版本中，移除AndroidAssert的代码

  ```pro
  # -dontoptimize ## 注意注意注意，proguard中配置dontoptimize；将会导致proguard不做代码优化，不会删除AndroidAssert类
  -assumenosideeffects class com.it.uncle.lib.util.AndroidAssert
  ```

  

