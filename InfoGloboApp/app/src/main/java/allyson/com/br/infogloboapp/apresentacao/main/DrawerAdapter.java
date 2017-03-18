package allyson.com.br.infogloboapp.apresentacao.main;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import allyson.com.br.infogloboapp.InterfacesComuns.OnItemClickListener;
import allyson.com.br.infogloboapp.R;

/**
 * Created by allys on 18/03/2017.
 */

public class DrawerAdapter  extends RecyclerView.Adapter<DrawerAdapter.ViewHolder> {

    private Context context;
    private List<String> editoriais;
    private OnItemClickListener onItemClickListener;


    public DrawerAdapter(Context context, List<String> editoriais, OnItemClickListener onItemClickListener){
        this.context = context;
        this.editoriais = editoriais;
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.drawer_row, parent, false);
        return new DrawerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_editorial.setText(editoriais.get(position));
    }

    @Override
    public int getItemCount() {
        return editoriais.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private AppCompatTextView tv_editorial;
        ViewHolder(View v) {
            super(v);
            tv_editorial = (AppCompatTextView) v.findViewById(R.id.tv_editorial);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(!editoriais.isEmpty()){
                onItemClickListener.OnClick(getAdapterPosition(),editoriais.get(getAdapterPosition()));
            }
        }
    }

}
