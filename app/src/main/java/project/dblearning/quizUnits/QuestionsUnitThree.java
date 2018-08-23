package project.dblearning.quizUnits;

public class QuestionsUnitThree {
    public String mQuestions[] = {
            "¿Una sentencia SELECT sin la cláusula WHERE devuelve",
            "En SQL, para ordenar los datos devueltos por una sentencia SELECT se emplea la cláusula",
            "¿Cuál de las siguientes no es una función de agregación?",
            "¿Qué instrucción se emplea para eliminar todo el contenido de una tabla, pero conservando la tabla?",
            "¿Cuál de las siguientes no es una función de agregación?",
            "En SQL, para eliminar las filas duplicadas del resultado de una sentencia SELECT se emplea",
            "En SQL, para modificar la estructura de una tabla de una base de datos se emplea la instrucción",
            "En una cláusula LIKE, ¿cómo se obtienen todos los nombres de personas que comienzan con \"Juan\"?",
            "En SQL, ¿cuál de estas sentencias añade una fila a una tabla en una base de datos?",
    };

    private String mChoices[][] = {
            {"Todos los registros existentes en la tabla ", "Las anteriores respuestas no son correctas ", "No se puede ejecutar una sentencia SELECT sin la cláusula WHERE ", "Todos los registros existentes en la tabla que no estén relacionados con otra tabla "},
            {"SORTED BY", "SORT BY", "ORDERED BY ", "ORDER BY "},
            {"COUNT()", "MIN()", "LIMIT()", "MAX()"},
            {"DROP TABLE", "DELETE TABLE ", "TRUNCATE TABLE", "Las anteriores respuestas no son correctas "},
            {" FLOOR()", "AVG()", "SUM()", "Las anteriores respuestas no son correctas"},
            {"NO DUPLICATE", "UNIQUE", "DISTINCT", "Las anteriores respuestas no son correctas "},
            {"CHANGE TABLE", "Las anteriores respuestas no son correctas", "MODIFY TABLE", "ALTER TABLE"},
            {"LIKE \"Juan%\"", "LIKE \"Juan*\"", "LIKE \"Juan$\"", "LIKE \"Juan&\""},
            {"INSERT", "ADD", "UPDATE", "INCLUDE"},

    };
    private  String mCorrectAnswer[] = {"Todos los registros existentes en la tabla ", "ORDER BY ", "LIMIT()", "TRUNCATE TABLE", " FLOOR()", "DISTINCT", "ALTER TABLE", "LIKE \"Juan%\"", "INSERT"};

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
