package data;

public class Date {
    private int annee;
    private int mois;
    private int jour;
    private int heure;
    private int minute;

    // constructeur par défaut
    public Date() {
        this.annee = 0;
        this.mois = 0;
        this.jour = 0;
        this.heure = 0;
        this.minute = 0;
    }

    // constructeur à partir de sauvegarde
    public Date(int annee, int mois, int jour, int heure, int minute) {
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
        this.heure = heure;
        this.minute = minute;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getHeure() {
        return heure;
    }

    public void setHeure(int heure) {
        this.heure = heure;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void resetToInitial() {
        this.annee = 2024; 
        this.mois = 1;     
        this.jour = 1;      
        this.heure = 0;     
        this.minute = 0;    
    }

    public void incrementMinute() {
        this.minute++;
        if (this.minute >= 60) {
            this.minute = this.minute - 60;
            this.heure++;
        }
        if (this.heure >= 24) {
            this.heure = this.heure - 24;
            this.jour++;
        }
        if (this.jour >= 30) {
            this.jour = this.jour - 30;
            this.mois++;
        }
        if (this.mois >= 12) {
            this.mois = this.mois - 12;
            this.annee++;
        }
    }

    public String getStringDate() {
        return (this.jour + "/" + this.mois + "/" + this.annee + " " + this.heure + ":" + this.minute);
    }
}
