package com.stackexchange.domain.model


import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import com.stackexchange.base.BaseDataModel

@Entity(tableName = "users", primaryKeys = ["userId"])
data class StackExchangeUserEntity(
    val avatar: String,
    val userId: String,
    val userName: String,
    val reputation: String,
    val topTags: String,
    val Badges: Int,
    val location: String,
    val createDate: String,
    val link: String
) : BaseDataModel, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(avatar)
        parcel.writeString(userId)
        parcel.writeString(userName)
        parcel.writeString(reputation)
        parcel.writeString(topTags)
        parcel.writeInt(Badges)
        parcel.writeString(location)
        parcel.writeString(createDate)
        parcel.writeString(link)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StackExchangeUserEntity> {
        override fun createFromParcel(parcel: Parcel): StackExchangeUserEntity {
            return StackExchangeUserEntity(parcel)
        }

        override fun newArray(size: Int): Array<StackExchangeUserEntity?> {
            return arrayOfNulls(size)
        }
    }
}