package com.ender.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ender.util.HttpCode;
import com.ender.util.PropertiesUtil;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ender on 2017/3/13.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity implements Serializable {

    public BaseEntity() {
    }

    public BaseEntity(Object o) {
        setEntity(o);
    }

    public BaseEntity(Object o, int code) {
        setEntity(o);
        setCode(code);
        setMessage(PropertiesUtil.getProperty(code));
    }

    public BaseEntity(Object o, int code, Object... objects) {
        setEntity(o);
        setCode(code);
        if (objects == null || objects.length == 0) {
            setMessage(PropertiesUtil.getProperty(code));
        } else {
            setMessage(MessageFormat.format(PropertiesUtil.getProperty(code), objects));
        }
    }

    private Object entity;

    private int code;

    private String message;

    private String error;


    public static BaseEntity noFound() {
        return new BaseEntity(null, HttpCode.NO_RESOURCE);
    }

    public static BaseEntity getBaseEntity(Object o, int code) {
        return new BaseEntity(o, code);
    }

    public static BaseEntity getBaseEntity(int code) {
        return new BaseEntity(null, code);
    }

    public static BaseEntity getBaseEntity(int code, Object... objects) {
        return new BaseEntity(null, code, objects);
    }


    /**
     * 格式化提示信息，占位符:{索引(从0开始)},如：{0},{1}
     *
     * @param objects 提示信息占位符对应值
     */
    public static BaseEntity getBaseEntity(Object o, int code, Object... objects) {
        return new BaseEntity(o, code, objects);
    }

    public static BaseEntity getBaseEntity(String key, Object val, int code, Object... objects) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, val);
        return new BaseEntity(map, code, objects);
    }


    public static BaseEntity success(Object o) {
        return new BaseEntity(o, HttpCode.SUCCESS);
    }

    public static BaseEntity success(String key, Object val) {
        Map<String, Object> map = new HashMap<>();
        map.put(key, val);
        return new BaseEntity(map, HttpCode.SUCCESS);
    }

    public static BaseEntity success() {
        return new BaseEntity(null, HttpCode.SUCCESS);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
