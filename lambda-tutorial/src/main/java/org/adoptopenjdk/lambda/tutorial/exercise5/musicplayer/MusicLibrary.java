package org.adoptopenjdk.lambda.tutorial.exercise5.musicplayer;

/*
 * #%L
 * lambda-tutorial
 * %%
 * Copyright (C) 2013 Adopt OpenJDK
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public interface MusicLibrary {

    /**
     * @return every song in the collection, in no specified order.
     */
    Collection<Song> allSongs();

    /**
     * Will sort a given collection of Songs by artist.
     */
    static class SongByArtistSorter {
        public static List<Song> sort(Collection<Song> songs) {
            return songs.stream().sorted(Comparator.comparing(Song::getArtist))
                    .collect(toList());
        }
    }

    default List<Song> sortedByArtist(){
        return SongByArtistSorter.sort(allSongs());
    }
    /**
     * Provides a rating for this song, between 1-100, inclusive.
     *
     * Default implementation takes a rating by normalising the play count for the given song with the play count for
     * all songs in this MusicLibrary.
     */
    default Rating ratingOf(Song song) {
        int totalPlayCount = allSongs().stream().mapToInt(this::timesPlayed).sum();
        float rating = (100f * timesPlayed(song)) / totalPlayCount;
        return new Rating(Math.round(rating));
    }

    int timesPlayed(Song song);
}
