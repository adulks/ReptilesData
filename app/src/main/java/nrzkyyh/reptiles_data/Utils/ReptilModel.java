package nrzkyyh.reptiles_data.Utils;

import android.net.Uri;

public class ReptilModel {
    private String nama, spek, image, id ;
    private Uri imageUri;

    public void setNama(String str){
        nama = str;
    }
    public void setSpek(String str){
        spek = str;
    }
    public void setImage(String str){
        image = str;
    }
    public void setId(String str){
        id = str;
    }
    public void setImageUri(Uri uri){
        imageUri = uri;
    }

    public String getNama(){
        return nama;
    }
    public String getSpek(){
        return spek;
    }
    public String getImage(){
        return image;
    }
    public String getId(){
        return id;
    }
    public Uri getImageUri(){
        return imageUri;
    }


}
