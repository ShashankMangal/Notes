package com.sharkBytesLab.shortNotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    //CODE STARTS HERE

    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    SharedPreferences sharedPreferences ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);

        //CODE STARTS HERE
        ListView listView = findViewById(R.id.listView);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes",null);
        if(set == null){
        notes.add("Example Note");}else{
            notes = new ArrayList(set);
        }
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,notes);
        listView.setAdapter(arrayAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),NoteEditorActivity.class);
                intent.putExtra("noteId",i);
                startActivity(intent);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long l) {

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                     notes.remove(i);
                                     arrayAdapter.notifyDataSetChanged();

                                HashSet<String> set = new HashSet<>(MainActivity.notes);
                                sharedPreferences.edit().putStringSet("notes",set).apply();
                            }
                        }).setNegativeButton("No",null).show();


                return true;
            }
        });



    }

    //MENU

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        switch(item.getItemId())
        {
            case R.id.add_note:{
                Intent intent = new Intent(getApplicationContext(),NoteEditorActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.share:
            {
                String shareBody = "Hey, I'm using Short Notes for creating my notes. Install using this link below." + " \n" +
                        "Download from Play Store\n" + "https://play.google.com/store/apps/details?id=" + getPackageName();

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(intent);
            }
            break;

            case R.id.review:
            {
                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);

                try {
                    startActivity(i);
                } catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "Error :" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            break;

            case R.id.terms:
            {
                Intent intent = new Intent(getApplicationContext(),TermsActivity.class);
                startActivity(intent);
            }
            break;

            case R.id.policy:
            {
                Intent intent = new Intent(getApplicationContext(),PolicyActivity.class);
                startActivity(intent);
            }
            break;

        }
        return true;
    }
}