package A01.serviciosIT.eu.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import A01.serviciosIT.eu.Aplicattion.GlobalApplication;
import A01.serviciosIT.eu.Interface.MainInterface;
import A01.serviciosIT.eu.Presenter.MainPresenter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainModel implements MainInterface.MainModel {

    public MainInterface.MainPresenter mPresenter;
    SharedPreferences preferences;

    public MainModel(MainPresenter mPresenter) {
        this.mPresenter = mPresenter;
        preferences = GlobalApplication.getContext().getSharedPreferences("serviciosit", Context.MODE_PRIVATE);

    }

    @Override
    public void sendPassword(String code) {
        postCode(code);
    }

    @Override
    public void getUrl() {
       String url = preferences.getString("url","");
       String title = preferences.getString("title","");
       mPresenter.url(url, title);
    }

    public void postCode(String code) {

        String url = "https://yourapp.serviciosit.eu/api_query.php";

        OkHttpClient client = new OkHttpClient();


        RequestBody formBody = new FormBody.Builder()
                .add("code", code)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mPresenter.sinRegistro("Error. Intente nuevamente");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String mMessage = response.body().string();
                if(!mMessage.equals("NO REGISTRO")){
                 String base = StringUtils.substringBefore(mMessage, "<");
                 String url = StringUtils.substringBetween(mMessage,">","<" );
                 String title = StringUtils.substringAfterLast(mMessage, ">");
                 SharedPreferences.Editor editor = preferences.edit();
                 editor.putString("url",base+url);
                 editor.putString("title",title);
                 editor.commit();
                    mPresenter.returnUrl(base+url, title);
                }else{
                    mPresenter.sinRegistro("SIN REGISTRO");
                }
            }
        });
    }

}
