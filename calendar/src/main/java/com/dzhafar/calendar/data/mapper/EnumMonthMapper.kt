package com.dzhafar.calendar.data.mapper

import com.dzhafar.calendar.domain.enums.EnumMonths
import com.dzhafar.coreDbApi.data.enums.MonthEnums

fun EnumMonths.mapToDataMonthEnum() = MonthEnums.values().find { it.id == id }!!

fun MonthEnums.mapToDomain() = EnumMonths.values().find { it.id == id }!!