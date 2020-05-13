package com.xinmintx.agent.common.parity;

import java.math.BigDecimal;
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
    // 去掉小数点后面的0
    public static String getPrettyNumber(BigDecimal totalMoney) {
        String fee = "0";
        if (totalMoney != null) {
            fee = BigDecimal.valueOf(Double.parseDouble(totalMoney.multiply(new BigDecimal("100")).toString())).stripTrailingZeros().toPlainString();
        }
        return fee;
    }
}
