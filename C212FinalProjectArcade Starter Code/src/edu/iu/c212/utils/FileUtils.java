package edu.iu.c212.utils;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static File file = new File("users.txt");

    // line format:
    // user_name|balance|item1,item2,item3
    // user name not allowed to contain pipe

    /**
     * Write user data to the file you provided above.
     *
     * @param users The total list of all users
     */
    public static void writeUserDataToFile(List<User> users) throws IOException {
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i < users.size(); i++) {
                String userInfo = users.get(i).getUsername() + " | " + users.get(i).getBalance() + " | " + users.get(i).getInventory();
                bw.write("\r\n" + userInfo);
            }
            bw.close();

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Read user data from the file you provided above. Return a list of Users
     */
    public static List<User> getUserDataFromFile() throws IOException {
        String line = "";
        String splitter = "|";
        String splitter2 = ",";

        BufferedReader br = new BufferedReader(new FileReader("products.csv"));
        while ((line = br.readLine()) != null){
            String[] info = line.split(splitter);
            String name = info[0];
            double bal = Double.parseDouble(info[1]);
            List<Item> t = new ArrayList<>();
            String[] itemInfo = info[2].split(splitter2);
            List<Item> fin = new ArrayList<>();
            for(int i = 0; i < itemInfo.length; i++){
                String[] itemInfoDiv = info[i].split("\\(");
                String name2 = itemInfoDiv[1];
                double val = Double.parseDouble(itemInfoDiv[3]);
                fin.add(new Item(name2, val));
            }


        }
    }
}
