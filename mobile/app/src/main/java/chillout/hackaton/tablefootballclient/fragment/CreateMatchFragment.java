package chillout.hackaton.tablefootballclient.fragment;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TimePicker;

import org.joda.time.DateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chillout.hackaton.tablefootballclient.R;
import chillout.hackaton.tablefootballclient.activity.MainActivity;
import chillout.hackaton.tablefootballclient.api.ApiClient;
import chillout.hackaton.tablefootballclient.api.TableFootballService;
import chillout.hackaton.tablefootballclient.api.response.BasicUser;
import retrofit2.Call;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateMatchFragment extends Fragment {


    private View mFragmentView;

    private DateTime matchTime = DateTime.now();

    private Button timeButton;

    private ProgressBar mProgressBar;
    private View mContentView;

    private GetUsersToPlayAsyncTask mGetUsersTask = null;

    List<BasicUser> usersToChoose = new ArrayList<>();

    public CreateMatchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentView = inflater.inflate(R.layout.fragment_create_match, container, false);
        timeButton = (Button)  mFragmentView.findViewById(R.id.timeEditText);

        mContentView = (LinearLayout) mFragmentView.findViewById(R.id.fragment_content);
        mProgressBar = (ProgressBar) mFragmentView.findViewById(R.id.progressbar);
        setupTimeButton();
        getUsers();
        return mFragmentView;
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
            Call<List<BasicUser>> call = apiService.getAllUsers();

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
                usersToChoose = list;
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
}
