/*
 * Copyright 2019-2024 JetBrains s.r.o. and contributors.
 * Use of this source code is governed by the Apache 2.0 License that can be found in the LICENSE.txt file.
 */

package kotlinx.datetime.test.samples.format

import kotlinx.datetime.*
import kotlinx.datetime.format.*
import kotlin.test.*

class LocalDateFormatSamples {
    @Test
    fun dayOfMonth() {
        // Using day-of-month with various paddings in a custom format
        val zeroPaddedDays = LocalDate.Format {
            day(); char('/'); monthNumber(); char('/'); year()
        }
        check(zeroPaddedDays.format(LocalDate(2021, 1, 6)) == "06/01/2021")
        check(zeroPaddedDays.format(LocalDate(2021, 1, 31)) == "31/01/2021")
        val spacePaddedDays = LocalDate.Format {
            day(padding = Padding.SPACE); char('/'); monthNumber(); char('/'); year()
        }
        check(spacePaddedDays.format(LocalDate(2021, 1, 6)) == " 6/01/2021")
        check(spacePaddedDays.format(LocalDate(2021, 1, 31)) == "31/01/2021")
    }

    @Test
    fun dayOfWeek() {
        // Using strings for day-of-week names in a custom format
        val format = LocalDate.Format {
            dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED); char(' '); day(); char('/'); monthNumber(); char('/'); year()
        }
        check(format.format(LocalDate(2021, 1, 13)) == "Wed 13/01/2021")
        check(format.format(LocalDate(2021, 12, 13)) == "Mon 13/12/2021")
    }

    @Test
    fun dayOfYear() {
        // Using day-of-year in a custom format
        val format = LocalDate.Format {
            year(); dayOfYear()
        }
        check(format.format(LocalDate(2021, 2, 13)) == "2021044")
        check(format.parse("2021044") == LocalDate(2021, 2, 13))
    }

    @Test
    fun date() {
        // Using a predefined format for a date in a larger custom format
        val format = LocalDateTime.Format {
            date(LocalDate.Formats.ISO)
            alternativeParsing({ char('t') }) { char('T') }
            hour(); char(':'); minute()
        }
        check(format.format(LocalDateTime(2021, 1, 13, 14, 30)) == "2021-01-13T14:30")
    }

    class DayOfWeekNamesSamples {
        @Test
        fun usage() {
            // Using strings for day-of-week names in a custom format
            val format = LocalDate.Format {
                date(LocalDate.Formats.ISO)
                chars(", ")
                dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED) // "Mon", "Tue", ...
            }
            check(format.format(LocalDate(2021, 1, 13)) == "2021-01-13, Wed")
        }

        @Test
        fun constructionFromStrings() {
            // Constructing a custom set of day of week names for parsing and formatting by passing 7 strings
            val myDayOfWeekNames = DayOfWeekNames(
                "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
            )
            check(myDayOfWeekNames == DayOfWeekNames.ENGLISH_ABBREVIATED) // could just use the built-in one...
        }

        @Test
        fun constructionFromList() {
            // Constructing a custom set of day of week names for parsing and formatting
            val germanDayOfWeekNames = listOf(
                "Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag"
            )
            // constructing by passing a list of 7 strings
            val myDayOfWeekNames = DayOfWeekNames(germanDayOfWeekNames)
            check(myDayOfWeekNames.names == germanDayOfWeekNames)
        }

        @Test
        fun names() {
            // Obtaining the list of day of week names
            check(DayOfWeekNames.ENGLISH_ABBREVIATED.names == listOf(
                "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
            ))
        }

        @Test
        fun englishFull() {
            // Using the built-in English day of week names in a custom format
            val format = LocalDate.Format {
                date(LocalDate.Formats.ISO)
                chars(", ")
                dayOfWeek(DayOfWeekNames.ENGLISH_FULL)
            }
            check(format.format(LocalDate(2021, 1, 13)) == "2021-01-13, Wednesday")
        }

        @Test
        fun englishAbbreviated() {
            // Using the built-in English abbreviated day of week names in a custom format
            val format = LocalDate.Format {
                date(LocalDate.Formats.ISO)
                chars(", ")
                dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
            }
            check(format.format(LocalDate(2021, 1, 13)) == "2021-01-13, Wed")
        }
    }
}
