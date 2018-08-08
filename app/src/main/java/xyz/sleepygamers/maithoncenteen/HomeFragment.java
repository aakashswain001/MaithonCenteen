package xyz.sleepygamers.maithoncenteen;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    View mView;
    LinearLayout breakfast,lunch,snacks,dinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        breakfast = mView.findViewById(R.id.breakfast);
        lunch = mView.findViewById(R.id.lunch);
        snacks = mView.findViewById(R.id.snacks);
        dinner = mView.findViewById(R.id.dinner);

        breakfast.setOnClickListener(this);
        lunch.setOnClickListener(this);
        snacks.setOnClickListener(this);
        dinner.setOnClickListener(this);

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Home");
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.breakfast:
                i = new Intent(getContext(), FoodmenuActivity.class);
                i.putExtra("type", "breakfast");
                startActivity(i);
                break;
            case R.id.lunch:
                i = new Intent(getContext(), FoodmenuActivity.class);
                i.putExtra("type", "lunch");
                startActivity(i);
                break;
            case R.id.snacks:
                i = new Intent(getContext(), FoodmenuActivity.class);
                i.putExtra("type", "snacks");
                startActivity(i);
                break;
            case R.id.dinner:
                i = new Intent(getContext(), FoodmenuActivity.class);
                i.putExtra("type", "dinner");
                startActivity(i);
                break;
        }
    }
}
