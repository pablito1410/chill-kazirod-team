package chillout.hackaton.tablefootballclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import chillout.hackaton.tablefootballclient.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesListFragment extends Fragment {

    private View mFragmentView;

    public MatchesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_matches_list, container, false);



        return mFragmentView;
    }

}
