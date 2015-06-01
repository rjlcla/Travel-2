package travel.teste.com.br.travel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.analytics.tracking.android.EasyTracker;
import com.google.analytics.tracking.android.MapBuilder;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;



public class MainActivity extends ActionBarActivity {


    public static final String URL = "http://demo4955212.mockable.io/viajabessa";

    private ListView mListView;
    private CustomAdapter mAdapter,mAdapter1 ;
    List<Pacotes> listPacotes;
    EasyTracker easyTracker;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);

        new SimpleTask().execute(URL);
        easyTracker = EasyTracker.getInstance(MainActivity.this);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case (R.id.refresh):

                new SimpleTask().execute(URL);

                break;
        }

        return super.onOptionsItemSelected(item);
    }
    private class SimpleTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            // Create Show ProgressBar


            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Carregando Ofertas...");
            pDialog.show();
        }


        protected String doInBackground(String... urls)   {
            String result = "";
            try {

                HttpGet httpGet = new HttpGet(urls[0]);
                HttpClient client = new DefaultHttpClient();

                HttpResponse response = client.execute(httpGet);

                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode == 200) {
                    InputStream inputStream = response.getEntity().getContent();
                    BufferedReader reader = new BufferedReader
                            (new InputStreamReader(inputStream));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result += line;
                    }
                }else {
                    Log.e("Json", "Erro durante o download do arquivo");
                }

            } catch (ClientProtocolException e) {

            } catch (IOException e) {

            }
            return result;
        }

        protected void onPostExecute(String jsonString)  {
            // Dismiss ProgressBar
            pDialog.dismiss();
            showData(jsonString);
        }
    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }
    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    private void showData(String jsonString) {
        Gson gson = new Gson();
        Info info = gson.fromJson(jsonString, Info.class);
        List<Pacotes> pacotes = info.getPacotes();



        mAdapter = new CustomAdapter(this, pacotes);
        mListView.setAdapter(mAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long code) {

                String modelo = getDeviceName();
                String versao_android=Build.VERSION.RELEASE;


                String versionName = null;
                try {
                    versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }





                Pacotes item	= (Pacotes) mAdapter.getItem(position);

                easyTracker.send(MapBuilder.createEvent("ui_action","oferta",item.gettitulo_pacote(),null).build());
                easyTracker.send(MapBuilder.createEvent("ui_action","modelo",modelo,null).build());
                easyTracker.send(MapBuilder.createEvent("ui_action","versao",versionName,null).build());
                easyTracker.send(MapBuilder.createEvent("ui_action","versao Android",versao_android ,null).build());

                Intent i	= new Intent(MainActivity.this, Detalhe.class);
                i.putExtra("id", item);
                startActivity(i);
            }

        });
    }

}