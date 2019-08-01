package com.dev.bootcamp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText edtFirstName,edtLastName,edtPhoneNumber;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtFirstName=findViewById(R.id.edt_first_name);
        edtLastName=findViewById(R.id.edt_last_name);
        edtPhoneNumber=findViewById(R.id.edt_phone_number);
        btnSubmit=findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(edtFirstName.getText()))
                {
                    edtFirstName.requestFocus();
                    edtFirstName.setError("Please enter first name");
                    return;
                }
                if(TextUtils.isEmpty(edtLastName.getText()))
                {
                    edtLastName.requestFocus();
                    edtLastName.setError("Please enter last name");
                    return;
                }
                if(TextUtils.isEmpty(edtPhoneNumber.getText()))
                {
                    edtPhoneNumber.requestFocus();

                    edtPhoneNumber.setError("Please enter phone number");
                    return;
                }


                final String firstName=edtFirstName.getText().toString();
                final String lastName=edtLastName.getText().toString();
                final String phoneNumber=edtPhoneNumber.getText().toString();
                Toast.makeText(MainActivity.this ,firstName+" " +
                        ""+lastName+"  "+phoneNumber,Toast.LENGTH_LONG).show();




                String url = "http://192.168.43.156/bootcamp/register.php";

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                        Toast.makeText(MainActivity.this,volleyError.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String, String> params = new HashMap<>();
                        params.put("firstname",firstName);
                        params.put("lastname",lastName);
                        params.put("phone",phoneNumber);
                        return params;
                    }
                };


                Volley.newRequestQueue(MainActivity.this).add(request);


            }
        });



    }





}
