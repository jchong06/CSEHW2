public class SongNode {
    private SongNode prev;
    private SongNode next;
    private Song data;

    public SongNode(SongNode prev, SongNode next, Song data) {
        this.prev = prev;
        this.next = next;
        this.data = data;
    }

    public SongNode getPrev() {
        return prev;
    }

    public void setPrev(SongNode prev) {
        this.prev = prev;
    }

    public SongNode getNext() {
        return next;
    }

    public void setNext(SongNode next) {
        this.next = next;
    }

    public Song getData() {
        return data;
    }

    public void setData(Song data) {
        this.data = data;
    }

    public String toString(){
        String result = "";
        for (int i = 0; i < 91; i++){
            if (i == 0) {
                result += getData().getName();
                i += getData().getName().length();
            }
            else if (i == 26){
                result += getData().getArtist();
                i += getData().getArtist().length();
            }
            else if (i == 54) {
                result += getData().getAlbum();
                i += getData().getAlbum().length();
            }
            else if (i == 80) {
                result += getData().getLength();
                i += (getData().getLength() + "").length();
            }
            else {
                result += (" ");
            }
        }
        return result;
    }
}
