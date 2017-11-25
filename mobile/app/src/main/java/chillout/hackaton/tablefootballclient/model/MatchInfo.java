package chillout.hackaton.tablefootballclient.model;

import chillout.hackaton.tablefootballclient.api.response.MatchResponse;
import chillout.hackaton.tablefootballclient.defs.MatchState;

/**
 * Created by matlo on 24.11.2017.
 */

public class MatchInfo {
    private Long id;

    private Long teamOneId;
    private Long teamTwoId;

    private String playerOneName;
    private String playerTwoName;
    private String playerThreeName;
    private String playerFourName;

    private MatchState matchState;

    private Integer teamOneScore;
    private Integer teamTwoScore;

    public MatchInfo(Long id, Long teamOneId, Long teamTwoId, String playerOneName, String playerTwoName, String playerThreeName, String playerFourName, MatchState matchState, Integer teamOneScore, Integer teamTwoScore) {
        this.id = id;
        this.teamOneId = teamOneId;
        this.teamTwoId = teamTwoId;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.playerThreeName = playerThreeName;
        this.playerFourName = playerFourName;
        this.matchState = matchState;
        this.teamOneScore = teamOneScore;
        this.teamTwoScore = teamTwoScore;
    }

    public Long getId() {
        return id;
    }

    public Long getTeamOneId() {
        return teamOneId;
    }

    public Long getTeamTwoId() {
        return teamTwoId;
    }

    public MatchState getMatchState() {
        return matchState;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public String getPlayerThreeName() {
        return playerThreeName;
    }

    public String getPlayerFourName() {
        return playerFourName;
    }

    public Integer getTeamOneScore() {
        return teamOneScore;
    }

    public Integer getTeamTwoScore() {
        return teamTwoScore;
    }


//    public static MatchInfo mapMatchResponse (MatchResponse response) {
//        return new MatchInfo(response.getId(), response.getFirstTeam())
//    }
}
