package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage extends JFrame {
    public LaunchPage() {
        // Configuration de la fenêtre principale
        setTitle("Parc d'Attraction - Accueil");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Utiliser BorderLayout

        // Charger l'image d'arrière-plan et la redimensionner pour qu'elle remplisse la fenêtre
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/images/background.jpg"));
        Image image = originalIcon.getImage(); // Transformer l'ImageIcon en Image pour pouvoir la redimensionner
        Image newimg = image.getScaledInstance(600,600,  Image.SCALE_SMOOTH); // Redimensionner l'image
        ImageIcon backgroundIcon = new ImageIcon(newimg); // Retourner l'image redimensionnée à un ImageIcon pour l'affichage

        // Création de l'étiquette pour l'image de fond
        JLabel background = new JLabel(backgroundIcon);
        background.setLayout(new GridLayout(3, 1)); // Disposition des boutons

        // Ajouter l'étiquette de fond à la fenêtre
        add(background);

        // Création et ajout des boutons
        addButton(background, "/images/play.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainGUI gameMainGUI = new MainGUI("Parc d'Attraction");
                Thread gameThread = new Thread(gameMainGUI);
                gameThread.start();
            }
        });

        addButton(background, "/images/help.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Votre objectif est de gérer le parc de la meilleure façon possible.\n Achetez des manèges pour attirer des visiteurs et faire prospérer votre parc ! \n N'oubliez pas de connecter vos manèges à des chemins, sinon les visiteurs ne pourront pas les utiliser. \n Les manèges ont des coûts de maintenance et des rendements décroissants, pensez a diversifier votre Parc! \n  Attention, si vous avez moins de 0 dollars, vous perdez !");
            }
        });

        addButton(background, "/images/exit.png", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void addButton(JLabel background, String iconPath, ActionListener action) {
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        JButton button = new JButton(icon);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 150));
        button.addActionListener(action);
        background.add(button);
    }

    public static void main(String[] args) {
        new LaunchPage(); // Lancer la page de lancement
    }
}