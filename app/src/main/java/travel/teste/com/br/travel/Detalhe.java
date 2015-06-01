package travel.teste.com.br.travel;



import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;


public class Detalhe extends Activity {

    ImageView ivImagemDetalhe;
    TextView tvQtdeVendida;
    TextView tvDescricaoDetalhe;
    TextView tvPrecoOriginal;
    TextView tvPrecoDetalhe;
    private Bitmap mBitmap;
    ImageView img;
    Bitmap bitmap;
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe);

        ivImagemDetalhe = (ImageView)findViewById(R.id.ivImgDetalheOferta);
        tvQtdeVendida = (TextView)findViewById(R.id.tvQtdeVendida);
        tvDescricaoDetalhe = (TextView)findViewById(R.id.tvDescricaoDetalhe);
        tvPrecoOriginal = (TextView)findViewById(R.id.tvPrecoOriginal);
        tvPrecoDetalhe = (TextView)findViewById(R.id.tvPrecoDetalhe);






        if(getIntent() != null) {

           Pacotes of = (Pacotes) getIntent().getExtras().get("id");



             ivImagemDetalhe = (ImageView) findViewById(R.id.ivImgDetalheOferta);




            tvDescricaoDetalhe.setText(of.getdescricao_pacote());

            tvPrecoOriginal.setText("De " +  of.valor_original);
            tvPrecoDetalhe.setText("Por " +  of.valor_desconto);
            tvQtdeVendida.setText(of.gettitulo_pacote()+" - "+String.valueOf(of.qtdvendida));

            final String URL = of.foto;

            Log.i("TAG",of.foto );

            new LoadImage().execute(URL);




            ivImagemDetalhe.setImageBitmap(mBitmap);



        }


    }


    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Detalhe.this);
            pDialog.setMessage("Carregando....");
            pDialog.show();

        }
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap image) {

            if(image != null){
                ivImagemDetalhe.setImageBitmap(image);
                pDialog.dismiss();

            }else{

                pDialog.dismiss();


            }
        }
    }


}
