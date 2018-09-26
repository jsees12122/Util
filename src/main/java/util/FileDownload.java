package util;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileDownload {
    private final String ROOT = "/download-file/";

    /**
     * 文件下载
     * @param response
     * @param file 下载的文件名
     * @throws FileNotFoundException
     */

    public void downloadLocal(HttpServletResponse response, String file) throws FileNotFoundException {
        // 下载本地文件
        String fileName = file.toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = this.getClass().getResourceAsStream(ROOT + fileName);
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
