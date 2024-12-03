package com.grusie.sharingmap.ui.main.edit

import android.content.Context
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruise.data.remote.RemoteError
import com.gruise.domain.usecase.archive.ArchiveUseCase
import com.gruise.domain.usecase.map.MapUseCases
import com.grusie.sharingmap.ui.mapper.toDomainModel
import com.grusie.sharingmap.ui.model.AdditionalArchiveUiModel
import com.grusie.sharingmap.ui.model.AdditionalAttachUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class EditPlaceViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val mapUseCases: MapUseCases,
    private val archiveUseCase: ArchiveUseCase
) : ViewModel() {
    private val _editPlaceUiState: MutableStateFlow<EditPlaceUiState> =
        MutableStateFlow(EditPlaceUiState())
    val editPlaceUiState: StateFlow<EditPlaceUiState> = _editPlaceUiState
    var toastJob: Job? = null


    fun getLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            mapUseCases.getAddressUseCase(latitude, longitude).onSuccess { address ->
                setAdditionalArchiveModel(
                    editPlaceUiState.value.additionalArchiveUiModel.copy(
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
            setEditPlaceUiState(
                _editPlaceUiState.value.copy(
                    additionalArchiveUiModel = additionalArchiveModel
                )
            )
        }
    }

    fun setAttachList(attachList: List<AdditionalAttachUiModel>) {
        viewModelScope.launch {
            setEditPlaceUiState(
                _editPlaceUiState.value.copy(
                    additionalAttachList = attachList
                )
            )
        }
    }

    fun showToast(toastMsg: String = "") {
        viewModelScope.launch {
            toastJob?.cancel()

            toastJob = viewModelScope.launch {
                setEditPlaceUiState(
                    _editPlaceUiState.value.copy(
                        isToastShow = true,
                        toastMsg = toastMsg.ifBlank { _editPlaceUiState.value.toastMsg }
                    )
                )
                delay(1500)
                setEditPlaceUiState(_editPlaceUiState.value.copy(isToastShow = false))
            }
        }
    }

    private fun setEditPlaceUiState(editPlaceUiState: EditPlaceUiState) {
        viewModelScope.launch {
            _editPlaceUiState.emit(editPlaceUiState)
        }
    }

    fun saveArchive() {
        viewModelScope.launch {
            setEditPlaceUiState(
                _editPlaceUiState.value.copy(isLoading = true)
            )
            val attachFileList = _editPlaceUiState.value.additionalAttachList.map {
                getFileFromUri(it.src.toUri())
            }

            archiveUseCase.saveArchiveUseCase(
                additionalArchiveModel = _editPlaceUiState.value.additionalArchiveUiModel.toDomainModel(),
                attachFileList = attachFileList
            ).onSuccess {
                setEditPlaceUiState(
                    _editPlaceUiState.value.copy(isLoading = false)
                )
            }.onFailure {
                setEditPlaceUiState(
                    _editPlaceUiState.value.copy(
                        isLoading = false,
                    )
                )
                showToast(toastMsg = (it as RemoteError).toStringForUser())
            }
        }
    }

    private fun getFileFromUri(uri: Uri): File? {
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