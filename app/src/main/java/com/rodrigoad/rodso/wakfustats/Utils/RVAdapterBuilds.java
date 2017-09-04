package com.rodrigoad.rodso.wakfustats.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rodrigoad.rodso.wakfustats.Layouts.TopBuildsFragment;
import com.rodrigoad.rodso.wakfustats.MainActivity;
import com.rodrigoad.rodso.wakfustats.R;
import com.rodrigoad.rodso.wakfustats.ViewBuildActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by rodso on 17/03/2017.
 */

public class RVAdapterBuilds extends RecyclerView.Adapter<RVAdapterBuilds.ItemViewHolder> {

    private ArrayList<Build> builds;
    private Context context;
    private static ArrayList<CheckBox> group;

    public RVAdapterBuilds(ArrayList<Build> builds, Context context){
        this.builds = builds;
        this.context=context;
        this.group = new ArrayList<CheckBox>();
    }

    @Override
    public int getItemCount() {
        return builds.size();
    }

    @Override
    public RVAdapterBuilds.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = null;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.buildlist, viewGroup, false);
        RVAdapterBuilds.ItemViewHolder viewHolder = new RVAdapterBuilds.ItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RVAdapterBuilds.ItemViewHolder itemViewHolder, final int i) {
        itemViewHolder.nome.setText(builds.get(i).getNome());
        itemViewHolder.nome.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

        itemViewHolder.nivel.setText(context.getResources().getString(R.string.view_nivel)+builds.get(i).getNivel());
        itemViewHolder.nivel.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

        switch (builds.get(i).getClasse()){
            case 0:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.cra_head));
                break;
            case 1:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.eca_head));
                break;
            case 2:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.elio_head));
                break;
            case 3:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.eni_head));
                break;
            case 4:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.enu_head));
                break;
            case 5:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.feca_head));
                break;
            case 6:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.hupp_head));
                break;
            case 7:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.iop_head));
                break;
            case 8:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.osa_head));
                break;
            case 9:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.panda_head));
                break;
            case 10:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.lad_head));
                break;
            case 11:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.sac_head));
                break;
            case 12:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.sad_head));
                break;
            case 13:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.sram_head));
                break;
            case 14:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.steam_head));
                break;
            case 15:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.xelor_head));
                break;
            case 16:
                itemViewHolder.head.setBackground(context.getResources().getDrawable(R.drawable.zob_head));
                break;
        }
        if(builds.get(i).isOnline()){
            itemViewHolder.box.getLayoutParams().height=0;
        }else {
            itemViewHolder.box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemViewHolder.box.isChecked();
                    MainActivity.build = builds.get(i);
                    Toast.makeText(context, context.getResources().getString(R.string.buildselected), Toast.LENGTH_SHORT).show();
                    VerificaCheckBox(itemViewHolder.box);
                }
            });
        }
        itemViewHolder.head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.context, ViewBuildActivity.class);
                intent.putExtra("build", new Gson().toJson(builds.get(i)));
                if(builds.get(i).isOnline()){
                    TopBuildsFragment.RemoveListener();
                }
                context.startActivity(intent);
            }
        });
    }

    private void VerificaCheckBox(CheckBox box){
        for(int i=0;i<group.size();i++){
            if(!group.get(i).equals(box)){
                group.get(i).setChecked(false);
            }
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        //atributos
        CardView cv;
        LinearLayout buildSelect;

        //textos titulos
        private TextView nome;
        private TextView nivel;

        //imageview
        private ImageView head;

        //checkbox
        private CheckBox box;

        ItemViewHolder(View itemView) {
            super(itemView);
            //pega dimensões da tela
            int screen_width = Resources.getSystem().getDisplayMetrics().widthPixels;
            RelativeLayout.LayoutParams icon_item_size = new RelativeLayout.LayoutParams(screen_width / 5, screen_width / 5);
            RelativeLayout.LayoutParams icon_rarity_size = new RelativeLayout.LayoutParams(screen_width / 20, screen_width / 20);
            RelativeLayout.LayoutParams item_nome_size = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, screen_width / 12 - 12);
            item_nome_size.leftMargin = screen_width / 18;
            RelativeLayout.LayoutParams item_tipo_size = new RelativeLayout.LayoutParams(screen_width / 20, screen_width / 20);
            item_tipo_size.topMargin = 6;
            RelativeLayout.LayoutParams item_nivel_size = new RelativeLayout.LayoutParams((screen_width / 100 * 60), screen_width / 12 - 12);
            //cardview
            cv = (CardView) itemView.findViewById(R.id.cv_builds);

            //cabeçalhos
            nome = (TextView) itemView.findViewById(R.id.view_nome);
            nivel = (TextView) itemView.findViewById(R.id.view_nivel);

            //head
            head = (ImageView) itemView.findViewById(R.id.view_head);

            box = (CheckBox) itemView.findViewById(R.id.build_check);
            group.add(box);
        }
    }

}
