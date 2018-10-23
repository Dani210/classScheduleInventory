package cryptStation;

public class Cryptor {

    public static char[] enrypt(char[] plainPassword) {
        if (plainPassword != null) {
            if (plainPassword.length > 0) {
                char[] encrypted = new char [plainPassword.length];
                for (int i = 0; i < plainPassword.length - 1; i++) {
                    encrypted[i] = (char) ((double) plainPassword[i]
                            * Math.sin((double) plainPassword[i]));
                }
                return encrypted;
            }
        }
        return new char[10];
    }

    public static String decrypt(char[] encryptedPass) {
        if (encryptedPass != null) {
            if (encryptedPass.length > 0) {
                String plain = "";
                for (int i = 0; i < encryptedPass.length - 1; i++) {
                    plain += (char) ((double) encryptedPass[i])
                            / Math.sin((double) encryptedPass[i]);
                }
                return plain;
            }

        }
        return new String("Password");
    }
}
