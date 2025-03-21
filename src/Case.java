public class Case {
    private int valeur;
    private Joueur jouePar;
    private boolean selectionnee;

    public Case(int valeur) {
        this.valeur = valeur;
        this.selectionnee = false;
    }

    public int getValeur() {
        return valeur;
    }

    public Joueur getJouePar() {
        return jouePar;
    }

    public void setJouePar(Joueur joueur) {
        this.jouePar = joueur;
    }

    public boolean estSelectionnee() {
        return selectionnee;
    }

    public void selectionner() {
        this.selectionnee = true;
    }

    @Override
    public String toString() {
        return String.valueOf(valeur);
    }
}
