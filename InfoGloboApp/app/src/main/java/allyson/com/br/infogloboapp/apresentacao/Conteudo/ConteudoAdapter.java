package allyson.com.br.infogloboapp.apresentacao.Conteudo;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import allyson.com.br.infogloboapp.InterfacesComuns.OnItemClickListener;
import allyson.com.br.infogloboapp.R;
import allyson.com.br.infogloboapp.model.Conteudo;

/**
 * Created by Allyson Rodrigues 15/03/2017.
 * Adapter para exibição do conteudo.
 */

class ConteudoAdapter extends RecyclerView.Adapter<ConteudoAdapter.ViewHolder> {

    private Context context;
    private List<Conteudo> conteudos;
    private OnItemClickListener onItemClickListener;

    ConteudoAdapter(Context context, List<Conteudo> conteudos, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.conteudos = conteudos;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_editorial, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Conteudo conteudo = conteudos.get(position);
        holder.tv_editorial.setText(conteudo.getSecao().getNome());
        holder.tv_titulo_editorial.setText(conteudo.getTitulo());
        try {
            Picasso.with(context)
                    .load(conteudo.getImagens().get(0).getUrl())
                    .placeholder(R.drawable.placeholder)
                    .into(holder.iv_editorial);
        } catch (Exception ex) {
            Log.e("ERRO", "Imagem não disponível");
            holder.iv_editorial.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return conteudos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatTextView tv_editorial;
        private AppCompatTextView tv_titulo_editorial;
        private AppCompatImageView iv_editorial;

        ViewHolder(View v) {
            super(v);
            tv_editorial = (AppCompatTextView) v.findViewById(R.id.tv_editorial);
            tv_titulo_editorial = (AppCompatTextView) v.findViewById(R.id.tv_titulo_editorial);
            iv_editorial = (AppCompatImageView) v.findViewById(R.id.iv_editorial);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (!conteudos.isEmpty()) {
                onItemClickListener.OnClick(getAdapterPosition(), conteudos.get(getAdapterPosition()));
            }
        }
    }

}
