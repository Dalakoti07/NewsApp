package com.example.newz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.ExampleViewHolder> {

    private Context context;
    private ArrayList<Source> sourceItems;
    private NewsApi newsApi2;
    int index=0;
    private ArrayList<Bitmap> bitmapArrayList;

    public SourceAdapter(Context context, ArrayList<Source> sourceItems, ArrayList<Bitmap> bitmapArrayList) {
        this.context = context;
        this.sourceItems = sourceItems;
        this.bitmapArrayList = bitmapArrayList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.sources_card_view,parent,false);
        Log.d("TAG", "onCreateViewHolder: "+"inside on createViewHolder");
        return new ExampleViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {


        Source currentSource=sourceItems.get(position);

        String description=currentSource.getDescription();
        String country=currentSource.getCountry();
        String language=currentSource.getLanguage();
        String name=currentSource.getName();
//        String webUrl=currentSource.getUrl();

        //Log.d("bitmap al", "onBindViewHolder: "+bitmapArrayList.toString());
        SourceImageParse sourceImageParse=currentSource.getSourceImageParse();
        Bitmap bitmap = null;
        if(sourceImageParse!=null) {
             bitmap= sourceImageParse.getSourceImage();
        }
        if(bitmap!=null) {
            holder.imageViewSource.setImageBitmap(bitmap);
        }
//        SourceImageParse imageObj =currentSource.getSourceImageParse();
//        if(imageObj!=null) {
//            Log.d("TAG", "onBindViewHolder: " + imageObj.toString());
//
//        }
//        Bitmap imageFile;
//        if(imageObj!=null) {
//
//            imageFile= imageObj.getSourceImage();
//            holder.imageViewSource.setImageBitmap(imageFile);
//
//        }
        holder.description.setText(description);
        holder.country.setText(country);
        holder.language.setText(language);
        holder.name.setText(name);

    }

    void addTheImageAtPosition(Bitmap bitmap,int index){
        sourceItems.get(index).setSourceImageParse(new SourceImageParse(bitmap));
        notifyItemChanged(index);
    }


    @Override
    public int getItemCount() {
        return sourceItems.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView description;
        public TextView country;
        public TextView language;


        public ImageView imageViewSource;
        public NavigationView navigationView;


        public ExampleViewHolder(View itemView){
            super(itemView);


            navigationView=itemView.findViewById(R.id.bottom_navigation_2);
            name=itemView.findViewById(R.id.name);
            imageViewSource=itemView.findViewById(R.id.image_view_source);
            description=itemView.findViewById(R.id.text_view_description);
            country=itemView.findViewById(R.id.text_view_country);
            language=itemView.findViewById(R.id.text_view_language);


        }

    }

//   private Bitmap getImageFile(String webUrl){
//       Gson gson = new GsonBuilder()
//               .setLenient()
//               .create();
//
//       Retrofit retrofit=new Retrofit.Builder()
//               .baseUrl("https://www.google.com/s2/")
//               .addConverterFactory(ScalarsConverterFactory.create())
//               .addConverterFactory(GsonConverterFactory.create(gson))
//               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//               .build();
//
//       newsApi2=retrofit.create(NewsApi.class);
//
//       Log.d("inside bitmap", "getImageFile: "+webUrl);
//        Call<ResponseBody> call_source=newsApi2.getImage(64,webUrl);
//
//        call_source.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if(!response.isSuccessful()){
//                    Log.d("TAG", "onResponse: "+response.code());
//                }
//
//                bitmap= BitmapFactory.decodeStream(response.body().byteStream());
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.d("on failure", "onFailure: "+t.getMessage());
//
//            }
//        });
//
//       return bitmap;
//
//   }
}
