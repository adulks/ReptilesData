package nrzkyyh.reptiles_data;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import java.util.ArrayList;

import nrzkyyh.reptiles_data.Utils.DBReptile;
import nrzkyyh.reptiles_data.Utils.ReptilModel;

public class Cari extends AppCompatActivity {

    AdapterReptil adapterReptil;
    ArrayList<ReptilModel> arrayList = new ArrayList<>();
    ArrayList<ReptilModel> arrayAll = new ArrayList<>();
    GridView listReptil;

    DBReptile myDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cari);

        myDb = new DBReptile(this);

        arrayAll = myDb.getAllData();
        arrayList = myDb.getAllData();
        listReptil = (GridView) findViewById(R.id.listImage);
        adapterReptil = new AdapterReptil(this,arrayList);
        listReptil.setAdapter(adapterReptil);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchableInfo searchableInfo =
                searchManager.getSearchableInfo(getComponentName());


        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setSearchableInfo(searchableInfo);

        initializeSearch(searchView);



    }

    public void initializeSearch(final SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                arrayList.clear();
                for(int i = 0;i<arrayAll.size();i++){
                    String nama = arrayAll.get(i).getNama().toLowerCase();
                    if(nama.contains(query.toLowerCase()))
                        arrayList.add(arrayAll.get(i));
                }


                adapterReptil.notifyDataSetChanged();


                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return onQueryTextSubmit(newText);
            }
        });

        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    searchView.setQueryHint("Cari...");
                }
            }
        });


        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                arrayList = arrayAll;
                adapterReptil.notifyDataSetChanged();
                return false;

            }
        });

    }

    private void initToolbar(){
        getSupportActionBar().setTitle("Reptiles Data");
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
