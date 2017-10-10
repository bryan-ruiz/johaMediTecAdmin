package com.example.joha.medi_tec_admin;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Bryan on 6/10/2017.
 */

public class ListaMedicamentosEnfermedad {
    private static ListaMedicamentosEnfermedad instancia = null;
    private ArrayList<MedicamentosEnfermedad> listaMedicamentosEnfermedad;

    public static ListaMedicamentosEnfermedad getInstancia() {
        if (instancia == null) {
            instancia = new ListaMedicamentosEnfermedad();
        }
        return instancia;
    }

    public ArrayList<MedicamentosEnfermedad> leer (Context context){
        this.listaMedicamentosEnfermedad = new ArrayList<MedicamentosEnfermedad>();
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO,
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD,
        };

        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_ENFERMEDAD_MEDICAMENTO, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );
        int idMedicamento;
        int idEnfermedad;
        MedicamentosEnfermedad medicamentosEnfermedad;
        if (cursor.moveToFirst()){

            idMedicamento = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO));
            idEnfermedad = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD));
            medicamentosEnfermedad = new MedicamentosEnfermedad(idEnfermedad, idMedicamento);
            this.listaMedicamentosEnfermedad.add(medicamentosEnfermedad);
            while(cursor.moveToNext()){
                idMedicamento = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO));
                idEnfermedad = cursor.getInt(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD));
                medicamentosEnfermedad = new MedicamentosEnfermedad(idEnfermedad, idMedicamento);
                this.listaMedicamentosEnfermedad.add(medicamentosEnfermedad);
            }
        }
        return listaMedicamentosEnfermedad;
    }
}
