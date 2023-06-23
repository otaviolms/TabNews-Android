package br.com.otaviolms.tabnews.implementations.annotations

import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class Binding(val binding: KClass<out ViewBinding>)