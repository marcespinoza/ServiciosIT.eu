package A01.serviciosIT.eu.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

import A01.serviciosIT.eu.Interface.MainInterface;
import A01.serviciosIT.eu.Presenter.MainPresenter;
import A01.serviciosIT.eu.R;
import A01.serviciosIT.eu.View.Dialogs.PassPopup;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainInterface.MainView, PassPopup.DialogCallback{

    private MainInterface.MainPresenter mPresenter;
    PassPopup passPopup = null;
    MaterialToolbar materialToolbar;
    TextView titletoolbar;
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swipeRefreshLayout;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        webView.setWebViewClient(new WebViewClient());
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.loadUrl(url);

            }
        });
        materialToolbar = findViewById(R.id.toolbar);
        titletoolbar = (TextView) materialToolbar.findViewById(R.id.toolbar_title);
        mPresenter = new MainPresenter(this);
        showPopup();
    }

    void showPopup(){
        FragmentManager fm = getSupportFragmentManager();
        passPopup = PassPopup.newInstance("Contrasenia");
        passPopup.show(fm, "pass");
    }

    @Override
    public void clickedAccept(String s) {
        passPopup.dismiss();
        swipeRefreshLayout.post(new Runnable()
        {
            @Override
            public void run()
            {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
        mPresenter.password(s);
    }

    @Override
    public void clickedCancel() {
        finish();
    }

    @Override
    public void emptyPass() {
    }

    @Override
    public void showUrl(String url, String title) {
        swipeRefreshLayout.setRefreshing(false);
        this.url = url;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(url);
                titletoolbar.setText("serviciosIT.eu | "+ title);
            }
        });
    }

    @Override
    public void showToast(String message) {
        showPopup();
        swipeRefreshLayout.setRefreshing(false);
        runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
