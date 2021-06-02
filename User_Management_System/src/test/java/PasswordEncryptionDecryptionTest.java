
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.user_management.registration.PasswordEncryptionAndDecryption;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author gamanaryal
 */
public class PasswordEncryptionDecryptionTest {

    PasswordEncryptionAndDecryption pead = new PasswordEncryptionAndDecryption();

    @Before
    public void setUp() {
        System.out.println("* PasswordEncryptionAndDecryption: Before method setUp()");
    }

    @After
    public void tearDown() {
        System.out.println("* PasswordEncryptionAndDecryption: After method tearDown()");
    }

    /**
     * unit test for formMessage method.
     */
    @Test
    public void testEncryptAndDecryptPasswordForTrueResult() {
        try {

            System.out.println("Encryption and Decryption test.");
            String PasswordToEncrypt = "Password@#$%7";
            String EncryptedPassword = pead.encryptPassword(PasswordToEncrypt);
            String DecryptedPassword = pead.decryptPassword(EncryptedPassword);
            
            String ResultFromTest = DecryptedPassword;
            String ExpectedResult = "Password@#$%7";
            
            assertEquals(ExpectedResult, ResultFromTest);

        } catch (UnsupportedEncodingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(PasswordEncryptionDecryptionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void testEncryptAndDecryptPasswordForFalseResult() {
        try {

            String PasswordToEncrypt = "Password@#$%7";
            String EncryptedPassword;
            EncryptedPassword = pead.encryptPassword(PasswordToEncrypt);
            String DecryptedPassword = pead.decryptPassword(EncryptedPassword);
            
            String ResultFromTest = DecryptedPassword;
            System.out.println("Encryption and Decryption test.");
            String ExpectedResult = "Password@#$%";
            
            assertEquals(ExpectedResult, ResultFromTest);

        } catch (UnsupportedEncodingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException ex) {
            Logger.getLogger(PasswordEncryptionDecryptionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
