package data;

public class ActiveStats {
    private int argent;
    private Date date;
    private int attractivite;
    private int tailleParc;
    private int nbVisiteurs;

    public ActiveStats() {
        this.argent = 4000;
        this.date = new Date();
        this.attractivite = 0;
        this.tailleParc = 0;
        this.nbVisiteurs = 0;
    }

    public ActiveStats(int argent, Date date, int attractivite, int tailleParc) {
        this.argent = argent;
        this.date = date;
        this.attractivite = attractivite;
        this.tailleParc = tailleParc;
        this.nbVisiteurs = 0;
    }

    public int getArgent() {
        return argent;
    }
    public void setArgent(int argent) {
        this.argent = argent;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getAttractivite() {
        return attractivite;
    }
    public void setAttractivite(int attractivite) {
        this.attractivite = attractivite;
    }
    public int getTailleParc() {
        return tailleParc;
    }
    public void setTailleParc(int tailleParc) {
        this.tailleParc = tailleParc;
    }

    public int getNbVisiteurs() {
        return nbVisiteurs;
    }

    public void setNbVisiteurs(int nbVisiteurs) {
        this.nbVisiteurs = nbVisiteurs;
    }
    
}
