package com.rodrigoad.rodso.wakfustats.Layouts.PointsFragments;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rodrigoad.rodso.wakfustats.Layouts.ViewBuildFragment;
import com.rodrigoad.rodso.wakfustats.R;
import com.rodrigoad.rodso.wakfustats.Utils.BD;
import com.rodrigoad.rodso.wakfustats.ViewBuildActivity;

import static com.rodrigoad.rodso.wakfustats.MainActivity.context;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AgiFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AgiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View rootView;
    private TextView tittle;
    private TextView pointToDist;

    private LinearLayout lockMinus;
    private TextView lockText;
    private LinearLayout lockPlus;

    private LinearLayout dodgeMinus;
    private TextView dodgeText;
    private LinearLayout dodgePlus;

    private LinearLayout iniciativeMinus;
    private TextView iniciativeText;
    private LinearLayout iniciativePlus;

    private LinearLayout lockanddodgeMinus;
    private TextView lockanddodgeText;
    private LinearLayout lockanddodgePlus;

    private LinearLayout apmpremovalMinus;
    private TextView apmpremovalText;
    private LinearLayout apmpremovalPlus;

    private LinearLayout apmpresMinus;
    private TextView apmpresText;
    private LinearLayout apmpresPlus;

    private Button save;

    private static ViewBuildFragment viewBuildFragment;

    private OnFragmentInteractionListener mListener;

    public AgiFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgiFragment newInstance(ViewBuildFragment param1, String param2) {
        AgiFragment fragment = new AgiFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(rootView==null) {
            rootView = inflater.inflate(R.layout.fragment_agi, container, false);

            tittle = (TextView) rootView.findViewById(R.id.tab_agi_tittle);
            tittle.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

            pointToDist = (TextView) rootView.findViewById(R.id.agi_points_to_dist);
            pointToDist.setText(""+ ViewBuildActivity.build.getAgiPoints());

            //Lock
            lockText = (TextView) rootView.findViewById(R.id.agi_lock_point);
            lockText.setText(ViewBuildActivity.build.getApinblock()+"");
            if(!ViewBuildActivity.build.isOnline()) {
                lockMinus = (LinearLayout) rootView.findViewById(R.id.agi_lock_minus);
                lockMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!lockText.getText().equals("0")) {
                            ViewBuildActivity.build.setApinblock(ViewBuildActivity.build.getApinblock() - 1);
                            ViewBuildActivity.build.setAgiPoints(ViewBuildActivity.build.getAgiPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            lockText.setText(ViewBuildActivity.build.getApinblock() + "");
                            if (lockText.getText().equals("0")) {
                                lockMinus.setEnabled(false);
                            }
                            lockPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                lockPlus = (LinearLayout) rootView.findViewById(R.id.agi_lock_plus);
                lockPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinblock(ViewBuildActivity.build.getApinblock() + 1);
                            ViewBuildActivity.build.setAgiPoints(ViewBuildActivity.build.getAgiPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            lockText.setText(ViewBuildActivity.build.getApinblock() + "");
                            lockMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (lockText.getText().equals("0")) {
                    lockMinus.setEnabled(false);
                    lockPlus.setEnabled(true);
                } else {
                    lockMinus.setEnabled(true);
                    lockPlus.setEnabled(true);
                }
            }else{
                lockMinus = (LinearLayout) rootView.findViewById(R.id.agi_lock_minus);
                lockMinus.getLayoutParams().height=0;
                lockPlus = (LinearLayout) rootView.findViewById(R.id.agi_lock_plus);
                lockPlus.getLayoutParams().height=0;
            }

            //Dodge
            dodgeText = (TextView) rootView.findViewById(R.id.agi_dodge_point);
            dodgeText.setText(ViewBuildActivity.build.getApinesquiva()+"");
            if(!ViewBuildActivity.build.isOnline()) {
                dodgeMinus = (LinearLayout) rootView.findViewById(R.id.agi_dodge_minus);
                dodgeMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!dodgeText.getText().equals("0")) {
                            ViewBuildActivity.build.setApinesquiva(ViewBuildActivity.build.getApinesquiva() - 1);
                            ViewBuildActivity.build.setAgiPoints(ViewBuildActivity.build.getAgiPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            dodgeText.setText(ViewBuildActivity.build.getApinesquiva() + "");
                            if (dodgeText.getText().equals("0")) {
                                dodgeMinus.setEnabled(false);
                            }
                            dodgePlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                dodgePlus = (LinearLayout) rootView.findViewById(R.id.agi_dodge_plus);
                dodgePlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinesquiva(ViewBuildActivity.build.getApinesquiva() + 1);
                            ViewBuildActivity.build.setAgiPoints(ViewBuildActivity.build.getAgiPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            dodgeText.setText(ViewBuildActivity.build.getApinesquiva() + "");
                            dodgeMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (dodgeText.getText().equals("0")) {
                    dodgeMinus.setEnabled(false);
                    dodgePlus.setEnabled(true);
                } else {
                    dodgeMinus.setEnabled(true);
                    dodgePlus.setEnabled(false);
                }
            }else{
                dodgeMinus = (LinearLayout) rootView.findViewById(R.id.agi_dodge_minus);
                dodgeMinus.getLayoutParams().height=0;
                dodgePlus = (LinearLayout) rootView.findViewById(R.id.agi_dodge_plus);
                dodgePlus.getLayoutParams().height=0;
            }

            //iniciativa
            iniciativeText = (TextView) rootView.findViewById(R.id.agi_iniciative_point);
            iniciativeText.setText(ViewBuildActivity.build.getApininiciativa()+"/20");
            if(!ViewBuildActivity.build.isOnline()) {
                iniciativeMinus = (LinearLayout) rootView.findViewById(R.id.agi_iniciative_minus);
                iniciativeMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!iniciativeText.getText().equals("0/20")) {
                            ViewBuildActivity.build.setApininiciativa(ViewBuildActivity.build.getApininiciativa() - 1);
                            ViewBuildActivity.build.setAgiPoints(ViewBuildActivity.build.getAgiPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            iniciativeText.setText(ViewBuildActivity.build.getApininiciativa() + "/20");
                            if (iniciativeText.getText().equals("0/20")) {
                                iniciativeMinus.setEnabled(false);
                            }
                            iniciativePlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                iniciativePlus = (LinearLayout) rootView.findViewById(R.id.agi_iniciative_plus);
                iniciativePlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApininiciativa(ViewBuildActivity.build.getApininiciativa() + 1);
                            ViewBuildActivity.build.setAgiPoints(ViewBuildActivity.build.getAgiPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            iniciativeText.setText(ViewBuildActivity.build.getApininiciativa() + "/20");
                            if (iniciativeText.getText().equals("20/20")) {
                                iniciativePlus.setEnabled(false);
                            }
                            iniciativeMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (iniciativeText.getText().equals("0/20")) {
                    iniciativeMinus.setEnabled(false);
                    iniciativePlus.setEnabled(true);
                } else {
                    iniciativeMinus.setEnabled(true);
                    iniciativePlus.setEnabled(true);
                }
            }else{
                iniciativeMinus = (LinearLayout) rootView.findViewById(R.id.agi_iniciative_minus);
                iniciativeMinus.getLayoutParams().height=0;
                iniciativePlus = (LinearLayout) rootView.findViewById(R.id.agi_iniciative_plus);
                iniciativePlus.getLayoutParams().height=0;
            }

            //lock and dodge
            lockanddodgeText = (TextView) rootView.findViewById(R.id.agi_lockanddodge_point);
            lockanddodgeText.setText(ViewBuildActivity.build.getApinblockandesquiva()+"");
            if(!ViewBuildActivity.build.isOnline()) {
                lockanddodgeMinus = (LinearLayout) rootView.findViewById(R.id.agi_lockanddodge_minus);
                lockanddodgeMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!lockanddodgeText.getText().equals("0")) {
                            ViewBuildActivity.build.setApinblockandesquiva(ViewBuildActivity.build.getApinblockandesquiva() - 1);
                            ViewBuildActivity.build.setAgiPoints(ViewBuildActivity.build.getAgiPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            lockanddodgeText.setText(ViewBuildActivity.build.getApinblockandesquiva() + "");
                            if (lockanddodgeText.getText().equals("0")) {
                                lockanddodgeMinus.setEnabled(false);
                            }
                            lockanddodgePlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                lockanddodgePlus = (LinearLayout) rootView.findViewById(R.id.agi_lockanddodge_plus);
                lockanddodgePlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinblockandesquiva(ViewBuildActivity.build.getApinblockandesquiva() + 1);
                            ViewBuildActivity.build.setAgiPoints(ViewBuildActivity.build.getAgiPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            lockanddodgeText.setText(ViewBuildActivity.build.getApinblockandesquiva() + "");
                            lockanddodgeMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (lockanddodgeText.getText().equals("0")) {
                    lockanddodgeMinus.setEnabled(false);
                    lockanddodgePlus.setEnabled(true);
                } else {
                    lockanddodgeMinus.setEnabled(true);
                    lockanddodgePlus.setEnabled(false);
                }
            }else{
                lockanddodgeMinus = (LinearLayout) rootView.findViewById(R.id.agi_lockanddodge_minus);
                lockanddodgeMinus.getLayoutParams().height=0;
                lockanddodgePlus = (LinearLayout) rootView.findViewById(R.id.agi_lockanddodge_plus);
                lockanddodgePlus.getLayoutParams().height=0;
            }

            //ap and mp removal
            apmpremovalText = (TextView) rootView.findViewById(R.id.agi_apmpremoval_point);
            apmpremovalText.setText(ViewBuildActivity.build.getApinremovepaandpm()+"/20");
            if(!ViewBuildActivity.build.isOnline()) {
                apmpremovalMinus = (LinearLayout) rootView.findViewById(R.id.agi_apmpremoval_minus);
                apmpremovalMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!apmpremovalText.getText().equals("0/20")) {
                            ViewBuildActivity.build.setApinremovepaandpm(ViewBuildActivity.build.getApinremovepaandpm() - 1);
                            ViewBuildActivity.build.setAgiPoints(ViewBuildActivity.build.getAgiPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            apmpremovalText.setText(ViewBuildActivity.build.getApinremovepaandpm() + "/20");
                            if (apmpremovalText.getText().equals("0/20")) {
                                apmpremovalMinus.setEnabled(false);
                            }
                            apmpremovalPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                apmpremovalPlus = (LinearLayout) rootView.findViewById(R.id.agi_apmpremoval_plus);
                apmpremovalPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinremovepaandpm(ViewBuildActivity.build.getApinremovepaandpm() + 1);
                            ViewBuildActivity.build.setAgiPoints(ViewBuildActivity.build.getAgiPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            apmpremovalText.setText(ViewBuildActivity.build.getApinremovepaandpm() + "/20");
                            if (apmpremovalText.getText().equals("20/20")) {
                                apmpremovalPlus.setEnabled(false);
                            }
                            apmpremovalMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (apmpremovalText.getText().equals("0/20")) {
                    apmpremovalMinus.setEnabled(false);
                    apmpremovalPlus.setEnabled(true);
                } else {
                    apmpremovalMinus.setEnabled(true);
                    apmpremovalPlus.setEnabled(true);
                }
            }else{
                apmpremovalMinus = (LinearLayout) rootView.findViewById(R.id.agi_apmpremoval_minus);
                apmpremovalMinus.getLayoutParams().height=0;
                apmpremovalPlus = (LinearLayout) rootView.findViewById(R.id.agi_apmpremoval_plus);
                apmpremovalPlus.getLayoutParams().height=0;
            }

            //ap and mp resistance
            apmpresText = (TextView) rootView.findViewById(R.id.agi_apmpres_point);
            apmpresText.setText(ViewBuildActivity.build.getApinremovepaandpm()+"/20");
            if(!ViewBuildActivity.build.isOnline()) {
                apmpresMinus = (LinearLayout) rootView.findViewById(R.id.agi_apmpres_minus);
                apmpresMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!apmpresText.getText().equals("0/20")) {
                            ViewBuildActivity.build.setApinrespmepm(ViewBuildActivity.build.getApinrespmepm() - 1);
                            ViewBuildActivity.build.setAgiPoints(ViewBuildActivity.build.getAgiPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            apmpresText.setText(ViewBuildActivity.build.getApinrespmepm() + "/20");
                            if (apmpresText.getText().equals("0/20")) {
                                apmpresMinus.setEnabled(false);
                            }
                            apmpresPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                apmpresPlus = (LinearLayout) rootView.findViewById(R.id.agi_apmpres_plus);
                apmpresPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinrespmepm(ViewBuildActivity.build.getApinrespmepm() + 1);
                            ViewBuildActivity.build.setAgiPoints(ViewBuildActivity.build.getAgiPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            apmpresText.setText(ViewBuildActivity.build.getApinrespmepm() + "/20");
                            if (apmpresText.getText().equals("20/20")) {
                                apmpresPlus.setEnabled(false);
                            }
                            apmpresMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (apmpresText.getText().equals("0/20")) {
                    apmpresMinus.setEnabled(false);
                    apmpresPlus.setEnabled(true);
                } else {
                    apmpresMinus.setEnabled(true);
                    apmpresPlus.setEnabled(true);
                }
            }else{
                apmpresMinus = (LinearLayout) rootView.findViewById(R.id.agi_apmpres_minus);
                apmpresMinus.getLayoutParams().height=0;
                apmpresPlus = (LinearLayout) rootView.findViewById(R.id.agi_apmpres_plus);
                apmpresPlus.getLayoutParams().height=0;
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
