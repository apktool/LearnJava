package com.examples;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author apktool
 * @date 2018-08-29 17:17
 */
public class AlbumDemo {
    public Album generateAlbum() {
        return new Album.Builder("Songs from the Big Chair", 1985)
                .artist("Tears For Fears")
                .songTitle("Shout")
                .songTitle("The Working Hour")
                .songTitle("Everybody Wants to Rule the World")
                .songTitle("Mothers Talk")
                .songTitle("I Believe")
                .songTitle("Broken")
                .songTitle("Head Over Heels")
                .songTitle("Listen")
                .build();
    }

    public Album instantiateAlbumFromBinary(final byte[] binaryAlbum) {
        Album album = null;
        try {
            final AlbumProtos.Album copiedAlbumProtos = AlbumProtos.Album.parseFrom(binaryAlbum);
            final List<String> copiedArtists = copiedAlbumProtos.getArtistList();
            final List<String> copiedSongsTitles = copiedAlbumProtos.getSongTitleList();
            album = new Album.Builder(
                    copiedAlbumProtos.getTitle(), copiedAlbumProtos.getReleaseYear())
                    .artists(copiedArtists)
                    .songsTitles(copiedSongsTitles)
                    .build();
        } catch (InvalidProtocolBufferException ipbe) {
            System.out.println("ERROR: Unable to instantiate AlbumProtos.Album instance from provided binary data - "
                    + ipbe);
        }
        return album;
    }

    public static void main(final String[] arguments) {
        final AlbumDemo instance = new AlbumDemo();

        /*
        final Album album = instance.generateAlbum();
        final AlbumProtos.Album albumMessage
                = AlbumProtos.Album.newBuilder()
                .setTitle(album.getTitle())
                .addAllArtist(album.getArtists())
                .setReleaseYear(album.getReleaseYear())
                .addAllSongTitle(album.getSongsTitles())
                .build();

        final byte[] binaryAlbum = albumMessage.toByteArray();

        final Album copiedAlbum = instance.instantiateAlbumFromBinary(binaryAlbum);
        System.out.println("BEFORE Album (" + System.identityHashCode(album) + "): " + album);
        System.out.println(" AFTER Album (" + System.identityHashCode(copiedAlbum) + "): " + String.valueOf(copiedAlbum));
        */

        //---------------------

        ArrayList<String> artistList = new ArrayList<String>();
        artistList.add("artistList1");
        artistList.add("artistList2");
        artistList.add("artistList3");
        ArrayList<String> songList = new ArrayList<String>();
        songList.add("song1");
        songList.add("song2");
        songList.add("song3");

        final AlbumProtos.Album temp = AlbumProtos.Album.newBuilder()
                .setTitle("li")
                .addAllArtist(artistList)
                .setReleaseYear(2018)
                .addAllSongTitle(songList)
                .build();

        System.out.println("------------------");
        System.out.println("\n"
                + temp.getTitle() + "\n"
                + temp.getArtistList() + "\n"
                + temp.getReleaseYear() + "\n"
                + temp.getSongTitleList()
        );
    }
}
