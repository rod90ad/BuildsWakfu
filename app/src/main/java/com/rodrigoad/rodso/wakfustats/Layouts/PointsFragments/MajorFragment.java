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
 * {@link MajorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MajorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MajorFragment extends Fragment {
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

    private LinearLayout apMinus;
    private TextView apText;
    private LinearLayout apPlus;

    private LinearLayout mpMinus;
    private TextView mpText;
    private LinearLayout mpPlus;

    private LinearLayout rangeMinus;
    private TextView rangeText;
    private LinearLayout rangePlus;

    private LinearLayout wakfuMinus;
    private TextView wakfuText;
    private LinearLayout wakfuPlus;

    private LinearLayout controlMinus;
    private TextView controlText;
    private LinearLayout controlPlus;

    private LinearLayout finaldmgMinus;
    private TextView finaldmgText;
    private LinearLayout finaldmgPlus;

    private LinearLayout resmjrMinus;
    private TextView resmjrText;
    private LinearLayout resmjrPlus;

    private Button save;
    private static ViewBuildFragment viewBuildFragment;

    private OnFragmentInteractionListener mListener;

    public MajorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MajorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MajorFragment newInstance(ViewBuildFragment param1, String param2) {
        MajorFragment fragment = new MajorFragment();
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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(rootView==null) {
            rootView = inflater.inflate(R.layout.fragment_major, container, false);

            tittle = (TextView) rootView.findViewById(R.id.tab_major_tittle);
            tittle.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

            pointToDist = (TextView) rootView.findViewById(R.id.major_points_to_dist);
            pointToDist.setText(""+ViewBuildActivity.build.getEspecialPoints());

            //AP
            apText = (TextView) rootView.findViewById(R.id.major_ap_point);
            apText.setText(ViewBuildActivity.build.getApinactionpoint()+"/1");
            if(!ViewBuildActivity.build.isOnline()) {
                apMinus = (LinearLayout) rootView.findViewById(R.id.major_ap_minus);
                apMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (apText.getText().equals("1/1")) {
                            apText.setText("0/1");
                            ViewBuildActivity.build.setApinactionpoint(0);
                            ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            apMinus.setEnabled(false);
                            apPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                apPlus = (LinearLayout) rootView.findViewById(R.id.major_ap_plus);
                apPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            if (apText.getText().equals("0/1")) {
                                apText.setText("1/1");
                                ViewBuildActivity.build.setApinactionpoint(1);
                                ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() - 1);
                                pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                                apPlus.setEnabled(false);
                                apMinus.setEnabled(true);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (apText.getText().equals("0/1")) {
                    apMinus.setEnabled(false);
                    apPlus.setEnabled(true);
                } else {
                    apMinus.setEnabled(true);
                    apPlus.setEnabled(false);
                }
            }else{
                apMinus = (LinearLayout) rootView.findViewById(R.id.major_ap_minus);
                apMinus.getLayoutParams().height=0;
                apPlus = (LinearLayout) rootView.findViewById(R.id.major_ap_plus);
                apPlus.getLayoutParams().height=0;
            }

            //MP
            mpText = (TextView) rootView.findViewById(R.id.major_mp_point);
            mpText.setText(ViewBuildActivity.build.getApinmovepoint()+"/1");
            if(!ViewBuildActivity.build.isOnline()) {
                mpMinus = (LinearLayout) rootView.findViewById(R.id.major_mp_minus);
                mpMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mpText.getText().equals("1/1")) {
                            mpText.setText("0/1");
                            ViewBuildActivity.build.setApinmovepoint(0);
                            ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            mpMinus.setEnabled(false);
                            mpPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                mpPlus = (LinearLayout) rootView.findViewById(R.id.major_mp_plus);
                mpPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            if (mpText.getText().equals("0/1")) {
                                mpText.setText("1/1");
                                ViewBuildActivity.build.setApinmovepoint(1);
                                ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() - 1);
                                pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                                mpPlus.setEnabled(false);
                                mpMinus.setEnabled(true);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (mpText.getText().equals("0/1")) {
                    mpMinus.setEnabled(false);
                    mpPlus.setEnabled(true);
                } else {
                    mpMinus.setEnabled(true);
                    mpPlus.setEnabled(false);
                }
            }else{
                mpMinus = (LinearLayout) rootView.findViewById(R.id.major_mp_minus);
                mpMinus.getLayoutParams().height=0;
                mpPlus = (LinearLayout) rootView.findViewById(R.id.major_mp_plus);
                mpPlus.getLayoutParams().height=0;
            }

            //range and damage
            rangeText = (TextView) rootView.findViewById(R.id.major_range_point);
            rangeText.setText(ViewBuildActivity.build.getApinrangeanddmg()+"/1");
            if(!ViewBuildActivity.build.isOnline()) {
                rangeMinus = (LinearLayout) rootView.findViewById(R.id.major_range_minus);
                rangeMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rangeText.getText().equals("1/1")) {
                            rangeText.setText("0/1");
                            ViewBuildActivity.build.setApinrangeanddmg(0);
                            ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            rangeMinus.setEnabled(false);
                            rangePlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                rangePlus = (LinearLayout) rootView.findViewById(R.id.major_range_plus);
                rangePlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            if (rangeText.getText().equals("0/1")) {
                                rangeText.setText("1/1");
                                ViewBuildActivity.build.setApinrangeanddmg(1);
                                ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() - 1);
                                pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                                rangePlus.setEnabled(false);
                                rangeMinus.setEnabled(true);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (rangeText.getText().equals("0/1")) {
                    rangeMinus.setEnabled(false);
                    rangePlus.setEnabled(true);
                } else {
                    rangeMinus.setEnabled(true);
                    rangePlus.setEnabled(false);
                }
            }else{
                rangeMinus = (LinearLayout) rootView.findViewById(R.id.major_range_minus);
                rangeMinus.getLayoutParams().height=0;
                rangePlus = (LinearLayout) rootView.findViewById(R.id.major_range_plus);
                rangePlus.getLayoutParams().height=0;
            }

            //wakfu
            wakfuText = (TextView) rootView.findViewById(R.id.major_wakfu_point);
            wakfuText.setText(ViewBuildActivity.build.getApinwakfupoint()+"/1");
            if(!ViewBuildActivity.build.isOnline()) {
                wakfuMinus = (LinearLayout) rootView.findViewById(R.id.major_wakfu_minus);
                wakfuMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (wakfuText.getText().equals("1/1")) {
                            wakfuText.setText("0/1");
                            ViewBuildActivity.build.setApinwakfupoint(0);
                            ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            wakfuMinus.setEnabled(false);
                            wakfuPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                wakfuPlus = (LinearLayout) rootView.findViewById(R.id.major_wakfu_plus);
                wakfuPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            if (wakfuText.getText().equals("0/1")) {
                                wakfuText.setText("1/1");
                                ViewBuildActivity.build.setApinwakfupoint(1);
                                ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() - 1);
                                pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                                wakfuPlus.setEnabled(false);
                                wakfuMinus.setEnabled(true);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (wakfuText.getText().equals("0/1")) {
                    wakfuMinus.setEnabled(false);
                    wakfuPlus.setEnabled(true);
                } else {
                    wakfuMinus.setEnabled(true);
                    wakfuPlus.setEnabled(false);
                }
            }else{
                wakfuMinus = (LinearLayout) rootView.findViewById(R.id.major_wakfu_minus);
                wakfuMinus.getLayoutParams().height=0;
                wakfuPlus = (LinearLayout) rootView.findViewById(R.id.major_wakfu_plus);
                wakfuPlus.getLayoutParams().height=0;
            }

            //control and dmg
            controlText = (TextView) rootView.findViewById(R.id.major_control_point);
            controlText.setText(ViewBuildActivity.build.getApincontrolanddmg()+"/1");
            if(!ViewBuildActivity.build.isOnline()) {
                controlMinus = (LinearLayout) rootView.findViewById(R.id.major_control_minus);
                controlMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (controlText.getText().equals("1/1")) {
                            controlText.setText("0/1");
                            ViewBuildActivity.build.setApincontrolanddmg(0);
                            ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            controlMinus.setEnabled(false);
                            controlPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                controlPlus = (LinearLayout) rootView.findViewById(R.id.major_control_plus);
                controlPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            if (controlText.getText().equals("0/1")) {
                                controlText.setText("1/1");
                                ViewBuildActivity.build.setApincontrolanddmg(1);
                                ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() - 1);
                                pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                                controlPlus.setEnabled(false);
                                controlMinus.setEnabled(true);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (controlText.getText().equals("0/1")) {
                    controlMinus.setEnabled(false);
                    controlPlus.setEnabled(true);
                } else {
                    controlMinus.setEnabled(true);
                    controlPlus.setEnabled(false);
                }
            }else{
                controlMinus = (LinearLayout) rootView.findViewById(R.id.major_control_minus);
                controlMinus.getLayoutParams().height=0;
                controlPlus = (LinearLayout) rootView.findViewById(R.id.major_control_plus);
                controlPlus.getLayoutParams().height=0;
            }

            //final dmg
            finaldmgText = (TextView) rootView.findViewById(R.id.major_finaldmg_point);
            finaldmgText.setText(ViewBuildActivity.build.getApinfinalDamage()+"/1");
            if(!ViewBuildActivity.build.isOnline()) {
                finaldmgMinus = (LinearLayout) rootView.findViewById(R.id.major_finaldmg_minus);
                finaldmgMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (finaldmgText.getText().equals("1/1")) {
                            finaldmgText.setText("0/1");
                            ViewBuildActivity.build.setApinfinalDamage(0);
                            ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            finaldmgMinus.setEnabled(false);
                            finaldmgPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                finaldmgPlus = (LinearLayout) rootView.findViewById(R.id.major_finaldmg_plus);
                finaldmgPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            if (finaldmgText.getText().equals("0/1")) {
                                finaldmgText.setText("1/1");
                                ViewBuildActivity.build.setApinfinalDamage(1);
                                ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() - 1);
                                pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                                finaldmgPlus.setEnabled(false);
                                finaldmgMinus.setEnabled(true);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (finaldmgText.getText().equals("0/1")) {
                    finaldmgMinus.setEnabled(false);
                    finaldmgPlus.setEnabled(true);
                } else {
                    finaldmgMinus.setEnabled(true);
                    finaldmgPlus.setEnabled(false);
                }
            }else{
                finaldmgMinus = (LinearLayout) rootView.findViewById(R.id.major_finaldmg_minus);
                finaldmgMinus.getLayoutParams().height=0;
                finaldmgPlus = (LinearLayout) rootView.findViewById(R.id.major_finaldmg_plus);
                finaldmgPlus.getLayoutParams().height=0;
            }

            //res ele mjr
            resmjrText = (TextView) rootView.findViewById(R.id.major_resmjr_point);
            resmjrText.setText(ViewBuildActivity.build.getApinreselemental()+"/1");
            if(!ViewBuildActivity.build.isOnline()) {
                resmjrMinus = (LinearLayout) rootView.findViewById(R.id.major_resmjr_minus);
                resmjrMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (resmjrText.getText().equals("1/1")) {
                            resmjrText.setText("0/1");
                            ViewBuildActivity.build.setApinreselemental(0);
                            ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            resmjrMinus.setEnabled(false);
                            resmjrPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                resmjrPlus = (LinearLayout) rootView.findViewById(R.id.major_resmjr_plus);
                resmjrPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            if (resmjrText.getText().equals("0/1")) {
                                resmjrText.setText("1/1");
                                ViewBuildActivity.build.setApinreselemental(1);
                                ViewBuildActivity.build.setEspecialPoints(ViewBuildActivity.build.getEspecialPoints() - 1);
                                pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                                resmjrPlus.setEnabled(false);
                                resmjrMinus.setEnabled(true);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (resmjrText.getText().equals("0/1")) {
                    resmjrMinus.setEnabled(false);
                    resmjrPlus.setEnabled(true);
                } else {
                    resmjrMinus.setEnabled(true);
                    resmjrPlus.setEnabled(false);
                }
            }else{
                resmjrMinus = (LinearLayout) rootView.findViewById(R.id.major_resmjr_minus);
                resmjrMinus.getLayoutParams().height=0;
                resmjrPlus = (LinearLayout) rootView.findViewById(R.id.major_resmjr_plus);
                resmjrPlus.getLayoutParams().height=0;
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
