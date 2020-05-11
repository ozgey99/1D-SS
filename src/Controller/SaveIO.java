package Controller;

import java.io.*;

public class SaveIO {
    public static boolean saveObjectToFile(Object o) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File("sts.save"));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(o);
            objectOutputStream.close();
            fileOutputStream.close();
            System.out.println("saved!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Object loadObjectFromFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("sts.save"));
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object o = objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
