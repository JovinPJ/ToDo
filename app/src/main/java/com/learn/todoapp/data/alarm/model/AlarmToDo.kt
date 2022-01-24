package com.learn.todoapp.data.alarm.model

import android.os.Parcel
import android.os.Parcelable
import com.learn.todoapp.domain.models.ToDoType

data class AlarmToDo(
    val id: Int = 0,
    val title: String,
    val description: String?,
    val hour: Int,
    val minute: Int,
    val date: Long?,
    val toDoType: ToDoType
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        enumValueOf<ToDoType>(parcel.readString() ?: "")
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeInt(hour)
        parcel.writeInt(minute)
        parcel.writeValue(date)
        parcel.writeString(this.toDoType.name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AlarmToDo> {
        override fun createFromParcel(parcel: Parcel): AlarmToDo {
            return AlarmToDo(parcel)
        }

        override fun newArray(size: Int): Array<AlarmToDo?> {
            return arrayOfNulls(size)
        }
    }
}
