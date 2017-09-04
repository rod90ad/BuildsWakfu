package com.rodrigoad.rodso.wakfustats.Utils;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rodrigoad.rodso.wakfustats.R;

import java.util.ArrayList;

/**
 * Created by rodso on 08/07/2017.
 */

public class RVAdapterComents extends RecyclerView.Adapter<RVAdapterComents.ComentViewHolder> {

    private ArrayList<Coment> coments = new ArrayList<Coment>();
    private Context context;

    public RVAdapterComents (ArrayList<Coment> coments, Context context){
        this.coments=coments;
        this.context=context;
    }

    @Override
    public ComentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coment_list, parent, false);
        RVAdapterComents.ComentViewHolder viewHolder = new RVAdapterComents.ComentViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ComentViewHolder holder, int position) {
        holder.name.setText(coments.get(position).getName());
        holder.coment.setText(coments.get(position).getComent());
    }

    @Override
    public int getItemCount() {
        if(coments==null){
            return 0;
        }else {
            return coments.size();
        }
    }

    public class ComentViewHolder extends RecyclerView.ViewHolder{

        private CardView cv;

        //usuario
        private TextView name;
        private TextView coment;

        public ComentViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.coment_cv);

            name = (TextView) itemView.findViewById(R.id.coment_cv_name);
            coment = (TextView) itemView.findViewById(R.id.coment_cv_coment);
        }
    }
}
