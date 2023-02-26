package com.narvasoft.bd;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;




import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import com.android.volley.Response;

public class SesionFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener {
    RequestQueue rq;
    JsonRequest jrq;
    EditText txtUser, txtPwd;
    Button btnConsultar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_sesion, container, false);
        View vista = inflater.inflate(R.layout.fragment_sesion, container, false);
        txtUser = (EditText) vista.findViewById(R.id.txtuser);
        txtPwd = (EditText) vista.findViewById(R.id.txtpwd);
        btnConsultar = (Button) vista.findViewById(R.id.btnsesion);

        // Crear una instancia de RequestQueue
        //RequestQueue queue = Volley.newRequestQueue(this);
        //    rq = Volley.newRequestQueue(getContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            rq = Volley.newRequestQueue(getContext());
        }


        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });
        return vista;
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Toast.makeText(getContext(),"No se encontró el usuario "+ error.toString(),Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onResponse(JSONObject response) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Toast.makeText(getContext(),"Se encontró el usuario "+ txtUser.toString(),Toast.LENGTH_SHORT).show();
        }
        User usuario = new User();
        JSONArray jsonArray = response.optJSONArray("datos");
        JSONObject jsonObject = null;
        try{
            jsonObject = jsonArray.getJSONObject(0);
            usuario.setUser(jsonObject.optString("user"));
            usuario.setPwd(jsonObject.optString("pwd"));
            usuario.setNames(jsonObject.optString("names"));

        }catch (JSONException e){
            e.printStackTrace();
        }

        Intent intencion = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            intencion = new Intent(getContext(), MainActivity2.class);
        }
        intencion.putExtra(MainActivity2.nombres, usuario.getNames());
        startActivity(intencion);


    }
    void iniciarSesion(){
        String url ="http://192.168.1.4/login/sesion.php?user="+txtUser.getText().toString()+
          "&pwd="+txtPwd.getText().toString();
        jrq = new JsonObjectRequest(Request.Method.GET,url, null,this, this);
        rq.add(jrq);
    }


}