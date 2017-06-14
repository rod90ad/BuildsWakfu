package com.buildswakfu.rodrigo.buildswakfu.Layouts.Decks;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.buildswakfu.rodrigo.buildswakfu.Layouts.PointsFragments.MajorFragment;
import com.buildswakfu.rodrigo.buildswakfu.Layouts.ViewBuildFragment;
import com.buildswakfu.rodrigo.buildswakfu.MainActivity;
import com.buildswakfu.rodrigo.buildswakfu.R;
import com.buildswakfu.rodrigo.buildswakfu.Utils.BD;
import com.buildswakfu.rodrigo.buildswakfu.Utils.Build;
import com.buildswakfu.rodrigo.buildswakfu.Utils.Item;
import com.buildswakfu.rodrigo.buildswakfu.Utils.Spell;
import com.buildswakfu.rodrigo.buildswakfu.Utils.SpellComponent;
import com.buildswakfu.rodrigo.buildswakfu.ViewBuildActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeckActivesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeckActivesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeckActivesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView actives_tittle;
    private TextView elemental_spells;
    private TextView active_spells;

    //person skills
    private ImageView skill1;
    private ImageView skill2;
    private ImageView skill3;
    private ImageView skill4;
    private ImageView skill5;
    private ImageView skill6;
    private ImageView skill7;
    private ImageView skill8;
    private ImageView skill9;
    private ImageView skill10;
    private ImageView skill11;
    private ImageView skill12;

    //Elemental spells to select
    private ImageView ele1_1;
    private ImageView ele1_2;
    private ImageView ele1_3;
    private ImageView ele1_4;
    private ImageView ele1_5;

    private ImageView ele2_1;
    private ImageView ele2_2;
    private ImageView ele2_3;
    private ImageView ele2_4;
    private ImageView ele2_5;

    private ImageView ele3_1;
    private ImageView ele3_2;
    private ImageView ele3_3;
    private ImageView ele3_4;
    private ImageView ele3_5;

    // if huppermage
    private LinearLayout hupper_ll;
    private ImageView ele4_1;
    private ImageView ele4_2;
    private ImageView ele4_3;
    private ImageView ele4_4;
    private ImageView ele4_5;

    //actives spells
    private ImageView active1;
    private ImageView active2;
    private ImageView active3;
    private ImageView active4;
    private ImageView active5;
    private ImageView active6;
    private ImageView active7;
    private ImageView active8;

    private View rootView;
    private ArrayList<Spell> spells;

    private static ViewBuildFragment viewBuildFragment;

    private OnFragmentInteractionListener mListener;

    public DeckActivesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeckActivesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeckActivesFragment newInstance(ViewBuildFragment param1, String param2) {
        DeckActivesFragment fragment = new DeckActivesFragment();
        Bundle args = new Bundle();
        viewBuildFragment = param1;
        //args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private ArrayList<Spell> getSkills(int classe){
        boolean active=true;
        switch (classe){
            case 0:
                return new BD(getContext()).getSpells("cra",active);    //cra
            case 1:
                return new BD(getContext()).getSpells("eca",active);    //ecaflip
            case 2:
                return new BD(getContext()).getSpells("elio",active);    //elio
            case 3:
                return new BD(getContext()).getSpells("eni",active);     //eniripsa
            case 4:
                return new BD(getContext()).getSpells("enu",active);     //enutrof
            case 5:
                return new BD(getContext()).getSpells("feca",active);     //feca
            case 6:
                return new BD(getContext()).getSpells("hupp",active);     //huppermage
            case 7:
                return new BD(getContext()).getSpells("iop",active);     //iop
            case 8:
                return new BD(getContext()).getSpells("osa", active);    //osamodas
            case 9:
                return new BD(getContext()).getSpells("panda",active);     //panda
            case 10:
                return new BD(getContext()).getSpells("lad",active);     //ladino
            case 11:
                return new BD(getContext()).getSpells("sac",active);     //sacrier
            case 12:
                return new BD(getContext()).getSpells("sad",active);     //sadida
            case 13:
                return new BD(getContext()).getSpells("sram",active);    //sram
            case 14:
                return new BD(getContext()).getSpells("steam",active);     //steamer
            case 15:
                return new BD(getContext()).getSpells("xelor",active);     //xelor
            case 16:
                return new BD(getContext()).getSpells("zob",active);     //zobal
            default:
                return new BD(getContext()).getSpells("cra", active);    //default cra
        }
    }

    // This defines your touch listener
    private final class MyTouchListener implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

            v.startDrag(data, shadowBuilder, v, 0);
            v.setVisibility(View.VISIBLE);
            return false;
        }
    }

    private class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //v.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //v.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DROP:
                    // Dropped, reassign View to ViewGroup
                    ImageView view = (ImageView) event.getLocalState();
                    ImageView container = (ImageView) v;
                    OnlySpellControl(view.getDrawable());
                    container.setImageDrawable(view.getDrawable());
                    view.setVisibility(View.VISIBLE);
                    SaveSpells();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setVisibility(View.VISIBLE);
                default:
                    break;
            }
            return true;
        }
    }

    private class MyOnClickSpell implements View.OnClickListener {

        Spell spell;

        public MyOnClickSpell(Spell spell){
            this.spell =spell;
        }

        @Override
        public void onClick(View v) {
            final Dialog d = new Dialog(getContext());
            d.setTitle(getResources().getString(R.string.tiraitem));
            d.setContentView(R.layout.spell_description);
            SpellComponent spellComponent = (SpellComponent) d.findViewById(R.id.spell);
            Log.e("SPELL", spell.getClasse()+" "+spell.getImage());
            spellComponent.setSpell(d.getContext(), spell);
            spellComponent.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                }
            });
            d.show();
        }
    }

    private void SaveSpells(){
        for(int i=0;i<spells.size();i++){
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)skill1.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setSpell1(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)skill2.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setSpell2(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)skill3.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setSpell3(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)skill4.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setSpell4(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)skill5.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setSpell5(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)skill6.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setSpell6(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)skill7.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setSpell7(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)skill8.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setSpell8(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)skill9.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setSpell9(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)skill10.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setSpell10(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)skill11.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setSpell11(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)skill12.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setSpell12(i);
            }
        }
        new BD(getContext()).salvaSpells(ViewBuildActivity.build);
    }

    private void OnlySpellControl(Drawable drawable){
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)skill1.getDrawable()).getBitmap())){
            skill1.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setSpell1(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)skill2.getDrawable()).getBitmap())){
            skill2.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setSpell2(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)skill3.getDrawable()).getBitmap())){
            skill3.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setSpell3(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)skill4.getDrawable()).getBitmap())){
            skill4.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setSpell4(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)skill5.getDrawable()).getBitmap())){
            skill5.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setSpell5(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)skill6.getDrawable()).getBitmap())){
            skill6.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setSpell6(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)skill7.getDrawable()).getBitmap())){
            skill7.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setSpell7(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)skill8.getDrawable()).getBitmap())){
            skill8.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setSpell8(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)skill9.getDrawable()).getBitmap())){
            skill9.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setSpell9(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)skill10.getDrawable()).getBitmap())){
            skill10.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setSpell10(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)skill11.getDrawable()).getBitmap())){
            skill11.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setSpell11(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)skill12.getDrawable()).getBitmap())){
            skill12.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setSpell12(0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView==null) {
            rootView = inflater.inflate(R.layout.fragment_deck_actives, container, false);

            actives_tittle = (TextView) rootView.findViewById(R.id.deck_actives_tittle);
            actives_tittle.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/namefont.ttf"));

            active_spells = (TextView) rootView.findViewById(R.id.deck_actives_active_spells);
            active_spells.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/namefont.ttf"));

            elemental_spells = (TextView) rootView.findViewById(R.id.deck_actives_elemental_spells);
            elemental_spells.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/namefont.ttf"));

            spells = getSkills(ViewBuildActivity.build.getClasse());

            skill1 = (ImageView) rootView.findViewById(R.id.active_skill1);
            skill1.setImageDrawable(ViewBuildActivity.build.getSpell1()==0 ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(ViewBuildActivity.build.getSpell1()).getImage()));
            skill1.setOnDragListener(new MyDragListener());
            skill2 = (ImageView) rootView.findViewById(R.id.active_skill2);
            skill2.setImageDrawable(ViewBuildActivity.build.getSpell2()==0 ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(ViewBuildActivity.build.getSpell2()).getImage()));
            skill2.setOnDragListener(new MyDragListener());
            skill3 = (ImageView) rootView.findViewById(R.id.active_skill3);
            skill3.setImageDrawable(ViewBuildActivity.build.getSpell3()==0 ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(ViewBuildActivity.build.getSpell3()).getImage()));
            skill3.setOnDragListener(new MyDragListener());
            skill4 = (ImageView) rootView.findViewById(R.id.active_skill4);
            skill4.setImageDrawable(ViewBuildActivity.build.getSpell4()==0 ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(ViewBuildActivity.build.getSpell4()).getImage()));
            skill4.setOnDragListener(new MyDragListener());
            skill5 = (ImageView) rootView.findViewById(R.id.active_skill5);
            skill5.setImageDrawable(ViewBuildActivity.build.getSpell5()==0 ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(ViewBuildActivity.build.getSpell5()).getImage()));
            skill5.setOnDragListener(new MyDragListener());
            skill6 = (ImageView) rootView.findViewById(R.id.active_skill6);
            skill6.setImageDrawable(ViewBuildActivity.build.getSpell6()==0 ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(ViewBuildActivity.build.getSpell6()).getImage()));
            skill6.setOnDragListener(new MyDragListener());
            if(ViewBuildActivity.build.getNivel()>=10) {
                skill7 = (ImageView) rootView.findViewById(R.id.active_skill7);
                skill7.setImageDrawable(ViewBuildActivity.build.getSpell7()==0 ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(ViewBuildActivity.build.getSpell7()).getImage()));
                skill7.setOnDragListener(new MyDragListener());
                if(ViewBuildActivity.build.getNivel()>=20) {
                    skill8 = (ImageView) rootView.findViewById(R.id.active_skill8);
                    skill8.setImageDrawable(ViewBuildActivity.build.getSpell8()==0 ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(ViewBuildActivity.build.getSpell8()).getImage()));
                    skill8.setOnDragListener(new MyDragListener());
                    if(ViewBuildActivity.build.getNivel()>=30) {
                        skill9 = (ImageView) rootView.findViewById(R.id.active_skill9);
                        skill9.setImageDrawable(ViewBuildActivity.build.getSpell9()==0 ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(ViewBuildActivity.build.getSpell9()).getImage()));
                        skill9.setOnDragListener(new MyDragListener());
                        if(ViewBuildActivity.build.getNivel()>=40) {
                            skill10 = (ImageView) rootView.findViewById(R.id.active_skill10);
                            skill10.setImageDrawable(ViewBuildActivity.build.getSpell10()==0 ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(ViewBuildActivity.build.getSpell10()).getImage()));
                            skill10.setOnDragListener(new MyDragListener());
                            if(ViewBuildActivity.build.getNivel()>=60) {
                                skill11 = (ImageView) rootView.findViewById(R.id.active_skill11);
                                skill11.setImageDrawable(ViewBuildActivity.build.getSpell11()==0 ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(ViewBuildActivity.build.getSpell11()).getImage()));
                                skill11.setOnDragListener(new MyDragListener());
                                if(ViewBuildActivity.build.getNivel()>=80) {
                                    skill12 = (ImageView) rootView.findViewById(R.id.active_skill12);
                                    skill12.setImageDrawable(ViewBuildActivity.build.getSpell12()==0 ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(ViewBuildActivity.build.getSpell12()).getImage()));
                                    skill12.setOnDragListener(new MyDragListener());
                                }else{
                                    skill12 = (ImageView) rootView.findViewById(R.id.active_skill12);
                                    skill12.setImageResource(R.drawable.spell_block80);
                                }
                            }else{
                                skill11 = (ImageView) rootView.findViewById(R.id.active_skill11);
                                skill11.setImageResource(R.drawable.spell_block60);
                                skill12 = (ImageView) rootView.findViewById(R.id.active_skill12);
                                skill12.setImageResource(R.drawable.spell_block80);
                            }
                        }else{
                            skill10 = (ImageView) rootView.findViewById(R.id.active_skill10);
                            skill10.setImageResource(R.drawable.spell_block40);
                            skill11 = (ImageView) rootView.findViewById(R.id.active_skill11);
                            skill11.setImageResource(R.drawable.spell_block60);
                            skill12 = (ImageView) rootView.findViewById(R.id.active_skill12);
                            skill12.setImageResource(R.drawable.spell_block80);
                        }
                    }else{
                        skill9 = (ImageView) rootView.findViewById(R.id.active_skill9);
                        skill9.setImageResource(R.drawable.spell_block30);
                        skill10 = (ImageView) rootView.findViewById(R.id.active_skill10);
                        skill10.setImageResource(R.drawable.spell_block40);
                        skill11 = (ImageView) rootView.findViewById(R.id.active_skill11);
                        skill11.setImageResource(R.drawable.spell_block60);
                        skill12 = (ImageView) rootView.findViewById(R.id.active_skill12);
                        skill12.setImageResource(R.drawable.spell_block80);
                    }
                }else{
                    skill8 = (ImageView) rootView.findViewById(R.id.active_skill8);
                    skill8.setImageResource(R.drawable.spell_block20);
                    skill9 = (ImageView) rootView.findViewById(R.id.active_skill9);
                    skill9.setImageResource(R.drawable.spell_block30);
                    skill10 = (ImageView) rootView.findViewById(R.id.active_skill10);
                    skill10.setImageResource(R.drawable.spell_block40);
                    skill11 = (ImageView) rootView.findViewById(R.id.active_skill11);
                    skill11.setImageResource(R.drawable.spell_block60);
                    skill12 = (ImageView) rootView.findViewById(R.id.active_skill12);
                    skill12.setImageResource(R.drawable.spell_block80);
                }
            }else{
                skill7 = (ImageView) rootView.findViewById(R.id.active_skill7);
                skill7.setImageResource(R.drawable.spell_block10);
                skill8 = (ImageView) rootView.findViewById(R.id.active_skill8);
                skill8.setImageResource(R.drawable.spell_block20);
                skill9 = (ImageView) rootView.findViewById(R.id.active_skill9);
                skill9.setImageResource(R.drawable.spell_block30);
                skill10 = (ImageView) rootView.findViewById(R.id.active_skill10);
                skill10.setImageResource(R.drawable.spell_block40);
                skill11 = (ImageView) rootView.findViewById(R.id.active_skill11);
                skill11.setImageResource(R.drawable.spell_block60);
                skill12 = (ImageView) rootView.findViewById(R.id.active_skill12);
                skill12.setImageResource(R.drawable.spell_block80);
            }

            ele1_1 = (ImageView) rootView.findViewById(R.id.active_ele1_1);
            ele1_1.setImageDrawable(GetImage(getContext(),spells.get(1).getImage()));
            ele1_1.setOnClickListener(new MyOnClickSpell(spells.get(1)));
            ele1_1.setOnLongClickListener(new MyTouchListener());
            ele1_2 = (ImageView) rootView.findViewById(R.id.active_ele1_2);
            ele1_2.setImageDrawable(GetImage(getContext(),spells.get(2).getImage()));
            ele1_2.setOnClickListener(new MyOnClickSpell(spells.get(2)));
            ele1_2.setOnLongClickListener(new MyTouchListener());
            ele1_3 = (ImageView) rootView.findViewById(R.id.active_ele1_3);
            ele1_3.setImageDrawable(GetImage(getContext(),spells.get(3).getImage()));
            ele1_3.setOnClickListener(new MyOnClickSpell(spells.get(3)));
            ele1_3.setOnLongClickListener(new MyTouchListener());
            ele1_4 = (ImageView) rootView.findViewById(R.id.active_ele1_4);
            ele1_4.setImageDrawable(GetImage(getContext(),spells.get(4).getImage()));
            ele1_4.setOnClickListener(new MyOnClickSpell(spells.get(4)));
            ele1_4.setOnLongClickListener(new MyTouchListener());
            ele1_5 = (ImageView) rootView.findViewById(R.id.active_ele1_5);
            ele1_5.setImageDrawable(GetImage(getContext(),spells.get(5).getImage()));
            ele1_5.setOnClickListener(new MyOnClickSpell(spells.get(5)));
            ele1_5.setOnLongClickListener(new MyTouchListener());

            ele2_1 = (ImageView) rootView.findViewById(R.id.active_ele2_1);
            ele2_1.setImageDrawable(GetImage(getContext(),spells.get(6).getImage()));
            ele2_1.setOnClickListener(new MyOnClickSpell(spells.get(6)));
            ele2_1.setOnLongClickListener(new MyTouchListener());
            ele2_2 = (ImageView) rootView.findViewById(R.id.active_ele2_2);
            ele2_2.setImageDrawable(GetImage(getContext(),spells.get(7).getImage()));
            ele2_2.setOnClickListener(new MyOnClickSpell(spells.get(7)));
            ele2_2.setOnLongClickListener(new MyTouchListener());
            ele2_3 = (ImageView) rootView.findViewById(R.id.active_ele2_3);
            ele2_3.setImageDrawable(GetImage(getContext(),spells.get(8).getImage()));
            ele2_3.setOnClickListener(new MyOnClickSpell(spells.get(8)));
            ele2_3.setOnLongClickListener(new MyTouchListener());
            ele2_4 = (ImageView) rootView.findViewById(R.id.active_ele2_4);
            ele2_4.setImageDrawable(GetImage(getContext(),spells.get(9).getImage()));
            ele2_4.setOnClickListener(new MyOnClickSpell(spells.get(9)));
            ele2_4.setOnLongClickListener(new MyTouchListener());
            ele2_5 = (ImageView) rootView.findViewById(R.id.active_ele2_5);
            ele2_5.setImageDrawable(GetImage(getContext(),spells.get(10).getImage()));
            ele2_5.setOnClickListener(new MyOnClickSpell(spells.get(10)));
            ele2_5.setOnLongClickListener(new MyTouchListener());

            ele3_1 = (ImageView) rootView.findViewById(R.id.active_ele3_1);
            ele3_1.setImageDrawable(GetImage(getContext(),spells.get(11).getImage()));
            ele3_1.setOnClickListener(new MyOnClickSpell(spells.get(11)));
            ele3_1.setOnLongClickListener(new MyTouchListener());
            ele3_2 = (ImageView) rootView.findViewById(R.id.active_ele3_2);
            ele3_2.setImageDrawable(GetImage(getContext(),spells.get(12).getImage()));
            ele3_2.setOnClickListener(new MyOnClickSpell(spells.get(12)));
            ele3_2.setOnLongClickListener(new MyTouchListener());
            ele3_3 = (ImageView) rootView.findViewById(R.id.active_ele3_3);
            ele3_3.setImageDrawable(GetImage(getContext(),spells.get(13).getImage()));
            ele3_3.setOnClickListener(new MyOnClickSpell(spells.get(13)));
            ele3_3.setOnLongClickListener(new MyTouchListener());
            ele3_4 = (ImageView) rootView.findViewById(R.id.active_ele3_4);
            ele3_4.setImageDrawable(GetImage(getContext(),spells.get(14).getImage()));
            ele3_4.setOnClickListener(new MyOnClickSpell(spells.get(14)));
            ele3_4.setOnLongClickListener(new MyTouchListener());
            ele3_5 = (ImageView) rootView.findViewById(R.id.active_ele3_5);
            ele3_5.setImageDrawable(GetImage(getContext(),spells.get(15).getImage()));
            ele3_5.setOnClickListener(new MyOnClickSpell(spells.get(15)));
            ele3_5.setOnLongClickListener(new MyTouchListener());

            if(ViewBuildActivity.build.getClasse()==6){
                //desativa o layout de skills do huppermago
                hupper_ll = (LinearLayout) rootView.findViewById(R.id.active_huppermage);
                LinearLayout.LayoutParams llp = (LinearLayout.LayoutParams) hupper_ll.getLayoutParams();
                llp.weight = (float) 0;
                llp.width = 0;
                llp.height = 0;
                hupper_ll.setLayoutParams(llp);

                //adiciona as skills do huppermago
                ele4_1 = (ImageView) rootView.findViewById(R.id.active_ele4_1);
                ele4_1.setImageDrawable(GetImage(getContext(),spells.get(23).getImage()));
                ele4_1.setOnClickListener(new MyOnClickSpell(spells.get(23)));
                ele4_1.setOnLongClickListener(new MyTouchListener());
                ele4_2 = (ImageView) rootView.findViewById(R.id.active_ele4_2);
                ele4_2.setImageDrawable(GetImage(getContext(),spells.get(24).getImage()));
                ele4_2.setOnClickListener(new MyOnClickSpell(spells.get(24)));
                ele4_2.setOnLongClickListener(new MyTouchListener());
                ele4_3 = (ImageView) rootView.findViewById(R.id.active_ele4_3);
                ele4_3.setImageDrawable(GetImage(getContext(),spells.get(25).getImage()));
                ele4_3.setOnClickListener(new MyOnClickSpell(spells.get(25)));
                ele4_3.setOnLongClickListener(new MyTouchListener());
                ele4_4 = (ImageView) rootView.findViewById(R.id.active_ele4_4);
                ele4_4.setImageDrawable(GetImage(getContext(),spells.get(26).getImage()));
                ele4_4.setOnClickListener(new MyOnClickSpell(spells.get(26)));
                ele4_4.setOnLongClickListener(new MyTouchListener());
                ele4_5 = (ImageView) rootView.findViewById(R.id.active_ele4_5);
                ele4_5.setImageDrawable(GetImage(getContext(),spells.get(27).getImage()));
                ele4_5.setOnClickListener(new MyOnClickSpell(spells.get(27)));
                ele4_5.setOnLongClickListener(new MyTouchListener());
            }

            active1 = (ImageView) rootView.findViewById(R.id.active_active1);
            active1.setImageDrawable(GetImage(getContext(),spells.get(16).getImage()));
            active1.setOnClickListener(new MyOnClickSpell(spells.get(16)));
            active1.setOnLongClickListener(new MyTouchListener());
            active2 = (ImageView) rootView.findViewById(R.id.active_active2);
            active2.setImageDrawable(GetImage(getContext(),spells.get(17).getImage()));
            active2.setOnClickListener(new MyOnClickSpell(spells.get(17)));
            active2.setOnLongClickListener(new MyTouchListener());
            active3 = (ImageView) rootView.findViewById(R.id.active_active3);
            active3.setImageDrawable(GetImage(getContext(),spells.get(18).getImage()));
            active3.setOnClickListener(new MyOnClickSpell(spells.get(18)));
            active3.setOnLongClickListener(new MyTouchListener());
            active4 = (ImageView) rootView.findViewById(R.id.active_active4);
            active4.setImageDrawable(GetImage(getContext(),spells.get(19).getImage()));
            active4.setOnClickListener(new MyOnClickSpell(spells.get(19)));
            active4.setOnLongClickListener(new MyTouchListener());
            active5 = (ImageView) rootView.findViewById(R.id.active_active5);
            active5.setImageDrawable(GetImage(getContext(),spells.get(20).getImage()));
            active5.setOnClickListener(new MyOnClickSpell(spells.get(20)));
            active5.setOnLongClickListener(new MyTouchListener());
            active6 = (ImageView) rootView.findViewById(R.id.active_active6);
            active6.setImageDrawable(GetImage(getContext(),spells.get(21).getImage())== null ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(21).getImage()));
            active6.setOnClickListener(new MyOnClickSpell(spells.get(21)));
            active6.setOnLongClickListener(new MyTouchListener());
            active7 = (ImageView) rootView.findViewById(R.id.active_active7);
            active7.setImageDrawable(GetImage(getContext(),spells.get(22).getImage())== null ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(22).getImage()));
            active7.setOnClickListener(new MyOnClickSpell(spells.get(22)));
            active7.setOnLongClickListener(new MyTouchListener());
            //caso seja                         feca                                       iop                                          steamer
            if(ViewBuildActivity.build.getClasse()==5 || ViewBuildActivity.build.getClasse()==7 || ViewBuildActivity.build.getClasse()==14 ||
                    //                                  osa                                     ladino                                      sacrier
                    ViewBuildActivity.build.getClasse()==8 || ViewBuildActivity.build.getClasse()==10 || ViewBuildActivity.build.getClasse()==11 ||
                    //                             sadida                                       Sram                                        Xelor
                   ViewBuildActivity.build.getClasse()==12 || ViewBuildActivity.build.getClasse()==13 || ViewBuildActivity.build.getClasse()==15) {
                active8 = (ImageView) rootView.findViewById(R.id.active_active8);
                active8.setImageDrawable(GetImage(getContext(),spells.get(23).getImage())== null ? getResources().getDrawable(R.drawable.spell_empty) : GetImage(getContext(),spells.get(23).getImage()));
                active8.setOnClickListener(new MyOnClickSpell(spells.get(23)));
                active8.setOnLongClickListener(new MyTouchListener());
            }
        }
        return rootView;
    }

    private Drawable GetImage(Context c, String ImageName) {
        return c.getResources().getDrawable(c.getResources().getIdentifier(ImageName, "drawable", c.getPackageName()));
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
