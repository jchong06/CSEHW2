import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;

public class SongLinkedList {
    private SongNode head;
    private SongNode tail;
    private SongNode cursor;
    private int size;

    public SongLinkedList() {
        head = null;
        tail = null;
        cursor = null;
        size = 0;
    }

    public void play(String name) throws IllegalArgumentException {
        SongNode nodePtr = head;
        while (nodePtr.getNext() != null){
            if (nodePtr.getData().getName().equals(name)){
                setCursor(nodePtr);
                try {
                    AudioInputStream AIS = AudioSystem.getAudioInputStream(
                            new File(name + ".wav"));
                    Clip c = AudioSystem.getClip();
                    c.open(AIS);
                    c.start();
                    System.out.println("'" + name + "' by " + nodePtr.getData().getArtist() + " is now playing.");
                }
                catch (Exception ex) {}
                break;
            }
            nodePtr = nodePtr.getNext();
        }
        if (nodePtr.getData().getName().equals(name)) {
            setCursor(nodePtr);
            try {
                AudioInputStream AIS = AudioSystem.getAudioInputStream(
                        new File(name + ".wav"));
                Clip c = AudioSystem.getClip();
                c.open(AIS);
                c.start();
                System.out.println("'" + name + "' by " + nodePtr.getData().getArtist() + " is now playing.");
            } catch (Exception ex) {
            }
            return;
        }
        System.out.println("\n'" + name + "'" + " not found.\n");
    }

    public void cursorForwards(){
        cursor = cursor.getNext();
        cursor.setNext(cursor.getNext());
        cursor.setPrev(cursor.getPrev());
    }

    public void cursorFor(){
        if (cursor.getNext() != null){
            cursor = cursor.getNext();
            cursor.setNext(cursor.getNext());
            cursor.setPrev(cursor.getPrev());
            System.out.println("\nCursor moved to the next song.\n");
        }
        else{
            System.out.println("\nAlready at the end of the playlist.\n");
        }
    }

    public void cursorBackwards(){
        cursor = cursor.getPrev();
    }

    public void cursorBack(){
        if (cursor.getPrev() != null){
            cursor = cursor.getPrev();
            System.out.println("\nCursor moved to the previous song.\n");
        }
        else{
            System.out.println("\nAlready at the beginning of the playlist.\n");
        }
    }

    public void insertAfterCursor(Song newSong){
        SongNode song = new SongNode(null, null, newSong);
        if (size == 0){
            setHead(song);
            setCursor(song);
            setTail(song);
            size++;
        }
        else{
            SongNode temp = cursor.getNext();
            cursor.setNext(song);
            song.setPrev(cursor);
            cursorForwards();
            song.setNext(temp);
            cursor.setNext(temp);
            if (temp != null){
                cursorForwards();
                cursor.setPrev(song);
                cursorBackwards();
            }
            else{
                setTail(cursor);
            }
            size ++;
        }

    }

    public Song removeCursor(){
        Song removed = cursor.getData();
        boolean done = false;
        if (size == 1){
            setHead(null);
            setCursor(null);
            setTail(null);
        }
        else{
            if (cursor.getNext() == null){
                cursorBackwards();
                cursor.setNext(null);
                setTail(cursor);
                done = true;
            }
            else{
                cursorForwards();
            }
            if ((cursor.getPrev().getPrev() == null) && (!done)){
                cursor.setPrev(null);
                setHead(cursor);
                done = true;
            }
            if (!done){
                cursorBackwards();
                SongNode temp = cursor.getNext();
                cursorBackwards();
                cursor.setNext(temp);
                temp = cursor;
                cursorForwards();
                cursor.setPrev(temp);
            }
        }
        size--;
        return removed;
    }

    public Song random(){
        cursor = head;
        for (int i = 0; i < randomHelper(); i++){
            cursorForwards();
        }
        try {
            AudioInputStream AIS = AudioSystem.getAudioInputStream(
                    new File(cursor.getData().getName() + ".wav"));
            Clip c = AudioSystem.getClip();
            c.open(AIS);
            c.start();
        }
        catch (Exception ex) {}
        return cursor.getData();
    }

    public void deleteAll(){
        setCursor(null);
        head = cursor;
        tail = cursor;
        size = 0;
    }

    @Override
    public String toString() {
        String result = "\nPlaylist:\n" +
                "Song                   | Artist                   | Album                 | Length (s)\n" +
                "-------------------------------------------------------------------------------------------";
        SongNode temp = cursor;
        cursor = head;
        for (int i = 0; i < size; i++){
            result += "\n" + cursor.getData().toString();
            if (cursor == temp){
                result += "<-";
            }
            if (i < size - 1){
                cursorForwards();
            }
        }
        cursor = temp;
        result += "\n";
        return result;
    }

    public int randomHelper(){
        return (int) (Math.random() * size) + 1;
    }


    public SongNode getHead() {
        return head;
    }

    public void setHead(SongNode head) {
        this.head = head;
    }

    public SongNode getTail() {
        return tail;
    }

    public void setTail(SongNode tail) {
        this.tail = tail;
    }

    public SongNode getCursor() {
        return cursor;
    }

    public void setCursor(SongNode cursor) {
        if (cursor == null){
            this.cursor.setData(null);
            this.cursor.setNext(null);
            this.cursor.setPrev(null);
        }
        else{
            this.cursor = cursor;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
