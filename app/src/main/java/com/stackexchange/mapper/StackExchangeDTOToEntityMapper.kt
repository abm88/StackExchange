package com.stackexchange.mapper

import com.stackexchange.base.Mapper
import com.stackexchange.data.model.StackExchangeDTO
import com.stackexchange.domain.model.StackExchangeUserEntity
import java.lang.Exception
import java.util.*

class StackExchangeDTOToEntityMapper : Mapper<StackExchangeDTO, List<StackExchangeUserEntity>> {
    override fun map(items: StackExchangeDTO) = arrayListOf<StackExchangeUserEntity>().apply {
        items.items.forEach {
            add(
                StackExchangeUserEntity(
                    it.profileImage ?: "",
                    it.userId.toString() ?: "",
                    it.displayName ?: "",
                    it.reputation.toString() ?: "",
                    getTags(it),
                    it.badgeCounts.gold,
                    it.location ?: "",
                    Date(it.creationDate * 1000).toString(),
                    link = it.link
                )
            )
        }
    }

    private fun getTags(item: StackExchangeDTO.Item): String {
        try {
            if (item.collectives.isNotEmpty()) {
                if (item.collectives[0].collective.tags.isNotEmpty()) {
                    return item.collectives[0].collective.tags.subList(
                        0,
                        if (item.collectives[0].collective.tags.size > 3) 2 else item.collectives[0].collective.tags.size
                    ).joinToString(" ")
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
        return ""
    }
}