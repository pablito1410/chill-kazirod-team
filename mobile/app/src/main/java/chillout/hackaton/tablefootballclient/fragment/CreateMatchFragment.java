package chillout.hackaton.tablefootballclient.fragment;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import org.joda.time.DateTime;

import chillout.hackaton.tablefootballclient.R;
import chillout.hackaton.tablefootballclient.activity.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateMatchFragment extends Fragment {


    private View mFragmentView;

    private DateTime matchTime = DateTime.now();

    private Button timeButton;


    public CreateMatchFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentView = inflater.inflate(R.layout.fragment_create_match, container, false);
        timeButton = (Button)  mFragmentView.findViewById(R.id.timeEditText);

        setupTimeButton();
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

}
