package com.edu.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FileUpload {


    public static Map uploadFile(HttpServletRequest req) throws ServletException, IOException {
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        Map<String,String> map = new HashMap<String,String>();
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        // 解析request请求，返回List集合，List集合存放FileItem对象
        String url = null;
        String uuidFileName = null;

        try {
            List<FileItem> list = fileUpload.parseRequest(req);
            for (FileItem fileItem:list){
                if(fileItem.isFormField()){
                    String name = fileItem.getFieldName();
                    String value = fileItem.getString("UTF-8");
                    map.put(name,value);
                }
                String fileName = fileItem.getName();
                if (fileName != null && !"".equals(fileName)){
                    uuidFileName = getFileName(fileName);
                    InputStream is = fileItem.getInputStream();
                    String path = req.getSession().getServletContext().getRealPath("/")+"images\\";
                    url = path + uuidFileName;

                    OutputStream os = new FileOutputStream(url);
                    int len = 0;
                    byte[] b = new byte[1024];
                    while ((len = is.read(b)) != -1)
                        os.write(b,0,len);
                    is.close();
                    os.close();
                }
                System.out.println(map);
                map.put("path",url);
                map.put("filename",uuidFileName);
            }
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static String getFileName(String fileName){
        int index = fileName.lastIndexOf(".");
        String exName = fileName.substring(index);
        String uuidFileName = UUID.randomUUID().toString().replace("-","")+exName;
        return uuidFileName;
    }
}
