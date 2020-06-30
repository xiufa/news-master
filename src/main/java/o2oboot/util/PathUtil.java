package o2oboot.util;

public class PathUtil {

    private static String seperator=System.getProperty("file.separator");
    public static String getImgBasePath(){
        String os=System.getProperty("os.name");
        String basePath="";
        if(os.toLowerCase().startsWith("win")){
            basePath="D:/o2o/image";
        }else{
            basePath="/home/o2o/image";
        }
//        System.out.println(basePath+" : "+seperator);
        basePath.replace("/",seperator);
        return basePath;

    }

    public static String getShopImagePath(Long shopId){
        String imagePath="/upload/item/shop/"+shopId+"/";
        return imagePath;
    }
}
