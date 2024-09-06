package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    private static ArrayList<Album> albums = new ArrayList<>();

    public static void main(String[] args) {
        // Create album and add songs
        Album album = new Album("Album1", "AC/DC");
        album.addSong("Cheers", 4.5, "C:\\Users\\HP 6470b\\Desktop\\song\\seeyou.wav");
        album.addSong("SeeYou", 3.5, "C:\\Users\\HP 6470b\\Desktop\\song\\cheers.wav");
        albums.add(album);

        album = new Album("Album2", "Eminem");
        album.addSong("Dog", 4.5, "C:\\Users\\HP 6470b\\Desktop\\song\\dog.wav");
        albums.add(album);

        // Create a playlist and add songs to it
        LinkedList<Song> playList = new LinkedList<>();
        albums.get(0).addToPlayList("Cheers", playList); // Case-sensitive check
        albums.get(0).addToPlayList("SeeYou", playList);
        albums.get(1).addToPlayList("Dog", playList);

        // Start playing the playlist
        play(playList);
    }

    private static void play(LinkedList<Song> playList) {
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        boolean forward = true;

        // ListIterator to iterate over the playlist
        ListIterator<Song> listIterator = playList.listIterator();
        if (playList.size() == 0) {
            System.out.println("This playlist has no songs.");
            return;
        } else {
            // Play the first song
            Song currentSong = listIterator.next();
            System.out.println("Now playing: " + currentSong);
            AudioPlayer.playSound(currentSong.getFilePath());
            System.out.println("Song finished. What would you like to do?");
            printMenu();
        }

        // User action loop
        while (!quit) {
            int action = sc.nextInt();
            sc.nextLine();

            switch (action) {
                case 0:
                    System.out.println("Exiting the playlist. Thank you for listening!");
                    quit = true;
                    break;

                case 1:
                    if (!forward) {
                        if (listIterator.hasNext()) {
                            listIterator.next();
                        }
                        forward = true;
                    }
                    if (listIterator.hasNext()) {
                        Song nextSong = listIterator.next();
                        System.out.println("Now playing: " + nextSong);
                        AudioPlayer.playSound(nextSong.getFilePath());
                        System.out.println("Song finished. What would you like to do next?");
                        printMenu();
                    } else {
                        System.out.println("You've reached the end of the playlist.");
                        forward = false;
                    }
                    break;

                case 2:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            listIterator.previous();
                        }
                        forward = false;
                    }
                    if (listIterator.hasPrevious()) {
                        Song previousSong = listIterator.previous();
                        System.out.println("Now playing: " + previousSong);
                        AudioPlayer.playSound(previousSong.getFilePath());
                        System.out.println("Song finished. What would you like to do next?");
                        printMenu();
                    } else {
                        System.out.println("You are at the start of the playlist.");
                    }
                    break;

                case 3:
                    if (forward) {
                        if (listIterator.hasPrevious()) {
                            Song replaySong = listIterator.previous();
                            System.out.println("Replaying: " + replaySong);
                            AudioPlayer.playSound(replaySong.getFilePath());
                            forward = false;
                            System.out.println("Song finished. What would you like to do next?");
                            printMenu();
                        }
                    } else {
                        if (listIterator.hasNext()) {
                            Song replaySong = listIterator.next();
                            System.out.println("Replaying: " + replaySong);
                            AudioPlayer.playSound(replaySong.getFilePath());
                            forward = true;
                            System.out.println("Song finished. What would you like to do next?");
                            printMenu();
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    printMenu();
                    break;
            }
        }
    }

    // Print the options menu for the user
    private static void printMenu() {
        System.out.println("Available actions:\nPress:");
        System.out.println("0 - to quit\n" +
                           "1 - to play the next song\n" +
                           "2 - to play the previous song\n" +
                           "3 - to replay the current song");
    }
}
