package chillout.hackaton.tablefootballclient.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

/**
 * Created by matlo on 25.11.2017.
 */

public class CreateMatchRequest {

    @SerializedName("firstTeam")
    @Expose
    private Team firstTeam;
    @SerializedName("secondTeam")
    @Expose
    private Team secondTeam;
    @SerializedName("dateTime")
    @Expose
    private DateTime dateTime;

    public CreateMatchRequest(Team firstTeam, Team secondTeam, DateTime dateTime) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.dateTime = dateTime;
    }

    public Team getFirstTeam() {
        return firstTeam;
    }

    public Team getSecondTeam() {
        return secondTeam;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Created by matlo on 25.11.2017.
     */

    public static class Team {

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
}
