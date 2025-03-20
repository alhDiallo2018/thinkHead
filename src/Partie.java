import java.util.Random;

public class Partie {
    private Grille grille;
    private Joueur[] joueurs;
    private int tour;
    private AllignementCase alignementActif;

    public Partie(Grille grille, Joueur[] joueurs) {
        this.grille = grille;
        this.joueurs = joueurs;
        this.tour = 0;
        this.alignementActif = initialiserAlignementActif();
    }

    private AllignementCase initialiserAlignementActif() {
        Random random = new Random();
        Orientation orientation = random.nextBoolean() ? Orientation.LIGNE : Orientation.COLONNE;
        int position = random.nextInt(grille.getRows());
        return new AllignementCase(orientation, grille.getCases(), position);
    }

    public void commencer() {
        while (!partieTerminee()) {
            jouerTour();
        }
        afficherResultats();
    }

    private void jouerTour() {
        Joueur joueurActuel = joueurs[tour % joueurs.length];
        System.out.println("Tour de " + joueurActuel.getNom());

        Position position;
        if (joueurActuel instanceof JoueurHumain) {
            position = ((JoueurHumain) joueurActuel).choisirPosition(grille, alignementActif);
        } else {
            position = ((JoueurMachine) joueurActuel).choisirPosition(grille, alignementActif);
        }

        grille.selectionnerCase(position, joueurActuel);
        grille.afficherGrille();

        // Mettre à jour l'alignement actif pour le prochain tour
        alignementActif = calculerNouvelAlignement(position);

        // Afficher les scores après chaque tour
        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getNom() + " : " + joueur.getScore() + " points");
        }

        // Afficher les cases restantes (pour débogage, à commenter en production)
        grille.afficherCasesRestantes();

        tour++;
    }

    private AllignementCase calculerNouvelAlignement(Position position) {
        Orientation nouvelleOrientation = (alignementActif.getOrientation() == Orientation.LIGNE) ? Orientation.COLONNE : Orientation.LIGNE;
        int nouvellePosition = (nouvelleOrientation == Orientation.LIGNE) ? position.getPosX() : position.getPosY();
        return new AllignementCase(nouvelleOrientation, grille.getCases(), nouvellePosition);
    }

    private void afficherResultats() {
        System.out.println("Résultats finaux :");
        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getNom() + " : " + joueur.getScore() + " points");
        }
    }

    private boolean partieTerminee() {
        if (grille.estPleine()) {
            return true;
        }

        // Vérifier si la dernière case d'une ligne ou colonne a été sélectionnée
        for (int i = 0; i < grille.getRows(); i++) {
            boolean ligneComplete = true;
            for (int j = 0; j < grille.getColumns(); j++) {
                if (!grille.getCase(new Position(i, j)).estSelectionnee()) {
                    ligneComplete = false;
                    break;
                }
            }
            if (ligneComplete) {
                return true;
            }
        }

        for (int j = 0; j < grille.getColumns(); j++) {
            boolean colonneComplete = true;
            for (int i = 0; i < grille.getRows(); i++) {
                if (!grille.getCase(new Position(i, j)).estSelectionnee()) {
                    colonneComplete = false;
                    break;
                }
            }
            if (colonneComplete) {
                return true;
            }
        }

        return false;
    }
}
