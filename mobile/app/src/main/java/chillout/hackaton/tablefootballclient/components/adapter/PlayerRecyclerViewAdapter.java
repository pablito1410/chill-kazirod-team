package chillout.hackaton.tablefootballclient.components.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chillout.hackaton.tablefootballclient.R;
import chillout.hackaton.tablefootballclient.api.response.BasicUser;

/**
 * Created by matlo on 25.11.2017.
 */

public class PlayerRecyclerViewAdapter extends RecyclerView.Adapter<PlayerRecyclerViewAdapter.ViewHolder>  {

    private Integer position;
    private List<BasicUser> basicUserList;

    private List<Integer> choosenPlayers = new ArrayList<>();

    public PlayerRecyclerViewAdapter(List<BasicUser> basicUserList) {
        this.basicUserList = basicUserList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_select_player, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        BasicUser user = basicUserList.get(position);
        holder.usernameTV.setText(user.getUserName());
        if(choosenPlayers.size() >= 4 || choosenPlayers.contains(position)) {
            holder.itemView.setAlpha(0.4f);
            holder.itemView.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return basicUserList.size();
    }

    public void setBasicUserList(List<BasicUser> basicUserList) {
        this.basicUserList = basicUserList;
        notifyDataSetChanged();
    }

    public void addToChosenPlayers() {
        choosenPlayers.add(position);
        notifyDataSetChanged();
    }

    public BasicUser getItem() {
        if(position != null){
            return basicUserList.get(position);
        }
        return null;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, View.OnLongClickListener{

        TextView usernameTV;

        public ViewHolder(View itemView) {
            super(itemView);

            usernameTV = itemView.findViewById(R.id.user_name);
            itemView.setOnLongClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                //menuInfo is null
                contextMenu.add(Menu.NONE, R.id.toMyTeam,
                        Menu.NONE, R.string.to_my_team);
                contextMenu.add(Menu.NONE, R.id.toOppositeTeam,
                        Menu.NONE, R.string.to_opposite_team);

        }

        @Override
        public boolean onLongClick(View view) {
            setPosition(getAdapterPosition());
            return false;
        }
    }

}
