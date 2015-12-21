package mert.sena.banksystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.io.IOException;
import java.util.Map;

public class RegisterPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_panel);
        Firebase.setAndroidContext(this);

        Button registerButton = (Button)findViewById(R.id.registerButton);

        registerButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Firebase database = new Firebase("https://sweltering-torch-6571.firebaseio.com/");
                        TextView nameText = (TextView)findViewById(R.id.nameText);
                        TextView addressText = (TextView)findViewById(R.id.addressText);
                        TextView limitText = (TextView)findViewById(R.id.limitText);
                        TextView usernameText = (TextView)findViewById(R.id.usernameText);
                        TextView pinCodeText = (TextView)findViewById(R.id.pinCodeText);
                        TextView passwordText = (TextView)findViewById(R.id.passwordText);
                        TextView statusLabel = (TextView)findViewById(R.id.statusLabel);
                        if(app.accounts.addAccount(nameText.getText().toString(), addressText.getText().toString(),
                                Integer.parseInt(String.valueOf(limitText.getText())),
                                usernameText.getText().toString(),
                                passwordText.getText().toString(),
                        Integer.parseInt(pinCodeText.getText().toString()))) {
                                //
                            statusLabel.setText("User Created!");
                            System.out.println("deneme");
                            database.createUser(usernameText.getText().toString(), passwordText.getText().toString(), new Firebase.ValueResultHandler<Map<String, Object>>() {
                                @Override
                                public void onSuccess(Map<String, Object> result) {
                                    Log.i("mertFilter", "user added");
                                    System.out.println("user added");
                                }

                                @Override
                                public void onError(FirebaseError firebaseError) {
                                    Log.i("mertFilter", "error");
                                    System.out.println("error");
                                }
                            });
                            Intent intent1 = new Intent(RegisterPanelActivity.this,AccountActivity.class);
                            startActivity(intent1);

                        }
                        else
                            statusLabel.setText("Username Exist!");


                    }
                }
        );
    }

}
