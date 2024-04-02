import java.util.Scanner;

public class TestThinkAhead {
    public static void main(String[] args) {
        Grille grille = new Grille(2, 2);

        grille.afficherGrille();

        JoueurHumain joueur1 = new JoueurHumain("Alain", 0); // Renommez le joueur "Alain"
        JoueurMachine joueur2 = new JoueurMachine("Ordinateur", 1, 2);

        Partie partie = new Partie(grille, new Joueur[]{joueur1, joueur2}, 3);
        Scanner scanner = new Scanner(System.in); // Créer un objet Scanner pour la saisie utilisateur

        Position position = new Position(0, 0);

        

        int tour = 1;
        while (true) {
            Joueur joueurCourant =partie.getJoueur(tour % 2);
            System.out.println("Coup n°" + tour + " (" + joueurCourant.getNom() + "):");
            
            AllignementCase alignementActif = partie.getAllignementActif();
            if (alignementActif != null) {
                System.out.println("Vous devez jouer dans la " + alignementActif.getOrientation() + " n°" + alignementActif.toStringP());
                System.out.println("Les positions possibles sont : " + grille.afficherPositionsPossibles()); // Affichage des positions possibles
                
                
                if (partie.getJoueur(tour % 2) instanceof JoueurHumain) {
                    // System.out.print("Saisissez la position où vous jouez (ligne colonne) : ");
                    // int ligne = scanner.nextInt();
                    // int colonne = scanner.nextInt();
                    // position = new Position(ligne, colonne);

                    joueur1.choisirPosition(grille);

                    //System.out.println("La partie est terminée.");
                    
                    System.out.println("Score de " + joueur1.getNom() + " : " + joueur1.getScore());
                } else {
                    position = partie.selectionnerPositionOrdinateur(grille);
                }
                
                System.out.println("Vous avez joué à la position : " + position);
                partie.selectionnerCase(position, partie.getJoueur(0));
                grille.afficherGrille();
                tour++;

                // Vérifier si la partie est terminée
                if (partie.partieTerminee()) {
                    break;
                }
            } else {
                System.out.println("Aucun alignement actif.");
                break;
            }
        }
       
        scanner.close(); // Fermer le scanner après utilisation pour éviter les fuites de ressources
    }

}
