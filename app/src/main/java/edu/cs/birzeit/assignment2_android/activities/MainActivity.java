package edu.cs.birzeit.assignment2_android.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.cs.birzeit.assignment2_android.R;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    private ListView lstTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
        lstTitles = findViewById(R.id.lstTitles);
    }

    public void btn_OnClick(View view) {

        String url = "https://poetrydb.org/author,title/Shakespeare;Sonnet";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                ArrayList<String> titles = new ArrayList<>();
                for (int i = 0; i < 30; i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        titles.add(obj.getString("title"));
                    } catch (JSONException exception) {
                        Log.d("volley_error", exception.toString());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1, titles);
                lstTitles.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volley_error", error.toString());
            }
        });

        queue.add(request);
    }
}