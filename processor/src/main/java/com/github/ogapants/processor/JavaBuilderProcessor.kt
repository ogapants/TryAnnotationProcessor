

package com.github.ogapants.processor

import com.github.ogapants.annotation.YamBuilder
import com.google.auto.service.AutoService
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
class JavaBuilderProcessor : AbstractProcessor() {

    override fun getSupportedSourceVersion() = SourceVersion.latestSupported()

    override fun getSupportedAnnotationTypes(): Set<String> {
        return hashSetOf(YamBuilder::class.java.canonicalName)
    }

    override fun process(set: MutableSet<out TypeElement>,
                         roundEnv: RoundEnvironment): Boolean {
        roundEnv.getElementsAnnotatedWith(YamBuilder::class.java)
                .forEach { generateClassFile(it) }
        return true
    }

    private fun generateClassFile(targetElement: Element) {
        val className = "${targetElement.simpleName}"
        val packageName = "${processingEnv.elementUtils.getPackageOf(targetElement)}"
        val fileName = "${className}YamBuilder"
        val typeSpec = TypeSpec.classBuilder(fileName).build()
        val file = JavaFile.builder(packageName, typeSpec)
                .build()

        file.writeTo(processingEnv.filer)
    }
}
