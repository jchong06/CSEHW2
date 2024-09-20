/**
 * @author Justin Chong
 * Email: justin.chong@stonybrook.edu
 * Student ID: 116143020
 * Recitation Number: CSE 214 R03
 * TA: Veronica Oreshko
 */
/**
 * The SongNode class represents a node in a doubly linked list used to store songs in a playlist.
 * Each node contains a reference to the previous node, the next node, and the song data.
 *
 * <p>
 * This class is used internally by the SongLinkedList class to manage the playlist.
 * </p>
 */
public class SongNode {
    private SongNode prev; // The previous node in the linked list
    private SongNode next; // The next node in the linked list
    private Song data;     // The song data contained in this node

    /**
     * Constructs a SongNode with the specified previous node, next node, and song data.
     *
     * @param prev the previous node in the linked list
     * @param next the next node in the linked list
     * @param data the song data for this node
     */
    public SongNode(SongNode prev, SongNode next, Song data) {
        this.prev = prev;
        this.next = next;
        this.data = data;
    }

    /**
     * Returns the previous node in the linked list.
     *
     * @return the previous node
     */
    public SongNode getPrev() {
        return prev;
    }

    /**
     * Sets the previous node in the linked list.
     *
     * @param prev the new previous node
     */
    public void setPrev(SongNode prev) {
        this.prev = prev;
    }

    /**
     * Returns the next node in the linked list.
     *
     * @return the next node
     */
    public SongNode getNext() {
        return next;
    }

    /**
     * Sets the next node in the linked list.
     *
     * @param next the new next node
     */
    public void setNext(SongNode next) {
        this.next = next;
    }

    /**
     * Returns the song data contained in this node.
     *
     * @return the song data
     */
    public Song getData() {
        return data;
    }

    /**
     * Sets the song data for this node.
     *
     * @param data the new song data
     */
    public void setData(Song data) {
        this.data = data;
    }
}
