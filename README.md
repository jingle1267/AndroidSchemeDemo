# H5唤起原生应用

点击浏览器中的URL链接，启动特定的APP

## 实现方式

### H5实现

##### 链接格式如下

```
<a href="[scheme]://[host]/[path]?[query]"> 唤起应用 </a> 
```

##### 各个项目含义如下所示：
 * scheme：唤起协议	※详细后述
 * host：  唤起指定host
 * path：  协议路径	※没有也可以
 * query： 一些参数	※没有也可以

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

  Activity中接受唤起协议的数据：

｀｀｀
Uri uri = getIntent().getData();
StringBuilder sb = new StringBuilder();
// 唤起链接
sb.append("string : ").append(getIntent().getDataString()).append("\n");
sb.append("scheme : ").append(uri.getScheme()).append("\n");
sb.append("host : ").append(uri.getHost()).append("\n");
sb.append("port : ").append(uri.getPort()).append("\n");
sb.append("path : ").append(uri.getPath()).append("\n");
// 接收唤起的参数
sb.append("name : ").append(uri.getQueryParameter("name")).append("\n");
sb.append("page : ").append(uri.getQueryParameter("page"));

tv_data.setText(sb.toString());
｀｀｀

  

[唤起源码](https://raw.githubusercontent.com/jingle1267/AndroidSchemeDemo/master/html/callback.html) 
[测试地址](https://rawgit.com/jingle1267/AndroidSchemeDemo/master/html/callback.html) 
[正式地址](https://cdn.rawgit.com/jingle1267/AndroidSchemeDemo/master/html/callback.html) 
