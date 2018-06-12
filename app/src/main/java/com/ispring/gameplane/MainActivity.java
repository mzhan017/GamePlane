package com.ispring.gameplane;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.text.format.DateFormat;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ispring.gameplane.user.User;
import com.ispring.gameplane.user.UserAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.litepal.crud.DataSupport;

import java.util.Random;


public class MainActivity extends Activity implements Button.OnClickListener {
    private List<User> userList = new ArrayList<>();
    private User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            userList = DataSupport.findAll(User.class);
            Toast.makeText(this,"user numbers: "+userList.size(),Toast.LENGTH_LONG).show();
            if(userList.size()>0) {
                UserAdapter adapter = new UserAdapter(MainActivity.this, R.layout.user_item, userList);
                ListView listView = (ListView) findViewById(R.id.list_score);
                if(listView!=null) {
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                        {
                            currentUser = userList.get(position);
                            startGame();
                            //Toast.makeText(MainActivity.this, currentUser.getId()+"",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }catch (Exception e)
        {
            Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
         if(viewId == R.id.btnGame){
            startGame();
        }
        else if(viewId == R.id.addButton)
        {
            try {
                Random rand = new Random();
                EditText a = (EditText) findViewById(R.id.editTextUserName);
                String userName = a.getText().toString();
                if (userName.length() == 0) {
                    userName = "temp" + rand.nextInt(100);
                }
                User user = new User();
                user.setUserName(userName);
                user.setScore(0);
                Toast.makeText(MainActivity.this, userName, Toast.LENGTH_LONG);
                DateFormat df = new DateFormat();
                Calendar calendar = Calendar.getInstance();
                df.format("yyyy-MM-dd", calendar);
                user.setDate(df.format("yyyy-MM-dd", calendar).toString());
                //Toast.makeText(MainActivity.this,df.format("yyyy-MM-dd", calendar).toString(),Toast.LENGTH_LONG).show();
                user.save();

                userList = DataSupport.findAll(User.class);
                if(userList.size()>0) {
                    UserAdapter adapter = new UserAdapter(MainActivity.this, R.layout.user_item, userList);
                    ListView listView = (ListView) findViewById(R.id.list_score);
                    if(listView!=null) {
                        listView.setAdapter(adapter);
                    }
                }
            }catch (Exception e)
            {
                Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }

    public void startGame(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}