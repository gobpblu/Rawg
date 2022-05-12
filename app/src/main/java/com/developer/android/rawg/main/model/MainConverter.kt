package com.developer.android.rawg.main.model

import com.developer.android.rawg.main.api.model.*

object MainConverter {
    fun fromNetwork(response: GamesResponse) =
        Games(
            count = response.count,
            next = response.next,
            previous = response.previous,
            games = gamesFromNetwork(response.games)
        )

    private fun gamesFromNetwork(games: List<GameDetailsResponse>) =
        games.map {
            GameDetails(
                id = it.id,
                slug = it.slug,
                name = it.name,
                released = it.released,
                tba = it.tba,
                backgroundImage = it.backgroundImage,
                rating = it.rating,
                ratingTop = it.ratingTop,
                ratings = ratingsFromNetwork(it.ratings),
                ratingsCount = it.ratingsCount,
                reviewsTextCount = it.reviewsTextCount,
                added = it.added,
                adds = fromNetwork(it.adds),
                metaCritic = it.metaCritic,
                playTime = it.playTime,
                updated = it.updated,
                genres = genresFromNetwork(it.genres),
                tags = tagsFromNetwork(it.tags),
                shortScreenshots = shortScreenshotsFromNetwork(it.shortScreenshots),
                parentPlatforms = parentPlatformsFromNetwork(it.parentPlatforms)
            )
        }

    private fun ratingsFromNetwork(ratings: List<RatingResponse>) =
        ratings.map {
            Rating(
                id = it.id,
                title = it.title,
                count = it.count,
                percent = it.percent
            )
        }

    private fun fromNetwork(adds: AddedByStatusResponse) =
        AddedByStatus(
            yet = adds.yet,
            owned = adds.owned,
            beaten = adds.beaten,
            toplay = adds.toplay,
            dropped = adds.dropped,
            playing = adds.playing
        )

    private fun genresFromNetwork(genres: List<GenreResponse>) =
        genres.map {
            Genre(
                id = it.id,
                name = it.name,
                slug = it.slug,
                gamesCount = it.gamesCount,
                imageBackground = it.imageBackground,
            )
        }

    private fun tagsFromNetwork(tags: List<TagResponse>) =
        tags.map {
            Tag(
                id = it.id,
                name = it.name,
                language = it.language,
                slug = it.slug,
                gamesCount = it.gamesCount,
                imageBackground = it.imageBackground
            )
        }

    private fun shortScreenshotsFromNetwork(shortScreenshots: List<ShortScreenshotResponse>) =
        shortScreenshots.map {
            ShortScreenshot(
                id = it.id,
                image = it.image
            )
        }

    private fun parentPlatformsFromNetwork(parentPlatforms: List<ParentPlatformContainerResponse>) =
        parentPlatforms.map {
            ParentPlatformContainer(
                parentPlatform = fromNetwork(it.parentPlatform)
            )
        }

    private fun fromNetwork(parentPlatformResponse: ParentPlatformResponse) =
        ParentPlatform(
            id = parentPlatformResponse.id,
            name = parentPlatformResponse.name,
            slug = parentPlatformResponse.slug
        )
}
