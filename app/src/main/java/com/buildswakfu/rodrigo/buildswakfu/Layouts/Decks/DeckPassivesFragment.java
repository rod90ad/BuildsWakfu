package com.buildswakfu.rodrigo.buildswakfu.Layouts.Decks;

import android.content.ClipData;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.buildswakfu.rodrigo.buildswakfu.Layouts.ViewBuildFragment;
import com.buildswakfu.rodrigo.buildswakfu.R;
import com.buildswakfu.rodrigo.buildswakfu.Utils.BD;
import com.buildswakfu.rodrigo.buildswakfu.ViewBuildActivity;

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
    private int[] passives;
    private TypedArray spells;

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

    private TypedArray getPassiveSkills(int classe){
        switch (classe){
            case 0:
                return getResources().obtainTypedArray(R.array.cra_passives);
            case 1:
                return getResources().obtainTypedArray(R.array.cra_passives);
            /*
            case 2:
                head.setBackground(getResources().getDrawable(R.drawable.elio_head));
            case 3:
                head.setBackground(getResources().getDrawable(R.drawable.eni_head));
            case 4:
                head.setBackground(getResources().getDrawable(R.drawable.enu_head));*/
            case 5:
                return getResources().obtainTypedArray(R.array.feca_passives);
            case 6:
                return getResources().obtainTypedArray(R.array.hupp_passives);/*
            case 7:
                head.setBackground(getResources().getDrawable(R.drawable.iop_head));
            case 8:
                head.setBackground(getResources().getDrawable(R.drawable.osa_head));
            case 9:
                head.setBackground(getResources().getDrawable(R.drawable.panda_head));
            case 10:
                head.setBackground(getResources().getDrawable(R.drawable.lad_head));
            case 11:
                head.setBackground(getResources().getDrawable(R.drawable.sac_head));
            case 12:
                head.setBackground(getResources().getDrawable(R.drawable.sad_head));*/
            case 13:
                return getResources().obtainTypedArray(R.array.sram_passives);
            case 14:
                return getResources().obtainTypedArray(R.array.steam_passives);
            case 15:
                return getResources().obtainTypedArray(R.array.xelor_passives);
            case 16:
                return getResources().obtainTypedArray(R.array.zob_passives);
            default:
                return getResources().obtainTypedArray(R.array.cra_passives);
        }
    }

    // This defines your touch listener
    private final class MyTouchListener implements View.OnTouchListener {

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

    private void SaveSpells(){
        for(int i=0;i<passives.length;i++){
            if(((BitmapDrawable)passive_skill1.getDrawable()).getBitmap().equals(((BitmapDrawable)spells.getDrawable(passives[i])).getBitmap())){
                ViewBuildActivity.build.setPSpell1(i);
            }
            if(((BitmapDrawable)passive_skill2.getDrawable()).getBitmap().equals(((BitmapDrawable)spells.getDrawable(passives[i])).getBitmap())){
                ViewBuildActivity.build.setPSpell2(i);
            }
            if(((BitmapDrawable)passive_skill3.getDrawable()).getBitmap().equals(((BitmapDrawable)spells.getDrawable(passives[i])).getBitmap())){
                ViewBuildActivity.build.setPSpell3(i);
            }
            if(((BitmapDrawable)passive_skill4.getDrawable()).getBitmap().equals(((BitmapDrawable)spells.getDrawable(passives[i])).getBitmap())){
                ViewBuildActivity.build.setPSpell4(i);
            }
            if(((BitmapDrawable)passive_skill5.getDrawable()).getBitmap().equals(((BitmapDrawable)spells.getDrawable(passives[i])).getBitmap())){
                ViewBuildActivity.build.setPSpell5(i);
            }
            if(((BitmapDrawable)passive_skill6.getDrawable()).getBitmap().equals(((BitmapDrawable)spells.getDrawable(passives[i])).getBitmap())){
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView==null){
            rootView = inflater.inflate(R.layout.fragment_deck_passives, container, false);

            passives_tittle = (TextView) rootView.findViewById(R.id.deck_passives_tittle);
            passives_tittle.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/namefont.ttf"));

            passive_spells = (TextView) rootView.findViewById(R.id.deck_passives_spells);
            passive_spells.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/namefont.ttf"));

            spells = getPassiveSkills(ViewBuildActivity.build.getClasse());
            passives= new int[spells.length()];
            for (int i=1;i<spells.length();i++){
                passives[i]=i;
            }

            if(ViewBuildActivity.build.getNivel()>=10) {
                passive_skill1 = (ImageView) rootView.findViewById(R.id.passive_skill1);
                passive_skill1.setImageResource(spells.getResourceId(ViewBuildActivity.build.getPSpell1(), R.drawable.spell_empty));
                passive_skill1.setOnDragListener(new MyDragListener());
                if(ViewBuildActivity.build.getNivel()>=30) {
                    passive_skill2 = (ImageView) rootView.findViewById(R.id.passive_skill2);
                    passive_skill2.setImageResource(spells.getResourceId(ViewBuildActivity.build.getPSpell2(), R.drawable.spell_empty));
                    passive_skill2.setOnDragListener(new MyDragListener());
                    if(ViewBuildActivity.build.getNivel()>=50) {
                        passive_skill3 = (ImageView) rootView.findViewById(R.id.passive_skill3);
                        passive_skill3.setImageResource(spells.getResourceId(ViewBuildActivity.build.getPSpell3(), R.drawable.spell_empty));
                        passive_skill3.setOnDragListener(new MyDragListener());
                        if(ViewBuildActivity.build.getNivel()>=100) {
                            passive_skill4 = (ImageView) rootView.findViewById(R.id.passive_skill4);
                            passive_skill4.setImageResource(spells.getResourceId(ViewBuildActivity.build.getPSpell4(), R.drawable.spell_empty));
                            passive_skill4.setOnDragListener(new MyDragListener());
                            if(ViewBuildActivity.build.getNivel()>=150) {
                                passive_skill5 = (ImageView) rootView.findViewById(R.id.passive_skill5);
                                passive_skill5.setImageResource(spells.getResourceId(ViewBuildActivity.build.getPSpell5(), R.drawable.spell_empty));
                                passive_skill5.setOnDragListener(new MyDragListener());
                                if (ViewBuildActivity.build.getNivel() >= 200){
                                    passive_skill6 = (ImageView) rootView.findViewById(R.id.passive_skill6);
                                    passive_skill6.setImageResource(spells.getResourceId(ViewBuildActivity.build.getPSpell6(), R.drawable.spell_empty));
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
            passive1.setImageResource(spells.getResourceId(passives[1], 1));
            passive1.setOnTouchListener(new MyTouchListener());
            passive2 = (ImageView) rootView.findViewById(R.id.passive2);
            passive2.setImageResource(spells.getResourceId(passives[2], 2));
            passive2.setOnTouchListener(new MyTouchListener());
            passive3 = (ImageView) rootView.findViewById(R.id.passive3);
            passive3.setImageResource(spells.getResourceId(passives[3], 3));
            passive3.setOnTouchListener(new MyTouchListener());
            passive4 = (ImageView) rootView.findViewById(R.id.passive4);
            passive4.setImageResource(spells.getResourceId(passives[4], 4));
            passive4.setOnTouchListener(new MyTouchListener());
            passive5 = (ImageView) rootView.findViewById(R.id.passive5);
            passive5.setImageResource(spells.getResourceId(passives[5], 5));
            passive5.setOnTouchListener(new MyTouchListener());
            passive6 = (ImageView) rootView.findViewById(R.id.passive6);
            passive6.setImageResource(spells.getResourceId(passives[6], 6));
            passive6.setOnTouchListener(new MyTouchListener());
            passive7 = (ImageView) rootView.findViewById(R.id.passive7);
            passive7.setImageResource(spells.getResourceId(passives[7], 7));
            passive7.setOnTouchListener(new MyTouchListener());
            passive8 = (ImageView) rootView.findViewById(R.id.passive8);
            passive8.setImageResource(spells.getResourceId(passives[8], 8));
            passive8.setOnTouchListener(new MyTouchListener());
            passive9 = (ImageView) rootView.findViewById(R.id.passive9);
            passive9.setImageResource(spells.getResourceId(passives[9], 9));
            passive9.setOnTouchListener(new MyTouchListener());
            passive10 = (ImageView) rootView.findViewById(R.id.passive10);
            passive10.setImageResource(spells.getResourceId(passives[10], 10));
            passive10.setOnTouchListener(new MyTouchListener());
            passive11 = (ImageView) rootView.findViewById(R.id.passive11);
            passive11.setImageResource(spells.getResourceId(passives[11], 11));
            passive11.setOnTouchListener(new MyTouchListener());
            passive12 = (ImageView) rootView.findViewById(R.id.passive12);
            passive12.setImageResource(spells.getResourceId(passives[12], 12));
            passive12.setOnTouchListener(new MyTouchListener());
            passive13 = (ImageView) rootView.findViewById(R.id.passive13);
            passive13.setImageResource(spells.getResourceId(passives[13], 13));
            passive13.setOnTouchListener(new MyTouchListener());
            passive14 = (ImageView) rootView.findViewById(R.id.passive14);
            passive14.setImageResource(spells.getResourceId(passives[14], 14));
            passive14.setOnTouchListener(new MyTouchListener());
            passive15 = (ImageView) rootView.findViewById(R.id.passive15);
            passive15.setImageResource(spells.getResourceId(passives[15], 15));
            passive15.setOnTouchListener(new MyTouchListener());
            //classes que so tem 15 passivas
            //                              osamodas
            if(ViewBuildActivity.build.getClasse()!=8) {
                passive16 = (ImageView) rootView.findViewById(R.id.passive16);
                passive16.setImageResource(spells.getResourceId(passives[16], 16));
                passive16.setOnTouchListener(new MyTouchListener());
                //classes que só tem 16
                //                              steamer                                         zobal                                       xelor
                if(ViewBuildActivity.build.getClasse()!=14 && ViewBuildActivity.build.getClasse()!=16 && ViewBuildActivity.build.getClasse()!=15) {
                    passive17 = (ImageView) rootView.findViewById(R.id.passive17);
                    passive17.setImageResource(spells.getResourceId(passives[17], 17));
                    passive17.setOnTouchListener(new MyTouchListener());
                    //classes que tem 17
                    //                                  cra                                         eca                                      elio
                    if(ViewBuildActivity.build.getClasse()!=0 && ViewBuildActivity.build.getClasse()!=1 && ViewBuildActivity.build.getClasse()!=2 &&
                            //                              eni                                         enu                                     feca
                            ViewBuildActivity.build.getClasse()!=3 && ViewBuildActivity.build.getClasse()!=4 && ViewBuildActivity.build.getClasse()!=5 &&
                            //                              panda                                       ladino                                      sadida
                            ViewBuildActivity.build.getClasse()!=9 && ViewBuildActivity.build.getClasse()!=10 && ViewBuildActivity.build.getClasse()!=12) {
                        passive18 = (ImageView) rootView.findViewById(R.id.passive18);
                        passive18.setImageResource(spells.getResourceId(passives[18], 18));
                        passive18.setOnTouchListener(new MyTouchListener());
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
