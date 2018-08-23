package project.dblearning.quizUnits;

public class QuestionsUnitOne {

    public String mQuestions[] = {
            "¿Es el campo que proporciona un valor único para cada registro de una tabla y sirve de identificador de registros?",
            "¿Representa una característica de un individuo u objeto. Es la unidad básica de una tabla?",
            "¿Es la interfaz entre la base de datos, el usuario y las aplicaciones que utiliza. Sirve para introducir, almacenar, eliminar, ordenar, consultar y filtrar datos?",
            "¿Al conjunto de datos estructurados, guardados en un medio de almacenamiento y relacionados entre sí, se le conoce como?",
            "333-45-6045 es un ejemplo de...",
            "¿Es el campo que permite introducir cadenas de caracteres hasta un máximo de 255 caracteres?",
            "¿Se compone de un conjunto de campos de iguales o de diferentes tipos?",
            "Oracle, Access, PostgreSQL, MySQL, Apache Derby, Firebird son ejemplos de:",
            "micorreo@hotmail.com, es un ejemplo de:",
    };

    private String mChoices[][] = {
            {"Campo llave o clave primaria", "Numerico", "Autonumerico", "Hipervinculo"},
            {"Informacion", "Campo", "Byte", "Datos"},
            {"Tablas, Consultas, Formularios", "Base de datos", "Informacion", "Sistema de Gestión de Base de Datos (SGBD"},
            {"Base de datos", "Tabla", "Consulta", "Informacion"},
            {"tipo de campo: Memo", "tipo de campo: Autonumérico", "tipo de campo: Texto", "tipo de campo: Numérico"},
            {"Memo", "Datos adjuntos", "Texto", "Alfanumerico"},
            {"Tipos de campo", "Bytes", "Base de datos", "RegistroT"},
            {"Sistema de Gestión de Bases de Datos", "Informacion", "Bases de datos", "Tablas, Consultas, Formularios"},
            {"tipo de campo: datos adjuntos", "tipo de campo: Objeto OLE", "tipo de campo: texto", "Tipo de campo: hipervínculo"}

    };
    private  String mCorrectAnswer[] = {"Campo llave o clave primaria", "Campo", "Sistema de Gestión de Base de Datos (SGBD)", "Base de datos", "tipo de campo: Texto", "Texto", "Registro", "Sistema de Gestión de Bases de Datos", "Tipo de campo: hipervínculo"};

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