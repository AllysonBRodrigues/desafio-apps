package allyson.com.br.infogloboapp.view.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import allyson.com.br.infogloboapp.R;
import allyson.com.br.infogloboapp.view.fragment.CapaFragment;
import allyson.com.br.infogloboapp.view.fragment.LinksExternosFragment;
import allyson.com.br.infogloboapp.view.fragment.ReportagemFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PrincipalActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (savedInstanceState == null) {
            getFragmentManagerTransaction(new CapaFragment(), "CAPA");
        }
    }

    public void getFragmentManagerTransaction(Fragment fragment, String fragmentDestiny) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_conteudo, fragment, fragmentDestiny).addToBackStack("pilha").commitAllowingStateLoss();
    }

    public void mudarTitulo(String titulo) {
        toolbar_title.setText(titulo);
    }

    public void navegacaoIcone(String tela){

        if(tela.equalsIgnoreCase("link") || tela.equalsIgnoreCase("reportagem")) {
            toolbar.setNavigationIcon(R.drawable.voltar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }else{
            toolbar.setNavigationIcon(null);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_conteudo);

        if (fragment instanceof CapaFragment) {
            finish();
        } else if (fragment instanceof ReportagemFragment) {
            getFragmentManagerTransaction(new CapaFragment(), "CAPA");
        } else if (fragment instanceof LinksExternosFragment) {
            getFragmentManagerTransaction(new CapaFragment(), "CAPA");
        }

    }

}
