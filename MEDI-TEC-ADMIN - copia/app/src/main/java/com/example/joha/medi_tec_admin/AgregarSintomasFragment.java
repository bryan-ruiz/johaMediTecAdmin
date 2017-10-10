package com.example.joha.medi_tec_admin;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AgregarSintomasFragment extends Fragment {

    Button aceptarButton, cancelarButton;
    EditText sintomasAgregarInputNombre;
    String nombre;
    private View rootView;

    public AgregarSintomasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_agregar_sintomas, container, false);
        aceptarButton = (Button) rootView.findViewById(R.id.sintimas_agregar_button_Aceptar);
        cancelarButton = (Button) rootView.findViewById(R.id.sintimas_agregar_button_Cancelar);
        sintomasAgregarInputNombre = (EditText) rootView.findViewById(R.id.sintomasAgregarInpuNombre);
        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goPreviousFragment();
            }
        });
        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sintomasAgregarInputNombre.getText().toString().equals("")){
                    Snackbar.make(view, "Nombre vacio", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    nombre = sintomasAgregarInputNombre.getText().toString();
                    int idNuevo = Global.listaSintomas.size() + 1;
                    Sintoma sintoma = new Sintoma(idNuevo,nombre);
                    sintoma.insertar(getContext());
                    Toast.makeText(getActivity(), "Agregado ", Toast.LENGTH_SHORT).show();
                    //Global.listaSintomas.add(sintoma);
                    goPreviousFragment();
                }
            }
        });
        return inflater.inflate(R.layout.fragment_agregar_sintomas, container, false);
    }

    private void goPreviousFragment() {
        SintomasListaFragment sintomasListaFragment = new SintomasListaFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.ContentForFragments, sintomasListaFragment).commit();
    }
}
