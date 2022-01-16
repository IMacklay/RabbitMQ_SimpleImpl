package Model;

import java.io.*;

public class Note implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    String name;
    int h;

    public Note(String name, int h) {
        this.name = name;
        this.h = h;
    }

    public byte[] getBytes(){
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(this);
             return bos.toByteArray();

        }catch (Exception e){
            e.printStackTrace();
        }

        throw new RuntimeException("Can't transfer "+this.getClass().getSimpleName()+" to Bytes");

    }

    public static Note byteToNote(byte[] data){
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
            ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (Note) ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }

        throw new RuntimeException("Can't read "+ Note.class.getSimpleName() +" from Bytes");
    }

    @Override
    public String toString() {
        return "Note{" +
                "name='" + name + '\'' +
                ", h=" + h +
                '}';
    }
}
