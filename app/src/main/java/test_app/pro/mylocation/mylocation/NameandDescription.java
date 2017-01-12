package test_app.pro.mylocation.mylocation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by dell on 8/27/2016.
 */
public class NameandDescription extends Activity implements View.OnClickListener{

    EditText etlocationname,etdescription;
    Button Btsubmit;
    String p1,p2;
    private UserListDbHelper uldh;
    String data="",data1="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.nameanddescription);

        etlocationname=(EditText)findViewById(R.id.et5);
        etdescription=(EditText)findViewById(R.id.et6);
        Btsubmit=(Button)findViewById(R.id.btintentAct);

        savedInstanceState=getIntent().getExtras();
        data=savedInstanceState.getString("my_name");
        data1 =savedInstanceState.getString("my_name1");

        Log.e("Lat_Data",data);
        Log.e("Log_Data",data1);

        get_sql_create_tab();
        Btsubmit.setOnClickListener(this);

    }

    public void get_sql_create_tab(){
        uldh = new UserListDbHelper(getApplicationContext());
        uldh.createTable();

    }

    @Override
    public void onClick(View view) {

        p1=etlocationname.getText().toString();
        p2=etdescription.getText().toString();

        if(p1.isEmpty() && p2.isEmpty())
        {
            etlocationname.setError("please enter some value");
            etdescription.setError("Please enter seome description");

        }

        else
        {
            etlocationname.setText(p1);
            etdescription.setText(p2);

            uldh.insertData(p1,p2,data,data1);
          /*  AlertDialog alertDialog = null;
            alertDialog = new AlertDialog.Builder(NameandDescription.this).create();
            alertDialog.setMessage("Your response has been saved successfully.");
            alertDialog.show();*/

            Intent ab=new Intent(getApplicationContext(),Old_MainActivity.class);
            startActivity(ab);
            finish();

        }



    }
}
