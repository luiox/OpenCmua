pluginManagement {
	repositories {
		maven {
			url = uri("https://maven.aliyun.com/repository/gradle-plugin")
		}
		maven {
			name = "Fabric"
			url = uri("https://maven.fabricmc.net/")
		}
		mavenCentral()
		gradlePluginPortal()
	}
}
