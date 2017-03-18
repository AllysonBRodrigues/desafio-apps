package allyson.com.br.infogloboapp.apresentacao.LinkExternos;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.gson.Gson;

import allyson.com.br.infogloboapp.R;
import allyson.com.br.infogloboapp.model.Conteudo;
import allyson.com.br.infogloboapp.apresentacao.main.PrincipalActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinksExternosFragment extends Fragment {


    public LinksExternosFragment() {
        // Required empty public constructor
    }

    @BindView(R.id.wv_externos)
    WebView wv_externos;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_links_externos, container, false);
        setHasOptionsMenu(true);

        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();
        Gson gson = new Gson();
        Conteudo conteudo = gson.fromJson(bundle.getString("reportagem"), Conteudo.class);
        PrincipalActivity principal = (PrincipalActivity) getActivity();
        principal.mudarTitulo(conteudo.getSecao().getNome());
        principal.configurarNavegacao("link");
        setWebViewClient(wv_externos);
        if (conteudo.getUrl() == null) {
            wv_externos.loadUrl(conteudo.getSecao().getUrl());
        } else {
            wv_externos.loadUrl(conteudo.getUrl());
        }
        return view;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bt_topo:
                wv_externos.scrollTo(0, 0);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setWebViewClient(WebView webview) {
        webview.setWebViewClient(new WebViewClient() {
                                     @Override
                                     public void onPageStarted(WebView webView, String url, Bitmap favicon) {
                                         super.onPageStarted(webView, url, favicon);
                                         progressBar.setVisibility(View.VISIBLE);
                                     }

                                     @Override
                                     public void onPageFinished(WebView webView, String url) {
                                         progressBar.setVisibility(View.INVISIBLE);

                                     }
                                 }


        );
    }

}
