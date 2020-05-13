package com.xinmintx.community.util.httpclient;

import java.util.*;
import java.util.Map.Entry;

public class MapUtil implements Comparator<String>{
	/**
	 * 
	 * Description: 替换map里的null为""
	 * @date 2017-5-11上午7:12:04
	 * @author baixs
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String, Object> mapRemoveWithNullByRecursion(Map<String, Object> map){
		Set<Entry<String, Object>> set = map.entrySet();
		Iterator<Entry<String, Object>> it = set.iterator();
		Map map2 = new HashMap();
		while(it.hasNext()){
			Entry<String, Object> en = it.next();
			if(!(en.getValue() instanceof Map)){
				if(null == en.getValue()||"null".equals(en.getValue())){
				en.setValue("");
				}
			}else{
				map2 = (Map) en.getValue();
				mapRemoveWithNullByRecursion(map2);
			}
		}
		return map;
	}

	@Override
    public int compare(String str1, String str2) {
		//KEY正序
        return str1.compareTo(str2);
    }

	/**
	 * MAP按KEY排序
	 * @param resultMap
	 * @return
	 */
	public static Map sortMapByKey(Map resultMap) {
        if (resultMap == null || resultMap.isEmpty()) {
            return null;
        }
        Map sortMap = new TreeMap(new MapUtil());
        sortMap.putAll(resultMap);

        return sortMap;
    }
}
