package org.techtown.provider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPerson();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryPerson();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePerson();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePerson();
            }
        });
    }
    public void println(String data) {
        textView.append(data + "\n");
    }

    public void insertPerson() {
        println("insertPerson 호출됨");

        String uriStr = "content://org.techtown.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriStr);

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        String[] columns = cursor.getColumnNames();
        for (int i = 0; i < columns.length; i++) {
            println("#" + i + " : " + columns[i]);
        }

        ContentValues values = new ContentValues();
        values.put("name", "john");
        values.put("age", 20);
        values.put("mobile", "010-1000-1000");

        uri = getContentResolver().insert(uri, values);
        println("insert result : " + uri.toString() );
    }

    public void queryPerson() {
        try {
            String uriStr = "content://org.techtown.provider/person";
            Uri uri = new Uri.Builder().build().parse(uriStr);

            String[] columns = new String[] {"name", "age", "mobile"};
            Cursor cursor = getContentResolver().query(uri, columns, null, null, "name ASC");
            println("query result : " + cursor.getCount());

            int index = 0;
            while(cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(columns[0]));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow(columns[1]));
                String mobile = cursor.getString(cursor.getColumnIndexOrThrow(columns[2]));

                println("#" + index + " -> " + name + ", " + age + ", " + mobile);
                index += 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePerson() {
        String uriStr = "content://org.techtown.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriStr);

        String selection = "mobile = ?";
        String[] selectionArgs = new String[] {"010-1000-1000"};
        ContentValues updateValues = new ContentValues();
        updateValues.put("mobile", "010-2000-2000");
        int count = getContentResolver().update(uri, updateValues, selection, selectionArgs);
        println("update result : " + count);
    }

    public void deletePerson() {
        String uriStr = "content://org.techtown.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriStr);

        String selection = "name = ?";
        String[] selectionArgs = new String[] {"john"};

        int count = getContentResolver().delete(uri, selection, selectionArgs);
        println("delete result : " + count);
    }
}