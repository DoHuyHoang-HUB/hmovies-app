package com.codingtok.hmovies.data.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.FormatStyle
import java.util.*

class Date(val date: LocalDate?): Comparable<Date> {
    constructor(str: String?) : this(validate(str))
    constructor(localDateTime: LocalDateTime?) : this(localDateTime?.toLocalDate())

    /**
     * Returns a human string depending on device location in form of [FormatStyle.LONG]
     */
    fun localize(locale: Locale = Locale.US, format: FormatStyle = FormatStyle.LONG): String? {
        return date?.format(DateTimeFormatter.ofLocalizedDate(format).withLocale(locale))
    }

    // For orderBy
    override fun compareTo(other: Date): Int {
        if (this.date == null || other.date == null)
            return 0

        return this.date.compareTo(other.date)
    }

    override fun toString(): String = date.toString()

    companion object {

        private fun validate(str: String?): LocalDate? {
            return try {
                LocalDate.parse(str?.split("T")?.first())
            } catch (e: DateTimeParseException) {
                null
            }
        }

        internal val ADAPTER = object : Any() {
            @FromJson
            fun fromJson(json: String?): Date? {
                return try {
                    Date(json)
                } catch (e: DateTimeParseException) {
                    null
                }
            }

            @ToJson
            fun toJson(date: Date): String {
                return date.toString()
            }
        }
    }
}