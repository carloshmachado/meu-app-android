package meuprimeiroapp.studio.com.meuprimeiroapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button logar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logar = (Button) findViewById(R.id.logar);
        final Boolean[] logado = new Boolean[1];

        logar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView cnpj = (TextView) findViewById(R.id.Cnpj);
                TextView razao = (TextView) findViewById(R.id.Razao);
                String sCnpj = cnpj.getText().toString();
                String sRazao = razao.getText().toString();

                logado[0] = Logar(sCnpj, sRazao);
                if (logado[0] = true) {
                    setContentView(R.layout.tela_inicial);
                }
            }
        });
    }

    public boolean Logar(String aCnpj, String aRazao) {
        try {

            URL url = new URL("http://localhost:8080/api/exemplo/" + aCnpj + "/" + aRazao);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/text");
            connection.connect();

            String resposta =
                    new Scanner(connection.getInputStream()).next();
            if (resposta.equals("Bem vindo Empresa " + aRazao)) {
                alert(resposta);
                return true;
            } else {
                alert(resposta);
                return false;
            }
        } catch (MalformedURLException e) {
            alert("Ocorreu um erro ao logar: " + e.getMessage());
            return false;
        } catch (IOException e) {
            alert("Ocorreu um erro ao logar: " + e.getMessage());
            return false;
        }
    }

    private void alert(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
