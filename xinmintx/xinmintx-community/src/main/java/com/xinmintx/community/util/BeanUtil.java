package com.xinmintx.community.util;

import cn.hutool.core.lang.Assert;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by zhangliang on 2019/10/10
 */

public class BeanUtil {

    public static <T> T copyProperties(Object source,Class clazz) {
        T target = null;
        try {
            target = (T) clazz.newInstance();
            cn.hutool.core.bean.BeanUtil.copyProperties(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return target;
    }
    public static <T> T copyProperties(Object source,Class clazz,boolean isCreate) {
        T target =  copyProperties(source,clazz);
        if (isCreate) {
            if(checkClassFieldNameExists(clazz,"createTime")){
                cn.hutool.core.bean.BeanUtil.setFieldValue(target, "createTime", new Date());
            }

        } else {
//            if(checkClassFieldNameExists(clazz,"updateUser")){
//                cn.hutool.core.bean.BeanUtil.setFieldValue(target, "updateUser", id);
//            }
            if(checkClassFieldNameExists(clazz,"updateTime")){
                cn.hutool.core.bean.BeanUtil.setFieldValue(target, "updateTime", new Date());
            }
        }

        return target;
    }

    /**
     * 判断类属性名是否存在
     *
     * @param pclass
     * @return
     * @throws Throwable
     */
    public static Boolean checkClassFieldNameExists(Class<?> pclass, String checkFiledName)  {
        if (pclass != null && checkFiledName != null) {
            Field[] propertyField = pclass.getDeclaredFields();
            for (int i = 0, len = propertyField.length; i < len; i++) {
                if (checkFiledName.equalsIgnoreCase(propertyField[i].getName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
