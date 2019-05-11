package example.com.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button consulter;
    Button ajouter;
    Button supprimer;
    Button modifier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        consulter=(Button)findViewById(R.id.bConsulter);
        ajouter=(Button)findViewById(R.id.bAjouter);
        supprimer=(Button)findViewById(R.id.bSupprimer);
        modifier=(Button)findViewById(R.id.bModifier);

        consulter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentt = new Intent(MainActivity.this, AfficherActivity.class);
                startActivity(intentt);
            }
        });

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentt = new Intent(MainActivity.this, AjouterActivity.class);
                startActivity(intentt);
            }
        });

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentt = new Intent(MainActivity.this, SupprimerActivity.class);
                startActivity(intentt);
            }
        });

        modifier.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentt=new Intent(MainActivity.this, ModifierActivity.class);
                startActivity(intentt);
            }
        });
    }



}





   /*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button)findViewById(R.id.button);
        nextIntent=(Button)findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intentt = new Intent(MainActivity.this, Main2Activity.class);
                    startActivity(intentt);
                    Toast.makeText(getApplicationContext(),"Mauvais mot de passe/username",Toast.LENGTH_LONG).show();
      */