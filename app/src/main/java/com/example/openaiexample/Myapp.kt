package com.example.openaiexample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class Myapp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}