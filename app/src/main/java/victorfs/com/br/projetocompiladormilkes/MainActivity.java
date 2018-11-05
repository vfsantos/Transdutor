package victorfs.com.br.projetocompiladormilkes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnCompilar;
    Button btnLimpar;
    Button btnMostrarCompilador;
    TextView txtArea;
    String codigo;
    Lexer lexer = new Lexer();
    TextView lbCodigo;

    public static final String CODIGO = "br.com.victorfs.projetocompiladormilkes.codigo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Compilador");
        setSupportActionBar(toolbar);

        btnCompilar = (Button) findViewById(R.id.btn_compilar);
        btnLimpar = (Button) findViewById(R.id.btn_limpar);
        btnMostrarCompilador = (Button) findViewById(R.id.btn_mostrar_compilador);
        txtArea = (TextView) findViewById(R.id.txt_area);
        btnMostrarCompilador.setVisibility(View.INVISIBLE);

    }


    public void onClickCompilar(View view) {
        try {
            if(txtArea.getText().toString().equals("")){
                String mensagemToast = "Campo vazio";
                Toast toast = Toast.makeText(this, mensagemToast, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            } else {
                String texto = (String) txtArea.getText().toString();
                codigo = lexer.scan(texto);
                System.out.print("teste entrada: " + texto );
                System.out.print("teste saida: " + codigo );
                String mensagemToast = "Compilado com sucesso";
                lbCodigo = (TextView) findViewById(R.id.lb_codigo);
                lbCodigo.setText(codigo);

                Toast toast = Toast.makeText(this, mensagemToast, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();

                btnMostrarCompilador.setVisibility(View.VISIBLE);
            }

        } catch (Exception e) {

        }

        /*
        Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
        intent.putExtra(CODIGO, codigo);
        startActivity(intent);*/
    }

    public void onClickMaisDetalhes(View view) {
        Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
        startActivity(intent);
    }

    public void onClickLimpar(View view) {
        txtArea.setText("");
        lbCodigo.setText("");
        lexer = new Lexer();
        btnMostrarCompilador.setVisibility(View.INVISIBLE);

    }

}
