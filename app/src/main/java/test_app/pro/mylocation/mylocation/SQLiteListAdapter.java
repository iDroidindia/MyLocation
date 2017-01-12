package test_app.pro.mylocation.mylocation;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SQLiteListAdapter extends BaseAdapter {
	
    Context context;
    ArrayList<String> Loc_ID;
    ArrayList<String> Loc_Name;
    ArrayList<String> Loc_Latitude;
    ArrayList<String> Loc_Lognitude ;
    ArrayList<String> Loc_Desc;

    public SQLiteListAdapter(
    		Context context2,
    		ArrayList<String> L_id,
    		ArrayList<String> L_Name,
    		ArrayList<String> L_Lat,
    		ArrayList<String> L_Log,
            ArrayList<String> L_Desc
    		) 
    {
        	
    	this.context = context2;
        this.Loc_ID = L_id;
        this.Loc_Name = L_Name;
        this.Loc_Latitude = L_Lat;
        this.Loc_Lognitude = L_Log ;
        this.Loc_Desc = L_Desc ;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return Loc_ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {
    	
        Holder holder;
        
        LayoutInflater layoutInflater;
        
        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listviewdatalayout, null);
            
            holder = new Holder();

            holder.TV_Loc_ID = (TextView) child.findViewById(R.id.textViewID);
            holder.TV_Loc_Desc = (TextView) child.findViewById(R.id.textViewDesc);
            holder.TV_Loc_Name = (TextView) child.findViewById(R.id.textViewNAME);
            holder.TV_Loc_Lat = (TextView) child.findViewById(R.id.textViewPHONE_NUMBER);
            holder.TV_Loc_Log = (TextView) child.findViewById(R.id.textViewSUBJECT);
            
            child.setTag(holder);
            
        } else {
        	
        	holder = (Holder) child.getTag();
        }

        holder.TV_Loc_ID.setText(Loc_ID.get(position));
        holder.TV_Loc_Name.setText(Loc_Name.get(position));
        holder.TV_Loc_Lat.setText(Loc_Latitude.get(position));
        holder.TV_Loc_Log.setText(Loc_Lognitude.get(position));
        holder.TV_Loc_Desc.setText(Loc_Desc.get(position));

        return child;
    }

    public class Holder {
        TextView TV_Loc_ID;
        TextView TV_Loc_Name;
        TextView TV_Loc_Lat;
        TextView TV_Loc_Log;
        TextView TV_Loc_Desc;
    }

}