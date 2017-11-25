package chillout.hackaton.tablefootballclient.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import chillout.hackaton.tablefootballclient.R;
import chillout.hackaton.tablefootballclient.components.adapter.MatchesRecyclerViewAdapter;
import chillout.hackaton.tablefootballclient.defs.MatchState;
import chillout.hackaton.tablefootballclient.model.MatchInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesListFragment extends Fragment {

    private View mFragmentView;
    private RecyclerView mRecyclerView;

    private ProgressBar mProgressBar;
    //private View mContentView;


    public MatchesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mFragmentView = inflater.inflate(R.layout.fragment_matches_list, container, false);
        mRecyclerView = (RecyclerView) mFragmentView.findViewById(R.id.matches_list);
        setupRecyclerView();

        //mContentView = (LinearLayout) mFragmentView.findViewById(R.id.fragment_content);
        mProgressBar = (ProgressBar) mFragmentView.findViewById(R.id.progressbar);

        return mFragmentView;
    }

    private void setupRecyclerView() {

        mRecyclerView.setAdapter(new MatchesRecyclerViewAdapter(mockData(),getActivity()));
    }

    private List<MatchInfo> mockData(){
        List<MatchInfo> matchInfoList = new ArrayList<>();
        for(long i = 0; i < 20; i++) {
            matchInfoList.add(new MatchInfo(i, i*10, i*20, "buźka",
                    "wojtek",
                    "tomek",
                    "pusty",
                    MatchState.ramdomState(), 0,
                    3));
        }
        return matchInfoList;
    }




    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
        mRecyclerView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mRecyclerView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressBar.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }


}
