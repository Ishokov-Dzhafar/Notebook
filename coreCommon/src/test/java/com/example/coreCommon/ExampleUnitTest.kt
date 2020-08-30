package com.example.coreCommon

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.ZoneId
import java.util.Calendar
import java.util.Date

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {

        val currentDate = LocalDate.now()
        //val currentDate = LocalDate.of(2482, 12, 16)
        val calendar = Calendar.getInstance()
        calendar.time = Date.from(currentDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
        val year = calendar.get(Calendar.YEAR)
        val day = 1
        val month = calendar.get(Calendar.MONTH)
        val cal = Calendar.getInstance().apply {
            set(Calendar.YEAR, year)
            set(Calendar.MONTH, month)
            set(Calendar.DAY_OF_MONTH, 1)
        }
        System.out.println("TEST $day-$month-$year  ${cal.time.time}")
        assertEquals(4, 2 + 2)
    }
}