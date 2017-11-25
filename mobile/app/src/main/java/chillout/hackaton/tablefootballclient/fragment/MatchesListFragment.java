package chillout.hackaton.tablefootballclient.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chillout.hackaton.tablefootballclient.R;
import chillout.hackaton.tablefootballclient.TFCApplication;
import chillout.hackaton.tablefootballclient.api.ApiClient;
import chillout.hackaton.tablefootballclient.api.TableFootballService;
import chillout.hackaton.tablefootballclient.api.response.BasicUser;
import chillout.hackaton.tablefootballclient.api.response.MatchResponse;
import chillout.hackaton.tablefootballclient.components.adapter.MatchesRecyclerViewAdapter;
import chillout.hackaton.tablefootballclient.defs.MatchState;
import chillout.hackaton.tablefootballclient.model.MatchInfo;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MatchesListFragment extends Fragment {

    private View mFragmentView;
    private RecyclerView mRecyclerView;
    private MatchesRecyclerViewAdapter mRvAdapter;

    private GetMatchesAsyncTask getMatchesTask = null;

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

        mProgressBar = (ProgressBar) mFragmentView.findViewById(R.id.progressbar);
        setupRecyclerView();

        //mContentView = (LinearLayout) mFragmentView.findViewById(R.id.fragment_content);
        getMatches();
        return mFragmentView;
    }

    private void setupRecyclerView() {
        mRvAdapter = new MatchesRecyclerViewAdapter(new ArrayList<MatchInfo>(),getActivity());

        mRecyclerView.setAdapter(mRvAdapter);
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

    private void getMatches() {
        showProgress(true);
        getMatchesTask = new GetMatchesAsyncTask();
        getMatchesTask.execute((Void)null);
    }


    public class GetMatchesAsyncTask extends AsyncTask<Void, Void, List<MatchInfo>> {

        @Override
        protected List<MatchInfo> doInBackground(Void... params) {
            TableFootballService apiService = ApiClient.instance();
            Call<List<MatchResponse>> call = apiService.getAllMatches(TFCApplication.getTOKEN());

            try {
                Response<List<MatchResponse>> response = call.execute();
                if(response.isSuccessful()){
                    List<MatchResponse> responseList = response.body();

                    List<MatchInfo> resultlList = new ArrayList<>();
                    for (MatchResponse resp : responseList) {
                        resultlList.add(MatchInfo.mapMatchResponse(resp));
                    }
                    return resultlList;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<MatchInfo> list) {
            getMatchesTask = null;

            if (list != null) {
                showProgress(false);
                mRvAdapter.setMatchInfoList(list);
            } else {
                Snackbar.make(getActivity().findViewById(R.id.drawer_layout), "Connection Error", Snackbar.LENGTH_LONG)
                        .setAction("Retry", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getMatches();
                            }
                        }).show();
            }
        }

        @Override
        protected void onCancelled() {
            getMatchesTask = null;
            showProgress(false);
        }
    }


}
