# H5唤起原生应用

点击浏览器中的URL链接，启动特定的APP

## 实现方式

### H5实现

##### 链接格式如下

```
<a href="[scheme]://[host]/[path]?[query]"> 唤起应用 </a> 
```

##### 各个项目含义如下所示：
 * scheme：判别启动的App。 ※详细后述
 * host：  适当记述
 * path：  传值时必须的key     ※没有也可以
 * query： 获取值的Key和Value  ※没有也可以

### APP实现

##### AndroidManifest中添加配置

```java
<activity android:name=".SchemeActivity">
	<intent-filter>
		<action android:name="android.intent.action.VIEW" />
		<category android:name="android.intent.category.BROWSABLE" />
		<category android:name="android.intent.category.DEFAULT" />
		<data android:scheme="schemedemo" />
	</intent-filter>
</activity>
```

  data标签中匹配原则如下：
 * `android:scheme` : 唤起协议
 * `android:host` : 唤起host，只有置顶的host才可被唤起
 * `android:pathPrefix` : 唤起的路径，对路径进一步的过滤
 * 

[唤起源码](https://raw.githubusercontent.com/jingle1267/AndroidSchemeDemo/master/html/callback.html) 
[测试地址](https://rawgit.com/jingle1267/AndroidSchemeDemo/master/html/callback.html) 
[正式地址](https://cdn.rawgit.com/jingle1267/AndroidSchemeDemo/master/html/callback.html) 
