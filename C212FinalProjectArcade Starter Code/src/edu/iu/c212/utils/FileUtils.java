package edu.iu.c212.utils;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FileUtils {

    //Kyle Liss

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
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < users.size(); i++) {
                String userInfo = users.get(i).getUsername() + " | " + users.get(i).getBalance() + " | " + users.get(i).getInventory();
                String toWrite = i == 0 ? userInfo : "\r\n" + userInfo;
                bw.write(toWrite);
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
        if (!file.exists()) {
            file.createNewFile();
        }

        String line;
        List<User> users = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("users.txt"));
        while ((line = br.readLine()) != null){
            String[] info = line.split("\\|");

            String name = info[0].trim();
            double bal = Double.parseDouble(info[1].trim());

            String inventoryArray = info[2].trim().replaceAll("\\[", "").replaceAll("]", "");
            String[] itemInfo = inventoryArray.split(",");

            List<Item> fin = new ArrayList<>();

            for (String s : itemInfo) {
                String[] itemInfoDiv = s.split("\\(");
                if (itemInfoDiv.length < 2) continue;

                String itemName = itemInfoDiv[1].split("\\)")[0].trim();
                switch (itemName) {
                    case "Candy" -> fin.add(Item.CANDY);
                    case "Nintendo Switch" -> fin.add(Item.NINTENDOSWITCH);
                    case "Yoyo" -> fin.add(Item.YOYO);
                    case "Nerf Gun" -> fin.add(Item.NERFGUN);
                    case "Four Wheeler" -> fin.add(Item.FOURWHEELER);
                }
            }

            users.add(new User(name, bal, fin));
        }
        return users;
    }
}
