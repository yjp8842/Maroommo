package com.a406.mrm.controller;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

@RestController
public class OcrController {

    @PostMapping("/ocr")
    public String ocr(@RequestParam("Image") MultipartFile image) throws IOException {

        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("eng+kor");

        var address = this.getClass().getClassLoader().getResource("tessdata");
        try {

            File find_key = null;
            String find_value = null;

            Map<File, String> data = convert(image);

            for (File key : data.keySet()) {
                find_key = key;
            }
            for (String value : data.values()) {
                find_value = value;
            }

            BufferedImage in = ImageIO.read(find_key);

            BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = newImage.createGraphics();
            g.drawImage(in, 0, 0, null);
            g.dispose();

            tesseract.setDatapath(Paths.get(address.toURI()).toString());

            String result = tesseract.doOCR(newImage);

//            find_key.delete();
            File delete_file = new File(find_value);
            delete_file.delete();

            return result;

        } catch (TesseractException | URISyntaxException | IOException e) {
            System.err.println(e.getMessage());
            return "Error while reading image";
        }

    }
    public static Map<File, String> convert(MultipartFile file) throws IOException {
        String uploadFileName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        uploadFileName = uuid.toString() + "_" + uploadFileName;
        String path = "BackEnd/src/main/resources/img/";
        File convFile = new File(path + uploadFileName);
        convFile.createNewFile();

        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        Map<File, String> data = Map.of(convFile, path + uploadFileName);
        return data;
    }


}
