package com.example.joha.medi_tec_admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Bryan on 2/10/2017.
 */

public class MedicamentosEnfermedad {
    private int idEnfermedad;
    private int idMedicamento;

    public MedicamentosEnfermedad(int idEnfermedad, int idMedicamento) {
        this.idEnfermedad = idEnfermedad;
        this.idMedicamento = idMedicamento;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public void insertar(Context context) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO, getIdMedicamento());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD, getIdEnfermedad());
        // Insertar la nueva fila
        db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_ENFERMEDAD_MEDICAMENTO, null, values);
    }
}
