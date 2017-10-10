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

import com.example.joha.medi_tec_admin.Global;
import com.example.joha.medi_tec_admin.MedicamentosListaFragment;
import com.example.joha.medi_tec_admin.R;
import com.example.joha.medi_tec_admin.Sintoma;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditarSintomasFragment extends Fragment {

    EditText editarSintmasInputNombre;
    Button editsrSintomasButtonAceptar;
    Bundle bundle;
    String valorString, nuevoNombre;
    int valorInt;
    private View rootView;

    Sintoma sintomaEditar;

    public EditarSintomasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_editar_sintomas, container, false);
        valorInt = Global.posicionItemListViewPresionado;
        editarSintmasInputNombre = (EditText) rootView.findViewById(R.id.editarSintomasInputNombre);
        editsrSintomasButtonAceptar = (Button) rootView.findViewById(R.id.editarSintomasButtonAceptar);
        sintomaEditar = Global.listaSintomas.get(valorInt);
        editarSintmasInputNombre.setText(sintomaEditar.getNombre());
        editsrSintomasButtonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoNombre = editarSintmasInputNombre.getText().toString();
                if(editarSintmasInputNombre.getText().toString().equals("")){
                    Snackbar.make(view, "Nombre vacio", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else{
                    Sintoma sintoma = Global.listaSintomas.get(valorInt);
                    sintoma.editar(getContext(),nuevoNombre);
                    goPreviousFragment();
                }

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
