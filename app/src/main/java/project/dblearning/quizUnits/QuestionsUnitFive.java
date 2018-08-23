package project.dblearning.quizUnits;

public class QuestionsUnitFive {
    public String mQuestions[] = {
            "Para crear un procedimiento almacenado debemos emplear la sentencia:",
            "Sentencia para eliminar un Procedimiento Almacenado",
            "¿Qué falta para que su cumpla la siguiente estructura? CREATE PROCEDURE...",
            "¿De qué se compone un procedimiento?",
            "¿Qué parámetros se usa para recopilar el procedimiento almacenado?",
            "¿Definición de  una vista?",
            "Sintaxis para crear una vista:",
            "¿Cómo puedo obtener la lista de vistas en una base de datos?",
            "¿Definición de un procedimiento almacenado?",
    };

    private String mChoices[][] = {
            {"CREATE PROCEDURE", "CREATE PROCEDES", "CREATE", "CREATE PROCEDS"},
            {"Delete nombre_procedimiento", "Drop nombre_procedimiento", "Delete  procedure nombre_procedimiento", "Drop procedure nombre_procedimiento"},
            {"...<ProcedureName, sysname, Procedure_Name>", "...<Procedure_Name, sysname, ProcedureName>", "...<sysname, ProcedureName>", "...<Procedure_Name, ProcedureName>"},
            {"Solamente de un bloque de código", "Un nombre y un conjunto de variables de tipo cadena", "Un nombre, un conjunto de parámetros y un bloque de código", "Un índice, un conjunto de funciones y un bloque de procesos"},
            {"SP_RECOMPILE", "WITH RECOMPILE", "Ninguna de las anteriores", "ST_RECOMPILE"},
            {"Son los objetos principales de bases de datos que se utilizan para guardar datos", "Son estructuras compuestas por filas y columnas", "Es una columna o varias columnas, que sirven para señalar cual es la clave primaria de otra tabla", "Un mecanismo que permite generar un resultado almacenado, y ejecutar nuevos pedidos sobre este resultado como si fuera una tabla normal."},
            {"Crete Nombre_vista view", "Create view Nombre_vista", "View Nombre_vista", "Ninguna de la anteriores"},
            {"SELECT * FROM sysobjects  WHERE xtype = 'V'", "SELECT* VIEWS", "Ninguna de las anteriores", "SELECT * FROM [INFORMATION_SCHEMA].[VIEWS]"},
            {"Un mecanismo que permite generar un resultado almacenado, y ejecutar nuevos pedidos sobre este resultado como si fuera una tabla normal", "Conjunto de comandos que pueden ser ejecutados directamente en el servidor", "Son los objetos principales de bases de datos que se utilizan para guardar datos", "Ninguna de las anteriores"},

    };
    private  String mCorrectAnswer[] = {"CREATE PROCEDURE", "Drop procedure nombre_procedimiento", "...<Procedure_Name, sysname, ProcedureName>", "Un nombre, un conjunto de parámetros y un bloque de código", "SP_RECOMPILE", "Un mecanismo que permite generar un resultado almacenado, y ejecutar nuevos pedidos sobre este resultado como si fuera una tabla normal.", "Create view Nombre_vista", "SELECT * FROM [INFORMATION_SCHEMA].[VIEWS]", "Conjunto de comandos que pueden ser ejecutados directamente en el servidor"};

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
