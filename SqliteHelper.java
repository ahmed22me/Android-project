package com.example.restaurantapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.restaurantapp.home.MealModel;
import com.example.restaurantapp.home.MealsAdapter;

import java.util.ArrayList;

public class SqliteHelper extends SQLiteOpenHelper {

    private static final String database_name = "appDB";
    SQLiteDatabase appDatabase;

    public SqliteHelper(Context context)
    {
        super(context,database_name,null,1);
    }
    @Override

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE users(email TEXT PRIMARY KEY,first_name TEXT,last_name TEXT,phone TEXT,password TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE meals(name TEXT PRIMARY KEY,category TEXT,description TEXT,price INTEGER,image INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE favourites(useremail TEXT,mealname TEXT,PRIMARY KEY(useremail,mealname),FOREIGN KEY(useremail) REFERENCES users(email) ON DELETE CASCADE,"+
                "FOREIGN KEY(mealname) REFERENCES meals(name) ON DELETE CASCADE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("drop table if exists meals");
        sqLiteDatabase.execSQL("drop table if exists favourites");
        onCreate(sqLiteDatabase);
    }

    public Boolean addUser(String email,String first_name,String last_name,String phone,String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("phone", phone);
        contentValues.put("password", password);


        long result = MyDB.insert("users",null, contentValues);
        if (result == -1){ return false;}
        else {
            return true;
        }
    }
    public void addMeal(String name,String category,String description,int price,int image)
    {
        ContentValues row = new ContentValues();
        row.put("name",name);
        row.put("category",category);
        row.put("description",description);
        row.put("price",price);
        row.put("image",image);
        appDatabase=getWritableDatabase();
        appDatabase.insert("meals",null,row);
        appDatabase.close();
    }
    public void addFavourite(String email,String name)
    {
        ContentValues row = new ContentValues();
        row.put("useremail",email);
        row.put("mealname",name);
        appDatabase=getWritableDatabase();
        appDatabase.insert("favourites",null,row);
        appDatabase.close();
    }

    public Cursor fetchAllMeals()
    {
        appDatabase=getReadableDatabase();
        String[] rowDetails ={"name","category","description","price","image"};
        Cursor cursor = appDatabase.query("meals",rowDetails,null,null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        appDatabase.close();
        return cursor;
    }
    public ArrayList<MealModel> displayData(String category){
        appDatabase=getReadableDatabase();
        Cursor cursor =appDatabase.rawQuery("select *from meals where category='"+category+"'",null);
        ArrayList<MealModel>modelArrayList=new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                modelArrayList.add(new MealModel(cursor.getString(0),cursor.getInt(3), cursor.getInt(4)));
            }while(cursor.moveToNext());
            cursor.close();
        }
        appDatabase.close();
        return modelArrayList;
    }
    public void deleteData(){
        appDatabase=getWritableDatabase();
        appDatabase.delete("meals","category",null);
        appDatabase.execSQL("delete from meals where category = Family");
        appDatabase.close();
    }
    public String descriptionOfMeals(String name){
        appDatabase=getReadableDatabase();
        Cursor cursor=appDatabase.rawQuery("select * from meals WHERE name ='"+name+"'",null);
        String desc=" ";
        if(cursor.moveToFirst()){
            desc = cursor.getString(2);
            cursor.close();
        }
        appDatabase.close();
        return desc;
    }

    public Boolean checkusername (String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery(" select * from users where email =? ", new String[] {username});
        if(cursor.getCount() > 0) {return true;}
        else{
            return false;}
    }

    public Boolean checkusernamepassword (String username , String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery(" select * from users where email =? and password = ? ", new String[] {username , password});
        if(cursor.getCount() > 0) {return true;}
        else{
            return false;}
    }


}