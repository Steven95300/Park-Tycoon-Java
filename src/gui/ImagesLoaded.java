package gui;

import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImagesLoaded {
    private Image imagesCharge;


    public ImagesLoaded() {
        imagesCharge = readImage("images/herbe.jpg");

    }

    public Image sendImage(){
        return imagesCharge;

    }



    public Image sendImageHaut() {
        return readImage("images/animHaut.png");

    }

    public Image sendImageBas() {
        return readImage("images/animBas.png");

    }
    public Image sendImageGauche() {
        return readImage("images/animGauche.png");

    }
    public Image sendImageDroite() {
        return readImage("images/animDroite.png");

    }
    public Image sendImageCursor() {
        return readImage("images/cursor.png");

    }

    public Image sendImage2(){
        return readImage("images/stone.png");

    }

    public Image sendImage3(){
        return readImage("images/carrousel.png");

    }

    public void loadImage(int n, String filePath) {

    }

    public Image readImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            System.err.println("-- Can not read the image file ! --");
            return null;
        }
    }

    public Image sendImageChaiseVolante(){
            return readImage("images/chaisevolante.png");
    }

    public Image sendImageFontaine() {
            return readImage("images/fontaine.png");
    
        
    }
        public Image sendImageGlace() {
            return readImage("images/glace.png");
    
        
    }
        public Image sendImageGR() {
            return readImage("images/grandeRoue.png");
    
        
    }
        public Image sendImageLampe() {
            return readImage("images/lampe.png");
    
        
    }
        public Image sendImagePlante() {
            return readImage("images/plante.png");
    
        
    }
        public Image sendImageResto() {
            return readImage("images/resto.png");
    
        
    }
        public Image sendImageKart() {
            return readImage("images/kart.png");
    
        
    }
}