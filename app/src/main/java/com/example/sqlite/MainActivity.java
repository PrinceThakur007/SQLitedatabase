package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {


    DataBaseHelper myDB;
    EditText editId, editName, editEmail, editAge;
    Button buttonAdd, buttonGetData, buttonUpdate, buttonDelete, buttonViewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDB = new DataBaseHelper(this);

        editId = findViewById(R.id.editText_id);
        editName = findViewById(R.id.editText_name);
        editEmail = findViewById(R.id.edit_email);
        editAge = findViewById(R.id.editage);


        buttonAdd = findViewById(R.id.add_data_button);
        buttonGetData = findViewById(R.id.get_data_button);
        buttonUpdate = findViewById(R.id.update_data_button);
        buttonDelete = findViewById(R.id.delete_data_button);
        buttonViewAll = findViewById(R.id.view_all_data_button);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = myDB.insertData(editName.getText().toString(), editEmail.getText().toString(), editAge.getText().toString());

                if (result) {
                    Toast.makeText(MainActivity.this, "Data is inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Data is not inserted", Toast.LENGTH_SHORT).show();

                }
            }


        });

        buttonGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = myDB.getData(editId.getText().toString());

                String data = null;
                if (c.moveToNext()) {
                    data = c.getString(0) + "\n" + c.getString(1) + "\n" + c.getString(2) + "\n" + c.getString(3);
                }
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }
        });


        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor c = myDB.viewAllData();
                StringBuffer data = new StringBuffer();
                if (c.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();

                } else {
                    while (c.moveToNext()) {
                        data.append(c.getString(0) + "\n" + c.getString(1) + "\n" + c.getString(2) + "\n" + c.getString(3)+"\n\n");

                    }
                    Toast.makeText(MainActivity.this, data.toString(), Toast.LENGTH_LONG).show();
                }
            }

        });


    }


}
