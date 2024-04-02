public class ControleurJeu {
    public Position choisirPosition(Grille grille) {
        int rows = grille.getRows();
        int columns = grille.getColumns();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Case currentCase = grille.getCase(new Position(i, j));
                if (!currentCase.estSelectionnee()) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }
}
