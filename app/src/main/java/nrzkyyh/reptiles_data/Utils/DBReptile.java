package nrzkyyh.reptiles_data.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBReptile extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "reptile";
    public static final String TABLE_NAME = "reptile_tabel";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_IMAGE = "image";
    public static final String COLUMN_SPECIFICATION = "specification";
    private HashMap hp;

    public DBReptile(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + TABLE_NAME + " (id integer primary key, name text,image text,specification text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String specification, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_SPECIFICATION, specification);
        contentValues.put(COLUMN_IMAGE, image);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_ID,
                        COLUMN_NAME, COLUMN_IMAGE,COLUMN_SPECIFICATION }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateData(Integer id, String name, String specification, String image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_SPECIFICATION, specification);
        contentValues.put(COLUMN_IMAGE, image);
        db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteData(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }

    public ArrayList<ReptilModel> getAllData() {
        ArrayList<ReptilModel> array_list = new ArrayList<>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " ORDER BY id DESC", null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            final ReptilModel um = new ReptilModel();
            um.setId(res.getString(res.getColumnIndex(COLUMN_ID)));
            um.setNama(res.getString(res.getColumnIndex(COLUMN_NAME)));
            um.setImage(res.getString(res.getColumnIndex(COLUMN_IMAGE)));
            um.setSpek(res.getString(res.getColumnIndex(COLUMN_SPECIFICATION)));
            array_list.add(um);
            res.moveToNext();
        }
        return array_list;
    }

    public Cursor getId(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[] { COLUMN_ID,
                        COLUMN_NAME, COLUMN_IMAGE,COLUMN_SPECIFICATION }, COLUMN_NAME + "=?",
                new String[] { id }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public String getImage(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where id=" + id + "", null);
        res.close();
        return res.getString(res.getColumnIndex(COLUMN_IMAGE));
    }




}