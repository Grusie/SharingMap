package com.grusie.sharingmap.ui.main.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruise.domain.usecase.map.MapUseCases
import com.grusie.sharingmap.ui.model.AdditionalArchiveModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPlaceViewModel @Inject constructor(
    private val mapUseCases: MapUseCases
) : ViewModel() {
    private val _additionalArchiveModel = MutableStateFlow(
        AdditionalArchiveModel(
            latitude = null,
            longitude = null,
            address = "",
            placeName = "",
            content = ""
        )
    )
    val additionalArchiveModel: StateFlow<AdditionalArchiveModel> =
        _additionalArchiveModel.asStateFlow()

    fun getLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            mapUseCases.getAddressUseCase(latitude, longitude).onSuccess { address ->
                setAdditionalArchiveModel(
                    _additionalArchiveModel.value.copy(
                        latitude = latitude,
                        longitude = longitude,
                        address = address
                    )
                )
            }
        }
    }

    fun setAdditionalArchiveModel(additionalArchiveModel: AdditionalArchiveModel) {
        viewModelScope.launch {
            _additionalArchiveModel.emit(additionalArchiveModel)
        }
    }
}