package test_app.pro.mylocation.mylocation;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends Activity {
	
	//SQLiteHelper SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;
    UserListDbHelper uldh;

    ArrayList<String> AR_Loc_ID = new ArrayList<String>();
    ArrayList<String> AR_Loc_Name = new ArrayList<String>();
    ArrayList<String> AR_Loc_Lat = new ArrayList<String>();
    ArrayList<String> AR_Loc_Log = new ArrayList<String>();
    ArrayList<String> AR_Loc_Desc = new ArrayList<String>();
    ListView LISTVIEW;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

//        SQLITEHELPER = new SQLiteHelper(this);
        uldh = new UserListDbHelper(getApplicationContext());


    }

    @Override
    protected void onResume() {

    	ShowData() ;
    	
        super.onResume();
    }

    private void ShowData() {

        Cursor cursor = uldh.getSQLiteDBdata();

        if(cursor != null) {

            AR_Loc_ID.clear();
            AR_Loc_Name.clear();
            AR_Loc_Lat.clear();
            AR_Loc_Log.clear();
            AR_Loc_Desc.clear();

            if (cursor.moveToFirst()) {
                do {
                    AR_Loc_ID.add(cursor.getString(cursor.getColumnIndex(uldh.ID)));

                    AR_Loc_Name.add(cursor.getString(cursor.getColumnIndex(uldh.LOC_Name)));

                    AR_Loc_Lat.add(cursor.getString(cursor.getColumnIndex(uldh.LOC_Latitude)));

                    AR_Loc_Log.add(cursor.getString(cursor.getColumnIndex(uldh.LOC_Longitude)));

                    AR_Loc_Desc.add(cursor.getString(cursor.getColumnIndex(uldh.LOC_Description)));


                } while (cursor.moveToNext());
            }

            ListAdapter = new SQLiteListAdapter(getApplicationContext(),

                    AR_Loc_ID,
                    AR_Loc_Name,
                    AR_Loc_Lat,
                    AR_Loc_Log,
                    AR_Loc_Desc

            );

            LISTVIEW.setAdapter(ListAdapter);

            cursor.close();

        }else {
            //Toast.makeText(getApplicationContext(), "DB does not exsists", Toast.LENGTH_SHORT).show();
        }
    }}