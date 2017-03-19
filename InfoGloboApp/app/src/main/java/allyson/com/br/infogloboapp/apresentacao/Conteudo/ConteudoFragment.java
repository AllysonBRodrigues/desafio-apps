package allyson.com.br.infogloboapp.apresentacao.Conteudo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import allyson.com.br.infogloboapp.InterfacesComuns.OnItemClickListener;
import allyson.com.br.infogloboapp.R;
import allyson.com.br.infogloboapp.apresentacao.LinkExternos.LinksExternosFragment;
import allyson.com.br.infogloboapp.apresentacao.Principal.PrincipalActivity;
import allyson.com.br.infogloboapp.apresentacao.Reportagem.ReportagemFragment;
import allyson.com.br.infogloboapp.data.api.Manager;
import allyson.com.br.infogloboapp.model.Conteudo;
import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Implementação do fragment da tela inicial onde é exibido o contéudo
 */
public class ConteudoFragment extends Fragment implements OnItemClickListener, ConteudoContrato.View {
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
    private ConteudoContrato.Apresentacao apresentacao;

    public ConteudoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_capa, container, false);
        ButterKnife.bind(this, view);
        apresentacao = new ConteudoApresentacao();
        apresentacao.bind(this, new Manager());
        principalActivity = (PrincipalActivity) getActivity();
        principalActivity.mudarTitulo("O GLOBO");
        principalActivity.configurarNavegacao("capa");
        checkInstanceState(savedInstanceState);

        return view;
    }

    /**
     * Clique na lista de noticias, captura o objeto selecionado e direciona para a tela de exibição
     * do conteúdo
     * Verifica se o tipo de reportagem é linkextreno, caso seja verdadeiro direcionada para o
     * fragment LinkExternosFragment para que seja carregada em uma webview. Caso o contrario carrega
     * o fragment ReportagemFragment onde será carregado as informaçoes da matéria.
     * @param pos posição na lista que se encontra o objeto selecionado
     * @param object objeto que foi selecionado
     */
    @Override
    public void OnClick(int pos, Object object) {
        Conteudo conteudo = (Conteudo) object;

        if (conteudo.getTipo().equalsIgnoreCase("linkExterno")) {
            LinksExternosFragment linksExternosFragment = new LinksExternosFragment();
            Gson gson = new Gson();
            Bundle bundle = new Bundle();
            bundle.putString("reportagem", gson.toJson(conteudo));
            linksExternosFragment.setArguments(bundle);
            principalActivity.getFragmentManagerTransaction(linksExternosFragment, "REPORTAGEM");
        } else {
            ReportagemFragment reportagemFragment = new ReportagemFragment();
            Gson gson = new Gson();
            Bundle bundle = new Bundle();
            bundle.putString("reportagem", gson.toJson(conteudo));
            reportagemFragment.setArguments(bundle);
            principalActivity.getFragmentManagerTransaction(reportagemFragment, "REPORTAGEM");
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Gson gson = new Gson();
        List<Conteudo> conteudos = new ArrayList<>();
        conteudos.add(capa);
        conteudos.addAll(editorial);
        outState.putString("conteudos", gson.toJson(conteudos));
    }

    /**
     * Recebe uma lista de conteudos da camada de apresentação ou de uma instancia salva e
     * exibe as informaçãos para o usuario.
     * Caso exista algum erro ao carregar a tela, ele faz uma nova requisição ao webservice
     * para recuperar os conteudos
     *
     * @param conteudos lista de conteudos que serão exibidos na tela
     */

    @Override
    public void atualizarView(List<Conteudo> conteudos) {
        try {
            capa = conteudos.get(0);
            editorial = conteudos.subList(1, conteudos.size());

            principalActivity.mudarTitulo("O GLOBO");
            principalActivity.configurarNavegacao("capa");
            Picasso.with(getActivity())
                    .load(conteudos.get(0).getImagens().get(0).getUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(iv_capa);

            iv_capa.setVisibility(View.VISIBLE);
            tv_titulo.setText(capa.getTitulo());
            tv_secao_nome.setText(capa.getSecao().getNome());
            tv_secao_nome.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.preto_trans));
            iv_capa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.OnClick(0, capa);
                }
            });
            pb_capa.setVisibility(View.GONE);
            rv_editorial.setLayoutManager(new LinearLayoutManager(getActivity()));
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rv_editorial.getContext(),
                    new LinearLayoutManager(getActivity()).getOrientation());
            rv_editorial.addItemDecoration(dividerItemDecoration);
            ConteudoAdapter editorialAdapter = new ConteudoAdapter(getActivity(), editorial, onItemClickListener);
            rv_editorial.setAdapter(editorialAdapter);
            editorialAdapter.notifyDataSetChanged();
            scrollView.fullScroll(View.FOCUS_UP);
        } catch (Exception e) {
            apresentacao.carregarConteudo();
        }
    }

    @Override
    public void erro() {
        Toast.makeText(getActivity(), "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show();
    }

    /**
     * Verifica se a tela possui uma instancia salva,
     * Caso seja falso, inicia comunicação do websevice para carregar os dados
     * Caso já possua instancia, recupera os dados dessa instancia e controi a tela
     * Evitando acesso desnecessario ao webservice na mundaça de orientação da tela
     *
     * @param savedInstanceState instancia da tela
     */

    @Override
    public void checkInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<ArrayList<Conteudo>>() {
            }.getType();
            List<Conteudo> conteudos = gson.fromJson(savedInstanceState.getString("conteudos"), tipoLista);
            atualizarView(conteudos);

        } else {
            pb_capa.setVisibility(View.VISIBLE);
            apresentacao.carregarConteudo();
        }
    }
}
