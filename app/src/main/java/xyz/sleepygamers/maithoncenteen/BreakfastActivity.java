package xyz.sleepygamers.maithoncenteen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import xyz.sleepygamers.maithoncenteen.models.foodmenu;
import xyz.sleepygamers.maithoncenteen.utils.MySingleton;

import static xyz.sleepygamers.maithoncenteen.utils.URLs.URL_BREAKFAST;

public class BreakfastActivity extends AppCompatActivity {

    List<foodmenu> breakfastList;
    RecyclerView recyclerView;
    public BreakfastAdapter BreakfastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        breakfastList = new ArrayList<>();
        BreakfastAdapter = new BreakfastAdapter(BreakfastActivity.this, breakfastList);
        recyclerView.setAdapter(BreakfastAdapter);
        loadfood();
    }

    public void loadfood() {
        String urlBreakfast = URL_BREAKFAST.concat("breakfast");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlBreakfast, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Toast.makeText(BreakfastActivity.this, "Received", Toast.LENGTH_SHORT).show();
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("images");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject food = array.getJSONObject(i);
                        breakfastList.add(new foodmenu(
                                food.getInt("id"),
                                food.getString("name"),
                                food.getString("price"),
                                food.getString("img")
                        ));
                    }
                    BreakfastAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Toast.makeText(BreakfastActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }
}
