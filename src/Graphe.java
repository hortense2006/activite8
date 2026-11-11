import java.io.File;
import java.io.IOException;
import java.util.*;

public class Graphe {
    /* attributs à compléter */


    /* chargement du graphe*/
    public void charger(String ficName)throws IOException {
        //ouverture du fichier en lecture
        Scanner sc=new Scanner(new File(ficName));
        //lecture de l'orientation
        orient=sc.nextInt();
        sc.nextLine();
        //lecture de l'ordre
        int n=sc.nextInt();
        //création de l'ArrayList de sommets
        l_sommets=new ArrayList<Sommet>();
        sc.nextLine();
        int id;
        String nom;
        //lecture, création et ajout des sommets dans l'ARrayList
        for(int i=0;i<n;i++){
            id=sc.nextInt();
            nom=sc.next();
            sc.nextLine();
            l_sommets.add(new Sommet(id,nom));
        }
        //lecture des liens
        int id1,id2;
        while(sc.hasNextLine()){
            //lecture des extrémités
            id1=sc.nextInt();
            id2=sc.nextInt();
            //ajout de id2 aux successeurs de id1
            l_sommets.get(id1).ajouterSuccesseur(l_sommets.get(id2));
            //si le graphe n'est pas orienté : ajout de id1 aux successeurs de id2
            if(orient==0) l_sommets.get(id2).ajouterSuccesseur(l_sommets.get(id1));
            sc.nextLine();
        }
        sc.close();
    }
    // Chercher et retourner un sommet d'après son id
    public Sommet getSommet(int id){
        for(Sommet s:l_sommets){
            if(s.getId()==id) return s;
        }
        return null;
    }
    /*exploration en largeur à partir d'un sommet s
    retour : liste des sommets explorés*/
    public ArrayList<Sommet> explorerLargeur(Sommet s){
        /* METHODE A COPLETER */





















    }
    /*exploration en profondeur à partir d'un sommet s
    version itérative avec pile
   retour : liste des sommets explorés*/
    public ArrayList<Sommet> explorerProfondeur_it(Sommet s){
        ArrayList<Sommet> l_decouverts=new ArrayList<Sommet>();
        Stack<Sommet> pile=new Stack<Sommet>();
        ArrayList<Boolean> marque=new ArrayList<Boolean>(l_sommets.size());
        for(int i=0;i<l_sommets.size();i++){
            marque.add(i,false);
        }
        pile.push(s);
        marque.set(s.getId(),true);
        l_decouverts.add(s);
        while(!pile.isEmpty()){
            s=pile.pop();
            for(Sommet succ:s.getSuccesseurs()){
                if(!(marque.get(succ.getId()))){
                    marque.set(succ.getId(),true);
                    pile.push(succ);
                    l_decouverts.add(succ);
                }
            }
        }
        return l_decouverts;
    }
    /*exploration en profondeur à partir d'un sommet s
    version récursive
   retour : liste des sommets explorés*/
    public ArrayList<Sommet> explorerProfondeur_rec(Sommet s){

    }
    public void parcoursProfondeur_rec(Sommet s,ArrayList<Sommet> l_decouverts,ArrayList<Boolean> marque){

        
    }
    /* Affichage des listes de successeurs*/
    public void afficherListeSucc(){
        for(Sommet s:l_sommets){
            System.out.print(s.getId()+":");
            for(Sommet succ:s.getSuccesseurs()){
                System.out.print(succ.getId()+" ");
            }
            System.out.println();
        }
    }
}
