package o2oboot.util;



import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class DESUtil {

    private static Key key;

    private static String KEY_STR="myKey";
    private static String CHARSETNAME="UTF-8";
    private static String ALGORITHM="DES";

    static {

        try {
            KeyGenerator generator=KeyGenerator.getInstance(ALGORITHM);
            SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEY_STR.getBytes());
            generator.init(secureRandom);
            key=generator.generateKey();
            generator=null;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }


    public static String getEncryptString(String str){
        Base64 b=new Base64();

        try{
            byte[] bytes=str.getBytes(CHARSETNAME);

            Cipher cipher=Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] dofinal=cipher.doFinal(bytes);


            return new String( b.encode(dofinal));
//            return new String( dofinal);

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getDecryptString(String str){
        Base64 b=new Base64();
        byte[] bytes=str.getBytes();
        bytes=b.decode(bytes);
//        bytes=b.decode(str);
        try {
            Cipher cipher=Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] dofinal=cipher.doFinal(bytes);
            return new String(dofinal,CHARSETNAME);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }


    }


}
