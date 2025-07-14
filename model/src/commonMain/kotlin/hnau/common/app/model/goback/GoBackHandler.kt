package hnau.common.app.model.goback

import kotlinx.coroutines.flow.StateFlow

typealias GoBackHandler = StateFlow<(() -> Unit)?>
