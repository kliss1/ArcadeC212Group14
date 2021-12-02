package edu.iu.c212.utils;

import edu.iu.c212.models.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
    }
}
