package xyz.sleepygamers.maithoncenteen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import xyz.sleepygamers.maithoncenteen.models.Order;
import xyz.sleepygamers.maithoncenteen.models.foodmenu;
import xyz.sleepygamers.maithoncenteen.utils.MySingleton;
import xyz.sleepygamers.maithoncenteen.utils.SharedPrefManager;
import xyz.sleepygamers.maithoncenteen.utils.URLs;

public class CheckoutActivity extends AppCompatActivity {
    String type;
    RecyclerView recyclerView;
    CheckoutAdapter checkoutAdapter;
    String date, orderString = "", deliveryType;
    int tot_price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ArrayList<foodmenu> menuList;
        menuList = (ArrayList<foodmenu>) getIntent().getSerializableExtra("menuList");
        type = getIntent().getStringExtra("type");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Cart (" + menuList.size() + ")");


        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        checkoutAdapter = new CheckoutAdapter(CheckoutActivity.this, menuList);
        recyclerView.setAdapter(checkoutAdapter);

        for (foodmenu m : menuList) {
            tot_price += m.getCount() * Integer.parseInt(m.getPrice());
        }


        //getting the menu list as comma separated string
        for (int i = 0; i < menuList.size(); i++) {
            String singleOrderString = menuList.get(i).getName() + " " + menuList.get(i).getPrice() + " " + Integer.toString(menuList.get(i).getCount());
            if (i != menuList.size() - 1)
                orderString += singleOrderString + ",";
            else
                orderString += singleOrderString;
        }

        TextView totPrice = findViewById(R.id.tv_totprice);
        totPrice.setText(Integer.toString(tot_price));
        Button checkout = findViewById(R.id.checkout);
        final RadioGroup rg = findViewById(R.id.rg_order);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rg.getCheckedRadioButtonId() == R.id.choice1) {
                    deliveryType = "home";
                } else {
                    deliveryType = "normal";
                }
                placeOrder();
            }
        });
    }

    //for putting into order database
    private void placeOrder() {
        String url = URLs.URL_ORDER.concat("?apicall=add");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded; charset=UTF-8";
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", SharedPrefManager.getInstance(getApplicationContext()).getUser().getId());
                params.put("order_details", orderString);
                params.put("price", Integer.toString(tot_price));
                params.put("order_type", type);
                params.put("delivery_type", deliveryType);
                return params;
            }
        };

        //creating a request queue
        MySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
