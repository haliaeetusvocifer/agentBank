package com.lotusbeta.agentbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Withdrawal extends AppCompatActivity {
TextView getStuff;
    @Override 
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);



       /* String givenLga = "LGA: "+bundle.getString("Lga");
        String givenTitle = "Title: "+bundle.getString("Title");
        String givenFirstName = "FirstName: "+bundle.getString("FirstName");
        String givenLastName = "Lastname: "+bundle.getString("Surname");*/


        /*getStuff = (TextView)findViewById(R.id.getView);*/
        GetMethodEx test = new GetMethodEx();
        String returned;
        try{

          returned  = test.getInternetData();
        } catch(Exception e){

            e.printStackTrace();
        }
        

        Button menuButton = (Button)findViewById(R.id.button7);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Withdrawal.this, menu.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_withdrawal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
