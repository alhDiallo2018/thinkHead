import java.util.Random;


public class Grille {
    private int rows;
    private int columns;
    private Case[][] lesCases;
    private AllignementCase alignementActif;

    public Grille(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.lesCases = new Case[rows][columns];
        initialiserGrille();
    }

    private void initialiserGrille() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                lesCases[i][j] = new Case(random.nextInt(10));
            }
        }
    }

    public void afficherGrille() {
        System.out.print("   ");
        for (int i = 0; i < lesCases[0].length; i++) {
            System.out.print("" + i + " ");
        }
        System.out.println();

        System.out.print("  +--");
        for (int i = 0; i < lesCases[0].length; i++) {
            System.out.print("--");
        }
        System.out.println("+");

        for (int i = 0; i < lesCases.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < lesCases[i].length; j++) {
                System.out.print("|" + lesCases[i][j] + " ");
            }
            System.out.println("|");

            System.out.print("  +--");
            for (int j = 0; j < lesCases[i].length; j++) {
                System.out.print("--");
            }
            System.out.println("+");
        }
    }

    public Case getCase(Position pos) {
        return lesCases[pos.getPos_x()][pos.getPos_y()];
    }

    public boolean selectionnerCase(Position pos, Joueur joueur) {
        Case laCase = lesCases[pos.getPos_x()][pos.getPos_y()];
        if (!laCase.estSelectionnee()) {
            laCase.setJouePar(joueur);
            laCase.selectionner();
            return true;
        }
        return false;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Position[] getPositionLibres() {
        int count = 0;
        // Compter d'abord le nombre de positions libres
        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[i].length; j++) {
                if (!lesCases[i][j].estSelectionnee()) {
                    count++;
                }
            }
        }
        
        Position[] positionsLibres = new Position[count];
        count = 0; // Réinitialiser le compteur
        // Enregistrer les positions libres
        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[i].length; j++) {
                if (!lesCases[i][j].estSelectionnee()) {
                    positionsLibres[count] = new Position(i, j);
                    count++;
                }
            }
        }
        
        return positionsLibres;
    }

    public boolean estPleine() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!lesCases[i][j].estSelectionnee()) {
                    return false;
                }
            }
        }
        return true;
    }

   
   
    
    public Position[] getPositionsLibres() {
        int count = 0;
    
        // Compter d'abord le nombre de positions libres
        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[i].length; j++) {
                if (!lesCases[i][j].estSelectionnee() && estPositionValide(new Position(i, j))) {
                    count++;
                }
            }
        }
    
        Position[] positionsLibres = new Position[count];
        count = 0; // Réinitialiser le compteur
    
        // Enregistrer les positions libres
        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[i].length; j++) {
                if (!lesCases[i][j].estSelectionnee() && estPositionValide(new Position(i, j))) {
                    positionsLibres[count] = new Position(i, j);
                    count++;
                }
            }
        }
    
        return positionsLibres;
    }
    
    public boolean estPositionValide(Position pos) {
        return pos.getPos_x() >= 0 && pos.getPos_x() < lesCases.length && pos.getPos_y() >= 0 && pos.getPos_y() < lesCases[0].length;
    }
    
    
    public String afficherPositionsPossibles() {
        StringBuilder sb = new StringBuilder();
        sb.append("Positions possibles :\n");
        Position[] positionsLibres = getPositionsLibres();
        for (Position position : positionsLibres) {
            sb.append("(").append(position.getPos_x()).append(", ").append(position.getPos_y()).append(")\n");
        }
        return sb.toString();
    }
    
    

    public int getNbLignes() {
        return lesCases.length;
    }

    public int getNbColonnes() {
        // Par exemple, si votre grille est rectangulaire, vous pouvez utiliser :
        return lesCases[0].length;
    }
    
    public AllignementCase getAlignementActif() {
        return alignementActif;
    }
    
    

    public int getValeurCase(int ligne, int colonne) {
        Position position = new Position(ligne, colonne);
        if (estPositionValide(position)) {
            Case uneCase = getCase(position);
            return uneCase.getValeur();
        } else {
            // Gérer les cas où la position spécifiée est invalide
            return -1; // Ou une autre valeur par défaut selon votre logique
        }
    }
    
    
   
} 
