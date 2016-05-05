package br.edu.iff.pooa20152.ppi.ppiapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import br.edu.iff.pooa20152.ppi.ppiapp.helper.RestFullHelper;

public class Professor extends AppCompatActivity {

    private EditText edIdProfessor;
    private EditText edProfessor;
    private EditText edFormacao;

    private final String TAG = "MAIN";
    private Button btCadastrar;
    private Button btMostrar;
    private Button btDeletar;
    private String durl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor);

        edIdProfessor = (EditText) findViewById(R.id.edIdProfessor);
        edProfessor = (EditText) findViewById(R.id.edProfessor);
        edFormacao = (EditText) findViewById(R.id.edFormacao);

        durl = getString(R.string.URL);

        btMostrar = (Button) findViewById(R.id.btMostrar);
        btMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filtro = edIdProfessor.getText().toString();
                if (!filtro.equalsIgnoreCase("")) {
                    getInformationtoAPI();
                }
            }
        });

        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edIdProfessor.getText().toString()))
                    postInformationtoAPI();
                else
                    putInformationtoAPI();
            }

        });

        btDeletar = (Button) findViewById(R.id.btDeletar);
        btDeletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletarInformationtoAPI();
            }
        });
    }

    private void deletarInformationtoAPI() {
        Log.i(TAG, "Deletar ORDER");
        JSONObject params = null;

        FabricanteTask bgtDel = new FabricanteTask(
                durl + "/professores/"
                        + edIdProfessor.getText().toString() + ".json",
                RestFullHelper.DELETAR, params);
        bgtDel.execute();
    }

    private void getInformationtoAPI() {
        JSONObject params = null;

        FabricanteTask bgtGet = new FabricanteTask(
                durl + "/professores/"
                        + edIdProfessor.getText().toString() + ".json",
                RestFullHelper.GET, params);
        bgtGet.execute();
    }

    private void postInformationtoAPI() {
        Log.d(TAG, "POSTING ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("nome", edProfessor.getText().toString());
            params.put("formacao", edFormacao.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        FabricanteTask bgtPost = new FabricanteTask(
                durl + "/professores.json", RestFullHelper.POST, params);
        bgtPost.execute();

    }

    private void putInformationtoAPI() {
        Log.i(TAG, "PUT ORDER");
        JSONObject params = new JSONObject();

        try {
            params.put("nome", edProfessor.getText().toString());
            params.put("formacao", edFormacao.getText().toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        FabricanteTask bgtPut = new FabricanteTask(
                durl + "/professores/"
                        + edIdProfessor.getText().toString() + ".json",
                RestFullHelper.PUT, params);
        bgtPut.execute();
    }

    private Context getContext() {
        return this;
    }

    private void alert(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public class FabricanteTask extends AsyncTask<String, String, JSONObject> {

        String url = null;
        String method = null;
        JSONObject params1 = null;

        ProgressDialog dialog;

        public FabricanteTask(String url, String method, JSONObject params1) {
            this.url = url;
            this.method = method;
            this.params1 = params1;

        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(Professor.this);
            dialog.show();
        }

        @Override
        protected void onPostExecute(JSONObject professor) {

            if (professor != null) {
                try {
                    edIdProfessor.setText(professor.getString("id"));
                    edProfessor.setText(professor.getString("nome"));
                    edFormacao.setText(professor.getString("formacao"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            dialog.dismiss();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            RestFullHelper http = new RestFullHelper();
            return http.getJSON(url, method, params1);
        }
    }
}
