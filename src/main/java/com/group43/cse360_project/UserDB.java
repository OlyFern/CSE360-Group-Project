// General methods for all User types and Users Database interaction
// -----------------------------------------------------------------
// Reads from "users.db" file
//   - Format: username:password:privilege

package com.group43.cse360_project;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
  
  private static String userTypeToString(UserType type) {
    switch (type) {
      case ADMIN:  return "A";
      case BUYER:  return "B";
      case SELLER: return "S";
      default:     return "R";
    }
  }

  public static enum UserType {
    ADMIN,
    BUYER,
    SELLER,
    RESTRICTED
  }

  public static boolean validateLogin(String name, String passwd) throws IOException {
    var reader = new BufferedReader(new FileReader("/database/users.db"));
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

  public static void addNewUser(String name, String passwd, UserType type) throws IOException {
    var writer = new BufferedWriter(new FileWriter("users.db"));
    writer.write(name + ":" + hashPassword(passwd) + ":" + userTypeToString(type) + "\n");
    writer.close();
  }
  
}
