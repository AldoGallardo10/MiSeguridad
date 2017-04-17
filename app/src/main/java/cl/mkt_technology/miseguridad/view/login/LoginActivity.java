package cl.mkt_technology.miseguridad.view.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ResultCodes;

import java.util.Arrays;

import cl.mkt_technology.miseguridad.view.main.MainActivity;
import cl.mkt_technology.miseguridad.R;

public class LoginActivity extends AppCompatActivity implements LoginCallback{

    private static final int RC_SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        new LoginValidation(this).init();
    }

    @Override
    public void logged() {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }

    @Override
    public void sign() {
        startActivityForResult( //metodo copiado desde firebase auth ui
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setProviders(Arrays.asList(
                                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                        .setTheme(R.style.FullscreenTheme)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RC_SIGN_IN == requestCode){
            if (ResultCodes.OK == resultCode){
                logged();

            }
        }
    }
}
