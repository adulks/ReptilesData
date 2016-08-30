package nrzkyyh.reptiles_data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class Details extends AppCompatActivity {

    public static final String IMAGE = "image";
    public static final String NAMA = "nama";
    public static final String SPEK = "spek";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initToolbar();

        ImageView iReptil = (ImageView) findViewById(R.id.iReptil);
        TextView tNama = (TextView) findViewById(R.id.tNama);
        TextView tSpek = (TextView) findViewById(R.id.tSpek);

        Uri imageUri = Uri.parse(getIntent().getStringExtra(IMAGE));

        try {
            iReptil.setImageBitmap(decodeUri(this,imageUri,300));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        tNama.setText(getIntent().getStringExtra(NAMA));
        tSpek.setText(getIntent().getStringExtra(SPEK));


    }


    public static Bitmap decodeUri(Context c, Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth
                , height_tmp = o.outHeight;
        int scale = 1;

        while(true) {
            if(width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
    }


    private void initToolbar(){
        getSupportActionBar().setTitle("Detail");
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
