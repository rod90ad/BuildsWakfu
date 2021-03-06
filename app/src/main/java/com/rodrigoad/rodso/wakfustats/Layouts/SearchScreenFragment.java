package com.rodrigoad.rodso.wakfustats.Layouts;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rodrigoad.rodso.wakfustats.MainActivity;
import com.rodrigoad.rodso.wakfustats.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.Tracker;
import com.rodrigoad.rodso.wakfustats.AnalyticsApplication;
import com.rodrigoad.rodso.wakfustats.Utils.BD;
import com.rodrigoad.rodso.wakfustats.Utils.Item;
import com.rodrigoad.rodso.wakfustats.Utils.RVAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchScreenFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchScreenFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Spinner raritySpinner;
    private Spinner tipoSpinner;
    private RecyclerView rv;
    private RVAdapter adapter;
    private ImageView search;
    private ArrayList<Item> it;
    private TextView numberPickerleft;
    private TextView numberPickerright;
    private Spinner spinnerStats;
    private AdView mAdView;
    private ImageView morebutton;
    private RelativeLayout rlstats;
    private boolean online=false;
    private Tracker mTracker;
    private boolean more=false;

    View rootView =null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchScreenFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchScreenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchScreenFragment newInstance(String param1, String param2) {
        SearchScreenFragment fragment = new SearchScreenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //Get a Tracker (should auto-report)
        ((AnalyticsApplication) this.getActivity().getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
        mTracker = ((AnalyticsApplication) this.getActivity().getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
    }

    @Override
    public void onDestroyView() {
        if (rootView.getParent() != null) {
            ((ViewGroup)rootView.getParent()).removeView(rootView);
        }
        super.onDestroyView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    public class ItemData {
        String text;
        Integer imageId;

        public ItemData(String text, Integer imageId) {
            this.text = text;
            this.imageId = imageId;
        }

        public String getText() {
            return text;
        }

        public Integer getImageId() {
            return imageId;
        }
    }

    public class SpinnerAdapter extends ArrayAdapter<ItemData> {
        int groupid;
        Activity context;
        ArrayList<ItemData> list;
        LayoutInflater inflater;

        public SpinnerAdapter(Activity context, int groupid, int id, ArrayList<ItemData> list) {
            super(context, id, list);
            this.list = list;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.groupid = groupid;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = inflater.inflate(groupid, parent, false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imTodos);
            imageView.setImageResource(list.get(position).getImageId());
            TextView textView = (TextView) itemView.findViewById(R.id.txTodos);
            textView.setText(list.get(position).getText());
            return itemView;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View v = getView(position, convertView, parent);
            return v;
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public void showAD(Boolean mostrar){
        if(!mostrar) {
            mAdView.getLayoutParams().height=0;
        }else{
            mAdView.getLayoutParams().height= RelativeLayout.LayoutParams.WRAP_CONTENT;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(rootView==null) {
            rootView = inflater.inflate(R.layout.fragment_search_screen, container, false);

            int screen_width = Resources.getSystem().getDisplayMetrics().widthPixels;
            int screen_height = Resources.getSystem().getDisplayMetrics().heightPixels;

            //spiner de raridade
            RelativeLayout.LayoutParams rlSpinerRarity = new RelativeLayout.LayoutParams(screen_width / 2, screen_height/5);
            rlSpinerRarity.addRule(RelativeLayout.CENTER_HORIZONTAL);
            raritySpinner = (Spinner) rootView.findViewById(R.id.spinnerRaridade);
            //raritySpinner.setLayoutParams(rlSpinerRarity);
            ArrayList<ItemData> listRarity = new ArrayList<>();
            listRarity.add(new ItemData(getResources().getString(R.string.todos), R.drawable.todosrar));
            listRarity.add(new ItemData(getResources().getString(R.string.comun), R.drawable.comum));
            listRarity.add(new ItemData(getResources().getString(R.string.incomum), R.drawable.incomum));
            listRarity.add(new ItemData(getResources().getString(R.string.raro), R.drawable.raro));
            listRarity.add(new ItemData(getResources().getString(R.string.mitico), R.drawable.mitico));
            listRarity.add(new ItemData(getResources().getString(R.string.lend), R.drawable.lendario));
            listRarity.add(new ItemData(getResources().getString(R.string.reliq), R.drawable.reliquia));
            listRarity.add(new ItemData(getResources().getString(R.string.epic), R.drawable.epico));
            listRarity.add(new ItemData(getResources().getString(R.string.pvp), R.drawable.pvp));
            SpinnerAdapter adapter = new SpinnerAdapter(getActivity(), R.layout.row, R.id.txTodos, listRarity);
            raritySpinner.setAdapter(adapter);

            //spiner tipo
            RelativeLayout.LayoutParams rlSpinerTipo = new RelativeLayout.LayoutParams(screen_width / 2,  screen_height/5);
            rlSpinerTipo.addRule(RelativeLayout.CENTER_HORIZONTAL);
            tipoSpinner = (Spinner) rootView.findViewById(R.id.spinnerTipo);
            //tipoSpinner.setLayoutParams(rlSpinerTipo);
            ArrayList<ItemData> listTipo = new ArrayList<>();
            listTipo.add(new ItemData(getResources().getString(R.string.todos), R.drawable.todosit));
            listTipo.add(new ItemData(getResources().getString(R.string.amu), R.drawable.amu));
            listTipo.add(new ItemData(getResources().getString(R.string.anel), R.drawable.anel));
            listTipo.add(new ItemData(getResources().getString(R.string.w1h), R.drawable.mao1));
            listTipo.add(new ItemData(getResources().getString(R.string.w2h), R.drawable.mao2));
            listTipo.add(new ItemData(getResources().getString(R.string.wsec), R.drawable.segmao));
            listTipo.add(new ItemData(getResources().getString(R.string.cap), R.drawable.cap));
            listTipo.add(new ItemData(getResources().getString(R.string.cint), R.drawable.cinto));
            listTipo.add(new ItemData(getResources().getString(R.string.elm), R.drawable.elmo));
            listTipo.add(new ItemData(getResources().getString(R.string.drag), R.drawable.drag));
            listTipo.add(new ItemData(getResources().getString(R.string.pet), R.drawable.pet));
            listTipo.add(new ItemData(getResources().getString(R.string.pei), R.drawable.peitoral));
            listTipo.add(new ItemData(getResources().getString(R.string.bot), R.drawable.bota));
            listTipo.add(new ItemData(getResources().getString(R.string.ins), R.drawable.todosit));
            listTipo.add(new ItemData(getResources().getString(R.string.montaria), R.drawable.mont));
            SpinnerAdapter adapterTipo = new SpinnerAdapter(getActivity(), R.layout.row, R.id.txTodos, listTipo);
            tipoSpinner.setAdapter(adapterTipo);

            rv = (RecyclerView) rootView.findViewById(R.id.recyclerview);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                rv.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                        if(!more){
                            MoreAction();
                        }
                    }
                });
            }
            mAdView = (AdView) rootView.findViewById(R.id.adView);
            search = (ImageView) rootView.findViewById(R.id.procurar_button);
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isOnline()) {
                        online=true;
                    }
                    showAD(online);
                    procurar(v);
                }
            });

            numberPickerleft = (TextView) rootView.findViewById(R.id.numberpickerleft);
            numberPickerleft.setText("1");
            numberPickerleft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowNumberPicker(numberPickerleft);
                }
            });

            numberPickerright = (TextView) rootView.findViewById(R.id.numberpickerright);
            numberPickerright.setText("200");
            numberPickerright.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowNumberPicker(numberPickerright);
                }
            });

            RelativeLayout.LayoutParams lpleft = (RelativeLayout.LayoutParams) numberPickerleft.getLayoutParams();
            lpleft.height = screen_height/12;
            numberPickerleft.setLayoutParams(lpleft);
            RelativeLayout.LayoutParams lpright = (RelativeLayout.LayoutParams) numberPickerright.getLayoutParams();
            lpright.height = screen_height/12;
            numberPickerright.setLayoutParams(lpright);

            rlstats = (RelativeLayout) rootView.findViewById(R.id.rlstats);

            morebutton = (ImageView) rootView.findViewById(R.id.morebutton);
            morebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MoreAction();
                }
            });

            spinnerStats = (Spinner) rootView.findViewById(R.id.spinnerstats);
            ArrayAdapter<String> a =new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.stats));
            a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerStats.setAdapter(a);

            mAdView = (AdView) rootView.findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
        return rootView;
    }

    private void ShowNumberPicker(final TextView textView) {
        final Dialog d = new Dialog(getContext());
        d.setTitle(getResources().getString(R.string.nivel));
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(200); // max value 100
        np.setMinValue(1);   // min value 0
        np.setValue(Integer.parseInt(String.valueOf(textView.getText())));
        np.setWrapSelectorWheel(true);
        //np.setOnValueChangedListener(MainActivity.this);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(String.valueOf(np.getValue())); //set the value to textview
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();
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

    public void MoreAction(){
        if(more){
            more=false;
            ViewCompat.animate(morebutton)
                    .rotation(45)
                    .withLayer()
                    .setDuration(250)
                    .setInterpolator(new OvershootInterpolator(10.0F))
                    .start();
            expand(rlstats);
        }else{
            more=true;
            ViewCompat.animate(morebutton)
                    .rotation(0)
                    .withLayer()
                    .setDuration(250)
                    .setInterpolator(new OvershootInterpolator(10.0F))
                    .start();
            collapse(rlstats);
        }
    }

    public void procurar(View v){
        int Rpos = raritySpinner.getSelectedItemPosition();
        int Tpos = tipoSpinner.getSelectedItemPosition();
        String[] tipoBusca = new String[5];
        tipoBusca[0] = "" + String.valueOf(numberPickerleft.getText());
        tipoBusca[1] = "" + String.valueOf(numberPickerright.getText());
        switch (Rpos) {
            case 0:
                tipoBusca[2] = "all";
                break;
            case 1:
                tipoBusca[2] = "1";
                break;
            case 2:
                tipoBusca[2] = "2";
                break;
            case 3:
                tipoBusca[2] = "3";
                break;
            case 4:
                tipoBusca[2] = "4";
                break;
            case 5:
                tipoBusca[2] = "5";
                break;
            case 6:
                tipoBusca[2] = "6";
                break;
            case 7:
                tipoBusca[2] = "7";
                break;
            case 8:
                tipoBusca[2] = "8";
                break;
        }
        switch (Tpos) {
            case 0:
                tipoBusca[3] = "all";
                break;
            case 1:
                tipoBusca[3] = "amu";
                break;
            case 2:
                tipoBusca[3] = "anel";
                break;
            case 3:
                tipoBusca[3] = "w1h";
                break;
            case 4:
                tipoBusca[3] = "w2h";
                break;
            case 5:
                tipoBusca[3] = "wsec";
                break;
            case 6:
                tipoBusca[3] = "cap";
                break;
            case 7:
                tipoBusca[3] = "cint";
                break;
            case 8:
                tipoBusca[3] = "elm";
                break;
            case 9:
                tipoBusca[3] = "drag";
                break;
            case 10:
                tipoBusca[3] = "pet";
                break;
            case 11:
                tipoBusca[3] = "pei";
                break;
            case 12:
                tipoBusca[3] = "bot";
                break;
            case 13:
                tipoBusca[3] = "ins";
                break;
            case 14:
                tipoBusca[3] = "mont";
                break;
        }
        switch (spinnerStats.getSelectedItemPosition()){
            case 0:
                tipoBusca[4]="all";
                break;
            case 1:
                tipoBusca[4]="pa";
                break;
            case 2:
                tipoBusca[4]="pm";
                break;
            case 3:
                tipoBusca[4]="range";
                break;
            case 4:
                tipoBusca[4]="control";
                break;
            case 5:
                tipoBusca[4]="pv";
                break;
            case 6:
                tipoBusca[4]="dmg";
                break;
            case 7:
                tipoBusca[4]="heal";
                break;
            case 8:
                tipoBusca[4]="block";
                break;
            case 9:
                tipoBusca[4]="criticalchance";
                break;
            case 10:
                tipoBusca[4]="backdmg";
                break;
            case 11:
                tipoBusca[4]="distancedmg";
                break;
            case 12:
                tipoBusca[4]="closecombat";
                break;
            case 13:
                tipoBusca[4]="aoedmg";
                break;
            case 14:
                tipoBusca[4]="onetargetdmg";
                break;
            case 15:
                tipoBusca[4]="dmg1ele";
                break;
            case 16:
                tipoBusca[4]="dmg2ele";
                break;
            case 17:
                tipoBusca[4]="dmg3ele";
                break;
            case 18:
                tipoBusca[4]="dmgw";
                break;
            case 19:
                tipoBusca[4]="dmgf";
                break;
            case 20:
                tipoBusca[4]="dmge";
                break;
            case 21:
                tipoBusca[4]="dmga";
                break;
            case 22:
                tipoBusca[4]="esquiva";
                break;
            case 23:
                tipoBusca[4]="dc";
                break;
            case 24:
                tipoBusca[4]="ini";
                break;
            case 25:
                tipoBusca[4]="stop";
                break;
            case 26:
                tipoBusca[4]="prosp";
                break;
            case 27:
                tipoBusca[4]="sab";
                break;
            case 28:
                tipoBusca[4]="pwmax";
                break;
            case 29:
                tipoBusca[4]="res";
                break;
            case 30:
                tipoBusca[4]="res1eler";
                break;
            case 31:
                tipoBusca[4]="res2eler";
                break;
            case 32:
                tipoBusca[4]="res3eler";
                break;
            case 33:
                tipoBusca[4]="resw";
                break;
            case 34:
                tipoBusca[4]="resf";
                break;
            case 35:
                tipoBusca[4]="rese";
                break;
            case 36:
                tipoBusca[4]="resa";
                break;
            case 37:
                tipoBusca[4]="resbd";
                break;
            case 38:
                tipoBusca[4]="rescd";
                break;
            case 39:
                tipoBusca[4]="respm";
                break;
            case 40:
                tipoBusca[4]="respa";
                break;
            case 41:
                tipoBusca[4]="wp";
                break;
            case 42:
                tipoBusca[4]="sts";
                break;
            case 43:
                tipoBusca[4]="pvphp";
                break;
            case 44:
                tipoBusca[4]="arteequipar";
                break;
            case 45:
                tipoBusca[4]="nvfw";
                break;
            case 46:
                tipoBusca[4]="nvff";
                break;
            case 47:
                tipoBusca[4]="nvfe";
                break;
            case 48:
                tipoBusca[4]="nvfa";
                break;
            case 49:
                tipoBusca[4]="nvfg";
                break;
            case 50:
                tipoBusca[4]="movespeed";
                break;
        }
        BD bd = new BD(v.getContext());
        it = bd.getItens(tipoBusca, MainActivity.prefs.getBoolean("pvp",false));
        if (it.size() == 0) {
            Toast.makeText(v.getContext(), R.string.buscaNull, Toast.LENGTH_LONG).show();
        } else {
            if(!more) {
                MoreAction();
            }
            LinearLayoutManager llm = new LinearLayoutManager(v.getContext());
            rv.setLayoutManager(llm);
            rv.removeAllViews();
            adapter = new RVAdapter(it, v.getContext(), this);
            rv.setAdapter(adapter);
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            Toast.makeText(v.getContext(), v.getContext().getResources().getString(R.string.encontrados1) + " " + it.size() + " " + v.getContext().getResources().getString(R.string.encontrados2), Toast.LENGTH_LONG).show();
        }
        it = null;
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
