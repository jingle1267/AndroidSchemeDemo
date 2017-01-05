# H5唤起原生应用

点击浏览器中的URL链接，启动特定的APP

## 实现方式

### H5实现

#### 链接格式如下

```
<a href="[scheme]://[host]/[path]?[query]"> 唤起应用 </a> 
```

#### 各个项目含义如下所示：

 * scheme：唤起协议	※详细后述
 * host：  唤起指定host
 * path：  协议路径	※没有也可以
 * query： 一些参数	※没有也可以

#### 终端未安装APP
  
  终端如果已经安装我们的应用，这个时候可以直接唤起原生应用；终端如果没有安装我们的应用，这时我们需要引导用户去安装。

#### JavaScript实现源码

```javascript
<a id="call-app" href="javascript:;" > Start or Download </a><br/><br/>
<input id="download-app" type="hidden" name="storeurl" value="http://jd.com/8JZ5OO">
	
<script>
    (function(){
        var ua = navigator.userAgent.toLowerCase();
        var t;
        var config = {
            /*scheme:必须*/
            scheme_IOS: 'schemedemo://',
            scheme_Adr: 'schemedemo://ihongqiqu.com/test/scheme?name=google&page=1',
            download_url: document.getElementById('download-app').value,
            timeout: 600
        };
 
        function openclient() {
            var startTime = Date.now();
 
            var ifr = document.createElement('iframe');

            ifr.src = ua.indexOf('os') > 0 ? config.scheme_IOS : config.scheme_Adr;
            ifr.style.display = 'none';
            document.body.appendChild(ifr);
 
            var t = setTimeout(function() {
                var endTime = Date.now();
 
                if (!startTime || endTime - startTime < config.timeout + 200) { 
                    window.location = config.download_url;
                } else {
                             
                }
            }, config.timeout);

            window.onblur = function() {
                clearTimeout(t);
            }
        }
        window.addEventListener("DOMContentLoaded", function(){
            document.getElementById("call-app").addEventListener('click', 
            	openclient, false);
        }, false);
    })()
</script>
```

### APP实现

#### AndroidManifest中添加配置

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

#### DATA标签中匹配原则如下：

 * `android:scheme` : 唤起协议
 * `android:host` : 唤起host，只有置顶的host才可被唤起
 * `android:pathPrefix` : 唤起的路径，对路径进一步的过滤

#### Activity中接受唤起协议的数据：

```java
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
```

### 测试资源

[唤起源码](https://raw.githubusercontent.com/jingle1267/AndroidSchemeDemo/master/html/callback.html) <br/>
[测试地址](https://rawgit.com/jingle1267/AndroidSchemeDemo/master/html/callback.html) <br/>
[正式地址](https://cdn.rawgit.com/jingle1267/AndroidSchemeDemo/master/html/callback.html) <br/>

### 其它

[Android Webview模块](https://github.com/jingle1267/WebActivity)

### 开发者

* [Zhenguo Jin](http://ihongqiqu.com) - <jingle1267@163.com>


### License

    Copyright 2015 Zhenguo Jin

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
