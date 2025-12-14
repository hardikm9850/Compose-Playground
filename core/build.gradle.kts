plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.hardik.core"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.material3)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.compose.icons.core)

    implementation(project(":feature-todo"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
  api("androidx.compose.animation:animation:1.10.0")
  implementation("androidx.compose.foundation:foundation:1.10.0")
  implementation("androidx.compose.ui:ui-text:1.10.0")
  implementation("androidx.compose.ui:ui-unit:1.10.0")
  api("androidx.navigation:navigation-common:2.9.6")
  api("androidx.navigation:navigation-runtime:2.9.6")
  androidTestImplementation("androidx.test:monitor:1.8.0")
  androidTestRuntimeOnly("androidx.test:runner:1.7.0")
  androidTestImplementation(libs.junit)
}