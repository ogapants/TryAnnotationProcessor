package com.github.ogapants.annotation

import java.lang.annotation.Retention
import java.lang.annotation.Target


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class YamBuilder