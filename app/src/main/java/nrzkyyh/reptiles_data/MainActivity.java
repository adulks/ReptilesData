package nrzkyyh.reptiles_data;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = (ImageView) findViewById(R.id.iReptil);
        //logo.setVisibility(View.GONE);
        animate();
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //logo.setVisibility(View.GONE);
                animateRotate();
            }
        });



        Button ke_cari = (Button) findViewById(R.id.btnCari);
        ke_cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent pindah = new Intent(MainActivity.this, Cari.class);
                startActivity(pindah);
                //menghubungkan antar activity dengan intent

            }
        });

        Button ke_pdr = (Button) findViewById(R.id.btnPDR);
        ke_pdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent pindah = new Intent(MainActivity.this, PDR.class);
                startActivity(pindah);
                //menghubungkan antar activity dengan intent

            }
        });

        Button ke_regist = (Button) findViewById(R.id.btnR);
        ke_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent pindah = new Intent(MainActivity.this, Regist.class);
                startActivity(pindah);
                //menghubungkan antar activity dengan intent

            }
        });

        Button ke_tambah = (Button) findViewById(R.id.btnTambah);
        ke_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent pindah = new Intent(MainActivity.this, Verifikasi.class);
                startActivity(pindah);
                //menghubungkan antar activity dengan intent

            }
        });

        Button ke_info = (Button) findViewById(R.id.btnInfo);
        ke_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent pindah = new Intent(MainActivity.this, Info.class);
                startActivity(pindah);
                //menghubungkan antar activity dengan intent

            }
        });

    }

    private void animate(){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                try {
                    logo.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.Landing).duration(2500).interpolate(new AccelerateDecelerateInterpolator()).playOn(logo);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, 10);
    }

    private void animateRotate(){
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                try {
                    logo.setVisibility(View.VISIBLE);
                    YoYo.with(Techniques.Bounce).duration(1000).interpolate(new AccelerateDecelerateInterpolator()).playOn(logo);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, 1);
    }
}
