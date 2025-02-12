package com.example.VirtualClassRoom.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class HashingService {
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
}
