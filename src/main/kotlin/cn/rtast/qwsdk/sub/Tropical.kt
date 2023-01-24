/*
 * Copyright 2023 RTAkland
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package cn.rtast.qwsdk.sub

import cn.rtast.qwsdk.QWeather
import cn.rtast.qwsdk.entity.tropical.forecast.TropicalForecastBean
import cn.rtast.qwsdk.entity.tropical.list.TropicalListBean
import cn.rtast.qwsdk.entity.tropical.track.TropicalTrackBean
import cn.rtast.qwsdk.enums.BasinType
import cn.rtast.qwsdk.errors.UnsupportedAreaException
import cn.rtast.qwsdk.errors.UnsupportedYearException
import cn.rtast.qwsdk.utils.HTTPUtil
import com.google.gson.Gson
import java.time.Year

class Tropical {

    private val gson = Gson()

    fun forecast(stormID: String): TropicalForecastBean {
        val url = "${QWeather.rootAPI}/tropical/storm-forecast" +
                "?stormid=$stormID"
        val result = HTTPUtil.get(url)
        return gson.fromJson(result, TropicalForecastBean::class.java)
    }

    fun track(stormID: String): TropicalTrackBean {
        val url = "${QWeather.rootAPI}/tropical/storm-track" +
                "?stormid=$stormID"
        val result = HTTPUtil.get(url)
        return gson.fromJson(result, TropicalTrackBean::class.java)
    }

    fun list(
        basin: BasinType = BasinType.NP,
        year: String
    ): TropicalListBean {

        if (basin != BasinType.NP) {
            throw UnsupportedAreaException("Current not support this area: ${basin.name.lowercase()}!")
        }

        val currentYear = Year.now().toString().toInt()
        val lastYear = currentYear - 1
        if (year != currentYear.toString() && year != lastYear.toString()) {
            throw UnsupportedYearException("You can't list the year before last year and future storms!")
        }
        val url = "${QWeather.rootAPI}/tropical/storm-list" +
                "?basin=${basin.name.lowercase()}" +
                "&year=$year"
        val result = HTTPUtil.get(url)
        return gson.fromJson(result, TropicalListBean::class.java)
    }
}