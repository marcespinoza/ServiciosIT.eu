package A01.serviciosIT.eu.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        mPresenter.password(s);
    }

    @Override
    public void emptyPass() {
    }

    @Override
    public void showUrl(String sub) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                titletoolbar.setText("serviciosIT.eu | "+sub);
            }
        });
        passPopup.dismiss();
    }
}
