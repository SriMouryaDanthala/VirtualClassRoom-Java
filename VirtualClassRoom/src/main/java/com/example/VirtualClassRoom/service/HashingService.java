package com.example.VirtualClassRoom.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashingService implements PasswordEncoder {
    public String hash(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return secureHashString(input);
    }

    private String secureHashString(String input) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hashedString = null;
        try{
            hashedString = makeSHA1Hash(input);
        }
        catch (Exception e){
            throw  e;
        }
        return hashedString;
    }

    private String makeSHA1Hash(String input)
            throws NoSuchAlgorithmException, UnsupportedEncodingException
    {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.reset();
        byte[] buffer = input.getBytes("UTF-8");
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return hexStr;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            return makeSHA1Hash(rawPassword.toString());
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        try {
            return makeSHA1Hash(rawPassword.toString()).equals(encodedPassword);
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
