plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    jacoco
}

android {
    namespace = "com.hardik.feature_todo"
    compileSdk = 36

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
        debug {
            enableUnitTestCoverage = true
        }
    }

    testOptions {
        unitTests.all {
            it.extensions.configure(JacocoTaskExtension::class.java) {
                isIncludeNoLocationClasses = true
                excludes = listOf("jdk.internal.*")
            }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.tooling.preview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.ui.tooling)
    api(libs.androidx.compose.foundation.layout)
    api("androidx.compose.foundation:foundation:1.10.0")
    implementation(libs.androidx.compose.ui.graphics)
    implementation("androidx.compose.ui:ui-text:1.10.0")
    implementation("androidx.compose.ui:ui-unit:1.10.0")
    implementation(libs.androidx.compose.ui)
    androidTestRuntimeOnly("androidx.loader:loader:1.0.0")
    androidTestRuntimeOnly("androidx.recyclerview:recyclerview:1.2.1")
    androidTestImplementation("androidx.test:monitor:1.8.0")
    androidTestRuntimeOnly("androidx.test:runner:1.7.0")
    androidTestImplementation(libs.junit)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
}