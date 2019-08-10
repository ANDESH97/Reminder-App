package com.brownie.mynotesapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.brownie.mynotesapp.R;
import com.brownie.mynotesapp.adapters.MyListViewAdapter;
import com.brownie.mynotesapp.model.Reminder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private ListView listView;

    private ArrayList<Reminder> reminderList;

    private MyListViewAdapter myListViewAdapter;

    private EditText addReminder;

    private ImageButton btnAddReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init()
    {
        addReminder = findViewById(R.id.main_et_note);
        btnAddReminder = findViewById(R.id.btn_add_reminder);
        btnAddReminder.setOnClickListener(this);

        reminderList = new ArrayList<>();
        reminderList.add(new Reminder("This is the first reminder","12:00 pm"));

        myListViewAdapter = new MyListViewAdapter(this, R.layout.list_view_cell, reminderList);

        listView = findViewById(R.id.main_list_view);
        listView.setAdapter(myListViewAdapter);

        setUpListItemClickListener();
    }

    private void setUpListItemClickListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), "Reminder " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btn_add_reminder:
            {
                if(!addReminder.getText().toString().isEmpty())
                {
                    Reminder newReminder = new Reminder();
                    newReminder.setrDescription(addReminder.getText().toString());
                    newReminder.setrTime("Time not set");

                    reminderList.add(newReminder);

                    myListViewAdapter.notifyDataSetChanged();
                }
            }
        }

    }
}
