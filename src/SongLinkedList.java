import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Arrays;

/**
 * @author Justin Chong
 * Email: justin.chong@stonybrook.edu
 * Student ID: 116143020
 * Recitation Number: CSE 214 R03
 * TA: Veronica Oreshko
 */

/**
 * The SongLinkedList class represents a playlist of songs implemented using a doubly linked list.
 * It supports operations such as adding, removing, shuffling, and playing songs. The list also maintains
 * a cursor that allows traversal of the playlist and interaction with the current song.
 *
 * <p>
 * This class provides methods to manipulate and access the playlist, including adding songs after the cursor,
 * moving the cursor forwards and backwards, removing the current song, playing a song, shuffling the playlist,
 * and clearing the entire playlist.
 * </p>
 */
public class SongLinkedList {
    private SongNode head;   // The head (first) node of the linked list
    private SongNode tail;   // The tail (last) node of the linked list
    private SongNode cursor; // The current node (song) in the linked list
    private int size;        // The number of songs in the playlist

    /**
     * Constructs an empty SongLinkedList.
     */
    public SongLinkedList() {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    /**
     * Plays the specified song by searching for it in the playlist. If found,
     * the song is played, and the cursor is set to that song. The song must be
     * available as a .wav file in the current directory.
     *
     * @param name the name of the song to be played
     * @throws IllegalArgumentException if the song is not found in the playlist
     */
    public void play(String name) throws IllegalArgumentException {
        SongNode nodePtr = head;
        while (nodePtr.getNext() != null) {
            if (nodePtr.getData().getName().equals(name)) {
                setCursor(nodePtr);
                try {
                    AudioInputStream AIS = AudioSystem.getAudioInputStream(new File(name + ".wav"));
                    Clip c = AudioSystem.getClip();
                    c.open(AIS);
                    c.start();
                } catch (Exception ex) {
                    // Handle the exception silently
                }
                break;
            }
            nodePtr = nodePtr.getNext();
        }
        if (nodePtr.getData().getName().equals(name)) {
            setCursor(nodePtr);
            try {
                AudioInputStream AIS = AudioSystem.getAudioInputStream(new File(name + ".wav"));
                Clip c = AudioSystem.getClip();
                c.open(AIS);
                c.start();
            } catch (Exception ex) {
                // Handle the exception silently
            }
            System.out.println("\n'" + name + "' by " + nodePtr.getData().getArtist() + " is now playing.\n");
            return;
        }
        System.out.println("\n'" + name + "' not found.\n");
    }

    /**
     * Moves the cursor forward to the next song in the playlist, if possible.
     * If the cursor is at the end of the playlist, it remains at the end.
     */
    public void cursorFor() {
        if (cursor.getNext() != null) {
            cursor = cursor.getNext();
            System.out.println("\nCursor moved to the next song.\n");
        } else {
            System.out.println("\nAlready at the end of the playlist.\n");
        }
    }

    /**
     * Moves the cursor backward to the previous song in the playlist, if possible.
     * If the cursor is at the beginning, it remains at the start.
     */
    public void cursorBack() {
        if (cursor.getPrev() != null) {
            cursor = cursor.getPrev();
            System.out.println("\nCursor moved to the previous song.\n");
        } else {
            System.out.println("\nAlready at the beginning of the playlist.\n");
        }
    }

    /**
     * Inserts a new song into the playlist after the current cursor position.
     * If the playlist is empty, the new song becomes the first and last song.
     *
     * @param newSong the new song to be inserted into the playlist
     */
    public void insertAfterCursor(Song newSong) {
        SongNode song = new SongNode(null, null, newSong);
        if (size == 0) {
            setHead(song);
            setCursor(song);
            setTail(song);
            size++;
        } else {
            SongNode temp = cursor.getNext();
            cursor.setNext(song);
            song.setPrev(cursor);
            cursor = cursor.getNext();
            song.setNext(temp);
            if (temp != null) {
                temp.setPrev(song);
            } else {
                setTail(song);
            }
            size++;
        }
    }

    /**
     * Removes the song at the cursor position from the playlist and returns it.
     * If the cursor is the only song in the playlist, the list becomes empty.
     *
     * @return the song that was removed from the playlist
     */
    public Song removeCursor() {
        Song removed = cursor.getData();
        if (size == 1) {
            head = null;
            cursor = null;
            tail = null;
        } else {
            if (cursor.getNext() == null) {
                cursor = cursor.getPrev();
                cursor.setNext(null);
                tail = cursor;
            } else {
                cursor = cursor.getNext();
                if (cursor.getPrev().getPrev() == null) {
                    head = cursor;
                    cursor.setPrev(null);
                } else {
                    SongNode temp = cursor.getPrev();
                    cursor.setPrev(temp.getPrev());
                    temp.getPrev().setNext(cursor);
                }
            }
        }
        size--;
        return removed;
    }

    /**
     * Shuffles the playlist randomly and rearranges the songs.
     */
    public void shuffle() {
        Song[] songs = new Song[size];
        cursor = head;
        for (int i = 0; i < size; i++) {
            songs[i] = cursor.getData();
            if (i != size - 1) {
                cursor = cursor.getNext();
            }
        }
        head = null;
        cursor = null;
        tail = null;
        size = 0;
        for (int i = 0; i < songs.length; i++) {
            insertAfterCursor(songs[(int) (Math.random() * songs.length)]);
        }
    }


    /**
     * Returns a random song from the playlist and plays it. The cursor is moved to the random song.
     * If the playlist is empty, returns null.
     *
     * @return a random song from the playlist, or null if the playlist is empty
     */
    public Song random() {
        if (cursor != null) {
            SongNode temp = cursor;
            cursor = head;
            for (int i = 0; i < randomHelper(size); i++) {
                cursor = cursor.getNext();
            }
            try {
                AudioInputStream AIS = AudioSystem.getAudioInputStream(new File(cursor.getData().getName() + ".wav"));
                Clip c = AudioSystem.getClip();
                c.open(AIS);
                c.start();
            } catch (Exception ex) {
                // Handle the exception silently
            }
            Song s = cursor.getData();
            cursor = temp;
            return s;
        }
        return null;
    }

    /**
     * Clears all songs from the playlist and resets the cursor, head, and tail.
     */
    public void deleteAll() {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    /**
     * Returns a string representation of the playlist. Displays each song in the playlist with its details
     * and indicates the position of the cursor.
     *
     * @return a formatted string of the playlist
     */
    @Override
    public String toString() {
        String result = "\nPlaylist:\n" +
                "Song                   | Artist                   | Album                 | Length (s)\n" +
                "-------------------------------------------------------------------------------------------";
        SongNode temp = cursor;
        cursor = head;
        for (int i = 0; i < size; i++) {
            result += "\n" + cursor.getData().toString();
            if (cursor == temp) {
                result += "<-";
            }
            if (i < size - 1) {
                cursor = cursor.getNext();
            }
        }
        cursor = temp;
        result += "\n";
        return result;
    }

    /**
     * Helper method to generate a random integer between 0 (inclusive) and the given size (exclusive).
     *
     * @param size the upper bound (exclusive) for the random integer
     * @return a random integer between 0 and size - 1
     */
    public int randomHelper(int size) {
        return (int) (Math.random() * size);
    }

    /**
     * Returns the head node of the playlist.
     *
     * @return the head node
     */
    public SongNode getHead() {
        return head;
    }

    /**
     * Sets the head node of the playlist.
     *
     * @param head the new head node
     */
    public void setHead(SongNode head) {
        this.head = head;
    }

    /**
     * Returns the tail node of the playlist.
     *
     * @return the tail node
     */
    public SongNode getTail() {
        return tail;
    }

    /**
     * Sets the tail node of the playlist.
     *
     * @param tail the new tail node
     */
    public void setTail(SongNode tail) {
        this.tail = tail;
    }

    /**
     * Returns the current cursor node in the playlist.
     *
     * @return the cursor node
     */
    public SongNode getCursor() {
        return cursor;
    }

    /**
     * Sets the cursor to the specified node.
     *
     * @param cursor the new cursor node
     */
    public void setCursor(SongNode cursor) {
        if (cursor == null) {
            if (this.cursor != null) {
                this.cursor.setData(null);
                this.cursor.setNext(null);
                this.cursor.setPrev(null);
            }
            this.cursor = null;
        } else {
            this.cursor = cursor;
        }
    }

    /**
     * Returns the number of songs in the playlist.
     *
     * @return the size of the playlist
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the number of songs in the playlist.
     *
     * @param size the new size of the playlist
     */
    public void setSize(int size) {
        this.size = size;
    }
}
