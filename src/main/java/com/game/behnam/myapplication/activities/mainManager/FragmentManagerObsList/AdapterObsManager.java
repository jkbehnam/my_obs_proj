package com.game.behnam.myapplication.activities.mainManager.FragmentManagerObsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codesgood.views.JustifiedTextView;
import com.game.behnam.myapplication.R;
import com.game.behnam.myapplication.utils.Event;
import com.game.behnam.myapplication.utils.Observer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by behnam_b on 7/5/2016.
 */
public class AdapterObsManager extends RecyclerView.Adapter<AdapterObsManager.MyViewHolder> {
    private String p_code;
    private List<Observer> data_event_list;

    Context context1;
    OnCardClickListner onCardClickListner;

    public class MyViewHolder extends RecyclerView.ViewHolder {

@BindView(R.id.tv_request_date)
        TextView tv_request_date;
        @BindView(R.id.item_event_tv_main)
        JustifiedTextView tv_main;
        @BindView(R.id.item_event_iv_main)
        ImageView iv_main;
        @BindView(R.id.event_list_rcle)
        RecyclerView rv_event;
        @BindView(R.id.item_event_card)
        CardView cv_event;
        @BindView(R.id.txt_message)
        ImageView iv;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }


    public AdapterObsManager(ArrayList<Observer> data_services_list) {
        this.data_event_list = data_services_list;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request_list, parent, false);
        context1 = parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Observer data_event = data_event_list.get(position);
        holder.tv_request_date.setVisibility(View.VISIBLE);
        holder.tv_request_date.setText(data_event.getSpecialty());
        holder.tv_main.setText(data_event.getName());
        //Glide.with(context1).load(data_event.getE_img()).into(holder.iv_main);
        holder.cv_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onCardClickListner.OnCardClicked(view, position,data_event);
            }
        });
/*
        GridLayoutManager layoutManager = new GridLayoutManager(context1, 3);
        holder.rv_event.setLayoutManager(layoutManager);
        ArrayList<InnerListItem> uel = new ArrayList<InnerListItem>();
        uel.add(new InnerListItem(data_event.getE_type(), "game", "نوع رویداد"));
        if (data_event.getE_type().equals("مسابقه")){
            uel.add(new InnerListItem("آنلاین", "placeholder", "محل برگزاری"));
            holder.iv_event_type.setColorFilter(ContextCompat.getColor(context1, R.color.event_main_title), android.graphics.PorterDuff.Mode.MULTIPLY);
        }else {
            uel.add(new InnerListItem(data_event.getE_city(), "placeholder", "محل برگزاری"));
            holder.iv_event_type.setColorFilter(ContextCompat.getColor(context1, R.color.boxStrokeColor), android.graphics.PorterDuff.Mode.MULTIPLY);

        }

        PersianDate persianDate;
        try {
             persianDate=new PersianDate(Long.parseLong(data_event.getE_start_time())*1000);
            uel.add(new InnerListItem(persianDate.getShDay()+" "+persianDate.monthName(), "placeholder", "محل برگزاری"));
        }catch (Exception e){

            uel.add(new InnerListItem("شروع دلخواه", "placeholder", "محل برگزاری"));
        }



        AdapterEvent madapter = new AdapterEvent(uel);
        Calendar cal= Calendar.getInstance();

        if (!data_event.getG_end_time().equals("0")) {
            holder.iv.setVisibility(View.VISIBLE);
            holder.cv_event.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    AlertDialog ad;
                    DialogEventEnd dt = new DialogEventEnd();
                    ad = dt.setTextViews(context1,"رویداد به پایان رسیده است.",data_event.getE_id());

                    ad.show();
                }
            });



        }else {
            holder.iv.setVisibility(View.GONE);
            holder.cv_event.setClickable(true);
            holder.cv_event.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onCardClickListner.OnCardClicked(view, position);
                }
            });
        }
        // SlideInBottomAnimationAdapter alphaAdapter = new SlideInBottomAnimationAdapter(madapter);
        // alphaAdapter.setFirstOnly(true);
        holder.rv_event.setAdapter(madapter);

        madapter.setOnCardClickListner(new AdapterEvent.OnCardClickListner() {
            @Override
            public void OnCardClicked(View view, int position) {

            }
        });
        Glide.with(context1).load(data_event.getE_img()).into(holder.iv_main);

*/
    }

    @Override
    public int getItemCount() {
        return data_event_list.size();
    }

    public interface OnCardClickListner {
        void OnCardClicked(View view, int position, Observer event);
    }

    public void setOnCardClickListner(OnCardClickListner onCardClickListner) {
        this.onCardClickListner = onCardClickListner;
    }

}