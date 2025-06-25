package com.example.utils

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): Date? {
    return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(this)
}

fun Date.toStringFormat(): String {
    return SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(this)
}