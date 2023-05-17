package com.example.fitpeak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ReadRecyclerAdapter extends RecyclerView.Adapter<ReadRecyclerAdapter.ReadViewHolder>
{
    private Context context;
    private ArrayList<fData> fitList;

    public ReadRecyclerAdapter(Context context, ArrayList<fData> fitList)
    {
        this.context = context;
        this.fitList = fitList;
    }

    public class ReadViewHolder extends RecyclerView.ViewHolder
    {
        TextView personName, personActivity, activityData;

        public ReadViewHolder(View itemView)
        {
            super(itemView);
            personName = itemView.findViewById(R.id.txtName);
            personActivity = itemView.findViewById(R.id.txtPersonActivity);
            activityData = itemView.findViewById(R.id.txtQuantity);
        }
    }

    @Override
    public ReadViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.single_data_item, parent, false);
        return new ReadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReadViewHolder holder, int position)
    {
        holder.personName.setText(fitList.get(position).getPersonName());
        holder.personActivity.setText(fitList.get(position).getPersonActivity());
        holder.activityData.setText(String.valueOf(fitList.get(position).getActivityData()));
    }

    @Override
    public int getItemCount()
    {
        return fitList.size();
    }
}
