Think Ahead - Projet Java
Bienvenue dans le projet Think Ahead, un jeu de stratégie basé sur une grille où deux joueurs (un humain et une machine) s'affrontent pour maximiser leur score en sélectionnant des cases selon des règles d'alignement spécifiques.

Structure du projet
Le projet est organisé comme suit :

src : Contient les fichiers sources Java.

modele : Les classes principales du jeu (Partie, Grille, Joueur, etc.).

modeTexte : Le mode texte pour tester le jeu en console.

modeGraphique : Le mode graphique (à implémenter).

bin : Contient les fichiers compilés (.class).

lib : Contient les dépendances externes (fichiers .jar).

out : Dossier de sortie généré par IntelliJ IDEA.

README.md : Ce fichier, contenant la documentation du projet.

Comment démarrer
Prérequis
Java Development Kit (JDK) : Assurez-vous d'avoir installé JDK 11 ou une version ultérieure.

IDE : Utilisez IntelliJ IDEA, Visual Studio Code ou tout autre IDE compatible Java.

Cloner le dépôt
Clonez ce dépôt sur votre machine locale :

bash
Copy
git clone https://github.com/alhDiallo2018/thinkHead.git
Ouvrez le projet dans votre IDE.

Compiler et exécuter
Compilez le projet :

Dans IntelliJ IDEA : Build > Rebuild Project.

En ligne de commande : javac -d bin src/**/*.java.

Exécutez le projet :

Dans IntelliJ IDEA : Exécutez la classe TestThinkAhead dans modeTexte.

En ligne de commande :

bash
Copy
java -cp bin TestThinkAhead
Règles du jeu
Grille : Le jeu se déroule sur une grille carrée de taille variable. Chaque case contient une valeur numérique.

Alignement : Les joueurs doivent alterner entre les lignes et les colonnes pour sélectionner des cases.

Score : Le score d'un joueur est la somme des valeurs des cases qu'il a sélectionnées.

Fin de partie : La partie se termine lorsque toutes les cases sont sélectionnées ou lorsqu'un joueur sélectionne la dernière case d'une ligne ou d'une colonne.

Stratégies de l'ordinateur
L'ordinateur utilise plusieurs stratégies pour choisir ses coups :

Aléatoire : Choisit une case libre au hasard.

Maximisation : Choisit la case libre avec la valeur maximale.

Anticipation : Choisit une case pour maximiser l'écart de score avec l'adversaire.

Personnalisation
Modifier la taille de la grille
Ouvrez la classe Grille dans modele et modifiez les paramètres du constructeur :

java
Copy
Grille grille = new Grille(3, 3); // Grille 5x5
Ajouter des stratégies
Implémentez de nouvelles stratégies dans la classe JoueurMachine en ajoutant des méthodes dans choisirPosition.

Dépendances
Le projet utilise les dépendances suivantes :

JUnit : Pour les tests unitaires (optionnel).

JavaFX : Pour le mode graphique (à implémenter).

Pour gérer les dépendances, utilisez le fichier pom.xml si vous utilisez Maven, ou ajoutez les fichiers .jar dans le dossier lib.

Contribuer
Les contributions sont les bienvenues ! Pour contribuer :

Forkez ce dépôt.

Créez une nouvelle branche :

bash
Copy
git checkout -b feature/nouvelle-fonctionnalite
Faites vos modifications et committez-les :

bash
Copy
git commit -m "Ajout d'une nouvelle fonctionnalité"
Poussez vos modifications :

bash
Copy
git push origin feature/nouvelle-fonctionnalite
Ouvrez une Pull Request.

Auteurs
Alhassane Diallo(Thierno Ono Garki) - Développeur principal - alhDiallo2018
Technicien Supérieur en Developpement Backend dans l'Institut Supérieur de l'Enseigneemnt Professionnel Amadou Trawaré de Diamniadio (ISEP-AT) P4 2023-2025
Licence
Ce projet est sous licence MIT. Voir le fichier LICENSE pour plus de détails.

Remerciements
Merci à VS Code Java pour leur documentation sur la gestion des dépendances.

Merci à tous les contributeurs pour leurs idées et suggestions.
