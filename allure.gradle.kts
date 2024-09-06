buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("io.qameta.allure.gradle.allure:allure-plugin:2.12.0")
    }
}

//apply(plugin = io.qameta.allure.gradle.allure.AllurePlugin)
apply<io.qameta.allure.gradle.allure.AllurePlugin>()

//apply plugin: io.qameta.allure.gradle.allure.AllurePlugin
