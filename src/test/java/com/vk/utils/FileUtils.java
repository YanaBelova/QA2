package com.vk.utils;

import aquality.selenium.browser.AqualityServices;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUtils {
    public static File convertFile(String pathToFile, String nameOfFile, String fileSuffix){
        File photoToUpload = new File(String.format("%s%s", pathToFile,nameOfFile));
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(photoToUpload);
        } catch (IOException e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }
        String fileName = String.format("%s%s%s",UUID.randomUUID(),".",fileSuffix);
        File resultPic = new File(String.format("%s%s",pathToFile,fileName));
        try {
            ImageIO.write(bufferedImage, fileSuffix, resultPic);
        } catch (IOException e) {
            AqualityServices.getLogger().error(String.valueOf(e));
        }
        return resultPic;
    }

    public static void deleteFile(File file){
        file.delete();
    }
}
