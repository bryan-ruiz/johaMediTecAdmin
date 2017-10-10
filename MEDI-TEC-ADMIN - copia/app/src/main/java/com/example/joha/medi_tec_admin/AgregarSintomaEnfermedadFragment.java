package com.example.joha.medi_tec_admin;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarSintomaEnfermedadFragment extends Fragment {

    private static List<String> listaSintomasLocal;
    private View rootView;
    private ArrayList<SintomasEnfermedad> listaSintomas;
    public AgregarSintomaEnfermedadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_agregar_sintoma_enfermedad, container, false);
        obtenerDatos();
        return rootView;
    }

    public class AgregarSintomasEnfermedadFragmentAdapter extends ArrayAdapter<String> {
        private final Activity context;
        private CheckBox checkBox;

        public AgregarSintomasEnfermedadFragmentAdapter(Activity context) {
            super(context, R.layout.row_simple, listaSintomasLocal);
            this.context = context;
        }

        public View getView(final int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.row_simple, null, true);
            checkBox = (CheckBox) rowView.findViewById(R.id.boxSimple);
            checkBox.setText(listaSintomasLocal.get(position));
            int valorInt;
            valorInt = Global.posicionItemListViewPresionado;
            final Enfermedad enfermedad = Global.listaEnfermedades.get(valorInt);
            ArrayList<Sintoma> getSintomasEnfermedad = enfermedad.getSintomas(listaSintomas);
            for (int i = 0; i < getSintomasEnfermedad.size(); i++) {
                if (getSintomasEnfermedad.get(i).getNombre().equals(listaSintomasLocal.get(position))) {
                    checkBox.setChecked(true);
                }
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        SintomasEnfermedad sintomasEnfermedad = new SintomasEnfermedad(enfermedad.getIdEnfermedad(), Global.listaSintomas.get(position).getId());
                        sintomasEnfermedad.insertar(getContext());
                        Toast.makeText(getActivity(), "Agregado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return rowView;
        }
    }

    private void cargarLista() {
        ListView lvDatos = (ListView) rootView.findViewById(R.id.lvSintomas);
        AgregarSintomaEnfermedadFragment.AgregarSintomasEnfermedadFragmentAdapter adapter= new AgregarSintomaEnfermedadFragment.AgregarSintomasEnfermedadFragmentAdapter(getActivity());
        lvDatos.setAdapter(adapter);
    }

    public void obtenerDatos() {
        listaSintomasLocal = new ArrayList<>();
        Global.listaSintomas = ListaSintomas.getInstancia().leer(getContext());
        listaSintomas = ListaSintomasEnfermedad.getInstancia().leer(getContext());
        for (int i =0; i < Global.listaSintomas.size(); i++) {
            listaSintomasLocal.add(Global.listaSintomas.get(i).getNombre());
        }
        cargarLista();
    }



}
