package project.dblearning.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import project.dblearning.objects.Courses;
import project.dblearning.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterCourses extends RecyclerView.Adapter<AdapterCourses.ViewHolderData> implements View.OnClickListener{

    ArrayList<Courses> arrayListCourses;
    View.OnClickListener listener;

    public AdapterCourses(ArrayList<Courses> arrayListCourses) {
        this.arrayListCourses = arrayListCourses;
    }

    @Override
    public ViewHolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_courses, null, false);
        view.setOnClickListener(this);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderData holder, int position) {
        holder.lblCourseTitle.setText(arrayListCourses.get(position).getNombre());
        holder.lblCourseDesc.setText(arrayListCourses.get(position).getDescripcion());
       Picasso
                .get()
                .load(arrayListCourses.get(position).getIcono())
                .into(holder.imgVwCourse);


    }

    @Override
    public int getItemCount() {
        return arrayListCourses.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {
        private TextView lblCourseTitle;
        private TextView lblCourseDesc;
        private CircleImageView imgVwCourse;

        public ViewHolderData(View itemView) {
            super(itemView);
            lblCourseTitle = itemView.findViewById(R.id.lbl_course_units_title);
            lblCourseDesc = itemView.findViewById(R.id.lbl_courses_units_desc);
            imgVwCourse = itemView.findViewById(R.id.img_vw_courses_show_image);

        }
    }
}
