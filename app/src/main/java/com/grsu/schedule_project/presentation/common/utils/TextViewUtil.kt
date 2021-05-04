package com.grsu.schedule_project.presentation.common.utils

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.CharacterStyle
import android.widget.TextView

data class SpanDataObject(
    val span: CharacterStyle,
    val spanString: String
)

private fun getSpannedText(spanDataObject: SpanDataObject, text: CharSequence): Spannable {
    val spannableText = text as? Spannable ?: SpannableString(text)
    val captionSpan = spanDataObject.spanString

    val indexStart = spannableText.indexOf(captionSpan)
    val indexEnd = indexStart + captionSpan.length

    spannableText.setSpan(
        spanDataObject.span,
        indexStart,
        indexEnd,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    return spannableText
}

fun TextView.setClickableSpan(spanDataObject: SpanDataObject) {
    setText(
        getSpannedText(spanDataObject, text),
        TextView.BufferType.SPANNABLE
    )
    movementMethod = LinkMovementMethod.getInstance()
}

fun TextView.setColoredSpan(spanDataObject: SpanDataObject) {
    setText(
        getSpannedText(spanDataObject, text),
        TextView.BufferType.SPANNABLE
    )
}

fun TextView.setUnderlinedSpan(spanDataObject: SpanDataObject) {
    setText(
        getSpannedText(spanDataObject, text),
        TextView.BufferType.SPANNABLE
    )
}