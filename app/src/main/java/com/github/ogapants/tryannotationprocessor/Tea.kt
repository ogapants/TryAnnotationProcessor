package com.github.ogapants.tryannotationprocessor

import com.github.ogapants.annotation.YamBuilder

/**
 * Created by oga on 2018/09/24.
 */
@YamBuilder
data class Tea (
        val id: Int,
        val name: String,
        val price: Int?,
        val category: List<String>
)