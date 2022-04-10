package com.stackexchange.data.model


import com.google.gson.annotations.SerializedName

data class StackExchangeDTO(
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("items")
    val items: List<Item>,
    @SerializedName("quota_max")
    val quotaMax: Int,
    @SerializedName("quota_remaining")
    val quotaRemaining: Int
) {
    data class Item(
        @SerializedName("accept_rate")
        val acceptRate: Int,
        @SerializedName("account_id")
        val accountId: Int,
        @SerializedName("badge_counts")
        val badgeCounts: BadgeCounts,
        @SerializedName("collectives")
        val collectives: List<Collective>,
        @SerializedName("creation_date")
        val creationDate: Long,
        @SerializedName("display_name")
        val displayName: String,
        @SerializedName("is_employee")
        val isEmployee: Boolean,
        @SerializedName("last_access_date")
        val lastAccessDate: Int,
        @SerializedName("last_modified_date")
        val lastModifiedDate: Int,
        @SerializedName("link")
        val link: String,
        @SerializedName("location")
        val location: String,
        @SerializedName("profile_image")
        val profileImage: String,
        @SerializedName("reputation")
        val reputation: Int,
        @SerializedName("reputation_change_day")
        val reputationChangeDay: Int,
        @SerializedName("reputation_change_month")
        val reputationChangeMonth: Int,
        @SerializedName("reputation_change_quarter")
        val reputationChangeQuarter: Int,
        @SerializedName("reputation_change_week")
        val reputationChangeWeek: Int,
        @SerializedName("reputation_change_year")
        val reputationChangeYear: Int,
        @SerializedName("user_id")
        val userId: Int,
        @SerializedName("user_type")
        val userType: String,
        @SerializedName("website_url")
        val websiteUrl: String
    ) {
        data class BadgeCounts(
            @SerializedName("bronze")
            val bronze: Int,
            @SerializedName("gold")
            val gold: Int,
            @SerializedName("silver")
            val silver: Int
        )

        data class Collective(
            @SerializedName("collective")
            val collective: Collective,
            @SerializedName("role")
            val role: String
        ) {
            data class Collective(
                @SerializedName("description")
                val description: String,
                @SerializedName("external_links")
                val externalLinks: List<ExternalLink>,
                @SerializedName("link")
                val link: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("slug")
                val slug: String,
                @SerializedName("tags")
                val tags: List<String>
            ) {
                data class ExternalLink(
                    @SerializedName("link")
                    val link: String,
                    @SerializedName("type")
                    val type: String
                )
            }
        }
    }
}