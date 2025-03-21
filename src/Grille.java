import java.util.Random;

public class Grille {
    private int rows;
    private int columns;
    private Case[][] lesCases;

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
        System.out.println("Grille :");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(lesCases[i][j] + " ");
            }
            System.out.println();
        }
    }

    public Case getCase(Position pos) {
        return lesCases[pos.getPosX()][pos.getPosY()];
    }

    public Case[][] getCases() {
        return lesCases;
    }

    public boolean selectionnerCase(Position pos, Joueur joueur) {
        Case laCase = getCase(pos);
        if (!laCase.estSelectionnee()) {
            laCase.setJouePar(joueur);
            laCase.selectionner();
            joueur.ajouterScore(laCase.getValeur());
            return true;
        }
        return false;
    }

    public boolean estPositionValide(Position pos) {
        return pos.getPosX() >= 0 && pos.getPosX() < rows && pos.getPosY() >= 0 && pos.getPosY() < columns;
    }

    public void afficherCasesRestantes() {
        System.out.println("Cases restantes :");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!lesCases[i][j].estSelectionnee()) {
                    System.out.println("Case [" + i + ", " + j + "] : " + lesCases[i][j].getValeur());
                }
            }
        }
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

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
