package com.example.health2.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Réponse de l'API Google Places.
 */
data class GetNearbyPlacesData(

    @SerializedName("results")
    val results: List<NearbyPlace> = emptyList(),

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("error_message")
    val errorMessage: String? = null
)

/**
 * Informations sur un lieu à proximité.
 */
data class NearbyPlace(

    @SerializedName("place_id")
    val placeId: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("vicinity")
    val vicinity: String? = null,

    @SerializedName("rating")
    val rating: Float? = null,

    @SerializedName("geometry")
    val geometry: PlaceGeometry? = null,

    @SerializedName("photos")
    val photos: List<PlacePhoto> = emptyList(),

    @SerializedName("types")
    val types: List<String> = emptyList(),

    @SerializedName("opening_hours")
    val openingHours: PlaceOpeningHours? = null
)

data class PlaceGeometry(

    @SerializedName("location")
    val location: PlaceLocation? = null
)

data class PlaceLocation(

    @SerializedName("lat")
    val latitude: Double? = null,

    @SerializedName("lng")
    val longitude: Double? = null
)

data class PlacePhoto(

    @SerializedName("photo_reference")
    val photoReference: String? = null
)

data class PlaceOpeningHours(

    @SerializedName("open_now")
    val openNow: Boolean? = null
)
