package allyson.com.br.infogloboapp.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.bluejamesbond.text.DocumentView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.security.Principal;
import java.util.ArrayList;

import allyson.com.br.infogloboapp.R;
import allyson.com.br.infogloboapp.model.Conteudo;
import allyson.com.br.infogloboapp.view.activity.PrincipalActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportagemFragment extends Fragment {

    @BindView(R.id.tv_titulo)
    AppCompatTextView tv_titulo;
    @BindView(R.id.tv_subtitulo)
    AppCompatTextView tv_subtitulo;
    @BindView(R.id.tv_autores)
    AppCompatTextView tv_autores;
    @BindView(R.id.tv_publicado_em)
    AppCompatTextView tv_publicado_em;
    @BindView(R.id.tv_legenda_foto)
    AppCompatTextView tv_legenda_foto;
    @BindView(R.id.tv_reportagem)
    DocumentView tv_reportagem;
    @BindView(R.id.iv_reportagem)
    AppCompatImageView iv_reportagem;
    @BindView(R.id.ll_autores)
    LinearLayout ll_autores;
    @BindView(R.id.scrollView)
    ScrollView scrollView;
    private Conteudo conteudo;
    public ReportagemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_reportagem, container, false);
        ButterKnife.bind(this, view);


        atualizarTela();


        return view;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bt_topo:
                scrollView.fullScroll(View.FOCUS_UP);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private String formatarData(DateTime data) {
        String dia = Integer.parseInt(data.dayOfMonth().getAsString()) < 10 ? "0" + data.dayOfMonth().getAsString() : data.dayOfMonth().getAsString();
        String mes = Integer.parseInt(data.monthOfYear().getAsString()) < 10 ? "0" + data.monthOfYear().getAsString() : data.monthOfYear().getAsString();
        String hora = Integer.parseInt(String.valueOf(data.getHourOfDay())) < 10 ? "0" + String.valueOf(data.getHourOfDay()) : String.valueOf(data.getHourOfDay());
        String minuto = Integer.parseInt(String.valueOf(data.getMinuteOfHour())) < 10 ? "0" + String.valueOf(data.getMinuteOfHour()) : String.valueOf(data.getMinuteOfHour());
        return dia + "/" + mes + "/" + data.year().getAsString() + " " + hora + ":" + minuto;
    }

    public void atualizarTela(){
        Bundle bundle = getArguments();
        Gson gson = new Gson();
        conteudo = gson.fromJson(bundle.getString("reportagem"), Conteudo.class);
        tv_titulo.setText(conteudo.getTitulo());
        tv_subtitulo.setText(conteudo.getSubTitulo());
        PrincipalActivity principal = (PrincipalActivity) getActivity();
        principal.mudarTitulo(conteudo.getSecao().getNome());
        principal.navegacaoIcone("reportagem");
        setHasOptionsMenu(true);


        if (conteudo.getAutores().size() != 0) {
            for (int i = 0; i < conteudo.getAutores().size(); i++) {
                if (i == 0) {
                    tv_autores.setText(conteudo.getAutores().get(i));
                } else {
                    tv_autores.setText(tv_autores.getText() + ", " + conteudo.getAutores().get(i));
                }
            }
        } else {
            ll_autores.setVisibility(View.GONE);
        }

        DateTime atualizadoEm = new DateTime(conteudo.getAtualizadoEm());
        DateTime publicadoEm = new DateTime(conteudo.getPublicadoEm());

        if (publicadoEm.compareTo(atualizadoEm) == 0) {
            tv_publicado_em.setText(formatarData(publicadoEm));
        } else {
            tv_publicado_em.setText(formatarData(atualizadoEm));
        }

        try {
            Picasso.with(getActivity())
                    .load(conteudo.getImagens().get(0).getUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(iv_reportagem);
            if (conteudo.getImagens().get(0).getLegenda() == null ||
                    conteudo.getImagens().get(0).getLegenda().equalsIgnoreCase("")) {
                tv_legenda_foto.setVisibility(View.GONE);
            } else {
                tv_legenda_foto.setText(conteudo.getImagens().get(0).getLegenda() + ". Foto: " +
                        conteudo.getImagens().get(0).getFonte());
            }
        } catch (Exception ex) {
            Log.e("ERRO", "Imagem não disponível");
            iv_reportagem.setVisibility(View.GONE);
        }

        tv_reportagem.setText(conteudo.getTexto());
    }

}
