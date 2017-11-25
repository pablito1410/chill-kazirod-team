package chillout.hackaton.tablefootballclient.components.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import chillout.hackaton.tablefootballclient.R;
import chillout.hackaton.tablefootballclient.api.response.BasicUser;

/**
 * Created by matlo on 25.11.2017.
 */

public class PlayerRecyclerViewAdapter extends RecyclerView.Adapter<PlayerRecyclerViewAdapter.ViewHolder>  {

    private List<BasicUser> basicUserList;

    public PlayerRecyclerViewAdapter(List<BasicUser> basicUserList) {
        this.basicUserList = basicUserList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_select_player, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BasicUser user = basicUserList.get(position);
        holder.usernameTV.setText(user.getUserName());
    }

    @Override
    public int getItemCount() {
        return basicUserList.size();
    }

    public void setBasicUserList(List<BasicUser> basicUserList) {
        this.basicUserList = basicUserList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView usernameTV;

        public ViewHolder(View itemView) {
            super(itemView);

            usernameTV = itemView.findViewById(R.id.user_name);
        }
    }
}
