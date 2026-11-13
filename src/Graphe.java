import java.io.File;
import java.io.IOException;
import java.util.*;

public class Graphe {
    /* attributs à compléter */
    private int orient;
    ArrayList<Sommet> l_sommets;

    /* chargement du graphe*/
    public void charger(String ficName)throws IOException
    {
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
        //lecture, création et ajout des sommets dans l'ArrayList
        for(int i=0;i<n;i++)
        {
            id=sc.nextInt();
            nom=sc.next();
            sc.nextLine();
            l_sommets.add(new Sommet(id,nom));
        }
        //lecture des liens
        int id1,id2;
        while(sc.hasNextLine())
        {
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
    public Sommet getSommet(int id)
    {
        for(Sommet s:l_sommets)
        {
            if(s.getId()==id) return s;
        }
        return null;
    }
    /*exploration en largeur à partir d'un sommet s
    retour : liste des sommets explorés*/
    public ArrayList<Sommet> explorerLargeur(Sommet s)
    {
        Queue<Sommet> q =new ArrayDeque<>();
        ArrayList<Sommet> sommetsVisites= new ArrayList<>();
        q.offer(s); // On ajoute le sommet de départ à la fin
        sommetsVisites.add(s); // On ajoute le sommet de départ à la liste des sommets explorés
        while(!q.isEmpty()) // Tant que la file q n'est pas vide, on continue
        {
            Sommet courant =q.poll();
            System.out.println(courant.getId());
            for(Sommet voisin : courant.getSuccesseurs())
            {
                if(!sommetsVisites.contains(voisin))
                {
                   sommetsVisites.add(voisin);
                   q.offer(voisin);
                }
            }
        }
        return sommetsVisites;
    }
    /*exploration en profondeur à partir d'un sommet s
    version itérative avec pile
   retour : liste des sommets explorés*/
    public ArrayList<Sommet> explorerProfondeur_it(Sommet s)
    {
        ArrayList<Sommet> l_decouverts=new ArrayList<Sommet>();
        Stack<Sommet> pile=new Stack<Sommet>();
        ArrayList<Boolean> marque=new ArrayList<Boolean>(l_sommets.size());
        for(int i=0;i<l_sommets.size();i++)
        {
            marque.add(i,false);
        }
        pile.push(s);
        marque.set(s.getId(),true);
        l_decouverts.add(s);
        while(!pile.isEmpty())
        {
            s=pile.pop();
            for(Sommet succ:s.getSuccesseurs())
            {
                if(!(marque.get(succ.getId())))
                {
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
    public ArrayList<Sommet> explorerProfondeur_rec(Sommet s)
    {
        ArrayList<Sommet> l_decouverts=new ArrayList<>();
        ArrayList<Boolean> marques =new ArrayList<>();
        //INITIALISATION
        for(Sommet sommet : l_sommets)
        {
            marques.add(false);
        }
        parcoursProfondeur_rec(s,l_decouverts,marques);
        return l_decouverts;
    }
    public void parcoursProfondeur_rec(Sommet s,ArrayList<Sommet> l_decouverts,ArrayList<Boolean> marque)
    {
        int indice = l_sommets.indexOf(s);
        if(indice == -1 || marque.get(indice)){return;}
        marque.set(indice,true);// on marque le sommet comme ayant été visité
        l_decouverts.add(s);// On l'ajoute à la liste des sommets découverts
        for(Sommet voisin : s.getSuccesseurs()) // On explore tous les successeurs du sommet découvert
        {
            int indiceVoisin = l_sommets.indexOf(voisin);
            if(indiceVoisin == -1 || marque.get(indiceVoisin))
            {
                parcoursProfondeur_rec(voisin,l_decouverts,marque);
            }
        }
    }
    /* Affichage des listes de successeurs*/
    public void afficherListeSucc()
    {
        for(Sommet s:l_sommets)
        {
            System.out.print(s.getId()+":");
            for(Sommet succ:s.getSuccesseurs())
            {
                System.out.print(succ.getId()+" ");
            }
            System.out.println();
        }
    }

    public ArrayList<Sommet> BFS_Parents(Sommet s) {
        ArrayList<Sommet> l_parents = new ArrayList<>();
        ArrayList<Boolean> marque = new ArrayList<>();
        Queue<Sommet> file = new LinkedList<>();

        for (int i = 0; i < l_sommets.size(); i++) {
            l_parents.add(null);
            marque.add(false);
        }

        file.add(s);
        marque.set(s.getId(), true);

        while (!file.isEmpty()) {
            Sommet courant = file.poll();

            for (Sommet succ : courant.getSuccesseurs()) {
                if (!marque.get(succ.getId())) {
                    marque.set(succ.getId(), true);
                    l_parents.set(succ.getId(), courant);
                    file.add(succ);
                }
            }
        }

        return l_parents;
    }

    public ArrayList<Sommet> DFS_parents(Sommet s) {
        ArrayList<Sommet> l_parents = new ArrayList<>();
        ArrayList<Boolean> marque = new ArrayList<>();

        for (int i = 0; i < l_sommets.size(); i++) {
            l_parents.add(null);
            marque.add(false);
        }

        marque.set(s.getId(), true);
        parcoursDFSParents(s, l_parents, marque);

        return l_parents;
    }
    private void parcoursDFSParents(
            Sommet s,
            ArrayList<Sommet> l_parents,
            ArrayList<Boolean> marque) {

        for (Sommet succ : s.getSuccesseurs()) {
            if (!marque.get(succ.getId())) {
                marque.set(succ.getId(), true);
                l_parents.set(succ.getId(), s);

                parcoursDFSParents(succ, l_parents, marque);
            }
        }
    }

    public ArrayList<Sommet> retrouverChemin(
            Sommet s1,
            Sommet s2,
            ArrayList<Sommet> l_parents) {

        ArrayList<Sommet> chemin = new ArrayList<>();

        Sommet courant = s2;

        while (courant != null) {
            chemin.add(0, courant);

            if (courant.equals(s1)) {
                return chemin;
            }

            courant = l_parents.get(courant.getId());
        }

        return new ArrayList<>();
    }


    public ArrayList<ArrayList<Sommet>> chercherCC() {
        ArrayList<ArrayList<Sommet>> composantes = new ArrayList<>();
        ArrayList<Sommet> visites = new ArrayList<>();

        for (Sommet depart : l_sommets) {

            if (!visites.contains(depart)) {
                ArrayList<Sommet> composante = new ArrayList<>();
                Queue<Sommet> file = new LinkedList<>();

                file.add(depart);
                visites.add(depart);

                while (!file.isEmpty()) {
                    Sommet courant = file.poll();
                    composante.add(courant);

                    // Explorer les successeurs
                    for (Sommet succ : courant.getSuccesseurs()) {
                        if (!visites.contains(succ)) {
                            visites.add(succ);
                            file.add(succ);
                        }
                    }

                    // Explorer aussi les arcs entrants
                    for (Sommet sommet : l_sommets) {
                        if (sommet.getSuccesseurs().contains(courant)
                                && !visites.contains(sommet)) {
                            visites.add(sommet);
                            file.add(sommet);
                        }
                    }
                }

                composantes.add(composante);
            }
        }

        return composantes;
    }

    public boolean testerConnexite() {
        return chercherCC().size() == 1;
    }

}
