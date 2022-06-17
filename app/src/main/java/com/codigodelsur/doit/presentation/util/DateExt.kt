package com.codigodelsur.doit.presentation.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun Date.toFormattedString(locale: Locale = Locale.getDefault()): String {
    return SimpleDateFormat("MMM dd, yyyy", locale).format(this)
}

fun String.toDateOrNull(locale: Locale = Locale.getDefault()): Date? {
    return try {
        SimpleDateFormat("ddMMyyyy", locale).parse(this)
    } catch (exception: ParseException) {
        null
    }
}
