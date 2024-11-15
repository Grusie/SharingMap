package com.gruise.data.model

import androidx.datastore.preferences.core.stringSetPreferencesKey

object DataStoreKeys {
    object SearchRegionHistory {
        val SEARCH_REGION_LIST = stringSetPreferencesKey("searchRegionList")
    }
}