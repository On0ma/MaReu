package com.onoma.mareu.ui;

import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.onoma.mareu.R;
import com.onoma.mareu.di.DI;
import com.onoma.mareu.model.Reunion;
import com.onoma.mareu.service.ReunionApiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReunionRecyclerViewAdapter extends RecyclerView.Adapter<ReunionRecyclerViewAdapter.ViewHolder> implements Filterable {

    public interface CallBack {
        void onDeleteClicked(Reunion reunion);
    }

    private List<Reunion> reunionsFiltered;
    private ReunionRecyclerViewAdapter.CallBack mcallback;

    private ReunionApiService mApiService;

    public ReunionRecyclerViewAdapter(List<Reunion> items,CallBack callback) {
        reunionsFiltered = items;
        mcallback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_reunion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        mApiService = DI.getReunionApiService();
        Reunion reunion = reunionsFiltered.get(position);
        holder.mReunionInfos.setText(reunion.concatenateInfos());
        holder.mReunionAttendees.setText(reunion.getAttendeeMail());

        switch (reunion.getRoom()) {
            case PEACH : {
                holder.mRoomColor.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.peach)));
                break;
            }
            case LUIGI : {
                holder.mRoomColor.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.luigi)));
                break;
            }
            case MARIO : {
                holder.mRoomColor.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(holder.itemView.getContext(), R.color.mario)));
                break;
            }
        }

        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcallback.onDeleteClicked(reunion);
            }
        });
    }

    @Override
    public int getItemCount() { return reunionsFiltered.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.reunion_fragment_subject)
        public TextView mReunionInfos;
        @BindView(R.id.reunion_fragment_attendee)
        public TextView mReunionAttendees;
        @BindView(R.id.reunion_fragment_room_color)
        public View mRoomColor;
        @BindView(R.id.reunion_fragment_delete)
        ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();

                List<Reunion> listFiltered = mApiService.filterReunions(charString);

                FilterResults filterResults = new FilterResults();
                filterResults.values = listFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                reunionsFiltered = (ArrayList<Reunion>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
