package allyson.com.br.infogloboapp.view.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import allyson.com.br.infogloboapp.R;
import allyson.com.br.infogloboapp.view.fragment.CapaFragment;

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
