public class TestThinkAhead {
    public static void main(String[] args) {
        Grille grille = new Grille(3, 3);
        JoueurHumain joueurHumain = new JoueurHumain("Alain", 0);
        JoueurMachine joueurMachine = new JoueurMachine("Ordinateur", 1, 1);

        Partie partie = new Partie(grille, new Joueur[]{joueurHumain, joueurMachine});
        partie.commencer();
    }
}
