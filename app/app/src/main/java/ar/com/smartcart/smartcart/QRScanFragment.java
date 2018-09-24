package ar.com.smartcart.smartcart;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.internal.NavigationMenu;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QRScanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QRScanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QRScanFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String toast;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public QRScanFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QRScanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QRScanFragment newInstance(String param1, String param2) {
        QRScanFragment fragment = new QRScanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_qrscan, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        scanFromFragment();
    }

    public void scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .setPrompt("Escaneá el código QR del Chango")
                .initiateScan();
    }

    private void displayToast() {
        if(getActivity() != null && toast != null) {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
            toast = null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                toast = "Vinculación de Chango cancelada";
            } else {
                String[] datosChango = result.getContents().split("\\|");
                Long idChango = Long.parseLong(datosChango[0]);
                String codChango = datosChango[1];
                toast = "Chango: " + codChango + " vinculado.";
                vincularChangoApp(idChango, codChango);
            }

            // At this point we may or may not have a reference to the activity
            displayToast();
            ((PrincipalActivity) getActivity())
                    .setFragment(((PrincipalActivity) getActivity()).ADMIN_LIST);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Long changoID);
    }

    public void vincularChangoApp(Long id, String cod){
        ((PrincipalActivity) getActivity()).getChango().setId(id);
        ((PrincipalActivity) getActivity()).getChango().setCodigo(cod);
        NavigationView navigationView = (NavigationView)
                ((PrincipalActivity) getActivity()).findViewById(R.id.nav_view);
        TextView txt = (TextView) navigationView.findViewById(R.id.textView);
        txt.setText(cod);
    }
}