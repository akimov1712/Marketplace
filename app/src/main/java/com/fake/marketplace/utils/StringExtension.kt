package com.fake.marketplace.utils

fun String.formatPhoneNumber(): String {
    val digits = this.filter { it.isDigit() }
    return buildString {
        append("+")
        for (i in digits.indices) {
            when (i) {
                1, 4, 7, 9 -> append(" ")
            }
            append(digits[i])
        }
    }
}

fun Int.productCountString(): String {
    return when {
        this % 10 == 1 && this % 100 != 11 -> "$this товар"
        this % 10 in 2..4 && this % 100 !in 12..14 -> "$this товара"
        else -> "$this товаров"
    }
}

fun Int.setTerminationFeedbackCountString(): String {
    return when {
        this % 10 == 1 && this % 100 != 11 -> "$this отзыв"
        this % 10 in 2..4 && this % 100 !in 12..14 -> "$this отзыва"
        else -> "$this отзывов"
    }
}


fun Int.productCountOrderQuantityString(): String {
    return when {
        this % 10 == 1 && this % 100 != 11 -> "$this штука"
        this % 10 in 2..4 && this % 100 !in 12..14 -> "$this штуки"
        else -> "$this штук"
    }
}