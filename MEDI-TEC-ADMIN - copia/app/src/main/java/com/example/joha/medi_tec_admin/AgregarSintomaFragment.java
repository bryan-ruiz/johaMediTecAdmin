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
public class AgregarSintomaFragment extends Fragment {

    Button aceptarButton, cancelarButton;
    EditText sintomasAgregarInputNombre;
    String nombre;
    private View rootView;

    public AgregarSintomaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_agregar_sintoma, container, false);
        aceptarButton = (Button) rootView.findViewById(R.id.aceptar);
        sintomasAgregarInputNombre = (EditText) rootView.findViewById(R.id.sintomasAgregarInpuNombre);
        cancelarButton = (Button) rootView.findViewById(R.id.cancelar);
        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "tttttt ", Toast.LENGTH_SHORT).show();
                if(sintomasAgregarInputNombre.getText().toString().equals("")){
                    Snackbar.make(v, "Nombre vacio", Snackbar.LENGTH_LONG)
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

        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "adsfasdfad ", Toast.LENGTH_SHORT).show();
                goPreviousFragment();
            }
        });
        return rootView;
    }

    private void goPreviousFragment() {
        SintomasListaFragment sintomasListaFragment = new SintomasListaFragment();
        FragmentManager manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.ContentForFragments, sintomasListaFragment).commit();
    }

}
