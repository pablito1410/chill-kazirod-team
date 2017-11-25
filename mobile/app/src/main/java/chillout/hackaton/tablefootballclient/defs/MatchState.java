package chillout.hackaton.tablefootballclient.defs;

import java.util.Arrays;
import java.util.Random;

import chillout.hackaton.tablefootballclient.R;

/**
 * Created by matlo on 25.11.2017.
 */

public enum MatchState {

    CREATED(R.color.blue,"Created"),
    ACCEPTED(R.color.yellow,"Accepted"),
    FINISHED(R.color.green,"Finished"),
    CANCELLED(R.color.red,"Cancelled")
    ;

    private Integer resourceTextColor;
    private String text;


    MatchState(Integer resourceTextColor, String text) {
        this.resourceTextColor = resourceTextColor;
        this.text = text;
    }

    public Integer getResourceTextColor() {
        return resourceTextColor;
    }

    public String getText() {
        return text;
    }

    public static MatchState ramdomState()  {
        return Arrays.asList(values()).get(new Random().nextInt(4));
    }
}
