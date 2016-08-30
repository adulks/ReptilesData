package nrzkyyh.reptiles_data;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.FileNotFoundException;
import java.net.URI;
import java.util.ArrayList;

import nrzkyyh.reptiles_data.Utils.ReptilModel;


public class AdapterReptil extends BaseAdapter {


    Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;


    public AdapterReptil(Activity a, ArrayList d) {
        // this.context = context;
        activity = a;
        data = d;

        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {

        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;

        if (convertView == null) {
            view = inflater.inflate(R.layout.reptil_list, null);
            holder = new ViewHolder();
            holder.iReptil = (ImageView) view.findViewById(R.id.iReptil);
            holder.tNama = (TextView) view.findViewById(R.id.tNama);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (data.size() > 0) {
            view.setVisibility(View.VISIBLE);
            ReptilModel rm = (ReptilModel) data.get(position);
            Uri imageUri = Uri.parse(rm.getImage());
            Log.w("imageUri", imageUri.toString());
            try {
                holder.iReptil.setImageBitmap(decodeUri(activity,imageUri,100));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            holder.tNama.setText(rm.getNama());

            final String nama = rm.getNama();
            final String spek = rm.getSpek();
            final String image = rm.getImage();

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openDetails(nama, spek, image);
                }
            });

        } else {
            view.setVisibility(View.GONE);
        }

        return view;
    }

    private void openDetails(String nama, String spek, String image) {
        Intent i = new Intent(activity,Details.class);
        i.putExtra(Details.NAMA, nama);
        i.putExtra(Details.SPEK, spek);
        i.putExtra(Details.IMAGE, image);
        activity.startActivity(i);
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



    public static class ViewHolder {
        ImageView iReptil;
        TextView tNama;
    }





}

