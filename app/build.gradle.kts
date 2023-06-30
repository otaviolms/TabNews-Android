plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
//    id("com.google.devtools.ksp")
}

android {
    namespace = "br.com.otaviolms.tabnews"
    compileSdk = 33

    defaultConfig {
        applicationId = "br.com.otaviolms.tabnews"
        minSdk = 30
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://www.tabnews.com.br/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }

//    applicationVariants.all { variant ->
//        variant.sourceSets.forEach {
//            it.kotlinDirectories += "build/generated/ksp/${variant.name}/kotlin"
//        }
//    }
}

dependencies {

    implementation("androidx.fragment:fragment-ktx:1.5.7")

    val nav_version = "2.6.0"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    val lifecycle_version = "2.6.1"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation("com.squareup.retrofit2:converter-jackson:$retrofit_version")

    val jackson_version = "2.14.1"
    implementation("com.fasterxml.jackson.core:jackson-databind:$jackson_version")
    implementation("com.fasterxml.jackson.core:jackson-core:$jackson_version")
    implementation("com.fasterxml.jackson.core:jackson-annotations:$jackson_version")

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    implementation("androidx.recyclerview:recyclerview:1.3.0")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    val markwon_version = "4.6.2"
    implementation("io.noties.markwon:core:$markwon_version")
    implementation("io.noties.markwon:image-glide:$markwon_version")
    implementation("io.noties.markwon:linkify:$markwon_version")
    implementation("io.noties.markwon:ext-tasklist:$markwon_version")
    implementation("io.noties.markwon:ext-tables:$markwon_version")
//    implementation("io.noties.markwon:syntax-highlight:$markwon_version")

//    val prism_version = "2.0.0"
//    implementation("io.noties:prism4j:$prism_version")
//    annotationProcessor("io.noties:prism4j-bundler:$prism_version")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.0")

    val koin_version = "3.4.2"
    implementation("io.insert-koin:koin-android:$koin_version")
//    implementation("io.insert-koin:koin-annotations:$koin_version")
//    ksp("io.insert-koin:koin-ksp-compiler:$koin_version")

    implementation("nl.dionsegijn:konfetti-xml:2.0.2")

//    TODO: Remover dependência
    implementation("com.github.javafaker:javafaker:1.0.2")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}