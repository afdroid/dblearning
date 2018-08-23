package project.dblearning.quizUnits;

public class QuestionsUnitTwo {

    public String mQuestions[] = {
            "¿De qué elementos se componen las tablas?",
            "¿Definición de registro?",
            "¿Definición de campo?",
            "¿Definición de clave primaria?",
            "¿Definición de clave foránea?",
            "¿Cómo se le llama al Diagrama que ayuda a visualizar la relación entre tablas de una base de datos?",
            "Conjunto de elementos orientados al tratamiento y administración de datos e información, organizados y listos para su posterior uso, generados para cubrir una necesidad (objetivo).",
            "Representación simbólica (numérica, alfabética, algorítmica etc.), un atributo o una característica de una entidad.",
            "Asociación de dos o más entidades",
    };

    private String mChoices[][] = {
            {"Registros y Campos", "Información ", "Claves primarias y foráneas", "Ninguna de las anteriores "},
            {"Son los objetos principales de bases de datos que se utilizan para guardar datos", "Es cada una de las filas en que se divide la tabla. ", "Ninguna de las anteriores", "Es un valor único que sirve para representar una tabla"},
            {"Sistema que permite la manipulación de las bases de datos mediante un interfaz que compone las sentencias de consultas y edición de dicha base de datos", "tEs un identificador único", "Son estructuras compuestas por filas y columnas", "Es cada una de las columnas que forman la tabla"},
            {"Ninguna de las anteriores", "Campo o a una combinación de campos que identifica de forma única a cada fila de una tabla", "Es cada una de las columnas que forman la tabla", "Son estructuras compuestas por filas y columnas"},
            {"Es una columna o varias columnas, que sirven para señalar cual es la clave primaria de otra tabla", "Son estructuras compuestas por filas y columnas", "Son los objetos principales de bases de datos que se utilizan para guardar datos", "Campo o a una combinación de campos que identifica de forma única a cada fila de una tabla"},
            {"tDiagrama Lógico", "ETL", "Modelo Relacional", "Diagrama UML"},
            {"SISTEMA DE BASE DE DATOS", "DIRECTORIO DE DATOS", "SISTEMA DE INFORMACION", "MANEJADOR DE BASE DE DATOS"},
            {"REGISTRO", "CAMPO", "DATO", "INFORMACION"},
            {"SMBD", "RELACION", "ASOCIACION ENTRE TABLAS", "DATOS"},

    };
    private  String mCorrectAnswer[] = {"Registros y Campos", "Es cada una de las filas en que se divide la tabla.", "Es cada una de las columnas que forman la tabla", "Campo o a una combinación de campos que identifica de forma única a cada fila de una tabla", "Es una columna o varias columnas, que sirven para señalar cual es la clave primaria de otra tabla", "Modelo Relacional", "SISTEMA DE BASE DE DATOS", "DATO","RELACION"};

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
