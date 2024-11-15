package com.gruise.data.model

import com.google.gson.annotations.SerializedName

data class SearchRegionsDto(
    @SerializedName("documents") val documents: List<SearchRegionDto>
)