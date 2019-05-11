package example.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AfficherActivity extends AppCompatActivity {


    private static final String DB_NAME = "Project.db";
    private static final String TABLE_NAME = "Projet";
    private SQLiteDatabase db;
    Button back;
    Cursor c = null;
    EditText activite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher);
        db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+ " (id INTEGER PRIMARY KEY AUTOINCREMENT," +" date TEXT, nombreH NUMBER, activite TEXT);");

        activite=(EditText)findViewById(R.id.etActivite);
        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentt = new Intent(AfficherActivity.this, MainActivity.class);
                startActivity(intentt);
            }
        });

        c = db.rawQuery(" select Projet.nombreH FROM Projet",null);
        if(c!=null){
            activite.setText(c.getString(2));
            Toast.makeText(getApplicationContext(),c.toString(),Toast.LENGTH_LONG).show();
        }
        db.close();




    }
}
