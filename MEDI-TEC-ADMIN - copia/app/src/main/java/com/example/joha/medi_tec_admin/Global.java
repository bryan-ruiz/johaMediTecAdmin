package com.example.joha.medi_tec_admin;

import java.util.ArrayList;

/**
 * Created by Juliana on 13/05/2017.
 */
public class Global {
    static ArrayList<Sintoma>  listaSintomas = new ArrayList<Sintoma>();
    static ArrayList<Enfermedad>  listaEnfermedades = new ArrayList<Enfermedad>();
    static ArrayList<Medicamento>  listaMedicamentos = new ArrayList<Medicamento>();
    static int posicionItemListViewPresionado;

    private static String baseurl ="http://172.24.41.77:8090";

    public static String getBaseUrl(){
        return baseurl;
    }
}
