package allyson.com.br.infogloboapp.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import allyson.com.br.infogloboapp.InterfacesComuns.OnItemClickListener;
import allyson.com.br.infogloboapp.R;
import allyson.com.br.infogloboapp.RetrofitInterfaces.IConteudos;
import allyson.com.br.infogloboapp.model.Conteudo;
import allyson.com.br.infogloboapp.model.RetornoRetrofit;
import allyson.com.br.infogloboapp.view.activity.PrincipalActivity;
import allyson.com.br.infogloboapp.view.adapter.EditorialAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class CapaFragment extends Fragment implements OnItemClickListener {
    @BindView(R.id.iv_capa)
    AppCompatImageView iv_capa;
    @BindView(R.id.tv_titulo)
    AppCompatTextView tv_titulo;
    @BindView(R.id.tv_secao_nome)
    AppCompatTextView tv_secao_nome;
    @BindView(R.id.pb_capa)
    ProgressBar pb_capa;
    @BindView(R.id.rv_editorial)
    RecyclerView rv_editorial;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private Conteudo capa = null;
    private List<Conteudo> editorial = new ArrayList<>();
    private OnItemClickListener onItemClickListener = this;
    private PrincipalActivity principalActivity;

    public CapaFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_capa, container, false);
        ButterKnife.bind(this, view);
        principalActivity = (PrincipalActivity) getActivity();
        if(savedInstanceState != null){
            Gson gson = new Gson();
            capa = gson.fromJson(savedInstanceState.getString("capa"),Conteudo.class);
            Type tipoLista = new TypeToken<ArrayList<Conteudo>>(){}.getType();
            editorial = gson.fromJson(savedInstanceState.getString("editorial"),tipoLista);
            atualizarTela();
        }else {

            principalActivity.mudarTitulo("O GLOBO");
            principalActivity.navegacaoIcone("capa");
            pb_capa.setVisibility(View.VISIBLE);
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com/Infoglobo/desafio-apps/master/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
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
        }
        return view;
    }

    public void atualizarTela() {

        Picasso.with(getActivity())
                .load(capa.getImagens().get(0).getUrl())
                .placeholder(R.drawable.placeholder)
                .into(iv_capa);
        iv_capa.setVisibility(View.VISIBLE);
        tv_titulo.setText(capa.getTitulo());
        tv_secao_nome.setText(capa.getSecao().getNome());
        tv_secao_nome.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.preto));
        iv_capa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.OnClick(0,capa);
            }
        });
        pb_capa.setVisibility(View.GONE);
        rv_editorial.setLayoutManager(new LinearLayoutManager(getActivity()));
        EditorialAdapter editorialAdapter = new EditorialAdapter(getActivity(), editorial, onItemClickListener);
        rv_editorial.setAdapter(editorialAdapter);
        editorialAdapter.notifyDataSetChanged();

    }


    @Override
    public void OnClick(int pos, Object object) {
        Conteudo conteudo = (Conteudo) object;

        if(conteudo.getTipo().equalsIgnoreCase("linkExterno")){
            LinksExternosFragment linksExternosFragment = new LinksExternosFragment();
            Gson gson = new Gson();
            Bundle bundle = new Bundle();
            bundle.putString("reportagem", gson.toJson(conteudo));
            linksExternosFragment.setArguments(bundle);
            principalActivity.getFragmentManagerTransaction(linksExternosFragment, "REPORTAGEM");
        }else {
            ReportagemFragment reportagemFragment = new ReportagemFragment();
            Gson gson = new Gson();
            Bundle bundle = new Bundle();
            bundle.putString("reportagem", gson.toJson(conteudo));
            reportagemFragment.setArguments(bundle);
            principalActivity.getFragmentManagerTransaction(reportagemFragment, "REPORTAGEM");
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Gson gson = new Gson();
        outState.putString("capa", gson.toJson(capa));
        outState.putString("editorial", gson.toJson(editorial));
    }

}
