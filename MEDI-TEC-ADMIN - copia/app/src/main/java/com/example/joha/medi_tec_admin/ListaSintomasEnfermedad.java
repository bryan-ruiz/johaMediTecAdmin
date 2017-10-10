package com.example.joha.medi_tec_admin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bryan on 6/10/2017.
 */

public class ListaSintomasEnfermedad {
    private static ListaSintomasEnfermedad instancia = null;
    private ArrayList<SintomasEnfermedad> listaSintomasEnfermedad;

    public static ListaSintomasEnfermedad getInstancia() {
        if (instancia == null) {
            instancia = new ListaSintomasEnfermedad();
        }
        return instancia;
    }

    public ArrayList<SintomasEnfermedad> leer (Context context){
        this.listaSintomasEnfermedad = new ArrayList<SintomasEnfermedad>();
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD,
        };

        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_ENFERMEDAD_SINTOMA, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );
        int idSintoma;
        int idEnfermedad;
        SintomasEnfermedad sintomasEnfermedad;
        if (cursor.moveToFirst()){

            idSintoma = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA));
            idEnfermedad = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD));
            sintomasEnfermedad = new SintomasEnfermedad(idEnfermedad, idSintoma);
            this.listaSintomasEnfermedad.add(sintomasEnfermedad);
            while(cursor.moveToNext()){
                idSintoma = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA));
                idEnfermedad = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD));
                sintomasEnfermedad = new SintomasEnfermedad(idEnfermedad, idSintoma);
                this.listaSintomasEnfermedad.add(sintomasEnfermedad);
            }
        }
        return listaSintomasEnfermedad;
    }
}
