// This file is needed because gradle on every run searches for a settings.gradle or settings.gradle.kts file
// If this file weren't here, gradle would search the entire hierarchy every time which would slow it down
rootProject.buildFileName = "build.gradle.kts"
