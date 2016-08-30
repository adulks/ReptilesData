package nrzkyyh.reptiles_data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import nrzkyyh.reptiles_data.Utils.DBUser;
import nrzkyyh.reptiles_data.Utils.UserModel;


public class Verifikasi extends AppCompatActivity
{
    EditText username, password;
    String Username, Password;
    Context ctx=this;
    Button bVerify;
    DBUser  dbUser;
    ArrayList<UserModel> arrayUser = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi);
        dbUser = new DBUser(this);
        arrayUser = dbUser.getAllData();

        initToolbar();
        username = (EditText) findViewById(R.id.verify_username);
        password = (EditText) findViewById(R.id.verify_password);
        bVerify = (Button) findViewById(R.id.btn_verify);
        bVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String pwd = password.getText().toString();
                if(uname.length()<=0){
                    Toast.makeText(ctx, "Username tidak boleh kosong",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd.length()<=0){
                    Toast.makeText(ctx, "Password tidak boleh kosong",Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isUser = false;

                for(int i = 0;i<arrayUser.size();i++){
                    if(uname.equals(arrayUser.get(i).getNama())){
                        if(pwd.equals(arrayUser.get(i).getPassword())){
                            isUser = true;
                            break;
                        }else{
                            isUser = false;
                        }
                    }else{
                        isUser = false;
                    }
                }

                if(isUser){
                    openTambah();
                }else{
                    Toast.makeText(ctx, "Username atau password salah",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Regist(View v)
    {
        startActivity(new Intent(this,Regist.class));
    }

   /* public void verifikasi(View v)
    {
        Username = username.getText().toString();
        Password = password.getText().toString();

        if(username.getText().toString().equals("") &&
                password.getText().toString().equals(""))
        {
            Toast.makeText(Verifikasi.this,
                    "Username dan password Kosong..tolong di isi",
                    Toast.LENGTH_LONG).show();
        }

        else if(username.getText().toString().equals(""))
        {
            Toast.makeText(Verifikasi.this, "username Kosong..tolong di isi",
                    Toast.LENGTH_LONG).show();
        }

        else
        {
            if (password.getText().toString().equals(""))
            {
                Toast.makeText(Verifikasi.this, "Password Kosong..tolong di isi",
                        Toast.LENGTH_LONG).show();
            }

            else
            {
                BackGround b = new BackGround();
                b.execute(Username, Password);
            }
        }
    }*/



    class BackGround extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... params)
        {
            String username = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try
            {
                URL url = new URL("http://reptilesdata.gadgetcanggih.com/Verify/get_verify.php");
                String urlParams = "username="+username+"&password="+password;

                HttpURLConnection httpURLConnection =
                        (HttpURLConnection) url.openConnection();

                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();

                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1)
                {
                    data+= (char)tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            }

            catch (MalformedURLException e)
            {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }

            catch (IOException e)
            {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s)
        {
            String err=null;

            try
            {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");

                Username = user_data.getString("username");
                Password = user_data.getString("password");

            }

            catch (JSONException e)
            {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }

            Intent i = new Intent(ctx, Tambah.class);

            startActivity(i);
            finish();
        }
    }

    private void initToolbar(){
        getSupportActionBar().setTitle("Verifikasi User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_kosong,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openTambah(){
        Intent i = new Intent(ctx, Tambah.class);

        startActivity(i);
        finish();
    }
}