package com.xinmintx.agent.util;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: create by wcj
 * @date: 2019/12/23 0023
 * @time: 下午 15:28
 * @Description:
 */
public class FieldUtils {

    /**
     * 验证对象的属性是否有为空
     *
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value == null) {
                    return true;
                } else {
                    if (StringUtils.isBlank(value.toString())) {
                        return true;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 验证对象的属性是否全部不为空
     *
     * @param obj
     * @return
     */
    public static boolean isNotEmpty(Object obj) {
        if (obj == null) {
            return false;
        }
        return !isEmpty(obj);
    }

    /**
     * 把一个对象的属性赋值给指定的class类型
     * @param v
     * @param clazz
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> T fieldTrans(V v, Class<T> clazz) {
        List<String> childName = getFieldName(clazz);
        List<String> superName = getFieldName(clazz.getSuperclass());
        try {
            Field[] fields = v.getClass().getDeclaredFields();
            T t = clazz.newInstance();
            for (Field field : fields) {
                field.setAccessible(true);
                if (Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                String name = field.getName();
                Object value = field.get(v);
                //判断当前对象属性是否存在
                if (childName.contains(name)) {
                    Field declaredField = t.getClass().getDeclaredField(name);
                    declaredField.setAccessible(true);
                    declaredField.set(t, value);
                    //当前对象不存在,判断他的父类是否有该属性
                } else if (superName.contains(name)) {
                    Field declaredField = t.getClass().getSuperclass().getDeclaredField(name);
                    declaredField.setAccessible(true);
                    declaredField.set(t, value);
                }
            }
            return t;
        } catch (IllegalAccessException | InstantiationException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把一个对象的属性赋值给另一个对象
     * @param t1
     * @param t2
     * @param <T>
     * @return
     */
    public static <T> T fieldTrans(T t1, T t2) {
        try {
            Field[] fields = t1.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (Modifier.isFinal(field.getModifiers())) {
                    continue;
                }
                String name = field.getName();
                Object value = field.get(t1);
                if (value == null){
                    continue;
                }
                Field declaredField = t2.getClass().getDeclaredField(name);
                declaredField.setAccessible(true);
                declaredField.set(t2, value);
            }
            return t2;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取该对象的属性名集合
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<String> getFieldName(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> fieldName = new ArrayList<>();
        for (Field field : fields) {
            fieldName.add(field.getName());
        }
        return fieldName;
    }
}
