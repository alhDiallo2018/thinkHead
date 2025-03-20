import java.util.Random;

public class JoueurMachine extends Joueur {
    private int niveau;

    public JoueurMachine(String nom, int index, int niveau) {
        super(nom, index);
        this.niveau = niveau;
    }

    @Override
    public Position choisirPosition(Grille grille, AllignementCase alignementActif) {
        Random random = new Random();
        Position position;

        do {
            // Choisir une case aléatoire dans l'alignement actif
            if (alignementActif.getOrientation() == Orientation.LIGNE) {
                int ligne = alignementActif.getPosition();
                int colonne = random.nextInt(grille.getColumns());
                position = new Position(ligne, colonne);
            } else {
                int colonne = alignementActif.getPosition();
                int ligne = random.nextInt(grille.getRows());
                position = new Position(ligne, colonne);
            }
        } while (!grille.estPositionValide(position) || grille.getCase(position).estSelectionnee());

        return position;
    }
}
