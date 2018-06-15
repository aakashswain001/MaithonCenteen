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

public class BreakfastActivity extends AppCompatActivity {

    private static final String URL_BREAKFAST = "http://www.sleepygamers.xyz/tatapower/foodmenu.php?apicall=getpics&category=breakfast";
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

        breakfastList=new ArrayList<>();
        BreakfastAdapter = new BreakfastAdapter(BreakfastActivity.this, breakfastList);
        recyclerView.setAdapter(BreakfastAdapter);



        loadfood();
    }
   private void LoadfoodDummy(){
        for(int i=0;i<10;i++){
            foodmenu f=new foodmenu(i,"a","12","1");

            breakfastList.add(f);
        }
        BreakfastAdapter.notifyDataSetChanged();

    }
    public void loadfood(){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL_BREAKFAST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array  = object.getJSONArray("images");

                    for (int i = 1; i <= array.length(); i++) {
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
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        Volley.newRequestQueue(this).add(stringRequest);
    }
}
