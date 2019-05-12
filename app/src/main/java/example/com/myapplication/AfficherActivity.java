package example.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AfficherActivity extends AppCompatActivity {


    private static final String DB_NAME = "Project.db";
    private static final String TABLE_NAME = "Projet";
    private SQLiteDatabase db;
    Button back;
    Cursor c = null;
    TextView activite;
    TextView nmbre;
    ArrayList<String> nomsActivite = new ArrayList<String> ();
    ArrayList<String> dates = new ArrayList<String>();
    ArrayList<Integer> nbr = new ArrayList<Integer>();
    int nombreDheures = 0;
    StringBuilder sb = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher);
        db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+ " (id INTEGER PRIMARY KEY AUTOINCREMENT," +" date TEXT, nombreH NUMBER, activite TEXT);");

        activite=(TextView)findViewById(R.id.etActivite);
        nmbre=(TextView)findViewById(R.id.tvNumber);

        back=(Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentt = new Intent(AfficherActivity.this, MainActivity.class);
                startActivity(intentt);
            }
        });
        Cursor c = db.query(TABLE_NAME, new String[] { "date", "nombreH",
                        "activite" }, null, null, null,
                null, "date");
        if (c.getCount() == 0) {
            c.close();
            Toast.makeText(getApplicationContext(),"Il n'y a aucun élément dans la base de données",Toast.LENGTH_LONG).show();
        }
        else{


            while (c.moveToNext()) {
                dates.add(c.getString(0));
                nbr.add(c.getInt(1));
                nomsActivite.add(c.getString(2));
            }


            for(int i=0;i<nomsActivite.size();i++){
                sb.append("Activité " + (i+1) + " : " + nomsActivite.get(i)+" ");
                sb.append("\n");
                nombreDheures=nombreDheures+nbr.get(i);
            }

            activite.setText(sb.toString());
            nmbre.setText(Integer.toString(nombreDheures));
            c.close();
        }
        db.close();
    }
}
