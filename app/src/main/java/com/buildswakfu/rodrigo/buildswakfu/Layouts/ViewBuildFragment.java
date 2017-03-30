package com.buildswakfu.rodrigo.buildswakfu.Layouts;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.buildswakfu.rodrigo.buildswakfu.AnalyticsApplication;
import com.buildswakfu.rodrigo.buildswakfu.MainActivity;
import com.buildswakfu.rodrigo.buildswakfu.R;
import com.buildswakfu.rodrigo.buildswakfu.Utils.BD;
import com.buildswakfu.rodrigo.buildswakfu.Utils.Item;
import com.buildswakfu.rodrigo.buildswakfu.ViewBuildActivity;
import com.google.android.gms.analytics.Tracker;
import com.squareup.picasso.Picasso;

import static com.buildswakfu.rodrigo.buildswakfu.MainActivity.build;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ViewBuildFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ViewBuildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ViewBuildFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //textos titulos
    private TextView nome;
    private TextView nivel;
    private TextView maestria;
    private TextView batalha;
    private TextView secundario;

    //textos status
    private TextView pv;
    private TextView pa;
    private TextView pm;
    private TextView pw;
    private TextView dmgagua;
    private TextView dmgterra;
    private TextView dmgar;
    private TextView dmgfogo;
    private TextView resagua;
    private TextView resterra;
    private TextView resar;
    private TextView resfogo;
    private TextView dmgpa;
    private TextView dmgpm;
    private TextView respa;
    private TextView respm;
    private TextView danofinal;
    private TextView curasrealizadas;
    private TextView golpescriticos;
    private TextView parada;
    private TextView iniciativa;
    private TextView alcance;
    private TextView esquiva;
    private TextView bloqueio;
    private TextView sabedoria;
    private TextView prospecção;
    private TextView controle;
    private TextView arteequipar;
    private TextView dominiocritico;
    private TextView rescritico;
    private TextView dominiocostas;
    private TextView rescostas;
    private TextView dominiocac;
    private TextView dominiodistancia;
    private TextView dominiounico;
    private TextView dominiozona;
    private TextView dominiocura;
    private TextView dominioberserk;

    //imageview
    private ImageView head;

    //imagens button
    private ImageButton capa;
    private ImageButton elmo;
    private ImageButton drag;
    private ImageButton amu;
    private ImageButton anel1;
    private ImageButton anel2;
    private ImageButton cint;
    private ImageButton msec;
    private ImageButton mmain;
    private ImageButton pei;
    private ImageButton bot;
    private ImageButton ins;
    private ImageButton pet;
    private ImageButton mont;
    private GridLayout gridStats;
    private Button delete;
    private Button share;
    private ViewBuildFragment viewBuildFragment;
    private int reliquia=0;
    private int epico=0;
    private View qrview;
    private Tracker mTracker;
    private View rootView;

    private com.buildswakfu.rodrigo.buildswakfu.Utils.Build build;

    private OnFragmentInteractionListener mListener;

    public ViewBuildFragment() {
        // Required empty public constructor
        viewBuildFragment=this;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ViewBuildFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ViewBuildFragment newInstance(String param1, String param2) {
        ViewBuildFragment fragment = new ViewBuildFragment();
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
        //Get a Tracker (should auto-report)
        ((AnalyticsApplication) this.getActivity().getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
        mTracker = ((AnalyticsApplication) this.getActivity().getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
        this.build = ViewBuildActivity.build;
    }

    public boolean EpicRelicControl(Item item){
        if(item.getRaridade()==6){
            if(reliquia==0) {
                reliquia++;
            } else{
                return true;
            }
        }else if(item.getRaridade()==7){
            if(epico==0) {
                epico++;
            }else{
                return true;
            }
        }
        return false;
    }

    public void setStatus(Item item){
        build.setActionpoint(build.getActionpoint() + item.getActionpoint());
        build.setMovepoint(build.getMovepoint() + item.getMovepoint());
        build.setRange(build.getRange() + item.getRange());
        build.setControl(build.getControl() + item.getControl());
        build.setVitalpoint(build.getVitalpoint() + item.getVitalpoint());
        build.setDmg(build.getDmg() + item.getDmg());
        build.setHeal(build.getHeal() + item.getHeal());
        build.setBlock(build.getBlock() + item.getBlock());
        build.setBeserkdmg(build.getBeserkdmg() + item.getBeserkdmg());
        build.setCriticalchance(build.getCriticalchance() + item.getCriticalchance());
        build.setBackdmg(build.getBackdmg() + item.getBackdmg());
        build.setDistancedmg(build.getDistancedmg() + item.getDistancedmg());
        build.setClosecombatdmg(build.getClosecombatdmg() + item.getClosecombatdmg());
        build.setElement1dmg(build.getElement1dmg() + item.getElement1dmg());
        build.setElement2dmg(build.getElement2dmg() + item.getElement2dmg());
        build.setElement3dmg(build.getElement3dmg() + item.getElement3dmg());
        build.setAreadmg(build.getAreadmg() + item.getAreadmg());
        build.setOnetargetdmg(build.getOnetargetdmg() + item.getOnetargetdmg());
        build.setEvasion(build.getEvasion() + item.getEvasion());
        build.setCriticaldmg(build.getCriticaldmg() + item.getCriticaldmg());
        build.setIniciative(build.getIniciative() + item.getIniciative());
        build.setStop(build.getStop() + item.getStop());
        build.setProsp(build.getProsp() + item.getProsp());
        build.setPwmax(build.getPwmax() + item.getPwmax());
        build.setResist(build.getResist() + item.getResist());
        build.setSabedoria(build.getSabedoria() + item.getSabedoria());
        build.setReswater(build.getReswater() + item.getReswater());
        build.setResfire(build.getResfire() + item.getResfire());
        build.setResair(build.getResair() + item.getResair());
        build.setResearth(build.getResearth() + item.getResearth());
        build.setResblackdmg(build.getResblackdmg() + item.getResblackdmg());
        build.setRescriticaldmg(build.getRescriticaldmg() + item.getRescriticaldmg());
        build.setRes1elerandom(build.getRes1elerandom() + item.getRes1elerandom());
        build.setRes2elerandom(build.getRes2elerandom() + item.getRes2elerandom());
        build.setRes3elerandom(build.getRes3elerandom() + item.getRes3elerandom());
        build.setWakfupoint(build.getWakfupoint() + item.getWakfupoint());
        build.setStatus(item.getStatus());
        build.setPvphp(build.getPvphp() + item.getPvphp());
        build.setArteequipar(build.getArteequipar() + item.getArteequipar());
        build.setNvfagua(build.getNvfagua() + item.getNvfagua());
        build.setNvfar(build.getNvfar() + item.getNvfar());
        build.setNvffogo(build.getNvffogo() + item.getNvffogo());
        build.setNvfterra(build.getNvfterra() + item.getNvfterra());
        build.setNvfgeral(build.getNvfgeral() + item.getNvfgeral());
        build.setMovespeed(build.getMovespeed() + item.getMovespeed());
        build.setRespm(build.getRespm() + item.getRespm());
        build.setRespa(build.getRespa() + item.getRespa());
        build.setDmgfire(build.getDmgfire() + item.getDmgfogo());
        build.setDmgwater(build.getDmgwater() + item.getDmgagua());
        build.setDmgearth(build.getDmgearth() + item.getDmgterra());
        build.setDmgair(build.getDmgair() + item.getDmgar());
        build.setMinertake(build.getMinertake() + item.getMinertake());
    }

    private class onImageClickListener implements View.OnClickListener {

        Item item;

        public onImageClickListener(Item item){
            this.item =item;
        }

        @Override
        public void onClick(final View v) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.tiraitem)
                    .setMessage(getResources().getString(R.string.remover) + " " + item.getNome()+ "?")
                    .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (item.getTipo()) {
                                case "amu":
                                    build.setAmuleto(new Item(), v.getContext());
                                    break;
                                case "anel":
                                    if (build.getAnel1().getLink() == item.getLink()) {
                                        build.setAnel1(new Item(), v.getContext());
                                    } else {
                                        build.setAnel2(new Item(), v.getContext());
                                    }
                                    break;
                                case "w1h":
                                    build.setArmamain(new Item(), v.getContext());
                                    break;
                                case "w2h":
                                    build.setArmamain(new Item(), v.getContext());
                                    break;
                                case "wsec":
                                    build.setArmasec(new Item(), v.getContext());
                                    break;
                                case "cap":
                                    build.setCapa(new Item(), v.getContext());
                                    break;
                                case "cint":
                                    build.setCinto(new Item(), v.getContext());
                                    break;
                                case "elm":
                                    build.setElmo(new Item(), v.getContext());
                                    break;
                                case "drag":
                                    build.setDragona(new Item(), v.getContext());
                                    break;
                                case "pet":
                                    build.setPet(new Item(), v.getContext());
                                    break;
                                case "pei":
                                    build.setPeitoral(new Item(), v.getContext());
                                    break;
                                case "bot":
                                    build.setBota(new Item(), v.getContext());
                                    break;
                                case "ins":
                                    build.setInsignia(new Item(), v.getContext());
                                    break;
                                case "mont":
                                    build.setMontaria(new Item(), v.getContext());
                                    break;
                            }
                            Toast.makeText(v.getContext(), getResources().getString(R.string.itemremove) + " " + build.getNome(), Toast.LENGTH_LONG).show();
                            build = (new BD(v.getContext()).getBuild(build.getCodigo()));
                            FragmentTransaction ft = getFragmentManager().beginTransaction();
                            ft.detach(viewBuildFragment).attach(viewBuildFragment).commit();
                        }

                    })
                    .setNegativeButton(R.string.nao, null);
            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    public void imageDone(ImageButton imageButton){
        int screen_width = Resources.getSystem().getDisplayMetrics().widthPixels;
        /*GridLayout.LayoutParams images_size = (GridLayout.LayoutParams) imageButton.getLayoutParams();
        images_size.width=screen_width/7;
        images_size.height=screen_width/7;
        imageButton.setLayoutParams(images_size);*/
        if(imageButton==capa) {
            if (!build.getCapa().equals(null)) {
                setStatus(build.getCapa());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getCapa().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getCapa())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getCapa()));
            }
        }
        if(imageButton==elmo){
            if (!build.getElmo().equals(null)) {
                setStatus(build.getElmo());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getElmo().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getElmo())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getElmo()));
            }
        }
        if(imageButton==drag){
            if (!build.getDragona().equals(null)) {
                setStatus(build.getDragona());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getDragona().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getDragona())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getDragona()));
            }
        }
        if(imageButton==amu){
            if (!build.getAmuleto().equals(null)) {
                setStatus(build.getAmuleto());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getAmuleto().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getAmuleto())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getAmuleto()));
            }
        }
        if(imageButton==anel1){
            if (!build.getAnel1().equals(null)) {
                setStatus(build.getAnel1());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getAnel1().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getAnel1())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getAnel1()));
            }
        }
        if(imageButton==anel2){
            if (!build.getAnel2().equals(null)) {
                setStatus(build.getAnel2());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getAnel2().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getAnel2())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getAnel2()));
            }
        }
        if(imageButton==cint){
            if (!build.getCinto().equals(null)) {
                setStatus(build.getCinto());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getCinto().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getCinto())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getCinto()));
            }
        }
        if(imageButton==msec){
            if (!build.getArmasec().equals(null)) {
                setStatus(build.getArmasec());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getArmasec().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getArmasec())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getArmasec()));
            }
        }
        if(imageButton==mmain){
            if (!build.getArmamain().equals(null)) {
                setStatus(build.getArmamain());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getArmamain().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getArmamain())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getArmamain()));
            }
        }
        if(imageButton==pei){
            if (!build.getPeitoral().equals(null)) {
                setStatus(build.getPeitoral());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getPeitoral().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getPeitoral())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getPeitoral()));
            }
        }
        if(imageButton==bot){
            if (!build.getBota().equals(null)) {
                setStatus(build.getBota());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getBota().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getBota())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getBota()));
            }
        }
        if(imageButton==ins){
            if (!build.getInsignia().equals(null)) {
                setStatus(build.getInsignia());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getInsignia().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getInsignia())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getInsignia()));
            }
        }
        if(imageButton==pet){
            if (!build.getPet().equals(null)) {
                setStatus(build.getPet());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getPet().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getPet())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getPet()));
            }
        }
        if(imageButton==mont){
            if (!build.getMontaria().equals(null)) {
                setStatus(build.getMontaria());
                Picasso.with(imageButton.getContext()).load("http://staticns.ankama.com/wakfu/portal/game/item/115/" + build.getMontaria().getLink() + ".png").into(imageButton);
                if(EpicRelicControl(build.getMontaria())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                imageButton.setOnClickListener(new onImageClickListener(build.getMontaria()));
            }
        }
    }

    public void showQR(){
        qrview = new View(getContext());
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setView(qrview);
        builder.setTitle("QRCode");
        builder.setInverseBackgroundForced(true);
        builder.setPositiveButton("Accept",new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Reject",new DialogInterface.OnClickListener()
        {

            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        AlertDialog alert=builder.create();
        alert.show();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void updateQR(BitmapDrawable bmp){
        qrview.setBackground(bmp);
    }

    private int getDano(int tipo){
        int aux=0;
        int ele = build.getElementp();
        switch (tipo) {
            case 1: //agua
                if(ele!=3 && ele!=4 && ele!=13 && ele!=16 && ele!=18 && ele!=23) { //verifica tri
                    aux+= build.getElement3dmg();
                }
                if(ele==0 || ele==1 || ele==14 || ele==15 || ele==20 || ele==21 || (ele>=6 && ele<=11)){   //verifica bi
                    aux+= build.getElement2dmg();
                }
                if(ele>=6 && ele<=11){                                              //verifica unico
                    aux+= build.getElement1dmg();
                }
                break;
            case 2: //terra
                if(ele!=0 && ele!=2 && ele!=6 && ele!=8 && ele!=12 && ele!=14) { //verifica tri
                    aux+= build.getElement3dmg();
                }
                if(ele==4 || ele==5 || ele==10 || ele==11 || ele==16 || ele==17 || (ele>=18 && ele<=23)){   //verifica bi
                    aux+= build.getElement2dmg();
                }
                if(ele>=18 && ele<=23){                                              //verifica unico
                    aux+= build.getElement1dmg();
                }
                break;
            case 3: //ar
                if(ele!=1 && ele!=5 && ele!=7 && ele!=11 && ele!=19 && ele!=21) { //verifica tri
                    aux+= build.getElement3dmg();
                }
                if(ele==2 || ele==3 || ele==8 || ele==9 || ele==22 || ele==23 || (ele>=12 && ele<=17)){   //verifica bi
                    aux+= build.getElement2dmg();
                }
                if(ele>=12 && ele<=17){                                              //verifica unico
                    aux+= build.getElement1dmg();
                }
                break;
            case 4: //fogo
                if(ele!=9 && ele!=10 && ele!=15 && ele!=17 && ele!=20 && ele!=22) { //verifica tri
                    aux+= build.getElement3dmg();
                }
                if(ele==6 || ele==7 || ele==12 || ele==13 || ele==18 || ele==19 || (ele>=0 && ele<=5)){   //verifica bi
                    aux+= build.getElement2dmg();
                }
                if(ele>=0 && ele<=5){                                             //verifica unico
                    aux+= build.getElement1dmg();
                }
                break;
        }
        return aux;
    }

    private int getResist(int tipo){
        int aux=0;
        int ele = build.getResistp();
        switch (tipo) {
            case 1: //agua
                if(ele!=3 && ele!=4 && ele!=13 && ele!=16 && ele!=18 && ele!=23) { //verifica tri
                    aux+= build.getRes3elerandom();
                }
                if(ele==0 || ele==1 || ele==14 || ele==15 || ele==20 || ele==21 || (ele>=6 && ele<=11)){   //verifica bi
                    aux+= build.getRes2elerandom();
                }
                if(ele>=6 && ele<=11){                                              //verifica unico
                    aux+= build.getRes1elerandom();
                }
                break;
            case 2: //terra
                if(ele!=0 && ele!=2 && ele!=6 && ele!=8 && ele!=12 && ele!=14) { //verifica tri
                    aux+= build.getRes3elerandom();
                }
                if(ele==4 || ele==5 || ele==10 || ele==11 || ele==16 || ele==17 || (ele>=18 && ele<=23)){   //verifica bi
                    aux+= build.getRes2elerandom();
                }
                if(ele>=18 && ele<=23){                                              //verifica unico
                    aux+= build.getRes1elerandom();
                }
                break;
            case 3: //ar
                if(ele!=1 && ele!=5 && ele!=7 && ele!=11 && ele!=19 && ele!=21) { //verifica tri
                    aux+= build.getRes3elerandom();
                }
                if(ele==2 || ele==3 || ele==8 || ele==9 || ele==22 || ele==23 || (ele>=12 && ele<=17)){   //verifica bi
                    aux+= build.getRes2elerandom();
                }
                if(ele>=12 && ele<=17){                                              //verifica unico
                    aux+= build.getRes1elerandom();
                }
                break;
            case 4: //fogo
                if(ele!=9 && ele!=10 && ele!=15 && ele!=17 && ele!=20 && ele!=22) { //verifica tri
                    aux+= build.getRes3elerandom();
                }
                if(ele==6 || ele==7 || ele==12 || ele==13 || ele==18 || ele==19 || (ele>=0 && ele<=5)){   //verifica bi
                    aux+= build.getRes2elerandom();
                }
                if(ele>=0 && ele<=5){                                             //verifica unico
                    aux+= build.getRes1elerandom();
                }
                break;
        }
        return aux;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        if(rootView==null) {
            rootView = inflater.inflate(R.layout.fragment_view_build, container, false);
            final Context context = rootView.getContext();
            RelativeLayout.LayoutParams lpText = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lpText.addRule(RelativeLayout.ALIGN_LEFT);

            this.build = ViewBuildActivity.build;

            reliquia = 0;
            epico = 0;

            nome = (TextView) rootView.findViewById(R.id.view_nome);
            nome.setText(build.getNome());
            nome.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

            nivel = (TextView) rootView.findViewById(R.id.view_nivel);
            nivel.setText(getResources().getString(R.string.view_nivel) + build.getNivel());
            nivel.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

            maestria = (TextView) rootView.findViewById(R.id.view_titulo1_text);
            maestria.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

            batalha = (TextView) rootView.findViewById(R.id.view_titulo2_text);
            batalha.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

            secundario = (TextView) rootView.findViewById(R.id.view_titulo3_text);
            secundario.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/namefont.ttf"));

            head = (ImageView) rootView.findViewById(R.id.view_head);
            switch (build.getClasse()) {
                case 0:
                    head.setBackground(getResources().getDrawable(R.drawable.cra_head));
                    break;
                case 1:
                    head.setBackground(getResources().getDrawable(R.drawable.eca_head));
                    break;
                case 2:
                    head.setBackground(getResources().getDrawable(R.drawable.elio_head));
                    break;
                case 3:
                    head.setBackground(getResources().getDrawable(R.drawable.eni_head));
                    break;
                case 4:
                    head.setBackground(getResources().getDrawable(R.drawable.enu_head));
                    break;
                case 5:
                    head.setBackground(getResources().getDrawable(R.drawable.feca_head));
                    break;
                case 6:
                    head.setBackground(getResources().getDrawable(R.drawable.hupp_head));
                    break;
                case 7:
                    head.setBackground(getResources().getDrawable(R.drawable.iop_head));
                    break;
                case 8:
                    head.setBackground(getResources().getDrawable(R.drawable.osa_head));
                    break;
                case 9:
                    head.setBackground(getResources().getDrawable(R.drawable.panda_head));
                    break;
                case 10:
                    head.setBackground(getResources().getDrawable(R.drawable.lad_head));
                    break;
                case 11:
                    head.setBackground(getResources().getDrawable(R.drawable.sac_head));
                    break;
                case 12:
                    head.setBackground(getResources().getDrawable(R.drawable.sad_head));
                    break;
                case 13:
                    head.setBackground(getResources().getDrawable(R.drawable.sram_head));
                    break;
                case 14:
                    head.setBackground(getResources().getDrawable(R.drawable.steam_head));
                    break;
                case 15:
                    head.setBackground(getResources().getDrawable(R.drawable.xelor_head));
                    break;
                case 16:
                    head.setBackground(getResources().getDrawable(R.drawable.zob_head));
                    break;
            }

            //cuida da capa
            capa = (ImageButton) rootView.findViewById(R.id.capa);
            imageDone(capa);

            //cuida do elmo
            elmo = (ImageButton) rootView.findViewById(R.id.elmo);
            imageDone(elmo);

            drag = (ImageButton) rootView.findViewById(R.id.drag);
            imageDone(drag);

            amu = (ImageButton) rootView.findViewById(R.id.amu);
            imageDone(amu);

            anel1 = (ImageButton) rootView.findViewById(R.id.anel1);
            imageDone(anel1);

            anel2 = (ImageButton) rootView.findViewById(R.id.anel2);
            imageDone(anel2);

            cint = (ImageButton) rootView.findViewById(R.id.cint);
            imageDone(cint);

            msec = (ImageButton) rootView.findViewById(R.id.wsec);
            imageDone(msec);

            mmain = (ImageButton) rootView.findViewById(R.id.wmain);
            imageDone(mmain);

            pei = (ImageButton) rootView.findViewById(R.id.pei);
            imageDone(pei);

            bot = (ImageButton) rootView.findViewById(R.id.bota);
            imageDone(bot);

            ins = (ImageButton) rootView.findViewById(R.id.ins);
            imageDone(ins);

            pet = (ImageButton) rootView.findViewById(R.id.pet);
            imageDone(pet);

            mont = (ImageButton) rootView.findViewById(R.id.mont);
            imageDone(mont);

            delete = (Button) rootView.findViewById(R.id.deletebutton);
        /*delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builderelm = new AlertDialog.Builder(v.getContext());
                builderelm.setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle(R.string.deletebuild)
                        .setMessage(getResources().getString(R.string.deletemsg))
                        .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                MainActivity.removerBuild(ft);
                                Toast.makeText(v.getContext(), getResources().getString(R.string.deletebuildsucess), Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(R.string.nao, null);
                AlertDialog alertelm = builderelm.create();
                alertelm.show();
            }
        });*/

            share = (Button) rootView.findViewById(R.id.view_share);
        /*share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                MainActivity.shareBuild(ft);
            }
        });*/

            //coloca os status

            pv = (TextView) rootView.findViewById(R.id.view_pv);
            int vidasemporcentagem = (build.getVitalpoint() + (build.getApinlife() * 20));
            double porcentagem = 1.0 + ((build.getApinlifepercent() * 4.0) / 100);
            double vida = vidasemporcentagem * porcentagem;
            pv.setText(String.format("%.0f", vida));

            pa = (TextView) rootView.findViewById(R.id.view_pa);
            pa.setText("" + (6 + build.getActionpoint() + build.getApinactionpoint()));

            pm = (TextView) rootView.findViewById(R.id.view_pm);
            pm.setText("" + (3 + build.getMovepoint() + build.getApinmovepoint()));

            pw = (TextView) rootView.findViewById(R.id.view_pw);
            pw.setText("" + (6 + build.getWakfupoint() + (build.getApinwakfupoint() * 2)));

            //aplica os danos
            dmgagua = (TextView) rootView.findViewById(R.id.view_dmg_water);
            //dano + dmg geral                  +   dmg agua                    +           20 do pm                      +         40 do alcance                       +           40 do controle
            dmgagua.setText((getDano(1) + build.getDmg() + build.getDmgwater() + (build.getApinmovepoint() * 20) + (build.getApinrangeanddmg() * 40) + (build.getApincontrolanddmg() * 40)) + "");

            dmgterra = (TextView) rootView.findViewById(R.id.view_dmg_terra);
            dmgterra.setText((getDano(2) + build.getDmg() + build.getDmgearth() + (build.getApinmovepoint() * 20) + (build.getApinrangeanddmg() * 40) + (build.getApincontrolanddmg() * 40)) + "");

            dmgar = (TextView) rootView.findViewById(R.id.view_dmg_ar);
            dmgar.setText((getDano(3) + build.getDmg() + build.getDmgair() + (build.getApinmovepoint() * 20) + (build.getApinrangeanddmg() * 40) + (build.getApincontrolanddmg() * 40)) + "");

            dmgfogo = (TextView) rootView.findViewById(R.id.view_dmg_fogo);
            dmgfogo.setText((getDano(4) + build.getDmg() + build.getDmgfire() + (build.getApinmovepoint() * 20) + (build.getApinrangeanddmg() * 40) + (build.getApincontrolanddmg() * 40)) + "");

            //aplica as resistencias
            resagua = (TextView) rootView.findViewById(R.id.view_res_water);
            resagua.setText((getResist(1) + build.getResist() + build.getReswater()) + (build.getApinreselemental() == 1 ? 50 : 0) + "");

            resterra = (TextView) rootView.findViewById(R.id.view_res_terra);
            resterra.setText((getResist(2) + build.getResist() + build.getResearth()) + (build.getApinreselemental() == 1 ? 50 : 0) + "");

            resar = (TextView) rootView.findViewById(R.id.view_res_ar);
            resar.setText((getResist(3) + build.getResist() + build.getResair()) + (build.getApinreselemental() == 1 ? 50 : 0) + "");

            resfogo = (TextView) rootView.findViewById(R.id.view_res_fogo);
            resfogo.setText((getResist(4) + build.getResist() + build.getResfire()) + (build.getApinreselemental() == 1 ? 50 : 0) + "");

            dmgpa = (TextView) rootView.findViewById(R.id.view_dmg_pa);
            dmgpa.setText((build.getApinremovepaandpm() > 0 ? build.getApinremovepaandpm() * 2 : 0) + "%");

            dmgpm = (TextView) rootView.findViewById(R.id.view_dmg_pm);
            dmgpm.setText((build.getApinremovepaandpm() > 0 ? build.getApinremovepaandpm() * 2 : 0) + "%");

            respa = (TextView) rootView.findViewById(R.id.view_res_pa);
            respa.setText(build.getRespa() + (build.getApinrespmepm() > 0 ? build.getApinrespmepm() * 2 : 0) + "%");

            respm = (TextView) rootView.findViewById(R.id.view_res_pm);
            respm.setText(build.getRespm() + (build.getApinrespmepm() > 0 ? build.getApinrespmepm() * 2 : 0) + "%");

            danofinal = (TextView) rootView.findViewById(R.id.view_danofinal);
            danofinal.setText(build.getApinfinalDamage() + "%");

            curasrealizadas = (TextView) rootView.findViewById(R.id.view_curas);
            curasrealizadas.setText("0%");

            golpescriticos = (TextView) rootView.findViewById(R.id.view_golpecritico);
            golpescriticos.setText((build.getCriticalchance() + (build.getApingolpecritico() * 1)) + "%");

            parada = (TextView) rootView.findViewById(R.id.view_parada);
            parada.setText((build.getStop() + (build.getApinparada() * 1)) + "%");

            iniciativa = (TextView) rootView.findViewById(R.id.view_iniciativa);
            iniciativa.setText((build.getIniciative() + (build.getApininiciativa() * 4)) + "");

            alcance = (TextView) rootView.findViewById(R.id.view_alcance);
            alcance.setText((build.getRange() + (build.getApinrangeanddmg() * 1)) + "");

            esquiva = (TextView) rootView.findViewById(R.id.view_esquiva);
            esquiva.setText((build.getEvasion() + (build.getApinesquiva() * 6) + (build.getApinblockandesquiva() * 4)) + "");

            bloqueio = (TextView) rootView.findViewById(R.id.view_bloqueio);
            bloqueio.setText((build.getBlock() + (build.getApinblock() * 6) + (build.getApinblockandesquiva() * 4)) + "");

            sabedoria = (TextView) rootView.findViewById(R.id.view_sabedoria);
            sabedoria.setText(build.getSabedoria() + "");

            prospecção = (TextView) rootView.findViewById(R.id.view_prospecção);
            prospecção.setText(build.getProsp() + "");

            controle = (TextView) rootView.findViewById(R.id.view_controle);
            controle.setText((build.getControl() + (build.getApincontrolanddmg() * 2)) + "");

            arteequipar = (TextView) rootView.findViewById(R.id.view_arteequipar);
            arteequipar.setText(build.getArteequipar() + "");

            dominiocritico = (TextView) rootView.findViewById(R.id.view_danocritico);
            dominiocritico.setText((build.getCriticaldmg() + (build.getApindanocritico() * 4)) + "");

            rescritico = (TextView) rootView.findViewById(R.id.view_rescritico);
            rescritico.setText((build.getRescriticaldmg() + (build.getApincritialres() * 4)) + "");

            dominiocostas = (TextView) rootView.findViewById(R.id.view_dominiocostas);
            dominiocostas.setText((build.getBackdmg() + (build.getApinbackdmg() * 6)) + "");

            rescostas = (TextView) rootView.findViewById(R.id.view_rescostas);
            rescostas.setText((build.getResblackdmg() + (build.getApinresbackdmg() * 4)) + "");

            dominiocac = (TextView) rootView.findViewById(R.id.view_dominiocac);
            dominiocac.setText((build.getClosecombatdmg() + (build.getApinCaC() * 8)) + "");

            dominiodistancia = (TextView) rootView.findViewById(R.id.view_dominiodistancia);
            dominiodistancia.setText((build.getDistancedmg() + (build.getApindistance() * 8)) + "");

            dominiounico = (TextView) rootView.findViewById(R.id.view_dominiounico);
            dominiounico.setText((build.getOnetargetdmg() + (build.getApinalvounico() * 8)) + "");

            dominiozona = (TextView) rootView.findViewById(R.id.view_dominiozona);
            dominiozona.setText((build.getAreadmg() + (build.getApinzona() * 8)) + "");

            dominiocura = (TextView) rootView.findViewById(R.id.view_dominiocura);
            dominiocura.setText((build.getHeal() + (build.getApinheal() * 6)) + "");

            dominioberserk = (TextView) rootView.findViewById(R.id.view_dominioberserk);
            dominioberserk.setText((build.getBeserkdmg() + (build.getApinbeserkdmg() * 8)) + "");

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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
