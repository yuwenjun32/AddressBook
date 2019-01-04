package com.example.lovem.addressbook;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddRelationActivity extends AppCompatActivity {
    private EditText addName,addTel;
    private Spinner addGroup;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_relation);
        addName=(EditText)findViewById(R.id.addName);
        addTel=(EditText)findViewById(R.id.addTel);
        addGroup=(Spinner)findViewById(R.id.addGroup);

        String s[]={"家人","同学","朋友","同事","领导"};
        spinner =(Spinner) findViewById(R.id.addGroup);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,android.R.id.text1,s);
        spinner.setAdapter(adapter);

    }
    public void save(View view){
        final ContentValues values=new ContentValues();
        values.put("name",addName.getText().toString());
        values.put("tel",addTel.getText().toString());
        values.put("groupName",addGroup.getSelectedItem().toString());
        final DatabaseHelper dbHelper=new DatabaseHelper(getApplicationContext());
        final AlertDialog.Builder adBuilder=new AlertDialog.Builder(this);
        adBuilder.setMessage("确认保存记录吗？").setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbHelper.insert(values);
                Intent intent=getIntent();
                setResult(0x111,intent);
                AddRelationActivity.this.finish();
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog=adBuilder.create();
        alertDialog.show();
    }
}
