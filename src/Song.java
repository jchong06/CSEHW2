public class Song {
    private String name;
    private String artist;
    private String album;
    private int length;

    public Song(String name, String artist, String album, int length) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String toString(){
        String result = "";
        for (int i = 0; i < 91; i++){
            if (i == 0) {
                result += name;
                i +=  name.length();
            }
            else if (i == 26){
                result += artist;
                i += artist.length();
            }
            else if (i == 54) {
                result += album;
                i += album.length();
            }
            else if (i == 80) {
                result += length;
                i += (length + "").length();
            }
            else {
                result += (" ");
            }
        }
        return result;
    }
}
