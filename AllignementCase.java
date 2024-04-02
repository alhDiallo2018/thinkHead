public class AllignementCase{
    private Orientation orientation;
    private Case[][] lesCases;
    private int position;

    public AllignementCase(Orientation orientation, Case[][] lesCases, int position) {
        this.orientation = orientation;
        this.lesCases = lesCases;
        this.position = position;
    }

    public Case getCaseNum(int num) {
        if (orientation == Orientation.ligne) {
            return lesCases[position][num];
        } else {
            return lesCases[num][position];
        }
    }

    public Case getCaseLibre(int num) {
        for (Case[] ligne : lesCases) {
            if (!ligne[num].estSelectionnee()) {
                return ligne[num];
            }
        }
        return null;
    }

    public Case getCaseLibreValeurMax(int num) {
        Case maxCase = null;
        int maxValue = Integer.MIN_VALUE;
        for (Case[] ligne : lesCases) {
            if (!ligne[num].estSelectionnee() && ligne[num].getValeur() > maxValue) {
                maxCase = ligne[num];
                maxValue = maxCase.getValeur();
            }
        }
        return maxCase;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Position[] getPositionsLibres() {
        int count = 0;
        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[i].length; j++) {
                if (!lesCases[i][j].estSelectionnee()) {
                    count++;
                }
            }
        }

        Position[] positionsLibres = new Position[count];
        int index = 0;
        for (int i = 0; i < lesCases.length; i++) {
            for (int j = 0; j < lesCases[i].length; j++) {
                if (!lesCases[i][j].estSelectionnee()) {
                    positionsLibres[index] = lesCases[i][j].getPosition();
                    index++;
                }
            }
        }
        return positionsLibres;
    }

    public int getPosition() {
        return position;
    }

    public String toStringO() {
        StringBuilder sb = new StringBuilder();
        
        String orientationString = getOrientation() + "\n";
        sb.append(orientationString);
    
        return sb.toString();
    }

    public String[] toStringT() {
        // Obtenir les positions libres
        Position[] positionsLibres = getPositionsLibres();
        
        // Convertir les positions libres en chaînes de caractères
        String[] positionsString = new String[positionsLibres.length];
        for (int i = 0; i < positionsLibres.length; i++) {
            if (positionsLibres[i] != null) {
                positionsString[i] = positionsLibres[i].toString();
            } else {
                positionsString[i] = "Position inconnue";
            }
        }
        
        return positionsString;
    }
    
    
    
    public String toStringP() {
        StringBuilder sb = new StringBuilder();
        
        String positionString = "Position: " + getPosition() + "\n";
        sb.append(positionString);
    
    
       
    
        return sb.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Orientation: ").append(orientation).append("\n");
        sb.append("Position: ").append(position).append("\n");
        sb.append("Cases libres:\n");
        for (Case[] ligne : lesCases) {
            for (Case uneCase : ligne) {
                sb.append(uneCase).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    
    
    
    
}
