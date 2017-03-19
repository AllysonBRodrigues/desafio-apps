package allyson.com.br.infogloboapp.apresentacao.Reportagem;


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
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;

import allyson.com.br.infogloboapp.R;
import allyson.com.br.infogloboapp.apresentacao.Principal.PrincipalActivity;
import allyson.com.br.infogloboapp.model.Conteudo;
import allyson.com.br.infogloboapp.util.Datas;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Implementação do fragment da fragmente onde é exibida a reportagem
 */
public class ReportagemFragment extends Fragment implements ReportagemContrato.View {

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

    public ReportagemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_reportagem, container, false);
        ButterKnife.bind(this, view);

        ReportagemContrato.Apresentacao apresentacao = new ReportagemApresentacao();
        apresentacao.bind(this);
        Bundle bundle = getArguments();
        apresentacao.carregarConteudo(bundle.getString("reportagem"));

        return view;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    /**
     * Ao clicar no menu bt_topo a pagina é levada ao topo
     *
     * @param item item do menu clicado
     * @return retorna true para que o menu seja exibido
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bt_topo:
                scrollView.fullScroll(View.FOCUS_UP);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Recebe uma lista de conteudos da camada de apresentação e
     * exibe as informaçãos para o usuario.
     *
     * @param conteudo conteudo recebido da camada de apresentação
     */

    @Override
    public void atualizarView(Conteudo conteudo) {
        tv_titulo.setText(conteudo.getTitulo());
        tv_subtitulo.setText(conteudo.getSubTitulo());
        PrincipalActivity principal = (PrincipalActivity) getActivity();
        principal.mudarTitulo(conteudo.getSecao().getNome());
        principal.configurarNavegacao("reportagem");
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
            tv_publicado_em.setText(Datas.formatarData(publicadoEm));
        } else {
            tv_publicado_em.setText(Datas.formatarData(atualizadoEm));
        }

        try {
            Picasso.with(getActivity())
                    .load(conteudo.getImagens().get(0).getUrl())
                    .resize(0,250)
                    .placeholder(R.drawable.placeholder)
                    .into(iv_reportagem);
            if (conteudo.getImagens().get(0).getLegenda() == null ||
                    conteudo.getImagens().get(0).getLegenda().equalsIgnoreCase("")) {
                tv_legenda_foto.setVisibility(View.GONE);
            } else {
                tv_legenda_foto.setText(conteudo.getImagens().get(0).getLegenda() + " " +
                        conteudo.getImagens().get(0).getFonte());
            }
        } catch (Exception ex) {
            Log.e("ERRO", "Imagem não disponível");
            iv_reportagem.setVisibility(View.GONE);
        }

        tv_reportagem.setText(conteudo.getTexto());
    }

}
