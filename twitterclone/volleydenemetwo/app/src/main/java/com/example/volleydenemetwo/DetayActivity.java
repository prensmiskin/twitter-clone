package com.example.volleydenemetwo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetayActivity extends AppCompatActivity {
    private TextView textViewIngilizce;
    private TextView textViewTurkce;
    private TextView idtext;
    private Kelimeler kelime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        textViewIngilizce = findViewById(R.id.textViewIngilizce);
        textViewTurkce = findViewById(R.id.textViewTurkce);
      //  idtext = findViewById(R.id.idtext);

        kelime = (Kelimeler) getIntent().getSerializableExtra("nesne");

        textViewTurkce.setText(kelime.getTurkce());
       // idtext.setText(kelime.getId());




    }
}
