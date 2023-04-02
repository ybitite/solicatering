package website.livingRoom.soliCatering.utile;



import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public abstract class AppPicasso {
    //FIELD
    /*DEFINED A SINGLETON, PREVENT MULTIPLE INSTANCES AT THE SAME TIME.*/
    private static volatile Picasso INSTANCE;
    

    public static Picasso getInstance(){
        if(INSTANCE == null){
                try {
                    INSTANCE =new  Picasso.Builder(Helper.getContext()).downloader(new OkHttp3Downloader(AppOkHttpClient.getTrustAllCertsClient())).build();
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                } catch (KeyManagementException e) {
                    throw new RuntimeException(e);
                }
        }
        return INSTANCE;
    }

}
