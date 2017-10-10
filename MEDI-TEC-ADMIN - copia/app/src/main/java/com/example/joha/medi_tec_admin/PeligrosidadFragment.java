package com.example.joha.medi_tec_admin;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class PeligrosidadFragment extends Fragment {

    private View rootView;
    private RadioButton radioButtonAlto, radioButtonMedio, radioButtonBajo;
    private int valorInt;
    private Enfermedad enfermedad;

    public PeligrosidadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_peligrosidad, container, false);
        radioButtonAlto = (RadioButton) rootView.findViewById(R.id.radioButtonAlto);
        radioButtonMedio = (RadioButton) rootView.findViewById(R.id.radioButtonMedio);
        radioButtonBajo = (RadioButton) rootView.findViewById(R.id.radioButtonBajo);
        valorInt = Global.posicionItemListViewPresionado;
        enfermedad = Global.listaEnfermedades.get(valorInt);
        String pel = enfermedad.leerPeligrosidad(getContext());
        Toast.makeText(getActivity(), pel, Toast.LENGTH_SHORT).show();
        if (pel.equals("Alto")) {
            radioButtonAlto.setChecked(true);
        }
        else if (pel.equals("Medio")) {
            radioButtonMedio.setChecked(true);
        }
        else if (pel.equals("Bajo")) {
            radioButtonBajo.setChecked(true);
        }
        radioButtonAlto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (radioButtonAlto.isChecked()) {
                    Toast.makeText(getActivity(), "check ", Toast.LENGTH_SHORT).show();
                    enfermedad.editarPeligrosidad(getContext(),"Alto");
                    goPreviousFragment();
                }
            }
        });

        radioButtonMedio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (radioButtonMedio.isChecked()) {
                    Toast.makeText(getActivity(), "check ", Toast.LENGTH_SHORT).show();
                    enfermedad.editarPeligrosidad(getContext(),"Medio");
                    goPreviousFragment();
                }
            }
        });

        radioButtonBajo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (radioButtonBajo.isChecked()) {
                    Toast.makeText(getActivity(), "check ", Toast.LENGTH_SHORT).show();
                    enfermedad.editarPeligrosidad(getContext(),"Bajo");
                    goPreviousFragment();
                }
            }
        });

        return rootView;
    }
    private void goPreviousFragment() {
        EnfermedadesListaFragment enfermedadesListaFragment = new EnfermedadesListaFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.ContentForFragments, enfermedadesListaFragment).commit();
    }
}
