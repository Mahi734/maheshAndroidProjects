plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.myfoodorder"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myfoodorder"
        minSdk = 26
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
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

}


dependencies {



    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")




    // Room components
    implementation ("androidx.room:room-runtime:2.5.1") // Use the latest version available
   annotationProcessor ("androidx.room:room-compiler:2.5.1") // For Java projects

    // For Kotlin projects, use KAPT instead of annotationProcessor
    kapt ("androidx.room:room-compiler:2.5.1")

    // Optional - Room Kotlin Extensions and Coroutines support
    //implementation ("androidx.room:room-ktx:2.6.1")

    // Optional - Testing Room databases
 //   testImplementation ("androidx.room:room-testing:2.5.1")


    //glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.activity:activity:1.8.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")


    //corotines
    dependencies {
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    }

    // navigation component
    implementation ("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.3.5")

    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    //viewmodel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.1.0")

    //gif
    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.17")

    //sizes
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    implementation ("com.intuit.ssp:ssp-android:1.1.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}