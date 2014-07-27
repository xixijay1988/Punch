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

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


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

    private Button btnScan;
    private TextView tvScan;

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
        tvScan.setText(result.getContents());
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btnScan = (Button) getActivity().findViewById(R.id.btnScan);
        btnScan.setOnClickListener(new Button.OnClickListener()
        {


            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"扫码",Toast.LENGTH_SHORT).show();
                scanFromFragment();
            }
        });
        tvScan = (TextView) getActivity().findViewById(R.id.scan_text);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_scan, container, false);
/*        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        FrameLayout fl = new FrameLayout(getActivity());
        fl.setLayoutParams(params);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, dm);
        //TextView v = new TextView(getActivity());
        Button btn = new Button(getActivity());
        params.setMargins(margin, margin, margin, margin);
        btn.setLayoutParams(params);
        btn.setLayoutParams(params);
        btn.setGravity(Gravity.CENTER);
        btn.setText("扫码");
        btn.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, dm));*/
    }

    public void scanFromFragment() {
        IntentIntegrator.forSupportFragment(this).initiateScan();
    }




}
