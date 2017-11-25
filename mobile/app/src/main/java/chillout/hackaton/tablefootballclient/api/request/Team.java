package chillout.hackaton.tablefootballclient.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by matlo on 25.11.2017.
 */

public class Team {

    @SerializedName("name")
    @Expose
    private String name;


    @SerializedName("firstPlayer")
    @Expose
    private Long firstPlayerId;


    @SerializedName("secondPlayer")
    @Expose
    private Long secondPlayerId;

    public Team(String name, Long firstPlayerId, Long secondPlayerId) {
        this.name = name;
        this.firstPlayerId = firstPlayerId;
        this.secondPlayerId = secondPlayerId;
    }

    public String getName() {
        return name;
    }

    public Long getFirstPlayerId() {
        return firstPlayerId;
    }

    public Long getSecondPlayerId() {
        return secondPlayerId;
    }
}
