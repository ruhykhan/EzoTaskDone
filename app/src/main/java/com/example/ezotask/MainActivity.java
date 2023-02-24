package com.example.ezotask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView textView;
    ImageView imageV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        btn = findViewById(R.id.button2);
        imageV = findViewById(R.id.imageView2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResult();
            }
        });

    }

    private void getResult(){
        Call<List<Result>> call = RetrofitClient.getInstance().getMyApi().getResult();
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {
                List<Result> nameList = response.body();


                String[] author_name = new String[nameList.size()];
                String[] image_url = new String[nameList.size()];


                for (int i = 0; i < nameList.size(); i++) {
                    author_name[i] = nameList.get(i).getAuthor();
                    image_url[i]= nameList.get(i).getDownloadUrl();

                }
                textView.setText(author_name[0]);

                Picasso.get().load(image_url[0])
                        .resize(200,200)
                .centerCrop().into(imageV);

            }

            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
    }
    }
