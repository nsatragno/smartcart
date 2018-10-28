package ar.com.smartcart.smartcart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

public class InicioFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        ((PrincipalActivity) getActivity()).getSupportActionBar().setTitle("Inicio SmartCart");

        Button btnVincularChango = view.findViewById(R.id.btn_vincular);
        Button btnAdminList = view.findViewById(R.id.btn_admin_list);
        Button btnPromos = view.findViewById(R.id.btn_promos);

        btnVincularChango.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PrincipalActivity) getActivity())
                        .setFragment(((PrincipalActivity) getActivity()).QR_SCAN, null);
            }
        });

        btnAdminList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PrincipalActivity) getActivity())
                        .setFragment(((PrincipalActivity) getActivity()).ADMIN_LISTAS, null);
            }
        });

        btnPromos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((PrincipalActivity) getActivity())
                        .setFragment(((PrincipalActivity) getActivity()).PROMOS, null);
            }
        });
        return view;
    }
}