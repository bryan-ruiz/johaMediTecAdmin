package com.example.joha.medi_tec_admin;

import android.provider.BaseColumns;

/**
 * Created by Bryan on 27/9/2017.
 */

public class DataBaseContract {
    // Implementa la interfaz BaseColumns para heredar campos estandar del marco de Android _ID

    public static class DataBaseEntry implements BaseColumns {

        // Clase Orden

        public static final String TABLE_NAME_ENFERMEDAD = "Enfermedad";
        public static final String COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD= "idEnfermedad";
        public static final String COLUMN_NAME_ENFERMEDAD_NOMBRE = "nombre";
        public static final String COLUMN_NAME_ENFERMEDAD_DESCRIPCION = "descripcion";
        public static final String COLUMN_NAME_ENFERMEDAD_PELIGROSIDAD = "peligrosidad";


        public static final String TABLE_NAME_SINTOMA = "Sintoma";
        public static final String COLUMN_NAME_SINTOMA_ID_SINTOMA = "idSintoma";
        public static final String COLUMN_NAME_SINTOMA_NOMBRE = "nombre";

        public static final String TABLE_NAME_MEDICAMENTO = "Medicamento";
        public static final String COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO = "idMedicamento";
        public static final String COLUMN_NAME_MEDICAMENTO_NOMBRE = "nombre";
        public static final String COLUMN_NAME_MEDICAMENTO_DESCRIPCION = "descripcion";

        public static final String TABLE_NAME_ENFERMEDAD_MEDICAMENTO = "EnfermedadMedicamento";

        public static final String TABLE_NAME_ENFERMEDAD_SINTOMA = "EnfermedadSintoma";
    }

// Construir las tablas de la base de datos

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";


    // Creacion de tablas

    public static final String SQL_CREATE_ENFERMEDAD =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_ENFERMEDAD + " (" +

                    DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD + INTEGER_TYPE + "PRIMARY KEY" + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_ENFERMEDAD_NOMBRE + TEXT_TYPE + COMMA_SEP +
                    DataBaseEntry.COLUMN_NAME_ENFERMEDAD_DESCRIPCION + TEXT_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_ENFERMEDAD_PELIGROSIDAD + TEXT_TYPE +")";


    public static final String SQL_DELETE_ENFERMEDAD =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_ENFERMEDAD;


    public static final String SQL_CREATE_SINTOMA =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_SINTOMA + " (" +

                    DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA + INTEGER_TYPE + "PRIMARY KEY" + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_SINTOMA_NOMBRE + TEXT_TYPE +")";

    public static final String SQL_DELETE_SINTOMA =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_SINTOMA;

    public static final String SQL_CREATE_MEDICAMENTO =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_MEDICAMENTO + " (" +

                    DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO + INTEGER_TYPE + "PRIMARY KEY" + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_MEDICAMENTO_NOMBRE + TEXT_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_MEDICAMENTO_DESCRIPCION + TEXT_TYPE + ")";


    public static final String SQL_DELETE_MEDICAMENTO  =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_MEDICAMENTO;


    public static final String SQL_CREATE_ENFERMEDAD_SINTOMA =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_ENFERMEDAD_MEDICAMENTO + " (" +

                    DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD + INTEGER_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO + INTEGER_TYPE + COMMA_SEP +

                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD + ") REFERENCES " +

                    DataBaseEntry.TABLE_NAME_ENFERMEDAD + "(" + DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD +

                    ")"+ COMMA_SEP +

                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO + ") REFERENCES " +

                    DataBaseEntry.TABLE_NAME_MEDICAMENTO + "(" + DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO +

                    "))";



    public static final String SQL_DELETE_ENFERMEDAD_SINTOMA =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_ENFERMEDAD_MEDICAMENTO;



    public static final String SQL_CREATE_ENFERMEDAD_MEDICAMENTO =

            "CREATE TABLE " + DataBaseEntry.TABLE_NAME_ENFERMEDAD_SINTOMA + " (" +

                    DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD + INTEGER_TYPE + COMMA_SEP +

                    DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA + INTEGER_TYPE + COMMA_SEP +

                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD + ") REFERENCES " +

                    DataBaseEntry.TABLE_NAME_ENFERMEDAD + "(" + DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD +

                    ")"+ COMMA_SEP +

                    "FOREIGN KEY(" + DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA + ") REFERENCES " +

                    DataBaseEntry.TABLE_NAME_SINTOMA + "(" + DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA +

                    "))";

    public static final String SQL_DELETE_ENFERMEDAD_MEDICAMENTO =

            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME_ENFERMEDAD_SINTOMA;
}
