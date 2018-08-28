package xyz.sleepygamers.maithoncenteen;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.andremion.counterfab.CounterFab;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import xyz.sleepygamers.maithoncenteen.interfaces.AddorRemoveCallbacks;
import xyz.sleepygamers.maithoncenteen.models.foodmenu;
import xyz.sleepygamers.maithoncenteen.utils.Converter;
import xyz.sleepygamers.maithoncenteen.utils.MySingleton;

import static xyz.sleepygamers.maithoncenteen.utils.URLs.GET_FOODMENU;

public class FoodmenuActivity extends AppCompatActivity implements AddorRemoveCallbacks {

    List<foodmenu> menuList;
    RecyclerView recyclerView;
    public FoodmenuAdapter foodmenuAdapter;
    String type;
    ProgressBar pBar;
    TextView textView;
    FloatingActionButton fab;
    int cart_count = 0;
    CounterFab mCounterFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmenu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //  fab = (FloatingActionButton) findViewById(R.id.fab);

        type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        menuList = new ArrayList<>();
        foodmenuAdapter = new FoodmenuAdapter(FoodmenuActivity.this, menuList);
        recyclerView.setAdapter(foodmenuAdapter);
        pBar = findViewById(R.id.pBar);
        textView = findViewById(R.id.tvmenu);
        mCounterFab = (CounterFab) findViewById(R.id.counter_fab);
        mCounterFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentCheckout();
            }
        });
        loadfood();
    }

    private void intentCheckout() {
        ArrayList<foodmenu> checkoutList = new ArrayList<>();
        for (foodmenu m : menuList) {
            if (m.getCount() > 0) {
                checkoutList.add(m);
            }
        }
        if (checkoutList.isEmpty()) {
            Snackbar.make(findViewById(R.id.parentlayout), "No item in cart !!", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            return;
        }
        Intent intent = new Intent(FoodmenuActivity.this, CheckoutActivity.class);
        intent.putExtra("menuList", checkoutList);
        intent.putExtra("type",type);
        startActivity(intent);
    }

    public void loadfood() {
        setVisibility(false);
        textView.setVisibility(View.GONE);
        String url = GET_FOODMENU.concat(type);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                setVisibility(true);
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("menus");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject food = array.getJSONObject(i);
                        menuList.add(new foodmenu(
                                food.getInt("id"),
                                food.getString("name"),
                                food.getString("price"),
                                food.getString("type"))
                        );
                    }
                    foodmenuAdapter.notifyDataSetChanged();
                    if (menuList.isEmpty()) {
                        textView.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }


            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        setVisibility(true);
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);


    }

    private void setVisibility(boolean state) {
        if (state) {
            pBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            pBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.foodmenu, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_action);
        menuItem.setIcon(Converter.convertLayoutToImage(FoodmenuActivity.this, cart_count, R.drawable.ic_shopping_cart_white_24dp));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.cart_action) {
            intentCheckout();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAddProduct(int n) {
        cart_count = 0;
        for (foodmenu menu :
                menuList) {
            cart_count += menu.getCount();
        }
        mCounterFab.setCount(cart_count);
        invalidateOptionsMenu();
        Snackbar.make(findViewById(R.id.parentlayout), "Added to cart !!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();


    }

    @Override
    public void onRemoveProduct(int n) {
        cart_count = 0;
        for (foodmenu menu :
                menuList) {
            cart_count += menu.getCount();
        }
        mCounterFab.setCount(cart_count);
        invalidateOptionsMenu();
        Snackbar.make(findViewById(R.id.parentlayout), "Removed from cart !!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

    }
}
