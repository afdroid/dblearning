package project.dblearning.introduction;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import project.dblearning.R;

public class SlideAdapterIntroduction extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflaterIntro;

    public SlideAdapterIntroduction(Context context){

        this.context = context;
    }

    //Arrays
    public int[] arraySlideIntroImages = {
            R.drawable.img_intro_intro,
            R.drawable.img_intro_content,
            R.drawable.img_intro_videos,
            R.drawable.img_intro_entertainment
    };

    public String[] arraySlideIntroTitles = {
            "Objetivo",
            "Contenido",
            "Videos",
            "Entretenimiento"
    };

    public String[] arraySlideIntroDesc = {
            "En esta aplicación aprenderás Bases de Datos desde cero de una manera interactivia y entretenida.",
            "Dentro de la aplicación podrás encontrar contenido que te ayudará a reforzar tus conocimientos en cuanto a Bases de datos.",
            "Aquí encontrarás una serie de videos que te ayudarán a entender mejor el contenido.",
            "¡Juega mientras aprendes!"

    };

    @Override
    public int getCount() {
        return arraySlideIntroTitles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflaterIntro = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflaterIntro.inflate(R.layout.activity_slide_adapter_introduction, container, false);

        ImageView imgVwIntroSlide = view.findViewById(R.id.img_vw_intro_slide);
        TextView lblIntroSlideTitle = view.findViewById(R.id.lbl_intro_slide_title);
        TextView lblIntroSlideDesc = view.findViewById(R.id.lbl_intro_slide_desc);

        imgVwIntroSlide.setImageResource(arraySlideIntroImages[position]);
        lblIntroSlideTitle.setText(arraySlideIntroTitles[position]);
        lblIntroSlideDesc.setText(arraySlideIntroDesc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((RelativeLayout)object);
    }
}
