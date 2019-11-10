package com.example.volleydenemetwo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main4Activity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView rv;
    private ArrayList<Kelimeler> kelimelerListe;
    private ArrayList<Kelimeler> kelimelerListetwo;


    private KelimelerAdapter adapter;
    Button go;
    EditText akis;

    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);

        go = findViewById(R.id.go);
        akis = findViewById(R.id.akis);
        mQueue = Volley.newRequestQueue(this);
        // jsonParse();


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(akis.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "boş", Toast.LENGTH_SHORT).show();
                }

                else{


                    kisiekle();
                    finish();
                    startActivity(getIntent());

                }
            }


        });




        toolbar.setTitle("Sözlük Uygulaması");
        setSupportActionBar(toolbar);

        rv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);

        tumKelimeler();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);

        MenuItem item = menu.findItem(R.id.action_ara);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.e("Gönderilen arama",query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }


    public void tumKelimeler(){

        String url = "https://goldgym.pro/akis.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                kelimelerListe = new ArrayList<>();


                try {
                    JSONObject jsonObj = new JSONObject(response);


                    JSONArray kelimeler = jsonObj.getJSONArray("employees");

                    // looping through All Contacts
                    for (int i = 0; i < kelimeler.length(); i++) {
                        JSONObject k = kelimeler.getJSONObject(i);


                        String turkce = k.getString("akis");
                        int id = k.getInt("id");
                        String likebtn = k.getString("likebtn");




                        Kelimeler kelime = new Kelimeler(turkce,id,likebtn);





                        kelimelerListe.add(kelime); // Çekilen Twit

                    }

                    adapter = new KelimelerAdapter(MainActivity.this,kelimelerListe);




                    rv.setAdapter(adapter);





                } catch (JSONException e) {


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(MainActivity.this).add(stringRequest);

    }


    public void kisiekle(){
        String url = "https://goldgym.pro/akis.php";
        final String akiss = akis.getText().toString().trim();



        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("Cevap",response);

                try{
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray movies = jsonObject.getJSONArray("employees");




                    StringBuilder sb = new StringBuilder();

                    for (int i=0; i < movies.length();i++){
                        JSONObject b = movies.getJSONObject(i);

                        String akis_ad = b.getString("akis");


                        // card_text.append(akis_ad + ","  + "\n\n");





                    }








                } catch (JSONException e){
                    e.printStackTrace();

                }





            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {


                Map<String,String> params = new HashMap<>();

                params.put("akis",akiss);


                return params;

            }
        };
        Volley.newRequestQueue(this).add(istek);


    }

    private void jsonParse(){



        String url  ="https://goldgym.pro/akis.php";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("employees");
                            for (int i = 0; i < jsonArray.length();i++){

                                JSONObject employee = jsonArray.getJSONObject(i);
                                String akis = employee.getString("akis");
                                //String lastName = employee.getString("lastName");

                                //card_text.append(akis + ","  + "\n\n");
                            }

                            String [] cardNames = {"a", "b", " c", "d"," e"};
                            ArrayList<String> cardName = new ArrayList();

                            for(int i = 0; i < cardNames.length; i++){
                                cardName.add(cardNames[i]);

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        mQueue.add(request);


    }
}

