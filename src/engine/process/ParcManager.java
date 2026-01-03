
package engine.process;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import config.ParcConfig;
import data.ActiveStats;
import data.Date;
import engine.map.Block;
import engine.map.Map;
import engine.mobile.Curseur;
import engine.mobile.Visitor;

public class ParcManager {
    private Map map;
    private ActiveStats aStats;
    private List<Visitor> visitors;
    private int nbChemin = 0;
    private int nbCarrousel = 0;
    private int nbChaiseVolante = 0;
    private int nbManege3 = 0;
    private int nbManege4 = 0;
    private int nbCommerce1 = 0;
    private int nbCommerce2 = 0;
    private int nbDecoration = 0;
    private Curseur Curseur;
    private Random random = new Random();
    private int maxVisitors = 3;

    public ParcManager(Map map) {
        this.map = map;
        this.aStats = new ActiveStats();
        this.visitors = new ArrayList<>();

        this.visitors = new ArrayList<>();
    }

    public void set(Curseur Curseur) {
        this.Curseur = Curseur;
    }

    // I ViSITEURS

    // Méthode pour générer aléatoirement des visiteurs sur les chemins
    public void spawnVisitors() {
        int currentVisitors = visitors.size();
        setMaxVisitors(1+getNbVisiteurs()/40);
        int visitorsToSpawn = maxVisitors - currentVisitors;

        for (int i = 0; i < visitorsToSpawn; i++) {
            Block randomBlock = getRandomPathBlock();
            if (randomBlock != null) {
                Visitor newVisitor = new Visitor(randomBlock);
                visitors.add(newVisitor);
            }
        }
    }

    // Méthode pour déplacer les visiteurs sur les chemins
    public void moveVisitors() {
        for (Visitor visitor : visitors) {
            int direction = random.nextInt(4);  // Génère un nombre aléatoire entre 0 et 3
            Block position = visitor.getPosition();
            Block newPosition = null;
            switch (direction) {
                case Visitor.HAUT:  // Si la direction est 0 (HAUT)
                    if (position.getLine() > 0 && canMoveTo(position.getLine() - 1, position.getColumn())) {
                        newPosition = map.getBlock(position.getLine() - 1, position.getColumn());
                        visitor.setPosition(newPosition, Visitor.HAUT);
                    }
                    break;
                case Visitor.BAS:  // Si la direction est 1 (BAS)
                    if (position.getLine() < ParcConfig.LINE_COUNT - 1 && canMoveTo(position.getLine() + 1, position.getColumn())) {
                        newPosition = map.getBlock(position.getLine() + 1, position.getColumn());
                        visitor.setPosition(newPosition, Visitor.BAS);
                    }
                    break;
                case Visitor.GAUCHE:  // Si la direction est 2 (GAUCHE)
                    if (position.getColumn() > 0 && canMoveTo(position.getLine(), position.getColumn() - 1)) {
                        newPosition = map.getBlock(position.getLine(), position.getColumn() - 1);
                        visitor.setPosition(newPosition, Visitor.GAUCHE);
                    }
                    break;
                case Visitor.DROITE:  // Si la direction est 3 (DROITE)
                    if (position.getColumn() < ParcConfig.COLUMN_COUNT - 1 && canMoveTo(position.getLine(), position.getColumn() + 1)) {
                        newPosition = map.getBlock(position.getLine(), position.getColumn() + 1);
                        visitor.setPosition(newPosition, Visitor.DROITE);
                    }
                    break;
            }
        }
    }
    
    // Méthode pour vérifier si un visiteur peut se déplacer vers une position
    // donnée
    private boolean canMoveTo(int line, int column) {
        Block block = map.getBlock(line, column);

        boolean isAccessibleType = block.getElement().equals("chemin") || block.getElement().equals("carrousel") || block.getElement().equals("chaiseVolante") || block.getElement().equals("Manege3")|| block.getElement().equals("Manege4")|| block.getElement().equals("Commerce1")|| block.getElement().equals("Commerce2");
        return isAccessibleType && !block.isLocked(); 
    }
    

    // Méthode pour vérifier l'interaction avec les carrousels
    public void checkCarouselInteraction() {
        List<Visitor> visitorsToRemove = new ArrayList<>();
        for (Visitor visitor : visitors) {
            Block visitorPosition = visitor.getPosition();
            String element = visitorPosition.getElement();
            if (element.equals("carrousel")) {
                visitorsToRemove.add(visitor);
            }
        }

        visitors.removeAll(visitorsToRemove);

        spawnVisitors();
    }

    // Méthode pour obtenir un bloc de chemin aléatoire
    private Block getRandomPathBlock() {
        List<Block> pathBlocks = new ArrayList<>();
        for (int i = 0; i < ParcConfig.LINE_COUNT; i++) {
            for (int j = 0; j < ParcConfig.COLUMN_COUNT; j++) {
                Block block = map.getBlock(i, j);
                if (block.getElement().equals("chemin")) {
                    pathBlocks.add(block);
                }
            }
        }
        if (!pathBlocks.isEmpty()) {
            return pathBlocks.get(random.nextInt(pathBlocks.size()));
        } else {
            return null;
        }
    }

    
    // Méthode pour acheter une zone du parc
    public boolean achatZone(int zone) {
        int[] zoneCosts = {1000, 1500, 2000, 2500}; // Coûts hypothétiques pour chaque zone
            // Vérifier que l'indice de la zone est valide
          if (zone < 1 || zone > zoneCosts.length) {
            return false; // Retourner faux si l'indice de la zone est invalide
        }
        // Obtenir le coût de la zone sélectionnée
        int cost = zoneCosts[zone - 1]; // Les indices de tableau commencent à 0
        // Vérifier si le joueur a assez d'argent
        if (getArgent() >= cost) {
            // Mettre à jour l'argent du joueur
            retirerArgent(cost);
    
            // Calculer la taille de la zone à déverrouiller
            int lineCount = map.getLineCount();
            int columnCount = map.getColumnCount();
            int startLine, endLine, startColumn, endColumn;

            if (zone == 1 || zone == 3) {
                startLine = 0;
                endLine = lineCount / 2;
            } else {
                startLine = lineCount / 2;
                endLine = lineCount;
            }
            
            if (zone == 1 || zone == 2) {
                startColumn = 0;
                endColumn = columnCount / 2;
            } else {
                startColumn = columnCount / 2;
                endColumn = columnCount;
            }
            
            // Déverrouiller les blocs de la zone achetée
            for (int lineIndex = startLine; lineIndex < endLine; lineIndex++) {
                for (int columnIndex = startColumn; columnIndex < endColumn; columnIndex++) {
                    Block block = map.getBlock(lineIndex, columnIndex);
                    if (block.isLocked()) { 
                        block.setLocked(false);
                    }
                }
            }
    
                return true; // Achat réussi
        }
    
            return false; // Argent insuffisant
    }
 
    public int getZoneCost(int zone) {
        int[] zoneCosts = {1000, 1500, 2000, 2500}; // Coûts hypothétiques pour chaque zone
        if (zone < 1 || zone > zoneCosts.length) {
            return 0;
        } else {
            return zoneCosts[zone - 1];
        }
        
    }

    // II DEPRECATED MOVEMENT FCTN

    public void moveUpCurseur() {
        Block position = Curseur.getPosition();
        if (position.getLine() > 0) {
            Block newPosition = map.getBlock(position.getLine() - 1, position.getColumn());
            if (!newPosition.isLocked()) { // Vérifier si le nouveau bloc n'est pas verrouillé
                Curseur.setPosition(newPosition);
            }
        }
    }

    public void moveDownCurseur() {
        Block position = Curseur.getPosition();
        if (position.getLine() < ParcConfig.LINE_COUNT - 1) {
            Block newPosition = map.getBlock(position.getLine() + 1, position.getColumn());
            if (!newPosition.isLocked()) { // Vérifier si le nouveau bloc n'est pas verrouillé
                Curseur.setPosition(newPosition);
            }
        }
    }

    public void moveLeftCurseur() {
        Block position = Curseur.getPosition();
        if (position.getColumn() > 0) {
            Block newPosition = map.getBlock(position.getLine(), position.getColumn() - 1);
            if (!newPosition.isLocked()) { // Vérifier si le nouveau bloc n'est pas verrouillé
                Curseur.setPosition(newPosition);
            }
        }
    }

    public void moveRightCurseur() {
        Block position = Curseur.getPosition();
        if (position.getColumn() < ParcConfig.COLUMN_COUNT - 1) {
            Block newPosition = map.getBlock(position.getLine(), position.getColumn() + 1);
            if (!newPosition.isLocked()) { // Vérifier si le nouveau bloc n'est pas verrouillé
                Curseur.setPosition(newPosition);
            }
        }
    }

    // III MAP

    public void moveSetCurseur(Block position) {
        Block newPosition = map.getBlock(position.getLine(), position.getColumn());
        Curseur.setPosition(newPosition);
    }

    public void changeBlock(String blockType) {
        if (!map.getBlock(Curseur.getPosition().getLine(), Curseur.getPosition().getColumn()).getElement()
                .equals(blockType)) {
            map.getBlock(Curseur.getPosition().getLine(), Curseur.getPosition().getColumn()).setElement(blockType);
            nbChemin++;
        }
    }

    public void changeBlock3x3(String blockType, int prix) {
        Block position = Curseur.getPosition();
        int line = position.getLine();
        int column = position.getColumn();

        // Assurer que la position est au moins à 1 bloc de distance des bords pour ne pas déborder
        if (line > 0 && line < ParcConfig.LINE_COUNT - 2 && column > 0 && column < ParcConfig.COLUMN_COUNT - 2) {
            boolean canPlace = true;
            // Vérifier tous les blocs dans une zone 3x3 autour du bloc central
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    Block testBlock = map.getBlock(line + i, column + j);
                    if (testBlock.isLocked() || !testBlock.getElement().equals("herbe")) {
                        canPlace = false; // Si un bloc est verrouillé ou non herbe, empêcher le placement
                        break;
                    }
                }
                if (!canPlace) break;
            }

            if (canPlace) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        map.getBlock(position.getLine() - 1 + i, position.getColumn() - 1 + j).setElement("bordStructure");
                        if (j==1 & i==1) {
                            map.getBlock(position.getLine() - 1 + i, position.getColumn() - 1 + j).setElement(blockType);
                        }
                    }
                }
                retirerArgent(prix); // Retirer l'argent après avoir placé la structure avec succès
            } else {
                JOptionPane.showMessageDialog(null,"Impossible de placer ici : le bloc est verrouillé.", "Erreur de placement", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Ne peut pas être placé sur le bord : hors limites.", "Erreur de placement", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Block cheminProche(Block start) {
        int[] directions = {-1, 0, 1};
        for (int dy : directions) {
            for (int dx : directions) {
                if (dx == 0 && dy == 0) continue; 
                int newY = start.getLine() + dy;
                int newX = start.getColumn() + dx;
                if (newY >= 0 && newY < map.getLineCount() && newX >= 0 && newX < map.getColumnCount()) {
                    Block nearbyBlock = map.getBlock(newY, newX);
                    if (nearbyBlock.getElement().equals("chemin")) {
                        return nearbyBlock;
                    }
                }
            }
        }
        return null;
    }

    public void creeStructure() {

    }

    public void supprimerStructure() {

    }

    // fonction avec un prix par défaut

    public void creeCarrousel(int prixCarrousel) {
        if (getArgent() >= prixCarrousel) {
            changeBlock3x3("carrousel", prixCarrousel); // Appel de la méthode avec le prix défini
            nbCarrousel++;
        }
        else {
            // Afficher un message d'erreur si l'argent est insuffisant
            JOptionPane.showMessageDialog(null, "Pas assez d'argent pour acheter un carrousel. Vous avez besoin " + prixCarrousel + " mais vous avez " + getArgent() + ".", "Erreur d'achat", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    public void supprimerChemin() {
        Block position = Curseur.getPosition();
        if (map.getBlock(position.getLine(), position.getColumn()).getElement().equals("chemin")) {
            List<Visitor> toRemove = new ArrayList<>();
            for (Visitor visitor : visitors) {
                if (visitor.getPosition().equals(position)) {
                    Block newBlock = cheminProche(position);
                    if (newBlock != null) {
                        visitor.setPosition(newBlock, visitor.getDirection()); // Utilise la direction actuelle
                    } else {
                        toRemove.add(visitor);
                    }
                }
            }
            visitors.removeAll(toRemove); // Supprimer les visiteurs de la liste s'ils ne peuvent pas être déplacés
            map.getBlock(position.getLine(), position.getColumn()).setElement("herbe");
            nbChemin--;
        }
    }
    
    public void supprimerCarrousel() {
        Block position = Curseur.getPosition();
        if (map.getBlock(position.getLine(), position.getColumn()).getElement().equals("carrousel")) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map.getBlock(position.getLine() - 1 + i, position.getColumn() - 1 + j).setElement("herbe");
                }
            }
            nbCarrousel--;
        }
    }

    public void creeChaiseVolante(int prixChaiseVolante) {
        if (getArgent() >= prixChaiseVolante) {
            changeBlock3x3("chaiseVolante", prixChaiseVolante);
            nbChaiseVolante++;
        }
        else {
            // Afficher un message d'erreur si l'argent est insuffisant
            JOptionPane.showMessageDialog(null, "Pas assez d'argent pour acheter une chaise volante. Vous avez besoin " + prixChaiseVolante + " mais vous avez " + getArgent() + ".", "Erreur d'achat", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void supprimerChaiseVolante() {
        Block position = Curseur.getPosition();
        if (map.getBlock(position.getLine(), position.getColumn()).getElement().equals("chaiseVolante")) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map.getBlock(position.getLine() - 1 + i, position.getColumn() - 1 + j).setElement("herbe");
                }
            }
            nbChaiseVolante--;
        }
    }

    public void creeManege3(int prix) {
        if (getArgent() >= prix) {
            changeBlock3x3("Manege3", prix);
            nbManege3++;
        }

    }

    public void supprimerManege3() {
        Block position = Curseur.getPosition();
        if (map.getBlock(position.getLine(), position.getColumn()).getElement().equals("Manege3")) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map.getBlock(position.getLine() - 1 + i, position.getColumn() - 1 + j).setElement("herbe");
                }
            }
            nbManege3--;
        }
    }

    public void creeManege4(int prix) {
        if (getArgent() >= prix) {
            changeBlock3x3("Manege4", prix);
            nbManege4++;
        }

    }

    public void supprimerManege4() {
        Block position = Curseur.getPosition();
        if (map.getBlock(position.getLine(), position.getColumn()).getElement().equals("Manege4")) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map.getBlock(position.getLine() - 1 + i, position.getColumn() - 1 + j).setElement("herbe");
                }
            }
            nbManege4--;
        }
    }

    public void creeCommerce1(int prix) {
        if (getArgent() >= prix) {
            changeBlock3x3("Commerce1", prix);
            nbCommerce1++;
        }

    }

    public void supprimerCommerce1() {
        Block position = Curseur.getPosition();
        if (map.getBlock(position.getLine(), position.getColumn()).getElement().equals("Commerce1")) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map.getBlock(position.getLine() - 1 + i, position.getColumn() - 1 + j).setElement("herbe");
                }
            }
            nbCommerce1--;
        }
    }

    public void creeCommerce2(int prix) {
        if (getArgent() >= prix) {
            changeBlock3x3("Commerce2", prix);
            nbCommerce2++;
        }

    }

    public void supprimerCommerce2() {
        Block position = Curseur.getPosition();
        if (map.getBlock(position.getLine(), position.getColumn()).getElement().equals("Commerce2")) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    map.getBlock(position.getLine() - 1 + i, position.getColumn() - 1 + j).setElement("herbe");
                }
            }
            nbCommerce2--;
        }
    }

    public void creeDeco(int prix, String blocktype){
        if (getArgent() >= prix) {
            changeBlock(blocktype);
            nbDecoration++;
        }
    }
    public void blockInfo() {
        System.out.println(map.getBlock(Curseur.getPosition().getLine(), Curseur.getPosition().getColumn()).getElement());
        System.out.print("Ligne nº" + Curseur.getPosition().getLine());
        System.out.print(" Colonne nº" + Curseur.getPosition().getColumn() + "\n");
    }

    public int getArgent() {
        return aStats.getArgent();
    }

    public void setArgent(int argent) {
        aStats.setArgent(argent);
    }

    public void updateAttractivite() {
        aStats.setAttractivite(getNbCarrousel() * 50 - (nbCarrousel * nbCarrousel * 2) + getNbChaiseVolante() * 500- (nbChaiseVolante * nbChaiseVolante * 20) + getNbManege3()*600 - (nbManege3*nbManege3*40) + getNbManege4()*2000 - (nbManege4*nbManege4*50) +getNbDecoration() * 10 );
    }

    public void updateVisiteurs() {
        aStats.setNbVisiteurs(aStats.getAttractivite() / 10);
    }

    public int CalculerDemande(){
        return getNbCarrousel()*getNbChaiseVolante()*getNbManege3()*getNbManege4()/25 +1;
    }

    public Date getDate() {
        return aStats.getDate();
    }

    public int getNbChemin() {
        return nbChemin;
    }

    public int getNbCarrousel() {
        return nbCarrousel;
    }

    public int getNbChaiseVolante() {
        return nbChaiseVolante;
    }

    public int getNbVisiteurs() {
        return aStats.getNbVisiteurs();
    }

    public Curseur getCurseur() {
        return Curseur;
    }

    public int calculerRevenue() {
        return (15 * aStats.getNbVisiteurs() * CalculerDemande() *(nbCommerce1+1)*(nbCommerce2/10 +1) );
    }

    public int calculerMaintenance() {
        return (5 * nbCarrousel + 25 * nbChaiseVolante + nbManege3*50 + nbManege4*400 + 1 * nbChemin + nbCommerce1*500 + nbCommerce2*50 );
    }

    public int calculerProfit() {
        return (calculerRevenue() - calculerMaintenance());
    }

    public void ajouterArgent(int ajout) {
        setArgent(getArgent() + ajout);
    }

    public void retirerArgent(int retrait) {
        setArgent(getArgent() - retrait);
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public ActiveStats getaStats() {
        return aStats;
    }

    public void setaStats(ActiveStats aStats) {
        this.aStats = aStats;
    }

    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public void setNbChemin(int nbChemin) {
        this.nbChemin = nbChemin;
    }

    public void setNbCarrousel(int nbCarrousel) {
        this.nbCarrousel = nbCarrousel;
    }

    public void setNbChaiseVolante(int nbChaiseVolante) {
        this.nbChaiseVolante = nbChaiseVolante;
    }

    public int getNbManege3() {
        return nbManege3;
    }

    public void setNbManege3(int nbManege3) {
        this.nbManege3 = nbManege3;
    }

    public int getNbManege4() {
        return nbManege4;
    }

    public void setNbManege4(int nbManege4) {
        this.nbManege4 = nbManege4;
    }

    public int getNbCommerce1() {
        return nbCommerce1;
    }

    public void setNbCommerce1(int nbCommerce1) {
        this.nbCommerce1 = nbCommerce1;
    }

    public int getNbCommerce2() {
        return nbCommerce2;
    }

    public void setNbCommerce2(int nbCommerce2) {
        this.nbCommerce2 = nbCommerce2;
    }

    public int getNbDecoration() {
        return nbDecoration;
    }

    public void setNbDecoration(int nbDecoration) {
        this.nbDecoration = nbDecoration;
    }

    public void setCurseur(Curseur curseur) {
        Curseur = curseur;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public int getMaxVisitors() {
        return maxVisitors;
    }

    public void setMaxVisitors(int maxVisitors) {
        this.maxVisitors = maxVisitors;
    }



}