package util;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class FileUploadUtil {
    private static final String ROOT = "/var/www/app/beavermanager/execl";

    private static final String[] SupportFileSuffix = {"xlsx"};
    /**
     * 文件上传
     * @param file
     * @return
     * 返回路径url
     */
    public static String uploadExcel(MultipartFile file) throws IOException {
        //TODO 判断文件类型
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            logger.info("上传的文件名为：" + fileName);
            // 获取文件的后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            logger.info("上传的后缀名为：" + suffixName);

            //检测是否支持这样的后缀
            boolean IsSupport = false;
            for (String suffix:SupportFileSuffix){
                if((suffixName.trim()).equals("."+suffix)){
                    IsSupport = true;break;
                }
            }
            if (!IsSupport){
                throw new RuntimeException("不支持此类型文件");
            }

            try {
                /* 判断是否存在这样的目录 */
                File dest = new File(ROOT+"//"+file.getOriginalFilename());
                if(!dest.getParentFile().exists()){
                    dest.getParentFile().mkdirs();
                }
                fileName = UUID.randomUUID().toString()+suffixName;
                Files.copy(file.getInputStream(), Paths.get(ROOT, fileName));
                return fileName;
            } catch (IOException |RuntimeException e) {
                throw new IOException("上传失败");
            }
        } else {
            throw new IOException("文件不能为空");
        }
    }
}
