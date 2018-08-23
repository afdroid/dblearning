package project.dblearning.quizUnits;

public class QuestionsUnitFour {

    public String mQuestions[] = {
            "La cláusula FROM indica el origen de datos para la consulta y puede constar de: ",
            "Si queremos utilizar la tabla de una base de datos externa (Base2) en nuestra consulta…",
            "El alias se puede aplicar en:",
            "Señala cuál de los siguientes ejemplos es CORRECTO:",
            "Señala cuál de los siguientes ejemplos es INCORRECTO:",
            "¿Dónde utilizamos la palabra clave *?",
            "Si a una columna le aplicamos una ordenación mediante el ORDER BY…",
            "La cláusula DISTINCT…",
            "La cláusula TOP…"
    };

    private String mChoices[][] = {
            {"Una tabla", "Una vista", "Una vista y una tabla", "Ninguna de las anteriores"},
            {"Deberemos cualificarla, de la siguiente forma: FROM Base2.tabla.", "Podremos indicar el nombre de la tabla directamente: FROM tabla.", "No podremos utilizar más de una base de datos en la misma consulta.", "Deberemos desconectar de la base de datos actual y conectar con la Base2."},
            {"El origen, por ejemplo: tablaempleados as Empleados.", "La lista de selección, por ejemplo: nomemp as [Nombre empleado].", "Los dos ejemplos son correctos", " Ninguna de las anteriores"},
            {"SELECT nom as Nombre FROM empleados WHERE Nombre = ‘Juan’.", "SELECT nom  Nombre FROM empleados WHERE Nombre = ‘Juan’.", "SELECT nom Nombre FROM empleados ORDER BY Nombre.", "SELECT nom as Nombre FROM empleados ORDER BY Apellido = ‘García’."},
            {"SELECT precio_unidad = (importe/cantidad)…", "SELECT (importe/cantidad) AS precio_unidad…", "SELECT precio_unidad = ROUND(importe/cantidad)…", "SELECT Precio_unidad = ROUND((imprte/cantidad), 2)…"},
            {"En la lista de selección, para indicar que queremos mostrar todas las columnas del origen indicado.", "En el origen de los datos, para indicar que queremos utilizar como origen todas las tablas.", "A y B son ciertas.", "A y B son falsas."},
            {"Deberemos indicar si queremos ordenar de forma ascendente o descendente o dará error.", "Deberemos indicar si queremos ordenar de forma ascendente o descendente, la forma ascendente viene por defecto.", "Deberemos indicar ASC o DESC, pero solo en caso de que se trate de un campo alfabético.", "Todas son falsas."},
            {"Agiliza las consultas porque debe mostrar menos resultados.", "Se utiliza para no mostrar las filas que repitan el mismo valor.", "Se utiliza para no mostrar las flas que se repitan en todos sus campos, es decir que sean idénticas.", "Ninguna de las anteriores."},
            {"Tiene más sentido en consultas con ORDER BY, Al ejecutarse, primero se ordenan y luego se extraen las N primeras.", "Puede ir acompañada de WITH TIES, si queremos mostrar todos los valores iguales al último del ranking.", "Puede ser porcentual, es decir, mostrar el 8% del total de registros.", "Todas son ciertas. "},

    };
    private  String mCorrectAnswer[] = {"Una vista y una tabla", "Deberemos cualificarla, de la siguiente forma: FROM Base2.tabla.", "Los dos ejemplos son correctos", "SELECT nom as Nombre FROM empleados ORDER BY Apellido = ‘García’.", "SELECT precio_unidad = (importe/cantidad)…", "En la lista de selección, para indicar que queremos mostrar todas las columnas del origen indicado.", "Deberemos indicar si queremos ordenar de forma ascendente o descendente, la forma ascendente viene por defecto.", "Se utiliza para no mostrar las flas que se repitan en todos sus campos, es decir que sean idénticas.", "Todas las anteriores"};

    public  String getQuestion(int a ){
        String question = mQuestions[a];
        return question;
    }


    public  String getChoiceOne(int a){
        String choice = mChoices[a][0];
        return choice;
    }
    public  String getChoiceTwo(int a){
        String choice = mChoices[a][1];
        return choice;
    }
    public  String getChoiceThree(int a){
        String choice = mChoices[a][2];
        return choice;
    }
    public  String getChoiceFour(int a){
        String choice = mChoices[a][3];
        return choice;
    }

    public  String getCorrectAnswer(int a){
        String answer = mCorrectAnswer[a];
        return answer;
    }
}
