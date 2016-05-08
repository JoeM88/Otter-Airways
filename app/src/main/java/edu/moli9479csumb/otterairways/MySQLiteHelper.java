package edu.moli9479csumb.otterairways;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {

    //Database Name
    private static final String DATABASE_NAME = "Accounts";

    //table name
    private static final String TABLE_USERS = "users";

    //column names of the users table.
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    //Database version
    private static final int DATABASE_VERSION = 1;

    // Log TAG for debugging purpose
    private static final String TAG = "SQLiteAppLog";


    // Constructor
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL statement to create a table called "users".
        String CREATE_USERS_TABLE = "CREATE TABLE users ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "username TEXT,"+
                "password TEXT)";

        db.execSQL(CREATE_USERS_TABLE);
    }

    // onUpdate() is invoked when you upgrade the database scheme.
    // Don’t consider it seriously for the sample app.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older books table if existed
        db.execSQL("DROP TABLE IF EXISTS users");

        // create fresh books table
        this.onCreate(db);
    }


    public void addUser(Users user){
        Log.d(TAG, "addUser() - " + user.toString());
        //1.get reference to writable db
        SQLiteDatabase db = this.getWritableDatabase();

        // 2. create content values to add key "column"/value'
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUserName()); // get username
        values.put(KEY_PASSWORD, user.getPassword()); // get password.

        //3 insert
        db.insert(TABLE_USERS, null, values);

        //4 close the db.
        db.close();
    }

    //Get all the users from the database
    public ArrayList<Users> getAllUsers(){
        ArrayList<Users> usersArrayList = new ArrayList<Users>();

        //Building the query.
        String query = "SELECT * FROM" + TABLE_USERS;

        //2. get reference to writable DB
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        //3 go over each row, build user and add it to list.
        Users user = null;

        if(cursor.moveToFirst()){
            do{
                user = new Users();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setUserName(cursor.getString(1));
                user.setPassword(cursor.getString(2));
                usersArrayList.add(user);
            }while(cursor.moveToNext());
        }
        Log.d(TAG, "getAllUsers() - " + usersArrayList.toString());

        return usersArrayList;
    }


}
