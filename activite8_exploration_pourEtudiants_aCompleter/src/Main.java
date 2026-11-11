import java.io.IOException;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        Graphe g=new Graphe();
        g.charger(     );
        g.afficherListeSucc();
        System.out.println("Exploration en largeur (BFS) :");

        System.out.println("Sommets découverts dans l'ordre :");

        System.out.println("Exploration en profondeur itératif avec pile (DFS) :");

        System.out.println("Sommets découverts dans l'ordre :");
        ;
        System.out.println("Sommets explorés dans l'ordre :");

        System.out.println("Exploration en profondeur récursif (DFS récursif) :");

        System.out.println("Sommets découverts dans l'ordre :");


    }
    public static void afficher_ordre_decouverte(ArrayList<Sommet> l){
        for(Sommet s: l){
            System.out.println(s.getName());
        }
    }
}