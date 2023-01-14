package com.subhash1e.masteringpdf

data class PdfRowDataClass(
    val stdName:String,
    val totMarks:Int,
    val sMarkDetails: List<SubjectDataClass>
)