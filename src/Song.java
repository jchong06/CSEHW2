/**
 * @author Justin Chong
 * Email: justin.chong@stonybrook.edu
 * Student ID: 116143020
 * Recitation Number: CSE 214 R03
 * TA: Veronica Oreshko
 */

/**
 * The Song class represents a song with details such as the song's name,
 * artist, album, and length in seconds.
 *
 * <p>
 * This class allows for the creation of Song objects and provides methods
 * to retrieve and modify the song's information. It also includes a
 * formatted string representation of the song, which organizes the name,
 * artist, album, and length in a fixed format for display purposes.
 * </p>
 *
 * <p>
 * Fields:
 * <ul>
 * <li>name: The title of the song.</li>
 * <li>artist: The artist(s) who performed the song.</li>
 * <li>album: The album in which the song is included.</li>
 * <li>length: The duration of the song in seconds.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The class includes getter and setter methods for each field, as well as
 * a custom toString method that formats the song information into a fixed-length
 * display with columns for the song's name, artist, album, and length.
 * </p>
 */
public class Song {

    private String name;
    private String artist;
    private String album;
    private int length;

    /**
     * Constructs a new Song object with the specified name, artist, album, and length.
     *
     * @param name the title of the song
     * @param artist the artist(s) who performed the song
     * @param album the album in which the song is included
     * @param length the length of the song in seconds
     */
    public Song(String name, String artist, String album, int length) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }

    /**
     * Returns the name of the song.
     *
     * @return the name of the song
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the song.
     *
     * @param name the name of the song
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the artist of the song.
     *
     * @return the artist of the song
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Sets the artist of the song.
     *
     * @param artist the artist of the song
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Returns the album of the song.
     *
     * @return the album of the song
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Sets the album of the song.
     *
     * @param album the album of the song
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Returns the length of the song in seconds.
     *
     * @return the length of the song in seconds
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the length of the song in seconds.
     *
     * @param length the length of the song in seconds
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Returns a formatted string representing the song with fixed spacing.
     * The format aligns the name, artist, album, and length in columns.
     *
     * @return a formatted string of the song's details
     */
    public String toString() {
        String result = "";
        for (int i = 0; i < 87; i++) {
            if (i == 0) {
                result += name;
                i += name.length();
            } else if (i == 26) {
                result += artist;
                i += artist.length();
            } else if (i == 54) {
                result += album;
                i += album.length();
            } else if (i == 80) {
                result += length;
                i += (length + "").length();
            } else {
                result += " ";
            }
        }
        return result;
    }
}
