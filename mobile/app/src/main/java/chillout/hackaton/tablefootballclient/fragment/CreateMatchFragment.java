package chillout.hackaton.tablefootballclient.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chillout.hackaton.tablefootballclient.R;
import chillout.hackaton.tablefootballclient.TFCApplication;
import chillout.hackaton.tablefootballclient.activity.MainActivity;
import chillout.hackaton.tablefootballclient.api.ApiClient;
import chillout.hackaton.tablefootballclient.api.TableFootballService;
import chillout.hackaton.tablefootballclient.api.request.CreateMatchRequest;
import chillout.hackaton.tablefootballclient.api.response.BasicUser;
import chillout.hackaton.tablefootballclient.components.adapter.PlayerRecyclerViewAdapter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateMatchFragment extends Fragment {


    private View mFragmentView;

    private DateTime matchTime = DateTime.now();

    private Button timeButton;

    private RecyclerView mRecyclerView;
    private PlayerRecyclerViewAdapter mRvAdapter;

    private ProgressBar mProgressBar;
    private View mContentView;

    private GetUsersToPlayAsyncTask mGetUsersTask = null;
    private CreateMatchAsyncTask mCreateMatchTask = null;

    private List<BasicUser> myTeam = new ArrayList<>();
    private List<BasicUser> oppositeTeam = new ArrayList<>();

    List<BasicUser> usersToChoose = new ArrayList<>();

    private Button createButton;

    //HashMap<Integer,BasicUser> playersHashMap = new HashMap<>();

    public CreateMatchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentView = inflater.inflate(R.layout.fragment_create_match2, container, false);
        timeButton = (Button)  mFragmentView.findViewById(R.id.timeEditText);

        mContentView = (LinearLayout) mFragmentView.findViewById(R.id.fragment_content);
        mProgressBar = (ProgressBar) mFragmentView.findViewById(R.id.progressbar);
        mRecyclerView = (RecyclerView) mFragmentView.findViewById(R.id.matches_list);
        setupRecyclerView();
        setupTimeButton();


        registerForContextMenu(mFragmentView);

        //TODO odkomentowac kiedy service bedzie
        getUsers();
        //mockUsers();

        createButton = (Button) mFragmentView.findViewById(R.id.createMatchButton);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((myTeam.size() + oppositeTeam.size())== 4) {
                    createMatch();
                }
            }
        });
        return mFragmentView;
    }

    private void setupRecyclerView() {
        mRvAdapter = new PlayerRecyclerViewAdapter(usersToChoose);
        mRecyclerView.setAdapter(mRvAdapter);
    }

    private void addToTeam(List<BasicUser> team, BasicUser user) {
        if(team.size() < 2) {
            team.add(user);
            mRvAdapter.addToChosenPlayers();
            reloadTeamUI();
        }
    }

    private void reloadTeamUI() {
       if(myTeam.size() > 0) {
           ((TextView)mFragmentView.findViewById(R.id.player1name)).setText(myTeam.get(0).getUserName());
       }
       if(myTeam.size() > 1) {
           ((TextView)mFragmentView.findViewById(R.id.player2name)).setText(myTeam.get(1).getUserName());
       }
        if(oppositeTeam.size() > 0) {
            ((TextView)mFragmentView.findViewById(R.id.player3name)).setText(oppositeTeam.get(0).getUserName());
        }
        if(oppositeTeam.size() > 1) {
            ((TextView)mFragmentView.findViewById(R.id.player4name)).setText(oppositeTeam.get(1).getUserName());
        }

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        try {
            position = mRvAdapter.getPosition();
        } catch (Exception e) {
            //Log.d(e.getLocalizedMessage().);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.toMyTeam:
               addToTeam(myTeam,mRvAdapter.getItem());
                break;
            case R.id.toOppositeTeam:
                addToTeam(oppositeTeam,mRvAdapter.getItem());
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void setupTimeButton() {
        timeButton.setText(matchTime.toString("hh:mm"));
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                       matchTime = DateTime.now().withTime(selectedHour,selectedMinute,0,0);
                       timeButton.setText(matchTime.toString("hh:mm"));
                    }
                }, matchTime.getHourOfDay(), matchTime.getMinuteOfHour(),true);
                mTimePicker.show();
            }
        });
    }

    @Override
    public void onResume() {

        super.onResume();
        ((MainActivity) getActivity()).setFabVisibility(View.GONE);
    }

    @Override
    public void onPause() {

        super.onPause();
        ((MainActivity) getActivity()).setFabVisibility(View.VISIBLE);
    }

    private void getUsers() {
        showProgress(true);
        mGetUsersTask = new GetUsersToPlayAsyncTask();
        mGetUsersTask.execute(((Void) null));
    }

    private void createMatch() {
        showProgress(true);
        StringBuilder teamNameBuilder = new StringBuilder();
        for (BasicUser user: myTeam) {
            teamNameBuilder.append(user.getUserName());
        }
        Long firstId = myTeam.get(0).getId();
        Long secondId = myTeam.get(1).getId();

        CreateMatchRequest.Team firstTeam = new CreateMatchRequest.Team(teamNameBuilder.toString(),firstId,secondId);

        teamNameBuilder = new StringBuilder();
        for (BasicUser user:  oppositeTeam) {
            teamNameBuilder.append(user.getUserName());
        }
        firstId = oppositeTeam.get(0).getId();
        secondId =  oppositeTeam.get(1).getId();
        CreateMatchRequest.Team secondTeam = new CreateMatchRequest.Team(teamNameBuilder.toString(),firstId,secondId);
        mCreateMatchTask = new CreateMatchAsyncTask(firstTeam,secondTeam,matchTime);
        mCreateMatchTask.execute((Void)null);
    }


    private void mockUsers() {
        showProgress(true);
        for(long i = 0; i < 10; i++) {
            usersToChoose.add(new BasicUser(i, "user" + i));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mRvAdapter.setBasicUserList(usersToChoose);
        showProgress(false);
    }

    private void showProgress(final boolean show) {
        int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

        mContentView.setVisibility(show ? View.GONE : View.VISIBLE);
        mContentView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mContentView.setVisibility(show ? View.GONE : View.VISIBLE);
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


    public class GetUsersToPlayAsyncTask extends AsyncTask<Void, Void, List<BasicUser>> {


        @Override
        protected List<BasicUser> doInBackground(Void... params) {
            TableFootballService apiService = ApiClient.instance();
            Call<List<BasicUser>> call = apiService.getAllUsers(TFCApplication.getTOKEN());

            try {
                Response<List<BasicUser>> response = call.execute();
                if(response.isSuccessful()){
                    return response.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<BasicUser> list) {
            mGetUsersTask = null;

            if (list != null) {
                showProgress(false);
                mRvAdapter.setBasicUserList(list);
            } else {
                Snackbar.make(getActivity().findViewById(R.id.drawer_layout), "Connection Error", Snackbar.LENGTH_LONG)
                       .setAction("Retry", new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                            getUsers();
                           }
                       }).show();
            }
        }

        @Override
        protected void onCancelled() {
            mGetUsersTask = null;
            showProgress(false);
        }
    }



    public class CreateMatchAsyncTask extends AsyncTask<Void, Void, Boolean> {

        private CreateMatchRequest.Team firstTeam;
        private CreateMatchRequest.Team secondTeam;
        private DateTime dateTime;

        public CreateMatchAsyncTask(CreateMatchRequest.Team firstTeam, CreateMatchRequest.Team secondTeam, DateTime dateTime) {
            this.firstTeam = firstTeam;
            this.secondTeam = secondTeam;
            this.dateTime = dateTime;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            CreateMatchRequest request = new CreateMatchRequest(firstTeam,secondTeam,dateTime);
            TableFootballService apiService = ApiClient.instance();
            Call<ResponseBody> call = apiService.createMatch(TFCApplication.getTOKEN(),request);

            try {
                Response<ResponseBody> response = call.execute();
                if(response.isSuccessful()){
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(final Boolean result) {
            mCreateMatchTask = null;

            if (result != null) {
                if (result) {
                    showProgress(false);
                    Snackbar.make(getActivity().findViewById(R.id.drawer_layout), "Done", Snackbar.LENGTH_LONG)
                            .show();
                } else {
                    Snackbar.make(getActivity().findViewById(R.id.drawer_layout), "Connection Error", Snackbar.LENGTH_LONG)
                            .setAction("Retry", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    createMatch();
                                }
                            }).show();
                }
            }
        }

        @Override
        protected void onCancelled() {
            mCreateMatchTask = null;
            showProgress(false);
        }
    }
}
