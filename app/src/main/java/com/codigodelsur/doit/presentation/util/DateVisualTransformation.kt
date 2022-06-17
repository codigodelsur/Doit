package com.codigodelsur.doit.presentation.util

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateVisualTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        val trimmedText = if (text.text.length >= 8) {
            text.text.substring(0..7)
        } else {
            text.text
        }

        var formattedText = ""
        for (i in trimmedText.indices) {
            formattedText += trimmedText[i]
            if (i % 2 == 1 && i < 4) formattedText += "/"
        }

        return TransformedText(
            text = AnnotatedString(formattedText),
            offsetMapping = OFFSET_MAPPING,
        )
    }

    companion object {
        private val OFFSET_MAPPING = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= 1 -> offset
                    offset <= 3 -> offset + 1
                    offset <= 8 -> offset + 2
                    else -> 10
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= 2 -> offset
                    offset <= 5 -> offset - 1
                    offset <= 10 -> offset - 2
                    else -> 8
                }
            }
        }
    }
}
