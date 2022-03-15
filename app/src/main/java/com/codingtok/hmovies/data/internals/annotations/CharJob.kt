package com.codingtok.hmovies.data.internals.annotations

import com.codingtok.hmovies.data.enums.MediaType
import com.codingtok.hmovies.data.model.*
import com.squareup.moshi.*
import java.lang.reflect.Type
import kotlin.streams.toList

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
@JsonQualifier
internal annotation class CharJob(
    val fieldName: String = "results",
    val mediaType: MediaType = MediaType.UNKNOWN
)

internal class CharJobAdapter(
    moshi: Moshi,
    fieldName: String,
    private val mediaType: MediaType
) : JsonAdapter<Any>() {

    private val personJsonAdapter = moshi.adapter(Person.Slim::class.java)
    private val movieJsonAdapter = moshi.adapter(Movie.Slim::class.java)
    private val showJsonAdapter = moshi.adapter(TVShow.Slim::class.java)
    private val mediaItemJsonAdapter = moshi.adapter(MediaTypeItem::class.java)

    private val options: JsonReader.Options = JsonReader.Options.of(fieldName)

    companion object {
        val INSTANCE = RoleJobFactory()
    }

    override fun fromJson(reader: JsonReader): Any {
        val result = mutableListOf<Pair<MediaTypeItem, Any>>()
        reader.beginObject()

        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                -1 -> {
                    // Unknown name, skip it.
                    reader.skipName()
                    reader.skipValue()
                }
                else -> {

                    reader.beginArray()
                    // Iterate over all items
                    while (reader.hasNext()) {
                        val tmpReader = reader.peekJson()
                        val cr = getCredit(tmpReader)!!

                        when (mediaType) {
                            MediaType.TV -> {
                                showJsonAdapter.fromJson(reader)?.let {
                                    result.add(Pair(it, cr))
                                }
                            }
                            MediaType.MOVIE -> {
                                movieJsonAdapter.fromJson(reader)?.let {
                                    result.add(Pair(it, cr))
                                }
                            }
                            MediaType.PERSON -> {
                                personJsonAdapter.fromJson(reader)?.let {
                                    result.add(Pair(it, cr))
                                }
                            }
                            else -> {
                                mediaItemJsonAdapter.fromJson(reader)?.let {
                                    result.add(Pair(it, cr))
                                }
                            }
                        }
                    }
                    reader.endArray()
                }
            }
        }

        reader.endObject()
        return result
    }

    private fun getCredit(reader: JsonReader): Any? {
        val options: JsonReader.Options = JsonReader.Options.of(
            "credit_id",        // 0
            "character",        // 1
            "job",              // 2
            "episode_count",    // 3
            "order",            // 4
            "department",       // 5
            "roles",            // 6    => credit_id, character, episode_count
            "jobs"              // 7
        )

        var creditId = "INVALID"
        var character: String? = null
        var job: String? = null
        var episodeCount: Int? = null
        var order = -1
        var department = "NOT_FOUND"

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.selectName(options)) {
                -1 -> {
                    reader.skipName()
                    reader.skipValue()
                }
                0 -> creditId = reader.nextString()
                1 -> character = reader.nextString()
                2 -> job = reader.nextString()
                3 -> episodeCount = reader.nextInt()
                4 -> order = reader.nextInt()
                5 -> department = reader.nextString()
                6 -> {
                    // Roles
                    reader.beginArray()
                    while (reader.hasNext()) {
                        reader.beginObject()
                        while (reader.hasNext()) {
                            when (reader.selectName(options)) {
                                -1 -> {
                                    reader.skipName()
                                    reader.skipValue()
                                }
                                0 -> creditId = reader.nextString()
                                1 -> character = reader.nextString()
                                3 -> episodeCount = reader.nextInt()
                            }
                        }
                        reader.endObject()
                    }
                    reader.endArray()
                }
                7 -> {
                    // Jobs
                    reader.beginArray()
                    while (reader.hasNext()) {
                        reader.beginObject()
                        while (reader.hasNext()) {
                            when (reader.selectName(options)) {
                                -1 -> {
                                    reader.skipName()
                                    reader.skipValue()
                                }
                                0 -> creditId = reader.nextString()
                                2 -> job = reader.nextString()
                                3 -> episodeCount = reader.nextInt()
                            }
                        }
                        reader.endObject()
                    }
                    reader.endArray()
                }
            }
        }
        reader.endObject()

        return when {
            character != null -> Person.CastRole(creditId, character, order, episodeCount)
            job != null -> Person.CrewJob(creditId, job, department, episodeCount)
            else -> null
        }
    }

    override fun toJson(writer: JsonWriter, value: Any?) =
        throw UnsupportedOperationException("ResultsListAdapter is only used to deserialize objects")

    class RoleJobFactory : Factory {
        override fun create(
            type: Type,
            annotations: Set<Annotation>,
            moshi: Moshi
        ): JsonAdapter<Any>? {
            Types.nextAnnotations(annotations, CharJob::class.java) ?: return null

            val rawType = Types.getRawType(type)

            if (!Error.isAnyError(rawType)) {
                if (Types.getRawType(type) != List::class.java)
                    throw IllegalArgumentException("Only lists may be annotated with @ResultsList. Found: $type")
            }

            val resListAnnotation =
                annotations.stream().filter { it is CharJob }.toList().firstOrNull()
                    ?: throw IllegalArgumentException("List has no valid fieldName: $type")

            val fieldName = (resListAnnotation as CharJob).fieldName
            val mediaType = resListAnnotation.mediaType

            return CharJobAdapter(moshi, fieldName, mediaType)
        }
    }
}