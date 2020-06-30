package o2oboot.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class ImageUtil {

    private static String basePath=Thread.currentThread().getContextClassLoader().getResource("").getPath();
    private static Logger logger= LoggerFactory.getLogger(ImageUtil.class);
    private static SimpleDateFormat sdateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
    private static Random r=new Random();
    public static String generateThumbnail(InputStream multipartFile, String fileName,String targetAddr)  {
        String realFileName=getRandomFileName();
        String extension=getFileExtension(fileName);
        System.out.println(targetAddr);
        makeDirPath(PathUtil.getImgBasePath()+targetAddr);
        String relativeAddr=targetAddr+realFileName+extension;
        File dest=new File(PathUtil.getImgBasePath()+relativeAddr);
        try{
            File watermark=new File(PathUtil.getImgBasePath()+"/watermark.jpg");
            Thumbnails.of(multipartFile)
                    .size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(watermark),0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        }catch (IOException e){
            logger.error(e.toString());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    public static String getRandomFileName(){
        String time=sdateFormat.format(new Date());
        int random=r.nextInt(8999)+10000;
        return time+random;
    }

    public static File transferCommonsMultipartFileToFile(CommonsMultipartFile commonsMultipartFile){
        File file=new File(commonsMultipartFile.getOriginalFilename());
        try {
            commonsMultipartFile.transferTo(file);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        return file;
    }

    private static String getFileExtension(String name) {

        return name.substring(name.lastIndexOf("."));
    }

    private static void makeDirPath(String targetAddr){
        File file=new File(targetAddr);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public static void deleteFileOrPath(String fileOrPath){
        File file =new File(PathUtil.getImgBasePath()+fileOrPath);
        if(file.exists()){
            if(file.isDirectory()){
                File[] files=file.listFiles();
                for(int i=0;i<files.length;i++){
                    files[i].delete();
                }
            }else{
                file.delete();
            }

        }
    }

}
