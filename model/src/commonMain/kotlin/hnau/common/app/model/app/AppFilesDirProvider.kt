package hnau.common.app.model.app

import hnau.common.app.model.file.File

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class AppFilesDirProvider {

    fun getAppFilesDir(): File
}