package cryptStation;

import java.security.SecureRandom;

public class Cryptor {

    private static char[] temporalKey;

    public static char[] enrypt(char[] plainPassword) {
        if (Cryptor.temporalKey == null) {
            createKey(plainPassword.length);
        }

        char[] encrypted = new char[plainPassword.length];

        char[] key = Cryptor.temporalKey;

        for (int i = 0; i < encrypted.length; i++) {
            encrypted[i] = (char) ((long) plainPassword[i] ^ (long) key[i]);
        }
        return encrypted;

    }

    public static String decrypt(char[] encryptedPass) {
        //decrypt with key
        char[] plain = new char[encryptedPass.length];

        char[] key = Cryptor.temporalKey;

        for (int i = 0; i < encryptedPass.length; i++) {
            plain[i] = (char) ((long) encryptedPass[i] ^ (long) key[i]);
        }

        String plainPass = "";
        for (int i = 0; i < plain.length; i++) {
            plainPass += plain[i];
        }
        return plainPass;
    }

    private static void createKey(int hashLength) {
        SecureRandom randy = new SecureRandom();
        StringBuilder key = new StringBuilder();

        for (int i = 0; i < hashLength; i++) {
            key.append((char) (randy.nextInt() ^ (randy.nextInt()))
            );
        }

        Cryptor.temporalKey = key.toString().toCharArray();
    }

}
