package io.github.keddnyo.quicktag.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.keddnyo.quicktag.utils.BBCode

@Entity(tableName = "board_rule_table")
data class BoardRule(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val content: String = ""

) {
    var contentPreview = BBCode().decode(content)
}