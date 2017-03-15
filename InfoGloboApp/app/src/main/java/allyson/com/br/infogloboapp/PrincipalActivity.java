package allyson.com.br.infogloboapp;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.Display;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import allyson.com.br.infogloboapp.RetrofitInterfaces.IConteudos;
import allyson.com.br.infogloboapp.model.Conteudo;
import allyson.com.br.infogloboapp.model.RetornoRetrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        AppCompatImageView imagem = (AppCompatImageView) findViewById(R.id.imagem);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        Picasso.with(this)
                .load("https://ogimg.infoglobo.com.br/in/20620804-669-05e/FT1086A/cabral-preso.jpg")
                .into(imagem);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Infoglobo/desafio-apps/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IConteudos iConteudos = retrofit.create(IConteudos.class);


        iConteudos.getConteudos().enqueue(new Callback<List<RetornoRetrofit>>() {
            @Override
            public void onResponse(Call<List<RetornoRetrofit>> call, Response<List<RetornoRetrofit>> response) {
                List <RetornoRetrofit> list = response.body();
                Conteudo capa =  null;
                List<Conteudo> editorial = new ArrayList<>();
                for (Conteudo conteudo:  list.get(0).getConteudos()) {
                    if(capa == null){
                        capa = conteudo;
                    }else{
                        editorial.add(conteudo);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RetornoRetrofit>> call, Throwable t) {
                Toast.makeText(PrincipalActivity.this,t.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });


    }
}
