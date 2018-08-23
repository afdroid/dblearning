package project.dblearning.adapters;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import project.dblearning.R;
import project.dblearning.objects.CardGlossary;
import  java.util.ArrayList;

public class AdapterGlossary extends RecyclerView.Adapter<AdapterGlossary.ViewHolder> implements View.OnClickListener{

    View.OnClickListener listener;
    public Context context;
    public ArrayList<CardGlossary> arrayListConcepts;

    public AdapterGlossary(Context context, ArrayList<CardGlossary> arrayListConcepts) {
        this.context = context;
        this.arrayListConcepts = arrayListConcepts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_card_view_glossary, viewGroup, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        String lblName = arrayListConcepts.get(position).getName();
        TextView lblAdapterInitial = viewHolder.lblGlossaryInitial;
        TextView lblAdapterName = viewHolder.lblGlossaryName;
        TextView lblAdapterDesc = viewHolder.lblGlossaryDesc;
        lblAdapterName.setText(lblName);
        lblAdapterInitial.setText(Character.toString(lblName.charAt(0)));
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        viewHolder.itemView.clearAnimation();
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        animateCircularReveal(viewHolder.itemView);
    }

    public void animateCircularReveal(View view) {
        int centerX = 0;
        int centerY = 0;
        int startRadius = 0;
        int endRadius = Math.max(view.getWidth(), view.getHeight());
        Animator animation = ViewAnimationUtils.createCircularReveal(view, centerX, centerY, startRadius, endRadius);
        view.setVisibility(View.VISIBLE);
        animation.start();
    }

    @Override
    public int getItemCount() {
        if (arrayListConcepts.isEmpty()) {
            return 0;
        } else {
            return arrayListConcepts.size();
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView lblGlossaryInitial;
        private TextView lblGlossaryName;
        private TextView lblGlossaryDesc;

        public ViewHolder(View v) {
            super(v);
            lblGlossaryInitial = v.findViewById(R.id.lbl_glossary_initial_letter);
            lblGlossaryName = v.findViewById(R.id.lbl_glossary_concepts_name);
            lblGlossaryDesc = v.findViewById(R.id.txt_vw_glossary_concepts_desc);
        }
    }
}
