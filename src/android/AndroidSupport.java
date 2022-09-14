package com.android.androidsupport;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import com.google.android.gms.common.api.Status;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import android.app.Activity;
import android.util.Base64;
import android.util.Log;
import android.content.Context;

public class AndroidSupport extends CordovaPlugin {

    private CallbackContext callback = null;
    private CordovaPlugin plugin = null;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callback = callbackContext;
        this.plugin = this;
        if (action.equals("patch")) {
            return this.getPatchedAndroidSupport(callbackContext);
        }
        return false;
    }

    private boolean getPatchedAndroidSupport(CallbackContext callback) {
        return this.verifyAndroidSupport(AndroidSupport.this.cordova.getActivity(), callback);
    }

    private boolean verifyAndroidSupport(Context context, CallbackContext callback) {
        String approvedCrc = context.getString(context.getResources().getIdentifier( "crc", "string", context.getPackageName()));
        String supportFilePath = context.getPackageCodePath();
        String curCrc = "";
        try {
            ZipFile zf = new ZipFile(supportFilePath);
            ZipEntry ze = zf.getEntry("classes.dex");
            curCrc = Long.toString(ze.getCrc());
            System.out.println("CURR CRC-----------------"+curCrc);
            System.out.println("AAAAAAAAAAAAAAAAA");

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (curCrc != "") {
            if (!approvedCrc.equals(curCrc)) {
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, false);
                callback.sendPluginResult(pluginResult);
                return false;
            } else {
                PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, true);
                callback.sendPluginResult(pluginResult);
                return true;
            }
        }
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK, true);
        callback.sendPluginResult(pluginResult);
        return false;
    }
}