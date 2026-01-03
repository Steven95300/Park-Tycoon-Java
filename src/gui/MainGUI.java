
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import config.ParcConfig;
import engine.map.Block;
import engine.map.Map;
import engine.process.GameBuilder;
import engine.process.ParcManager;

public class MainGUI extends JFrame implements Runnable {
    private static final long serialVersionUID = 1L;
    private JPanel topPanel;
    private JPanel sidePanel;
    private JPanel bottomPanel;
    private JLabel nbCheminLabel;
    private JLabel nbCarrouselLabel;
    private JLabel nbChaiseVolanteLabel;
    private JLabel argentLabel;
    private JLabel dateLabel;
    private int miliseconde;
    private Map map;
    private JLabel nbVisitorsLabel;
    private ParcManager manager;
    private GameDisplay fenetre;
    private final static Dimension preferredSize = new Dimension(ParcConfig.LARGEUR, ParcConfig.HAUTEUR);
    private JButton cheminButton;
    private JButton herbeButton;
    private JButton carrouselButton;
    private JButton chaiseVolanteButton;
    private JButton kartingButton;
    private JButton grandeRButton;
    private JButton fontaineButton;
    private JButton glaceButton;
    private JButton planteButton;
    private JButton restoButton;
    private JButton lampeButton;

    
    private JButton supprimerlampeButton;
    private JButton supprimergrandeRButton;
    private JButton supprimerfontaineButton;
    private JButton supprimerglaceButton;
    private JButton supprimerplanteButton;
    private JButton supprimerrestoButton;
    private JButton supprimerkartingButton;
    private JButton supprimerCheminButton;
    private JButton supprimerCarrouselButton;
    private JButton supprimerChaiseVolanteButton;
    
    private JButton placeStructureButton;
    private JButton removeStructureButton;
    private JButton backButton;
    private JButton pauseButton;
    private JButton unpauseButton;

    private boolean isPaused;

    public MainGUI(String title) {
        super(title);
        init();
    }

    private void init() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel statLabel = new JLabel("Statistiques");
        topPanel.add(statLabel);
        contentPane.add(topPanel, BorderLayout.NORTH);
        topPanel.setPreferredSize(new Dimension(ParcConfig.LARGEUR, 75));




        miliseconde = 0;

        argentLabel = new JLabel("Nombre de chemins : 0");
        topPanel.add(argentLabel);

        nbCheminLabel = new JLabel("Nombre de chemins : 0");
        topPanel.add(nbCheminLabel);

        nbCarrouselLabel = new JLabel("Nombre de carrousels : 0");
        topPanel.add(nbCarrouselLabel);

        nbVisitorsLabel = new JLabel("Nombre de visiteurs : 0");
        topPanel.add(nbVisitorsLabel);

        nbChaiseVolanteLabel = new JLabel("Nombre de Chaise Volante : 0");
        topPanel.add(nbChaiseVolanteLabel);

        dateLabel = new JLabel("Date");
        topPanel.add(dateLabel);

        sidePanel = new JPanel();
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sidePanel.setPreferredSize(new Dimension(200, ParcConfig.HAUTEUR));
        contentPane.add(sidePanel, BorderLayout.EAST);

        placeStructureButton = new JButton("Place Structure");
        placeStructureButton.addActionListener(e -> showPlaceStructureOptions());
        sidePanel.add(placeStructureButton);

        removeStructureButton = new JButton("Remove Structure");
        removeStructureButton.addActionListener(e -> showRemoveStructureOptions());
        sidePanel.add(removeStructureButton);

        cheminButton = new JButton("Placer un chemin");
        cheminButton.addActionListener(e -> manager.changeBlock("chemin"));
        cheminButton.setVisible(false);
        sidePanel.add(cheminButton);

        carrouselButton = new JButton("Placer un carrousel (300$)");
        carrouselButton.addActionListener(e -> manager.creeCarrousel(300));
        carrouselButton.setVisible(false);
        sidePanel.add(carrouselButton);

        supprimerCheminButton = new JButton("Supprimer un chemin");
        supprimerCheminButton.addActionListener(e -> manager.supprimerChemin());
        supprimerCheminButton.setVisible(false);
        sidePanel.add(supprimerCheminButton);

        supprimerCarrouselButton = new JButton("Supprimer un carrousel");
        supprimerCarrouselButton.addActionListener(e -> manager.supprimerCarrousel());
        supprimerCarrouselButton.setVisible(false);
        sidePanel.add(supprimerCarrouselButton);

        chaiseVolanteButton = new JButton("Placer une chaise volante (3000$)");
        chaiseVolanteButton.addActionListener(e -> manager.creeChaiseVolante(3000));
        chaiseVolanteButton.setVisible(false);
        sidePanel.add(chaiseVolanteButton);

        supprimerChaiseVolanteButton = new JButton("Supprimer chaise volante");
        supprimerChaiseVolanteButton.addActionListener(e -> manager.supprimerChaiseVolante());
        supprimerChaiseVolanteButton.setVisible(false);
        sidePanel.add(supprimerChaiseVolanteButton);

        kartingButton = new JButton("Placer un karting (3000$)");
        kartingButton.addActionListener(e -> manager.creeManege3(3000));
        kartingButton.setVisible(false);
        sidePanel.add(kartingButton);

        supprimerkartingButton = new JButton("Supprimer karting");
        supprimerkartingButton.addActionListener(e -> manager.supprimerManege3());
        supprimerkartingButton.setVisible(false);
        sidePanel.add(supprimerkartingButton);

        grandeRButton = new JButton("Placer un Grande Roue (30000$)");
        grandeRButton.addActionListener(e -> manager.creeManege4(10000));
        grandeRButton.setVisible(false);
        sidePanel.add(grandeRButton);

        supprimergrandeRButton = new JButton("Supprimer Grande Roue");
        supprimergrandeRButton.addActionListener(e -> manager.supprimerManege4());
        supprimergrandeRButton.setVisible(false);
        sidePanel.add(supprimergrandeRButton);

        fontaineButton = new JButton("Placer une fontaine (1000$)");
        fontaineButton.addActionListener(e -> manager.creeDeco(1000, "fontaine"));
        fontaineButton.setVisible(false);
        sidePanel.add(fontaineButton);

        supprimerfontaineButton = new JButton("Supprimer fontaine");
        supprimerfontaineButton.addActionListener(e -> manager.changeBlock("herbe"));
        supprimerfontaineButton.setVisible(false);
        sidePanel.add(supprimerfontaineButton);

        planteButton = new JButton("Placer une plante (1000$)");
        planteButton.addActionListener(e -> manager.creeDeco(1000, "plante"));
        planteButton.setVisible(false);
        sidePanel.add(planteButton );

        supprimerplanteButton = new JButton("Supprimer planteButton ");
        supprimerplanteButton.addActionListener(e -> manager.changeBlock("herbe"));
        supprimerplanteButton.setVisible(false);
        sidePanel.add(supprimerplanteButton);

        lampeButton = new JButton("Placer une lampe (1000$)");
        lampeButton.addActionListener(e -> manager.creeDeco(1000, "lampe"));
        lampeButton.setVisible(false);
        sidePanel.add(lampeButton );

        supprimerlampeButton = new JButton("Supprimer lampe ");
        supprimerlampeButton.addActionListener(e -> manager.changeBlock("herbe"));
        supprimerlampeButton.setVisible(false);
        sidePanel.add(supprimerlampeButton);

        restoButton = new JButton("Placer un restaurant (10000$)");
        restoButton.addActionListener(e -> manager.creeCommerce1(10000));
        restoButton.setVisible(false);
        sidePanel.add(restoButton);

        supprimerrestoButton = new JButton("Supprimer restaurant");
        supprimerrestoButton.addActionListener(e -> manager.supprimerCommerce1());
        supprimerrestoButton.setVisible(false);
        sidePanel.add(supprimerrestoButton);

        glaceButton = new JButton("Placer un stand de glace (10000$)");
        glaceButton.addActionListener(e -> manager.creeCommerce2(10000));
        glaceButton.setVisible(false);
        sidePanel.add(glaceButton );

        supprimerlampeButton = new JButton("Supprimer stand de glace ");
        supprimerlampeButton.addActionListener(e -> manager.supprimerCommerce2());
        supprimerlampeButton.setVisible(false);
        sidePanel.add(supprimerlampeButton);


        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainMenu());
        backButton.setVisible(true);
        sidePanel.add(backButton);


        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> pause());
        pauseButton.setVisible(true);
        sidePanel.add(pauseButton);

        JButton unpauseButton = new JButton("Reprendre");
        unpauseButton.addActionListener(e -> unpause());
        unpauseButton.setVisible(true);
        sidePanel.add(unpauseButton);

        KeyControls keyControls = new KeyControls();
        JTextField textField = new JTextField();
        textField.addKeyListener(keyControls);
        contentPane.add(textField, BorderLayout.SOUTH);

        map = GameBuilder.buildMap();
        manager = GameBuilder.buildInitMobile(map);
        fenetre = new GameDisplay(map, manager);
        fenetre.setPreferredSize(preferredSize);
        contentPane.add(fenetre, BorderLayout.CENTER);

        MouseControls mouseControls = new MouseControls();
        fenetre.addMouseListener(mouseControls);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setPreferredSize(preferredSize);
        setResizable(false);
    }

    private void showPlaceStructureOptions() {
        JButton[] structureButtons = { cheminButton, carrouselButton, chaiseVolanteButton, kartingButton, grandeRButton, fontaineButton, planteButton, lampeButton,restoButton,glaceButton };
        toggleButtons(structureButtons, true);
        JButton[] mainMenuButtons = { placeStructureButton, removeStructureButton, backButton };
        toggleButtons(mainMenuButtons, false);

    }
    
    private void showRemoveStructureOptions() {
        JButton[] supprimerButtons = {supprimerCheminButton, supprimerCarrouselButton, supprimerChaiseVolanteButton, supprimerkartingButton, supprimergrandeRButton, supprimerfontaineButton,supprimerplanteButton,supprimerlampeButton,supprimerrestoButton,supprimerglaceButton};
        toggleButtons(supprimerButtons, true);
        JButton[] mainMenuButtons = { placeStructureButton, removeStructureButton, backButton };
        toggleButtons(mainMenuButtons, false);

    }
    
    private void showMainMenu() {
        if (cheminButton.isVisible()){
            JButton[] structureButtons = { cheminButton, carrouselButton, chaiseVolanteButton, kartingButton, grandeRButton, fontaineButton, planteButton, lampeButton,restoButton,glaceButton};
            toggleButtons(structureButtons, false);
        }
        else if(supprimerCarrouselButton.isVisible()){
            JButton[] structureButtons = { supprimerCheminButton, supprimerCarrouselButton, supprimerChaiseVolanteButton, supprimerkartingButton, supprimergrandeRButton, supprimerfontaineButton,supprimerplanteButton,supprimerlampeButton,supprimerrestoButton,supprimerglaceButton};
            toggleButtons(structureButtons, false);
        }


        JButton[] mainMenuButtons = { placeStructureButton, removeStructureButton, backButton };
        toggleButtons(mainMenuButtons, true);

    }

    private void toggleButtons(JButton[] buttons, boolean visible) {
        for (JButton button : buttons) {
            //if((button.isVisible()==false)&(visible==false)){

            //}
            //else {
                button.setVisible(visible);
                System.out.println(button);
            //}

        }
    }

    private void pause(){
        isPaused=true;
    }

    private void unpause(){
        isPaused=false;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(ParcConfig.GAME_SPEED);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            fenetre.repaint();

            // Mettre à jour les labels avec les valeurs actuelles de manager
            argentLabel.setText("Argent : " + manager.getArgent() + "$");
            nbCheminLabel.setText("Nombre de chemins : " + manager.getNbChemin());
            nbCarrouselLabel.setText("Nombre de carrousels : " + manager.getNbCarrousel());
            nbChaiseVolanteLabel.setText("Nombre de Chaise Volante : " + manager.getNbChaiseVolante());
            nbVisitorsLabel.setText("Nombre de Visiteurs : " + manager.getNbVisiteurs());
            dateLabel.setText("Date : " + manager.getDate().getStringDate());
            // fait des actions comme génèrer de l'argent chaque vingtième de seconde selon
            // le nombre des différentes structures
            if (isPaused==false){
            
                if (miliseconde % 10 == 0) {
                    manager.spawnVisitors();
                    manager.moveVisitors();
                    manager.checkCarouselInteraction();
                    manager.updateAttractivite();
                    manager.updateVisiteurs();
                }
                if (miliseconde % 100 == 0) {
                    manager.ajouterArgent(manager.calculerProfit());
                    manager.getDate().incrementMinute();
                    System.out.println(manager.getDate().getMinute());

                }
                if (miliseconde % 1000 == 0) {

                    miliseconde = 0;
                }
                miliseconde++;
            }
        }
    }

    public void loadSave(){
        //map =
        //manager.map = 
        //manager,astats
        //manager.loadsave
    }
    

    private class MouseControls implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            int y = (e.getX()) / ParcConfig.BLOCK_SIZE;
            int x = (e.getY()) / ParcConfig.BLOCK_SIZE;
            Block b = new Block(x, y);
            manager.moveSetCurseur(b);
            Block clickedBlock = map.getBlock(x, y);
            
            if (clickedBlock.isLocked()) {
                // Calculer le coût de la zone pour l'affichage
                int zone = determineZone(x, y); // Assurez-vous que cette méthode est bien définie
                int cost = manager.getZoneCost(zone); // Assurez-vous que cette méthode est bien définie dans ParcManager
    
                // Boîte de dialogue demandant à l'utilisateur s'il souhaite acheter la zone
                int response = JOptionPane.showConfirmDialog(null, "Voulez-vous acheter cette zone pour " + cost + "$ ?", "Achat", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    boolean success = manager.achatZone(zone);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Zone achetée avec succès!");
                        fenetre.repaint(); // Mettre à jour l'affichage pour montrer la zone déverrouillée
                    } else {
                        JOptionPane.showMessageDialog(null, "Pas assez d'argent pour acheter cette zone.", "Erreur d'achat", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                manager.moveSetCurseur(clickedBlock);
            }
        }

        private int determineZone(int x, int y) {
            int lineCount = map.getLineCount();
            int columnCount = map.getColumnCount();
            if (y < lineCount / 2) {
                return x < columnCount / 2 ? 1 : 2;
            } else {
                return x < columnCount / 2 ? 3 : 4;
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }



    }

    private class KeyControls implements KeyListener {
        @Override
        public void keyPressed(KeyEvent event) {
            char keyChar = event.getKeyChar();
            switch (keyChar) {
                case 'q':
                    manager.moveLeftCurseur();
                    break;
                case 'd':
                    manager.moveRightCurseur();
                    break;
                case 'z':
                    manager.moveUpCurseur();
                    break;
                case 's':
                    manager.moveDownCurseur();
                    break;
                case 'c':
                    manager.changeBlock("chemin");
                    break;
                case 'h':
                    manager.changeBlock("herbe");
                    break;
                case 'l':
                    manager.changeBlock3x3("carrousel", 300);
                    break;
                case 'i':
                    manager.blockInfo();
                    break;
                default:
                    break;
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }


}



