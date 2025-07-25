package com.sukhesh.scoutingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.sukhesh.scoutingapp.storage.JSONStorage;

public class QRPage extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_qr, container, false);
        //Somehow have to combine shared preferences of all the inputs in dashboard and put it all into the qr good luck zayn
        SharedPreferences sp = requireContext().getSharedPreferences("matches", Context.MODE_PRIVATE);
        JSONStorage storage = new JSONStorage(sp);
        //String str = storage.makeQuickDebugString(sp.getString("currentMatch", "Q1"));
        String keyValueString = storage.makeCitrusCircuitsStyleString(sp.getString("currentMatch", "Q1"), getResources().getStringArray(R.array.codes));


        ImageView ivOutput = rootView.findViewById(R.id.iv_output);
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(keyValueString, BarcodeFormat.QR_CODE, 1000, 1000);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap = encoder.createBitmap(matrix);
            ivOutput.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return rootView;
    }
}