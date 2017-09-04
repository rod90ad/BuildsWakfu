package com.rodrigoad.rodso.wakfustats.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rodrigoad.rodso.wakfustats.R;
import com.squareup.picasso.Picasso;

/**
 * Created by rodso on 30/03/2017.
 */

public class ItemComponent extends RelativeLayout {

    private ImageView itemImg;
    private ImageView itemRarity;
    private TextView itemName;
    private TextView itemLevel;
    private ImageView itemType;
    private GridLayout gridStats;

    public ItemComponent(Context context) {
        super(context);
    }

    public ItemComponent(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.item_view_layout, this);

        int screen_width = Resources.getSystem().getDisplayMetrics().widthPixels;
        RelativeLayout.LayoutParams icon_item_size = new RelativeLayout.LayoutParams(screen_width / 5, screen_width / 5);
        RelativeLayout.LayoutParams icon_rarity_size = new RelativeLayout.LayoutParams(screen_width / 20, screen_width / 20);
        RelativeLayout.LayoutParams item_nome_size = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, screen_width / 12 - 12);
        item_nome_size.leftMargin = screen_width / 18;
        RelativeLayout.LayoutParams item_tipo_size = new RelativeLayout.LayoutParams(screen_width / 20, screen_width / 20);
        item_tipo_size.topMargin = 6;
        RelativeLayout.LayoutParams item_nivel_size = new RelativeLayout.LayoutParams((screen_width / 100 * 60), screen_width / 12 - 12);

        itemImg = (ImageView) findViewById(R.id.item_view_image);
        itemImg.setLayoutParams(icon_item_size);

        itemRarity = (ImageView) findViewById(R.id.item_view_rarity);
        itemRarity.setLayoutParams(icon_rarity_size);

        itemName = (TextView) findViewById(R.id.item_view_name);
        itemName.setLayoutParams(item_nome_size);

        itemLevel = (TextView) findViewById(R.id.item_view_nivel);
        itemLevel.setLayoutParams(item_nivel_size);

        itemType = (ImageView) findViewById(R.id.item_view_tipo);
        itemType.setLayoutParams(item_tipo_size);

        gridStats = (GridLayout) findViewById(R.id.grid_view_stats);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        // this is the right point to do some things with View objects,
        // as example childs of THIS View object
    }

    public void setItem(Item item){
        Context context = getContext();
        boolean Status = false;
        Boolean cinza = false;

        itemName.setText(item.getNome());
        itemLevel.setText(context.getResources().getString(R.string.nivel) + ": " + item.getNivel());
        switch (item.getRaridade()) {
            case 1:
                itemRarity.setImageResource(R.mipmap.comum);
                break;
            case 2:
                itemRarity.setImageResource(R.mipmap.incomum);
                break;
            case 3:
                itemRarity.setImageResource(R.mipmap.raro);
                break;
            case 4:
                itemRarity.setImageResource(R.mipmap.mitico);
                break;
            case 5:
                itemRarity.setImageResource(R.mipmap.lendario);
                break;
            case 6:
                itemRarity.setImageResource(R.mipmap.reliquia);
                break;
            case 7:
                itemRarity.setImageResource(R.mipmap.epico);
                break;
            case 8:
                itemRarity.setImageResource(R.mipmap.pvp);
                break;
        }
        Picasso.with(getContext()).load(item.getLink()).into(itemImg);
        switch (item.getTipo()) {
            case "elm":
                itemType.setImageResource(R.mipmap.elmo);
                break;
            case "amu":
                itemType.setImageResource(R.mipmap.amu);
                break;
            case "w1h":
                itemType.setImageResource(R.mipmap.mao1);
                break;
            case "bot":
                itemType.setImageResource(R.mipmap.bota);
                break;
            case "cap":
                itemType.setImageResource(R.mipmap.cap);
                break;
            case "drag":
                itemType.setImageResource(R.mipmap.drag);
                break;
            case "pei":
                itemType.setImageResource(R.mipmap.peitoral);
                break;
            case "pet":
                itemType.setImageResource(R.mipmap.pet);
                break;
            case "mont":
                itemType.setImageResource(R.mipmap.mont);
                break;
            case "cint":
                itemType.setImageResource(R.mipmap.cinto);
                break;
            case "anel":
                itemType.setImageResource(R.mipmap.anel);
                break;
            case "w2h":
                itemType.setImageResource(R.mipmap.mao2);
                break;
            case "wsec":
                itemType.setImageResource(R.mipmap.segmao);
                break;
        }
        RelativeLayout.LayoutParams lpText = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpText.addRule(RelativeLayout.ALIGN_LEFT);
        gridStats.removeAllViews();

        //PA
        if (item.getActionpoint() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getActionpoint() > 0) {
                txStats.setText("+" + item.getActionpoint() + " " + context.getResources().getString(R.string.posPA));
            } else {
                txStats.setText(item.getActionpoint() + " " + context.getResources().getString(R.string.posPA));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //PM
        if (item.getMovepoint() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getMovepoint() > 0) {
                txStats.setText("+" + item.getMovepoint() + " " + context.getResources().getString(R.string.posPM));
            } else {
                txStats.setText(item.getMovepoint() + " " + context.getResources().getString(R.string.posPM));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //WP
        if (item.getWakfupoint() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getWakfupoint() > 0) {
                txStats.setText("+" + item.getWakfupoint() + " " + context.getResources().getString(R.string.posWP));
            } else {
                txStats.setText(item.getWakfupoint() + " " + context.getResources().getString(R.string.posWP));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //alcance
        if (item.getRange() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getRange() > 0) {
                txStats.setText("+" + item.getRange() + " " + context.getResources().getString(R.string.posRange));
            } else {
                txStats.setText(item.getRange() + " " + context.getResources().getString(R.string.posRange));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Controle
        if (item.getControl() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getControl() > 0) {
                txStats.setText("+" + item.getControl() + " " + context.getResources().getString(R.string.posCtrl));
            } else {
                txStats.setText(item.getControl() + " " + context.getResources().getString(R.string.posCtrl));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //PV
        if (item.getVitalpoint() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getVitalpoint() > 0) {
                txStats.setText("+" + item.getVitalpoint() + " " + context.getResources().getString(R.string.posPV));
            } else {
                txStats.setText(item.getVitalpoint() + " " + context.getResources().getString(R.string.posPV));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //esquiva
        if (item.getEvasion() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getEvasion() > 0) {
                txStats.setText("+" + item.getEvasion() + " " + context.getResources().getString(R.string.posEva));
            } else {
                txStats.setText(item.getEvasion() + " " + context.getResources().getString(R.string.posEva));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //sabedoria
        if (item.getSabedoria() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getSabedoria() > 0) {
                txStats.setText("+" + item.getSabedoria() + " " + context.getResources().getString(R.string.posSab));
            } else {
                txStats.setText(item.getSabedoria() + " " + context.getResources().getString(R.string.posSab));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Prospecção
        if (item.getProsp() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getProsp() > 0) {
                txStats.setText("+" + item.getProsp() + " " + context.getResources().getString(R.string.posProsp));
            } else {
                txStats.setText(item.getProsp() + " " + context.getResources().getString(R.string.posProsp));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //bloqueio
        if (item.getBlock() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getBlock() > 0) {
                txStats.setText("+" + item.getBlock() + " " + context.getResources().getString(R.string.posblock));
            } else {
                txStats.setText(item.getBlock() + " " + context.getResources().getString(R.string.posblock));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //iniciativa
        if (item.getIniciative() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getIniciative() > 0) {
                txStats.setText("+" + item.getIniciative() + " " + context.getResources().getString(R.string.posIni));
            } else {
                txStats.setText(item.getIniciative() + " " + context.getResources().getString(R.string.posIni));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Golpes criticos
        if (item.getCriticalchance() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getCriticalchance() > 0) {
                txStats.setText("+" + item.getCriticalchance() + context.getResources().getString(R.string.posCChance));
            } else {
                txStats.setText(item.getCriticalchance() + context.getResources().getString(R.string.posCChance));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //parada
        if (item.getStop() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getStop() > 0) {
                txStats.setText("+" + item.getStop() + context.getResources().getString(R.string.posstop));
            } else {
                txStats.setText(item.getStop() + context.getResources().getString(R.string.posstop));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Dano geral
        if (item.getDmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getDmg() > 0) {
                txStats.setText("+" + item.getDmg() + " " + context.getResources().getString(R.string.posDmg));
            } else {
                txStats.setText(item.getDmg() + " " + context.getResources().getString(R.string.posDmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dano 1 elemento
        if (item.getElement1dmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getElement1dmg() > 0) {
                txStats.setText("+" + item.getElement1dmg() + " " + context.getResources().getString(R.string.pos1Dmg));
            } else {
                txStats.setText(item.getElement1dmg() + " " + context.getResources().getString(R.string.pos1Dmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dano bi
        if (item.getElement2dmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getElement2dmg() > 0) {
                txStats.setText("+" + item.getElement2dmg() + " " + context.getResources().getString(R.string.pos2Dmg));
            } else {
                txStats.setText(item.getElement2dmg() + " " + context.getResources().getString(R.string.pos2Dmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dano tri
        if (item.getElement3dmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getElement3dmg() > 0) {
                txStats.setText("+" + item.getElement3dmg() + " " + context.getResources().getString(R.string.pos3Dmg));
            } else {
                txStats.setText(item.getElement3dmg() + " " + context.getResources().getString(R.string.pos3Dmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dano beserk
        if (item.getBeserkdmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getBeserkdmg() > 0) {
                txStats.setText("+" + item.getBeserkdmg() + " " + context.getResources().getString(R.string.posbskdmg));
            } else {
                txStats.setText(item.getBeserkdmg() + " " + context.getResources().getString(R.string.posbskdmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dano corpo a corpo
        if (item.getClosecombatdmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getClosecombatdmg() > 0) {
                txStats.setText("+" + item.getClosecombatdmg() + " " + context.getResources().getString(R.string.poscacdmg));
            } else {
                txStats.setText(item.getClosecombatdmg() + " " + context.getResources().getString(R.string.poscacdmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dano alvo unico
        if (item.getOnetargetdmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getOnetargetdmg() > 0) {
                txStats.setText("+" + item.getOnetargetdmg() + " " + context.getResources().getString(R.string.posonedmg));
            } else {
                txStats.setText(item.getOnetargetdmg() + " " + context.getResources().getString(R.string.posonedmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dano distancia
        if (item.getDistancedmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getDistancedmg() > 0) {
                txStats.setText("+" + item.getDistancedmg() + " " + context.getResources().getString(R.string.posdistdmg));
            } else {
                txStats.setText(item.getDistancedmg() + " " + context.getResources().getString(R.string.posdistdmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dano zona area
        if (item.getAreadmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getAreadmg() > 0) {
                txStats.setText("+" + item.getAreadmg() + " " + context.getResources().getString(R.string.posareadmg));
            } else {
                txStats.setText(item.getAreadmg() + " " + context.getResources().getString(R.string.posareadmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dano nas costas
        if (item.getBackdmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getBackdmg() > 0) {
                txStats.setText("+" + item.getBackdmg() + " " + context.getResources().getString(R.string.posbackdmg));
            } else {
                txStats.setText(item.getBackdmg() + " " + context.getResources().getString(R.string.posbackdmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Dominio Cura
        if (item.getHeal() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getHeal() > 0) {
                txStats.setText("+" + item.getHeal() + " " + context.getResources().getString(R.string.posHeal));
            } else {
                txStats.setText(item.getHeal() + " " + context.getResources().getString(R.string.posHeal));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Dano Critico
        if (item.getCriticaldmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getCriticaldmg() > 0) {
                txStats.setText("+" + item.getCriticaldmg() + " " + context.getResources().getString(R.string.posDC));
            } else {
                txStats.setText(item.getCriticaldmg() + " " + context.getResources().getString(R.string.posDC));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a Agua
        if (item.getReswater() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getReswater() > 0) {
                txStats.setText("+" + item.getReswater() + " " + context.getResources().getString(R.string.posResw));
            } else {
                txStats.setText(item.getReswater() + " " + context.getResources().getString(R.string.posResw));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a Fogo
        if (item.getResfire() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getResfire() > 0) {
                txStats.setText("+" + item.getResfire() + " " + context.getResources().getString(R.string.posResf));
            } else {
                txStats.setText(item.getResfire() + " " + context.getResources().getString(R.string.posResf));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a Ar
        if (item.getResair() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getResair() > 0) {
                txStats.setText("+" + item.getResair() + " " + context.getResources().getString(R.string.posResa));
            } else {
                txStats.setText(item.getResair() + " " + context.getResources().getString(R.string.posResa));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Feitiço fogo
        if (item.getNvffogo() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getNvffogo() > 0) {
                txStats.setText("+" + item.getNvffogo() + " " + context.getResources().getString(R.string.posffogo));
            } else {
                txStats.setText(item.getNvffogo() + " " + context.getResources().getString(R.string.posffogo));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Feitiço agua
        if (item.getNvfagua() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getNvfagua() > 0) {
                txStats.setText("+" + item.getNvfagua() + " " + context.getResources().getString(R.string.posfagua));
            } else {
                txStats.setText(item.getNvfagua() + " " + context.getResources().getString(R.string.posfagua));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Feitiço terra
        if (item.getNvfterra() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getNvfterra() > 0) {
                txStats.setText("+" + item.getNvfterra() + " " + context.getResources().getString(R.string.posfterra));
            } else {
                txStats.setText(item.getNvfterra() + " " + context.getResources().getString(R.string.posfterra));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Feitiço ar
        if (item.getNvfar() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getNvfar() > 0) {
                txStats.setText("+" + item.getNvfar() + " " + context.getResources().getString(R.string.posfar));
            } else {
                txStats.setText(item.getNvfar() + " " + context.getResources().getString(R.string.posfar));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Feitiço geral
        if (item.getNvfgeral() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getNvfgeral() > 0) {
                txStats.setText("+" + item.getNvfgeral() + " " + context.getResources().getString(R.string.posfgeral));
            } else {
                txStats.setText(item.getNvfgeral() + " " + context.getResources().getString(R.string.posfgeral));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dominio fogo
        if (item.getDmgfogo() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getDmgfogo() > 0) {
                txStats.setText("+" + item.getDmgfogo() + " " + context.getResources().getString(R.string.posdmgfogo));
            } else {
                txStats.setText(item.getDmgfogo() + " " + context.getResources().getString(R.string.posdmgfogo));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dominio agua
        if (item.getDmgagua() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getDmgagua() > 0) {
                txStats.setText("+" + item.getDmgagua() + " " + context.getResources().getString(R.string.posdmgagua));
            } else {
                txStats.setText(item.getDmgagua() + " " + context.getResources().getString(R.string.posdmgagua));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dominio terra
        if (item.getDmgterra() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getDmgterra() > 0) {
                txStats.setText("+" + item.getDmgterra() + " " + context.getResources().getString(R.string.posdmgterra));
            } else {
                txStats.setText(item.getDmgterra() + " " + context.getResources().getString(R.string.posdmgterra));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //dominio ar
        if (item.getDmgar() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getDmgar() > 0) {
                txStats.setText("+" + item.getDmgar() + " " + context.getResources().getString(R.string.posdmgar));
            } else {
                txStats.setText(item.getDmgar() + " " + context.getResources().getString(R.string.posdmgar));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a Terra
        if (item.getResearth() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getResearth() > 0) {
                txStats.setText("+" + item.getResearth() + " " + context.getResources().getString(R.string.posRest));
            } else {
                txStats.setText(item.getResearth() + " " + context.getResources().getString(R.string.posRest));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Resistencia
        if (item.getResist() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getResist() > 0) {
                txStats.setText("+" + item.getResist() + " " + context.getResources().getString(R.string.posRes));
            } else {
                txStats.setText(item.getResist() + " " + context.getResources().getString(R.string.posRes));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a 1 elementos
        if (item.getRes1elerandom() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getRes1elerandom() > 0) {
                txStats.setText("+" + item.getRes1elerandom() + " " + context.getResources().getString(R.string.posRes1));
            } else {
                txStats.setText(item.getRes1elerandom() + " " + context.getResources().getString(R.string.posRes1));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a 2 elementos
        if (item.getRes2elerandom() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getRes2elerandom() > 0) {
                txStats.setText("+" + item.getRes2elerandom() + " " + context.getResources().getString(R.string.posRes2));
            } else {
                txStats.setText(item.getRes2elerandom() + " " + context.getResources().getString(R.string.posRes2));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a 3 elementos
        if (item.getRes3elerandom() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getRes3elerandom() > 0) {
                txStats.setText("+" + item.getRes3elerandom() + " " + context.getResources().getString(R.string.posRes3));
            } else {
                txStats.setText(item.getRes3elerandom() + " " + context.getResources().getString(R.string.posRes3));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a critico
        if (item.getRescriticaldmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getRescriticaldmg() > 0) {
                txStats.setText("+" + item.getRescriticaldmg() + " " + context.getResources().getString(R.string.posrescritical));
            } else {
                txStats.setText(item.getRescriticaldmg() + " " + context.getResources().getString(R.string.posrescritical));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Resistencia a dano costas
        if (item.getResblackdmg() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getResblackdmg() > 0) {
                txStats.setText("+" + item.getResblackdmg() + " " + context.getResources().getString(R.string.posresbackdmg));
            } else {
                txStats.setText(item.getResblackdmg() + " " + context.getResources().getString(R.string.posresbackdmg));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Colheita minerador
        if (item.getMinertake() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getMinertake() > 0) {
                txStats.setText("+" + item.getMinertake() + context.getResources().getString(R.string.posminertake));
            } else {
                txStats.setText(item.getMinertake() + context.getResources().getString(R.string.posminertake));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Velocidade de movimento
        if (item.getMovespeed() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getMovespeed() > 0) {
                txStats.setText("+" + item.getMovespeed() + context.getResources().getString(R.string.posmovespeed));
            } else {
                txStats.setText(item.getMovespeed() + context.getResources().getString(R.string.posmovespeed));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        //Arte de Equipar
        if (item.getArteequipar() != 0) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            if (item.getArteequipar() > 0) {
                txStats.setText("+" + item.getArteequipar() + " " + context.getResources().getString(R.string.posartequipar));
            } else {
                txStats.setText(item.getArteequipar() + " " + context.getResources().getString(R.string.posartequipar));
            }
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }

        /*Status de Itens Shushu ou outros itens.
        if (!item.getStatus().equals("")) {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
            RelativeLayout stats = new RelativeLayout(context);
            TextView txStats = new TextView(context);
            txStats.setLayoutParams(lpText);
            txStats.setPadding(6, 6, 6, 6);
            txStats.setText(item.getStatus());
            if (cinza) {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz1));
                cinza = !cinza;
            } else {
                txStats.setBackgroundColor(context.getResources().getColor(R.color.cz2));
                cinza = !cinza;
            }
            stats.addView(txStats);
            gridStats.addView(stats);
            Status = true;
        }*/

        if (Status) {
        } else {
            gridStats.setRowCount(gridStats.getRowCount() + 1);
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
            gridStats.addView(rlNoStats);
        }
    }
}
