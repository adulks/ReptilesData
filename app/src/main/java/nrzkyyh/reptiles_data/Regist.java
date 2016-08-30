package nrzkyyh.reptiles_data;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import nrzkyyh.reptiles_data.Utils.DBUser;

public class Regist extends AppCompatActivity {

    EditText username, password, cPassword;
    String Username, Password;
    Context ctx=this;
    Button bRegister;



    DBUser dbUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initToolbar();
        dbUser = new DBUser(this);

        username = (EditText) findViewById(R.id.verify_username);
        password = (EditText) findViewById(R.id.verify_password);
        cPassword = (EditText) findViewById(R.id.confirm_password);
        bRegister = (Button) findViewById(R.id.btn_register);
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = username.getText().toString();
                String pwd = password.getText().toString();
                String c_pwd = cPassword.getText().toString();
                if(uname.length()<=0){
                    Toast.makeText(ctx, "Username tidak boleh kosong",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd.length()<=0||c_pwd.length()<=0){
                    Toast.makeText(ctx, "Password tidak boleh kosong",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!pwd.equals(c_pwd)){
                    Toast.makeText(ctx, "Password tidak sama",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(dbUser.insertData(uname,pwd)){
                    Toast.makeText(ctx, "Register Sukses", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Toast.makeText(ctx, "Terjadi Kesalahan, Silakan coba lagi", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    /*public void register_register(View v)
    {
        Username = username.getText().toString();
        Password = password.getText().toString();

        if(username.getText().toString().equals("") &&
                password.getText().toString().equals(""))
        {
            Toast.makeText(Regist.this, "Terjadi Kesalahan",
                    Toast.LENGTH_LONG).show();
        }

        else if (username.getText().toString().equals(""))
        {
            Toast.makeText(Regist.this, "Kesalahan Pada Username",
                    Toast.LENGTH_LONG).show();
        }

        else if (password.getText().toString().equals(""))
        {
            Toast.makeText(Regist.this, "Kesalahan Pada Username",
                    Toast.LENGTH_LONG).show();
        }

        else if(isNetworkAvailable()==false)
        {
            Toast.makeText(Regist.this, "Tidak Ada Koneksi",
                    Toast.LENGTH_LONG).show();
        }

        else
        {
            BackGround b = new BackGround();
            b.execute(Username, Password);

            Toast.makeText(Regist.this, "Registrasi Berhasil",
                    Toast.LENGTH_LONG).show();

            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

    }*/

    // Check all connectivities whether available or not
    public boolean isNetworkAvailable()
    {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected())
        {
            return true;
        }
        return false;
    }

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
                URL url = new URL("http://reptilesdata.gadgetcanggih.com/regist.php");
                String urlParams = "username="+username+"&password="+password;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
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


    }

    private void initToolbar(){
        getSupportActionBar().setTitle("Registrasi");
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


}
