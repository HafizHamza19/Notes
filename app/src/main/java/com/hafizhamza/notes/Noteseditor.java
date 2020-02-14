package com.hafizhamza.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.notes.R;

import java.util.HashMap;
import java.util.HashSet;

public class Noteseditor extends AppCompatActivity {
EditText editText;
    int noteId;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noteseditor);
        editText=(EditText)findViewById(R.id.editor);
        Intent intent=getIntent();
        String newnotes=intent.getStringExtra("new");
        noteId=intent.getIntExtra("notesId",-1);
if (noteId!=-1)
{
    editText.setText(MainActivity.arrayList.get(noteId).toString());
}else {
      MainActivity.arrayList.add("");
      noteId=MainActivity.arrayList.size()-1;
}
    editText.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
MainActivity.arrayList.set(noteId,String.valueOf(charSequence));
MainActivity.adapter.notifyDataSetChanged();
            SharedPreferences sharedPreferences=getApplicationContext().getSharedPreferences("com.hafizhamza.notes", Context.MODE_PRIVATE);
            HashSet<String> set=new HashSet<>(MainActivity.arrayList);
            sharedPreferences.edit().putStringSet("notes",set).apply();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
