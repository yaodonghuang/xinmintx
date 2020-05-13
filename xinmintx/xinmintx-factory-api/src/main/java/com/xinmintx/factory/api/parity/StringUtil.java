package com.xinmintx.factory.api.parity;

import com.xinmintx.factory.api.util.ConfigInfo;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isEmpty(String str){
		if(null == str || "".equals(str) || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	public static boolean isEmail(String email){
		if(isEmpty(email))return false;
		boolean bool = true;
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if(!matcher.matches()){
			bool = false;
		}
		return bool;
	}
	
	public static boolean isNumeric(String number){
		if(number.matches("//d*")){
			return true;
		}else{
			return false;
		}
	}
	
	 /**
     * 过滤参数
     * @author  
     * @param sArray
     * @return
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("signature")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }
    
    /**
     * @author 
     * @param payParams
     * @return
     */
    public static String payParamsToString(StringBuilder sb,Map<String, String> payParams,boolean encoding){
        buildPayParams(sb,payParams,encoding);
        return sb.toString();
    }
    
    /**
     * @author 
     * @param payParams
     * @return
     */
    public static void buildPayParams(StringBuilder sb,Map<String, String> payParams,boolean encoding){
        List<String> keys = new ArrayList<String>();
        Set set = payParams.keySet();
        Iterator it = set.iterator();
        while(it.hasNext()){
        	keys.add((String)it.next());
        }
        if(keys.size()>0){
        	 Collections.sort(keys);
             for(String key : keys){
                 sb.append(key).append("=");
                 if(encoding){
                     sb.append(payParams.get(key));
                 }else{
                     sb.append(payParams.get(key));
                 }
                 sb.append("&");
             }
             sb.setLength(sb.length() - 1);
        }
       
    }
    
    /**
     * 商户
     * @param aesStr
     * @param pubFile
     * @return
     */
    public static String encryptWarrantr(String aesStr,String pubFile){
    	CertificateFactory certificateFactory;
		try {
			certificateFactory = CertificateFactory.getInstance("X.509");
			FileInputStream inputStream = new FileInputStream(new File(pubFile));
	    	Certificate cert = certificateFactory.generateCertificate(inputStream);
	    	
	        PublicKey publicKey = cert.getPublicKey();
	        // 对数据加密
	        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
	        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
	        byte[] keyByte = cipher.doFinal(aesStr.getBytes());
	        return Base64.encodeBase64String(keyByte);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    public static String encryptWarrantr(String aesStr){
    	String encryptWarrantr = encryptWarrantr(aesStr, ConfigInfo.getSecretKeyFile());
    	Pattern p = Pattern.compile("\\s*|\t|\r|\n");  
        Matcher m = p.matcher(encryptWarrantr);  
        return m.replaceAll("");
    }
}
