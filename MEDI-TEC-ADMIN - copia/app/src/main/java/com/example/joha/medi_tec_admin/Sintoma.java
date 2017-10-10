package com.example.joha.medi_tec_admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Juliana on 13/05/2017.
 */
public class Sintoma {
    int id;
    String nombre;
    public Sintoma(int idVar, String nom){
        this.id = idVar;
        this.nombre = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void insertar(Context context) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Crear un mapa de valores donde las columnas son las llaves
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA, getId());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_NOMBRE, getNombre());
        // Insertar la nueva fila
        db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_SINTOMA, null, values);
    }

    public void editar(Context context, String nombreN) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase bd = dataBaseHelper.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_NOMBRE, nombreN);
        bd.update(DataBaseContract.DataBaseEntry.TABLE_NAME_SINTOMA, registro, DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA + "='" + this.getId()+"'",null);
    }

    public void eliminar (Context context, int codigo){
        // usar la clase DataBaseHelper para realizar la operacion de eliminar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Define el where para el borrado
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_SINTOMA_ID_SINTOMA + " LIKE ?";
        // Se detallan los argumentos
        String[] selectionArgs = {String.valueOf(codigo)};
        // Realiza el SQL de borrado
        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_SINTOMA, selection, selectionArgs);
    }
}
