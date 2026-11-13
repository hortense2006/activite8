import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Graphe g = new Graphe();
        g.charger("exercice7.txt"); // Chargement du fichier de l'exercice 7
        g.afficherListeSucc(); 
        
        System.out.println("Exploration en largeur (BFS) :");
        System.out.println("Sommets découverts dans l'ordre :");
        // On choisit un sommet de départ, par exemple le sommet initial C (id = 2)
        Sommet depart = g.getSommet(2);
        if (depart != null) {
            ArrayList<Sommet> resBFS = g.explorerLargeur(depart); //
            afficher_ordre_decouverte(resBFS); //
        }

        System.out.println("Exploration en profondeur itératif avec pile (DFS) :");
        System.out.println("Sommets découverts dans l'ordre :");
        if (depart != null)
            ArrayList<Sommet> resDFS_it = g.explorerProfondeur_it(depart); //
            afficher_ordre_decouverte(resDFS_it); //
        }
        
        System.out.println("Sommets explorés dans l'ordre :");
        // (Si vous souhaitez afficher un autre résultat ou liste spécifique d'exploration)

        System.out.println("Exploration en profondeur récursif (DFS récursif) :");
        System.out.println("Sommets découverts dans l'ordre :");
        if (depart != null) {
            ArrayList<Sommet> resDFS_rec = g.explorerProfondeur_rec(depart); //
            afficher_ordre_decouverte(resDFS_rec); //
    }

    public static void afficher_ordre_decouverte(ArrayList<Sommet> l) {
        for (Sommet s : l) {
            System.out.println(s.getName()); //Affiche le nom de chaque sommet
        }
    }
}
