// General methods for all User types and Users Database interaction
// -----------------------------------------------------------------
// Reads from "users.db" file
//   - Format: asuID:password:privilege:username:email:cart

package com.group43.cse360_project;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.Objects;

import static com.group43.cse360_project.UserType.parseUserTypeDBFlag;

class UserDB {
    private static String hashPassword(String passwd) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(passwd.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(String asuID) throws IOException {
        var reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/users.db"));
        String line;
        while ((line = reader.readLine())!= null) {
            String[] user = line.split(":");
            if (Objects.equals(user[0], asuID)) {
                return new User(
                        asuID,
                        user[1],
                        parseUserTypeDBFlag(user[2]),
                        user[3],
                        user[4]);
            }
        }
        reader.close();
        return null;
    }

    public static boolean validateLogin(String name, String passwd) throws IOException {
        var reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/users.db"));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(name)) {
                String[] user = line.split(":");
                return hashPassword(passwd).compareTo(user[1]) == 0;
            }
        }
        reader.close();
        return false;
    }

    public static void addNewUser(String asuID, String passwd, UserType type, String name, String email) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(
                "src/main/resources/com/group43/cse360_project/users.db",
                true));
        writer.write(asuID + ":" + hashPassword(passwd) + ":" + UserType.getUserTypeDBFlag(type) + ":" +
                name + ":" + email + "\n");
        writer.close();
    }

    // Updates user information (Searches for asuID which will always remain the same)
    public static void updateUser(User user) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/com/group43/cse360_project/users.db"));
        StringBuffer buffer = new StringBuffer();
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.startsWith(user.getAsuID())) {
                String[] fields = line.split(":");
                line = user.getAsuID() + ":" + fields[1] + ":" + UserType.getUserTypeDBFlag(user.getType()) + ":" +
                        user.getName() + ":" + user.getEmail();

                LinkedList<String> cart = user.getCart();
                for (String book : cart)
                    line += ":" + book;
            }

            buffer.append(line);
            buffer.append("\n");
        }

        FileOutputStream out = new FileOutputStream("src/main/resources/com/group43/cse360_project/users.db");
        out.write(buffer.toString().getBytes());
        out.close();
    }
}
