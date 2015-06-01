package travel.teste.com.br.travel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Pacotes> mPacotes;
    private ViewHolder mViewHolder;

    private Bitmap mBitmap;
    private Pacotes mPacote;
    private Activity mActivity;

    public CustomAdapter(Activity activity, List<Pacotes> pacotes) {
        mInflater = (LayoutInflater) activity.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        mPacotes = pacotes;
        mActivity = activity;
    }

    @Override
    public int getCount() {
        return mPacotes.size();
    }

    @Override
    public Object getItem(int position) {
        return mPacotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.post, parent, false);
            mViewHolder = new ViewHolder();

            mViewHolder.valor_desconto = (TextView) convertView.findViewById(R.id.valor_desconto);
            mViewHolder.titulo_pacote = (TextView) convertView.findViewById(R.id.titulo_pacote);


            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }


        mPacote = mPacotes.get(position);

        mViewHolder.valor_desconto.setText(mPacote.getvalor_desconto());
        mViewHolder.titulo_pacote.setText(mPacote.gettitulo_pacote());


        return convertView;
    }

    private static class ViewHolder {

        TextView titulo_pacote;
        TextView valor_desconto;

    }
}
