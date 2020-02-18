package A01.serviciosIT.eu.Presenter;

import A01.serviciosIT.eu.Interface.MainInterface;
import A01.serviciosIT.eu.Model.MainModel;
import A01.serviciosIT.eu.View.MainActivity;

public class MainPresenter implements MainInterface.MainPresenter {

    public MainInterface.MainModel mModel;
    public MainInterface.MainView mView;

    public MainPresenter(MainActivity mView) {
        this.mView = mView;
        mModel = new MainModel(this);
    }

    @Override
    public void password(String s) {
        if(s.isEmpty()){
            mView.emptyPass();
        }else{
            mModel.sendPassword(s);
        }
    }

    @Override
    public void returnUrl(String url, String title) {
        mView.showUrl(url, title);
    }

    @Override
    public void sinRegistro(String message) {
        mView.showToast(message);
    }

    @Override
    public void getSharedP() {
        mModel.getUrl();
    }

    @Override
    public void url(String url, String title) {
        if(url.isEmpty()){
            mView.showPopup();
        }else{
            mView.showPage(url, title);
        }
    }
}
