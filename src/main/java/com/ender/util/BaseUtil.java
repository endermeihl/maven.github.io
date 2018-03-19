package com.ender.util;

import com.ender.util.exception.BusinessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 基本工具类
 * 提供了非空判断
 * 默认非空返回
 * Created by ender on 2017/3/14.
 */
public class BaseUtil {

    private static String NUL = "";

    /**
     * 判断对象是否为空或者空字符
     *
     * @param obj
     * @return false 空，true 非空
     */
    public static boolean isValid(Object obj) {
        if (obj == null || obj.toString().trim().equals(NUL)) {
            return false;
        }
        return true;
    }

    public static boolean isEmpty(Object obj) {
        return !isValid(obj);
    }

    /**
     * 返回非空字符串
     *
     * @param str
     * @return str为空返回NUL
     */
    public static String dealString(Object str) {
        if (!isValid(str)) {
            return NUL;
        } else {
            return str.toString();
        }
    }

    /**
     * 返回非空字符串
     * 设置默认值
     *
     * @param str
     * @return str为空返回默认值
     */
    public static String dealString(Object str, String defaultValue) {
        if (!isValid(str)) {
            return dealString(defaultValue);
        } else {
            return str.toString();
        }
    }

    /**
     * 返回非空对象
     * 要求设置默认值
     *
     * @param obj
     * @return obj为空返回默认值
     */
    public static Object dealNull(Object obj, Object defaultValue) {
        if (!isValid(obj)) {
            return defaultValue;
        }
        return obj;
    }

    /**
     * 返回非空对象
     * 要求设置默认值
     *
     * @param str
     * @return obj为空返回默认值
     */
    public static int dealNumber(Object str, int defaultValue) {
        if (!isValid(str)) {
            return defaultValue;
        }
        return dealNumber(str);
    }

    public static long dealLong(Object str) {
        if (!isValid(str)) {
            return 0;
        } else {
            try {
                return Long.parseLong(str.toString());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    public static long dealLong(Object str, long defaultValue) {
        if (!isValid(str)) {
            return defaultValue;
        }
        return dealLong(str);
    }

    public static float dealFloat(Object str) {
        if (!isValid(str)) {
            return 0;
        } else {
            try {
                return Float.parseFloat(str.toString());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    public static float dealFloat(Object str, long defaultValue) {
        if (!isValid(str)) {
            return defaultValue;
        }
        return dealLong(str);
    }

    public static int dealNumber(Object str) {
        if (!isValid(str)) {
            return 0;
        } else {
            try {
                return Integer.parseInt(str.toString());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    public static String getClientIp(HttpServletRequest request) {
        //Nginx配置项：  proxy_set_header  X-Real-IP        $remote_addr;
        //              proxy_set_header  X-Forwarded-For  $proxy_add_x_forwarded_for;
        String ip = request.getHeader("X-Real-IP");
        if (isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getIp() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null && attributes.getRequest() != null) {
            HttpServletRequest req = attributes.getRequest();
            return getClientIp(req);
        }
        return null;
    }


    /**
     * 获取request属性值
     * @param key req.getAttribute 键
     */
    private static Object getRequestAttr(String key) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null && attributes.getRequest() != null) {
            HttpServletRequest req = attributes.getRequest();
            return req.getAttribute(key);
        }
        return null;
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null && attributes.getRequest() != null) {
            return attributes.getRequest();
        }
        return null;
    }

    public static Map<String, Object> getArrayMap(Object[]... objects) {
        Map<String, Object> map = new HashMap<>();
        if (objects == null) {
            return map;
        }
        for (Object[] objs : objects) {
            if (objs.length == 1 || objs[1] == null) {
                continue;
            }
            map.put(objs[0].toString(), objs[1]);
        }
        return map;
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 判断是否空，空抛异常
     * @param o 判断对象
     * @param errorCode 错误代码
     */
    public static <T> T requireNonNull(T o, int errorCode,Object ...params) {
        if (o == null||"".equals(o))
            throw new BusinessException(errorCode,params);
        return o;
    }

    public static  boolean  doLock(RedisTemplate redisTemplate, String key, long second) {
        try {
                return redisTemplate.opsForValue().setIfAbsent(key, 1);
        } finally {
            redisTemplate.expire(key, second, TimeUnit.SECONDS);
        }
    }


}
