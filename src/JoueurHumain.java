import java.util.Scanner;

public class JoueurHumain extends Joueur {

    public JoueurHumain(String nom, int index) {
        super(nom, index); // Appel du constructeur de la classe parente avec nom et index
    }

    @Override
    public Position choisirPosition(Grille grille, AllignementCase alignementActif) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Saisissez la position (ligne colonne) : ");
            int ligne = scanner.nextInt();
            int colonne = scanner.nextInt();
            Position position = new Position(ligne, colonne);

            // Vérifier si la case est dans l'alignement actif
            if (alignementActif != null) {
                if (alignementActif.getOrientation() == Orientation.LIGNE && ligne != alignementActif.getPosition()) {
                    System.out.println("Vous devez jouer dans la ligne " + alignementActif.getPosition());
                    continue; // Demander à nouveau la position
                } else if (alignementActif.getOrientation() == Orientation.COLONNE && colonne != alignementActif.getPosition()) {
                    System.out.println("Vous devez jouer dans la colonne " + alignementActif.getPosition());
                    continue; // Demander à nouveau la position
                }
            }

            // Vérifier si la case est valide et non sélectionnée
            if (grille.estPositionValide(position) && !grille.getCase(position).estSelectionnee()) {
                return position;
            } else {
                System.out.println("Position invalide ou occupée. Réessayez.");
            }
        }
    }
}
