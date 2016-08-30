package nrzkyyh.reptiles_data.Utils;

import android.net.Uri;

public class UserModel {
    private String nama, password, id ;

    public void setNama(String str){
        nama = str;
    }
    public void setPassword(String str){
        password = str;
    }
   
    public void setId(String str){
        id = str;
    }
   

    public String getNama(){
        return nama;
    }
    public String getPassword(){
        return password;
    }
    public String getId(){
        return id;
    }


}
