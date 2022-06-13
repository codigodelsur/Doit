package com.codigodelsur.doit.presentation.util

import java.text.SimpleDateFormat
import java.util.*

fun Date.toFormattedString(locale: Locale = Locale.getDefault()): String {
    return SimpleDateFormat("MMM dd, yyyy", locale).format(this)
}
