package com.rodrigoad.rodso.wakfustats.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rodrigoad.rodso.wakfustats.R;

import java.util.ArrayList;

/**
 * Created by rodso on 11/05/2017.
 */

public class SpellComponent extends LinearLayout {

    private ImageView image;
    public ImageView cancel;
    private TextView name;
    private TextView description;
    private LinearLayout caracteristicas;
    private GridLayout lines;
    private TextView level;
    private LinearLayout.LayoutParams lpline;
    private LinearLayout.LayoutParams lpscrollline;
    private LinearLayout.LayoutParams lplineText;
    private LinearLayout.LayoutParams lplineImage;
    private LinearLayout llEffects;
    private LinearLayout llConditions;
    private LinearLayout llClickable;
    private TextView effects;

    public SpellComponent (Context context){
        super(context);
    }

    public SpellComponent(Context context, AttributeSet attributeSet){
        super(context,attributeSet);

        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.spell_component, this);

        image = (ImageView) findViewById(R.id.spell_image);

        name = (TextView) findViewById(R.id.spell_name);

        level = (TextView) findViewById(R.id.spell_level);

        caracteristicas = (LinearLayout) findViewById(R.id.spell_caracteristicas);

        description = (TextView) findViewById(R.id.spell_description);

        lines = (GridLayout) findViewById(R.id.spell_grid_lines);
        lpline = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpline.gravity= Gravity.CENTER_VERTICAL;

        lpscrollline = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpscrollline.gravity= Gravity.CENTER_VERTICAL;

        lplineText = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lplineText.gravity=Gravity.CENTER_VERTICAL;

        lplineImage = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lplineImage.gravity=Gravity.CENTER_VERTICAL;

        llEffects = (LinearLayout) findViewById(R.id.ll_effects);
        llConditions = (LinearLayout) findViewById(R.id.ll_conditions);
        llClickable = (LinearLayout) findViewById(R.id.ll_clickable);

        effects = (TextView) findViewById(R.id.spell_effects);

        cancel = (ImageView) findViewById(R.id.spell_cancel);
    }

    @Override
    public void onFinishInflate() {
        super.onFinishInflate();
        // this is the right point to do some things with View objects,
        // as example childs of THIS View object
    }

    public void setSpell(final Context context, final Spell spell){
        name.setText(spell.getName());
        level.setText(getResources().getString(R.string.nivel)+": "+spell.getLevel());
        image.setImageDrawable(GetImage(context, spell.getImage()));
        description.setText(spell.getDescription());

        if(spell.isActive()) {
            SetCaracteristicas(caracteristicas, spell);
        }else{
            effects.setText(getResources().getString(R.string.passive));
            llClickable.removeView(llConditions);
        }

        ArrayList<String> slines = spell.getLines();
        for(int i=0;i<slines.size();i++){
            lines.setRowCount(lines.getRowCount()+1);
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(HORIZONTAL);
            linearLayout.setLayoutParams(lpline);
            HorizontalScrollView scrollView = new HorizontalScrollView(context);
            scrollView.setLayoutParams(lpscrollline);
            scrollView.setFillViewport(true);
            scrollView.setScrollbarFadingEnabled(true);
            scrollView.addView(linearLayout);
            if(i%2==0){
                linearLayout.setBackgroundColor(getResources().getColor(R.color.stats1));
            }else{
                linearLayout.setBackgroundColor(getResources().getColor(R.color.stats2));
            }
            lines.addView(scrollView);
            setComponentesOfLine(context, linearLayout, slines.get(i), spell);
        }

        llEffects.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                llEffects.setBackground(getResources().getDrawable(R.drawable.border_shadow_active));
                llConditions.setBackground(getResources().getDrawable(R.drawable.border_shadow_deactive));
                lines.removeAllViews();
                ArrayList<String> slines = spell.getLines();
                for(int i=0;i<slines.size();i++){
                    lines.setRowCount(lines.getRowCount()+1);
                    LinearLayout linearLayout = new LinearLayout(context);
                    linearLayout.setOrientation(HORIZONTAL);
                    linearLayout.setLayoutParams(lpline);
                    HorizontalScrollView scrollView = new HorizontalScrollView(context);
                    scrollView.setLayoutParams(lpscrollline);
                    scrollView.setFillViewport(true);
                    scrollView.setScrollbarFadingEnabled(true);
                    scrollView.addView(linearLayout);
                    if(i%2==0){
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.stats1));
                    }else{
                        linearLayout.setBackgroundColor(getResources().getColor(R.color.stats2));
                    }
                    lines.addView(scrollView);
                    setComponentesOfLine(context, linearLayout, slines.get(i), spell);
                }
            }
        });

        if(!spell.getConditions().get(0).equals("")) {
            llConditions.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    llEffects.setBackground(getResources().getDrawable(R.drawable.border_shadow_deactive));
                    llConditions.setBackground(getResources().getDrawable(R.drawable.border_shadow_active));
                    lines.removeAllViews();
                    ArrayList<String> slines = spell.getConditions();
                    for (int i = 0; i < slines.size(); i++) {
                        lines.setRowCount(lines.getRowCount() + 1);
                        LinearLayout linearLayout = new LinearLayout(context);
                        linearLayout.setOrientation(HORIZONTAL);
                        linearLayout.setLayoutParams(lpline);
                        HorizontalScrollView scrollView = new HorizontalScrollView(context);
                        scrollView.setLayoutParams(lpscrollline);
                        scrollView.setFillViewport(true);
                        scrollView.setScrollbarFadingEnabled(true);
                        scrollView.addView(linearLayout);
                        if (i % 2 == 0) {
                            linearLayout.setBackgroundColor(getResources().getColor(R.color.stats1));
                        } else {
                            linearLayout.setBackgroundColor(getResources().getColor(R.color.stats2));
                        }
                        lines.addView(scrollView);
                        setComponentesOfLine(context, linearLayout, slines.get(i), spell);
                    }
                }
            });
        }else{
            llClickable.removeView(llConditions);
        }
    }

    private void SetCaracteristicas(LinearLayout linearLayout, Spell spell){
        if(spell.getPa_used()>0){
            //texto
            TextView pa = new TextView(getContext());
            pa.setLines(1);
            pa.setText(""+spell.getPa_used());
            pa.setLayoutParams(lplineText);
            pa.setTextColor(getResources().getColor(R.color.blank));
            linearLayout.addView(pa);
            //icone
            ImageView pa_icon = new ImageView(getContext());
            pa_icon.setImageDrawable(getResources().getDrawable(R.drawable.action_point));
            pa_icon.setLayoutParams(lplineImage);
            linearLayout.addView(pa_icon);
        }
        if(spell.getPm_used()>0){
            //texto
            TextView pa = new TextView(getContext());
            pa.setLines(1);
            pa.setText(""+spell.getPm_used());
            pa.setLayoutParams(lplineText);
            pa.setTextColor(getResources().getColor(R.color.blank));
            linearLayout.addView(pa);
            //icone
            ImageView pa_icon = new ImageView(getContext());
            pa_icon.setImageDrawable(getResources().getDrawable(R.drawable.movement_point));
            pa_icon.setLayoutParams(lplineImage);
            linearLayout.addView(pa_icon);
        }
        if(spell.getWakfu_used()>0){
            //texto
            TextView pa = new TextView(getContext());
            pa.setLines(1);
            pa.setText(""+spell.getWakfu_used());
            pa.setLayoutParams(lplineText);
            pa.setTextColor(getResources().getColor(R.color.blank));
            linearLayout.addView(pa);
            //icone
            ImageView pa_icon = new ImageView(getContext());
            pa_icon.setImageDrawable(getResources().getDrawable(R.drawable.wakfu_icon));
            pa_icon.setLayoutParams(lplineImage);
            linearLayout.addView(pa_icon);
        }
        if(spell.getRange_end()!=-1){
            //range ini and end
            TextView range = new TextView(getContext());
            range.setLines(1);
            if(spell.getRange_end()==0){
                range.setText(""+spell.getRange_ini());
            }else {
                range.setText(spell.getRange_ini() + "-" + spell.getRange_end());
            }
            range.setLayoutParams(lplineText);
            range.setTextColor(getResources().getColor(R.color.blank));
            linearLayout.addView(range);
            if(spell.isLinhadevisao()) {
                //range icon
                ImageView range_icon = new ImageView(getContext());
                range_icon.setImageDrawable(getResources().getDrawable(R.drawable.range_icon));
                range_icon.setLayoutParams(lplineImage);
                linearLayout.addView(range_icon);
            }else{
                //icone
                ImageView icon = new ImageView(getContext());
                icon.setImageDrawable(getResources().getDrawable(R.drawable.linhadevisao_icon));
                icon.setLayoutParams(lplineImage);
                linearLayout.addView(icon);
            }
        }
        if(spell.isRange_mod()){
            //icone
            ImageView icon = new ImageView(getContext());
            icon.setImageDrawable(getResources().getDrawable(R.drawable.range_mod_icon));
            icon.setLayoutParams(lplineImage);
            linearLayout.addView(icon);
        }
        if(spell.isLinear()){
            //linear icon
            ImageView linear_icon = new ImageView(getContext());
            linear_icon.setImageDrawable(getResources().getDrawable(R.drawable.linear_icon));
            linear_icon.setLayoutParams(lplineImage);
            linearLayout.addView(linear_icon);
        }
        if(spell.isDiagonal()){
            //linear icon
            ImageView linear_icon = new ImageView(getContext());
            linear_icon.setImageDrawable(getResources().getDrawable(R.drawable.diagonal_icon));
            linear_icon.setLayoutParams(lplineImage);
            linearLayout.addView(linear_icon);
        }
        if(spell.isArea()){
            //area icon
            ImageView area_icon = new ImageView(getContext());
            area_icon.setImageDrawable(getResources().getDrawable(R.drawable.area_icon));
            area_icon.setLayoutParams(lplineImage);
            linearLayout.addView(area_icon);
        }else{
            //single icon
            ImageView single_icon = new ImageView(getContext());
            single_icon.setImageDrawable(getResources().getDrawable(R.drawable.singletarget_icon));
            single_icon.setLayoutParams(lplineImage);
            linearLayout.addView(single_icon);
        }
    }

    private void setComponentesOfLine(Context context, LinearLayout grid, String line, Spell spell){
        boolean first = true;
        for(int g=0;g<line.length();) {
            int index = line.toString().indexOf('@');
            int aux;
            if (index == 0) {
                if(line.substring(index+1,4).equals("dmg")) {
                    aux = line.indexOf(' ');
                    TextView dmg = new TextView(getContext());
                    dmg.setLines(1);
                    dmg.setLayoutParams(lplineText);
                    if (first){
                        dmg.setPadding(10, 0, 0, 0);
                        first=false;
                    }
                    dmg.setTextColor(getResources().getColor(R.color.blank));
                    int dano = (spell.getBasedmg() + (int) (spell.getLevel() * spell.getScale()));
                    dmg.setText(""+dano);
                    grid.addView(dmg);
                    line = line.substring(aux);
                }else {
                    String drawableId = line.substring(index);
                    aux = drawableId.indexOf(' ');
                    if (aux == -1) {
                        drawableId = drawableId.substring(index + 1);
                        line = "";
                    } else {
                        drawableId = drawableId.substring(index + 1, aux);
                        line = line.substring(aux);
                    }
                    ImageView icon = new ImageView(context);
                    if(drawableId.equals("arrow_down_right_icon")){
                        icon.setPadding(40,0,0,0);
                    }else if(first){
                        icon.setPadding(10,0,0,0);
                        first=false;
                    }
                    icon.setImageDrawable(GetImage(context, drawableId));
                    icon.setLayoutParams(lplineImage);
                    grid.addView(icon);
                }
            } else if(index>0){
                aux = line.indexOf('@');
                TextView textView = new TextView(context);
                textView.setLines(1);
                if(aux==-1){
                    textView.setText(line.substring(index));
                    line="";
                }else {
                    textView.setText(line.substring(0, aux));
                    line= line.substring(aux);
                }
                if(first){
                    textView.setPadding(10,0,0,0);
                    first=false;
                }
                textView.setLayoutParams(lplineText);
                textView.setTextColor(getResources().getColor(R.color.blank));
                grid.addView(textView);
            }else if(index==-1){
                TextView textView = new TextView(context);
                textView.setLines(1);
                textView.setText(line);
                line= "";
                if(first){
                    textView.setPadding(10,0,0,0);
                    first=false;
                }
                textView.setLayoutParams(lplineText);
                textView.setTextColor(getResources().getColor(R.color.blank));
                grid.addView(textView);
            }
        }
    }

    private Drawable GetImage(Context context, String imageName) {
        Resources resources = context.getResources();
        Log.e("IMAGE NAME", imageName);
        final int resourceId = resources.getIdentifier(imageName, "drawable",
                context.getPackageName());
        return resources.getDrawable(resourceId);
    }
}
