package com.game.behnam.myapplication.activities.mainManager.gameDetails;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.game.behnam.myapplication.R;
import com.game.behnam.myapplication.utils.OtherGamer;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by behnam_b on 7/5/2016.
 */
public class adapter_gamer_event_rank extends RecyclerView.Adapter<adapter_gamer_event_rank.MyViewHolder> {
    private List<OtherGamer> data_services_list;

    Context context1;
    OnCardClickListner onCardClickListner;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView q_name, q_code, q_level, q_get_time;
        LinearLayout li;


        public MyViewHolder(View view) {
            super(view);
            q_name = view.findViewById(R.id.tv_name);
            q_code = view.findViewById(R.id.tv_member);
            q_level = view.findViewById(R.id.tv_score);


            li = view.findViewById(R.id.relativeLayout1);
        }
    }


    public adapter_gamer_event_rank(ArrayList<OtherGamer> data_services_list, String p_code) {
        this.data_services_list = data_services_list;
        String p_code1 = p_code;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gamers_list, parent, false);
        context1 = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final OtherGamer data_service = data_services_list.get(position);

        holder.q_name.setText(data_service.getName());
        holder.q_level.setText(String.valueOf(data_service.getScore()));
        holder.q_code.setText("#"+ (position + 1));



    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public int getItemCount() {
        return data_services_list.size();
    }

    public interface OnCardClickListner {
        void OnCardClicked(View view, int position);
    }

    public void setOnCardClickListner(OnCardClickListner onCardClickListner) {
        this.onCardClickListner = onCardClickListner;
    }
}