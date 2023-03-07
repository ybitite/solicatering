package website.livingRoom.soliCatering.utile;


public class Helper {

    //todo replace this methode to much resource
    //RETURN ID RESOURCE FROM CONTEXT
    public static int idResource(String nom) {
        return AppUtile.getContext().getResources().getIdentifier(nom, "drawable", AppUtile.getContext().getPackageName());
    }
}
