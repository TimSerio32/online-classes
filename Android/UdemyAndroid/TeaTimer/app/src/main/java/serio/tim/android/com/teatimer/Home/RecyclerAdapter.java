package serio.tim.android.com.teatimer.Home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import serio.tim.android.com.teatimer.R;
import serio.tim.android.com.teatimer.Timers.BlackTeaTimer;
import serio.tim.android.com.teatimer.Timers.GreenTeaTimer;
import serio.tim.android.com.teatimer.Timers.GyokuroTimer;
import serio.tim.android.com.teatimer.Timers.OolongTeaTimer;
import serio.tim.android.com.teatimer.Timers.PuErhTimer;
import serio.tim.android.com.teatimer.Timers.RooibosTeaTimer;
import serio.tim.android.com.teatimer.Timers.WhiteTeaTimer;
import serio.tim.android.com.teatimer.Timers.YerbaMateTimer;

/**
 * Created by Tim on 11/12/17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.TeaTypeViewHolder> {
    public Context contextOne;

    public class TeaTypeViewHolder extends RecyclerView.ViewHolder {
        public View view;
        CardView cardView;
        TextView teaTypeName;

        TeaTypeViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            teaTypeName = (TextView) itemView.findViewById(R.id.teaTypeText);
            view = itemView;
            /*cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Intent intent = new Intent(view.getContext(), TimerActivity.class);
                    //intent.putExtra("teaType", teaTypeName.getText().toString());
                    //view.getContext().startActivity(intent);
                    TimerActivity timerActivity = new TimerActivity();
                    timerActivity.setViewPager(0);


                }
            });*/
        }
    }

    private Context context;
    private List<TeaType> teaTypes;
    RecyclerAdapter(Context context, List<TeaType> teaTypes) {
        this.context = context;
        this.teaTypes = teaTypes;
    }

    @Override
    public TeaTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_items, parent, false);
        TeaTypeViewHolder teaTypeViewHolder = new TeaTypeViewHolder(v);
        return teaTypeViewHolder;
    }

    @Override
    public void onBindViewHolder(TeaTypeViewHolder holder, int position) {
        final int pos = position;
        holder.teaTypeName.setText(teaTypes.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pos == 0) {
                    view.getContext().startActivity(new Intent(view.getContext(), GreenTeaTimer.class));
                } else if(pos == 1) {
                    view.getContext().startActivity(new Intent(view.getContext(), BlackTeaTimer.class));
                } else if(pos == 2) {
                    view.getContext().startActivity(new Intent(view.getContext(), OolongTeaTimer.class));
                } else if(pos == 3) {
                    view.getContext().startActivity(new Intent(view.getContext(), WhiteTeaTimer.class));
                } else if(pos == 4) {
                    view.getContext().startActivity(new Intent(view.getContext(), RooibosTeaTimer.class));
                } else if(pos == 5) {
                    view.getContext().startActivity(new Intent(view.getContext(), GyokuroTimer.class));
                } else if(pos == 6) {
                    view.getContext().startActivity(new Intent(view.getContext(), YerbaMateTimer.class));
                } else if(pos == 7) {
                    view.getContext().startActivity(new Intent(view.getContext(), PuErhTimer.class));
                }
                //Intent intent = new Intent(view.getContext(), TimerActivity.class);
                //intent.putExtra("teaType", teaTypeName.getText().toString());
                //view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return teaTypes.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}