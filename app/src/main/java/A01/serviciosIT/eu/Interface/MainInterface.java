package A01.serviciosIT.eu.Interface;

public interface MainInterface {

    interface MainModel{
       void sendPassword(String code);
    }

    interface MainPresenter{
        void password(String s);
        void returnUrl(String url, String sub);
        void sinRegistro(String message);
    }

    interface MainView{

        void emptyPass();
        void showUrl(String url, String title);
        void showToast(String message);
    }

}
