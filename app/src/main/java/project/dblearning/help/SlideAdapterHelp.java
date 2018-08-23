package project.dblearning.help;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import project.dblearning.R;

public class SlideAdapterHelp extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapterHelp(Context context){

        this.context = context;
    }

    //Arreglos
    public int[] arraySlideHelpImages = {
            R.drawable.img_help_start,
            R.drawable.img_help_content,
            R.drawable.img_help_main_menu,
            R.drawable.img_help_profile,
            R.drawable.img_help_list_games,
            R.drawable.img_help_game_points,
            R.drawable.img_help_game_correct,
            R.drawable.img_help_glossary,
            R.drawable.img_help_exams,
            R.drawable.img_help_exams_score,
            R.drawable.img_help_list_videos,
            R.drawable.img_help_about_of
    };

    public String[] arraySlideHelpTitles = {
            "Inicio",
            "Contenido",
            "Menú principal",
            "Mi perfil",
            "Entretenimiento",
            "Puntuación",
            "Correcto/Incorrecto",
            "Glosario de términos",
            "Exámenes",
            "Puntaje",
            "Videos",
            "Acerca de..."


    };

    public String[] arraySlideHelpDesc = {
            "En esta parte se muestra el temario de la aplicación, para seleccionar algun tema de tu interés basta con dar clic sobre alguna de las tarjetas.",
            "Al dar clic sobre alguna tarjeta se abrirá el contenido correspondiente a esa unidad, el contenido está dividido en subtemas los cuales se encuentan del lado derecho, si quieres leer otro subtema solamente tienes que deslizar el dedo de derecha a izquierda sobre la pantalla.",
            "Éste es el menú principal de la aplicación, aquí encontrarás todas las funcionalidades de ésta, desde juegos hasta videos, para seleccionar una categoría, sólo da clic sobre algún nombre.",
            "En el perfíl podrás modificar los datos de tu cuenta, por ejemplo, tu foto de perfil o tu nombre de usuario, para ello da clic sobre alguno de los botones que se muestran.",
            "En ésta parte se muestra la lista de juegos disponibles dentro de la aplicación, para abrir alguno, da clic sobre el botón con el nombre del juego.",
            "Cada vez que inicias un nuevo juego tienes 3 vidas, al llegar éstas a cero se terminará el juego, también tienes un acumulador de puntaje el cual aumentará cada vez que tu respuesta sea correcta.",
            "Estos mensajes aparecerán dependiendo si tu respuesta es correcta o no.",
            "En el glosario encontrarás tarjetas con los términos más utilizados en base de datos, cada tarjeta contiene la descripción del término, para visualizarlo debes dar clic sobre la tarjeta.",
            "Ésta es la lista de los exámenes, los cuales están ordendos por unidad temática.",
            "Al finalizar cada examen se te enviará un mensaje con el número de aciertos que hayas obtenido, con la opción de volver a intentarlo o de salir del exámen.",
            "En la sección de videos se muestra una lista de videos, el nombre corresponde a la unidad temática, cada unidad contiene por lo menos 1 video, para visualizarlo debes dar clic sobre el nombre del video.",
            "La sección acerca de, contiene información sobre los desarrolladores de la aplicación."
    };

    @Override
    public int getCount() {
        return arraySlideHelpTitles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.activity_slide_adapter_help, container, false);

        ImageView imgVwHelpSlide = view.findViewById(R.id.img_vw_help_slide);
        TextView lblHelpSlideTitle = view.findViewById(R.id.lbl_help_slide_title);
        TextView lblHelpSlideDesc = view.findViewById(R.id.lbl_help_slide_desc);

        imgVwHelpSlide.setImageResource(arraySlideHelpImages[position]);
        lblHelpSlideTitle.setText(arraySlideHelpTitles[position]);
        lblHelpSlideDesc.setText(arraySlideHelpDesc[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((RelativeLayout)object);
    }

}
