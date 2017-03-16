package allyson.com.br.infogloboapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("O GLOBINHO");
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getFragmentManagerTransaction(new CapaFragment(), "CAPA");
        }

    }

    public void getFragmentManagerTransaction(Fragment fragment, String fragmentDestiny) {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_conteudo, fragment, fragmentDestiny).addToBackStack("pilha").commitAllowingStateLoss();
    }

}
