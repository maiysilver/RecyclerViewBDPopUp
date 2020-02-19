package com.example.recyclerview;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private myDB myyDB;
    private DataBaseHelper database ;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private LinkedList<String> mWordList;
    private int index=15;
    private static String[] palabra=new String[100];
    private static String[] cant=new String[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final LinkedList<String> mWordList= new LinkedList<String>();
        crear();
        seleccionar();
        for(int i=0; i<palabra.length; i++){
            if(palabra[i]!=null){
                mWordList.add(palabra[i]+"  -----------------------------  "+cant[i]);
            }
        }
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // alertFormElements(getActivity());
                //mWordList.add("+ maincra "+index);
                //index++;
                //mAdapter.notifyItemInserted(index);
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mAdapter = new WordListAdapter(this, mWordList);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void alertFormElements(final Context context) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.dialogo,
                null, false);

        final EditText nameEditText = (EditText) formElementsView
                .findViewById(R.id.planeta);

        // the alert dialog
        new AlertDialog.Builder(MainActivity.this).setView(formElementsView)
                .setTitle("Form Elements")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @TargetApi(11)
                    public void onClick(DialogInterface dialog, int id) {
                        String toastString = "";
                        toastString += "Name is: " + nameEditText.getText()+ "!\n";
                        toastea(context,toastString);
                        dialog.cancel();
                    }
                }).show();
    }

    public void toastea(Context context, String tosta){
        String tostada= tosta;
        Toast toast1 =
                Toast.makeText(context, tostada, Toast.LENGTH_SHORT);
        toast1.show();
    }

    public void crear(){
        DataBaseHelper my= new DataBaseHelper(this);
        myyDB = new myDB(this);
        String[] planets={"Earth", "Jupiter", "Mars", "Mercury", "Moon", "Neptune", "Pluto", "Saturn", "Sun", "Uranus", "Venus"};
        String[] gravity={"9.8", "23.12", "3.71", "3.7", "1.6", "1", "0.6", "8.96", "275", "4.8151", "8.69", "8.87"};
        int i;
         for(i=0;i<11;i++){
            myyDB.createRecords(String.valueOf(i),planets[i],gravity[i]);
        }
    }

    public void seleccionar(){
        int i;
        Cursor cur= myyDB.selectRecords();
        i=0;
        palabra[i]=(cur.getString(1));
        cant[i]=(cur.getString(2));
        while(cur.moveToNext()){
            i++;
            //text.append(cur.getString(0)+"  "+cur.getString(1)+" \n");
            palabra[i]=(cur.getString(1));
            cant[i]=(cur.getString(2));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
