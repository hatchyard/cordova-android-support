# cordova-android-support

This is a android support patch plugin for **android** to get android support for cordova android latest applications after API 32+.
You can use following line to call the plugin
```
cordova.AndroidSupport.patch([], onSuccessCallback, onErrorCallback)
```

You can access the callbacks like following code.
```
function onSuccessCallback(response) {
    console.log("This is successful callback", response);
}

function onErrorCallback(response) {
    console.log("This is failure callback", response);
}
```
