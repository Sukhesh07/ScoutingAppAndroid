package com.sukhesh.scoutingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class FinishTab extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_finish_tab, container, false);
        View rootView2 = inflater.inflate(R.layout.activity_main, container, false);
        //Somehow have to combine shared preferences of all the inputs in dashboard and put it all into the qr good luck zayn
        String str = "Hello";
        ImageView ivOutput = rootView.findViewById(R.id.iv_output);
        MultiFormatWriter writer = new MultiFormatWriter();
            try {
                BitMatrix matrix = writer.encode(str, BarcodeFormat.QR_CODE, 1000, 1000);
                BarcodeEncoder encoder = new BarcodeEncoder();
                Bitmap bitmap = encoder.createBitmap(matrix);
                ivOutput.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        return rootView;
    }
}