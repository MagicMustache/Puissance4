import java.io.*;
import java.util.ArrayList;

public class PersistanceHandler {

    public void writePersistancce(ArrayList<Game> history) {
        String fileName = "history.ser";
        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(history);
            oos.close();
            fos.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    public ArrayList<Game> readPersistance(){
        String fileName = "history.ser";
        ArrayList<Game> history = new ArrayList<>();

        try{
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            history = (ArrayList<Game>) ois.readObject();
            ois.close();
            fis.close();
            return history;


        }catch(IOException | ClassNotFoundException ioe){
            ioe.printStackTrace();
            return history;
        }
    }

}
