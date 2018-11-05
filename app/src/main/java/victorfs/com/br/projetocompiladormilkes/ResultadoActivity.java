package victorfs.com.br.projetocompiladormilkes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultadoActivity extends AppCompatActivity {

    Button btnVoltar;
    Intent intentVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);

        Intent intent = getIntent();
        String codigo = (String) intent.getSerializableExtra(MainActivity.CODIGO);

    }

    public void onClickVoltar (View v) {
        intentVoltar = new Intent(ResultadoActivity.this, MainActivity.class);
        startActivity(intentVoltar);
    }



}
