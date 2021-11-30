package fr.mbds.tdaumas.idbdata.api.response

import com.google.gson.annotations.SerializedName
import fr.mbds.tdaumas.idbdata.data.Category

internal data class CategoryResponse(
    @SerializedName("genres")
    val genres: List<Genre>
) {
    data class Genre(
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String
    ) {
        fun toCategory(): Category {
            return Category(id, name)
        }
    }
}