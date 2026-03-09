plugins {
    id("com.android.library")
}

android {
    namespace = "com.zauberfluff.core.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

}

dependencies {
    implementation(project(":core:domain"))
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    implementation("com.android.billingclient:billing-ktx:6.1.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("javax.inject:javax.inject:1")
}
