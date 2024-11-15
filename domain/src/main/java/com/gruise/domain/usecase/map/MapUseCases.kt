package com.gruise.domain.usecase.map

data class MapUseCases(
    val getAddressUseCase: GetAddressUseCase,
    val getSearchRegionListUseCase: GetSearchRegionListUseCase,
    val saveSearchRegionHistory: SaveSearchRegionHistoryUseCase,
    val clearSearchRegionHistory: ClearSearchRegionHistoryUseCase,
    val getSearchRegionHistory: GetSearchRegionHistoryUseCase
)