package com.aplikasi.testedumath.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Data : Serializable {
    @SerializedName("id")
    val id: String? = null

    @SerializedName("text")
    val text: String? = null

    @SerializedName("selection")
    val selection: List<Selection>? = null

    @SerializedName("selection_answer")
    val selectionAnswer: List<SelectionAnswer>? = null

    @SerializedName("discussion")
    val discussion: String? = null

    @SerializedName("question_type")
    val questionType: Int? = null

    @SerializedName("short_answer")
    val shortAnswer: List<ShortAnswer>? = null

    @SerializedName("subject_name")
    val subjectName: String? = null

    @SerializedName("category_name")
    val categoryName: String? = null
}

class Selection : Serializable{
    @SerializedName("id_selection")
    val idSelection: Int? = null

    @SerializedName("selection_text")
    val selectionText: String? = null
}

class SelectionAnswer : Serializable{
    @SerializedName("id_answer")
    val idAnswer: Int? = null

    @SerializedName("selection_id")
    val selectionId: Int? = null

    @SerializedName("selection_text")
    val selectionText: String? = null
}

class ShortAnswer : Serializable{
    @SerializedName("id_short_answer")
    val idShortAnswer: Int? = null

    @SerializedName("short_answer_text")
    val shortAnswerText: String? = null

    @SerializedName("first_range")
    val firstRange: String? = null

    @SerializedName("second_range")
    val secondRange: String? = null

    @SerializedName("subject_name")
    val subjectName: String? = null

    @SerializedName("category_name")
    val categoryName: String? = null
}