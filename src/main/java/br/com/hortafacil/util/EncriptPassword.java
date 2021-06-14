package br.com.hortafacil.util;

import org.apache.commons.codec.binary.Base64;

public class EncriptPassword {

  public static String codify(String password) {
    return new Base64().encodeToString(password.getBytes());
  }

  public static String decode(String password) {
    return new String(new Base64().decode(password));
  }

  public static boolean compare(String password, String passwordEncripted) {
    String passEncript = new Base64().encodeToString(password.getBytes());
    return passEncript.equals(passwordEncripted);
  }
}
