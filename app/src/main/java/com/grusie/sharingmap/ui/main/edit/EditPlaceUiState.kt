package com.grusie.sharingmap.ui.main.edit

import com.grusie.sharingmap.ui.model.AdditionalArchiveUiModel
import com.grusie.sharingmap.ui.model.AdditionalAttachUiModel

data class EditPlaceUiState(
    val isLoading: Boolean = false,
    val additionalAttachList: List<AdditionalAttachUiModel> = emptyList(),
    val additionalArchiveUiModel: AdditionalArchiveUiModel = AdditionalArchiveUiModel(),
    val isToastShow: Boolean = false,
    val toastMsg: String = ""
)