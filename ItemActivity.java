package com.example.restaurantapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.restaurantapp.categories.OfferFragment;

public class ItemActivity extends AppCompatActivity {
    SqliteHelper sql;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        sql=new SqliteHelper(this);

        TextView nameText=findViewById(R.id.nameID);
        ImageView imageView=findViewById(R.id.imageID);
        TextView priceText=findViewById(R.id.priceID);
        TextView descriptionText =findViewById(R.id.descriptionID);
        Button increment=findViewById(R.id.increment);
        Button decrement=findViewById(R.id.decrement);
        TextView numberOfMeals=findViewById(R.id.numberOfMeals);
        Button add=findViewById(R.id.addToCart);
        String description=" ";
        count=Integer.parseInt(numberOfMeals.getText().toString());

        Bundle extras=getIntent().getExtras();
        if(extras!=null){
            nameText.setText(extras.getString("name"));
            imageView.setImageResource(extras.getInt("image"));
            priceText.setText(String.valueOf(extras.getInt("price")));
            description=sql.descriptionOfMeals(extras.getString("name"));
            descriptionText.setText(description);
        }

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                numberOfMeals.setText(String.valueOf(count));
            }
        });
        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count<=0)
                    count=0;
                else
                    count--;
                numberOfMeals.setText(String.valueOf(count));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (count !=0 ){
                    Toast.makeText(ItemActivity.this, " Your Order In Preparation ", Toast.LENGTH_LONG).show();
                    //Intent intent = new Intent(getApplicationContext(), OfferFragment.class);
                    //startActivity(intent);

                }
                else {
                    Toast.makeText(ItemActivity.this, " Choice number of meals ", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}
