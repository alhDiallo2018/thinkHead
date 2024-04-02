public class Coup {
    private Joueur joueur;
    private Position positionCase;
    private Orientation orientation;

    public Coup(Joueur joueur, Position p, Orientation orientation) {
        this.joueur = joueur;
        this.positionCase = p;
        this.orientation = orientation;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Position getPositionCase() {
        return positionCase;
    }

    public void setPositionCase(Position positionCase) {
        this.positionCase = positionCase;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void afficher() {
        System.out.println("Coup (" + joueur.getNom() + ")");
        System.out.println("Vous devez jouer dans la " + orientation + " n°" + positionCase);
    }
}