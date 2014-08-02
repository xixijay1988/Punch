package net.idawn.punch;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.qrcode.encoder.Encoder;

import java.lang.reflect.GenericArrayType;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CreateFragment extends android.support.v4.app.Fragment {

    BootstrapEditText edName;
    BootstrapEditText edTel;
    BootstrapButton btnID;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    BootstrapButton btnAdd;
    ImageView imageQR;
    ImageView zxingQR;
    private static int id = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        edName = (BootstrapEditText) getActivity().findViewById(R.id.edName);
        edTel = (BootstrapEditText) getActivity().findViewById(R.id.edTel);
        btnID = (BootstrapButton) getActivity().findViewById(R.id.btnID);
        btnAdd = (BootstrapButton) getActivity().findViewById(R.id.btnAdd);
        imageQR = (ImageView) getActivity().findViewById(R.id.imageQR);
        //imageQR.setId(R.id.zxing_barcode_image_view);
        zxingQR = (ImageView) getActivity().findViewById(R.id.zxing_image_view);
        //aa.getDrawingCache();
        btnAdd.setOnClickListener(new BootstrapButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                //qrCodeEncoder = new QRCodeEncoder(this, intent, smallerDimension, useVCard);
                create();

            }
        });
    }

    private void create(){
        IntentIntegrator.forSupportFragment(this).shareText(id + "," +
                edName.getText().toString()+","+edTel.getText().toString());
        btnID.setText(id +"");
        id++;
    }

}
