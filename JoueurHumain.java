import java.util.Scanner;

public class JoueurHumain extends Joueur {

    // protected int score;

    public JoueurHumain(String nom, int index) {
        super(nom, index);
        this.score = 0;
    }

    public Position choisirPosition(Grille grille) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("hello workd");

        while (true) {
            System.out.print("Saisissez la position jouuuuoù vous jouez (ligne colonne) : ");
            int ligne = scanner.nextInt();
            int colonne = scanner.nextInt();

            if (estPositionValide(grille, ligne, colonne)) {
                int valeurCase = grille.getValeurCase(ligne, colonne);
                this.score += valeurCase;
                return new Position(ligne, colonne);
            }
            scanner.close();
        }
    }

    private boolean estPositionValide(Grille grille, int x, int y) {
        // Crée une instance de Position avec les coordonnées (x, y)
        Position position = new Position(x, y);

        // Vérifie si la position est valide dans la grille
        if (!grille.estPositionValide(position)) {
            System.out.println("Position invalide. Veuillez saisir à nouveau.");
            return false;
        }

        // Vérifie si la case est déjà sélectionnée
        if (grille.getCase(position).estSelectionnee()) {
            System.out.println("La case est déjà sélectionnée. Veuillez saisir à nouveau.");
            return false;
        }

        // Vérifie si c'est au joueur de choisir selon la règle
        AllignementCase alignementActif = grille.getAlignementActif();
        if (alignementActif != null) {
            if (alignementActif.getOrientation() == Orientation.ligne && y != alignementActif.getPosition()) {
                System.out.println(
                        "Vous devez choisir une colonne correspondant à l'alignement actif. Veuillez saisir à nouveau.");
                return false;
            }
            if (alignementActif.getOrientation() == Orientation.colonne && x != alignementActif.getPosition()) {
                System.out.println(
                        "Vous devez choisir une ligne correspondant à l'alignement actif. Veuillez saisir à nouveau.");
                return false;
            }
        }

        return true;
    }

}
