// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0-beta04" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false

//    val ksp_version = "1.8.21-1.0.11"
//    id("com.google.devtools.ksp") version "$ksp_version"
}

buildscript {
    dependencies {
        val nav_version = "2.6.0"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
    }
}