package com.buildswakfu.rodrigo.buildswakfu.Layouts.PointsFragments;

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

import com.buildswakfu.rodrigo.buildswakfu.Layouts.ViewBuildFragment;
import com.buildswakfu.rodrigo.buildswakfu.R;
import com.buildswakfu.rodrigo.buildswakfu.Utils.BD;
import com.buildswakfu.rodrigo.buildswakfu.ViewBuildActivity;

import static com.buildswakfu.rodrigo.buildswakfu.MainActivity.context;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StrFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StrFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StrFragment extends Fragment {
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

    private LinearLayout eleMMinus;
    private TextView eleMText;
    private LinearLayout eleMPlus;

    private LinearLayout singleMMinus;
    private TextView singleMText;
    private LinearLayout singleMPlus;

    private LinearLayout aoeMMinus;
    private TextView aoeMText;
    private LinearLayout aoeMPlus;

    private LinearLayout cacMMinus;
    private TextView cacMText;
    private LinearLayout cacMPlus;

    private LinearLayout distMMinus;
    private TextView distMText;
    private LinearLayout distMPlus;

    private LinearLayout healthMMinus;
    private TextView healthMText;
    private LinearLayout healthMPlus;

    private Button save;

    private static ViewBuildFragment viewBuildFragment;

    private OnFragmentInteractionListener mListener;

    public StrFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StrFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StrFragment newInstance(ViewBuildFragment param1, String param2) {
        StrFragment fragment = new StrFragment();
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
            rootView = inflater.inflate(R.layout.fragment_str, container, false);

            tittle = (TextView) rootView.findViewById(R.id.tab_str_tittle);
            tittle.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

            pointToDist = (TextView) rootView.findViewById(R.id.str_points_to_dist);
            pointToDist.setText(""+ ViewBuildActivity.build.getForcePoints());

            //Elemental Mastery
            eleMText = (TextView) rootView.findViewById(R.id.str_elemaster_point);
            eleMText.setText(ViewBuildActivity.build.getApingeral()+"");
            eleMMinus = (LinearLayout) rootView.findViewById(R.id.str_elemaster_minus);
            eleMMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!eleMText.getText().equals("0")){
                        ViewBuildActivity.build.setApingeral(ViewBuildActivity.build.getApingeral()-1);
                        ViewBuildActivity.build.setForcePoints(ViewBuildActivity.build.getForcePoints()+1);
                        pointToDist.setText(""+(Integer.parseInt(pointToDist.getText()+"")+1));
                        eleMText.setText(ViewBuildActivity.build.getApingeral()+"");
                        if(eleMText.getText().equals("0")) {
                            eleMMinus.setEnabled(false);
                        }
                        eleMPlus.setEnabled(true);
                    }
                }
            });
            eleMPlus = (LinearLayout) rootView.findViewById(R.id.str_elemaster_plus);
            eleMPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Integer.parseInt(""+pointToDist.getText())>0) {
                        ViewBuildActivity.build.setApingeral(ViewBuildActivity.build.getApingeral()+1);
                        ViewBuildActivity.build.setForcePoints(ViewBuildActivity.build.getForcePoints()-1);
                        pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                        eleMText.setText(ViewBuildActivity.build.getApingeral()+"");
                        eleMMinus.setEnabled(true);
                    }
                }
            });
            if(eleMText.getText().equals("0")){
                eleMMinus.setEnabled(false);
                eleMPlus.setEnabled(true);
            }else{
                eleMMinus.setEnabled(true);
                eleMPlus.setEnabled(true);
            }

            //Single Target Mastery
            singleMText = (TextView) rootView.findViewById(R.id.str_single_mastery_point);
            singleMText.setText(ViewBuildActivity.build.getApinalvounico()+"/20");
            singleMMinus = (LinearLayout) rootView.findViewById(R.id.str_single_mastery_minus);
            singleMMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!singleMText.getText().equals("0/20")){
                        ViewBuildActivity.build.setApinalvounico(ViewBuildActivity.build.getApinalvounico()-1);
                        ViewBuildActivity.build.setForcePoints(ViewBuildActivity.build.getForcePoints()+1);
                        pointToDist.setText(""+(Integer.parseInt(pointToDist.getText()+"")+1));
                        singleMText.setText(ViewBuildActivity.build.getApinalvounico()+"/20");
                        if(singleMText.getText().equals("0/20")) {
                            singleMMinus.setEnabled(false);
                        }
                        singleMPlus.setEnabled(true);
                    }
                }
            });
            singleMPlus = (LinearLayout) rootView.findViewById(R.id.str_single_mastery_plus);
            singleMPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Integer.parseInt(""+pointToDist.getText())>0) {
                        ViewBuildActivity.build.setApinalvounico(ViewBuildActivity.build.getApinalvounico()+1);
                        ViewBuildActivity.build.setForcePoints(ViewBuildActivity.build.getForcePoints()-1);
                        pointToDist.setText(""+(Integer.parseInt(pointToDist.getText()+"")-1));
                        singleMText.setText(ViewBuildActivity.build.getApinalvounico()+"/20");
                        if(singleMText.getText().equals("20/20")) {
                            singleMPlus.setEnabled(false);
                        }
                        singleMMinus.setEnabled(true);

                    }
                }
            });
            if(singleMText.getText().equals("0/20")){
                singleMMinus.setEnabled(false);
                singleMPlus.setEnabled(true);
            }else{
                singleMMinus.setEnabled(true);
                singleMPlus.setEnabled(true);
            }

            //Area Mastery
            aoeMText = (TextView) rootView.findViewById(R.id.str_aoe_point);
            aoeMText.setText(ViewBuildActivity.build.getApinzona()+"/20");
            aoeMMinus = (LinearLayout) rootView.findViewById(R.id.str_aoe_minus);
            aoeMMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!aoeMText.getText().equals("0/20")){
                        ViewBuildActivity.build.setApinzona(ViewBuildActivity.build.getApinzona()-1);
                        ViewBuildActivity.build.setForcePoints(ViewBuildActivity.build.getForcePoints()+1);
                        pointToDist.setText(""+(Integer.parseInt(pointToDist.getText()+"")+1));
                        aoeMText.setText(ViewBuildActivity.build.getApinzona()+"/20");
                        if(aoeMText.getText().equals("0/20")) {
                            aoeMMinus.setEnabled(false);
                        }
                        aoeMPlus.setEnabled(true);
                    }
                }
            });
            aoeMPlus = (LinearLayout) rootView.findViewById(R.id.str_aoe_plus);
            aoeMPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Integer.parseInt(""+pointToDist.getText())>0) {
                        ViewBuildActivity.build.setApinzona(ViewBuildActivity.build.getApinzona()+1);
                        ViewBuildActivity.build.setForcePoints(ViewBuildActivity.build.getForcePoints()-1);
                        pointToDist.setText(""+(Integer.parseInt(pointToDist.getText()+"")-1));
                        aoeMText.setText(ViewBuildActivity.build.getApinzona()+"/20");
                        if(aoeMText.getText().equals("20/20")) {
                            aoeMPlus.setEnabled(false);
                        }
                        aoeMMinus.setEnabled(true);

                    }
                }
            });
            if(aoeMText.getText().equals("0/20")){
                aoeMMinus.setEnabled(false);
                aoeMPlus.setEnabled(true);
            }else{
                aoeMMinus.setEnabled(true);
                aoeMPlus.setEnabled(true);
            }

            //Close Combat Mastery
            cacMText = (TextView) rootView.findViewById(R.id.str_cac_point);
            cacMText.setText(ViewBuildActivity.build.getApinremovepaandpm()+"/20");
            cacMMinus = (LinearLayout) rootView.findViewById(R.id.str_cac_minus);
            cacMMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!cacMText.getText().equals("0/20")){
                        ViewBuildActivity.build.setApinCaC(ViewBuildActivity.build.getApinCaC()-1);
                        ViewBuildActivity.build.setForcePoints(ViewBuildActivity.build.getForcePoints()+1);
                        pointToDist.setText(""+(Integer.parseInt(pointToDist.getText()+"")+1));
                        cacMText.setText(ViewBuildActivity.build.getApinCaC()+"/20");
                        if(cacMText.getText().equals("0/20")) {
                            cacMMinus.setEnabled(false);
                        }
                        cacMPlus.setEnabled(true);
                    }
                }
            });
            cacMPlus = (LinearLayout) rootView.findViewById(R.id.str_cac_plus);
            cacMPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Integer.parseInt(""+pointToDist.getText())>0) {
                        ViewBuildActivity.build.setApinCaC(ViewBuildActivity.build.getApinCaC()+1);
                        ViewBuildActivity.build.setForcePoints(ViewBuildActivity.build.getForcePoints()-1);
                        pointToDist.setText(""+(Integer.parseInt(pointToDist.getText()+"")-1));
                        cacMText.setText(ViewBuildActivity.build.getApinCaC()+"/20");
                        if(cacMText.getText().equals("20/20")) {
                            cacMPlus.setEnabled(false);
                        }
                        cacMMinus.setEnabled(true);

                    }
                }
            });
            if(cacMText.getText().equals("0/20")){
                cacMMinus.setEnabled(false);
                cacMPlus.setEnabled(true);
            }else{
                cacMMinus.setEnabled(true);
                cacMPlus.setEnabled(true);
            }

            //Distance Mastery
            distMText = (TextView) rootView.findViewById(R.id.str_distance_point);
            distMText.setText(ViewBuildActivity.build.getApindistance()+"/20");
            distMMinus = (LinearLayout) rootView.findViewById(R.id.str_distance_minus);
            distMMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!distMText.getText().equals("0/20")){
                        ViewBuildActivity.build.setApindistance(ViewBuildActivity.build.getApindistance()-1);
                        ViewBuildActivity.build.setForcePoints(ViewBuildActivity.build.getForcePoints()+1);
                        pointToDist.setText(""+(Integer.parseInt(pointToDist.getText()+"")+1));
                        distMText.setText(ViewBuildActivity.build.getApindistance()+"/20");
                        if(distMText.getText().equals("0/20")) {
                            distMMinus.setEnabled(false);
                        }
                        distMPlus.setEnabled(true);
                    }
                }
            });
            distMPlus = (LinearLayout) rootView.findViewById(R.id.str_distance_plus);
            distMPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Integer.parseInt(""+pointToDist.getText())>0) {
                        ViewBuildActivity.build.setApindistance(ViewBuildActivity.build.getApindistance()+1);
                        ViewBuildActivity.build.setForcePoints(ViewBuildActivity.build.getForcePoints()-1);
                        pointToDist.setText(""+(Integer.parseInt(pointToDist.getText()+"")-1));
                        distMText.setText(ViewBuildActivity.build.getApindistance()+"/20");
                        if(distMText.getText().equals("20/20")) {
                            distMPlus.setEnabled(false);
                        }
                        distMMinus.setEnabled(true);
                    }
                }
            });
            if(distMText.getText().equals("0/20")){
                distMMinus.setEnabled(false);
                distMPlus.setEnabled(true);
            }else{
                distMMinus.setEnabled(true);
                distMPlus.setEnabled(true);
            }

            //Health Points
            healthMText = (TextView) rootView.findViewById(R.id.str_heath_point);
            healthMText.setText(ViewBuildActivity.build.getApinlife()+"");
            healthMMinus = (LinearLayout) rootView.findViewById(R.id.str_heath_minus);
            healthMMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!healthMText.getText().equals("0")){
                        ViewBuildActivity.build.setApinlife(ViewBuildActivity.build.getApinlife()-1);
                        ViewBuildActivity.build.setForcePoints(ViewBuildActivity.build.getForcePoints()+1);
                        pointToDist.setText(""+(Integer.parseInt(pointToDist.getText()+"")+1));
                        healthMText.setText(ViewBuildActivity.build.getApinlife()+"");
                        if(healthMText.getText().equals("0")) {
                            healthMMinus.setEnabled(false);
                        }
                        healthMPlus.setEnabled(true);
                    }
                }
            });
            healthMPlus = (LinearLayout) rootView.findViewById(R.id.str_heath_plus);
            healthMPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Integer.parseInt(""+pointToDist.getText())>0) {
                        ViewBuildActivity.build.setApinlife(ViewBuildActivity.build.getApinlife()+1);
                        ViewBuildActivity.build.setForcePoints(ViewBuildActivity.build.getForcePoints()-1);
                        pointToDist.setText("" + (Integer.parseInt(pointToDist.getText() + "") - 1));
                        healthMText.setText(ViewBuildActivity.build.getApinlife()+"");
                        healthMMinus.setEnabled(true);
                    }
                }
            });
            if(healthMText.getText().equals("0")){
                healthMMinus.setEnabled(false);
                healthMPlus.setEnabled(true);
            }else{
                healthMMinus.setEnabled(true);
                healthMPlus.setEnabled(true);
            }

            //save
            save = (Button) rootView.findViewById(R.id.button_str_save);
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new BD(context).salvapontos(ViewBuildActivity.build);
                    viewBuildFragment.Refresh();
                    Toast.makeText(context, getResources().getString(R.string.pontossalvos), Toast.LENGTH_SHORT).show();
                }
            });
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
