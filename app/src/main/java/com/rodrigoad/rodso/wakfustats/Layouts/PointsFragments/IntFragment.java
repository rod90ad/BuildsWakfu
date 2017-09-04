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
 * {@link IntFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IntFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IntFragment extends Fragment {
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

    private LinearLayout healthpercentMinus;
    private TextView healthpercentText;
    private LinearLayout healthpercentPlus;

    private LinearLayout eleresMinus;
    private TextView eleresText;
    private LinearLayout eleresPlus;

    private LinearLayout barrierMinus;
    private TextView barrierText;
    private LinearLayout barrierPlus;

    private LinearLayout healsrMinus;
    private TextView healsrText;
    private LinearLayout healsrPlus;

    private LinearLayout armorMinus;
    private TextView armorText;
    private LinearLayout armorPlus;

    private Button save;

    private static ViewBuildFragment viewBuildFragment;

    private OnFragmentInteractionListener mListener;

    public IntFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IntFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IntFragment newInstance(ViewBuildFragment param1, String param2) {
        IntFragment fragment = new IntFragment();
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
            rootView = inflater.inflate(R.layout.fragment_int, container, false);

            tittle = (TextView) rootView.findViewById(R.id.tab_int_tittle);
            tittle.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

            pointToDist = (TextView) rootView.findViewById(R.id.int_points_to_dist);
            pointToDist.setText(""+ ViewBuildActivity.build.getIntPoints());

            //Health Percent
            healthpercentText = (TextView) rootView.findViewById(R.id.int_health_point);
            healthpercentText.setText(ViewBuildActivity.build.getApinlifepercent()+"");
            if(!ViewBuildActivity.build.isOnline()) {
                healthpercentMinus = (LinearLayout) rootView.findViewById(R.id.int_health_minus);
                healthpercentMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!healthpercentText.getText().equals("0")) {
                            ViewBuildActivity.build.setApinlifepercent(ViewBuildActivity.build.getApinlifepercent() - 1);
                            ViewBuildActivity.build.setIntPoints(ViewBuildActivity.build.getIntPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            healthpercentText.setText(ViewBuildActivity.build.getApinlifepercent() + "");
                            if (healthpercentText.getText().equals("0")) {
                                healthpercentMinus.setEnabled(false);
                            }
                            healthpercentPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                healthpercentPlus = (LinearLayout) rootView.findViewById(R.id.int_health_plus);
                healthpercentPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinlifepercent(ViewBuildActivity.build.getApinlifepercent() + 1);
                            ViewBuildActivity.build.setIntPoints(ViewBuildActivity.build.getIntPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            healthpercentText.setText(ViewBuildActivity.build.getApinlifepercent() + "");
                            healthpercentMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (healthpercentText.getText().equals("0")) {
                    healthpercentMinus.setEnabled(false);
                    healthpercentPlus.setEnabled(true);
                } else {
                    healthpercentMinus.setEnabled(true);
                    healthpercentPlus.setEnabled(true);
                }
            }else{
                healthpercentMinus = (LinearLayout) rootView.findViewById(R.id.int_health_minus);
                healthpercentMinus.getLayoutParams().height=0;
                healthpercentPlus = (LinearLayout) rootView.findViewById(R.id.int_health_plus);
                healthpercentPlus.getLayoutParams().height=0;
            }

            //Elemental Resistance
            eleresText = (TextView) rootView.findViewById(R.id.int_eleres_point);
            eleresText.setText(ViewBuildActivity.build.getApinresele()+"/10");
            if(!ViewBuildActivity.build.isOnline()) {
                eleresMinus = (LinearLayout) rootView.findViewById(R.id.int_eleres_minus);
                eleresMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!eleresText.getText().equals("0/10")) {
                            ViewBuildActivity.build.setApinresele(ViewBuildActivity.build.getApinresele() - 1);
                            ViewBuildActivity.build.setIntPoints(ViewBuildActivity.build.getIntPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            eleresText.setText(ViewBuildActivity.build.getApinresele() + "/10");
                            if (eleresText.getText().equals("0/10")) {
                                eleresMinus.setEnabled(false);
                            }
                            eleresPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                eleresPlus = (LinearLayout) rootView.findViewById(R.id.int_eleres_plus);
                eleresPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinresele(ViewBuildActivity.build.getApinresele() + 1);
                            ViewBuildActivity.build.setIntPoints(ViewBuildActivity.build.getIntPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            eleresText.setText(ViewBuildActivity.build.getApinresele() + "/10");
                            if (eleresText.getText().equals("10/10")) {
                                eleresPlus.setEnabled(false);
                            }
                            eleresMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (eleresText.getText().equals("0/10")) {
                    eleresMinus.setEnabled(false);
                    eleresPlus.setEnabled(true);
                } else {
                    eleresMinus.setEnabled(true);
                    eleresPlus.setEnabled(true);
                }
            }else{
                eleresMinus = (LinearLayout) rootView.findViewById(R.id.int_eleres_minus);
                eleresMinus.getLayoutParams().height=0;
                eleresPlus = (LinearLayout) rootView.findViewById(R.id.int_eleres_plus);
                eleresPlus.getLayoutParams().height=0;
            }

            //Barrier
            barrierText = (TextView) rootView.findViewById(R.id.int_barrier_point);
            barrierText.setText(ViewBuildActivity.build.getApinbarreira()+"/10");
            if(!ViewBuildActivity.build.isOnline()) {
                barrierMinus = (LinearLayout) rootView.findViewById(R.id.int_barrier_minus);
                barrierMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!barrierText.getText().equals("0/10")) {
                            ViewBuildActivity.build.setApinbarreira(ViewBuildActivity.build.getApinbarreira() - 1);
                            ViewBuildActivity.build.setIntPoints(ViewBuildActivity.build.getIntPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            barrierText.setText(ViewBuildActivity.build.getApinbarreira() + "/10");
                            if (barrierText.getText().equals("0/10")) {
                                barrierMinus.setEnabled(false);
                            }
                            barrierPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                barrierPlus = (LinearLayout) rootView.findViewById(R.id.int_barrier_plus);
                barrierPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinbarreira(ViewBuildActivity.build.getApinbarreira() + 1);
                            ViewBuildActivity.build.setIntPoints(ViewBuildActivity.build.getIntPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            barrierText.setText(ViewBuildActivity.build.getApinbarreira() + "/10");
                            if (barrierText.getText().equals("10/10")) {
                                barrierPlus.setEnabled(false);
                            }
                            barrierMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (barrierText.getText().equals("0/10")) {
                    barrierMinus.setEnabled(false);
                    barrierPlus.setEnabled(true);
                } else {
                    barrierMinus.setEnabled(true);
                    barrierPlus.setEnabled(true);
                }
            }else{
                barrierMinus = (LinearLayout) rootView.findViewById(R.id.int_barrier_minus);
                barrierMinus.getLayoutParams().height=0;
                barrierPlus = (LinearLayout) rootView.findViewById(R.id.int_barrier_plus);
                barrierPlus.getLayoutParams().height=0;
            }

            //Heal Received
            healsrText = (TextView) rootView.findViewById(R.id.int_heal_point);
            healsrText.setText(ViewBuildActivity.build.getApinhealrecived()+"/20");
            if(!ViewBuildActivity.build.isOnline()) {
                healsrMinus = (LinearLayout) rootView.findViewById(R.id.int_heal_minus);
                healsrMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!healsrText.getText().equals("0/5")) {
                            ViewBuildActivity.build.setApinhealrecived(ViewBuildActivity.build.getApinhealrecived() - 1);
                            ViewBuildActivity.build.setIntPoints(ViewBuildActivity.build.getIntPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            healsrText.setText(ViewBuildActivity.build.getApinhealrecived() + "/5");
                            if (healsrText.getText().equals("0/5")) {
                                healsrMinus.setEnabled(false);
                            }
                            healsrPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                healsrPlus = (LinearLayout) rootView.findViewById(R.id.int_heal_plus);
                healsrPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinhealrecived(ViewBuildActivity.build.getApinhealrecived() + 1);
                            ViewBuildActivity.build.setIntPoints(ViewBuildActivity.build.getIntPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            healsrText.setText(ViewBuildActivity.build.getApinhealrecived() + "/5");
                            if (healsrText.getText().equals("5/5")) {
                                healsrPlus.setEnabled(false);
                            }
                            healsrMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (healsrText.getText().equals("0/5")) {
                    healsrMinus.setEnabled(false);
                    healsrPlus.setEnabled(true);
                } else {
                    healsrMinus.setEnabled(true);
                    healsrPlus.setEnabled(true);
                }
            }else{
                healsrMinus = (LinearLayout) rootView.findViewById(R.id.int_heal_minus);
                healsrMinus.getLayoutParams().height=0;
                healsrPlus = (LinearLayout) rootView.findViewById(R.id.int_heal_plus);
                healsrPlus.getLayoutParams().height=0;
            }

            //Armor
            armorText = (TextView) rootView.findViewById(R.id.int_armorperlife_point);
            armorText.setText(ViewBuildActivity.build.getApinlifearmor()+"/20");
            if(!ViewBuildActivity.build.isOnline()) {
                armorMinus = (LinearLayout) rootView.findViewById(R.id.int_armorperlife_minus);
                armorMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!armorText.getText().equals("0/10")) {
                            ViewBuildActivity.build.setApinlifearmor(ViewBuildActivity.build.getApinlifearmor() - 1);
                            ViewBuildActivity.build.setIntPoints(ViewBuildActivity.build.getIntPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            armorText.setText(ViewBuildActivity.build.getApinlifearmor() + "/10");
                            if (armorText.getText().equals("0/10")) {
                                armorMinus.setEnabled(false);
                            }
                            armorPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                armorPlus = (LinearLayout) rootView.findViewById(R.id.int_armorperlife_plus);
                armorPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinlifearmor(ViewBuildActivity.build.getApinlifearmor() + 1);
                            ViewBuildActivity.build.setIntPoints(ViewBuildActivity.build.getIntPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            armorText.setText(ViewBuildActivity.build.getApinlifearmor() + "/10");
                            if (armorText.getText().equals("10/10")) {
                                armorPlus.setEnabled(false);
                            }
                            armorMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (armorText.getText().equals("0/10")) {
                    armorMinus.setEnabled(false);
                    armorPlus.setEnabled(true);
                } else {
                    armorMinus.setEnabled(true);
                    armorPlus.setEnabled(true);
                }
            }else{
                armorMinus = (LinearLayout) rootView.findViewById(R.id.int_armorperlife_minus);
                armorMinus.getLayoutParams().height=0;
                armorPlus = (LinearLayout) rootView.findViewById(R.id.int_armorperlife_plus);
                armorPlus.getLayoutParams().height=0;
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
