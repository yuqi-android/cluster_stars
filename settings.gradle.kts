pluginManagement {
    repositories {
        maven ("https://maven.aliyun.com/nexus/content/groups/public/")
        maven ("https://maven.aliyun.com/repository/google")
        maven ("https://maven.aliyun.com/repository/public")
        maven ("https://maven.aliyun.com/repository/central")
        maven ("https://jitpack.io")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven ("https://maven.aliyun.com/nexus/content/groups/public/")
        maven ("https://maven.aliyun.com/repository/google")
        maven ("https://maven.aliyun.com/repository/public")
        maven ("https://maven.aliyun.com/repository/central")
        maven ("https://jitpack.io")
        google()
        mavenCentral()
    }
}

rootProject.name = "cluster_stars"
include(":app")
include(":libraryBase")
