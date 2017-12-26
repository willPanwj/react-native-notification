import {
  NativeModules,
  Platform,
  PushNotificationIOS,
	NativeAppEventEmitter,
} from 'react-native'

const { RNPush } = NativeModules

const RNPushAndroid = {
  ...RNPush,
  addEventListener: function (event, listener) {
    return NativeAppEventEmitter.addListener(event, listener)
  }
}

export default RNPushAndroid