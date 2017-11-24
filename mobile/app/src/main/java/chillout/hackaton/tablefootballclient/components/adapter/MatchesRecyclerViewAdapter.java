package chillout.hackaton.tablefootballclient.components.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import chillout.hackaton.tablefootballclient.R;
import chillout.hackaton.tablefootballclient.model.MatchInfo;

/**
 * Created by matlo on 24.11.2017.
 */

public class MatchesRecyclerViewAdapter extends RecyclerView.Adapter<MatchesRecyclerViewAdapter.ViewHolder> {

    private List<MatchInfo> matchInfoList;

    public MatchesRecyclerViewAdapter(List<MatchInfo> matchInfoList) {
        this.matchInfoList = matchInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_matches, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MatchInfo matchInfo = matchInfoList.get(position);

        holder.playerOneNameTV.setText(matchInfo.getPlayerOneName());
        holder.playerTwoNameTV.setText(matchInfo.getPlayerTwoName());
        holder.playerThreeNameTV.setText(matchInfo.getPlayerThreeName());
        holder.playerFourNameTV.setText(matchInfo.getPlayerFourName());

        holder.teamOneScoreTV.setText(Integer.toString(matchInfo.getTeamOneScore()));
        holder.teamTwoScoreTV.setText(Integer.toString(matchInfo.getTeamTwoScore()));
    }

    @Override
    public int getItemCount() {
        return matchInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView playerOneNameTV;
        TextView playerTwoNameTV;
        TextView playerThreeNameTV;
        TextView playerFourNameTV;

        TextView teamOneScoreTV;
        TextView teamTwoScoreTV;

        //TODO


        public ViewHolder(View itemView) {
            super(itemView);

            playerOneNameTV = itemView.findViewById(R.id.player1name);
            playerTwoNameTV = itemView.findViewById(R.id.player2name);
            playerThreeNameTV = itemView.findViewById(R.id.player3name);
            playerFourNameTV = itemView.findViewById(R.id.player4name);

            teamOneScoreTV = itemView.findViewById(R.id.team1Score);
            teamTwoScoreTV = itemView.findViewById(R.id.team2Score);
        }
    }
}
