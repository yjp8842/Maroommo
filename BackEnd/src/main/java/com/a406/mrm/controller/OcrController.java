package com.a406.mrm.controller;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class OcrController {

    @PostMapping("/ocr")
    public String ocr(@RequestParam("Image") MultipartFile image) throws IOException {

        Tesseract tesseract = new Tesseract();
        tesseract.setLanguage("eng+kor");

        var address = this.getClass().getClassLoader().getResource("tessdata");
        try {
            BufferedImage in = ImageIO.read(convert(image));

            BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);

            Graphics2D g = newImage.createGraphics();
            g.drawImage(in, 0, 0, null);
            g.dispose();

            tesseract.setDatapath(Paths.get(address.toURI()).toString());

            String result = tesseract.doOCR(newImage);
            convert(image).delete();

            return result;

        } catch (TesseractException | URISyntaxException | IOException e) {
            System.err.println(e.getMessage());
            return "Error while reading image";
        }

    }
    public static File convert(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


}
