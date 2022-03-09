package com.codingtok.hmovies

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import kotlin.properties.Delegates

/**
 * Create by Do Huy Hoang
 */
@HiltAndroidApp
class App: Application() {
    companion object {
        private val TAG = "App"

        var context: Context by Delegates.notNull()
        private set
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        initConfig()

        if (enableLogging()) {
            Timber.plant(Timber.DebugTree())
        }

        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }

    /**
     * Cấu hình ban đầu
     */
    private fun initConfig() {
    }

    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Timber.tag(TAG).d("onCreated: %s", activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Timber.tag(TAG).d("onStart: %s", activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            Timber.tag(TAG).d("onDestroy: %s", activity.componentName.className)
        }
    }
}

fun enableLogging() = BuildConfig.BUILD_TYPE != "release"