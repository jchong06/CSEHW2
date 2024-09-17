import java.util.Scanner;

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
            if (option.equals("A")){
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
                System.out.println("\n'" + name + "'" + " by " + artist +  " is added to your playlist.\n");
            }
            if (option.equals("F")){
                songs.cursorFor();
            }
            if (option.equals("B"))
                songs.cursorBack();
            if (option.equals("P")){
                System.out.println(songs);
            }
            if (option.equals("T")) {
                if (songs.getSize() == 0){
                    System.out.println("\nYour playlist is empty\n");
                }
                else {
                    System.out.println("\nYour playlist contains " + songs.getSize() + " songs.\n");
                }
            }
            if (option.equals("R")) {
                if (songs.getCursor() != null){
                    Song removed = songs.removeCursor();
                    String songName = removed.getName();
                    String a = removed.getArtist();
                    System.out.println("\n'" + songName + "' by " + a + " was removed from the playlist.\n");
                }
                else{
                    System.out.println("\nYour playlist is empty\n");
                }
            }
            if (option.equals("L")){
                System.out.print("Enter name of song to play: ");
                String name = input.nextLine();
                songs.play(name);
            }
            if (option.equals("C")){
                songs.deleteAll();
                System.out.println("\nPlaylist cleared.\n");
            }
            System.out.println(menu);
            System.out.print("Please enter a command: ");
            option = input.nextLine().toUpperCase();
        }
    }
}