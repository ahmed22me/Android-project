package com.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import com.example.restaurantapp.home.CartFragment;
import com.example.restaurantapp.home.FavouriteFragment;
import com.example.restaurantapp.home.HomeFragment;
import com.example.restaurantapp.home.ProfileFragment;
import com.example.restaurantapp.home.ViewPageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    SqliteHelper sql;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         sql=new SqliteHelper(this);



        viewPager=findViewById(R.id.mainViewPagerID);
        bottomNavigationView=findViewById(R.id.bottom_navigation);

        ViewPageAdapter adapter=new ViewPageAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.cart).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.profile).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.favourite).setChecked(true);
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.cart:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.profile:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.favourite:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });

        offer();

        pizza();
        extras();
        dessert();
        SoftDrinks();

        







    }

    private void SoftDrinks() {
        sql.addMeal(" Pepsi ","SoftDrinks", " pepsi 1 ltr ",30, R.drawable.dr1);


        sql.addMeal(" 7 UP ","SoftDrinks"," 7UP 1 ltr ",30, R.drawable.dr2);


        sql.addMeal(" Diet Pepsi ","SoftDrinks"," diet pepsi 1 ltr ",30, R.drawable.dr3);


        sql.addMeal(" Mirinda ","SoftDrinks"," mirinda 1 ltr ",30, R.drawable.dr4);


        sql.addMeal(" Mineral Water ","SoftDrinks"," Aquafina ",20, R.drawable.dr5);






    }

    private void dessert() {
        sql.addMeal(" Chocolate & Marshmallow ","Desserts", " Chocolate hazelnut with small pieces marshmallow ", 60  , R.drawable.d1);


        sql.addMeal(" Chocolate Cake ","Desserts"," A rich, soft, dark chocolate cake. A must for chocolate lovers ", 60 , R.drawable.d2);


        sql.addMeal(" Cheesecake ","Desserts"," Creamy cheesecake topped with strawberry sauce ", 60 , R.drawable.d3);





    }

    private void extras() {

       sql.addMeal(" Cheese Fondue Sauce ", "Extras","  ", 100, R.drawable.x1);


      sql.addMeal(" Honey Mustard Sauce ", "","  ", 100, R.drawable.x2);


        sql.addMeal(" Thai Chili Sauce ", "Extras","  ", 160, R.drawable.x3);


        sql.addMeal(" Ranch Sauce ","Extras", "  ", 160, R.drawable.x4);


        sql.addMeal(" Spicy Kriss Cut Fries ", "Extras"," Oven baked kriss cut fries with Tabasco sprinkles ", 250, R.drawable.x5);


        sql.addMeal(" Fire Wings ", "Extras"," Served with our special BBQ sauce (Spicy Hot) ", 100, R.drawable.x6);

        sql.addMeal(" Coleslaw ", "Extras"," ", 20, R.drawable.s1);


        sql.addMeal(" Green Salad ", "Extras","  ", 20, R.drawable.s2);


        sql.addMeal(" Garlic sauce ", "Extras","  ", 20, R.drawable.s3);


        sql.addMeal(" Thousand island souce ", "Extras","  ", 20, R.drawable.s4);


        sql.addMeal(" Mix Beans (Green n white) ", "Extras","  ", 20, R.drawable.s5);


       sql.addMeal(" Mix Vegetable with mayonnaise ","Extras", "  ", 20, R.drawable.s6);


        sql.addMeal(" Potato with mayonnaise ", "Extras","  ", 20, R.drawable.s7);


        sql.addMeal(" White cheese ", "Extras","  ",  20 , R.drawable.s8);






    }

    private void pizza() {

        sql.addMeal(" BBQ Chicken Ranch ", "pizza"," Ranch base sauce with grilled chicken pieces, topped with mushroom slices, onions, mixed with Mozzarella.  ",60  , R.drawable.p1);

        sql.addMeal("  Dynamite Chicken Ranch ","pizza"," Ranch base sauce with grilled chicken pieces, mushroom slices, mozzarella cheese, diced tomatoes and crushed garlic", 75  , R.drawable.p2);

        sql.addMeal(" Margherita ","pizza"," Go Back to where it all began with the classic cheese and\n" +
                "tomato base less\n ",80  , R.drawable.p3);

        sql.addMeal("Super Supreme ","pizza"," Our famous combination of Beef Pepperoni, juicy Beef, Mushrooms, Green Peppers, Onions, Black Olives and melting mozzarella cheese. ", 65, R.drawable.p4);

        sql.addMeal(" Classic Pepperoni ","pizza"," One of our all time specialties. A meaty feast of Pepperoni, Mushroom, Black Olives, mozzarella cheese and tomato sauce ", 150, R.drawable.p5);

        sql.addMeal(" Cheese Lovers ","pizza"," A special triple blend of cheese  ",100 , R.drawable.p6);

        sql.addMeal(" Chicken Supreme ","pizza"," The ultimate mix of Chicken together with Mushrooms, Red Onions, Green Peppers, fresh Tomatoes, Olives and melting mozzarella cheese\n ", 125 , R.drawable.p7);

        sql.addMeal(" Vegetarian ","pizza"," Mushrooms, Green peppers,\n" +
                "Fresh Tomatoes, Onions, and Black Olives Loaded on a tomato base and topped with melting mozzarella cheese \n ", 165 , R.drawable.p8);

        sql.addMeal(" Spicy Chicken Ranch ","pizza"," Ranch base sauce with grilled chicken pieces, topped with mushroom slices, mixed with Mozzarella ", 140 , R.drawable.p9);

        sql.addMeal(" Prawn ","pizza"," A mouth-watering combination of Prawns, Black Olives, Green Peppers and a touch of Garlic ",180 , R.drawable.p10);


    }

    private void offer() {

        sql.addMeal(" control offer ","Offer" ," 4pieces of chicks + 4pieces of chicks strips + 2small rice + 2medium choleslaw + 2coca_cola + 2breads ",160  , R.drawable.a1);

        sql.addMeal(" control offer 2 ","Offer"," 6pieces of chicks + 2small rice + 2medium choleslaw + 2coca_cola + 2breads ",160, R.drawable.a2);

        sql.addMeal(" control offer 3 ","Offer"," 2single sandwiches + 2medium fries + 2medium choleslaw + 2coca_cola ", 160 , R.drawable.a3);

        sql.addMeal(" control offer 4 ","Offer"," 2rap sandwiches + 2rice + 2medium fries + 2choleslaw + 2coca_cola ", 160 , R.drawable.a4);

        sql.addMeal(" lammt elsaada ","Offer"," 10 pieces of chicks + 1big choleslaw + 3breads + 1coca_cola ",250 , R.drawable.a5);

        sql.addMeal(" king of happiness ","Offer"," 1single sandwiches + 1piece of checks + 1medium choleslaw + 1medium fries + 1coca_cola ", 100 , R.drawable.a6);

        sql.addMeal(" kings of happiness ","Offer"," 4pieces of chicks + 1medium choleslaw + 1small rice + 1coca_cola + 1bread ", 125  , R.drawable.a7);

        sql.addMeal(" lammt elsohap ","Offer"," 12pieces of chicks + 2rice + 1coca_cola + 1big choleslaw + 4breads ", 365  , R.drawable.a8);

        sql.addMeal(" rock of happiness ","Offer"," 2sandwiches talka( beaf or chichen ) + 2choleslaw + 2can coca_cola ", 140  , R.drawable.a9);

        sql.addMeal(" doubled of happiness ","Offer"," 2sandwiches double chicken + 1medium fries + 2coca_cola ", 180  , R.drawable.a10);

        sql.addMeal(" single of happiness ","Offer"," 3pieces of chicks strips + 1big rice + 1sauce + 1cola ", 80 , R.drawable.a11);

        sql.addMeal(" super of happiness ","Offer"," 2pieces of chicks + 1rice + 1choleslaw + 1coca_cola ", 100  , R.drawable.a12);

        sql.addMeal(" rap of happiness ","Offer"," 4sandwiches rap chicken ", 100 , R.drawable.a13);

        sql.addMeal(" box of happiness 1 ","Offer"," 2single sandwiches(checken or burger) + 2sandwiches talka(checken or burger) + 2fries + 1cola ",215  ,R.drawable.a14);

        sql.addMeal(" box of happiness 2 ","Offer"," 2single sandwiches(checken or burger) + 4pieces of checks + 2fries + 1cola ",220  ,R.drawable.a15);

        sql.addMeal(" box of happiness 3 ","Offer"," 8pieces of checks + 2small rice + 1big choleslaw + 1cola + 4breads ", 235  ,R.drawable.a16);

        sql.addMeal(" box of happiness 4 ","Offer"," 6pieces of checks + 6pieces of strips checks + 2small rice + 1big choleslaw + 1cola + 4breads ",240  , R.drawable.a17);

        sql.addMeal(" doubled box of happiness ","Offer"," 2double sandwiches + 2sandwiches talka(beaf or checken) + 2fries + 1cola ", 250,R.drawable.a18);

        sql.addMeal(" single control box ","Offer"," 4single sandwiches + 2fries + 1cola ",260  , R.drawable.a19);

        sql.addMeal(" doubled control box ","Offer"," 4double sandwiches + 2fries + 1cola ", 340, R.drawable.a20);

    }


}