package com.community.tsinghua;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by LG on 2015-12-01.
 */
public class pwdMD5 {
    public String pwdMD5(String str){
        String MD5="";
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte byteData[]=md.digest();
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<byteData.length;i++){
                sb.append(Integer.toString((byteData[i]&0xff)+0x100,16).substring(1));
            }
            MD5=sb.toString();

        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            MD5=null;
        }
        return MD5;
    }
}
