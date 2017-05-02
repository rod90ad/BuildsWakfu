package com.buildswakfu.rodrigo.buildswakfu.Layouts;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.buildswakfu.rodrigo.buildswakfu.Layouts.Decks.DeckActivesFragment;
import com.buildswakfu.rodrigo.buildswakfu.Layouts.Decks.DeckPassivesFragment;
import com.buildswakfu.rodrigo.buildswakfu.Layouts.PointsFragments.AgiFragment;
import com.buildswakfu.rodrigo.buildswakfu.Layouts.PointsFragments.ChanceFragment;
import com.buildswakfu.rodrigo.buildswakfu.Layouts.PointsFragments.IntFragment;
import com.buildswakfu.rodrigo.buildswakfu.Layouts.PointsFragments.MajorFragment;
import com.buildswakfu.rodrigo.buildswakfu.Layouts.PointsFragments.StrFragment;
import com.buildswakfu.rodrigo.buildswakfu.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DecksFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DecksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DecksFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static ViewBuildFragment viewBuildFragment;
    private MajorFragment majorFragment;
    private View rootView;
    private FragmentTabHost tabHost;

    private OnFragmentInteractionListener mListener;

    public DecksFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DecksFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DecksFragment newInstance(ViewBuildFragment param1, String param2) {
        DecksFragment fragment = new DecksFragment();
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
        if(tabHost==null) {
            tabHost = new FragmentTabHost(getActivity());
            tabHost.setup(getActivity(), getChildFragmentManager(), R.layout.fragment_decks);
            tabHost.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

            String[] names = getResources().getStringArray(R.array.deckscreens);

            Bundle arg1 = new Bundle();
            arg1.putInt(names[0], 1);
            tabHost.addTab(tabHost.newTabSpec(names[0]).setIndicator(names[0]), DeckActivesFragment.newInstance(viewBuildFragment, "B").getClass(), arg1);

            Bundle arg2 = new Bundle();
            arg2.putInt(names[1], 2);
            tabHost.addTab(tabHost.newTabSpec(names[1]).setIndicator(names[1]), DeckPassivesFragment.newInstance(viewBuildFragment, "B").getClass(), arg2);
        }
        return tabHost;
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
