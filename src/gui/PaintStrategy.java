package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


import config.ParcConfig;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Curseur;
import engine.mobile.Visitor;

public class PaintStrategy {
	
	private Image imageHaut, imageBas, imageGauche, imageDroite;

	public void paint(Map map, Graphics graphics, Image imageHerbe, Image imageStone, Image imageCarrousel, Image imagechaisevolante, Image imagekart, Image imgHaut, Image imgBas, Image imgGauche, Image imgDroite, Image imagefontaine, Image imageglace,Image imageGR,Image imageLampe,Image imageplante ,Image imageresto) {
		int blockSize = ParcConfig.BLOCK_SIZE;
		this.imageHaut = imgHaut;
        this.imageBas = imgBas;
        this.imageGauche = imgGauche;
        this.imageDroite = imgDroite;
		Block[][] blocks = map.getBlocks();

		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				Block block = blocks[lineIndex][columnIndex];
				if (true) {
					if (block.getElement() == "herbe") {
						graphics.drawImage(imageHerbe, block.getColumn() * blockSize, block.getLine() * blockSize,
								blockSize + 1, blockSize + 1, null);

					} 
					else if (block.getElement() == "chemin") {
						graphics.drawImage(imageStone, block.getColumn() * blockSize, block.getLine() * blockSize,
								blockSize + 1, blockSize + 1, null);
					}

					else if (block.getElement() == "carrousel") {
						//graphics.setColor(Color.RED.darker());
						//graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize,
						//		blockSize);
						//graphics.setColor(Color.GRAY.darker().darker());
						//graphics.drawRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize,
						//		blockSize);
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) {
								graphics.drawImage(imageHerbe,(block.getColumn()-2+i) * blockSize, (block.getLine()-2+j) * blockSize, blockSize+1, blockSize+1, null);
						  }
					  }
						graphics.drawImage(imageCarrousel,(block.getColumn()-2) * blockSize, (block.getLine()-2) * blockSize, blockSize*3, blockSize*3, null);

						/*int v=0;
						
						if (v==0){
							graphics.drawImage(imageCarrousel,block.getColumn() * blockSize, block.getLine() * blockSize, blockSize*3, blockSize*3, null);
						}
					*/
					}

					else if (block.getElement() == "chaiseVolante") {
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) {
								graphics.drawImage(imageHerbe,(block.getColumn()-2+i) * blockSize, (block.getLine()-2+j) * blockSize, blockSize+1, blockSize+1, null);
						  }
					  }
						graphics.drawImage(imagechaisevolante,(block.getColumn()-2) * blockSize, (block.getLine()-2) * blockSize, blockSize*3, blockSize*3, null);

					}

					else if (block.getElement() == "Manege3") {
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) {
								graphics.drawImage(imageHerbe,(block.getColumn()+i-2) * blockSize, (block.getLine()+j-2) * blockSize, blockSize+1, blockSize+1, null);
						  }
					  }
						graphics.drawImage(imagekart,(block.getColumn()-2) * blockSize, (block.getLine()-2) * blockSize, blockSize*3, blockSize*3, null);

					}

					else if (block.getElement() == "Manege4") {
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) {
								graphics.drawImage(imageHerbe,(block.getColumn()-2+i) * blockSize, (block.getLine()-2+j) * blockSize, blockSize+1, blockSize+1, null);
						  }
					  }
						graphics.drawImage(imageGR,(block.getColumn()-2) * blockSize, (block.getLine()-2) * blockSize, blockSize*3, blockSize*3, null);

					}

					else if (block.getElement() == "Commerce1") {
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) {
								graphics.drawImage(imageHerbe,(block.getColumn()-2+i) * blockSize, (block.getLine()-2+j) * blockSize, blockSize+1, blockSize+1, null);
						  }
					  }
						graphics.drawImage(imageresto,(block.getColumn()-2) * blockSize, (block.getLine()-2) * blockSize, blockSize*3, blockSize*3, null);

					}

					else if (block.getElement() == "Commerce2") {
						for (int i = 0; i < 3; i++) {
							for (int j = 0; j < 3; j++) {
								graphics.drawImage(imageHerbe,(block.getColumn()-2+i) * blockSize, (block.getLine()-2+j) * blockSize, blockSize+1, blockSize+1, null);
						  }
					  }
						graphics.drawImage(imageglace,(block.getColumn()-2) * blockSize, (block.getLine()-2) * blockSize, blockSize*3, blockSize*3, null);

					}

					else if (block.getElement() == "lampe") {
						graphics.drawImage(imageHerbe,(block.getColumn()) * blockSize, (block.getLine()) * blockSize, blockSize, blockSize, null);
						graphics.drawImage(imageLampe,(block.getColumn()) * blockSize, (block.getLine()) * blockSize, blockSize, blockSize, null);

					}

					else if (block.getElement() == "plante") {
						graphics.drawImage(imageHerbe,(block.getColumn()) * blockSize, (block.getLine()) * blockSize, blockSize, blockSize, null);
						graphics.drawImage(imageplante,(block.getColumn()) * blockSize, (block.getLine()) * blockSize, blockSize, blockSize, null);

					}

					else if (block.getElement() == "fontaine") {

						graphics.drawImage(imageHerbe,(block.getColumn()) * blockSize, (block.getLine()) * blockSize, blockSize, blockSize, null);
						graphics.drawImage(imagefontaine,(block.getColumn()) * blockSize, (block.getLine()) * blockSize, blockSize, blockSize, null);

					}

					else {
					//	graphics.setColor(Color.BLACK);
					//	graphics.fillRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize,
					//			blockSize);
					//	graphics.setColor(Color.GRAY.darker().darker());
					//	graphics.drawRect(block.getColumn() * blockSize, block.getLine() * blockSize, blockSize,
					//			blockSize);
					}
			
				}
			}
		}
	}

	public void paint(Curseur curseur, Graphics graphics, Image imageCurseur) {
		Block position = curseur.getPosition();
		int blockSize = ParcConfig.BLOCK_SIZE;

		int x = position.getColumn() * blockSize;
		int y = position.getLine() * blockSize;

		// Taille désirée de l'image du curseur
		int curseurWidth = blockSize * 2;
		int curseurHeight = blockSize * 2;

		x -= blockSize / 2;
		y -= blockSize / 2;

		graphics.drawImage(imageCurseur, x, y, curseurWidth, curseurHeight, null);


		
	}

	public void paint(Visitor visitor, Graphics g) {
        Block position = visitor.getPosition();
        int blockSize = ParcConfig.BLOCK_SIZE;
        Image imageToDraw = null;
        switch (visitor.getDirection()) {
            case Visitor.HAUT:
                imageToDraw = imageHaut;
                break;
            case Visitor.BAS:
                imageToDraw = imageBas;
                break;
            case Visitor.GAUCHE:
                imageToDraw = imageGauche;
                break;
            case Visitor.DROITE:
                imageToDraw = imageDroite;
                break;
        }
        if (imageToDraw != null) {
            g.drawImage(imageToDraw, position.getColumn() * blockSize, position.getLine() * blockSize, blockSize, blockSize, null);
        }

    }




}
