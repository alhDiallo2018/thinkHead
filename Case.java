public class Case {
    private int valeur;
    private Joueur jouePar;
    private Position position;
    private boolean selectionnee;

    public Case(int valeur) {
        this.valeur = valeur;
        this.selectionnee = false;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Joueur getJouePar() {
        return jouePar;
    }

    public void setJouePar(Joueur jouePar) {
        this.jouePar = jouePar;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean estSelectionnee() {
        return selectionnee;
    }

    public void selectionner() {
        this.selectionnee = true;
    }

    public boolean estLibre() {
        return jouePar == null;
    }

    @Override
    public String toString() {
        return Integer.toString(valeur);
    }

    
}
