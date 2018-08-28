package xyz.sleepygamers.maithoncenteen;

import android.content.Intent;
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

import java.util.ArrayList;

import xyz.sleepygamers.maithoncenteen.models.foodmenu;

public class CheckoutActivity extends AppCompatActivity {
    String type;
    RecyclerView recyclerView;
    CheckoutAdapter checkoutAdapter;
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
       int tot_price =0;
        for (foodmenu m:menuList) {
            tot_price += m.getCount() * Integer.parseInt(m.getPrice());
        }
        TextView totPrice  = findViewById(R.id.tv_totprice);
        totPrice.setText(Integer.toString(tot_price));
        Button checkout = findViewById(R.id.checkout);
        final RadioGroup rg = findViewById(R.id.rg_order);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rg.getCheckedRadioButtonId() == R.id.choice1){

                }else{

                }
                placeOrder();
            }
        });
    }

    private void placeOrder() {

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
