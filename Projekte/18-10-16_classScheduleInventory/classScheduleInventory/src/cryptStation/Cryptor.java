package cryptStation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Cryptor {
    
    private static final int DEFAULT_HASH_LENGTH = 256;
    
    
    //256 Zeichen groß (immer)
    private static char [] temporalKey;

    public static char[] enrypt(char[] plainPassword) {
        if(Cryptor.temporalKey == null){
            //createKey
            createKey(DEFAULT_HASH_LENGTH);
        }
        else{
            //encrypt with key
        }
        
        return new char[10];
    }

    public static String decrypt(char[] encryptedPass) {
        //decrypt with key
        return "";
    }
    
    private static void createKey(int hashLength){
        StringBuilder hashD = new StringBuilder("" + LocalDate.now().hashCode());
        StringBuilder hashT = new StringBuilder("" + LocalTime.now().hashCode());
        Random randy = new Random();
        
        StringBuilder finalHash = new StringBuilder();
        
        //hashD -> leading zeroes
        //offset == Stelle an der eingefügt wird
        while(hashD.length() < hashLength){
            //offset == Stelle an der eingefügt wird
            hashD = hashD.insert(0, (char) randy.nextInt());
        }
        
        //hashT -> trailing zeroes
        while(hashT.length() < hashLength){
            //offset == Stelle an der eingefügt wird
            hashT = hashT.insert(hashT.length(), (char) randy.nextInt());
        }
        
        
        //anded -> hashD & hashT
        for(int i = 0; i < hashLength; i++){
            finalHash.append((char)
                    (((int)hashD.charAt(i)) ^ ((int)hashT.charAt(i)))
            );
        }
    }
    
    public static void main(String[] args) {
        createKey(DEFAULT_HASH_LENGTH);
    }
    
}
