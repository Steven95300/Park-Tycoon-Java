package gui;

import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;

import config.ParcConfig;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Curseur;
import engine.mobile.Visitor;
import engine.process.ParcManager;

public class GameDisplay extends JPanel {


	private Map map;
	private ParcManager manager;
	private PaintStrategy paintStrategy = new PaintStrategy();
	//private ImagesLoaded images;
	private ImagesLoaded images= new ImagesLoaded();

	public GameDisplay(Map map, ParcManager manager) {
		this.map = map;
		this.manager = manager;
		this.images = new ImagesLoaded();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		paintStrategy.paint(map, g, images.sendImage(), images.sendImage2(), images.sendImage3(), images.sendImageChaiseVolante(), images.sendImageKart(), images.sendImageHaut(), images.sendImageBas(), images.sendImageGauche(),images.sendImageDroite(), images.sendImageFontaine(),images.sendImageGlace(),images.sendImageGR(),images.sendImageLampe(),images.sendImagePlante(),images.sendImageResto());


		for (int lineIndex = 0; lineIndex < map.getLineCount(); lineIndex++) {
			for (int columnIndex = 0; columnIndex < map.getColumnCount(); columnIndex++) {
				Block block = map.getBlock(lineIndex, columnIndex);
				if (block.isLocked()) {
					g.setColor(new Color(100, 100, 100, 128)); // Semi-transparent gris
					int blockSize = ParcConfig.BLOCK_SIZE;
					g.fillRect(columnIndex * blockSize, lineIndex * blockSize, blockSize, blockSize);
				}
			}
		}

		for (Visitor visitor : manager.getVisitors()) {
			paintStrategy.paint(visitor, g); // Dessine chaque visiteur avec sa méthode dédiée
        }

        Curseur curseur = manager.getCurseur();
        paintStrategy.paint(curseur, g, images.sendImageCursor());

		for (Visitor visitor : manager.getVisitors()){
			paintStrategy.paint(visitor, g);
		}
	}


}
