# SmartGo
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/Sausure/SmartGo/blob/master/LICENSE.txt)
[![Bintray](https://img.shields.io/bintray/v/sausure/maven/SmartGo.svg?maxAge=2592000)](https://github.com/Sausure/SmartGo)

## Getting Start
In your `build.gradle`:
```groovy
dependencies {
    compile 'go.smart.woaiwhz.smartgo:SmartGo:0.9.1'
}
```

## Features
Now you can launch an explicit Activity like this:
```java
SmartGo.from(this)
        .to(ExplicitActivity.class)
        .shareElements()
        .like(v)
        .withSystemUI()
        .go();
```
or an implicit Activity like:
```java
SmartGo.from(this)
        .to("go.smart.woaiwhz.smartgoproject.implicit")//action
        .category("go.smart.woaiwhz.smartgoproject.have.fun")
        .requestCode(REQUEST_CODE)
        .animate(android.R.anim.fade_in, android.R.anim.fade_out)
        .go();
```
Also,you can launch a Service or a Broadcast easier than before
```java
//launch service
SmartGo.from(this)
        .run(BackgroundService.class)
        .bind(mServiceConntect, Service.BIND_AUTO_CREATE)
        .go();

//launch broadcast
SmartGo.from(this)
        .send("go.smart.woaiwhz.smartgoproject.broadcast")
        .extras()
        .with(REQUEST_STRING,"I'm a broadcast!")
        .then()
        .go();
```
> I will add more features by this funny way in the future and I wish you can tell me how I can do better!

##License

    Copyright 2016 Sausure.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
