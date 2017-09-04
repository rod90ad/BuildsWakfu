package com.rodrigoad.rodso.wakfustats.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rodrigoad.rodso.wakfustats.Layouts.SearchScreenFragment;
import com.rodrigoad.rodso.wakfustats.MainActivity;
import com.rodrigoad.rodso.wakfustats.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rodso on 25/07/2016.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ItemViewHolder> {

    private static List<Item> itens;
    private Context context;
    private SearchScreenFragment sscren;
    private int AD_TYPE=0;
    private int CONTENT_TYPE =1;

    public RVAdapter(List<Item> itens, Context context, SearchScreenFragment sscreen){
        this.itens = itens;
        this.context=context;
        this.sscren=sscreen;
    }

    @Override
    public int getItemViewType(int position)
    {
        if (position % 10 == 0 && position!=0)
            return AD_TYPE;
        return CONTENT_TYPE;
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = null;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemlist, viewGroup, false);
        ItemViewHolder viewHolder = new ItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        boolean Status = false;
        Boolean cinza = false;
        Context context = itemViewHolder.gridStats.getContext();

        itemViewHolder.itemName.setText(itens.get(i).getNome());
        itemViewHolder.itemNivel.setText(context.getResources().getString(R.string.nivel) + ": " + itens.get(i).getNivel());
        itemViewHolder.add.setOnClickListener(new onClickHandler(itens.get(i)));
        switch (itens.get(i).getRaridade()) {
            case 1:
                itemViewHolder.icon_rarity.setImageResource(R.mipmap.comum);
                break;
            case 2:
                itemViewHolder.icon_rarity.setImageResource(R.mipmap.incomum);
                break;
            case 3:
                itemViewHolder.icon_rarity.setImageResource(R.mipmap.raro);
                break;
            case 4:
                itemViewHolder.icon_rarity.setImageResource(R.mipmap.mitico);
                break;
            case 5:
                itemViewHolder.icon_rarity.setImageResource(R.mipmap.lendario);
                break;
            case 6:
                itemViewHolder.icon_rarity.setImageResource(R.mipmap.reliquia);
                break;
            case 7:
                itemViewHolder.icon_rarity.setImageResource(R.mipmap.epico);
                break;
            case 8:
                itemViewHolder.icon_rarity.setImageResource(R.mipmap.pvp);
                break;
        }
        Picasso.with(context).load(itens.get(i).getLink()).into(itemViewHolder.itemImage);
        switch (itens.get(i).getTipo()) {
            case "elm":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.elmo);
                break;
            case "amu":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.amu);
                break;
            case "w1h":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.mao1);
                break;
            case "bot":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.bota);
                break;
            case "cap":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.cap);
                break;
            case "drag":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.drag);
                break;
            case "pei":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.peitoral);
                break;
            case "pet":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.pet);
                break;
            case "mont":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.mont);
                break;
            case "cint":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.cinto);
                break;
            case "anel":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.anel);
                break;
            case "w2h":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.mao2);
                break;
            case "wsec":
                itemViewHolder.item_tipo.setImageResource(R.mipmap.segmao);
                break;
        }
        RelativeLayout.LayoutParams lpText = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpText.addRule(RelativeLayout.ALIGN_LEFT);
        itemViewHolder.gridStats.removeAllViews();

        //PA
        if (itens.get(i).getActionpoint() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getActionpoint() > 0) {
                txStats.setText("+" + itens.get(i).getActionpoint() + " " + context.getResources().getString(R.string.posPA));
            } else {
                txStats.setText(itens.get(i).getActionpoint() + " " + context.getResources().getString(R.string.posPA));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //PM
        if (itens.get(i).getMovepoint() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getMovepoint() > 0) {
                txStats.setText("+" + itens.get(i).getMovepoint() + " " + context.getResources().getString(R.string.posPM));
            } else {
                txStats.setText(itens.get(i).getMovepoint() + " " + context.getResources().getString(R.string.posPM));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //WP
        if (itens.get(i).getWakfupoint() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getWakfupoint() > 0) {
                txStats.setText("+" + itens.get(i).getWakfupoint() + " " + context.getResources().getString(R.string.posWP));
            } else {
                txStats.setText(itens.get(i).getWakfupoint() + " " + context.getResources().getString(R.string.posWP));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //alcance
        if (itens.get(i).getRange() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getRange() > 0) {
                txStats.setText("+" + itens.get(i).getRange() + " " + context.getResources().getString(R.string.posRange));
            } else {
                txStats.setText(itens.get(i).getRange() + " " + context.getResources().getString(R.string.posRange));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Controle
        if (itens.get(i).getControl() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getControl() > 0) {
                txStats.setText("+" + itens.get(i).getControl() + " " + context.getResources().getString(R.string.posCtrl));
            } else {
                txStats.setText(itens.get(i).getControl() + " " + context.getResources().getString(R.string.posCtrl));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //PV
        if (itens.get(i).getVitalpoint() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getVitalpoint() > 0) {
                txStats.setText("+" + itens.get(i).getVitalpoint() + " " + context.getResources().getString(R.string.posPV));
            } else {
                txStats.setText(itens.get(i).getVitalpoint() + " " + context.getResources().getString(R.string.posPV));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //esquiva
        if (itens.get(i).getEvasion() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getEvasion() > 0) {
                txStats.setText("+" + itens.get(i).getEvasion() + " " + context.getResources().getString(R.string.posEva));
            } else {
                txStats.setText(itens.get(i).getEvasion() + " " + context.getResources().getString(R.string.posEva));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //sabedoria
        if (itens.get(i).getSabedoria() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getSabedoria() > 0) {
                txStats.setText("+" + itens.get(i).getSabedoria() + " " + context.getResources().getString(R.string.posSab));
            } else {
                txStats.setText(itens.get(i).getSabedoria() + " " + context.getResources().getString(R.string.posSab));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Prospecção
        if (itens.get(i).getProsp() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getProsp() > 0) {
                txStats.setText("+" + itens.get(i).getProsp() + " " + context.getResources().getString(R.string.posProsp));
            } else {
                txStats.setText(itens.get(i).getProsp() + " " + context.getResources().getString(R.string.posProsp));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //bloqueio
        if (itens.get(i).getBlock() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getBlock() > 0) {
                txStats.setText("+" + itens.get(i).getBlock() + " " + context.getResources().getString(R.string.posblock));
            } else {
                txStats.setText(itens.get(i).getBlock() + " " + context.getResources().getString(R.string.posblock));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //iniciativa
        if (itens.get(i).getIniciative() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getIniciative() > 0) {
                txStats.setText("+" + itens.get(i).getIniciative() + " " + context.getResources().getString(R.string.posIni));
            } else {
                txStats.setText(itens.get(i).getIniciative() + " " + context.getResources().getString(R.string.posIni));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Golpes criticos
        if (itens.get(i).getCriticalchance() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getCriticalchance() > 0) {
                txStats.setText("+" + itens.get(i).getCriticalchance() + context.getResources().getString(R.string.posCChance));
            } else {
                txStats.setText(itens.get(i).getCriticalchance() + context.getResources().getString(R.string.posCChance));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //parada
        if (itens.get(i).getStop() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getStop() > 0) {
                txStats.setText("+" + itens.get(i).getStop() + context.getResources().getString(R.string.posstop));
            } else {
                txStats.setText(itens.get(i).getStop() + context.getResources().getString(R.string.posstop));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Dano geral
        if (itens.get(i).getDmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getDmg() > 0) {
                txStats.setText("+" + itens.get(i).getDmg() + " " + context.getResources().getString(R.string.posDmg));
            } else {
                txStats.setText(itens.get(i).getDmg() + " " + context.getResources().getString(R.string.posDmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dano 1 elemento
        if (itens.get(i).getElement1dmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getElement1dmg() > 0) {
                txStats.setText("+" + itens.get(i).getElement1dmg() + " " + context.getResources().getString(R.string.pos1Dmg));
            } else {
                txStats.setText(itens.get(i).getElement1dmg() + " " + context.getResources().getString(R.string.pos1Dmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dano bi
        if (itens.get(i).getElement2dmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getElement2dmg() > 0) {
                txStats.setText("+" + itens.get(i).getElement2dmg() + " " + context.getResources().getString(R.string.pos2Dmg));
            } else {
                txStats.setText(itens.get(i).getElement2dmg() + " " + context.getResources().getString(R.string.pos2Dmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dano tri
        if (itens.get(i).getElement3dmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getElement3dmg() > 0) {
                txStats.setText("+" + itens.get(i).getElement3dmg() + " " + context.getResources().getString(R.string.pos3Dmg));
            } else {
                txStats.setText(itens.get(i).getElement3dmg() + " " + context.getResources().getString(R.string.pos3Dmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dano beserk
        if (itens.get(i).getBeserkdmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getBeserkdmg() > 0) {
                txStats.setText("+" + itens.get(i).getBeserkdmg() + " " + context.getResources().getString(R.string.posbskdmg));
            } else {
                txStats.setText(itens.get(i).getBeserkdmg() + " " + context.getResources().getString(R.string.posbskdmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dano corpo a corpo
        if (itens.get(i).getClosecombatdmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getClosecombatdmg() > 0) {
                txStats.setText("+" + itens.get(i).getClosecombatdmg() + " " + context.getResources().getString(R.string.poscacdmg));
            } else {
                txStats.setText(itens.get(i).getClosecombatdmg() + " " + context.getResources().getString(R.string.poscacdmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dano alvo unico
        if (itens.get(i).getOnetargetdmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getOnetargetdmg() > 0) {
                txStats.setText("+" + itens.get(i).getOnetargetdmg() + " " + context.getResources().getString(R.string.posonedmg));
            } else {
                txStats.setText(itens.get(i).getOnetargetdmg() + " " + context.getResources().getString(R.string.posonedmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dano distancia
        if (itens.get(i).getDistancedmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getDistancedmg() > 0) {
                txStats.setText("+" + itens.get(i).getDistancedmg() + " " + context.getResources().getString(R.string.posdistdmg));
            } else {
                txStats.setText(itens.get(i).getDistancedmg() + " " + context.getResources().getString(R.string.posdistdmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dano zona area
        if (itens.get(i).getAreadmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getAreadmg() > 0) {
                txStats.setText("+" + itens.get(i).getAreadmg() + " " + context.getResources().getString(R.string.posareadmg));
            } else {
                txStats.setText(itens.get(i).getAreadmg() + " " + context.getResources().getString(R.string.posareadmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dano nas costas
        if (itens.get(i).getBackdmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getBackdmg() > 0) {
                txStats.setText("+" + itens.get(i).getBackdmg() + " " + context.getResources().getString(R.string.posbackdmg));
            } else {
                txStats.setText(itens.get(i).getBackdmg() + " " + context.getResources().getString(R.string.posbackdmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Dominio Cura
        if (itens.get(i).getHeal() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getHeal() > 0) {
                txStats.setText("+" + itens.get(i).getHeal() + " " + context.getResources().getString(R.string.posHeal));
            } else {
                txStats.setText(itens.get(i).getHeal() + " " + context.getResources().getString(R.string.posHeal));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Dano Critico
        if (itens.get(i).getCriticaldmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getCriticaldmg() > 0) {
                txStats.setText("+" + itens.get(i).getCriticaldmg() + " " + context.getResources().getString(R.string.posDC));
            } else {
                txStats.setText(itens.get(i).getCriticaldmg() + " " + context.getResources().getString(R.string.posDC));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a Agua
        if (itens.get(i).getReswater() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getReswater() > 0) {
                txStats.setText("+" + itens.get(i).getReswater() + " " + context.getResources().getString(R.string.posResw));
            } else {
                txStats.setText(itens.get(i).getReswater() + " " + context.getResources().getString(R.string.posResw));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a Fogo
        if (itens.get(i).getResfire() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getResfire() > 0) {
                txStats.setText("+" + itens.get(i).getResfire() + " " + context.getResources().getString(R.string.posResf));
            } else {
                txStats.setText(itens.get(i).getResfire() + " " + context.getResources().getString(R.string.posResf));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a Ar
        if (itens.get(i).getResair() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getResair() > 0) {
                txStats.setText("+" + itens.get(i).getResair() + " " + context.getResources().getString(R.string.posResa));
            } else {
                txStats.setText(itens.get(i).getResair() + " " + context.getResources().getString(R.string.posResa));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Feitiço fogo
        if (itens.get(i).getNvffogo() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getNvffogo() > 0) {
                txStats.setText("+" + itens.get(i).getNvffogo() + " " + context.getResources().getString(R.string.posffogo));
            } else {
                txStats.setText(itens.get(i).getNvffogo() + " " + context.getResources().getString(R.string.posffogo));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Feitiço agua
        if (itens.get(i).getNvfagua() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getNvfagua() > 0) {
                txStats.setText("+" + itens.get(i).getNvfagua() + " " + context.getResources().getString(R.string.posfagua));
            } else {
                txStats.setText(itens.get(i).getNvfagua() + " " + context.getResources().getString(R.string.posfagua));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Feitiço terra
        if (itens.get(i).getNvfterra() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getNvfterra() > 0) {
                txStats.setText("+" + itens.get(i).getNvfterra() + " " + context.getResources().getString(R.string.posfterra));
            } else {
                txStats.setText(itens.get(i).getNvfterra() + " " + context.getResources().getString(R.string.posfterra));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Resistencia MP
        if (itens.get(i).getRespm() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getRespm() > 0) {
                txStats.setText("+" + itens.get(i).getRespm() + " " + context.getResources().getString(R.string.posresmp));
            } else {
                txStats.setText(itens.get(i).getRespm() + " " + context.getResources().getString(R.string.posresmp));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Resistencia AP
        if (itens.get(i).getRespa() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getRespa() > 0) {
                txStats.setText("+" + itens.get(i).getRespa() + " " + context.getResources().getString(R.string.posresap));
            } else {
                txStats.setText(itens.get(i).getRespa() + " " + context.getResources().getString(R.string.posresap));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Feitiço ar
        if (itens.get(i).getNvfar() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getNvfar() > 0) {
                txStats.setText("+" + itens.get(i).getNvfar() + " " + context.getResources().getString(R.string.posfar));
            } else {
                txStats.setText(itens.get(i).getNvfar() + " " + context.getResources().getString(R.string.posfar));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Feitiço geral
        if (itens.get(i).getNvfgeral() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getNvfgeral() > 0) {
                txStats.setText("+" + itens.get(i).getNvfgeral() + " " + context.getResources().getString(R.string.posfgeral));
            } else {
                txStats.setText(itens.get(i).getNvfgeral() + " " + context.getResources().getString(R.string.posfgeral));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dominio fogo
        if (itens.get(i).getDmgfogo() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getDmgfogo() > 0) {
                txStats.setText("+" + itens.get(i).getDmgfogo() + " " + context.getResources().getString(R.string.posdmgfogo));
            } else {
                txStats.setText(itens.get(i).getDmgfogo() + " " + context.getResources().getString(R.string.posdmgfogo));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dominio agua
        if (itens.get(i).getDmgagua() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getDmgagua() > 0) {
                txStats.setText("+" + itens.get(i).getDmgagua() + " " + context.getResources().getString(R.string.posdmgagua));
            } else {
                txStats.setText(itens.get(i).getDmgagua() + " " + context.getResources().getString(R.string.posdmgagua));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dominio terra
        if (itens.get(i).getDmgterra() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getDmgterra() > 0) {
                txStats.setText("+" + itens.get(i).getDmgterra() + " " + context.getResources().getString(R.string.posdmgterra));
            } else {
                txStats.setText(itens.get(i).getDmgterra() + " " + context.getResources().getString(R.string.posdmgterra));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //dominio ar
        if (itens.get(i).getDmgar() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getDmgar() > 0) {
                txStats.setText("+" + itens.get(i).getDmgar() + " " + context.getResources().getString(R.string.posdmgar));
            } else {
                txStats.setText(itens.get(i).getDmgar() + " " + context.getResources().getString(R.string.posdmgar));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a Terra
        if (itens.get(i).getResearth() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getResearth() > 0) {
                txStats.setText("+" + itens.get(i).getResearth() + " " + context.getResources().getString(R.string.posRest));
            } else {
                txStats.setText(itens.get(i).getResearth() + " " + context.getResources().getString(R.string.posRest));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Resistencia
        if (itens.get(i).getResist() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getResist() > 0) {
                txStats.setText("+" + itens.get(i).getResist() + " " + context.getResources().getString(R.string.posRes));
            } else {
                txStats.setText(itens.get(i).getResist() + " " + context.getResources().getString(R.string.posRes));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a 1 elementos
        if (itens.get(i).getRes1elerandom() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getRes1elerandom() > 0) {
                txStats.setText("+" + itens.get(i).getRes1elerandom() + " " + context.getResources().getString(R.string.posRes1));
            } else {
                txStats.setText(itens.get(i).getRes1elerandom() + " " + context.getResources().getString(R.string.posRes1));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a 2 elementos
        if (itens.get(i).getRes2elerandom() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getRes2elerandom() > 0) {
                txStats.setText("+" + itens.get(i).getRes2elerandom() + " " + context.getResources().getString(R.string.posRes2));
            } else {
                txStats.setText(itens.get(i).getRes2elerandom() + " " + context.getResources().getString(R.string.posRes2));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a 3 elementos
        if (itens.get(i).getRes3elerandom() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getRes3elerandom() > 0) {
                txStats.setText("+" + itens.get(i).getRes3elerandom() + " " + context.getResources().getString(R.string.posRes3));
            } else {
                txStats.setText(itens.get(i).getRes3elerandom() + " " + context.getResources().getString(R.string.posRes3));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a critico
        if (itens.get(i).getRescriticaldmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getRescriticaldmg() > 0) {
                txStats.setText("+" + itens.get(i).getRescriticaldmg() + " " + context.getResources().getString(R.string.posrescritical));
            } else {
                txStats.setText(itens.get(i).getRescriticaldmg() + " " + context.getResources().getString(R.string.posrescritical));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a dano costas
        if (itens.get(i).getResblackdmg() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getResblackdmg() > 0) {
                txStats.setText("+" + itens.get(i).getResblackdmg() + " " + context.getResources().getString(R.string.posresbackdmg));
            } else {
                txStats.setText(itens.get(i).getResblackdmg() + " " + context.getResources().getString(R.string.posresbackdmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Colheita minerador
        if (itens.get(i).getMinertake() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getMinertake() > 0) {
                txStats.setText("+" + itens.get(i).getMinertake() + context.getResources().getString(R.string.posminertake));
            } else {
                txStats.setText(itens.get(i).getMinertake() + context.getResources().getString(R.string.posminertake));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Velocidade de movimento
        if (itens.get(i).getMovespeed() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getMovespeed() > 0) {
                txStats.setText("+" + itens.get(i).getMovespeed() + context.getResources().getString(R.string.posmovespeed));
            } else {
                txStats.setText(itens.get(i).getMovespeed() + context.getResources().getString(R.string.posmovespeed));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        //Arte de Equipar
        if (itens.get(i).getArteequipar() != 0) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (itens.get(i).getArteequipar() > 0) {
                txStats.setText("+" + itens.get(i).getArteequipar() + " " + context.getResources().getString(R.string.posartequipar));
            } else {
                txStats.setText(itens.get(i).getArteequipar() + " " + context.getResources().getString(R.string.posartequipar));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }

        /*Status de Itens Shushu ou outros itens.
        if (!itens.get(i).getStatus().equals("0")) {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            txStats.setText(itens.get(i).getStatus());
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            itemViewHolder.gridStats.addView(stats);
            Status = true;
        }*/

        if (Status) {
        } else {
            itemViewHolder.gridStats.setRowCount(itemViewHolder.gridStats.getRowCount() + 1);
            RelativeLayout rlNoStats = new RelativeLayout(context);
            TextView txNoStats = new TextView(context);
            txNoStats.setLayoutParams(lpText);
            txNoStats.setPadding(6, 6, 6, 6);
            txNoStats.setText(R.string.noStats);
            if (cinza) {
                txNoStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txNoStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            rlNoStats.addView(txNoStats);
            itemViewHolder.gridStats.addView(rlNoStats);
        }
        context.getResources().getDimension(R.dimen.screen60p);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{

        //atributos
        CardView cv;
        ImageView icon_layout;
        ImageView icon_rarity;
        ImageView item_tipo;
        GridLayout gridItemV;
        TextView itemName;
        TextView itemNivel;
        ImageView itemImage;
        ImageView add;
        GridLayout gridStats;


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
            cv = (CardView) itemView.findViewById(R.id.cv);
            //icon layout
            /*
            gridItemV = (GridLayout) itemView.findViewById(R.id.grid_nome);
            GridLayout.LayoutParams itemLP = (GridLayout.LayoutParams) gridItemV.getLayoutParams();
            itemLP.width = screen_width / 100 * 68;
            gridItemV.setLayoutParams(itemLP);*/
            //icon
            icon_layout = (ImageView) itemView.findViewById(R.id.item_image);
            icon_layout.setLayoutParams(icon_item_size);
            //icone de raridade
            icon_rarity = (ImageView) itemView.findViewById(R.id.item_rarity);
            icon_rarity.setLayoutParams(icon_rarity_size);
            //item tipo
            item_tipo = (ImageView) itemView.findViewById(R.id.item_tipo);
            item_tipo.setLayoutParams(item_tipo_size);
            //item name
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            //itemName.setTextSize(screen_width / 60);
            itemName.setLayoutParams(item_nome_size);
            //item nivel
            itemNivel = (TextView) itemView.findViewById(R.id.item_nivel);
            itemNivel.setLayoutParams(item_nivel_size);
            //scroll

            //imagem
            itemImage = (ImageView) itemView.findViewById(R.id.item_image);
            //botão adicionar
            add = (ImageView) itemView.findViewById(R.id.add_buttom);
            add.setLayoutParams(icon_item_size);
            //grid de status
            gridStats = (GridLayout) itemView.findViewById(R.id.grid_stats);
        }
    }

    private void showTradeDialog(final Context context, String preTroca, String posTroca, final Item item1, final Item item2, final Item item3, final String toastResp, final String toastResp2){
        final Dialog d = new Dialog(sscren.getContext());
        d.setTitle(context.getResources().getString(R.string.trocaitem));
        d.setContentView(R.layout.item_trade_dialog);
        TextView txPreTroca = (TextView) d.findViewById(R.id.item_view_pretroca);
        txPreTroca.setText(preTroca);
        TextView txPosTroca = (TextView) d.findViewById(R.id.item_view_postroca);
        txPosTroca.setText(posTroca);
        Button b1 = (Button) d.findViewById(R.id.item_view_trade);
        Button b2 = (Button) d.findViewById(R.id.item_view_cancel);
        ItemComponent lItem1 = (ItemComponent) d.findViewById(R.id.item1);
        lItem1.setItem(item1);
        ItemComponent lItem2 = (ItemComponent) d.findViewById(R.id.item2);
        lItem2.setItem(item2);
        if(item3!=null){
            RelativeLayout rland = (RelativeLayout) d.findViewById(R.id.rl_and);
            LinearLayout.LayoutParams lpand = (LinearLayout.LayoutParams) rland.getLayoutParams();
            lpand.height = RecyclerView.LayoutParams.WRAP_CONTENT;
            rland.setLayoutParams(lpand);

            ItemComponent lItem3 = (ItemComponent) d.findViewById(R.id.item3);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lItem3.getLayoutParams();
            lp.height = RecyclerView.LayoutParams.WRAP_CONTENT;
            lItem3.setLayoutParams(lp);
            lItem3.setItem(item3);

            if(item1.getTipo().equals("anel")) {
                Button b3 = (Button) d.findViewById(R.id.item_view_trade2);
                RelativeLayout.LayoutParams lpButton = (RelativeLayout.LayoutParams) b3.getLayoutParams();
                lpButton.width = RelativeLayout.LayoutParams.WRAP_CONTENT;
                lpButton.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
                b3.setLayoutParams(lpButton);

                b1.setText(context.getResources().getString(R.string.ring1));
                b3.setText(context.getResources().getString(R.string.ring2));
                b3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (item2.getTipo()) {
                            case "elm":
                                MainActivity.build.setElmo(item2);
                                break;
                            case "amu":
                                MainActivity.build.setAmuleto(item2);
                                break;
                            case "w1h":
                                MainActivity.build.setArmamain(item2);
                                break;
                            case "bot":
                                MainActivity.build.setBota(item2);
                                break;
                            case "cap":
                                MainActivity.build.setCapa(item2);
                                break;
                            case "drag":
                                MainActivity.build.setDragona(item2);
                                break;
                            case "pei":
                                MainActivity.build.setPeitoral(item2);
                                break;
                            case "pet":
                                MainActivity.build.setPet(item2);
                                break;
                            case "mont":
                                MainActivity.build.setMontaria(item2);
                                break;
                            case "cint":
                                MainActivity.build.setCinto(item2);
                                break;
                            case "anel":
                                MainActivity.build.setAnel2(item2);
                                break;
                            case "w2h":
                                MainActivity.build.setArmamain(item2);
                                break;
                            case "wsec":
                                MainActivity.build.setArmasec(item2);
                                break;
                        }
                        Toast.makeText(context, toastResp2 + " " + MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        new BD(context).AtualizarBuild(MainActivity.build);
                        d.dismiss();
                    }
                });
            }else{
                TextView txAnd = (TextView) d.findViewById(R.id.tx_and);
                txAnd.setText(context.getResources().getString(R.string.conditiontroca2h));
            }
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (item2.getTipo()) {
                    case "elm":
                        MainActivity.build.setElmo(item2);
                        break;
                    case "amu":
                        MainActivity.build.setAmuleto(item2);
                        break;
                    case "w1h":
                        MainActivity.build.setArmamain(item2);
                        if(item1.getTipo().equals("w2h")){
                            MainActivity.build.setArmasec(new Item());
                        }
                        break;
                    case "bot":
                        MainActivity.build.setBota(item2);
                        break;
                    case "cap":
                        MainActivity.build.setCapa(item2);
                        break;
                    case "drag":
                        MainActivity.build.setDragona(item2);
                        break;
                    case "pei":
                        MainActivity.build.setPeitoral(item2);
                        break;
                    case "pet":
                        MainActivity.build.setPet(item2);
                        break;
                    case "mont":
                        MainActivity.build.setMontaria(item2);
                        break;
                    case "cint":
                        MainActivity.build.setCinto(item2);
                        break;
                    case "anel":
                        MainActivity.build.setAnel1(item2);
                        break;
                    case "w2h":
                        MainActivity.build.setArmamain(item2);
                        MainActivity.build.setArmasec(new Item());
                        break;
                    case "wsec":
                        MainActivity.build.setArmasec(item2);
                        if(MainActivity.build.getArmamain().getTipo().equals("w2h")){
                            MainActivity.build.setArmamain(new Item());
                        }
                        break;
                }
                Toast.makeText(context, toastResp +" " + MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                new BD(context).AtualizarBuild(MainActivity.build);
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();
    }

    private class onClickHandler implements View.OnClickListener{
        Item it;
        public onClickHandler(Item it){
            this.it = it;
        }

        public void onClick(final View v){
            final Context context = v.getContext();
            if(MainActivity.build==null){
                Toast.makeText(context, R.string.buildnotset, Toast.LENGTH_LONG).show();
            }else {
                switch (it.getTipo()) {
                    case "mont":
                        if(MainActivity.build.getMontaria().getCodigo()==0){
                            MainActivity.build.setMontaria(it);
                            Toast.makeText(context, context.getResources().getString(R.string.montadd) + " " + MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        }else{
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getMontaria(),it, null,
                                    context.getResources().getString(R.string.monttrade),null);
                        }
                        break;
                    case "elm":
                        if (MainActivity.build.getElmo().getCodigo() ==0) {
                            MainActivity.build.setElmo(it);
                            Toast.makeText(context, context.getResources().getString(R.string.elmoadd) + " " + MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        }else{
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getElmo(), it, null,
                                    context.getResources().getString(R.string.elmotrade), null);
                        }
                        break;
                    case "amu":
                        if (MainActivity.build.getAmuleto().getCodigo() == 0) {
                            MainActivity.build.setAmuleto(it);
                            Toast.makeText(context, context.getResources().getString(R.string.amuadd) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        }else{
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getAmuleto(), it, null,
                                    context.getResources().getString(R.string.amutrade), null);
                        }
                        break;
                    case "w1h":
                        if (MainActivity.build.getArmamain().getCodigo() == 0) {
                            MainActivity.build.setArmamain(it);
                            Toast.makeText(context, context.getResources().getString(R.string.w1hadd) +" " + MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        }else{
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getArmamain(), it, null,
                                    context.getResources().getString(R.string.w1htrade), null);
                        }
                        break;
                    case "w2h":
                        if(MainActivity.build.getArmamain().getCodigo() == 0 && MainActivity.build.getArmasec().getCodigo() == 0) {
                            MainActivity.build.setArmamain(it);
                            Toast.makeText(context, context.getResources().getString(R.string.w2hadd) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        }else if(MainActivity.build.getArmamain().getTipo().equals("w1h") && MainActivity.build.getArmasec().getCodigo() !=0){
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca2h),context.getResources().getString(R.string.postroca2h),
                                    MainActivity.build.getArmamain(), it, MainActivity.build.getArmasec(),
                                    context.getResources().getString(R.string.w2htrade), null);
                        }else if(MainActivity.build.getArmamain().getCodigo()!=0){
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getArmamain(), it, null,
                                    context.getResources().getString(R.string.w2htrade), null);
                        }else{
                            showTradeDialog(context, context.getResources().getString(R.string.pretroca2h),context.getResources().getString(R.string.postroca2h),
                                    MainActivity.build.getArmasec(),it,null,
                                    context.getResources().getString(R.string.w2htrade),null);
                        }
                        break;

                    case "bot":
                        if (MainActivity.build.getBota().getCodigo() == 0) {
                            MainActivity.build.setBota(it);
                            Toast.makeText(context, context.getResources().getString(R.string.botaadd) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        }else{
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getBota(), it, null,
                                    context.getResources().getString(R.string.botatrade), null);
                        }
                        break;
                    case "cap":
                        if (MainActivity.build.getCapa().getCodigo() == 0) {
                            MainActivity.build.setCapa(it);
                            Toast.makeText(context, context.getResources().getString(R.string.capaadd) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        }else{
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getCapa(), it, null,
                                    context.getResources().getString(R.string.capatrade), null);
                        }
                        break;
                    case "pei":
                        if (MainActivity.build.getPeitoral().getCodigo() == 0) {
                            MainActivity.build.setPeitoral(it);
                            Toast.makeText(context, context.getResources().getString(R.string.peitoraladd) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        }else{
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getPeitoral(), it, null,
                                    context.getResources().getString(R.string.peitoraltrade), null);
                        }
                        break;
                    case "drag":
                        if (MainActivity.build.getDragona().getCodigo() == 0) {
                            MainActivity.build.setDragona(it);
                            Toast.makeText(context, context.getResources().getString(R.string.dragadd) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        }else{
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getDragona(), it, null,
                                    context.getResources().getString(R.string.dragtrade), null);
                        }
                        break;
                    case "cint":
                        if (MainActivity.build.getCinto().getCodigo() == 0) {
                            MainActivity.build.setCinto(it);
                            Toast.makeText(context, context.getResources().getString(R.string.cintadd) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        }else{
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getCinto(), it, null,
                                    context.getResources().getString(R.string.cinttrade), null);
                        }
                        break;
                    case "wsec":
                        if(MainActivity.build.getArmamain().getCodigo()==0 && MainActivity.build.getArmasec().getCodigo() == 0) {
                            MainActivity.build.setArmasec(it);
                            Toast.makeText(context, context.getResources().getString(R.string.armsecadd) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        }else if (MainActivity.build.getArmamain().getTipo().equals("w1h")) {
                            if (MainActivity.build.getArmasec().getCodigo() == 0) {
                                MainActivity.build.setArmasec(it);
                                Toast.makeText(context, context.getResources().getString(R.string.armsecadd) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                            }else{
                                showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                        MainActivity.build.getArmasec(), it, null,
                                        context.getResources().getString(R.string.armsectrade), null);
                            }
                        }else if(MainActivity.build.getArmamain().getTipo().equals("w2h")){
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getArmamain(), it, null,
                                    context.getResources().getString(R.string.armsectrade), null);
                        }
                        break;
                    case "anel":
                        if (MainActivity.build.getAnel1().getCodigo() == 0) {
                            MainActivity.build.setAnel1(it);
                            Toast.makeText(context, context.getResources().getString(R.string.anel1add) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        } else if (MainActivity.build.getAnel2().getCodigo() == 0) {
                            MainActivity.build.setAnel2(it);
                            Toast.makeText(context, context.getResources().getString(R.string.anel2add) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        } else {
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getAnel1(), it, MainActivity.build.getAnel2(),
                                    context.getResources().getString(R.string.anel1trade),context.getResources().getString(R.string.anel2trade));
                        }
                        break;
                    case "pet":
                        if (MainActivity.build.getPet().getCodigo() == 0) {
                            MainActivity.build.setPet(it);
                            Toast.makeText(context, context.getResources().getString(R.string.petadd) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        } else {
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getPet(), it, null,
                                    context.getResources().getString(R.string.pettrade), null);
                        }
                        break;
                    case "ins":
                        if (MainActivity.build.getInsignia().getCodigo() == 0) {
                            MainActivity.build.setInsignia(it);
                            Toast.makeText(context, context.getResources().getString(R.string.insadd) +" "+ MainActivity.build.getNome(), Toast.LENGTH_LONG).show();
                        } else {
                            showTradeDialog(context,context.getResources().getString(R.string.pretroca),context.getResources().getString(R.string.postroca),
                                    MainActivity.build.getInsignia(), it, null,
                                    context.getResources().getString(R.string.instrade), null);
                        }
                        break;
                }
                new BD(context).AtualizarBuild(MainActivity.build);
            }
        }
    }
}
