package com.codingtok.hmovies.services

import com.codingtok.hmovies.data.enums.MediaType
import com.codingtok.hmovies.data.network.service.trending.Trending
import com.codingtok.hmovies.data.network.api.Api
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TrendingServiceTest {
    @Test
    fun `Get trending movies`() = runBlocking {
        val trending = Api.trendingService.get(Trending.Type.MOVIE, Trending.TimeWindow.DAY).invoke()!!
        Assertions.assertTrue(trending.totalResults > 100)
        Assertions.assertTrue(trending.totalPages > 100)

        val firstMovie = trending.results.first()
        Assertions.assertEquals(MediaType.MOVIE, firstMovie.mediaType)
    }

    @Test
    fun `Get trending shows`() = runBlocking {
        val trending = Api.trendingService.get(Trending.Type.TV, Trending.TimeWindow.DAY).invoke()!!
        Assertions.assertTrue(trending.totalResults > 100)
        Assertions.assertTrue(trending.totalPages > 100)

        val firstShow = trending.results.first()
        Assertions.assertEquals(MediaType.TV, firstShow.mediaType)
    }

//    @Test
//    fun `Get trending persons`() = runBlocking {
//        val trending = Api.trendingService.get(Trending.Type.PERSON, Trending.TimeWindow.DAY).invoke()!!
//        Assertions.assertTrue(trending.totalResults > 100)
//        Assertions.assertTrue(trending.totalPages > 100)
//
//        val firstPerson = trending.results.first()
//        Assertions.assertEquals(MediaType.PERSON, firstPerson.mediaType)
//    }
}