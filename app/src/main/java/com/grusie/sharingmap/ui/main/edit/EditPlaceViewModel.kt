package com.grusie.sharingmap.ui.main.edit

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruise.domain.usecase.archive.ArchiveUseCase
import com.gruise.domain.usecase.map.MapUseCases
import com.grusie.sharingmap.ui.mapper.toDomainModel
import com.grusie.sharingmap.ui.model.AdditionalArchiveUiModel
import com.grusie.sharingmap.ui.model.AdditionalAttachUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditPlaceViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val mapUseCases: MapUseCases,
    private val archiveUseCase: ArchiveUseCase
) : ViewModel() {
    private val _additionalArchiveModel = MutableStateFlow(
        AdditionalArchiveUiModel(
            latitude = null,
            longitude = null,
            address = "",
            placeName = "",
            content = ""
        )
    )
    val additionalArchiveModel: StateFlow<AdditionalArchiveUiModel> =
        _additionalArchiveModel.asStateFlow()

    private val _attachList: MutableStateFlow<List<AdditionalAttachUiModel>> =
        MutableStateFlow(emptyList())
    val attachList: StateFlow<List<AdditionalAttachUiModel>> = _attachList


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

    fun setAdditionalArchiveModel(additionalArchiveModel: AdditionalArchiveUiModel) {
        viewModelScope.launch {
            _additionalArchiveModel.emit(additionalArchiveModel)
        }
    }

    fun setAttachList(attachList: List<AdditionalAttachUiModel>) {
        viewModelScope.launch {
            _attachList.emit(attachList)
        }
    }

    fun saveArchive() {
        viewModelScope.launch {

            val attachFileList = attachList.value.map {
                getFileFromUri(it.src.toUri())
            }

            archiveUseCase.saveArchiveUseCase(
                additionalArchiveModel = _additionalArchiveModel.value.toDomainModel(),
                attachFileList = attachFileList
            )
        }
    }

    fun getFileFromUri(uri: Uri): File? {
        // ContentResolver로 파일 읽기
        return try {
            val inputStream = context.contentResolver.openInputStream(uri)
            val file = File(context.cacheDir, "temp_file") // 임시 파일 생성
            file.outputStream().use { output ->
                inputStream?.copyTo(output)
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}