import java.util.ArrayList;

public class Sommet {
    /* attributs à compléter */
    int id;
    String nom;
    private ArrayList<Sommet> successeurs = new ArrayList<>();
    /* constructeur à compléter */
    public Sommet(int id,String name)
    {
        this.id=id;
        this.nom=name;
    }
    /* méthode d'ajout d'u successeur à compléter */
    public void ajouterSuccesseur (Sommet s)
    {
        successeurs.add(s);
    }


    /*accesseurs à compléter */
    public ArrayList<Sommet> getSuccesseurs(){
        return successeurs;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.nom;
    }
}
