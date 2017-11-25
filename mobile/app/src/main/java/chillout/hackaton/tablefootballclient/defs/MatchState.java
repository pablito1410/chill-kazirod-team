package chillout.hackaton.tablefootballclient.defs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import chillout.hackaton.tablefootballclient.R;

/**
 * Created by matlo on 25.11.2017.
 */

public enum MatchState {

    CREATED(R.color.blue,"CREATED"),
    ACCEPTED(R.color.yellow,"ACCEPTED"),
    FINISHED(R.color.green,"FINISHED"),
    CANCELLED(R.color.red,"CANCELLED")
    ;

    private Integer resourceTextColor;
    private String text;


    MatchState(Integer resourceTextColor, String text) {
        this.resourceTextColor = resourceTextColor;
        this.text = text;
    }

    public static Map<String, MatchState> map = new HashMap<String, MatchState>();
    static {
        for (MatchState userType : MatchState.values()) {
            map.put(userType.name(), userType);
        }
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
