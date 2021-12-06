package edu.iu.c212.utils;

import edu.iu.c212.models.Item;
import edu.iu.c212.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        List<User> users = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("products.csv"));
        while ((line = br.readLine()) != null){

            String[] info = line.split(splitter);

            String name = info[0];
            double bal = Double.parseDouble(info[1]);

            String[] itemInfo = info[2].split(splitter2);

            List<Item> fin = new ArrayList<>();

            for(int i = 0; i < itemInfo.length; i++){
                String[] itemInfoDiv = info[i].split("\\(");
                String name2 = itemInfoDiv[1];
                if(name2.equals("Candy")){
                    fin.add(Item.CANDY);
                }
                else if(name2.equals("Nintendo Switch")){
                    fin.add(Item.NINTENDOSWITCH);
                }
                else if(name2.equals("Yoyo")){
                    fin.add(Item.YOYO);
                }
                else if(name2.equals("Nerf Gun")){
                    fin.add(Item.NERFGUN);
                }
                else if(name2.equals("Four Wheeler")){
                    fin.add(Item.FOURWHEELER);
                }

            }

            users.add(new User(name, bal, fin));




        }
        return users;
    }
}
