package io.github.keddnyo.quicktag.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.keddnyo.quicktag.utils.BBCode

@Entity(tableName = "rules")
data class BoardRule(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "content")
    val content: String = "",

    @ColumnInfo(name = "is_official", defaultValue = "true")
    val isOfficial: Boolean = false

) {
    var contentPreview = BBCode().decode(content)
}