package org.example;

<<<<<<< HEAD
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
=======
import org.checkerframework.checker.units.qual.A;

>>>>>>> 88f7a60f18c591e8d4c48d9ebac41b797c51434c
import java.util.ArrayList;


public class Main {
<<<<<<< HEAD
    public static void main(String[] args) throws Exception {
        try {
            XWPFDocument document = new XWPFDocument();
//            File file = new File("./document.docx");
            FileOutputStream fOut = new FileOutputStream(new File("./document.docx"));

            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("Test for write to word");

            document.write(fOut);
            fOut.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("Write success");
=======
    public static void main(String[] args) {
>>>>>>> 88f7a60f18c591e8d4c48d9ebac41b797c51434c
    }
}