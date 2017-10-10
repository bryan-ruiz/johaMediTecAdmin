package com.example.joha.medi_tec_admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Bryan on 6/10/2017.
 */

public class SintomasEnfermedad {
    private int idEnfermedad;
    private int idSintoma;

    public SintomasEnfermedad(int idEnfermedad, int idSintoma) {
        this.idEnfermedad = idEnfermedad;
        this.idSintoma = idSintoma;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public int getIdSintoma() {
        return idSintoma;
    }

    public void setIdSintoma(int idSintoma) {
        this.idSintoma = idSintoma;
    }

    public void insertar(Context context) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA, getIdSintoma());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD, getIdEnfermedad());
        // Insertar la nueva fila
        db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_ENFERMEDAD_SINTOMA, null, values);
    }
}
