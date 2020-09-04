package com.dzhafar.calendar.domain.usecases

import com.dzhafar.calendar.domain.enums.EnumDayOfWeek
import com.dzhafar.coreCommon.usecase.base.FlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@Suppress("MagicNumber")
class GetDayOfWeekFromDateUseCase @Inject constructor() :
    FlowUseCase<EnumDayOfWeek, GetDayOfWeekFromDateUseCase.Params> {

    override fun execute(params: Params): Flow<EnumDayOfWeek> {
        return flow {
            val calendar = Calendar.getInstance().apply {
                time = params.currentDate
            }
            val twoDigitsOfYear = calendar.get(Calendar.YEAR) % 100
            var result: Int = twoDigitsOfYear / 4
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            result += dayOfMonth
            val month = calendar.get(Calendar.MONTH)
            result += getMagicHashMap(params.currentDate)[month]!!
            result += getCenturyCoefficient(params.currentDate)
            result += twoDigitsOfYear
            result %= 7
            emit(EnumDayOfWeek.values().find { it.id == result }!!)
        }
    }

    private fun getMagicHashMap(date: Date): HashMap<Int, Int> {
        val calendar = Calendar.getInstance().apply {
            time = date
        }
        return hashMapOf(
            0 to 1,
            1 to if (calendar.get(Calendar.YEAR) % 4 == 0) 1 else 4,
            2 to 4,
            3 to 0,
            4 to 2,
            5 to 5,
            6 to 0,
            7 to 3,
            8 to 6,
            9 to 1,
            10 to 4,
            11 to 6
        )
    }

    private fun getCenturyCoefficient(date: Date): Int {
        val year = Calendar.getInstance().apply {
            time = date
        }.get(Calendar.YEAR)
        val yearCoeff = year % 400
        val yearHashMap: HashMap<Int, Int> = hashMapOf(
            0 to 6,
            1 to 4,
            2 to 2,
            3 to 0
        )
        return yearHashMap[yearCoeff / 100]!!
    }


    data class Params(val currentDate: Date)
}