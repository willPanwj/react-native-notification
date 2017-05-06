
package me.youchai.rnpush;

import android.content.Context;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import me.youchai.rnpush.utils.Logger;

public class RNPushModule extends ReactContextBaseJavaModule {

  private static String TAG = "RNPushModule";
  private PushService pushService;

  public RNPushModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.pushService = PushServiceFactory.create(reactContext);
  }

  @Override
  public boolean canOverrideExistingModule() {
    return true;
  }

  @Override
  public String getName() {
    return "RNPush";
  }

  public static void sendEvent(String key, WritableMap event) {
    // send event to js
  }

  @ReactMethod
  public void initPush() {
    pushService.init();
    Logger.i(TAG, "init Success!");
  }

  @ReactMethod
  public void stopPush() {
    pushService.stop();
    Logger.i(TAG, "Stop push");
  }

  @ReactMethod
  public void resumePush() {
    pushService.resume();
    Logger.i(TAG, "Resume push");
  }

  /**
   * Get registration id, different from RNPushModule.addGetRegistrationIdListener, this
   * method has no calling limits.
   *
   * @param callback callback with registrationId
   */
  @ReactMethod
  public void getRegistrationID(Promise promise) {
    try {
      String id = pushService.getRegistrationID();
      promise.resolve(id);
    } catch (Exception e) {
      e.printStackTrace();
      promise.reject(e.getMessage());
    }
  }

  /**
   * Clear all notifications, suggest invoke this method while exiting app.
   */
  @ReactMethod
  public void clearAllNotifications(Promise promise) {
    try {
      pushService.clearAllNotification();
      promise.resolve(true);
    } catch (Exception e) {
      e.printStackTrace();
      promise.reject(e.getMessage());
    }
  }

  /**
   * Clear specified notification
   *
   * @param id the notification id
   */
  // @ReactMethod
  public void clearNotificationById(String id, Promise promise) {
    try {
      pushService.clearNotificationById(id);
      promise.resolve(true);
    } catch (Exception e) {
      e.printStackTrace();
      promise.reject(e.getMessage());
    }
  }

}
