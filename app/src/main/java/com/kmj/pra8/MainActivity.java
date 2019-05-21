package com.kmj.pra8;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TabHost th;
    EditText c1_et2,c1_et3,c2_et1;
    Button c1_btn1,c1_btn2,c2_btn1;
    TextView c2_tv1,c1_tv1;
    AlertDialog song;
    int index;
    String[] items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c1_btn1=findViewById(R.id.content_input_btn);
        c1_btn2=findViewById(R.id.content_input_btn2);
        c1_tv1=findViewById(R.id.content_input_tv1);
        c1_et2=findViewById(R.id.content_input_et2);
        c1_et3=findViewById(R.id.content_input_et3);
        c2_et1=findViewById(R.id.content_search_et1);
        c2_btn1=findViewById(R.id.content_search_btn1);
        c2_tv1=findViewById(R.id.content_search_tv1);
        th=findViewById(R.id.main_tabHost);
        th.setup();
        TabHost.TabSpec ts1=th.newTabSpec("Tab1");
        ts1.setIndicator("입력");
        ts1.setContent(R.id.Content1);
        th.addTab(ts1);

        TabHost.TabSpec ts2=th.newTabSpec("Tab2");
        ts2.setIndicator("조회");
        ts2.setContent(R.id.content2);
        th.addTab(ts2);

        th.setCurrentTab(0);

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("노래 장르 선택하세요 ^~^");
        builder.setSingleChoiceItems(R.array.song_kind, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                index=which;
            }
        });
        items=getResources().getStringArray(R.array.song_kind);
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (index){

                    case 0:
                        c1_tv1.setText(items[0]);
                        break;
                    case 1:
                        c1_tv1.setText(items[1]);
                        break;
                    case 2:
                        c1_tv1.setText(items[2]);
                        break;
                    case 3:
                        c1_tv1.setText(items[3]);
                        break;
                    case 4:
                        c1_tv1.setText(items[4]);
                        break;
                }
            }
        });
        song=builder.create();
        c1_tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                song.show();
            }
        });

        c1_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper helper=new Helper(MainActivity.this);
                SQLiteDatabase db=helper.getWritableDatabase();

                String kind=c1_tv1.getText().toString();
                String name=c1_et2.getText().toString();
                String lyrics=c1_et3.getText().toString();

                String insertSQL="INSERT INTO songs VALUES('"+kind+"','"+name+"','"+lyrics+"')";
               try{ db.execSQL(insertSQL);
                   Toast.makeText(MainActivity.this, "성공함!", Toast.LENGTH_SHORT).show();
               }
               catch (Exception e){
                   Toast.makeText(MainActivity.this, "실패함!", Toast.LENGTH_SHORT).show();
               }
               db.close();
            }
        });
        c2_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper helper=new Helper(MainActivity.this);
                SQLiteDatabase db=helper.getReadableDatabase();
                String name=c2_et1.getText().toString();

                String insertSQL="SELECT * FROM songs WHERE name='"+name+"'";
                Cursor s= db.rawQuery(insertSQL,null);
                s.moveToNext();
                c2_tv1.setText(s.getString(2));

                db.close();


            }
        });
    }
}
