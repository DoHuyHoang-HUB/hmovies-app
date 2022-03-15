package com.codingtok.hmovies.data.internals.models

import com.codingtok.hmovies.data.internals.annotations.OtherCases
import com.codingtok.hmovies.data.model.WatchProviderListObject
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

@JsonClass(generateAdapter = true)
internal data class WatchProviderListObj(
    val results: Map<String, WatchProviderListObject>
) {
    companion object {
        internal val ADAPTER = object : Any() {
            @OtherCases
            @FromJson
            fun from(obj: WatchProviderListObj): Map<String, WatchProviderListObject> {
                return obj.results
            }

            @ToJson
            fun to(@OtherCases map: Map<String, WatchProviderListObject>): WatchProviderListObj {
                throw NotImplementedError("Not implemented")
            }
        }
    }
}