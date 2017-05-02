package com.buildswakfu.rodrigo.buildswakfu.Layouts.Decks;

import android.content.ClipData;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
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
import android.widget.TextView;

import com.buildswakfu.rodrigo.buildswakfu.Layouts.ViewBuildFragment;
import com.buildswakfu.rodrigo.buildswakfu.R;
import com.buildswakfu.rodrigo.buildswakfu.ViewBuildActivity;

import java.lang.reflect.Array;

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
    private ImageView ele1_6;

    private ImageView ele2_1;
    private ImageView ele2_2;
    private ImageView ele2_3;
    private ImageView ele2_4;
    private ImageView ele2_5;
    private ImageView ele2_6;

    private ImageView ele3_1;
    private ImageView ele3_2;
    private ImageView ele3_3;
    private ImageView ele3_4;
    private ImageView ele3_5;
    private ImageView ele3_6;

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

    private TypedArray getSkills(int classe){
        switch (classe){
            case 0:
                return getResources().obtainTypedArray(R.array.cra_spells);
            case 1:
                return getResources().obtainTypedArray(R.array.cra_spells);
            /*
            case 2:
                head.setBackground(getResources().getDrawable(R.drawable.elio_head));
            case 3:
                head.setBackground(getResources().getDrawable(R.drawable.eni_head));
            case 4:
                head.setBackground(getResources().getDrawable(R.drawable.enu_head));
            case 5:
                head.setBackground(getResources().getDrawable(R.drawable.feca_head));
            case 6:
                head.setBackground(getResources().getDrawable(R.drawable.hupp_head));
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
                head.setBackground(getResources().getDrawable(R.drawable.sad_head));
            case 13:
                head.setBackground(getResources().getDrawable(R.drawable.sram_head));
            case 14:
                head.setBackground(getResources().getDrawable(R.drawable.steam_head));
            case 15:
                head.setBackground(getResources().getDrawable(R.drawable.xelor_head));
            case 16:
                head.setBackground(getResources().getDrawable(R.drawable.zob_head));*/
            default:
                return getResources().obtainTypedArray(R.array.cra_spells);
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
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setVisibility(View.VISIBLE);
                default:
                    break;
            }
            return true;
        }
    }

    private void OnlySpellControl(Drawable drawable){
        if(drawable.equals(skill1.getDrawable())){
            skill1.setImageResource(R.drawable.deck_empty_skill);
        }
        if(drawable.equals(skill2.getDrawable())){
            skill2.setImageResource(R.drawable.deck_empty_skill);
        }
        if(drawable.equals(skill3.getDrawable())){
            skill3.setImageResource(R.drawable.deck_empty_skill);
        }
        if(drawable.equals(skill4.getDrawable())){
            skill4.setImageResource(R.drawable.deck_empty_skill);
        }
        if(drawable.equals(skill5.getDrawable())){
            skill5.setImageResource(R.drawable.deck_empty_skill);
        }
        if(drawable.equals(skill6.getDrawable())){
            skill6.setImageResource(R.drawable.deck_empty_skill);
        }
        if(drawable.equals(skill7.getDrawable())){
            skill7.setImageResource(R.drawable.deck_empty_skill);
        }
        if(drawable.equals(skill8.getDrawable())){
            skill8.setImageResource(R.drawable.deck_empty_skill);
        }
        if(drawable.equals(skill9.getDrawable())){
            skill9.setImageResource(R.drawable.deck_empty_skill);
        }
        if(drawable.equals(skill10.getDrawable())){
            skill10.setImageResource(R.drawable.deck_empty_skill);
        }
        if(drawable.equals(skill11.getDrawable())){
            skill11.setImageResource(R.drawable.deck_empty_skill);
        }
        if(drawable.equals(skill12.getDrawable())){
            skill12.setImageResource(R.drawable.deck_empty_skill);
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

            TypedArray spells = getSkills(ViewBuildActivity.build.getClasse());
            int[] elemental= new int[spells.length()];
            for (int i=1;i<spells.length();i++){
                elemental[i]=i;
            }

            skill1 = (ImageView) rootView.findViewById(R.id.active_skill1);
            skill1.setImageResource(spells.getResourceId(ViewBuildActivity.build.getSpell1(), R.drawable.deck_empty_skill));
            skill1.setOnDragListener(new MyDragListener());
            skill2 = (ImageView) rootView.findViewById(R.id.active_skill2);
            skill2.setImageResource(spells.getResourceId(ViewBuildActivity.build.getSpell2(), R.drawable.deck_empty_skill));
            skill2.setOnDragListener(new MyDragListener());
            skill3 = (ImageView) rootView.findViewById(R.id.active_skill3);
            skill3.setImageResource(spells.getResourceId(ViewBuildActivity.build.getSpell3(), R.drawable.deck_empty_skill));
            skill3.setOnDragListener(new MyDragListener());
            skill4 = (ImageView) rootView.findViewById(R.id.active_skill4);
            skill4.setImageResource(spells.getResourceId(ViewBuildActivity.build.getSpell4(), R.drawable.deck_empty_skill));
            skill4.setOnDragListener(new MyDragListener());
            skill5 = (ImageView) rootView.findViewById(R.id.active_skill5);
            skill5.setImageResource(spells.getResourceId(ViewBuildActivity.build.getSpell5(), R.drawable.deck_empty_skill));
            skill5.setOnDragListener(new MyDragListener());
            skill6 = (ImageView) rootView.findViewById(R.id.active_skill6);
            skill6.setImageResource(spells.getResourceId(ViewBuildActivity.build.getSpell6(), R.drawable.deck_empty_skill));
            skill6.setOnDragListener(new MyDragListener());
            skill7 = (ImageView) rootView.findViewById(R.id.active_skill7);
            skill7.setImageResource(spells.getResourceId(ViewBuildActivity.build.getSpell7(), R.drawable.deck_empty_skill));
            skill7.setOnDragListener(new MyDragListener());
            skill8 = (ImageView) rootView.findViewById(R.id.active_skill8);
            skill8.setImageResource(spells.getResourceId(ViewBuildActivity.build.getSpell8(), R.drawable.deck_empty_skill));
            skill8.setOnDragListener(new MyDragListener());
            skill9 = (ImageView) rootView.findViewById(R.id.active_skill9);
            skill9.setImageResource(spells.getResourceId(ViewBuildActivity.build.getSpell9(), R.drawable.deck_empty_skill));
            skill9.setOnDragListener(new MyDragListener());
            skill10 = (ImageView) rootView.findViewById(R.id.active_skill10);
            skill10.setImageResource(spells.getResourceId(ViewBuildActivity.build.getSpell10(), R.drawable.deck_empty_skill));
            skill10.setOnDragListener(new MyDragListener());
            skill11 = (ImageView) rootView.findViewById(R.id.active_skill11);
            skill11.setImageResource(spells.getResourceId(ViewBuildActivity.build.getSpell11(), R.drawable.deck_empty_skill));
            skill11.setOnDragListener(new MyDragListener());
            skill12 = (ImageView) rootView.findViewById(R.id.active_skill12);
            skill12.setImageResource(spells.getResourceId(ViewBuildActivity.build.getSpell12(), R.drawable.deck_empty_skill));
            skill12.setOnDragListener(new MyDragListener());

            ele1_1 = (ImageView) rootView.findViewById(R.id.active_ele1_1);
            ele1_1.setImageResource(spells.getResourceId(elemental[1], 1));
            ele1_1.setOnTouchListener(new MyTouchListener());
            ele1_2 = (ImageView) rootView.findViewById(R.id.active_ele1_2);
            ele1_2.setImageResource(spells.getResourceId(elemental[2], 2));
            ele1_2.setOnTouchListener(new MyTouchListener());
            ele1_3 = (ImageView) rootView.findViewById(R.id.active_ele1_3);
            ele1_3.setImageResource(spells.getResourceId(elemental[3], 3));
            ele1_3.setOnTouchListener(new MyTouchListener());
            ele1_4 = (ImageView) rootView.findViewById(R.id.active_ele1_4);
            ele1_4.setImageResource(spells.getResourceId(elemental[4], 4));
            ele1_4.setOnTouchListener(new MyTouchListener());
            ele1_5 = (ImageView) rootView.findViewById(R.id.active_ele1_5);
            ele1_5.setImageResource(spells.getResourceId(elemental[5], 5));
            ele1_5.setOnTouchListener(new MyTouchListener());

            ele2_1 = (ImageView) rootView.findViewById(R.id.active_ele2_1);
            ele2_1.setImageResource(spells.getResourceId(elemental[6], 6));
            ele2_1.setOnTouchListener(new MyTouchListener());
            ele2_2 = (ImageView) rootView.findViewById(R.id.active_ele2_2);
            ele2_2.setImageResource(spells.getResourceId(elemental[7], 7));
            ele2_2.setOnTouchListener(new MyTouchListener());
            ele2_3 = (ImageView) rootView.findViewById(R.id.active_ele2_3);
            ele2_3.setImageResource(spells.getResourceId(elemental[8], 8));
            ele2_3.setOnTouchListener(new MyTouchListener());
            ele2_4 = (ImageView) rootView.findViewById(R.id.active_ele2_4);
            ele2_4.setImageResource(spells.getResourceId(elemental[9], 9));
            ele2_4.setOnTouchListener(new MyTouchListener());
            ele2_5 = (ImageView) rootView.findViewById(R.id.active_ele2_5);
            ele2_5.setImageResource(spells.getResourceId(elemental[10], 10));
            ele2_5.setOnTouchListener(new MyTouchListener());

            ele3_1 = (ImageView) rootView.findViewById(R.id.active_ele3_1);
            ele3_1.setImageResource(spells.getResourceId(elemental[11], 11));
            ele3_1.setOnTouchListener(new MyTouchListener());
            ele3_2 = (ImageView) rootView.findViewById(R.id.active_ele3_2);
            ele3_2.setImageResource(spells.getResourceId(elemental[12], 12));
            ele3_2.setOnTouchListener(new MyTouchListener());
            ele3_3 = (ImageView) rootView.findViewById(R.id.active_ele3_3);
            ele3_3.setImageResource(spells.getResourceId(elemental[13], 13));
            ele3_3.setOnTouchListener(new MyTouchListener());
            ele3_4 = (ImageView) rootView.findViewById(R.id.active_ele3_4);
            ele3_4.setImageResource(spells.getResourceId(elemental[14], 14));
            ele3_4.setOnTouchListener(new MyTouchListener());
            ele3_5 = (ImageView) rootView.findViewById(R.id.active_ele3_5);
            ele3_5.setImageResource(spells.getResourceId(elemental[15], 15));
            ele3_5.setOnTouchListener(new MyTouchListener());

            active1 = (ImageView) rootView.findViewById(R.id.active_active1);
            active1.setImageResource(spells.getResourceId(elemental[16], 16));
            active1.setOnTouchListener(new MyTouchListener());
            active2 = (ImageView) rootView.findViewById(R.id.active_active2);
            active2.setImageResource(spells.getResourceId(elemental[17], 17));
            active2.setOnTouchListener(new MyTouchListener());
            active3 = (ImageView) rootView.findViewById(R.id.active_active3);
            active3.setImageResource(spells.getResourceId(elemental[18], 18));
            active3.setOnTouchListener(new MyTouchListener());
            active4 = (ImageView) rootView.findViewById(R.id.active_active4);
            active4.setImageResource(spells.getResourceId(elemental[19], 19));
            active4.setOnTouchListener(new MyTouchListener());
            active5 = (ImageView) rootView.findViewById(R.id.active_active5);
            active5.setImageResource(spells.getResourceId(elemental[20], 20));
            active5.setOnTouchListener(new MyTouchListener());
            /*
            active6 = (ImageView) rootView.findViewById(R.id.active_active6);
            active6.setImageResource(spells.getIndex(21));
            active7 = (ImageView) rootView.findViewById(R.id.active_active7);
            active7.setImageResource(spells.getIndex(22));
            active8 = (ImageView) rootView.findViewById(R.id.active_active8);
            active8.setImageResource(spells.getIndex(23));*/
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
