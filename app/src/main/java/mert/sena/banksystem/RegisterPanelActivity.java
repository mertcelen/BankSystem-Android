package mert.sena.banksystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class RegisterPanelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_panel);

        Button registerButton = (Button)findViewById(R.id.registerButton);

        registerButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        TextView nameText = (TextView)findViewById(R.id.nameText);
                        TextView addressText = (TextView)findViewById(R.id.addressText);
                        TextView limitText = (TextView)findViewById(R.id.limitText);
                        TextView usernameText = (TextView)findViewById(R.id.usernameText);
                        TextView passwordText = (TextView)findViewById(R.id.passwordText);
                        TextView statusLabel = (TextView)findViewById(R.id.statusLabel);
                        if(app.accounts.addAccount(nameText.getText().toString(), addressText.getText().toString(),
                                Integer.parseInt(String.valueOf(limitText.getText())),
                                usernameText.getText().toString(),
                                passwordText.getText().toString())) {
                                //
                            statusLabel.setText("User Created!");
                            try {
                                FileManagement.save();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                FileManagement.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
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
