package com.example.lovem.addressbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class main_interface_Activity extends AppCompatActivity {
    private ListView listView;
    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_interface_);
        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null){
            actionBar.hide();
        }
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        listView = (ListView) findViewById(R.id.tv_interface_listView);
               getRelationFromDB();

    }

    private void getRelationFromDB() {
        final DatabaseHelper dbHelper = new DatabaseHelper(this);
        final Cursor cursor = dbHelper.query();
        final String[] from = { "name", "tel", "groupName"};
        int[] to = { R.id.name, R.id.tel, R.id.group};
        SimpleCursorAdapter scadapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.relationlist, cursor, from, to);
        listView.setAdapter(scadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final long temp=id;
                AlertDialog.Builder adBuilder=new AlertDialog.Builder(main_interface_Activity.this);
                adBuilder.setMessage("确认要删除记录吗？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.del((int)temp);
                        Cursor cursor=dbHelper.query();
                        String[] from={"name","tel","groupName"};
                        int[] to={R.id.name,R.id.tel,R.id.group};
                        SimpleCursorAdapter scadapter=new SimpleCursorAdapter(getApplicationContext(),R.layout.relationlist,cursor,from,to);
                        main_interface_Activity.this.listView.setAdapter(scadapter);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alertDialog=adBuilder.create();
                alertDialog.show();
            }
        });
        dbHelper.close();
    }

    public void add(View view){
        Intent intent=new Intent(main_interface_Activity.this,AddRelationActivity.class);
        startActivityForResult(intent,0x111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0x111&&requestCode==0x111){
            getRelationFromDB();
        }
    }
}
