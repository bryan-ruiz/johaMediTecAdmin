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
public class EditarEnfermedadFragment extends Fragment {

    Button aceptarButton, cancelarButton;
    EditText input_nombre, input_descripcion;
    String stringNombre,stringDescripcion;
    Bundle bundle;
    String valorString;
    int valorInt;
    private View rootView;
    Enfermedad enfermedad;

    public EditarEnfermedadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_editar_enfermedad, container, false);

        valorInt = Global.posicionItemListViewPresionado;

        aceptarButton = (Button) rootView.findViewById(R.id.enfermedades_editar_button_aceptar);
        cancelarButton = (Button) rootView.findViewById(R.id.enfermedades_editar_button_cancelar);

        input_nombre = (EditText) rootView.findViewById(R.id.enfermedades_editar_input_nombre);
        input_descripcion = (EditText) rootView.findViewById(R.id.enfermedades_editar_input_descripcion);



        enfermedad = Global.listaEnfermedades.get(valorInt);
        stringNombre = enfermedad.getNombre();
        stringDescripcion = enfermedad.getDescripcion();

        input_nombre.setText(stringNombre);
        input_descripcion.setText(stringDescripcion);

        cancelarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goPreviousFragment();
            }
        });
        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input_nombre.getText().toString().equals("")){
                    Snackbar.make(view, "Nombre vacio", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else if(input_descripcion.getText().toString().equals("")){
                    Snackbar.make(view, "Descripcion vacio", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    stringNombre = input_nombre.getText().toString();
                    stringDescripcion = input_descripcion.getText().toString();
                    Enfermedad enfermedad = Global.listaEnfermedades.get(valorInt);
                    enfermedad.editar(getContext(),stringNombre,stringDescripcion);
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
