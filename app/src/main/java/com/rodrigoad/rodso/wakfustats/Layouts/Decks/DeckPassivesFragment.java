package com.rodrigoad.rodso.wakfustats.Layouts.Decks;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rodrigoad.rodso.wakfustats.Layouts.ViewBuildFragment;
import com.rodrigoad.rodso.wakfustats.R;
import com.rodrigoad.rodso.wakfustats.Utils.BD;
import com.rodrigoad.rodso.wakfustats.Utils.Spell;
import com.rodrigoad.rodso.wakfustats.Utils.SpellComponent;
import com.rodrigoad.rodso.wakfustats.ViewBuildActivity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeckPassivesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeckPassivesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeckPassivesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //tittles
    private TextView passives_tittle;
    private TextView passive_spells;

    //passives person
    private ImageView passive_skill1;
    private ImageView passive_skill2;
    private ImageView passive_skill3;
    private ImageView passive_skill4;
    private ImageView passive_skill5;
    private ImageView passive_skill6;

    //passive spells
    private ImageView passive1;
    private ImageView passive2;
    private ImageView passive3;
    private ImageView passive4;
    private ImageView passive5;
    private ImageView passive6;
    private ImageView passive7;
    private ImageView passive8;
    private ImageView passive9;
    private ImageView passive10;
    private ImageView passive11;
    private ImageView passive12;
    private ImageView passive13;
    private ImageView passive14;
    private ImageView passive15;
    private ImageView passive16;
    private ImageView passive17;
    private ImageView passive18;

    private View rootView;
    private ArrayList<Spell> spells;

    private static ViewBuildFragment viewBuildFragment;

    private OnFragmentInteractionListener mListener;

    public DeckPassivesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeckPassivesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeckPassivesFragment newInstance(ViewBuildFragment param1, String param2) {
        DeckPassivesFragment fragment = new DeckPassivesFragment();
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

    // This defines your touch listener
    private final class MyTouchListener implements View.OnLongClickListener {

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.VISIBLE);
                return true;
            }else{
                return false;
            }
        }

        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("", "");
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);

            v.startDrag(data, shadowBuilder, v, 0);
            v.setVisibility(View.VISIBLE);
            return true;
        }
    }

    private final class MyLongClickListener implements View.OnLongClickListener{


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

    private Drawable GetImage(Context c, String ImageName) {
        return c.getResources().getDrawable(c.getResources().getIdentifier(ImageName, "drawable", c.getPackageName()));
    }

    private void SaveSpells(){
        for(int i=0;i<spells.size();i++){
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)passive_skill1.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setPSpell1(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)passive_skill2.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setPSpell2(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)passive_skill3.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setPSpell3(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)passive_skill4.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setPSpell4(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)passive_skill5.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setPSpell5(i);
            }
            if(((BitmapDrawable)GetImage(getContext(),spells.get(i).getImage())).getBitmap().equals(((BitmapDrawable)passive_skill6.getDrawable()).getBitmap())){
                ViewBuildActivity.build.setPSpell6(i);
            }
        }
        new BD(getContext()).salvaSpells(ViewBuildActivity.build);
    }

    private void OnlySpellControl(Drawable drawable){
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)passive_skill1.getDrawable()).getBitmap())){
            passive_skill1.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setPSpell1(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)passive_skill2.getDrawable()).getBitmap())){
            passive_skill2.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setPSpell2(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)passive_skill3.getDrawable()).getBitmap())){
            passive_skill3.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setPSpell3(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)passive_skill4.getDrawable()).getBitmap())){
            passive_skill4.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setPSpell4(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)passive_skill5.getDrawable()).getBitmap())){
            passive_skill5.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setPSpell5(0);
        }
        if(((BitmapDrawable)drawable).getBitmap().equals(((BitmapDrawable)passive_skill6.getDrawable()).getBitmap())){
            passive_skill6.setImageResource(R.drawable.spell_empty);
            ViewBuildActivity.build.setPSpell6(0);
        }
    }

    private ArrayList<Spell> getSkills(int classe){
        boolean active=false;
        switch (classe){
            case 0:
                return new BD(getContext()).getSpells("cra",active); //cra
            case 1:
                return new BD(getContext()).getSpells("eca",active); //ecaflip
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

    private class showSpell implements View.OnClickListener{

        private int numero;
        private Spell spell = new Spell();

        public showSpell(int numero){
            this.numero=numero;
        }


        @Override
        public void onClick(View v) {
            boolean show=false;
            final Dialog d = new Dialog(getContext());
            d.setTitle(getResources().getString(R.string.mostraitem));
            d.setContentView(R.layout.showspell);
            SpellComponent spellc = (SpellComponent) d.findViewById(R.id.showspell_spell);
            switch (numero) {
                case 1:
                    if(ViewBuildActivity.build.getSpell1()!=0) {
                        spell = spells.get(ViewBuildActivity.build.getPSpell1());
                        show=true;
                    }
                    break;
                case 2:
                    if(ViewBuildActivity.build.getSpell2()!=0) {
                        spell = spells.get(ViewBuildActivity.build.getPSpell2());
                        show=true;
                    }
                    break;
                case 3:
                    if(ViewBuildActivity.build.getSpell3()!=0) {
                        spell = spells.get(ViewBuildActivity.build.getPSpell3());
                        show=true;
                    }
                    break;
                case 4:
                    if(ViewBuildActivity.build.getSpell4()!=0) {
                        spell = spells.get(ViewBuildActivity.build.getPSpell4());
                        show=true;
                    }
                    break;
                case 5:
                    if(ViewBuildActivity.build.getSpell5()!=0) {
                        spell = spells.get(ViewBuildActivity.build.getPSpell5());
                        show=true;
                    }
                    break;
                case 6:
                    if(ViewBuildActivity.build.getSpell6()!=0) {
                        spell = spells.get(ViewBuildActivity.build.getPSpell6());
                        show=true;
                    }
                    break;
            }
            if(show)
                spellc.setSpell(getContext(), spell);
            if (ViewBuildActivity.build.isOnline()) {
                Button button = (Button) d.findViewById(R.id.showspell_button);
                button.getLayoutParams().height = 0;
            } else {
                final Button button = (Button) d.findViewById(R.id.showspell_button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (numero) {
                            case 1:
                                ViewBuildActivity.build.setSpell1(0);
                                passive_skill1.setImageDrawable(GetImage(getContext(),spells.get(0).getImage()));
                                break;
                            case 2:
                                ViewBuildActivity.build.setSpell2(0);
                                passive_skill2.setImageDrawable(GetImage(getContext(),spells.get(0).getImage()));
                                break;
                            case 3:
                                ViewBuildActivity.build.setSpell3(0);
                                passive_skill3.setImageDrawable(GetImage(getContext(),spells.get(0).getImage()));
                                break;
                            case 4:
                                ViewBuildActivity.build.setSpell4(0);
                                passive_skill4.setImageDrawable(GetImage(getContext(),spells.get(0).getImage()));
                                break;
                            case 5:
                                ViewBuildActivity.build.setSpell5(0);
                                passive_skill5.setImageDrawable(GetImage(getContext(),spells.get(0).getImage()));
                                break;
                            case 6:
                                ViewBuildActivity.build.setSpell6(0);
                                passive_skill6.setImageDrawable(GetImage(getContext(),spells.get(0).getImage()));
                                break;
                        }
                        new BD(getContext()).AtualizarBuild(ViewBuildActivity.build);
                        d.dismiss();
                    }
                });
            }
            if(show)
                d.show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView==null){
            rootView = inflater.inflate(R.layout.fragment_deck_passives, container, false);

            passives_tittle = (TextView) rootView.findViewById(R.id.deck_passives_tittle);
            passives_tittle.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/namefont.ttf"));

            passive_spells = (TextView) rootView.findViewById(R.id.deck_passives_spells);
            passive_spells.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/namefont.ttf"));

            spells = getSkills(ViewBuildActivity.build.getClasse());

            if(ViewBuildActivity.build.getNivel()>=10) {
                passive_skill1 = (ImageView) rootView.findViewById(R.id.passive_skill1);
                passive_skill1.setImageDrawable(GetImage(getContext(),spells.get(ViewBuildActivity.build.getPSpell1()).getImage()));
                passive_skill1.setOnClickListener(new showSpell(1));
                if(!ViewBuildActivity.build.isOnline())
                passive_skill1.setOnDragListener(new MyDragListener());
                if(ViewBuildActivity.build.getNivel()>=30) {
                    passive_skill2 = (ImageView) rootView.findViewById(R.id.passive_skill2);
                    passive_skill2.setImageDrawable(GetImage(getContext(),spells.get(ViewBuildActivity.build.getPSpell2()).getImage()));
                    passive_skill2.setOnClickListener(new showSpell(2));
                    if(!ViewBuildActivity.build.isOnline())
                    passive_skill2.setOnDragListener(new MyDragListener());
                    if(ViewBuildActivity.build.getNivel()>=50) {
                        passive_skill3 = (ImageView) rootView.findViewById(R.id.passive_skill3);
                        passive_skill3.setImageDrawable(GetImage(getContext(),spells.get(ViewBuildActivity.build.getPSpell3()).getImage()));
                        passive_skill3.setOnClickListener(new showSpell(3));
                        if(!ViewBuildActivity.build.isOnline())
                        passive_skill3.setOnDragListener(new MyDragListener());
                        if(ViewBuildActivity.build.getNivel()>=100) {
                            passive_skill4 = (ImageView) rootView.findViewById(R.id.passive_skill4);
                            passive_skill4.setImageDrawable(GetImage(getContext(),spells.get(ViewBuildActivity.build.getPSpell4()).getImage()));
                            passive_skill4.setOnClickListener(new showSpell(4));
                            if(!ViewBuildActivity.build.isOnline())
                            passive_skill4.setOnDragListener(new MyDragListener());
                            if(ViewBuildActivity.build.getNivel()>=150) {
                                passive_skill5 = (ImageView) rootView.findViewById(R.id.passive_skill5);
                                passive_skill5.setImageDrawable(GetImage(getContext(),spells.get(ViewBuildActivity.build.getPSpell5()).getImage()));
                                passive_skill5.setOnClickListener(new showSpell(5));
                                if(!ViewBuildActivity.build.isOnline())
                                passive_skill5.setOnDragListener(new MyDragListener());
                                if (ViewBuildActivity.build.getNivel() >= 200){
                                    passive_skill6 = (ImageView) rootView.findViewById(R.id.passive_skill6);
                                    passive_skill6.setImageDrawable(GetImage(getContext(),spells.get(ViewBuildActivity.build.getPSpell6()).getImage()));
                                    passive_skill6.setOnClickListener(new showSpell(6));
                                    if(!ViewBuildActivity.build.isOnline())
                                    passive_skill6.setOnDragListener(new MyDragListener());
                                }else{
                                    passive_skill6 = (ImageView) rootView.findViewById(R.id.passive_skill6);
                                    passive_skill6.setImageResource(R.drawable.spell_block200);
                                }
                            }else{
                                passive_skill5 = (ImageView) rootView.findViewById(R.id.passive_skill5);
                                passive_skill5.setImageResource(R.drawable.spell_block150);
                                passive_skill6 = (ImageView) rootView.findViewById(R.id.passive_skill6);
                                passive_skill6.setImageResource(R.drawable.spell_block200);
                            }
                        }else{
                            passive_skill4 = (ImageView) rootView.findViewById(R.id.passive_skill4);
                            passive_skill4.setImageResource(R.drawable.spell_block100);
                            passive_skill5 = (ImageView) rootView.findViewById(R.id.passive_skill5);
                            passive_skill5.setImageResource(R.drawable.spell_block150);
                            passive_skill6 = (ImageView) rootView.findViewById(R.id.passive_skill6);
                            passive_skill6.setImageResource(R.drawable.spell_block200);
                        }
                    }else{
                        passive_skill3 = (ImageView) rootView.findViewById(R.id.passive_skill3);
                        passive_skill3.setImageResource(R.drawable.spell_block50);
                        passive_skill4 = (ImageView) rootView.findViewById(R.id.passive_skill4);
                        passive_skill4.setImageResource(R.drawable.spell_block100);
                        passive_skill5 = (ImageView) rootView.findViewById(R.id.passive_skill5);
                        passive_skill5.setImageResource(R.drawable.spell_block150);
                        passive_skill6 = (ImageView) rootView.findViewById(R.id.passive_skill6);
                        passive_skill6.setImageResource(R.drawable.spell_block200);
                    }
                }else{
                    passive_skill2 = (ImageView) rootView.findViewById(R.id.passive_skill2);
                    passive_skill2.setImageResource(R.drawable.spell_block30);
                    passive_skill3 = (ImageView) rootView.findViewById(R.id.passive_skill3);
                    passive_skill3.setImageResource(R.drawable.spell_block50);
                    passive_skill4 = (ImageView) rootView.findViewById(R.id.passive_skill4);
                    passive_skill4.setImageResource(R.drawable.spell_block100);
                    passive_skill5 = (ImageView) rootView.findViewById(R.id.passive_skill5);
                    passive_skill5.setImageResource(R.drawable.spell_block150);
                    passive_skill6 = (ImageView) rootView.findViewById(R.id.passive_skill6);
                    passive_skill6.setImageResource(R.drawable.spell_block200);
                }
            }else{
                passive_skill1 = (ImageView) rootView.findViewById(R.id.passive_skill1);
                passive_skill1.setImageResource(R.drawable.spell_block10);
                passive_skill2 = (ImageView) rootView.findViewById(R.id.passive_skill2);
                passive_skill2.setImageResource(R.drawable.spell_block30);
                passive_skill3 = (ImageView) rootView.findViewById(R.id.passive_skill3);
                passive_skill3.setImageResource(R.drawable.spell_block50);
                passive_skill4 = (ImageView) rootView.findViewById(R.id.passive_skill4);
                passive_skill4.setImageResource(R.drawable.spell_block100);
                passive_skill5 = (ImageView) rootView.findViewById(R.id.passive_skill5);
                passive_skill5.setImageResource(R.drawable.spell_block150);
                passive_skill6 = (ImageView) rootView.findViewById(R.id.passive_skill6);
                passive_skill6.setImageResource(R.drawable.spell_block200);
            }


            passive1 = (ImageView) rootView.findViewById(R.id.passive1);
            passive1.setImageDrawable(GetImage(getContext(), spells.get(1).getImage()));
            passive1.setOnClickListener(new MyOnClickSpell(spells.get(1)));
            passive1.setOnLongClickListener(new MyLongClickListener());

            passive2 = (ImageView) rootView.findViewById(R.id.passive2);
            passive2.setImageDrawable(GetImage(getContext(), spells.get(2).getImage()));
            passive2.setOnClickListener(new MyOnClickSpell(spells.get(2)));
            passive2.setOnLongClickListener(new MyTouchListener());

            passive3 = (ImageView) rootView.findViewById(R.id.passive3);
            passive3.setImageDrawable(GetImage(getContext(), spells.get(3).getImage()));
            passive3.setOnClickListener(new MyOnClickSpell(spells.get(3)));
            passive3.setOnLongClickListener(new MyTouchListener());

            passive4 = (ImageView) rootView.findViewById(R.id.passive4);
            passive4.setImageDrawable(GetImage(getContext(), spells.get(4).getImage()));
            passive4.setOnClickListener(new MyOnClickSpell(spells.get(4)));
            passive4.setOnLongClickListener(new MyTouchListener());

            passive5 = (ImageView) rootView.findViewById(R.id.passive5);
            passive5.setImageDrawable(GetImage(getContext(), spells.get(5).getImage()));
            passive5.setOnClickListener(new MyOnClickSpell(spells.get(5)));
            passive5.setOnLongClickListener(new MyTouchListener());

            passive6 = (ImageView) rootView.findViewById(R.id.passive6);
            passive6.setImageDrawable(GetImage(getContext(), spells.get(6).getImage()));
            passive6.setOnClickListener(new MyOnClickSpell(spells.get(6)));
            passive6.setOnLongClickListener(new MyTouchListener());

            passive7 = (ImageView) rootView.findViewById(R.id.passive7);
            passive7.setImageDrawable(GetImage(getContext(), spells.get(7).getImage()));
            passive7.setOnClickListener(new MyOnClickSpell(spells.get(7)));
            passive7.setOnLongClickListener(new MyTouchListener());

            passive8 = (ImageView) rootView.findViewById(R.id.passive8);
            passive8.setImageDrawable(GetImage(getContext(), spells.get(8).getImage()));
            passive8.setOnClickListener(new MyOnClickSpell(spells.get(8)));
            passive8.setOnLongClickListener(new MyTouchListener());

            passive9 = (ImageView) rootView.findViewById(R.id.passive9);
            passive9.setImageDrawable(GetImage(getContext(), spells.get(9).getImage()));
            passive9.setOnClickListener(new MyOnClickSpell(spells.get(9)));
            passive9.setOnLongClickListener(new MyTouchListener());

            passive10 = (ImageView) rootView.findViewById(R.id.passive10);
            passive10.setImageDrawable(GetImage(getContext(), spells.get(10).getImage()));
            passive10.setOnClickListener(new MyOnClickSpell(spells.get(10)));
            passive10.setOnLongClickListener(new MyTouchListener());

            passive11 = (ImageView) rootView.findViewById(R.id.passive11);
            passive11.setImageDrawable(GetImage(getContext(), spells.get(11).getImage()));
            passive11.setOnClickListener(new MyOnClickSpell(spells.get(11)));
            passive11.setOnLongClickListener(new MyTouchListener());

            passive12 = (ImageView) rootView.findViewById(R.id.passive12);
            passive12.setImageDrawable(GetImage(getContext(), spells.get(12).getImage()));
            passive12.setOnClickListener(new MyOnClickSpell(spells.get(12)));
            passive12.setOnLongClickListener(new MyTouchListener());

            passive13 = (ImageView) rootView.findViewById(R.id.passive13);
            passive13.setImageDrawable(GetImage(getContext(), spells.get(13).getImage()));
            passive13.setOnClickListener(new MyOnClickSpell(spells.get(13)));
            passive13.setOnLongClickListener(new MyTouchListener());

            passive14 = (ImageView) rootView.findViewById(R.id.passive14);
            passive14.setImageDrawable(GetImage(getContext(), spells.get(14).getImage()));
            passive14.setOnClickListener(new MyOnClickSpell(spells.get(14)));
            passive14.setOnLongClickListener(new MyTouchListener());

            passive15 = (ImageView) rootView.findViewById(R.id.passive15);
            passive15.setImageDrawable(GetImage(getContext(), spells.get(15).getImage()));
            passive15.setOnClickListener(new MyOnClickSpell(spells.get(15)));
            passive15.setOnLongClickListener(new MyTouchListener());
            //classes que so tem 15 passivas
            //                              osamodas
            if(ViewBuildActivity.build.getClasse()!=8) {
                passive16 = (ImageView) rootView.findViewById(R.id.passive16);
                passive16.setImageDrawable(GetImage(getContext(), spells.get(16).getImage()));
                passive16.setOnClickListener(new MyOnClickSpell(spells.get(16)));
                passive16.setOnLongClickListener(new MyTouchListener());
                //classes que só tem 16
                //                              steamer                                         zobal                                       xelor
                if(ViewBuildActivity.build.getClasse()!=14 && ViewBuildActivity.build.getClasse()!=16 && ViewBuildActivity.build.getClasse()!=15) {
                    passive17 = (ImageView) rootView.findViewById(R.id.passive17);
                    passive17.setImageDrawable(GetImage(getContext(), spells.get(17).getImage()));
                    passive17.setOnClickListener(new MyOnClickSpell(spells.get(17)));
                    passive17.setOnLongClickListener(new MyTouchListener());
                    //classes que tem 17
                    //                                  cra                                         eca                                      elio
                    if(ViewBuildActivity.build.getClasse()!=0 && ViewBuildActivity.build.getClasse()!=1 && ViewBuildActivity.build.getClasse()!=2 &&
                            //                              eni                                         enu                                     feca
                            ViewBuildActivity.build.getClasse()!=3 && ViewBuildActivity.build.getClasse()!=4 && ViewBuildActivity.build.getClasse()!=5 &&
                            //                              panda                                       ladino                                      sadida
                            ViewBuildActivity.build.getClasse()!=9 && ViewBuildActivity.build.getClasse()!=10 && ViewBuildActivity.build.getClasse()!=12) {
                        passive18 = (ImageView) rootView.findViewById(R.id.passive18);
                        passive18.setImageDrawable(GetImage(getContext(), spells.get(18).getImage()));
                        passive18.setOnClickListener(new MyOnClickSpell(spells.get(18)));
                        passive18.setOnLongClickListener(new MyTouchListener());
                    }
                }
            }
        }
        return rootView;
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
