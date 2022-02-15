package com.codingtok.hmovies.data.annotations

import android.os.Build
import com.codingtok.hmovies.data.model.Image
import com.squareup.moshi.*
import java.lang.reflect.Type

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
@JsonQualifier
internal annotation class ImageAnnotation
internal class ImageAdapter: JsonAdapter<Image?>() {
    private val stringAdapter: JsonAdapter<String?> = Moshi.Builder().build().adapter(String::class.java)

    companion object {
        val INSTANCE = ImageFactory()
    }

    override fun fromJson(reader: JsonReader): Image? = stringAdapter.fromJson(reader)?.let { Image(it) }

    override fun toJson(writer: JsonWriter, value: Image?) = throw UnsupportedOperationException("ImageAdapter is only used to deserialize objects")

    class ImageFactory: Factory {
        override fun create(
            type: Type,
            annotations: MutableSet<out Annotation>,
            moshi: Moshi
        ): JsonAdapter<*>? {
            Types.nextAnnotations(annotations, ImageAnnotation::class.java) ?: return null

            if (Types.getRawType(type) != Image::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    throw IllegalArgumentException(
                        "Only Image may be annotation with @Image. Found: ${type.typeName}"
                    )
                }

            return ImageAdapter()
        }

    }
}