package com.nicholasdoglio.nycschools.ui.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nicholasdoglio.nycschools.R;
import com.nicholasdoglio.nycschools.data.model.SchoolListResponse;
import com.nicholasdoglio.nycschools.ui.common.NavigationController;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nicholas Doglio
 */
public class SchoolListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SchoolListResponse> schoolListResponses;
    private NavigationController navigationController;

    SchoolListAdapter(NavigationController navigationController) {
        this.navigationController = navigationController;
        schoolListResponses = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_school_list, parent, false);
        return new SchoolItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SchoolItemViewHolder schoolItemViewHolder = (SchoolItemViewHolder) holder;
        schoolItemViewHolder.bindTo(schoolListResponses.get(position));
        schoolItemViewHolder
                .schoolNameTextView
                .setOnClickListener(view ->
                        navigationController
                                .openDetailFragment(schoolListResponses.get(position).getDbn(),
                                        schoolListResponses.get(position).getSchoolName()));
    }

    @Override
    public int getItemCount() {
        return schoolListResponses.size();
    }

    public void setList(List<SchoolListResponse> schoolListResponses) {
        this.schoolListResponses.addAll(schoolListResponses);
        notifyDataSetChanged();
    }

    class SchoolItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.school_name)
        TextView schoolNameTextView;

        SchoolItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindTo(SchoolListResponse schoolListResponse) {
            schoolNameTextView.setText(schoolListResponse.getSchoolName());
        }
    }
}
