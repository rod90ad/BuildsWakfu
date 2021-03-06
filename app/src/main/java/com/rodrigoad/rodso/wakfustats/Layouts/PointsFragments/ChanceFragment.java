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
 * {@link ChanceFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChanceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChanceFragment extends Fragment {
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

    private LinearLayout criticalChanceMinus;
    private TextView criticalChanceText;
    private LinearLayout criticalChancePlus;

    private LinearLayout blockMinus;
    private TextView blockText;
    private LinearLayout blockPlus;

    private LinearLayout criticalDmgMinus;
    private TextView criticalDmgText;
    private LinearLayout criticalDmgPlus;

    private LinearLayout rearMasteryMinus;
    private TextView rearMasteryText;
    private LinearLayout rearMasteryPlus;

    private LinearLayout berserkMasteryMinus;
    private TextView berserkMasteryText;
    private LinearLayout berserkMasteryPlus;

    private LinearLayout healingMasteryMinus;
    private TextView healingMasteryText;
    private LinearLayout healingMasteryPlus;

    private LinearLayout rearResistMinus;
    private TextView rearResistText;
    private LinearLayout rearResistPlus;

    private LinearLayout criticalResistMinus;
    private TextView criticalResistText;
    private LinearLayout criticalResistPlus;

    private Button save;
    private static ViewBuildFragment viewBuildFragment;


    private OnFragmentInteractionListener mListener;

    public ChanceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChanceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChanceFragment newInstance(ViewBuildFragment param1, String param2) {
        ChanceFragment fragment = new ChanceFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        viewBuildFragment = param1;
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
            rootView = inflater.inflate(R.layout.fragment_chance, container, false);

            tittle = (TextView) rootView.findViewById(R.id.tab_chance_tittle);
            tittle.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

            pointToDist = (TextView) rootView.findViewById(R.id.chance_points_to_dist);
            pointToDist.setText(""+ ViewBuildActivity.build.getLuckPoints());

            //Critical Chance
            criticalChanceText = (TextView) rootView.findViewById(R.id.chance_criticalchance_point);
            criticalChanceText.setText(ViewBuildActivity.build.getApingolpecritico()+"/20");
            if(!ViewBuildActivity.build.isOnline()) {
                criticalChanceMinus = (LinearLayout) rootView.findViewById(R.id.chance_criticalchance_minus);
                criticalChanceMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!criticalChanceText.getText().equals("0/20")) {
                            ViewBuildActivity.build.setApingolpecritico(ViewBuildActivity.build.getApingolpecritico() - 1);
                            ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() + 1);
                            criticalChanceText.setText(ViewBuildActivity.build.getApingolpecritico() + "/20");
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            if (criticalChanceText.getText().equals("0/20")) {
                                criticalChanceMinus.setEnabled(false);
                            }
                            criticalChancePlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                criticalChancePlus = (LinearLayout) rootView.findViewById(R.id.chance_criticalchance_plus);
                criticalChancePlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            if (!criticalChanceText.getText().equals("20/20")) {
                                ViewBuildActivity.build.setApingolpecritico(ViewBuildActivity.build.getApingolpecritico() + 1);
                                ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() - 1);
                                criticalChanceText.setText(ViewBuildActivity.build.getApingolpecritico() + "/20");
                                pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                                if (criticalChanceText.getText().equals("20/20")) {
                                    criticalChancePlus.setEnabled(false);
                                }
                                criticalChanceMinus.setEnabled(true);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (criticalChanceText.getText().equals("0/20")) {
                    criticalChanceMinus.setEnabled(false);
                    criticalChancePlus.setEnabled(true);
                } else if (criticalChanceText.getText().equals("20/20")) {
                    criticalChanceMinus.setEnabled(true);
                    criticalChancePlus.setEnabled(false);
                } else {
                    criticalChanceMinus.setEnabled(true);
                    criticalChancePlus.setEnabled(true);
                }
            }else{
                criticalChanceMinus = (LinearLayout) rootView.findViewById(R.id.chance_criticalchance_minus);
                criticalChanceMinus.getLayoutParams().height=0;
                criticalChancePlus = (LinearLayout) rootView.findViewById(R.id.chance_criticalchance_plus);
                criticalChancePlus.getLayoutParams().height=0;
            }

            //block
            blockText = (TextView) rootView.findViewById(R.id.chance_block_point);
            blockText.setText(ViewBuildActivity.build.getApinparada()+"/20");
            if(!ViewBuildActivity.build.isOnline()) {
                blockMinus = (LinearLayout) rootView.findViewById(R.id.chance_block_minus);
                blockMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!blockText.getText().equals("0/20")) {
                            ViewBuildActivity.build.setApinparada(ViewBuildActivity.build.getApinparada() - 1);
                            ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() + 1);
                            blockText.setText(ViewBuildActivity.build.getApinparada() + "/20");
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            if (blockText.getText().equals("0/20")) {
                                blockMinus.setEnabled(false);
                            }
                            blockPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                blockPlus = (LinearLayout) rootView.findViewById(R.id.chance_block_plus);
                blockPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            if (!blockText.getText().equals("20/20")) {
                                ViewBuildActivity.build.setApinparada(ViewBuildActivity.build.getApinparada() + 1);
                                ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() - 1);
                                blockText.setText(ViewBuildActivity.build.getApinparada() + "/20");
                                pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                                if (blockText.getText().equals("20/20")) {
                                    blockPlus.setEnabled(false);
                                }
                                blockMinus.setEnabled(true);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (blockText.getText().equals("0/20")) {
                    blockMinus.setEnabled(false);
                    blockPlus.setEnabled(true);
                } else if (blockText.getText().equals("20/20")) {
                    blockMinus.setEnabled(true);
                    blockPlus.setEnabled(false);
                } else {
                    blockMinus.setEnabled(true);
                    blockPlus.setEnabled(true);
                }
            }else{
                blockMinus = (LinearLayout) rootView.findViewById(R.id.chance_block_minus);
                blockMinus.getLayoutParams().height=0;
                blockPlus = (LinearLayout) rootView.findViewById(R.id.chance_block_plus);
                blockPlus.getLayoutParams().height=0;
            }


            //critical dmg
            criticalDmgText = (TextView) rootView.findViewById(R.id.chance_criticaldmg_point);
            criticalDmgText.setText(ViewBuildActivity.build.getApindanocritico()+"");
            if(!ViewBuildActivity.build.isOnline()) {
                criticalDmgMinus = (LinearLayout) rootView.findViewById(R.id.chance_criticaldmg_minus);
                criticalDmgMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!criticalDmgText.getText().equals("0")) {
                            ViewBuildActivity.build.setApindanocritico(ViewBuildActivity.build.getApindanocritico() - 1);
                            ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() + 1);
                            criticalDmgText.setText(ViewBuildActivity.build.getApindanocritico() + "");
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            if (criticalDmgText.getText().equals("0")) {
                                criticalDmgMinus.setEnabled(false);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                criticalDmgPlus = (LinearLayout) rootView.findViewById(R.id.chance_criticaldmg_plus);
                criticalDmgPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApindanocritico(ViewBuildActivity.build.getApindanocritico() + 1);
                            ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() - 1);
                            criticalDmgText.setText(ViewBuildActivity.build.getApindanocritico() + "");
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            criticalDmgMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (criticalDmgText.getText().equals("0")) {
                    criticalDmgMinus.setEnabled(false);
                    criticalDmgPlus.setEnabled(true);
                } else {
                    criticalDmgMinus.setEnabled(true);
                    criticalDmgPlus.setEnabled(true);
                }
            }else{
                criticalDmgMinus = (LinearLayout) rootView.findViewById(R.id.chance_criticaldmg_minus);
                criticalDmgMinus.getLayoutParams().height=0;
                criticalDmgPlus = (LinearLayout) rootView.findViewById(R.id.chance_criticaldmg_plus);
                criticalDmgPlus.getLayoutParams().height=0;
            }

            //rear dmg
            rearMasteryText = (TextView) rootView.findViewById(R.id.chance_reardmg_point);
            rearMasteryText.setText(ViewBuildActivity.build.getApinbackdmg()+"");
            if(!ViewBuildActivity.build.isOnline()) {
                rearMasteryMinus = (LinearLayout) rootView.findViewById(R.id.chance_reardmg_minus);
                rearMasteryMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!rearMasteryText.getText().equals("0")) {
                            ViewBuildActivity.build.setApinbackdmg(ViewBuildActivity.build.getApinbackdmg() - 1);
                            ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            rearMasteryText.setText(ViewBuildActivity.build.getApinbackdmg() + "");
                            if (rearMasteryText.getText().equals("0")) {
                                rearMasteryMinus.setEnabled(false);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                rearMasteryPlus = (LinearLayout) rootView.findViewById(R.id.chance_reardmg_plus);
                rearMasteryPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinbackdmg(ViewBuildActivity.build.getApinbackdmg() + 1);
                            ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            rearMasteryText.setText(ViewBuildActivity.build.getApinbackdmg() + "");
                            rearMasteryMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (rearMasteryText.getText().equals("0")) {
                    rearMasteryMinus.setEnabled(false);
                    rearMasteryPlus.setEnabled(true);
                } else {
                    rearMasteryMinus.setEnabled(true);
                    rearMasteryPlus.setEnabled(true);
                }
            }else{
                rearMasteryMinus = (LinearLayout) rootView.findViewById(R.id.chance_reardmg_minus);
                rearMasteryMinus.getLayoutParams().height=0;
                rearMasteryPlus = (LinearLayout) rootView.findViewById(R.id.chance_reardmg_plus);
                rearMasteryPlus.getLayoutParams().height=0;
            }

            //beserk dmg
            berserkMasteryText = (TextView) rootView.findViewById(R.id.chance_bsrkdmg_point);
            berserkMasteryText.setText(ViewBuildActivity.build.getApinbeserkdmg()+"");
            if(!ViewBuildActivity.build.isOnline()) {
                berserkMasteryMinus = (LinearLayout) rootView.findViewById(R.id.chance_bsrkdmg_minus);
                berserkMasteryMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!berserkMasteryText.getText().equals("0")) {
                            ViewBuildActivity.build.setApinbeserkdmg(ViewBuildActivity.build.getApinbeserkdmg() - 1);
                            ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            berserkMasteryText.setText(ViewBuildActivity.build.getApinbeserkdmg() + "");
                            if (berserkMasteryText.getText().equals("0")) {
                                berserkMasteryMinus.setEnabled(false);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                berserkMasteryPlus = (LinearLayout) rootView.findViewById(R.id.chance_bsrkdmg_plus);
                berserkMasteryPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinbeserkdmg(ViewBuildActivity.build.getApinbeserkdmg() + 1);
                            ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            berserkMasteryText.setText(ViewBuildActivity.build.getApinbeserkdmg() + "");
                            berserkMasteryMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (berserkMasteryText.getText().equals("0")) {
                    berserkMasteryMinus.setEnabled(false);
                    berserkMasteryPlus.setEnabled(true);
                } else {
                    berserkMasteryMinus.setEnabled(true);
                    berserkMasteryPlus.setEnabled(true);
                }
            }else{
                berserkMasteryMinus = (LinearLayout) rootView.findViewById(R.id.chance_bsrkdmg_minus);
                berserkMasteryMinus.getLayoutParams().height=0;
                berserkMasteryPlus = (LinearLayout) rootView.findViewById(R.id.chance_bsrkdmg_plus);
                berserkMasteryPlus.getLayoutParams().height=0;
            }

            //healing mastery
            healingMasteryText = (TextView) rootView.findViewById(R.id.chance_healingM_point);
            healingMasteryText.setText(ViewBuildActivity.build.getApinheal()+"");
            if(!ViewBuildActivity.build.isOnline()) {
                healingMasteryMinus = (LinearLayout) rootView.findViewById(R.id.chance_healingM_minus);
                healingMasteryMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!healingMasteryText.getText().equals("0")) {
                            ViewBuildActivity.build.setApinheal(ViewBuildActivity.build.getApinheal() - 1);
                            ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() + 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            healingMasteryText.setText(ViewBuildActivity.build.getApinheal() + "");
                            if (healingMasteryText.getText().equals("0")) {
                                healingMasteryMinus.setEnabled(false);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                healingMasteryPlus = (LinearLayout) rootView.findViewById(R.id.chance_healingM_plus);
                healingMasteryPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            ViewBuildActivity.build.setApinheal(ViewBuildActivity.build.getApinheal() + 1);
                            ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() - 1);
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                            healingMasteryText.setText(ViewBuildActivity.build.getApinheal() + "");
                            healingMasteryMinus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (healingMasteryText.getText().equals("0")) {
                    healingMasteryMinus.setEnabled(false);
                    healingMasteryPlus.setEnabled(true);
                } else {
                    healingMasteryMinus.setEnabled(true);
                    healingMasteryPlus.setEnabled(true);
                }
            }else{
                healingMasteryMinus = (LinearLayout) rootView.findViewById(R.id.chance_healingM_minus);
                healingMasteryMinus.getLayoutParams().height=0;
                healingMasteryPlus = (LinearLayout) rootView.findViewById(R.id.chance_healingM_plus);
                healingMasteryPlus.getLayoutParams().height=0;
            }

            //rear resist
            rearResistText = (TextView) rootView.findViewById(R.id.chance_rearR_point);
            rearResistText.setText(ViewBuildActivity.build.getApinresbackdmg()+"/20");
            if(!ViewBuildActivity.build.isOnline()) {
                rearResistMinus = (LinearLayout) rootView.findViewById(R.id.chance_rearR_minus);
                rearResistMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!rearResistText.getText().equals("0/20")) {
                            ViewBuildActivity.build.setApinresbackdmg(ViewBuildActivity.build.getApinresbackdmg() - 1);
                            ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() + 1);
                            rearResistText.setText(ViewBuildActivity.build.getApinresbackdmg() + "/20");
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            if (rearResistText.getText().equals("0/20")) {
                                rearResistMinus.setEnabled(false);
                            }
                            rearResistPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                rearResistPlus = (LinearLayout) rootView.findViewById(R.id.chance_rearR_plus);
                rearResistPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            if (!rearResistText.getText().equals("20/20")) {
                                ViewBuildActivity.build.setApinresbackdmg(ViewBuildActivity.build.getApinresbackdmg() + 1);
                                ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() - 1);
                                rearResistText.setText(ViewBuildActivity.build.getApinresbackdmg() + "/20");
                                pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                                if (rearResistText.getText().equals("20/20")) {
                                    rearResistPlus.setEnabled(false);
                                }
                                rearResistMinus.setEnabled(true);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (rearResistText.getText().equals("0/20")) {
                    rearResistMinus.setEnabled(false);
                    rearResistPlus.setEnabled(true);
                } else if (rearResistText.getText().equals("20/20")) {
                    rearResistMinus.setEnabled(true);
                    rearResistPlus.setEnabled(false);
                } else {
                    rearResistMinus.setEnabled(true);
                    rearResistPlus.setEnabled(true);
                }
            }else{
                rearResistMinus = (LinearLayout) rootView.findViewById(R.id.chance_rearR_minus);
                rearResistMinus.getLayoutParams().height=0;
                rearResistPlus = (LinearLayout) rootView.findViewById(R.id.chance_rearR_plus);
                rearResistPlus.getLayoutParams().height=0;
            }

            //critical resistance
            criticalResistText = (TextView) rootView.findViewById(R.id.chance_criticalR_point);
            criticalResistText.setText(ViewBuildActivity.build.getApinresbackdmg()+"/20");
            if(!ViewBuildActivity.build.isOnline()) {
                criticalResistMinus = (LinearLayout) rootView.findViewById(R.id.chance_criticalR_minus);
                criticalResistMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!criticalResistText.getText().equals("0/20")) {
                            ViewBuildActivity.build.setApincritialres(ViewBuildActivity.build.getApincritialres() - 1);
                            ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() + 1);
                            criticalResistText.setText(ViewBuildActivity.build.getApincritialres() + "/20");
                            pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") + 1));
                            if (criticalResistText.getText().equals("0/20")) {
                                criticalResistMinus.setEnabled(false);
                            }
                            criticalResistPlus.setEnabled(true);
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                criticalResistPlus = (LinearLayout) rootView.findViewById(R.id.chance_criticalR_plus);
                criticalResistPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Integer.parseInt("" + pointToDist.getText()) > 0) {
                            if (!criticalResistText.getText().equals("20/20")) {
                                ViewBuildActivity.build.setApincritialres(ViewBuildActivity.build.getApincritialres() + 1);
                                ViewBuildActivity.build.setLuckPoints(ViewBuildActivity.build.getLuckPoints() - 1);
                                criticalResistText.setText(ViewBuildActivity.build.getApincritialres() + "/20");
                                pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                                if (criticalResistText.getText().equals("20/20")) {
                                    criticalResistPlus.setEnabled(false);
                                }
                                criticalResistMinus.setEnabled(true);
                            }
                        }
                        new BD(getContext()).salvapontos(ViewBuildActivity.build);
                    }
                });
                if (criticalResistText.getText().equals("0/20")) {
                    criticalResistMinus.setEnabled(false);
                    criticalResistPlus.setEnabled(true);
                } else if (criticalResistText.getText().equals("20/20")) {
                    criticalResistMinus.setEnabled(true);
                    criticalResistPlus.setEnabled(false);
                } else {
                    criticalResistMinus.setEnabled(true);
                    criticalResistPlus.setEnabled(true);
                }
            }else{
                criticalResistMinus = (LinearLayout) rootView.findViewById(R.id.chance_criticalR_minus);
                criticalResistMinus.getLayoutParams().height=0;
                criticalResistPlus = (LinearLayout) rootView.findViewById(R.id.chance_criticalR_plus);
                criticalResistPlus.getLayoutParams().height=0;
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
