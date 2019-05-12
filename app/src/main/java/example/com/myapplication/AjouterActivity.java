package example.com.myapplication;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AjouterActivity extends AppCompatActivity {

    private static final String DB_NAME = "Project.db";
    private static final String TABLE_NAME = "Projet";
    private SQLiteDatabase db;
    private StringBuilder t_debug=new StringBuilder();
    private TextView etDate;
    private EditText etHeure;
    private EditText etActivite;
    private Button confirmer;

    final Calendar myCalendar = Calendar.getInstance();


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);db = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);

        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME+ " (id INTEGER PRIMARY KEY AUTOINCREMENT," +" date TEXT, nombreH NUMBER, activite TEXT);");

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        etDate=(TextView) findViewById(R.id.etDate);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AjouterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        etHeure=(EditText)findViewById(R.id.etHeure);
        etActivite=(EditText)findViewById(R.id.etActivite);


        confirmer=(Button)findViewById(R.id.bConfirmer);

        confirmer.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             if(!etHeure.getText().toString().isEmpty()&&!etActivite.getText().toString().isEmpty()&&!etDate.getText().toString().isEmpty()) {
                                                 String date = etDate.getText().toString();
                                                 int heure = Integer.valueOf(etHeure.getText().toString());
                                                 String activite = etActivite.getText().toString();
                                                 db.execSQL("INSERT INTO PROJET (date, nombreH, activite) " +
                                                         "VALUES ('"+date+"','"+heure+"','"+activite+"')",new String[]{});
                                                 db.close();
                                                 Intent intentt = new Intent(AjouterActivity.this, MainActivity.class);
                                                 startActivity(intentt);

                                             }
                                             else Toast.makeText(getApplicationContext(),"Vous n'avez pas rempli tous les champs",Toast.LENGTH_LONG).show();

                                         }
                                     }

        );

    }
    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etDate.setText(sdf.format(myCalendar.getTime()));
    }
}
