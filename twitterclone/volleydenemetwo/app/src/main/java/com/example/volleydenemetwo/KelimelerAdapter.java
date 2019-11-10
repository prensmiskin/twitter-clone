package com.example.volleydenemetwo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kasimadalan on 1.05.2018.
 */

public class KelimelerAdapter extends RecyclerView.Adapter<KelimelerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private int sayaç ;
    private List<Kelimeler> kelimelerListe;



    public KelimelerAdapter(Context mContext, List<Kelimeler> kelimelerListe) {
        this.mContext = mContext;
        this.kelimelerListe = kelimelerListe;

    }




    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_tasarim,parent,false);

        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(final CardTasarimTutucu holder, int position) {
        final Kelimeler kelime = kelimelerListe.get(position);
        final Kelimeler kelimeS = kelimelerListe.get(position);


        holder.textViewTurkce.setText(kelime.getTurkce());
        holder.idtext.setText(String.valueOf(kelime.getId()));
        holder.txt.setText(kelime.getLikebtn());
        //holder.txt.setText(String.valueOf(kelime.getLikebtn()));


        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayaç = Integer.valueOf((String) holder.txt.getText());

                sayaç ++ ;
               holder.txt.setText(Integer.toString(sayaç));
                kisiekle(holder);

            }
        });

        holder.kelime_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mContext,DetayActivity.class);

                intent.putExtra("nesne",kelime);

                mContext.startActivity(intent);


            }
        });



    }



    @Override
    public int getItemCount() {
        return kelimelerListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        public TextView textViewIngilizce,txt,idtext;
        private TextView textViewTurkce;
        private TextView textViewdeneme;
        private CardView kelime_card;
        private Button btn;
        private int sayaç;

        private CardView kelime_cardtwo ;
        public CardTasarimTutucu(View itemView) {
            super(itemView);
            textViewIngilizce = itemView.findViewById(R.id.textViewIngilizce);
            textViewTurkce = itemView.findViewById(R.id.textViewTurkce);
            idtext = itemView.findViewById(R.id.idtext);
            kelime_card = itemView.findViewById(R.id.kelime_card);
            kelime_cardtwo = itemView.findViewById(R.id.kelime_cardtwo);
            btn = itemView.findViewById(R.id.btn);
            txt = itemView.findViewById(R.id.txt);
           // idtext = itemView.findViewById(R.id.idtext);
        }
    }

    public void kisiekle(final CardTasarimTutucu holder){
        String url = "https://goldgym.pro/likeservice.php";
        final String userlike = holder.txt.getText().toString().trim();
        final String idtext = holder.idtext.getText().toString().trim();


        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("sus", String.valueOf(holder.getAdapterPosition()));



                try{
                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("success");

                    Log.e("sus",succes);






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
                Map<String, String> params = new HashMap<>();

                params.put("like",userlike);
                params.put("id",idtext);
                return params;
            }
        };
        Volley.newRequestQueue(mContext).add(istek);


    }





}
