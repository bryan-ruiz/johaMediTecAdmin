package com.example.joha.medi_tec_admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Bryan on 17/5/2017.
 */

public class Medicamento {
    private int idMedicamento;
    private String nombre;
    private String descripcion;

    public Medicamento(int idMedicamento, String nombre, String descripcion) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void insertar(Context context) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO, getIdMedicamento());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_NOMBRE, getNombre());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_DESCRIPCION, getDescripcion());
        // Insertar la nueva fila
        db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_MEDICAMENTO, null, values);
    }

    public void editar(Context context, String nombreN, String descripcionN) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase bd = dataBaseHelper.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_NOMBRE, nombreN);
        registro.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_DESCRIPCION, descripcionN);
        bd.update(DataBaseContract.DataBaseEntry.TABLE_NAME_MEDICAMENTO, registro, DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO + "='" + this.getIdMedicamento()+"'",null);
    }

    public void eliminar (Context context, int codigo){
        // usar la clase DataBaseHelper para realizar la operacion de eliminar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Define el where para el borrado
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_MEDICAMENTO_ID_MEDICAMENTO + " LIKE ?";
        // Se detallan los argumentos
        String[] selectionArgs = {String.valueOf(codigo)};
        // Realiza el SQL de borrado
        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_MEDICAMENTO, selection, selectionArgs);
    }
}
