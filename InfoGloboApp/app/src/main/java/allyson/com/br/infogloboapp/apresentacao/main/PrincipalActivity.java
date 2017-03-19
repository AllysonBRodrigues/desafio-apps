package allyson.com.br.infogloboapp.apresentacao.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import allyson.com.br.infogloboapp.InterfacesComuns.OnItemClickListener;
import allyson.com.br.infogloboapp.R;
import allyson.com.br.infogloboapp.apresentacao.conteudo.ConteudoFragment;
import allyson.com.br.infogloboapp.model.Conteudo;
import allyson.com.br.infogloboapp.model.Secao;
import allyson.com.br.infogloboapp.apresentacao.LinkExternos.LinksExternosFragment;
import allyson.com.br.infogloboapp.apresentacao.Reportagem.ReportagemFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, OnItemClickListener{

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    private Toolbar toolbar;
    NavigationView navigationView;
    private DrawerAdapter drawerAdapter;
    @BindView(R.id.rv_drawer)
    RecyclerView rv_drawer;
    private List<String> editoriais;
    private OnItemClickListener onItemClickListener = this;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        editoriais = new ArrayList<>();
        Collections.addAll(editoriais, getResources().getStringArray(R.array.drawer_list));

        if (savedInstanceState == null) {
            getFragmentManagerTransaction(new ConteudoFragment(), "CAPA");
        }
    }

    public void getFragmentManagerTransaction(Fragment fragment, String fragmentDestiny) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_conteudo, fragment, fragmentDestiny).addToBackStack("pilha").commitAllowingStateLoss();
    }

    public void mudarTitulo(String titulo) {
        toolbar_title.setText(titulo);
    }

    public void configurarNavegacao(String tela){

        if(tela.equalsIgnoreCase("link") || tela.equalsIgnoreCase("reportagem")) {
            toolbar.setNavigationIcon(R.drawable.voltar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }else{

            rv_drawer.setLayoutManager(new LinearLayoutManager(this));
            drawerAdapter = new DrawerAdapter(this, editoriais,onItemClickListener);
            rv_drawer.setAdapter(drawerAdapter);
            drawerAdapter.notifyDataSetChanged();

            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.addDrawerListener(toggle);
            toggle.syncState();

            navigationView = (NavigationView) findViewById(R.id.nav_view);
            toolbar.setNavigationIcon(R.drawable.ic_drawer);
        }
    }


    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_conteudo);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if (fragment instanceof ConteudoFragment) {
            finish();
        } else if (fragment instanceof ReportagemFragment) {
            getFragmentManagerTransaction(new ConteudoFragment(), "CAPA");
        } else if (fragment instanceof LinksExternosFragment) {
            getFragmentManagerTransaction(new ConteudoFragment(), "CAPA");
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void OnClick(int pos, Object object) {
        Conteudo conteudo = new Conteudo();
        final LinksExternosFragment linksExternosFragment = new LinksExternosFragment();
        Bundle bundle = new Bundle();
        Secao secao = new Secao();
        secao.setNome(object.toString());
        conteudo.setSecao(secao);
        Gson gson = new Gson();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        switch (pos){
            case 0:
                conteudo.getSecao().setUrl("https://m.oglobo.globo.com/rio/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;
            case 1:
                conteudo.getSecao().setUrl("https://m.oglobo.globo.com/brasil/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;
            case 2:
                conteudo.getSecao().setUrl("https://m.oglobo.globo.com/mundo/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;
            case 3:
                conteudo.getSecao().setUrl("https://m.oglobo.globo.com/economia/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;
            case 4:
                conteudo.getSecao().setUrl("https://m.oglobo.globo.com/sociedade/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;
            case 5:
                conteudo.getSecao().setUrl("http://oglobo.globo.com/sociedade/tecnologia/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;
            case 6:
                conteudo.getSecao().setUrl("http://oglobo.globo.com/sociedade/ciencia/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;
            case 7:
                conteudo.getSecao().setUrl("http://oglobo.globo.com/sociedade/saude/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;
            case 8:
                conteudo.getSecao().setUrl("https://m.oglobo.globo.com/ela/");
                break;
            case 9:
                conteudo.getSecao().setUrl("https://m.oglobo.globo.com/cultura/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;
            case 10:
                conteudo.getSecao().setUrl("http://oglobo.globo.com/cultura/revista-da-tv/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;
            case 11:
                conteudo.getSecao().setUrl("http://rioshow.oglobo.globo.com/aspx/geral/home_principal.aspx");
                break;
            case 12:
                conteudo.getSecao().setUrl("http://oglobo.globo.com/esportes/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;
            case 13:
                conteudo.getSecao().setUrl("http://oglobo.globo.com/fotogalerias/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;

            case 14:
                conteudo.getSecao().setUrl("http://oglobo.globo.com/videos/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;
            case 15:
                conteudo.getSecao().setUrl("http://oglobo.globo.com/horoscopo/");
                bundle.putString("reportagem", gson.toJson(conteudo));
                break;

        }
        linksExternosFragment.setArguments(bundle);
        getFragmentManagerTransaction(linksExternosFragment, "REPORTAGEM");
    }
}
