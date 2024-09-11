plugins {
    alias(libs.plugins.wim.android.library)
    alias(libs.plugins.wim.android.hilt)
    kotlin("plugin.serialization") version "2.0.20"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.serialization.json)
    implementation(libs.bundles.network)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}