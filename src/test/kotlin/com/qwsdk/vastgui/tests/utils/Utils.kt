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

package com.qwsdk.vastgui.tests.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

fun randomID(): String {
    val idList = listOf(
        "101010100",
        "101020100",
        "101030100",
        "101040100",
        "101210101",
        "101280101",
        "101250101"
    )
    return idList[Random.nextInt(0, 7)]
}

fun getCurrentDate(): String {
    return SimpleDateFormat("yyyyMMdd").format(Date())
}

fun getCurrentYear(): String {
    return SimpleDateFormat("yyyy").format(Date())
}
