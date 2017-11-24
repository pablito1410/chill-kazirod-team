package chillout.hackaton.tablefootballclient.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import chillout.hackaton.tablefootballclient.R;
import chillout.hackaton.tablefootballclient.components.adapter.MatchesRecyclerViewAdapter;
import chillout.hackaton.tablefootballclient.model.MatchInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesListFragment extends Fragment {

    private View mFragmentView;
    private RecyclerView mRecyclerView;

    public MatchesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_matches_list, container, false);
        mRecyclerView = (RecyclerView) mFragmentView.findViewById(R.id.matches_list);
        setupRecyclerView();

        return mFragmentView;
    }

    private void setupRecyclerView() {

        mRecyclerView.setAdapter(new MatchesRecyclerViewAdapter(mockData()));
    }

    private List<MatchInfo> mockData(){
        List<MatchInfo> matchInfoList = new ArrayList<>();
        MatchInfo mockdata = new MatchInfo("buźka",
                "wojtek",
                "tomek",
                "pusty",
                0,
                3);
        for(int i = 0; i < 20; i++) {
            matchInfoList.add(mockdata);
        }
        return matchInfoList;
    }
}
