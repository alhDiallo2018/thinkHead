public class Joueur {
    private String nom;
    private int index;
    protected int score;
    private int nombreCoupsJoues;

    public Joueur(String nom, int index) {
        this.nom = nom;
        this.index = index;
        this.score =0;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "nom='" + nom + '\'' +
                '}';
    }

    public int getIndex() {
        return index;
    }

    public String getNom() {
        return nom;
    }

    public int getScore() {
        return score;
    }

    public int getNombreCoupsJoues() {
        return nombreCoupsJoues;
    }
    
}
