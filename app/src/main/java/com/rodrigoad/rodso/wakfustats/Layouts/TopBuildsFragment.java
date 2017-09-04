package com.rodrigoad.rodso.wakfustats.Layouts;

import android.content.Context;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rodrigoad.rodso.wakfustats.MainActivity;
import com.rodrigoad.rodso.wakfustats.R;
import com.rodrigoad.rodso.wakfustats.Utils.Build;
import com.rodrigoad.rodso.wakfustats.Utils.RVAdapterBuilds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

import static com.rodrigoad.rodso.wakfustats.MainActivity.context;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopBuildsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopBuildsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopBuildsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View rootView;
    private static ValueEventListener valueEventListenerTopBuilds;
    private RelativeLayout rlmain;

    //textView Name of persons
    private TextView Cra;
    private TextView Eca;
    private TextView Elio;
    private TextView Eni;
    private TextView Enu;
    private TextView Feca;
    private TextView Hupp;
    private TextView Iop;
    private TextView Osa;
    private TextView Panda;
    private TextView Lad;
    private TextView Sac;
    private TextView Sad;
    private TextView Sram;
    private TextView Steam;
    private TextView Xelor;
    private TextView Zob;

    //Image view (expland)
    private ImageView ivCra;
    private ImageView ivEca;
    private ImageView ivElio;
    private ImageView ivEni;
    private ImageView ivEnu;
    private ImageView ivFeca;
    private ImageView ivHupp;
    private ImageView ivIop;
    private ImageView ivOsa;
    private ImageView ivPanda;
    private ImageView ivLad;
    private ImageView ivSac;
    private ImageView ivSad;
    private ImageView ivSram;
    private ImageView ivSteam;
    private ImageView ivXelor;
    private ImageView ivZob;

    //textView Number of builds
    private TextView tvCra;
    private TextView tvEca;
    private TextView tvElio;
    private TextView tvEni;
    private TextView tvEnu;
    private TextView tvFeca;
    private TextView tvHupp;
    private TextView tvIop;
    private TextView tvOsa;
    private TextView tvPanda;
    private TextView tvLad;
    private TextView tvSac;
    private TextView tvSad;
    private TextView tvSram;
    private TextView tvSteam;
    private TextView tvXelor;
    private TextView tvZob;

    //Arrays of classes
    private ArrayList<Build> alCra = new ArrayList<Build>();
    private ArrayList<Build> alEca = new ArrayList<Build>();
    private ArrayList<Build> alElio = new ArrayList<Build>();
    private ArrayList<Build> alEni = new ArrayList<Build>();
    private ArrayList<Build> alEnu = new ArrayList<Build>();
    private ArrayList<Build> alFeca = new ArrayList<Build>();
    private ArrayList<Build> alHupp = new ArrayList<Build>();
    private ArrayList<Build> alIop = new ArrayList<Build>();
    private ArrayList<Build> alOsa = new ArrayList<Build>();
    private ArrayList<Build> alPanda = new ArrayList<Build>();
    private ArrayList<Build> alLad = new ArrayList<Build>();
    private ArrayList<Build> alSac = new ArrayList<Build>();
    private ArrayList<Build> alSad = new ArrayList<Build>();
    private ArrayList<Build> alSram = new ArrayList<Build>();
    private ArrayList<Build> alSteam = new ArrayList<Build>();
    private ArrayList<Build> alXelor = new ArrayList<Build>();
    private ArrayList<Build> alZob = new ArrayList<Build>();

    //boleans de mostrando
    private boolean craShowing=false;
    private boolean ecaShowing=false;
    private boolean elioShowing=false;
    private boolean eniShowing=false;
    private boolean enuShowing=false;
    private boolean fecaShowing=false;
    private boolean huppShowing=false;
    private boolean iopShowing=false;
    private boolean osaShowing=false;
    private boolean pandaShowing=false;
    private boolean ladShowing=false;
    private boolean sacShowing=false;
    private boolean sadShowing=false;
    private boolean sramShowing=false;
    private boolean steamShowing=false;
    private boolean xelorShowing=false;
    private boolean zobShowing=false;

    //Recycler view dos persoangens
    private RecyclerView rvCra;
    private RecyclerView rvEca;
    private RecyclerView rvElio;
    private RecyclerView rvEni;
    private RecyclerView rvEnu;
    private RecyclerView rvFeca;
    private RecyclerView rvHupp;
    private RecyclerView rvIop;
    private RecyclerView rvOsa;
    private RecyclerView rvPanda;
    private RecyclerView rvLad;
    private RecyclerView rvSac;
    private RecyclerView rvSad;
    private RecyclerView rvSram;
    private RecyclerView rvSteam;
    private RecyclerView rvXelor;
    private RecyclerView rvZob;

    private OnFragmentInteractionListener mListener;

    public TopBuildsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopBuildsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopBuildsFragment newInstance(String param1, String param2) {
        TopBuildsFragment fragment = new TopBuildsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void RemoveListener(){
        MainActivity.mDatabase.child("TopBuilds").removeEventListener(valueEventListenerTopBuilds);
    }

    public static void SetListener(){
        MainActivity.mDatabase.child("TopBuilds").addValueEventListener(valueEventListenerTopBuilds);
    }

    private void ClearArrays(){
        alCra.clear();
        alEca.clear();
        alElio.clear();
        alEni.clear();
        alEnu.clear();
        alFeca.clear();
        alHupp.clear();
        alIop.clear();
        alOsa.clear();
        alPanda.clear();
        alLad.clear();
        alSac.clear();
        alSad.clear();
        alSram.clear();
        alSteam.clear();
        alXelor.clear();
        alZob.clear();
    }

    public static void expand(final View v) {
        v.measure(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? RecyclerView.LayoutParams.WRAP_CONTENT
                        : (int)(targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(((int)(targetHeight / v.getContext().getResources().getDisplayMetrics().density)) * 2);
        v.startAnimation(a);
    }

    public static void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation()
        {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if(interpolatedTime == 1){
                    v.setVisibility(View.GONE);
                }else{
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration(((int)(initialHeight / v.getContext().getResources().getDisplayMetrics().density)) * 2);
        v.startAnimation(a);
    }

    public static void removeRV(ImageView imageView, RecyclerView rv){
        ViewCompat.animate(imageView)
                .rotation(0)
                .withLayer()
                .setDuration(250)
                .setInterpolator(new OvershootInterpolator(10.0F))
                .start();
        collapse(rv);
    }

    public static void setRV(Context context,ImageView imageView,ArrayList<Build> list, RecyclerView rv){
        ViewCompat.animate(imageView)
                .rotation(45)
                .withLayer()
                .setDuration(250)
                .setInterpolator(new OvershootInterpolator(10.0F))
                .start();
        RVAdapterBuilds adapterBuilds = new RVAdapterBuilds(list ,context);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
        rv.setAdapter(adapterBuilds);
        expand(rv);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView==null) {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_top_builds, container, false);
            if(isOnline()) {

                Cra = (TextView) rootView.findViewById(R.id.topBuilds_cra_name);
                Cra.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Eca = (TextView) rootView.findViewById(R.id.topBuilds_eca_name);
                Eca.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Elio = (TextView) rootView.findViewById(R.id.topBuilds_elio_name);
                Elio.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Eni = (TextView) rootView.findViewById(R.id.topBuilds_eni_name);
                Eni.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Enu = (TextView) rootView.findViewById(R.id.topBuilds_enu_name);
                Enu.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Feca = (TextView) rootView.findViewById(R.id.topBuilds_feca_name);
                Feca.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Hupp = (TextView) rootView.findViewById(R.id.topBuilds_hupp_name);
                Hupp.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Iop = (TextView) rootView.findViewById(R.id.topBuilds_iop_name);
                Iop.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Osa = (TextView) rootView.findViewById(R.id.topBuilds_osa_name);
                Osa.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Panda = (TextView) rootView.findViewById(R.id.topBuilds_panda_name);
                Panda.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Lad = (TextView) rootView.findViewById(R.id.topBuilds_lad_name);
                Lad.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Sac = (TextView) rootView.findViewById(R.id.topBuilds_sac_name);
                Sac.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Sad = (TextView) rootView.findViewById(R.id.topBuilds_sad_name);
                Sad.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Sram = (TextView) rootView.findViewById(R.id.topBuilds_sram_name);
                Sram.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Steam = (TextView) rootView.findViewById(R.id.topBuilds_steam_name);
                Steam.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Xelor = (TextView) rootView.findViewById(R.id.topBuilds_xelor_name);
                Xelor.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));
                Zob = (TextView) rootView.findViewById(R.id.topBuilds_zob_name);
                Zob.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

                valueEventListenerTopBuilds = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        ClearArrays();
                        for(final DataSnapshot data : dataSnapshot.getChildren()){
                            Build build = new Gson().fromJson(data.getValue(String.class),Build.class);
                            build.setFirekey(data.getKey());
                            switch (build.getClasse()){
                                case 0:
                                    alCra.add(build);
                                    break;
                                case 1:
                                    alEca.add(build);
                                    break;
                                case 2:
                                    alElio.add(build);
                                    break;
                                case 3:
                                    alEni.add(build);
                                    break;
                                case 4:
                                    alEnu.add(build);
                                    break;
                                case 5:
                                    alFeca.add(build);
                                    break;
                                case 6:
                                    alHupp.add(build);
                                    break;
                                case 7:
                                    alIop.add(build);
                                    break;
                                case 8:
                                    alOsa.add(build);
                                    break;
                                case 9:
                                    alPanda.add(build);
                                    break;
                                case 10:
                                    alLad.add(build);
                                    break;
                                case 11:
                                    alSac.add(build);
                                    break;
                                case 12:
                                    alSad.add(build);
                                    break;
                                case 13:
                                    alSram.add(build);
                                    break;
                                case 14:
                                    alSteam.add(build);
                                    break;
                                case 15:
                                    alXelor.add(build);
                                    break;
                                case 16:
                                    alZob.add(build);
                                    break;
                            }
                        }
                        //cuida do cra
                        if(alCra.size()>0){
                            tvCra = (TextView) rootView.findViewById(R.id.topBuilds_cra_number);
                            tvCra.setText(""+alCra.size());
                            ivCra = (ImageView) rootView.findViewById(R.id.topBuilds_cra_more);
                            ivCra.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(craShowing){
                                        craShowing=false;
                                        removeRV(ivCra, rvCra);
                                    }else{
                                        craShowing=true;
                                        rvCra = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_cra);
                                        setRV(getContext(), ivCra, alCra, rvCra);
                                    }
                                }
                            });
                            if(craShowing){
                                setRV(getContext(),ivCra,alCra,rvCra);
                            }
                        }else{
                            tvCra = (TextView) rootView.findViewById(R.id.topBuilds_cra_number);
                            tvCra.setText(""+alCra.size());
                            if(craShowing){
                                removeRV(ivCra, rvCra);
                            }
                        }
                        //cuida do Ecaflip
                        if(alEca.size()>0){
                            tvEca = (TextView) rootView.findViewById(R.id.topBuilds_eca_number);
                            tvEca.setText(""+alEca.size());
                            ivEca = (ImageView) rootView.findViewById(R.id.topBuilds_eca_more);
                            ivEca.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(ecaShowing){
                                        ecaShowing=false;
                                        removeRV(ivEca, rvEca);
                                    }else{
                                        ecaShowing=true;
                                        rvEca = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_eca);
                                        setRV(getContext(), ivEca, alEca, rvEca);
                                    }
                                }
                            });
                            if(ecaShowing){
                                setRV(getContext(),ivEca,alEca,rvEca);
                            }
                        }else{
                            tvEca = (TextView) rootView.findViewById(R.id.topBuilds_eca_number);
                            tvEca.setText(""+alEca.size());
                            if(ecaShowing){
                                removeRV(ivEca, rvEca);
                            }
                        }
                        //cuida do Eliotrope
                        if(alElio.size()>0){
                            tvElio = (TextView) rootView.findViewById(R.id.topBuilds_elio_number);
                            tvElio.setText(""+alElio.size());
                            ivElio = (ImageView) rootView.findViewById(R.id.topBuilds_elio_more);
                            ivElio.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(elioShowing){
                                        elioShowing=false;
                                        removeRV(ivElio, rvElio);
                                    }else{
                                        elioShowing=true;
                                        rvElio = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_elio);
                                        setRV(getContext(), ivElio, alElio, rvElio);
                                    }
                                }
                            });
                            if(elioShowing){
                                setRV(getContext(),ivElio,alElio,rvElio);
                            }
                        }else{
                            tvElio = (TextView) rootView.findViewById(R.id.topBuilds_elio_number);
                            tvElio.setText(""+alElio.size());
                            if(elioShowing){
                                removeRV(ivElio, rvElio);
                            }
                        }
                        //cuida do Eni
                        if(alEni.size()>0){
                            tvEni = (TextView) rootView.findViewById(R.id.topBuilds_eni_number);
                            tvEni.setText(""+alEni.size());
                            ivEni = (ImageView) rootView.findViewById(R.id.topBuilds_eni_more);
                            ivEni.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(eniShowing){
                                        eniShowing=false;
                                        removeRV(ivEni, rvEni);
                                    }else{
                                        eniShowing=true;
                                        rvEni = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_eni);
                                        setRV(getContext(), ivEni, alEni, rvEni);
                                    }
                                }
                            });
                            if(eniShowing){
                                setRV(getContext(),ivEni,alEni,rvEni);
                            }
                        }else{
                            tvEni = (TextView) rootView.findViewById(R.id.topBuilds_eni_number);
                            tvEni.setText(""+alEni.size());
                            if(eniShowing){
                                removeRV(ivEni, rvEni);
                            }
                        }
                        //cuida do Enu
                        if(alEnu.size()>0){
                            tvEnu = (TextView) rootView.findViewById(R.id.topBuilds_enu_number);
                            tvEnu.setText(""+alEnu.size());
                            ivEnu = (ImageView) rootView.findViewById(R.id.topBuilds_enu_more);
                            ivEnu.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(enuShowing){
                                        enuShowing=false;
                                        removeRV(ivEnu, rvEnu);
                                    }else{
                                        enuShowing=true;
                                        rvEnu = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_enu);
                                        setRV(getContext(), ivEnu, alEnu, rvEnu);
                                    }
                                }
                            });
                            if(enuShowing){
                                setRV(getContext(),ivEnu,alEnu,rvEnu);
                            }
                        }else{
                            tvEnu = (TextView) rootView.findViewById(R.id.topBuilds_enu_number);
                            tvEnu.setText(""+alEnu.size());
                            if(enuShowing){
                                removeRV(ivEnu, rvEnu);
                            }
                        }
                        //cuida do Feca
                        if(alFeca.size()>0){
                            tvFeca = (TextView) rootView.findViewById(R.id.topBuilds_feca_number);
                            tvFeca.setText(""+alFeca.size());
                            ivFeca = (ImageView) rootView.findViewById(R.id.topBuilds_feca_more);
                            ivFeca.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(fecaShowing){
                                        fecaShowing=false;
                                        removeRV(ivFeca, rvFeca);
                                    }else{
                                        fecaShowing=true;
                                        rvFeca = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_feca);
                                        setRV(getContext(), ivFeca, alFeca, rvFeca);
                                    }
                                }
                            });
                            if(fecaShowing){
                                setRV(getContext(),ivFeca,alFeca,rvFeca);
                            }
                        }else{
                            tvFeca = (TextView) rootView.findViewById(R.id.topBuilds_feca_number);
                            tvFeca.setText(""+alFeca.size());
                            if(fecaShowing){
                                removeRV(ivFeca, rvFeca);
                            }
                        }
                        //cuida do Hupp
                        if(alHupp.size()>0){
                            tvHupp = (TextView) rootView.findViewById(R.id.topBuilds_hupp_number);
                            tvHupp.setText(""+alHupp.size());
                            ivHupp = (ImageView) rootView.findViewById(R.id.topBuilds_hupp_more);
                            ivHupp.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(huppShowing){
                                        huppShowing=false;
                                        removeRV(ivHupp, rvHupp);
                                    }else{
                                        huppShowing=true;
                                        rvHupp = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_hupp);
                                        setRV(getContext(), ivHupp, alHupp, rvHupp);
                                    }
                                }
                            });
                            if(huppShowing){
                                setRV(getContext(),ivHupp,alHupp,rvHupp);
                            }
                        }else{
                            tvHupp = (TextView) rootView.findViewById(R.id.topBuilds_hupp_number);
                            tvHupp.setText(""+alHupp.size());
                            if(huppShowing){
                                removeRV(ivHupp, rvHupp);
                            }
                        }
                        //cuida do Iop
                        if(alIop.size()>0){
                            tvIop = (TextView) rootView.findViewById(R.id.topBuilds_iop_number);
                            tvIop.setText(""+alIop.size());
                            ivIop = (ImageView) rootView.findViewById(R.id.topBuilds_iop_more);
                            ivIop.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(iopShowing){
                                        iopShowing=false;
                                        removeRV(ivIop, rvIop);
                                    }else{
                                        iopShowing=true;
                                        rvIop = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_iop);
                                        setRV(getContext(), ivIop, alIop, rvIop);
                                    }
                                }
                            });
                            if(iopShowing){
                                setRV(getContext(),ivIop,alIop,rvIop);
                            }
                        }else{
                            tvIop = (TextView) rootView.findViewById(R.id.topBuilds_iop_number);
                            tvIop.setText(""+alIop.size());
                            if(iopShowing){
                                removeRV(ivIop, rvIop);
                            }
                        }
                        //cuida do Osa
                        if(alOsa.size()>0){
                            tvOsa = (TextView) rootView.findViewById(R.id.topBuilds_osa_number);
                            tvOsa.setText(""+alOsa.size());
                            ivOsa = (ImageView) rootView.findViewById(R.id.topBuilds_osa_more);
                            ivOsa.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(osaShowing){
                                        osaShowing=false;
                                        removeRV(ivOsa, rvOsa);
                                    }else{
                                        osaShowing=true;
                                        rvOsa = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_osa);
                                        setRV(getContext(), ivOsa, alOsa, rvOsa);
                                    }
                                }
                            });
                            if(osaShowing){
                                setRV(getContext(),ivOsa,alOsa,rvOsa);
                            }
                        }else{
                            tvOsa = (TextView) rootView.findViewById(R.id.topBuilds_osa_number);
                            tvOsa.setText(""+alOsa.size());
                            if(osaShowing){
                                removeRV(ivOsa, rvOsa);
                            }
                        }
                        //cuida do Panda
                        if(alPanda.size()>0){
                            tvPanda = (TextView) rootView.findViewById(R.id.topBuilds_panda_number);
                            tvPanda.setText(""+alPanda.size());
                            ivPanda = (ImageView) rootView.findViewById(R.id.topBuilds_panda_more);
                            ivPanda.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(pandaShowing){
                                        pandaShowing=false;
                                        removeRV(ivPanda, rvPanda);
                                    }else{
                                        pandaShowing=true;
                                        rvPanda = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_panda);
                                        setRV(getContext(), ivPanda, alPanda, rvPanda);
                                    }
                                }
                            });
                            if(pandaShowing){
                                setRV(getContext(),ivPanda,alPanda,rvPanda);
                            }
                        }else{
                            tvPanda = (TextView) rootView.findViewById(R.id.topBuilds_panda_number);
                            tvPanda.setText(""+alPanda.size());
                            if(pandaShowing){
                                removeRV(ivPanda, rvPanda);
                            }
                        }
                        //cuida do lad
                        if(alLad.size()>0){
                            tvLad = (TextView) rootView.findViewById(R.id.topBuilds_lad_number);
                            tvLad.setText(""+alLad.size());
                            ivLad = (ImageView) rootView.findViewById(R.id.topBuilds_lad_more);
                            ivLad.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(ladShowing){
                                        ladShowing=false;
                                        removeRV(ivLad, rvLad);
                                    }else{
                                        ladShowing=true;
                                        rvLad = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_lad);
                                        setRV(getContext(), ivLad, alLad, rvLad);
                                    }
                                }
                            });
                            if(ladShowing){
                                setRV(getContext(),ivLad,alLad,rvLad);
                            }
                        }else{
                            tvLad = (TextView) rootView.findViewById(R.id.topBuilds_lad_number);
                            tvLad.setText(""+alLad.size());
                            if(ladShowing){
                                removeRV(ivLad, rvLad);
                            }
                        }
                        //cuida do Sac
                        if(alSac.size()>0){
                            tvSac = (TextView) rootView.findViewById(R.id.topBuilds_sac_number);
                            tvSac.setText(""+alSac.size());
                            ivSac = (ImageView) rootView.findViewById(R.id.topBuilds_sac_more);
                            ivSac.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(sacShowing){
                                        sacShowing=false;
                                        removeRV(ivSac, rvSac);
                                    }else{
                                        sacShowing=true;
                                        rvSac = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_sac);
                                        setRV(getContext(), ivSac, alSac, rvSac);
                                    }
                                }
                            });
                            if(sacShowing){
                                setRV(getContext(),ivSac,alSac,rvSac);
                            }
                        }else{
                            tvSac = (TextView) rootView.findViewById(R.id.topBuilds_sac_number);
                            tvSac.setText(""+alSac.size());
                            if(sacShowing){
                                removeRV(ivSac, rvSac);
                            }
                        }
                        //cuida do Sadida
                        if(alSad.size()>0){
                            tvSad = (TextView) rootView.findViewById(R.id.topBuilds_sad_number);
                            tvSad.setText(""+alSad.size());
                            ivSad = (ImageView) rootView.findViewById(R.id.topBuilds_sad_more);
                            ivSad.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(sadShowing){
                                        sadShowing=false;
                                        removeRV(ivSad, rvSad);
                                    }else{
                                        sadShowing=true;
                                        rvSad = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_sad);
                                        setRV(getContext(), ivSad, alSad, rvSad);
                                    }
                                }
                            });
                            if(sadShowing){
                                setRV(getContext(),ivSad,alSad,rvSad);
                            }
                        }else{
                            tvSad = (TextView) rootView.findViewById(R.id.topBuilds_sad_number);
                            tvSad.setText(""+alSad.size());
                            if(sadShowing){
                                removeRV(ivSad, rvSad);
                            }
                        }
                        //cuida do Sram
                        if(alSram.size()>0){
                            tvSram = (TextView) rootView.findViewById(R.id.topBuilds_sram_number);
                            tvSram.setText(""+alSram.size());
                            ivSram = (ImageView) rootView.findViewById(R.id.topBuilds_sram_more);
                            ivSram.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(sramShowing){
                                        sramShowing=false;
                                        removeRV(ivSram, rvSram);
                                    }else{
                                        sramShowing=true;
                                        rvSram = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_sram);
                                        setRV(getContext(), ivSram, alSram, rvSram);
                                    }
                                }
                            });
                            if(sramShowing){
                                setRV(getContext(),ivSram,alSram,rvSram);
                            }
                        }else{
                            tvSram = (TextView) rootView.findViewById(R.id.topBuilds_sram_number);
                            tvSram.setText(""+alSram.size());
                            if(sramShowing){
                                removeRV(ivSram, rvSram);
                            }
                        }
                        //cuida do Steamer
                        if(alSteam.size()>0){
                            tvSteam = (TextView) rootView.findViewById(R.id.topBuilds_steam_number);
                            tvSteam.setText(""+alSteam.size());
                            ivSteam = (ImageView) rootView.findViewById(R.id.topBuilds_steam_more);
                            ivSteam.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(steamShowing){
                                        steamShowing=false;
                                        removeRV(ivSteam, rvSteam);
                                    }else{
                                        steamShowing=true;
                                        rvSteam = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_steam);
                                        setRV(getContext(), ivSteam, alSteam, rvSteam);
                                    }
                                }
                            });
                            if(steamShowing){
                                setRV(getContext(),ivSteam,alSteam,rvSteam);
                            }
                        }else{
                            tvSteam = (TextView) rootView.findViewById(R.id.topBuilds_steam_number);
                            tvSteam.setText(""+alSteam.size());
                            if(steamShowing){
                                removeRV(ivSteam, rvSteam);
                            }
                        }
                        //cuida do Xelor
                        if(alXelor.size()>0){
                            tvXelor = (TextView) rootView.findViewById(R.id.topBuilds_xelor_number);
                            tvXelor.setText(""+alXelor.size());
                            ivXelor = (ImageView) rootView.findViewById(R.id.topBuilds_xelor_more);
                            ivXelor.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(xelorShowing){
                                        xelorShowing=false;
                                        removeRV(ivXelor, rvXelor);
                                    }else{
                                        iopShowing=true;
                                        rvXelor = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_xelor);
                                        setRV(getContext(), ivXelor, alXelor, rvXelor);
                                    }
                                }
                            });
                            if(xelorShowing){
                                setRV(getContext(),ivXelor,alXelor,rvXelor);
                            }
                        }else{
                            tvXelor = (TextView) rootView.findViewById(R.id.topBuilds_xelor_number);
                            tvXelor.setText(""+alXelor.size());
                            if(xelorShowing){
                                removeRV(ivXelor, rvXelor);
                            }
                        }
                        //cuida do Zobal
                        if(alZob.size()>0){
                            tvZob = (TextView) rootView.findViewById(R.id.topBuilds_zob_number);
                            tvZob.setText(""+alZob.size());
                            ivZob = (ImageView) rootView.findViewById(R.id.topBuilds_zob_more);
                            ivZob.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(zobShowing){
                                        zobShowing=false;
                                        removeRV(ivZob, rvZob);
                                    }else{
                                        zobShowing=true;
                                        rvZob = (RecyclerView) rootView.findViewById(R.id.topBuilds_rv_zob);
                                        setRV(getContext(), ivZob, alZob, rvZob);
                                    }
                                }
                            });
                            if(zobShowing){
                                setRV(getContext(),ivZob,alZob,rvZob);
                            }
                        }else{
                            tvZob = (TextView) rootView.findViewById(R.id.topBuilds_zob_number);
                            tvZob.setText(""+alZob.size());
                            if(zobShowing){
                                removeRV(ivZob, rvZob);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };

                SetListener();
            }else{
                rlmain = (RelativeLayout) rootView.findViewById(R.id.topBuilds_main);
                rlmain.removeAllViews();

                TextView textView = new TextView(getContext());
                textView.setText(getString(R.string.offline));
                rlmain.addView(textView);
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
