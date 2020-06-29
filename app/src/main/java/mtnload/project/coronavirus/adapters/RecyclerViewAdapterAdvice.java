package mtnload.project.coronavirus.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import mtnload.project.coronavirus.R;


public class RecyclerViewAdapterAdvice extends RecyclerView.Adapter<RecyclerViewAdapterAdvice.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapterSymp";

    private Context mContext;

    public RecyclerViewAdapterAdvice(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterAdvice.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        RecyclerViewAdapterAdvice.ViewHolder holder = new RecyclerViewAdapterAdvice.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterAdvice.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: callled");

        RelativeLayout.LayoutParams params0 = (RelativeLayout.LayoutParams)holder.imageView.getLayoutParams();
        params0.addRule(RelativeLayout.ALIGN_PARENT_START);

        switch (position){

            case 0:
                holder.imageView.setVisibility(View.VISIBLE);
                holder.imageView.setImageResource(R.drawable.advice_1);
                break;
            case 1:
                holder.imageView2.setVisibility(View.VISIBLE);
                holder.imageView2.setImageResource(R.drawable.advice_2);
                break;
            case 2:
                holder.imageView.setVisibility(View.VISIBLE);
                holder.imageView.setImageResource(R.drawable.advice_3);
                break;
            case 3:
                holder.imageView2.setVisibility(View.VISIBLE);
                holder.imageView2.setImageResource(R.drawable.advice_4);
                break;
            case 4:
                holder.imageView.setVisibility(View.VISIBLE);
                holder.imageView.setImageResource(R.drawable.advice_5);
                break;
            case 5:
                holder.imageView2.setVisibility(View.VISIBLE);
                holder.imageView2.setImageResource(R.drawable.advice_6);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView, imageView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            imageView2 = itemView.findViewById(R.id.imageView2);
        }
    }

}
