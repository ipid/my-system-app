package me.ipid.android.mysystemapp

import android.app.ActivityManager
import android.app.ActivityThread
import android.app.IActivityManager
import android.content.pm.IPackageManager

object Daemon {
    lateinit var actMan: IActivityManager
    lateinit var pacMan: IPackageManager

    @JvmStatic
    fun main(args: Array<String>) {
        actMan = ActivityManager.getService()
        pacMan = ActivityThread.getPackageManager()

        println("Alive!")
        println("actMan: $actMan")
        println("pacMan: $pacMan")
    }
}

