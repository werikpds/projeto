package com.projeto.projetopi;

import android.graphics.Bitmap;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;

public class ProfessorHome extends AppCompatActivity {

    private FloatingActionButton button;
    private ImageView qrCode ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.button = (FloatingActionButton) findViewById(R.id.fab);
        this.qrCode = (ImageView) findViewById(R.id.qrImg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gerarQRCode();
            }
        });

    }

    private void gerarQRCode() {
        String codigo = passGenerator(10);
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(codigo, BarcodeFormat.QR_CODE,1000,1000);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrCode.setImageBitmap(bitmap);
            System.out.println("Chegou aqui");
        }catch (WriterException we){
            we.printStackTrace();
        }catch (Exception e){

        }
    }


    public String passGenerator(int passLength){
        java.util.Random r = new java.util.Random();
        char[] goodChar = { 'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h','i', 'j', 'k','l', 'm', 'n','o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'x','w',
                'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I','J', 'K','L',
                'M', 'N','O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z','1',
                '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < passLength; i++) {
            sb.append(goodChar[r.nextInt(goodChar.length)]);
        }
        System.out.println(sb.toString());
        return sb.toString();

    }

}
