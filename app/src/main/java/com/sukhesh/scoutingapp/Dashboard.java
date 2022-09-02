package com.sukhesh.scoutingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Dashboard extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        TextView text = rootView.findViewById(R.id.dashboard_title);

        Match currentMatch = Match.MatchFromSharedPreferences(getContext().getSharedPreferences("quals", Context.MODE_PRIVATE));

        text.setText(currentMatch.matchName());

        //Qr generator, added implementation in build.gradle
        //https://github.com/zxing/zxing
        Button btGenerate = rootView.findViewById(R.id.bt_generate);
        ImageView ivOutput = rootView.findViewById(R.id.iv_output);
        String str = "Hello";
            btGenerate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MultiFormatWriter writer = new MultiFormatWriter();
                    try {
                        BitMatrix matrix = writer.encode(str, BarcodeFormat.QR_CODE, 500, 500);
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        Bitmap bitmap = encoder.createBitmap(matrix);
                        ivOutput.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            });
        return rootView;
    }
}