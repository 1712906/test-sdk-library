package com.production.sdkskin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import com.production.sdkskin.Adapter.DetailAdapter;
import com.production.sdkskin.Item.Item;
import com.production.sdkskin.Model.DataSpecial;
import com.production.sdkskin.Model.DrawArr;
import com.production.sdkskin.Model.facedata;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {
    SweetAlertDialog pDialog;
    ImageView imgResult;
    Bitmap bitmapResult;
    HashMap<String, Integer> sourceColor;
    facedata data;
    Switch swt_ance,swt_spout,swt_pimple,swt_backHeads,swt_mole;
    HashMap<String,Boolean> isRender;
    List<Item> dataGernenral;
    DetailAdapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setTitle("AI Results");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.purple)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        prepareView();
        prepareData();
        Intent intent = getIntent();
        String id = (String) intent.getSerializableExtra("idImage");
        id =id.replace("\"","");
        pDialog= new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Loading");
        pDialog.setCancelable(false);
        imgResult=findViewById(R.id.img_result);



        FetchDetailImage(id);


    }
    //
    private void prepareView() {
        recyclerView=findViewById(R.id.list_General);
        swt_ance=findViewById(R.id.SwitchAcne);
        swt_pimple=findViewById(R.id.swithPimple);
        swt_spout=findViewById(R.id.swithSpot);
        swt_backHeads=findViewById(R.id.SwitchBackHeads);
        swt_mole=findViewById(R.id.SwitchMole);
        // Add function onClick;
        swt_backHeads.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isRender.replace("SkinBlackHeads",isChecked);
                reRederImage();
            }
        });
        //
        swt_spout.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isRender.replace("SkinSpot",isChecked);
                reRederImage();
            }
        });
        //
        swt_pimple.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isRender.replace("SkinPimple",isChecked);
                reRederImage();
            }
        });
        //
        swt_ance.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isRender.replace("SkinAcne",isChecked);
                reRederImage();
            }
        });
        swt_mole.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isRender.replace("SkinMole",isChecked);
                reRederImage();
            }
        });
    }
    //
    private void prepareData()
    {
        //
        dataGernenral = new ArrayList<>();

        //Source Colors
        sourceColor =new HashMap<String, Integer>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            sourceColor.put("SkinAcne", getResources().getColor(R.color.switchAcne));
            sourceColor.put("SkinSpot", getResources().getColor(R.color.switchSpot));
            sourceColor.put("SkinPimple", getResources().getColor(R.color.switchPimple));
            sourceColor.put("SkinBlackHeads", getResources().getColor(R.color.switchBlacKhead));
            sourceColor.put("Face", getResources().getColor(R.color.switchFace));
            sourceColor.put("SkinMole",getResources().getColor(R.color.switchMole));
        }
        else
        {     sourceColor.put("SkinAcne", getResources().getColor(R.color.switchAcne));
            sourceColor.put("SkinSpot", getResources().getColor(R.color.switchSpot));
            sourceColor.put("SkinPimple", getResources().getColor(R.color.switchPimple));
            sourceColor.put("SkinBlackHeads", getResources().getColor(R.color.switchBlacKhead));
            sourceColor.put("Face", getResources().getColor(R.color.switchFace));
            sourceColor.put("SkinMole",getResources().getColor(R.color.switchMole));

        }
        // Render Form
        isRender =new HashMap<>();
        isRender.put("SkinAcne", true);
        isRender.put("SkinSpot",  true);
        isRender.put("SkinPimple",  true);
        isRender.put("SkinBlackHeads",  true);
        isRender.put("Face", true);
        isRender.put("SkinMole",true);

    }


    private void FetchDetailImage(String id) {
        pDialog.show();
        RetrofitInstance service = ApiUtils.getInstance(MainActivity.domain);
        service.getDetail(id).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                Log.d("AAAAAA",response.toString());
                JsonObject object= response.body().getAsJsonObject("user");
                object = object.getAsJsonObject("facedata");

                Log.d("CCCCCCCCC",object.toString());
                try {
                    data = JSON.parseObject(object.toString(), facedata.class);

                }
                catch (Exception ex)
                {

                   Log.d("DDDDDDD",ex.toString());
                }
                    prepareListData();
                    String url =data.getImageinfo().url;
                    url=url.replace("http:","https:");
                    AsyncCaller asyncCaller= new AsyncCaller();
                    asyncCaller.execute(url);



            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                pDialog.hide();
            }
        });
    }

    private void prepareListData() {
        com.production.sdkskin.Model.generalResult generalResult= data.getGeneralResult();
        List<DataSpecial> dataPoints= generalResult.getData();
        for (DataSpecial item : dataPoints)
        {
            for(DataSpecial.Datum i:item.data)
            {

                if(i.key.contains("SkinAge"))
                {
                    dataGernenral.add(new Item(R.drawable.age_icon,"SkinAge",i.valueEN));
                }
                else if(i.key.contains("SkinColorLevel"))
                {
                    dataGernenral.add(new Item(R.drawable.brightness_icon,"SkinColorLevel",i.valueEN));
                }
                else if (i.key.contains("SkinType"))
                {
                    dataGernenral.add(new Item(R.drawable.skin_icon,"SkinType",i.valueEN));
                }
            }
        }
        dataPoints=data.getSpecialResult().getData();

        for (DataSpecial item : dataPoints)
        {
            for(DataSpecial.Datum i:item.data)
            {

                if(i.valueEN.contains("Skin PandaEye Left:"))
                {
                    dataGernenral.add(new Item(R.drawable.darkeye_left_icon,"SkinPandaEye_Left",i.valueEN));
                }
                else if(i.valueEN.contains("Skin PandaEye Right:"))
                {
                    dataGernenral.add(new Item(R.drawable.darkeye_right_icon,"SkinPandaEye_Right",i.valueEN));
                }
            }
        }

        adapter =new DetailAdapter(getApplicationContext(),dataGernenral);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private class AsyncCaller extends AsyncTask<String, Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... strings) {
            String src =strings[0];
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                Log.d("CCCCC",e.toString());
                return null;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            bitmapResult=bitmap;
            Bitmap tempBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.RGB_565);

            Canvas canvas = new Canvas(tempBitmap);
            canvas.drawBitmap(bitmap, 0, 0, null);
            Paint paint=new Paint();
            paint.setStyle(Paint.Style.STROKE);
            List<DataSpecial> dataPoints= data.getSpecialResult().getData();
            for (DataSpecial item : dataPoints)
            {
                for(DataSpecial.Datum i:item.data)
                {
                    if(i.drawArr.size()>0)
                    {
                        paint.setColor(sourceColor.get(i.key));
                        for( DrawArr drawArr : i.drawArr)
                        {
                            canvas.drawRoundRect(new RectF((float) drawArr.left,(float)drawArr.top,drawArr.left+(float)drawArr.width,drawArr.top+(float)drawArr.height),2,2,paint);
                        }
                    }
                }
            }
            dataPoints= data.getGeneralResult().getData();
            for (DataSpecial item : dataPoints)
            {
                for(DataSpecial.Datum i:item.data)
                {
                    if(i.drawArr.size()>0)
                    {
                        paint.setColor(sourceColor.get(i.key));
                        for( DrawArr drawArr : i.drawArr)
                        {
                            canvas.drawRoundRect(new RectF((float) drawArr.left,(float)drawArr.top,drawArr.left+(float)drawArr.width,drawArr.top+(float)drawArr.height),2,2,paint);
                        }
                    }
                }
            }
            imgResult.setImageDrawable(new BitmapDrawable(getResources(),Bitmap.createScaledBitmap(tempBitmap,600,800,false)));
            pDialog.hide();
        }
    }

    public void reRederImage()
    {

        Bitmap tempBitmap = Bitmap.createBitmap(bitmapResult.getWidth(), bitmapResult.getHeight(), Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(tempBitmap);
        canvas.drawBitmap(bitmapResult, 0, 0, null);
        Paint paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        List<DataSpecial> dataPoints= data.getSpecialResult().getData();
        for (DataSpecial item : dataPoints)
        {
            for(DataSpecial.Datum i:item.data)
            {
                if(i.key.contains("SkinMole"))
                {
                    Log.d("Error",i.drawArr.toString());
                }
                if(i.drawArr.size()>0 && isRender.get(i.key))
                {
                    paint.setColor(sourceColor.get(i.key));
                    for( DrawArr drawArr : i.drawArr)
                    {
                        canvas.drawRect(new RectF((float) drawArr.left,(float)drawArr.top,drawArr.left+(float)drawArr.width,drawArr.top+(float)drawArr.height),paint);
                    }
                }
            }
        }
         dataPoints= data.getGeneralResult().getData();
        for (DataSpecial item : dataPoints)
        {
            for(DataSpecial.Datum i:item.data)
            {
                if(i.key.contains("SkinMole"))
                {
                    Log.d("Error",i.drawArr.toString());
                }
                if(i.drawArr.size()>0 && isRender.get(i.key))
                {
                    paint.setColor(sourceColor.get(i.key));
                    for( DrawArr drawArr : i.drawArr)
                    {
                        canvas.drawRect(new RectF((float) drawArr.left,(float)drawArr.top,drawArr.left+(float)drawArr.width,drawArr.top+(float)drawArr.height),paint);
                    }
                }
            }
        }
        imgResult.setImageDrawable(new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(tempBitmap,600,800,false)));
    }

}
