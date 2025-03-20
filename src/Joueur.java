public abstract class Joueur {
    private String nom;
    private int index;
    private int score;

    public Joueur(String nom, int index) {
        this.nom = nom;
        this.index = index;
        this.score = 0;
    }

    public String getNom() {
        return nom;
    }

    public int getIndex() {
        return index;
    }

    public int getScore() {
        return score;
    }

    public void ajouterScore(int valeur) {
        this.score += valeur;
    }

    // Méthode abstraite à implémenter par les sous-classes
    public abstract Position choisirPosition(Grille grille, AllignementCase alignementActif);
}
