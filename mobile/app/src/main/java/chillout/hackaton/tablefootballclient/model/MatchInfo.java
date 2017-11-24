package chillout.hackaton.tablefootballclient.model;

/**
 * Created by matlo on 24.11.2017.
 */

public class MatchInfo {
    private String playerOneName;
    private String playerTwoName;
    private String playerThreeName;
    private String playerFourName;

    private Integer teamOneScore;
    private Integer teamTwoScore;

    public MatchInfo(String playerOneName, String playerTwoName, String playerThreeName, String playerFourName, Integer teamOneScore, Integer teamTwoScore) {
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.playerThreeName = playerThreeName;
        this.playerFourName = playerFourName;
        this.teamOneScore = teamOneScore;
        this.teamTwoScore = teamTwoScore;
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
}
