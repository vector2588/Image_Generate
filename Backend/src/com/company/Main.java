package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImagingOpException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        File input = new File("D:\\MyProject\\Resource\\A2.jpg");

        //******************************************************************************************
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\MyProject\\Resource\\List.csv"),"UTF8"));
        //File list = new File("D:\\MyProject\\Resource\\List.csv");
        Scanner scan = new Scanner(br);

        String[][] Array = new String[11][4];

        String Student_id;
        String name;
        String card_id;
        String address;
        int num = 1;

        scan.nextLine();
        int index = 0;
        while(scan.hasNextLine()) {
            String[] student = scan.nextLine().split("[,]");
            Student_id = student[0];
            name = student[1];
            card_id = student[2];
            address = student[3];
            File output = new File("D:\\MyProject\\Resource\\Output" + num + ".jpg");
            addTextNameInImage(name, Student_id, card_id, address,"jpg",input,output);
            num++;
        }
        //*********************************************************************************************
    }


    public static void addTextNameInImage(String name, String Student_id, String card_id, String address, String type, File source, File destination) throws IOException {
        BufferedImage image = ImageIO.read(source);
        int imagetype = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;

        BufferedImage bold = new BufferedImage(image.getWidth(), image.getHeight(), imagetype);

        Graphics2D w1 = (Graphics2D) bold.getGraphics();
        Graphics2D w2 = (Graphics2D) bold.getGraphics();
        //******************************************************************************************
        w1.drawImage(image,1,2,null);
        AlphaComposite alpha1 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);

        w1.setComposite(alpha1);
        w1.setColor(Color.BLACK);
        w1.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));

        FontMetrics fmatrix1 = w1.getFontMetrics();
        Rectangle2D rect1 = fmatrix1.getStringBounds(name,w1);
        //******************************************************************************************
        w2.drawImage(image,1,2,null);
        AlphaComposite alpha2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);

        w2.setComposite(alpha2);
        w2.setColor(Color.BLACK);
        w2.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 35));

        FontMetrics fmatrix2 = w2.getFontMetrics();
        Rectangle2D rect2 = fmatrix2.getStringBounds(name,w2);
        //******************************************************************************************
        //int centerX = ((image.getWidth() - (int)rect.getWidth())/2)+(((image.getWidth() - (int)rect.getWidth())/2)/2)/2;
        int centerX = 600;

        int centerY1 = image.getHeight()/2 - 10;
        int centerY2 = image.getHeight()/2 + 51;
        int centerY3 = image.getHeight()/2 + 114;
        int centerY4 = image.getHeight()/2 + 177;

        w1.setFont(new Font("Angsana New", Font.PLAIN, 50));

        w1.drawString(name, centerX, centerY1);
        w2.drawString(Student_id, centerX + 120, centerY2);
        w2.drawString(card_id, centerX  + 120, centerY3);
        w2.drawString(address, centerX + 120, centerY4);
        ImageIO.write(bold,type,destination);

        w1.dispose();
        w2.dispose();
    }
}
