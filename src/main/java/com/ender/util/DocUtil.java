package com.ender.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class DocUtil {

    private DocUtil() {
    }

    //返回接口文档页面
    public static void returnDocPage(HttpServletRequest req, HttpServletResponse res, String filePath) {
        try {
            res.setHeader("Pragma", "No-cache");
            res.setHeader("Cache-Control", "no-cache");
            res.setContentType("text/html;charset=UTF-8");
            res.getWriter().write(getDocPage(req.getContextPath(),filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //拼装接口文档的md解析页面
    public static String getDocPage(String serverPath,String filePath) {
        String beginStr = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\"> \n" +
                "    <link href=\""+serverPath+"/themes/style.css\" rel=\"stylesheet\">\n" +
                "    <link href=\""+serverPath+"/themes/foundation.min.css\" rel=\"stylesheet\">\n" +
                "    <link href=\""+serverPath+"/themes/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "    <script src=\"https://code.jquery.com/jquery-3.2.0.min.js\"></script>   \n" +
                "    <script src=\""+serverPath+"/scripts/showdown.js\"></script>\n" +
                "</head>\n" +
                "<body style=\"padding:30px\">\n" +
                "<div style=\"display: none\">\n" +
                "    <textarea id=\"text-input\" oninput=\"this.editor.update()\" rows=\"6\" cols=\"60\">";

        String endStr = "</textarea>\n" +
                "</div>\n" +
                "<div id=\"preview\"></div>\n" +
                "<script> function Editor(input, preview) {\n" +
                "    var converter = new showdown.Converter();  \n" +
                "    this.update = function () {\n" +
                "    preview.innerHTML = converter.makeHtml(input.value);\n" +
                "        };\n" +
                "        input.editor = this;\n" +
                "        this.update();\n" +
                "    }" +
                "var $ = function (id) {\n" +
                "    return document.getElementById(id);\n" +
                "};\n" +
                "new Editor($(\"text-input\"), $(\"preview\")); </script>\n" +
                "</body>\n" +
                "</html>";
        String mdFileStr = readFile(filePath);
        mdFileStr = "noFound".equals(mdFileStr) ? "####该目录未提供接口文档" : mdFileStr;
        return beginStr + mdFileStr + endStr;
    }

    //读取文件内容
    public static String readFile(String filePath) {
        String str = "";
        FileInputStream in = null;
        try {
            File file = new File(DocUtil.class.getResource("/").getPath() + "../../doc/" + filePath);
            if (!file.exists()) {
                return "noFound";
            }
            in = new FileInputStream(file);
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();
            str = new String(buffer, "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeObject(in);
        }
        return str;
    }

    /**
     * 关闭文件输入流
     *
     * @param finp
     */
    public static void closeObject(FileInputStream finp) {
        try {
            if (finp != null) {
                finp.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
