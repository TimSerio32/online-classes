package serio.tim.android.com.chat;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Tim on 11/8/17.
 */

public class ChatMessageViewHolder extends RecyclerView.ViewHolder {

    private final Activity activity;

    TextView name, message;
    ImageView image;

    public ChatMessageViewHolder(Activity activity, View itemView) {
        super(itemView);
        this.activity = activity;
        name = (TextView)itemView.findViewById(R.id.text1);
        message = (TextView)itemView.findViewById(R.id.text2);
        image = new ImageView(activity);((ViewGroup)itemView).addView(image);
    }

    public void bind(ChatMessage chat) {
        name.setText
    }
}
