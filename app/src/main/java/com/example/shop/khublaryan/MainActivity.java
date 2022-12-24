package com.example.shop.khublaryan;

// Khublaryan Edward 303 group

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    CheckBox[] chk = new CheckBox[4];
    EditText[] num = new EditText[4];
    EditText[] price = new EditText[4];

    RadioButton rbDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // checkBox
        {
            chk[0] = findViewById(R.id.chkBoxApple);
            chk[1] = findViewById(R.id.chkBoxStrawberry);
            chk[2] = findViewById(R.id.chkBoxBanana);
            chk[3] = findViewById(R.id.chkBoxCheese);
        }

        // editText
        {
            num[0] = findViewById(R.id.editTextApple);
            num[1] = findViewById(R.id.editTextStrawberry);
            num[2] = findViewById(R.id.editTextBanana);
            num[3] = findViewById(R.id.editTextCheese);

            price[0] = findViewById(R.id.editTextPriceApple);
            price[1] = findViewById(R.id.editTextPriceStrawberry);
            price[2] = findViewById(R.id.editTextPriceBanana);
            price[3] = findViewById(R.id.editTextPriceCheese);
        }

        rbDialog = findViewById(R.id.rbDialog);
    }

    public void Calculate(View v){
        double result = 0;
        double sum;
        String message = "";

        for (int i = 0; i < Arrays.stream(chk).count(); i++){
            if (chk[i].isChecked()){
                try {
                    sum = Double.parseDouble(num[i].getText().toString()) * Double.parseDouble(price[i].getText().toString());
                    result += sum;
                    message += String.format("%d: %.2f * %.2f = %.2f\n", i + 1, Double.parseDouble(num[i].getText().toString()), Double.parseDouble(price[i].getText().toString()), sum);
                }
                catch (Exception ex){
                    Toast toast = Toast.makeText(this, "Enter the correct values", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
            }
        }
        message += String.format("Total: %.2f", result);

        if (result == 0){
            Toast toast = Toast.makeText(this, "Products not selected", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        if (rbDialog.isChecked()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Result")
                    .setMessage(message)
                    .setIcon(R.drawable.good_picture)
                    .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog dlg = builder.create();
            dlg.show();
        }
        else{
            Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}