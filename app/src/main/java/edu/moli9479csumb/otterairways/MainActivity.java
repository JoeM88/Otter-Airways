package edu.moli9479csumb.otterairways;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create a database for the app.
        MySQLiteHelper db = new MySQLiteHelper(this);

        //Adding the users to the database
        db.addUser(new Users("A@lice5", "@cSit100"));
        db.addUser(new Users("$BriAn7", "123aBc##"));
        db.addUser(new Users("!chriS12!", "CHrIS12!!"));

        //Read the users information from the database
        //and store it into an arraylist.
        ArrayList<Users> list = db.getAllUsers();

        init();
    }

    public Button createAccountButton;
    //Method for the create Account button
    public void  init(){
        createAccountButton = (Button) findViewById(R.id.signupButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent1);

            }
        });
    }


}
