plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.travel_app_srilanka"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.travel_app_srilanka"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        viewBinding { true }
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    implementation ("com.google.android.gms:play-services-location:18.0.0")
    implementation ("com.google.android.gms:play-services-auth:19.2.0")
    implementation ("com.facebook.android:facebook-login:[8,9)")


}