package com.musiceye.audio

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

/**
 * Package provider for MusicEyeAudioModule.
 *
 * Register in MainApplication.java:
 *   @Override
 *   protected List<ReactPackage> getPackages() {
 *     return Arrays.<ReactPackage>asList(
 *       new MainReactPackage(),
 *       new MusicEyeAudioPackage(),  // <-- Add this
 *       ...
 *     );
 *   }
 */
class MusicEyeAudioPackage : ReactPackage {
    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        return listOf(MusicEyeAudioModule(reactContext))
    }

    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return emptyList()
    }
}
