package com.rodrigoad.rodso.wakfustats.Layouts;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.rodrigoad.rodso.wakfustats.AnalyticsApplication;
import com.rodrigoad.rodso.wakfustats.Utils.BD;
import com.rodrigoad.rodso.wakfustats.Utils.Build;
import com.rodrigoad.rodso.wakfustats.MainActivity;
import com.rodrigoad.rodso.wakfustats.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateBuildFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateBuildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateBuildFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //elementos do layout
    private Spinner spnClass;
    private TextView numberpickerLevel;
    private Spinner spnElementp;
    private Spinner spnresp;
    private Button limpar;
    private Button cancelar;
    private Button criar;
    private EditText txnome;
    private static BuildsFragment buildsFragment;
    private View rootView;
    private Tracker mTracker;


    private OnFragmentInteractionListener mListener;

    public CreateBuildFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateBuildFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateBuildFragment newInstance(String param1, BuildsFragment param2) {
        CreateBuildFragment fragment = new CreateBuildFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        buildsFragment = param2;
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
        // Obtain the shared Tracker instance.
        //Get a Tracker (should auto-report)
        ((AnalyticsApplication) getActivity().getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
        mTracker = ((AnalyticsApplication) getActivity().getApplication()).getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);
    }

    @Override
    public void onStart(){
        super.onStart();
        // Set screen name.
        mTracker.setScreenName("Create Build");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
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

    public class SpinnerAdapterEle extends ArrayAdapter<ElementData> {
        int groupid;
        Activity context;
        ArrayList<ElementData> list;
        LayoutInflater inflater;

        public SpinnerAdapterEle(Activity context, int groupid, int id, ArrayList<ElementData> list) {
            super(context, id, list);
            this.list = list;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.groupid = groupid;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = inflater.inflate(groupid, parent, false);
            ImageView imageView1 = (ImageView) itemView.findViewById(R.id.imele1);
            imageView1.setImageResource(list.get(position).getImageId1());
            ImageView imageView2 = (ImageView) itemView.findViewById(R.id.imele2);
            imageView2.setImageResource(list.get(position).getImageId2());
            ImageView imageView3 = (ImageView) itemView.findViewById(R.id.imele3);
            imageView3.setImageResource(list.get(position).getImageId3());
            ImageView imageView4 = (ImageView) itemView.findViewById(R.id.imele4);
            imageView4.setImageResource(list.get(position).getImageId4());
            return itemView;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View v = getView(position, convertView, parent);
            return v;
        }
    }

    public class ElementData {
        Integer imageid1;
        Integer imageid2;
        Integer imageid3;
        Integer imageid4;

        public ElementData(Integer imageid1, Integer imageid2,Integer imageid3, Integer imageid4) {
            this.imageid1 = imageid1;
            this.imageid2 = imageid2;
            this.imageid3 = imageid3;
            this.imageid4 = imageid4;
        }

        public Integer getImageId1() {  return imageid1;  }
        public Integer getImageId2() {  return imageid2;  }
        public Integer getImageId3() {  return imageid3;  }
        public Integer getImageId4() {  return imageid4;  }
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

    private void Cancelar(){
        buildsFragment.setRV();
        this.onDestroy();
    }

    private void Limpar(){
        txnome.setText("");
        spnClass.setSelection(0);
        numberpickerLevel.setText("1");
        spnElementp.setSelection(0);
        spnresp.setSelection(0);
    }

    private void Criar(View v){
        //EditText text = (EditText) ma.findViewById(R.id.txNome);
        if (txnome.getText() == null || txnome.getText().toString().equals("")) {
            Toast.makeText(v.getContext(), R.string.errorname, Toast.LENGTH_LONG).show();
        } else {
            Build build = new Build();
            build.setClasse(spnClass.getSelectedItemPosition());
            build.setNivel(Integer.parseInt(numberpickerLevel.getText().toString()));
            String nome = txnome.getText().toString().trim();
            build.setNome(nome);
            build.setElementp(spnElementp.getSelectedItemPosition());
            build.setResistp(spnresp.getSelectedItemPosition());
            if (new BD(v.getContext()).verificaBuild(nome)) {
                new BD(getContext()).salvaBuild(build);
                Toast.makeText(v.getContext(), R.string.createbuildsucess, Toast.LENGTH_LONG).show();
                Limpar();
                buildsFragment.setRV();
                this.onDestroy();
            } else {
                Toast.makeText(v.getContext(), R.string.errornamesame, Toast.LENGTH_LONG).show();
            }
        }
    }



    private void ShowNumberPicker(final TextView textView) {
        final Dialog d = new Dialog(getContext());
        d.setTitle(getResources().getString(R.string.nivel));
        d.setContentView(R.layout.dialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(200); // max value 200
        np.setMinValue(0);   // min value 0
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(rootView==null) {
            rootView = inflater.inflate(R.layout.fragment_create_build, container, false);

            //spiner de classe
            spnClass = (Spinner) rootView.findViewById(R.id.spinnerclass);
            ArrayList<ItemData> listClass = new ArrayList<>();
            listClass.add(new ItemData(rootView.getResources().getString(R.string.cra), R.drawable.cra));
            listClass.add(new ItemData(getResources().getString(R.string.eca), R.drawable.eca));
            listClass.add(new ItemData(getResources().getString(R.string.elio), R.drawable.elio));
            listClass.add(new ItemData(getResources().getString(R.string.eni), R.drawable.eni));
            listClass.add(new ItemData(getResources().getString(R.string.enu), R.drawable.enu));
            listClass.add(new ItemData(getResources().getString(R.string.feca), R.drawable.feca));
            listClass.add(new ItemData(getResources().getString(R.string.hupp), R.drawable.hupp));
            listClass.add(new ItemData(getResources().getString(R.string.iop), R.drawable.iop));
            listClass.add(new ItemData(getResources().getString(R.string.osa), R.drawable.osa));
            listClass.add(new ItemData(getResources().getString(R.string.panda), R.drawable.panda));
            listClass.add(new ItemData(getResources().getString(R.string.lad), R.drawable.lad));
            listClass.add(new ItemData(getResources().getString(R.string.sac), R.drawable.sac));
            listClass.add(new ItemData(getResources().getString(R.string.sad), R.drawable.sad));
            listClass.add(new ItemData(getResources().getString(R.string.sram), R.drawable.sram));
            listClass.add(new ItemData(getResources().getString(R.string.steamer), R.drawable.steamer));
            listClass.add(new ItemData(getResources().getString(R.string.xelor), R.drawable.xelor));
            listClass.add(new ItemData(getResources().getString(R.string.zob), R.drawable.zob));
            final SpinnerAdapter adapter = new SpinnerAdapter(getActivity(), R.layout.row2, R.id.txTodos, listClass);
            spnClass.setAdapter(adapter);

            //spiner de elemento prioritario
            spnElementp = (Spinner) rootView.findViewById(R.id.spinnerelementp);
            ArrayList<ElementData> listElementp = new ArrayList<>();
            //fire comb
            listElementp.add(new ElementData(R.drawable.fire, R.drawable.water, R.drawable.air, R.drawable.earth)); //0
            listElementp.add(new ElementData(R.drawable.fire, R.drawable.water, R.drawable.earth, R.drawable.air)); //1
            listElementp.add(new ElementData(R.drawable.fire, R.drawable.air, R.drawable.water, R.drawable.earth)); //2
            listElementp.add(new ElementData(R.drawable.fire, R.drawable.air, R.drawable.earth, R.drawable.water)); //3
            listElementp.add(new ElementData(R.drawable.fire, R.drawable.earth, R.drawable.air, R.drawable.water)); //4
            listElementp.add(new ElementData(R.drawable.fire, R.drawable.earth, R.drawable.water, R.drawable.air)); //5
            //water comb
            listElementp.add(new ElementData(R.drawable.water, R.drawable.fire, R.drawable.air, R.drawable.earth)); //6
            listElementp.add(new ElementData(R.drawable.water, R.drawable.fire, R.drawable.earth, R.drawable.air)); //7
            listElementp.add(new ElementData(R.drawable.water, R.drawable.air, R.drawable.fire, R.drawable.earth)); //8
            listElementp.add(new ElementData(R.drawable.water, R.drawable.air, R.drawable.earth, R.drawable.fire)); //9
            listElementp.add(new ElementData(R.drawable.water, R.drawable.earth, R.drawable.air, R.drawable.fire)); //10
            listElementp.add(new ElementData(R.drawable.water, R.drawable.earth, R.drawable.fire, R.drawable.air)); //11
            //air comb
            listElementp.add(new ElementData(R.drawable.air, R.drawable.fire, R.drawable.water, R.drawable.earth)); //12
            listElementp.add(new ElementData(R.drawable.air, R.drawable.fire, R.drawable.earth, R.drawable.water)); //13
            listElementp.add(new ElementData(R.drawable.air, R.drawable.water, R.drawable.fire, R.drawable.earth)); //14
            listElementp.add(new ElementData(R.drawable.air, R.drawable.water, R.drawable.earth, R.drawable.fire)); //15
            listElementp.add(new ElementData(R.drawable.air, R.drawable.earth, R.drawable.fire, R.drawable.water)); //16
            listElementp.add(new ElementData(R.drawable.air, R.drawable.earth, R.drawable.water, R.drawable.fire)); //17
            //earth comb
            listElementp.add(new ElementData(R.drawable.earth, R.drawable.fire, R.drawable.air, R.drawable.water)); //18
            listElementp.add(new ElementData(R.drawable.earth, R.drawable.fire, R.drawable.water, R.drawable.air)); //19
            listElementp.add(new ElementData(R.drawable.earth, R.drawable.water, R.drawable.air, R.drawable.fire)); //20
            listElementp.add(new ElementData(R.drawable.earth, R.drawable.water, R.drawable.fire, R.drawable.air)); //21
            listElementp.add(new ElementData(R.drawable.earth, R.drawable.air, R.drawable.water, R.drawable.fire)); //22
            listElementp.add(new ElementData(R.drawable.earth, R.drawable.air, R.drawable.fire, R.drawable.water)); //23
            SpinnerAdapterEle adapterElep = new SpinnerAdapterEle(getActivity(), R.layout.row3, R.id.imele1, listElementp);
            spnElementp.setAdapter(adapterElep);

            //spinner resistencia prioritaria
            spnresp = (Spinner) rootView.findViewById(R.id.spinnerresistp);
            ArrayList<ElementData> listresp = new ArrayList<>();
            //fire comb
            listresp.add(new ElementData(R.drawable.fire, R.drawable.water, R.drawable.air, R.drawable.earth));
            listresp.add(new ElementData(R.drawable.fire, R.drawable.water, R.drawable.earth, R.drawable.air));
            listresp.add(new ElementData(R.drawable.fire, R.drawable.air, R.drawable.water, R.drawable.earth));
            listresp.add(new ElementData(R.drawable.fire, R.drawable.air, R.drawable.earth, R.drawable.water));
            listresp.add(new ElementData(R.drawable.fire, R.drawable.earth, R.drawable.air, R.drawable.water));
            listresp.add(new ElementData(R.drawable.fire, R.drawable.earth, R.drawable.water, R.drawable.air));
            //water comb
            listresp.add(new ElementData(R.drawable.water, R.drawable.fire, R.drawable.air, R.drawable.earth));
            listresp.add(new ElementData(R.drawable.water, R.drawable.fire, R.drawable.earth, R.drawable.air));
            listresp.add(new ElementData(R.drawable.water, R.drawable.air, R.drawable.fire, R.drawable.earth));
            listresp.add(new ElementData(R.drawable.water, R.drawable.air, R.drawable.earth, R.drawable.fire));
            listresp.add(new ElementData(R.drawable.water, R.drawable.earth, R.drawable.air, R.drawable.fire));
            listresp.add(new ElementData(R.drawable.water, R.drawable.earth, R.drawable.fire, R.drawable.air));
            //air comb
            listresp.add(new ElementData(R.drawable.air, R.drawable.fire, R.drawable.water, R.drawable.earth));
            listresp.add(new ElementData(R.drawable.air, R.drawable.fire, R.drawable.earth, R.drawable.water));
            listresp.add(new ElementData(R.drawable.air, R.drawable.water, R.drawable.fire, R.drawable.earth));
            listresp.add(new ElementData(R.drawable.air, R.drawable.water, R.drawable.earth, R.drawable.fire));
            listresp.add(new ElementData(R.drawable.air, R.drawable.earth, R.drawable.fire, R.drawable.water));
            listresp.add(new ElementData(R.drawable.air, R.drawable.earth, R.drawable.water, R.drawable.fire));
            //earth comb
            listresp.add(new ElementData(R.drawable.earth, R.drawable.fire, R.drawable.air, R.drawable.water));
            listresp.add(new ElementData(R.drawable.earth, R.drawable.fire, R.drawable.water, R.drawable.air));
            listresp.add(new ElementData(R.drawable.earth, R.drawable.water, R.drawable.air, R.drawable.fire));
            listresp.add(new ElementData(R.drawable.earth, R.drawable.water, R.drawable.fire, R.drawable.air));
            listresp.add(new ElementData(R.drawable.earth, R.drawable.air, R.drawable.water, R.drawable.fire));
            listresp.add(new ElementData(R.drawable.earth, R.drawable.air, R.drawable.fire, R.drawable.water));
            SpinnerAdapterEle adapterresp = new SpinnerAdapterEle(getActivity(), R.layout.row3, R.id.imele1, listresp);
            spnresp.setAdapter(adapterresp);

            //texto nome
            txnome = (EditText) rootView.findViewById(R.id.txNome);

            cancelar = (Button) rootView.findViewById(R.id.btnCancel);
            cancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cancelar();
                }
            });

            //botão limpar
            limpar = (Button) rootView.findViewById(R.id.btnLimpar);
            limpar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Limpar();
                }
            });

            criar = (Button) rootView.findViewById(R.id.btnCriar);
            criar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Criar(v);
                }
            });

            numberpickerLevel = (TextView) rootView.findViewById(R.id.Numberpickerlevel);
            numberpickerLevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowNumberPicker(numberpickerLevel);
                }
            });
        }
        return rootView;
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
