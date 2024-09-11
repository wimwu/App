plugins {
    id("kotlin-kapt")
    alias(libs.plugins.ksp)
    alias(libs.plugins.wim.android.application)
    alias(libs.plugins.wim.android.hilt)
}

android {
    namespace = "com.wim.app"

    defaultConfig {
        applicationId = "com.wim.app"
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
    }

}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.bundles.navigation)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}