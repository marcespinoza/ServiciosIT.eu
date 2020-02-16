package A01.serviciosIT.eu.View.Dialogs;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import A01.serviciosIT.eu.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;

public class PassPopup extends DialogFragment {


    @BindView(R.id.input_password)   TextInputEditText pass;
    @BindView(R.id.input_layout_password)  TextInputLayout inputLayout;
    private  DialogCallback dialogCallback;

    public interface DialogCallback{

        void clickedAccept(String pass);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.password_popup, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setCancelable(false);
    }

    public static PassPopup newInstance(String title) {
        PassPopup frag = new PassPopup();
        return frag;
    }

    @OnClick(R.id.aceptar)
    void aceptarCallback(){
        String password = pass.getText().toString();
        if(password.isEmpty()){
            Animation shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
            inputLayout.startAnimation(shake);
        }else{
            dialogCallback.clickedAccept(pass.getText().toString());
        }

    }

    @OnClick(R.id.cancelar)
    void cancelarCallback(){
        dismiss();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            dialogCallback = (DialogCallback) getActivity();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: " + e.getMessage());
        }
    }

}
