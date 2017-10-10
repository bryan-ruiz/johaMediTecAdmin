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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarMedicamentoEnfermedadFragment extends Fragment {

    private static List<String> listaMedicamentosLocal;
    private View rootView;
    private ArrayList<MedicamentosEnfermedad> listaMedicamentos;
    public AgregarMedicamentoEnfermedadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_agregar_medicamento_enfermedad, container, false);
        obtenerDatos();
        return rootView;
    }

    public class AgregarMedicamentoEnfermedadFragmentAdapter extends ArrayAdapter<String> {
        private final Activity context;
        private CheckBox checkBox;

        public AgregarMedicamentoEnfermedadFragmentAdapter(Activity context) {
            super(context, R.layout.row_simple, listaMedicamentosLocal);
            this.context = context;
        }

        public View getView(final int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.row_simple, null, true);
            checkBox = (CheckBox) rowView.findViewById(R.id.boxSimple);
            checkBox.setText(listaMedicamentosLocal.get(position));
            int valorInt;
            valorInt = Global.posicionItemListViewPresionado;
            final Enfermedad enfermedad = Global.listaEnfermedades.get(valorInt);
            ArrayList<Medicamento> getMedicamentosEnfermedad = enfermedad.getMedicamentos(listaMedicamentos);
            for (int i = 0; i < getMedicamentosEnfermedad.size(); i++) {
                if (getMedicamentosEnfermedad.get(i).getNombre().equals(listaMedicamentosLocal.get(position))) {
                    checkBox.setChecked(true);
                }
            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        MedicamentosEnfermedad medicamentosEnfermedad = new MedicamentosEnfermedad(enfermedad.getIdEnfermedad(), Global.listaMedicamentos.get(position).getIdMedicamento());
                        medicamentosEnfermedad.insertar(getContext());
                        Toast.makeText(getActivity(), "Agregado", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            return rowView;
        }
    }

    private void cargarLista() {
        ListView lvDatos = (ListView) rootView.findViewById(R.id.lvMedicamentos);
        AgregarMedicamentoEnfermedadFragmentAdapter adapter= new AgregarMedicamentoEnfermedadFragmentAdapter(getActivity());
        lvDatos.setAdapter(adapter);
    }

    public void obtenerDatos() {
        listaMedicamentosLocal = new ArrayList<>();
        Global.listaMedicamentos = ListaMedicamentos.getInstancia().leer(getContext());
        listaMedicamentos = ListaMedicamentosEnfermedad.getInstancia().leer(getContext());
        for (int i =0; i < Global.listaMedicamentos.size(); i++) {
            listaMedicamentosLocal.add(Global.listaMedicamentos.get(i).getNombre());
        }
        cargarLista();
    }



}
