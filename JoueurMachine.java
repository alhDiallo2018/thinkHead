public class JoueurMachine extends Joueur {
    public int niveau;

    public JoueurMachine(String nom, int niveau, int index) {
        super(nom, index);
        this.niveau = niveau;
    }
}
