/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.user_management.registration;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Asus
 */
public class PasswordEncryptionAndDecryption {

    private static final String UNICODE_FORMAT = "UTF-8";
    String EncryptionType = "AES";

    private static final byte[] KEY_DATA = {
        (byte) 0x00, (byte) 0x01, (byte) 0x02, (byte) 0x03,
        (byte) 0x04, (byte) 0x05, (byte) 0x06, (byte) 0x07,
        (byte) 0x08, (byte) 0x09, (byte) 0x0A, (byte) 0x0B,
        (byte) 0x0C, (byte) 0x0D, (byte) 0x0E, (byte) 0x0F,};

    public String encryptPassword(String PasswordToEncrypt) throws UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {

        Cipher ciphertext;
        ciphertext = Cipher.getInstance(EncryptionType);

        SecretKey Ekey = new SecretKeySpec(KEY_DATA, EncryptionType);

        byte[] BeforeEncryption = PasswordToEncrypt.getBytes(UNICODE_FORMAT);
        ciphertext.init(Cipher.ENCRYPT_MODE, Ekey);
        byte[] AfterEncryption = ciphertext.doFinal(BeforeEncryption);

        String EncryptedPassword = new String(AfterEncryption);

        return EncryptedPassword;
    }

    public String decryptPassword(String PasswordToDecrypt) throws UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {

        Cipher ciphertext;
        ciphertext = Cipher.getInstance(EncryptionType);

        SecretKey Dkey = new SecretKeySpec(KEY_DATA, EncryptionType);

        byte[] BeforeDecryption = PasswordToDecrypt.getBytes(UNICODE_FORMAT);
        ciphertext.init(Cipher.DECRYPT_MODE, Dkey);
        byte[] AfterDecryption = ciphertext.doFinal(BeforeDecryption);

        String DecryptedPassword = new String(AfterDecryption);

        return DecryptedPassword;
    }
}
