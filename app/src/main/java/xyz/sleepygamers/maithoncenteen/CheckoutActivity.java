package xyz.sleepygamers.maithoncenteen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import xyz.sleepygamers.maithoncenteen.models.foodmenu;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        ArrayList<foodmenu> menuList;
        menuList = (ArrayList<foodmenu>) getIntent().getSerializableExtra("menuList");
        Toast.makeText(this, Integer.toString(menuList.size()), Toast.LENGTH_SHORT).show();
    }
}
