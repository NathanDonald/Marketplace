package Application.Screens.UserScreen;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordEncoder {

    public static String generateSalt(){
        //Create salt
        byte[] salt = new byte[20];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        String saltString = Base64.getEncoder().encodeToString(salt);
        return saltString;

    }
    public static String[] saltAndHash(String input) throws NoSuchAlgorithmException
    {

        String saltAsString = generateSalt();

        String hashedPassword = passSalt(input, saltAsString);


        return new String[]{hashedPassword, saltAsString};
    }
    public static String passSalt(String input, String stringSalt) throws NoSuchAlgorithmException {

        System.out.println(stringSalt);
        //Create salt
        byte[] salt = Base64.getDecoder().decode(stringSalt);

        //Combine salt and password
        String algorithm = "SHA-256";
        MessageDigest data = MessageDigest.getInstance(algorithm);
        data.reset();
        data.update(input.getBytes());
        data.update(salt);

        //Hash the password, encode in base 64 string and put into array.
        byte[] hashed = data.digest();
        String hashedPassword = Base64.getEncoder().encodeToString(hashed);
        String output = hashedPassword;

        System.out.println(hashedPassword);

        return output;
    }
}
