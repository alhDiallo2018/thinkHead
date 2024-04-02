import java.util.*;

public class Partie {
    private Grille laGrille;
    private Joueur[] joueurs;
    private Coup[][] lesCoups;
    private int tour;
    private Joueur joueur1;
    private Joueur joueur2;
    private int seuil;

    public Partie(Grille laGrille, Joueur[] joueurs, int seuil) {
        this.laGrille = laGrille;
        this.joueurs = joueurs;
        this.seuil = seuil;
        this.lesCoups = new Coup[laGrille.getRows() * laGrille.getColumns()][joueurs.length];
        this.tour = 0;

        if (joueurs.length >= 1) {
            this.joueur1 = joueurs[0];
        }
        if (joueurs.length >= 2) {
            this.joueur2 = joueurs[1];
        }
    }

    public void commencer() {
        while (!partieTerminee()) {
            jouerTour();
        }
        afficherResultats();
    }


    private Position choisirPosition(Grille grille) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Les positions possibles sont : " + grille.getPositionLibres());
            System.out.print("Saisissez la position où vous jouez (ligne colonne) : ");
            int ligne = scanner.nextInt();
            int colonne = scanner.nextInt();
            Position position = new Position(ligne, colonne);
            if (grille.estPositionValide(position) && grille.getCase(position).estLibre()) {
                return position;
            } else {
                System.out.println("Position invalide ou occupée. Réessayez.");
            }
            scanner.close();
        }
    }

    public boolean partieTerminee() {
        return laGrille.estPleine();
    }

    private void afficherResultats() {
        for (Joueur joueur : joueurs) {
            System.out.println(joueur.getNom() + " : " + joueur.getScore());
        }
    }

    public void selectionnerCase(Position pos, Joueur joueur) {
        laGrille.selectionnerCase(pos, joueur);
        Orientation orientation = getPositionOrientation(pos);
        if (orientation != null) {
            for (int i = 0; i < lesCoups.length; i++) {
                // Vérifier si l'indice de joueur est valide et si l'élément correspondant est null
                if (i < lesCoups.length && joueur.getIndex() >= 0 && joueur.getIndex() < lesCoups[i].length && lesCoups[i][joueur.getIndex()] == null) {
                    lesCoups[i][joueur.getIndex()] = new Coup(joueur, pos, orientation);
                    break;
                }
            }
        } else {
            System.out.println("L'orientation du coup ne peut pas être déterminée pour la position : " + pos);
        }
    }
    
    
    private Orientation getPositionOrientation(Position pos) {
        int selectedInRow = 0;
        int selectedInColumn = 0;
    
        // Vérification des limites de la grille
        if (pos.getPos_x() < 0 || pos.getPos_x() >= laGrille.getRows() || pos.getPos_y() < 0 || pos.getPos_y() >= laGrille.getColumns()) {
            return null; // Retourner null si la position est en dehors des limites de la grille
        }
    
        // Vérifier la ligne correspondant à la position donnée
        for (int i = 0; i < laGrille.getColumns(); i++) {
            if (laGrille.getCase(new Position(pos.getPos_x(), i)).estSelectionnee()) {
                selectedInRow++;
            }
        }
    
        // Vérifier la colonne correspondant à la position donnée
        for (int j = 0; j < laGrille.getRows(); j++) {
            if (laGrille.getCase(new Position(j, pos.getPos_y())).estSelectionnee()) {
                selectedInColumn++;
            }
        }
    
        if (selectedInRow >= seuil) {
            return Orientation.colonne;
        } else if (selectedInColumn >= seuil) {
            return Orientation.ligne;
        } else {
            return null; // Retourner null si l'orientation du coup ne peut pas être déterminée
        }
    }
    
    
    private Position choisirPositionOrdinateur(Grille grille) {
        Random random = new Random();
        int rows = grille.getRows();
        int columns = grille.getColumns();
        Position position;
        do {
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            position = new Position(row, column);
        } while (grille.getCase(position).estSelectionnee());
        return position;
    }

    public AllignementCase getAllignementActif() {
        if (grilleEstVide()) {
            return new AllignementCase(Orientation.ligne, new Case[0][0], 0);
        }

        int nombreDeCoups = getTotalCoupsJoues();

        Orientation orientation = (nombreDeCoups % 2 == 0) ? Orientation.ligne : Orientation.colonne;
        int position = nombreDeCoups / 2;

        AllignementCase alignementActif = new AllignementCase(orientation, obtenirCasesAlignees(orientation, position), position);

        return alignementActif;
    }

    private boolean grilleEstVide() {
        for (int i = 0; i < laGrille.getRows(); i++) {
            for (int j = 0; j < laGrille.getColumns(); j++) {
                if (laGrille.getCase(new Position(i, j)).estSelectionnee()) {
                    return false;
                }
            }
        }
        return true;
    }

    private Case[][] obtenirCasesAlignees(Orientation orientation, int position) {
        int rows = laGrille.getRows();
        int cols = laGrille.getColumns();
        Case[][] casesAlignees;

        if (orientation == Orientation.ligne) {
            casesAlignees = new Case[1][cols];
            for (int j = 0; j < cols; j++) {
                casesAlignees[0][j] = laGrille.getCase(new Position(position, j));
            }
        } else {
            casesAlignees = new Case[rows][1];
            for (int i = 0; i < rows; i++) {
                casesAlignees[i][0] = laGrille.getCase(new Position(i, position));
            }
        }

        return casesAlignees;
    }

    private int getTotalCoupsJoues() {
        return joueur1.getNombreCoupsJoues() + joueur2.getNombreCoupsJoues();
    }

    public Joueur getJoueur(int index) {
        if (index >= 0 && index < joueurs.length) {
            return joueurs[index];
        } else {
            throw new IllegalArgumentException("Index de joueur invalide : " + index);
        }
    }
    
    public Position selectionnerPositionOrdinateur(Grille grille) {
        Random random = new Random();
        int rows = grille.getRows();
        int columns = grille.getColumns();
        Position position;
        do {
            int row = random.nextInt(rows);
            int column = random.nextInt(columns);
            position = new Position(row, column);
        } while (grille.getCase(position).estSelectionnee());
        return position;
    }
    

    @Override
    public String toString() {
        return "(" + laGrille.getRows() + ", " + laGrille.getColumns() + ")";
    }
    

    public Position getPositionJoueur(int indexJoueur) {
        if (indexJoueur >= 0 && indexJoueur < joueurs.length) {
            Joueur joueur = joueurs[indexJoueur];
            // Si le joueur est un joueur humain, utilisez sa méthode choisirPosition
            if (joueur instanceof JoueurHumain) {
                return ((JoueurHumain) joueur).choisirPosition(laGrille);
            }
            // Si le joueur est un joueur machine, utilisez la méthode de la partie pour sélectionner une position pour l'ordinateur
            else if (joueur instanceof JoueurMachine) {
                return selectionnerPositionOrdinateur(laGrille);
            }
        }
        // Si l'index du joueur est invalide, retournez null
        return null;
    }
    
    public Position[] getPositionsPossiblesJoueurHumain() {
        return laGrille.getPositionLibres();
    }
    
    public void jouerTour() {
        Joueur joueurActuel = joueurs[tour % joueurs.length];
        System.out.println("C'est au tour de " + joueurActuel.getNom());
        Position position;
        if (joueurActuel instanceof JoueurHumain) {
            position = choisirPosition(laGrille);
        } else {
            position = choisirPositionOrdinateur(laGrille);
        }
        selectionnerCase(position, joueurActuel);
        tour++;
    
        // Vérifier si le joueur précédent a sélectionné la dernière case de sa ligne ou de sa colonne
        AllignementCase alignementActif = getAllignementActif();
        if (alignementActif != null && alignementActif.getPosition() == laGrille.getRows() - 1 &&
                alignementActif.getOrientation() == Orientation.ligne) {
            System.out.println("Le joueur précédent a sélectionné la dernière case de sa ligne. La partie est terminée.");
            afficherResultats();
            return;
        } else if (alignementActif != null && alignementActif.getPosition() == laGrille.getColumns() - 1 &&
                alignementActif.getOrientation() == Orientation.colonne) {
            System.out.println("Le joueur précédent a sélectionné la dernière case de sa colonne. La partie est terminée.");
            afficherResultats();
            return;
        }
    
        if (partieTerminee()) {
            System.out.println("La grille est pleine. La partie est terminée.");
            afficherResultats();
        }
    }
    
   
}
    


