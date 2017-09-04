package com.rodrigoad.rodso.wakfustats.Layouts;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.Tracker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.rodrigoad.rodso.wakfustats.AnalyticsApplication;
import com.rodrigoad.rodso.wakfustats.Layouts.Decks.PassiveAttribute;
import com.rodrigoad.rodso.wakfustats.MainActivity;
import com.rodrigoad.rodso.wakfustats.R;
import com.rodrigoad.rodso.wakfustats.ShareBuildAcitivy;
import com.rodrigoad.rodso.wakfustats.Utils.BD;
import com.rodrigoad.rodso.wakfustats.Utils.Coment;
import com.rodrigoad.rodso.wakfustats.Utils.Item;
import com.rodrigoad.rodso.wakfustats.Utils.ItemComponent;
import com.rodrigoad.rodso.wakfustats.Utils.RVAdapterComents;
import com.rodrigoad.rodso.wakfustats.ViewBuildActivity;
import com.squareup.picasso.Picasso;


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
    private ValueEventListener valueEventListenerViewBuild;
    private int reliquia=0;
    private int epico=0;
    private View qrview;
    private Tracker mTracker;
    private View rootView;
    private FloatingActionButton fabMenu;
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private boolean isShowing=false;
    private static RecyclerView rv;

    private static com.rodrigoad.rodso.wakfustats.Utils.Build build;

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
    }

    public void Refresh(){
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
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
        build.addStatus(item.getStatus());
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
            final Dialog d = new Dialog(getContext());
            d.setTitle(getResources().getString(R.string.tiraitem));
            d.setContentView(R.layout.dialog_item_remove);
            ItemComponent item1 = (ItemComponent) d.findViewById(R.id.item_remove1);
            item1.setItem(item);
            Button delete = (Button) d.findViewById(R.id.item_remove_delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (item.getTipo()) {
                        case "amu":
                            ViewBuildActivity.build.setAmuleto(new Item());
                            break;
                        case "anel":
                            if (build.getAnel1().getLink() == item.getLink()) {
                                build.setAnel1(new Item());
                            } else {
                                build.setAnel2(new Item());
                            }
                            break;
                        case "w1h":
                            build.setArmamain(new Item());
                            break;
                        case "w2h":
                            build.setArmamain(new Item());
                            break;
                        case "wsec":
                            build.setArmasec(new Item());
                            break;
                        case "cap":
                            build.setCapa(new Item());
                            break;
                        case "cint":
                            build.setCinto(new Item());
                            break;
                        case "elm":
                            build.setElmo(new Item());
                            break;
                        case "drag":
                            build.setDragona(new Item());
                            break;
                        case "pet":
                            build.setPet(new Item());
                            break;
                        case "pei":
                            build.setPeitoral(new Item());
                            break;
                        case "bot":
                            build.setBota(new Item());
                            break;
                        case "ins":
                            build.setInsignia(new Item());
                            break;
                        case "mont":
                            build.setMontaria(new Item());
                            break;
                    }
                    new BD(v.getContext()).AtualizarBuild(build);
                    Toast.makeText(v.getContext(), getResources().getString(R.string.itemremove) + " " + build.getNome(), Toast.LENGTH_LONG).show();
                    build = (new BD(v.getContext()).getBuild(build.getCodigo()));
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.detach(viewBuildFragment).attach(viewBuildFragment).commit();
                    d.dismiss();
                }
            });
            Button cancel = (Button) d.findViewById(R.id.item_remove_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    d.dismiss();
                }
            });
            d.show();
        }
    }

    public void imageDone(ImageButton imageButton){
        int screen_width = Resources.getSystem().getDisplayMetrics().widthPixels;
        /*GridLayout.LayoutParams images_size = (GridLayout.LayoutParams) imageButton.getLayoutParams();
        images_size.width=screen_width/7;
        images_size.height=screen_width/7;
        imageButton.setLayoutParams(images_size);*/
        if(imageButton==capa) {
            if (build.getCapa().getCodigo()!=0) {
                setStatus(build.getCapa());
                Picasso.with(imageButton.getContext()).load(build.getCapa().getLink()).into(imageButton);
                if(EpicRelicControl(build.getCapa())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getCapa()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getCapa()));
                }
            }
        }
        if(imageButton==elmo){
            if (build.getElmo().getCodigo()!=0) {
                setStatus(build.getElmo());
                Picasso.with(imageButton.getContext()).load(build.getElmo().getLink()).into(imageButton);
                if(EpicRelicControl(build.getElmo())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getElmo()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getElmo()));
                }
            }
        }
        if(imageButton==drag){
            if (build.getDragona().getCodigo()!=0) {
                setStatus(build.getDragona());
                Picasso.with(imageButton.getContext()).load(build.getDragona().getLink()).into(imageButton);
                if(EpicRelicControl(build.getDragona())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()){
                    imageButton.setOnClickListener(new onImageClickListener(build.getDragona()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getDragona()));
                }
            }
        }
        if(imageButton==amu){
            if (build.getAmuleto().getCodigo()!=0) {
                setStatus(build.getAmuleto());
                Picasso.with(imageButton.getContext()).load(build.getAmuleto().getLink()).into(imageButton);
                if(EpicRelicControl(build.getAmuleto())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getAmuleto()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getAmuleto()));
                }
            }
        }
        if(imageButton==anel1){
            if (build.getAnel1().getCodigo()!=0) {
                setStatus(build.getAnel1());
                Picasso.with(imageButton.getContext()).load(build.getAnel1().getLink()).into(imageButton);
                if(EpicRelicControl(build.getAnel1())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getAnel1()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getAnel1()));
                }
            }
        }
        if(imageButton==anel2){
            if (build.getAnel2().getCodigo()!=0) {
                setStatus(build.getAnel2());
                Picasso.with(imageButton.getContext()).load(build.getAnel2().getLink()).into(imageButton);
                if(EpicRelicControl(build.getAnel2())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getAnel2()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getAnel2()));
                }
            }
        }
        if(imageButton==cint){
            if (build.getCinto().getCodigo()!=0) {
                setStatus(build.getCinto());
                Picasso.with(imageButton.getContext()).load(build.getCinto().getLink()).into(imageButton);
                if(EpicRelicControl(build.getCinto())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getCinto()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getCinto()));
                }
            }
        }
        if(imageButton==msec){
            if (build.getArmasec().getCodigo()!=0) {
                setStatus(build.getArmasec());
                Picasso.with(imageButton.getContext()).load(build.getArmasec().getLink()).into(imageButton);
                if(EpicRelicControl(build.getArmasec())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getArmasec()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getArmasec()));
                }
            }
        }
        if(imageButton==mmain){
            if (build.getArmamain().getCodigo()!=0) {
                setStatus(build.getArmamain());
                Picasso.with(imageButton.getContext()).load(build.getArmamain().getLink()).into(imageButton);
                if(EpicRelicControl(build.getArmamain())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getArmamain()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getArmamain()));
                }
            }
        }
        if(imageButton==pei){
            if (build.getPeitoral().getCodigo()!=0) {
                setStatus(build.getPeitoral());
                Picasso.with(imageButton.getContext()).load(build.getPeitoral().getLink()).into(imageButton);
                if(EpicRelicControl(build.getPeitoral())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getPeitoral()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getPeitoral()));
                }
            }
        }
        if(imageButton==bot){
            if (build.getBota().getCodigo()!=0) {
                setStatus(build.getBota());
                Picasso.with(imageButton.getContext()).load(build.getBota().getLink()).into(imageButton);
                if(EpicRelicControl(build.getBota())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getBota()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getBota()));
                }
            }
        }
        if(imageButton==ins){
            if (build.getInsignia().getCodigo()!=0) {
                setStatus(build.getInsignia());
                Picasso.with(imageButton.getContext()).load(build.getInsignia().getLink()).into(imageButton);
                if(EpicRelicControl(build.getInsignia())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getInsignia()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getInsignia()));
                }
            }
        }
        if(imageButton==pet){
            if (build.getPet().getCodigo()!=0) {
                setStatus(build.getPet());
                Picasso.with(imageButton.getContext()).load(build.getPet().getLink()).into(imageButton);
                if(EpicRelicControl(build.getPet())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getPet()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getPet()));
                }
            }
        }
        if(imageButton==mont){
            if (build.getMontaria().getCodigo()!=0) {
                setStatus(build.getMontaria());
                Picasso.with(imageButton.getContext()).load(build.getMontaria().getLink()).into(imageButton);
                if(EpicRelicControl(build.getMontaria())){
                    imageButton.setBackgroundColor(getActivity().getResources().getColor(R.color.red));
                }
                if(!build.isOnline()) {
                    imageButton.setOnClickListener(new onImageClickListener(build.getMontaria()));
                }else{
                    imageButton.setOnClickListener(new OnShowItem(build.getMontaria()));
                }
            }
        }
    }

    private class OnShowItem implements View.OnClickListener{

        private Item item;

        public OnShowItem(Item item){
            this.item=item;
        }

        @Override
        public void onClick(View v) {
            final Dialog d = new Dialog(getContext());
            d.setTitle(getResources().getString(R.string.mostraitem));
            d.setContentView(R.layout.itemshow);
            ItemComponent item1 = (ItemComponent) d.findViewById(R.id.itemshow_item);
            item1.setItem(item);
            d.show();
        }
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

    private void DeleteOwner(){
        final AlertDialog.Builder builderelm = new AlertDialog.Builder(getContext());
        builderelm.setIcon(android.R.drawable.ic_dialog_info)
                .setTitle(R.string.deletebuild)
                .setMessage(getResources().getString(R.string.deletemsg))
                .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.mDatabase.child("TopBuilds/"+build.getFirekey()).removeEventListener(valueEventListenerViewBuild);
                        TopBuildsFragment.SetListener();
                        ViewBuildActivity.dispose();
                        MainActivity.mDatabase.child("TopBuilds/"+build.getFirekey()).setValue(null);
                        Toast.makeText(getContext(),getResources().getString(R.string.deletebuildsucess),Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton(R.string.nao, null);
        AlertDialog alertelm = builderelm.create();
        alertelm.show();
    }

    @TargetApi(android.os.Build.VERSION_CODES.JELLY_BEAN)
    public void updateQR(BitmapDrawable bmp){
        qrview.setBackground(bmp);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        this.build = ViewBuildActivity.build;
        rootView=null;
        this.build.resetStatus();
        rootView = inflater.inflate(R.layout.fragment_view_build, container, false);
        final Context context = rootView.getContext();
        RelativeLayout.LayoutParams lpText = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lpText.addRule(RelativeLayout.ALIGN_LEFT);

        this.build = ViewBuildActivity.build;
        if(build.isOnline()){
            RelativeLayout rlOwner = (RelativeLayout) rootView.findViewById(R.id.view_rl_owner);
            rlOwner.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;

            TextView txNome = (TextView) rootView.findViewById(R.id.view_user_name);
            txNome.setText("Created by: "+build.getOwner().getName());

            if(build.getOwner().getUserID().equals(MainActivity.user.getUserID())) {
                FloatingActionButton floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.view_delete_onwer);
                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DeleteOwner();
                    }
                });
            }else{
                FloatingActionButton floatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.view_delete_onwer);
                floatingActionButton.getLayoutParams().height=0;
            }

            valueEventListenerViewBuild = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String aux = dataSnapshot.getValue(String.class);
                    if(aux!=null) {
                        build = new Gson().fromJson(aux, com.rodrigoad.rodso.wakfustats.Utils.Build.class);
                        build.setFirekey(dataSnapshot.getKey());
                        setComents(getContext());
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };

            MainActivity.mDatabase.child("TopBuilds/" + build.getFirekey()).addValueEventListener(valueEventListenerViewBuild);

            RelativeLayout rlComents = (RelativeLayout) rootView.findViewById(R.id.view_rl_coments);
            rlComents.getLayoutParams().height = RelativeLayout.LayoutParams.WRAP_CONTENT;

            final EditText et = (EditText) rootView.findViewById(R.id.view_text_coment);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.view_send_coment);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String sComent = et.getText().toString();
                    sComent = sComent.replaceAll("\\s","");
                    if (sComent != null && !sComent.isEmpty() && !sComent.equals("null")) {
                        final Coment coment = new Coment(et.getText().toString(), MainActivity.user.getName());
                        et.setText("");
                        MainActivity.mDatabase.child("TopBuilds/"+build.getFirekey()).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                com.rodrigoad.rodso.wakfustats.Utils.Build aux = new Gson().fromJson(dataSnapshot.getValue(String.class), com.rodrigoad.rodso.wakfustats.Utils.Build.class);
                                aux.getComents().add(coment);
                                MainActivity.mDatabase.child("TopBuilds/" + build.getFirekey()).setValue(new Gson().toJson(aux));
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
            });

            RelativeLayout rlFloats = (RelativeLayout) rootView.findViewById(R.id.view_rl_floats);
            rlFloats.getLayoutParams().height = 0;

            rv = (RecyclerView) rootView.findViewById(R.id.view_rv_coments);
            setComents(context);
        }else {

            final Animation show_fab_1 = AnimationUtils.loadAnimation(getContext(), R.anim.fab1_show);
            final Animation hide_fab_1 = AnimationUtils.loadAnimation(getContext(), R.anim.fab1_hide);
            final Animation show_fab_2 = AnimationUtils.loadAnimation(getContext(), R.anim.fab2_show);
            final Animation hide_fab_2 = AnimationUtils.loadAnimation(getContext(), R.anim.fab2_hide);
            final Animation show_fab_3 = AnimationUtils.loadAnimation(getContext(), R.anim.fab3_show);
            final Animation hide_fab_3 = AnimationUtils.loadAnimation(getContext(), R.anim.fab3_hide);

            fab1 = (FloatingActionButton) rootView.findViewById(R.id.fab_1);
            fab1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ShareBuildAcitivy.class);
                    Bundle b = new Bundle();
                    b.putString("codigo", new Gson().toJson(ViewBuildActivity.build));
                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
            fab2 = (FloatingActionButton) rootView.findViewById(R.id.fab_2);
            fab2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final AlertDialog.Builder builderelm = new AlertDialog.Builder(v.getContext());
                    builderelm.setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle(R.string.deletebuild)
                            .setMessage(getResources().getString(R.string.deletemsg))
                            .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new BD(getContext()).deleteBuild(ViewBuildActivity.build.getCodigo());
                                    BuildsFragment.setRV();
                                    Toast.makeText(v.getContext(), getResources().getString(R.string.deletebuildsucess), Toast.LENGTH_LONG).show();
                                    ViewBuildActivity.dispose();
                                }
                            })
                            .setNegativeButton(R.string.nao, null);
                    AlertDialog alertelm = builderelm.create();
                    alertelm.show();
                }
            });
            fab3 = (FloatingActionButton) rootView.findViewById(R.id.fab_3);
            fab3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final AlertDialog.Builder builderelm = new AlertDialog.Builder(v.getContext());
                    builderelm.setIcon(android.R.drawable.ic_dialog_info)
                            .setTitle(R.string.publishtittle)
                            .setMessage(getResources().getString(R.string.publishmessage))
                            .setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    build.setOwner(MainActivity.user);
                                    build.setOnline(true);
                                    MainActivity.mDatabase.child("TopBuilds/").push().setValue(new Gson().toJson(build));
                                    Toast.makeText(getContext(), getResources().getString(R.string.buildsendtotop), Toast.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton(R.string.nao, null);
                    AlertDialog alertelm = builderelm.create();
                    alertelm.show();
                }
            });

            fabMenu = (FloatingActionButton) rootView.findViewById(R.id.view_option_fab);
            fabMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isShowing) {
                        isShowing = false;
                        //fabMenu rotate
                        ViewCompat.animate(fabMenu)
                                .rotation(0)
                                .withLayer()
                                .setDuration(250)
                                .setInterpolator(new OvershootInterpolator(10.0F))
                                .start();
                        //fab1
                        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fab1.getLayoutParams();
                        layoutParams1.rightMargin -= (int) (fab1.getWidth() * 1.7);
                        layoutParams1.bottomMargin -= (int) (fab1.getHeight() * 0);
                        fab1.setLayoutParams(layoutParams1);
                        fab1.startAnimation(hide_fab_1);
                        fab1.setClickable(false);
                        //fab2
                        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
                        layoutParams2.rightMargin -= (int) (fab2.getWidth() * 3.4);
                        layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 0);
                        fab2.setLayoutParams(layoutParams2);
                        fab2.startAnimation(hide_fab_2);
                        fab2.setClickable(false);
                        //fab3
                        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
                        layoutParams3.rightMargin -= (int) (fab3.getWidth() * 5.1);
                        layoutParams3.bottomMargin -= (int) (fab3.getHeight() * 0);
                        fab3.setLayoutParams(layoutParams3);
                        fab3.startAnimation(hide_fab_3);
                        fab3.setClickable(false);
                    } else {
                        isShowing = true;
                        //fabMenu rotate
                        ViewCompat.animate(fabMenu)
                                .rotation(45)
                                .withLayer()
                                .setDuration(250)
                                .setInterpolator(new OvershootInterpolator(10.0F))
                                .start();
                        //fab1
                        FrameLayout.LayoutParams layoutParams1 = (FrameLayout.LayoutParams) fab1.getLayoutParams();
                        layoutParams1.rightMargin += (int) (fab1.getWidth() * 1.7);
                        layoutParams1.bottomMargin += (int) (fab1.getHeight() * 0);
                        fab1.setLayoutParams(layoutParams1);
                        fab1.startAnimation(show_fab_1);
                        fab1.setClickable(true);
                        //fab2
                        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
                        layoutParams2.rightMargin += (int) (fab2.getWidth() * 3.4);
                        layoutParams2.bottomMargin += (int) (fab2.getHeight() * 0);
                        fab2.setLayoutParams(layoutParams2);
                        fab2.startAnimation(show_fab_2);
                        fab2.setClickable(true);
                        //fab3
                        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
                        layoutParams3.rightMargin += (int) (fab3.getWidth() * 5.1);
                        layoutParams3.bottomMargin += (int) (fab3.getHeight() * 0);
                        fab3.setLayoutParams(layoutParams3);
                        fab3.startAnimation(show_fab_3);
                        fab3.setClickable(true);
                    }
                }
            });
        }

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

        build= PassiveAttribute.setPoints(build);

        //coloca as passivas
        switch (build.getClasse()){
            case 0:
                build = PassiveAttribute.Cra(build); //cra
                break;
            case 1:
                build = PassiveAttribute.Eca(build); //ecaflip
                break;
            case 2:
                build = PassiveAttribute.Elio(build); //elio
                break;
            case 3:
                build = PassiveAttribute.Eni(build); //eniripsa
                break;
            case 4:
                build = PassiveAttribute.Enu(build); //enutrof
                break;
            case 5:
                build = PassiveAttribute.Feca(build); //feca
                break;
            case 6:
                build = PassiveAttribute.Hupp(build); //huppermage
                break;
            case 7:
                build = PassiveAttribute.Iop(build); //iop
                break;
            case 8:
                build = PassiveAttribute.Osa(build); //osamodas
                break;
            case 9:
                build = PassiveAttribute.Panda(build); //panda
                break;
            case 10:
                build = PassiveAttribute.Lad(build); //ladino
                break;
            case 11:
                build = PassiveAttribute.Sac(build); //sacrier
                break;
            case 12:
                build = PassiveAttribute.Sad(build); //sadida
                break;
            case 13:
                build = PassiveAttribute.Sram(build); //sram
                break;
            case 14:
                build = PassiveAttribute.Steamer(build); //steamer
                break;
            case 15:
                build = PassiveAttribute.Xelor(build); //xelor
                break;
            case 16:
                build = PassiveAttribute.Zob(build); //zobal
                break;
        }

        //coloca os status

        pv = (TextView) rootView.findViewById(R.id.view_pv);
        pv.setText(""+build.getVitalpoint());

        pa = (TextView) rootView.findViewById(R.id.view_pa);
        pa.setText("" + (6 + build.getActionpoint()));

        pm = (TextView) rootView.findViewById(R.id.view_pm);
        pm.setText("" + (3 + build.getMovepoint()));

        pw = (TextView) rootView.findViewById(R.id.view_pw);
        pw.setText("" + (6 + build.getWakfupoint()));

        //aplica os danos
        dmgagua = (TextView) rootView.findViewById(R.id.view_dmg_water);
        //dano + dmg geral                  +   dmg agua                    +           20 do pm                      +         40 do alcance                       +           40 do controle
        dmgagua.setText((getDano(1) + build.getDmg() + build.getDmgwater() + (build.getApinmovepoint() * 20) + (build.getApinrangeanddmg() * 40) + (build.getApincontrolanddmg() * 40)) + (build.getApingeral() * 5) + "");

        dmgterra = (TextView) rootView.findViewById(R.id.view_dmg_terra);
        dmgterra.setText((getDano(2) + build.getDmg() + build.getDmgearth() + (build.getApinmovepoint() * 20) + (build.getApinrangeanddmg() * 40) + (build.getApincontrolanddmg() * 40)) + (build.getApingeral() * 5) + "");

        dmgar = (TextView) rootView.findViewById(R.id.view_dmg_ar);
        dmgar.setText((getDano(3) + build.getDmg() + build.getDmgair() + (build.getApinmovepoint() * 20) + (build.getApinrangeanddmg() * 40) + (build.getApincontrolanddmg() * 40)) + (build.getApingeral() * 5) + "");

        dmgfogo = (TextView) rootView.findViewById(R.id.view_dmg_fogo);
        dmgfogo.setText((getDano(4) + build.getDmg() + build.getDmgfire() + (build.getApinmovepoint() * 20) + (build.getApinrangeanddmg() * 40) + (build.getApincontrolanddmg() * 40)) + (build.getApingeral() * 5) + "");

        //aplica as resistencias
        resagua = (TextView) rootView.findViewById(R.id.view_res_water);
        resagua.setText((getResist(1) + build.getResist() + build.getReswater()) + "");

        resterra = (TextView) rootView.findViewById(R.id.view_res_terra);
        resterra.setText((getResist(2) + build.getResist() + build.getResearth()) + "");

        resar = (TextView) rootView.findViewById(R.id.view_res_ar);
        resar.setText((getResist(3) + build.getResist() + build.getResair()) + "");

        resfogo = (TextView) rootView.findViewById(R.id.view_res_fogo);
        resfogo.setText((getResist(4) + build.getResist() + build.getResfire()) + "");

        dmgpa = (TextView) rootView.findViewById(R.id.view_dmg_pa);
        dmgpa.setText(build.getApmpremove() + "%");

        dmgpm = (TextView) rootView.findViewById(R.id.view_dmg_pm);
        dmgpm.setText(build.getApmpremove() + "%");

        respa = (TextView) rootView.findViewById(R.id.view_res_pa);
        respa.setText(build.getRespa() + "%");

        respm = (TextView) rootView.findViewById(R.id.view_res_pm);
        respm.setText(build.getRespm() + "%");

        danofinal = (TextView) rootView.findViewById(R.id.view_danofinal);
        danofinal.setText(build.getFinaldamage() + "%");

        curasrealizadas = (TextView) rootView.findViewById(R.id.view_curas);
        curasrealizadas.setText( build.getHeal() +"%");

        golpescriticos = (TextView) rootView.findViewById(R.id.view_golpecritico);
        golpescriticos.setText(build.getCriticalchance()+ "%");

        parada = (TextView) rootView.findViewById(R.id.view_parada);
        parada.setText(build.getStop() + "%");

        iniciativa = (TextView) rootView.findViewById(R.id.view_iniciativa);
        iniciativa.setText(build.getIniciative()+ "");

        alcance = (TextView) rootView.findViewById(R.id.view_alcance);
        alcance.setText(build.getRange() + "");

        esquiva = (TextView) rootView.findViewById(R.id.view_esquiva);
        esquiva.setText(build.getEvasion() + "");

        bloqueio = (TextView) rootView.findViewById(R.id.view_bloqueio);
        bloqueio.setText(build.getBlock() + "");

        sabedoria = (TextView) rootView.findViewById(R.id.view_sabedoria);
        sabedoria.setText(build.getSabedoria() + "");

        prospecção = (TextView) rootView.findViewById(R.id.view_prospecção);
        prospecção.setText(build.getProsp() + "");

        controle = (TextView) rootView.findViewById(R.id.view_controle);
        controle.setText(build.getControl() + "");

        arteequipar = (TextView) rootView.findViewById(R.id.view_arteequipar);
        arteequipar.setText(build.getArteequipar() + "");

        dominiocritico = (TextView) rootView.findViewById(R.id.view_danocritico);
        dominiocritico.setText(build.getCriticaldmg() + "");

        rescritico = (TextView) rootView.findViewById(R.id.view_rescritico);
        rescritico.setText(build.getRescriticaldmg() + "");

        dominiocostas = (TextView) rootView.findViewById(R.id.view_dominiocostas);
        dominiocostas.setText(build.getBackdmg() + "");

        rescostas = (TextView) rootView.findViewById(R.id.view_rescostas);
        rescostas.setText(build.getResblackdmg() + "");

        dominiocac = (TextView) rootView.findViewById(R.id.view_dominiocac);
        dominiocac.setText(build.getClosecombatdmg() + "");

        dominiodistancia = (TextView) rootView.findViewById(R.id.view_dominiodistancia);
        dominiodistancia.setText(build.getDistancedmg() + "");

        dominiounico = (TextView) rootView.findViewById(R.id.view_dominiounico);
        dominiounico.setText(build.getOnetargetdmg() + "");

        dominiozona = (TextView) rootView.findViewById(R.id.view_dominiozona);
        dominiozona.setText(build.getAreadmg() + "");

        dominiocura = (TextView) rootView.findViewById(R.id.view_dominiocura);
        dominiocura.setText(build.getHeal() + build.getDominioCura() + "");

        dominioberserk = (TextView) rootView.findViewById(R.id.view_dominioberserk);
        dominioberserk.setText((build.getBeserkdmg()) + "");

        return rootView;
    }

    public static void setComents(Context context){
        RVAdapterComents adapterComents = new RVAdapterComents(build.getComents(), context);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv.setLayoutManager(llm);
        rv.removeAllViews();
        rv.setAdapter(adapterComents);
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
