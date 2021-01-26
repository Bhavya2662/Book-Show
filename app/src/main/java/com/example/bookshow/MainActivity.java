package com.example.bookshow;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.NetworkRegistrationInfo;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText search;
    private Button btn;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RequestQueue requestQueue;
    private ArrayList<Book> books;
    public Context context;
    private TextView errorMessage;

    private static  final  String URL="https://www.googleapis.com/books/v1/volumes?q=";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Bundle holds previous state when re-initialized
        super.onCreate(savedInstanceState);
        //Inflate the activity's UI
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        errorMessage = findViewById(R.id.message_display);
        search = findViewById(R.id.search_box);
        btn = findViewById(R.id.search_buttton);
        progressBar = findViewById(R.id.loading_indicator);
        books = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                books.clear();
                search();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private boolean NetworkStatus(Context context){
        CheckNetwork network = new CheckNetwork(getApplicationContext());
        network.registerNetworkCallback();

        // Check network connection
        if (Variables.isNetworkConnected){
            return true;
        }else{
            return false;
        }
    }

    private void parseJson(String key){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,key.toString(),null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Book data = gson.fromJson(URL, Book.class);
                        recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this,books));
                    }
                },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Something went wrong!",Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void search(){
        String searchQuery = search.getText().toString();
        boolean isConnected = NetworkStatus(this);
        if(!isConnected){
            errorMessage.setText("Please check your Internet Connection");
            recyclerView.setVisibility(View.INVISIBLE);
            errorMessage.setVisibility(View.VISIBLE);
        }
        if (searchQuery.equals("")){
            Toast.makeText(this,"Please search something.",Toast.LENGTH_SHORT).show();
        }
        String finalQuery = searchQuery.replace(" ","+");
        Uri uri=Uri.parse(URL+finalQuery);
        Uri.Builder builder = uri.buildUpon();
        parseJson(builder.toString());


    }
    }




