import org.gradle.api.Project

fun Project.getApiKey(): String {
    var apiKey = ""

    try {
        val apikeyPropertiesFile = rootProject.file("apikey.properties")
        val apikeyProperties = java.util.Properties().apply { load(apikeyPropertiesFile.inputStream()) }

        apiKey = apikeyProperties["NEWSAPI_KEY"] as String
    } catch (e: Exception) {
        error("API key not found.")
    }

    return apiKey
}
