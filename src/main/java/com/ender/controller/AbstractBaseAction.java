package com.ender.controller;

import com.ender.entity.BaseEntity;
import com.ender.util.DocUtil;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基础action类
 * response返回参数
 * http头公用
 * Created by ender on 2017/3/11.
 */
public abstract class AbstractBaseAction {
    protected Logger log = LogManager.getLogger(this.getClass().getName());

    private static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    protected static String getRequestPostStr(HttpServletRequest request) {
        try {
            byte buffer[] = getRequestPostBytes(request);
            String charEncoding = request.getCharacterEncoding();
            if (charEncoding == null) {
                charEncoding = "UTF-8";
            }
            return new String(buffer, charEncoding);
        } catch (IOException var4) {
            var4.printStackTrace();
        }
        return null;
    }


    /**
     * 统一返回接口文档页面
     * 注：接口文档为md格式，放在webapp/doc目录下，文档名与BaseAction的子类同名
     * 例如IndexController.java对应的接口文档为webapp/doc/IndexController.md
     *
     * @param res
     * @return
     */
    @RequestMapping(value = "doc",method = RequestMethod.GET)
    public BaseEntity doGetDoc(HttpServletRequest req,HttpServletResponse res) {
        DocUtil.returnDocPage(req,res, getClass().getSimpleName() + ".md");
        return null;
    }

}
