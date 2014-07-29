package net.idawn.punch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScanFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ScanFragment extends android.support.v4.app.Fragment {

    private BootstrapButton btnScan;
    private BootstrapButton btnChk;
    private TextView tvId;
    private TextView tvTel;
    private TextView tvUsername;
    private TextView tvTime;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String toast = "";
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                toast = "Cancelled from fragment";
            } else {
                toast = "Scanned from fragment: " + result.getContents();
            }
        }
        Toast.makeText(getActivity(),toast,Toast.LENGTH_SHORT).show();
        tvId.setText(result.getContents().split(",")[0]);
        tvUsername.setText(result.getContents().split(",")[1]);
        tvTel.setText(result.getContents().split(",")[2]);
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        tvTime.setText(df.format(new Date()));
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnScan = (BootstrapButton) getActivity().findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new BootstrapButton.OnClickListener()
        {


            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"扫码",Toast.LENGTH_SHORT).show();
                scanFromFragment();
            }
        });
        btnChk = (BootstrapButton)getActivity().findViewById(R.id.btnChk);
        btnChk.setOnClickListener(new BootstrapButton.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvId = (TextView) getActivity().findViewById(R.id.tvId);
        tvUsername = (TextView) getActivity().findViewById(R.id.tvName);
        tvTel = (TextView) getActivity().findViewById(R.id.tvTel);
        tvTime = (TextView)getActivity().findViewById(R.id.tvTime);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_scan, container, false);
    }

    public void scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).initiateScan();
    }




}
