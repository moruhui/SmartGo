# SmartGo

Now you can launch an explicit Activity like this:
```java
SmartGo.from(this)
        .to(ExplicitActivity.class)
        .shareElements()
        .like(v)
        .withSystemUI()
        .go();
```
or an implicit Activity like this:
```java
SmartGo.from(this)
        .to("go.smart.woaiwhz.smartgoproject.implicit")//action
        .category("go.smart.woaiwhz.smartgoproject.have.fun")
        .requestCode(REQUEST_CODE)
        .animate(android.R.anim.fade_in, android.R.anim.fade_out)
        .go();
```
Also,you can launch a Service or a broadcast easier than before
```java
\\launch service
SmartGo.from(this)
        .run(BackgroundService.class)
        .bind(mServiceConntect, Service.BIND_AUTO_CREATE)
        .go();

\\launch broadcast
SmartGo.from(this)
        .send("go.smart.woaiwhz.smartgoproject.broadcast")
        .extras()
        .with(REQUEST_STRING,"I'm a broadcast!")
        .then()
        .go();
```

