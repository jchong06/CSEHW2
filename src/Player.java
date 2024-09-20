import java.util.Scanner;
/**
 * @author Justin Chong
 * Email: justin.chong@stonybrook.edu
 * Student ID: 116143020
 * Recitation Number: CSE 214 R03
 * TA: Veronica Oreshko
 */
/**
 * The Player class provides an interactive menu-driven application that allows
 * users to manage a playlist of songs. The playlist is implemented as a linked
 * list, enabling users to perform various actions such as adding, removing,
 * navigating, and playing songs.
 *
 * <p>
 * The class contains a main method that presents a menu to the user, offering
 * the following options:
 * <ul>
 * <li>Add a song to the playlist</li>
 * <li>Move to the next song in the playlist</li>
 * <li>Move to the previous song in the playlist</li>
 * <li>Remove a song from the playlist</li>
 * <li>Play a song</li>
 * <li>Clear the playlist</li>
 * <li>Shuffle the playlist</li>
 * <li>Play a random song from the playlist</li>
 * <li>Print the current playlist</li>
 * <li>Get the total number of songs in the playlist</li>
 * <li>Exit the playlist</li>
 * </ul>
 *
 * <p>
 * The program runs in a loop, repeatedly displaying the menu and executing the
 * user's chosen command until the user opts to exit the application by selecting
 * the 'Q' option.
 * </p>
 *
 * <p>
 * This class interacts with the SongLinkedList class for playlist management and
 * the Song class for storing song details.
 * </p>
 *
*/
public class Player {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        SongLinkedList songs = new SongLinkedList();
        String menu = "(A) Add Song to Playlist\n" +
                "(F) Go to Next Song\n" +
                "(B) Go to Previous Song\n" +
                "(R) Remove Song from Playlist\n" +
                "(L) Play a Song\n" +
                "(C) Clear the Playlist\n" +
                "(S) Shuffle Playlist\n" +
                "(Z) Random Song\n" +
                "(P) Print Playlist\n" +
                "(T) Get the total amount of songs in the playlist\n" +
                "(Q) Exit the playlist";
        System.out.println(menu);
        System.out.print("Please enter a command: ");
        String option = input.nextLine().toUpperCase();
        while (!option.equals("Q")) {
            if (option.equals("A")) {
                System.out.print("Enter song title: ");
                String name = input.nextLine();
                System.out.print("Enter artist(s) of the song: ");
                String artist = input.nextLine();
                System.out.print("Enter album: ");
                String album = input.nextLine();
                System.out.print("Enter length (in seconds): ");
                String length = input.nextLine();
                Song s = new Song(name, artist, album, Integer.parseInt(length));
                songs.insertAfterCursor(s);
                System.out.println("\n'" + name + "'" + " by " + artist + " is added to your playlist.\n");
            }
            if (option.equals("F")) {
                songs.cursorFor();
            }
            if (option.equals("B"))
                songs.cursorBack();
            if (option.equals("P")) {
                System.out.println(songs);
            }
            if (option.equals("T")) {
                if (songs.getSize() == 0) {
                    System.out.println("\nYour playlist is empty\n");
                } else {
                    System.out.println("\nYour playlist contains " + songs.getSize() + " songs.\n");
                }
            }
            if (option.equals("R")) {
                if (songs.getCursor() != null) {
                    Song removed = songs.removeCursor();
                    String songName = removed.getName();
                    String a = removed.getArtist();
                    System.out.println("\n'" + songName + "' by " + a + " was removed from the playlist.\n");
                } else {
                    System.out.println("\nYour playlist is empty\n");
                }
            }
            if (option.equals("L")) {
                if (songs.getCursor() != null) {
                    System.out.print("Enter name of song to play: ");
                    String name = input.nextLine();
                    songs.play(name);
                } else {
                    System.out.println("\nYour playlist is empty\n");
                }
            }
            if (option.equals("C")) {
                songs.deleteAll();
                System.out.println("\nPlaylist cleared.\n");
            }
            if (option.equals("S")) {
                songs.shuffle();
                System.out.println("\nPlaylist shuffled.\n");
            }
            if (option.equals("Z")) {
                Song s = songs.random();
                if (s == null) {
                    System.out.println("\nYour playlist is empty\n");
                } else {
                    System.out.println("\nPlaying a random song\n");
                    System.out.println("\n'" + s.getName() + "' by " + s.getArtist() + " is now playing\n");
                }
            }
            System.out.println(menu);
            System.out.print("Please enter a command: ");
            option = input.nextLine().toUpperCase();
        }
    }
}
