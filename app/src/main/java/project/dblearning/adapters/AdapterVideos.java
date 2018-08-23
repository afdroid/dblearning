package project.dblearning.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import project.dblearning.R;
import project.dblearning.videos.VideoClass;

import java.util.ArrayList;

public class AdapterVideos extends RecyclerView.Adapter<AdapterVideos.ViewHolder> implements View.OnClickListener{

    private ArrayList<VideoClass> arrayListVideos;
    private View.OnClickListener listener;

    public AdapterVideos(ArrayList<VideoClass> arrayListVideos) {
        this.arrayListVideos = arrayListVideos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_videos, null, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lblVideoTitle.setText(arrayListVideos.get(position).getVideoName());

    }

    @Override
    public int getItemCount() {
        return arrayListVideos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView lblVideoTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            lblVideoTitle = itemView.findViewById(R.id.lbl_video_title);
        }
    }
}
