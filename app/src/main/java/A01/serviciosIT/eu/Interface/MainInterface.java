package A01.serviciosIT.eu.Interface;

public interface MainInterface {

    interface MainModel{
       void sendPassword(String code);
    }

    interface MainPresenter{
        void password(String s);
        void returnUrl(String sub);
    }


    interface MainView{

        void emptyPass();
        void showUrl(String sub);
    }

}
