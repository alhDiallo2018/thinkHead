public class AllignementCase {
    private Orientation orientation;
    private Case[][] lesCases;
    private int position;

    public AllignementCase(Orientation orientation, Case[][] lesCases, int position) {
        this.orientation = orientation;
        this.lesCases = lesCases;
        this.position = position;
    }

    public Case getCaseNum(int num) {
        if (orientation == Orientation.LIGNE) {
            return lesCases[position][num];
        } else {
            return lesCases[num][position];
        }
    }

    public Case getCaseLibre(int num) {
        if (orientation == Orientation.LIGNE) {
            for (int j = 0; j < lesCases[position].length; j++) {
                if (!lesCases[position][j].estSelectionnee()) {
                    return lesCases[position][j];
                }
            }
        } else {
            for (int i = 0; i < lesCases.length; i++) {
                if (!lesCases[i][position].estSelectionnee()) {
                    return lesCases[i][position];
                }
            }
        }
        return null;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Alignement: " + orientation + " à la position " + position;
    }
}
