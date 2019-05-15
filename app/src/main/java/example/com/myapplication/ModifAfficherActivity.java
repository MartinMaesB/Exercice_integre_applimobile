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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ModifAfficherActivity extends AppCompatActivity {

    private static final String DB_NAME = "Project.db";
    private static final String TABLE_NAME = "Projet";
    private SQLiteDatabase db;
    TextView affiche;
    EditText numéro;
    Button btn;
    ArrayList<String> nomsActivite = new ArrayList<String> ();
    ArrayList<String> dates = new ArrayList<String>();
    ArrayList<Integer> nbr = new ArrayList<Integer>();
    ArrayList<Integer> id= new ArrayList<Integer>();
    StringBuilder sb = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_afficher);

        btn=(Button)findViewById(R.id.bConfir);
        affiche=(TextView)findViewById(R.id.affiche);
        numéro=(EditText)findViewById(R.id.etNum);

        db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, date TEXT, nombreH NUMBER, activite TEXT);");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.contains(Integer.valueOf(numéro.getText().toString()))){
                    //Integer.valueOf(numéro.getText().toString())<=id.size()&&Integer.valueOf(numéro.getText().toString())>0
                    Intent intentt = new Intent(ModifAfficherActivity.this, ModifierActivity.class);

                    intentt.putExtra("ID",numéro.getText().toString());

                    startActivity(intentt);}
                else  Toast.makeText(getApplicationContext(),"Veuillez rentrer un numéro valide",Toast.LENGTH_LONG).show();
            }
        });

        Cursor c = db.query(TABLE_NAME, new String[] { "id","date", "nombreH", "activite" }, null, null, null, null, "id");

        if (c.getCount() == 0) {
            c.close();
            Toast.makeText(getApplicationContext(),"Il n'y a aucun élément dans la base de données",Toast.LENGTH_LONG).show();
            Intent intentt = new Intent(ModifAfficherActivity.this, MainActivity.class);
            startActivity(intentt);
        }
        else{
            while (c.moveToNext()) {
                id.add(c.getInt(0));
                dates.add(c.getString(1));
                nbr.add(c.getInt(2));
                nomsActivite.add(c.getString(3));
            }


            for(int i=0;i<nomsActivite.size();i++){
                sb.append("Numéro "+id.get(i)+" :      Nom: "+ nomsActivite.get(i)+",   heures: "+nbr.get(i)+",   Date: "+dates.get(i));
                sb.append("\n");
            }
            affiche.setText(sb.toString());

        db.close();



    }     c.close();
}
}
