package allyson.com.br.infogloboapp;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class CapaFragment extends Fragment {

    private AppCompatImageView iv_capa;
    private AppCompatTextView tv_titulo;
    private AppCompatTextView tv_secao_nome;
    private Conteudo capa = null;
    private List<Conteudo> editorial = new ArrayList<>();
    public CapaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_capa, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Infoglobo/desafio-apps/master/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IConteudos iConteudos = retrofit.create(IConteudos.class);


        iConteudos.getConteudos().enqueue(new Callback<List<RetornoRetrofit>>() {
            @Override
            public void onResponse(Call<List<RetornoRetrofit>> call, Response<List<RetornoRetrofit>> response) {
                List<RetornoRetrofit> list = response.body();

                for (Conteudo conteudo : list.get(0).getConteudos()) {
                    if (capa == null) {
                        capa = conteudo;
                    } else {
                        editorial.add(conteudo);
                    }
                }
                atualizarTela();
            }

            @Override
            public void onFailure(Call<List<RetornoRetrofit>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        iv_capa = (AppCompatImageView) view.findViewById(R.id.iv_capa);
        tv_titulo = (AppCompatTextView) view.findViewById(R.id.tv_titulo);
        tv_secao_nome = (AppCompatTextView) view.findViewById(R.id.tv_secao_nome);
        return view;
    }

    public void atualizarTela(){

        Picasso.with(getActivity())
                .load(capa.getImagens().get(0).getUrl())
                .placeholder(R.drawable.placeholder)
                .into(iv_capa);

        tv_titulo.setText(capa.getTitulo());
        tv_secao_nome.setText(capa.getSecao().getNome());
        tv_secao_nome.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.preto));

    }


}