package com.example.joha.medi_tec_admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Juliana on 15/05/2017.
 */
public class Enfermedad {
    private int idEnfermedad;
    private String nombre;
    private String descripcion;
    private String peligrosidad;
    private ArrayList<Sintoma> listaSintomas;
    private ArrayList<Medicamento> listaMedicamentos;
    public Enfermedad(int idVar, String nombreVar, String descripVar){
        this.idEnfermedad = idVar;
        this.nombre = nombreVar;
        this.descripcion = descripVar;
        this.listaSintomas  = new ArrayList<Sintoma>();
        this.listaMedicamentos  = new ArrayList<Medicamento>();
        this.peligrosidad = "bajo";
    }

    public String getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(String peligrosidad) {
        this.peligrosidad = peligrosidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
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
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD, getIdEnfermedad());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_NOMBRE, getNombre());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_DESCRIPCION, getDescripcion());
        values.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_PELIGROSIDAD, "Bajo");
        // Insertar la nueva fila
        db.insert(DataBaseContract.DataBaseEntry.TABLE_NAME_ENFERMEDAD, null, values);
    }

    public ArrayList<Medicamento> getMedicamentos(ArrayList<MedicamentosEnfermedad> medicamentosEnfermedads){
        for (int i = 0; i < medicamentosEnfermedads.size(); i++) {
            if (medicamentosEnfermedads.get(i).getIdEnfermedad() == getIdEnfermedad()) {
                for (int j = 0; j < Global.listaMedicamentos.size(); j++) {
                    if (Global.listaMedicamentos.get(j).getIdMedicamento() == medicamentosEnfermedads.get(i).getIdMedicamento()) {
                        this.listaMedicamentos.add(Global.listaMedicamentos.get(j));
                    }
                }
            }
        }
        return listaMedicamentos;
    }

    public ArrayList<Sintoma> getSintomas(ArrayList<SintomasEnfermedad> sintomasEnfermedads){
        for (int i = 0; i < sintomasEnfermedads.size(); i++) {
            if (sintomasEnfermedads.get(i).getIdEnfermedad() == getIdEnfermedad()) {
                for (int j = 0; j < Global.listaSintomas.size(); j++) {
                    if (Global.listaSintomas.get(j).getId() == sintomasEnfermedads.get(i).getIdEnfermedad()) {
                        this.listaSintomas.add(Global.listaSintomas.get(j));
                    }
                }
            }
        }
        return listaSintomas;
    }

    public String leerPeligrosidad (Context context){
        // usar la clase DataBaseHelper para realizar la operacion de leer
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo lectura
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        // Define cuales columnas quiere solicitar // en este caso todas las de la clase
        String[] projection = {
                DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_PELIGROSIDAD,
        };

        // Resultados en el cursor
        Cursor cursor = db.query(
                DataBaseContract.DataBaseEntry.TABLE_NAME_ENFERMEDAD, // tabla
                projection, // columnas
                null, // where
                null, // valores del where
                null, // agrupamiento
                null, // filtros por grupo
                null // orden
        );
        String peligrosidad = "";
        if (cursor.moveToFirst()){
            peligrosidad = cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_PELIGROSIDAD));
        }
        return peligrosidad;
    }

    public void editar(Context context, String nombreN, String descripcionN) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase bd = dataBaseHelper.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_NOMBRE, nombreN);
        registro.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_DESCRIPCION, descripcionN);
        bd.update(DataBaseContract.DataBaseEntry.TABLE_NAME_ENFERMEDAD, registro, DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD + "='" + this.getIdEnfermedad()+"'",null);
    }

    public void editarPeligrosidad(Context context, String peligrosidadN) {
        // usar la clase DataBaseHelper para realizar la operacion de insertar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase bd = dataBaseHelper.getWritableDatabase();
        ContentValues registro = new ContentValues();
        registro.put(DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_PELIGROSIDAD, peligrosidadN);
        bd.update(DataBaseContract.DataBaseEntry.TABLE_NAME_ENFERMEDAD, registro, DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD + "='" + this.getIdEnfermedad()+"'",null);
    }

    public void eliminar (Context context, int codigo){
        // usar la clase DataBaseHelper para realizar la operacion de eliminar
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        // Obtiene la base de datos en modo escritura
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        // Define el where para el borrado
        String selection = DataBaseContract.DataBaseEntry.COLUMN_NAME_ENFERMEDAD_ID_ENFERMEDAD + " LIKE ?";
        // Se detallan los argumentos
        String[] selectionArgs = {String.valueOf(codigo)};
        // Realiza el SQL de borrado
        db.delete(DataBaseContract.DataBaseEntry.TABLE_NAME_ENFERMEDAD, selection, selectionArgs);
    }
}
