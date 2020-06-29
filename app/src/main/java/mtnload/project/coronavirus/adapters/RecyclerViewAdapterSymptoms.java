package mtnload.project.coronavirus.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import mtnload.project.coronavirus.R;


public class RecyclerViewAdapterSymptoms extends RecyclerView.Adapter<RecyclerViewAdapterSymptoms.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapterSymp";

    private Context mContext;

    public RecyclerViewAdapterSymptoms(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: callled");

        switch (position){

            case 0:
                holder.imageView.setVisibility(View.VISIBLE);
                holder.imageView.setImageResource(R.drawable.symptom_1);
                break;
            case 1:
                holder.imageView2.setVisibility(View.VISIBLE);
                holder.imageView2.setImageResource(R.drawable.symptom_2);
                break;
            case 2:
                holder.imageView.setVisibility(View.VISIBLE);
                holder.imageView.setImageResource(R.drawable.symptom_3);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
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
