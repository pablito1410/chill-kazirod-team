package chillout.hackaton.tablefootballclient.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import chillout.hackaton.tablefootballclient.defs.MatchState;

/**
 * Created by matlo on 25.11.2017.
 */

public class MatchResponse extends BasicId {

    @SerializedName("state")
    @Expose
    private MatchState state;

    @SerializedName("firstTeam")
    @Expose
    private ProjectionTeam firstTeam;

    @SerializedName("secondTeam")
    @Expose
    private ProjectionTeam secondTeam;

//    @SerializedName("winner")
//    @Expose
//    private ProjectionTeam winner;

    @SerializedName("firstScore")
    @Expose
    private Integer firstScore;

    @SerializedName("secondScore")
    @Expose
    private Integer secondScore;


    public Integer getFirstScore() {
        return firstScore;
    }

    public Integer getSecondScore() {
        return secondScore;
    }

    public MatchState getState() {
        return state;
    }

    public void setState(MatchState state) {
        this.state = state;
    }

    public ProjectionTeam getFirstTeam() {
        return firstTeam;
    }

    public void setFirstTeam(ProjectionTeam firstTeam) {
        this.firstTeam = firstTeam;
    }

    public ProjectionTeam getSecondTeam() {
        return secondTeam;
    }

    public void setSecondTeam(ProjectionTeam secondTeam) {
        this.secondTeam = secondTeam;
    }

    public class ProjectionTeam extends BasicId {

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("firstPlayer")
        @Expose
        private BasicUser firstPlayer;

        @SerializedName("secondPlayer")
        @Expose
        private BasicUser secondPlayer;

        public ProjectionTeam(String name, BasicUser firstPlayer, BasicUser secondPlayer) {
            this.name = name;
            this.firstPlayer = firstPlayer;
            this.secondPlayer = secondPlayer;
        }

        public String getName() {
            return name;
        }

        public BasicUser getFirstPlayer() {
            return firstPlayer;
        }

        public BasicUser getSecondPlayer() {
            return secondPlayer;
        }
    }
}
