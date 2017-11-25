package chillout.hackaton.tablefootballclient.api.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.List;

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


    public class Team {

        @SerializedName("players")
        @Expose
        private List<Integer> players = null;

        public List<Integer> getPlayers() {
            return players;
        }

        public void setPlayers(List<Integer> players) {
            this.players = players;
        }

    }
}
