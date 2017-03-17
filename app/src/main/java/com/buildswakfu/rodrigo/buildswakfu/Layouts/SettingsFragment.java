package com.buildswakfu.rodrigo.buildswakfu.Layouts;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.buildswakfu.rodrigo.buildswakfu.MainActivity;
import com.buildswakfu.rodrigo.buildswakfu.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.vision.text.Text;
import com.squareup.picasso.Picasso;

import static com.google.android.gms.wearable.DataMap.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView userPhoto;
    private ImageView userLang;
    private TextView userName;
    private TextView userLangChange;
    private ImageButton userLogout;
    private View rootView;
    private final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE=2;

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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

    private boolean showPhoneStatePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                rootView.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {
            return true;
        }
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        userPhoto = (ImageView) rootView.findViewById(R.id.user_img);
        userName = (TextView) rootView.findViewById(R.id.user_name);
        userLogout = (ImageButton) rootView.findViewById(R.id.user_logout);
        userLang = (ImageView) rootView.findViewById(R.id.user_lang);
        userLangChange = (TextView) rootView.findViewById(R.id.user_langChange);
        userLangChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificaLang();
            }
        });
        userLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.user!=null){
                    signOut();
                    userPhoto.setImageDrawable(getResources().getDrawable(R.drawable.wakfustatus_icon));
                    userName.setText("Username");
                    userLogout.setImageDrawable(getResources().getDrawable(R.drawable.login));
                    MainActivity.user=null;
                }else{
                    Intent i = getActivity().getIntent();
                    getActivity().finish();
                    startActivity(i);
                }
            }
        });

        if(MainActivity.user!=null) {
            Picasso.with(rootView.getContext()).load(MainActivity.user.getPhotoUrl()).into(userPhoto);
            userName.setText(MainActivity.user.getName());
            switch (MainActivity.user.getLang()){
                case "pt":
                    userLang.setImageDrawable(getResources().getDrawable(R.drawable.br));
                    break;
                case "en":
                    userLang.setImageDrawable(getResources().getDrawable(R.drawable.us));
                    break;
                case "fr":
                    userLang.setImageDrawable(getResources().getDrawable(R.drawable.fr));
                    break;
                case "es":
                    userLang.setImageDrawable(getResources().getDrawable(R.drawable.es));
                    break;
                default:
                    userLang.setImageDrawable(getResources().getDrawable(R.drawable.us));
            }

        }

        return rootView;
    }

    private void VerificaLang(){
        final CharSequence[] items = getResources().getStringArray(R.array.idiomas);
        AlertDialog dialog = new AlertDialog.Builder(this.getContext())
                .setTitle(R.string.select_lang)
                .setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                if(!MainActivity.user.getLang().equals("en")) {
                                    MainActivity.user.setLang("en");
                                    MainActivity.user.setLastSync(0);
                                    MainActivity.mDatabase.child("users/" + MainActivity.user.getUserID()).setValue(MainActivity.user);
                                    MainActivity.prefs.edit().putString("idioma", MainActivity.user.getLang()).commit();
                                    Intent i = getActivity().getIntent();
                                    getActivity().finish();
                                    startActivity(i);
                                }
                                break;
                            case 2:
                                if(!MainActivity.user.getLang().equals("fr")) {
                                    MainActivity.user.setLang("fr");
                                    MainActivity.user.setLastSync(0);
                                    MainActivity.mDatabase.child("users/" + MainActivity.user.getUserID()).setValue(MainActivity.user);
                                    MainActivity.prefs.edit().putString("idioma", MainActivity.user.getLang()).commit();
                                    Intent i = getActivity().getIntent();
                                    getActivity().finish();
                                    startActivity(i);
                                }
                                break;
                            case 1:
                                if(!MainActivity.user.getLang().equals("pt")) {
                                    MainActivity.user.setLang("pt");
                                    MainActivity.user.setLastSync(0);
                                    MainActivity.mDatabase.child("users/" + MainActivity.user.getUserID()).setValue(MainActivity.user);
                                    MainActivity.prefs.edit().putString("idioma", MainActivity.user.getLang()).commit();
                                    Intent i = getActivity().getIntent();
                                    getActivity().finish();
                                    startActivity(i);
                                }
                                break;
                            case 3:
                                if(!MainActivity.user.getLang().equals("es")) {
                                    MainActivity.user.setLang("es");
                                    MainActivity.user.setLastSync(0);
                                    MainActivity.mDatabase.child("users/" + MainActivity.user.getUserID()).setValue(MainActivity.user);
                                    MainActivity.prefs.edit().putString("idioma", MainActivity.user.getLang()).commit();
                                    Intent i = getActivity().getIntent();
                                    getActivity().finish();
                                    startActivity(i);
                                }
                                break;
                            default:
                                MainActivity.user.setLang("en");
                        }
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    public void signOut() {
        MainActivity.mAuth.signOut();
        Auth.GoogleSignInApi.signOut(MainActivity.mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // ...
                    }
                });
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