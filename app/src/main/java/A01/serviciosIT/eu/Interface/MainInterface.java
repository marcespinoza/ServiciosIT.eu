package A01.serviciosIT.eu.Interface;

public interface MainInterface {

    interface MainModel{
       void sendPassword(String code);
       void getUrl();
    }

    interface MainPresenter{
        void password(String s);
        void returnUrl(String url, String sub);
        void sinRegistro(String message);
        void getSharedP();
        void url(String url, String title);
    }

    interface MainView{

        void emptyPass();
        void showUrl(String url, String title);
        void showToast(String message);
        void showPage(String url, String title);
        void showPopup();
    }

}
