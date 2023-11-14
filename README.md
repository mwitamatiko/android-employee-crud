# android-employee-crud

# dependencies 
  1. implementation 'androidx.compose.material:material-icons-core:1.5.4'
  2. implementation 'androidx.compose.material:material-icons-extended:1.5.4'
  3. implementation 'androidx.navigation:navigation-compose:2.7.5'
  4. implementation("com.squareup.retrofit2:retrofit:2.9.0")
  5. implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  6. implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
  7. implementation 'com.google.code.gson:gson:2.8.9'

# Upgrade build.gradle(project)
  id 'org.jetbrains.kotlin.android' version '1.8.10' apply false 
    use 1.8.10
# Upgrade buid.gradle(app)
  1. compileSdk 34 and targetSdk 34
  2. composeOptions {kotlinCompilerExtensionVersion '1.4.2'}

# Manifest file 
  <uses-permission android:name="android.permission.INTERNET"/>
  
