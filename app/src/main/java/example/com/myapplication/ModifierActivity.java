package example.com.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ModifierActivity extends AppCompatActivity {

    private static final String DB_NAME = "Project.db";
    private static final String TABLE_NAME = "Projet";
    private SQLiteDatabase db;
    Button confirmer;
    Button modifDate,modifHeure,modifActivite;
    TextView etDate;
    TextView etHeure;
    TextView etActivite;
    String numéro;
    String date;
    int heure;
    String activité;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);

        confirmer=(Button)findViewById(R.id.bConfirmer);
        modifActivite=(Button)findViewById(R.id.btnActivite);
        modifDate=(Button)findViewById(R.id.btnDate);
        modifHeure=(Button)findViewById(R.id.btnHeure);
        etDate=(TextView)findViewById(R.id.etDate);
        etHeure=(TextView)findViewById(R.id.etHeure);
        etActivite=(TextView)findViewById(R.id.etActivite);

        Intent intent=getIntent();
        numéro = intent.getStringExtra("ID");

        db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+ " (id INTEGER PRIMARY KEY AUTOINCREMENT," +" date TEXT, nombreH NUMBER, activite TEXT);");


        Cursor c = db.query(TABLE_NAME, new String[] { "id","date", "nombreH",
                        "activite" }, "id LIKE \"" + Integer.valueOf(numéro) + "\"", null, null,
                null, "id");

        if(c.getCount() == 0){
            c.close();
            Toast.makeText(getApplicationContext(),"Erreur",Toast.LENGTH_LONG).show();

        }
        else{
            while(c.moveToNext()){
                date=c.getString(1);
                heure=c.getInt(2);
                activité=c.getString(3);
                }

                etDate.setText(date);
                etHeure.setText(Integer.toString(heure));
                etActivite.setText(activité);
            }



        confirmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(date!=etDate.getText().toString()){

                    ContentValues content = new ContentValues();
                    content.put("date",etDate.getText().toString());
                    db.update(TABLE_NAME, content, "id = " + Integer.valueOf(numéro), null);
                }

                if(heure!=Integer.valueOf(etHeure.getText().toString())){
                    ContentValues content = new ContentValues();
                    content.put("nombreH",etHeure.getText().toString());
                    db.update(TABLE_NAME, content, "id = " + Integer.valueOf(numéro), null);
                }

                if(activité!=etActivite.getText().toString()){
                    ContentValues content = new ContentValues();
                    content.put("activite",etActivite.getText().toString());
                    db.update(TABLE_NAME, content, "id = " + Integer.valueOf(numéro), null);
                }




                db.close();
                Intent intentt=new Intent(ModifierActivity.this, ModifAfficherActivity.class);
                startActivity(intentt);
            }
        });

        modifDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ModifierActivity.this);
                mBuilder.setTitle("Entrez une nouvelle date");
                final EditText input = new EditText(ModifierActivity.this);
                input.setInputType(InputType.TYPE_CLASS_DATETIME);
                input.setHint("date");
                mBuilder.setView(input);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String txt=input.getText().toString();
                        etDate.setText(txt);
                    }
                });

                mBuilder.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        modifHeure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ModifierActivity.this);
                mBuilder.setTitle("Entrez une nouvelle durée (en heures)");
                final EditText input = new EditText(ModifierActivity.this);
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                input.setHint("Nombre d'heures");
                mBuilder.setView(input);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String txt=input.getText().toString();
                        etHeure.setText(txt);
                    }
                });

                mBuilder.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

        modifActivite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(ModifierActivity.this);
                mBuilder.setTitle("Entrez une nouvelle description");
                final EditText input = new EditText(ModifierActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                input.setHint("description");
                mBuilder.setView(input);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String txt=input.getText().toString();
                        etActivite.setText(txt);
                    }
                });

                mBuilder.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

    }
}
