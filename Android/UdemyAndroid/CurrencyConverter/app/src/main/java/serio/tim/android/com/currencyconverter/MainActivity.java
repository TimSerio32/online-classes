package serio.tim.android.com.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convertPressed(View view) {
        EditText amountEditText = (EditText)findViewById(R.id.amountEditText);
        RadioGroup inputRadioGroup = (RadioGroup)findViewById(R.id.inputRadioGroup);
        RadioGroup toRadioGroup = (RadioGroup)findViewById(R.id.toRadioGroup);
        TextView convertedTextView = (TextView)findViewById(R.id.convertedTextView);

        Double amountDbl = Double.parseDouble(amountEditText.getText().toString());

        if(inputRadioGroup.getCheckedRadioButtonId() == R.id.dollar && toRadioGroup.getCheckedRadioButtonId() == R.id.toDollar) {
            convertedTextView.setText("$" + String.format("%.2f", amountDbl));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.dollar && toRadioGroup.getCheckedRadioButtonId() == R.id.toEuro) {
            Double dollarToEuro = amountDbl * 0.84;
            convertedTextView.setText("€" + String.format("%.2f", dollarToEuro));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.dollar && toRadioGroup.getCheckedRadioButtonId() == R.id.toPeso) {
            Double dollarToPeso = amountDbl * 18.82;
            convertedTextView.setText("Mex$" + String.format("%.2f", dollarToPeso));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.dollar && toRadioGroup.getCheckedRadioButtonId() == R.id.toPound) {
            Double dollarToPound = amountDbl * 0.76;
            convertedTextView.setText("£" + String.format("%.2f", dollarToPound));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.dollar && toRadioGroup.getCheckedRadioButtonId() == R.id.toReal) {
            Double dollarToReal = amountDbl * 3.17;
            convertedTextView.setText("R$" + String.format("%.2f", dollarToReal));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.dollar && toRadioGroup.getCheckedRadioButtonId() == R.id.toRupee) {
            Double dollarToRupee = amountDbl * 64.91;
            convertedTextView.setText("₹" + String.format("%.2f", dollarToRupee));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.dollar && toRadioGroup.getCheckedRadioButtonId() == R.id.toYuan) {
            Double dollarToYuan = amountDbl * 6.63;
            convertedTextView.setText("¥" + String.format("%.2f", dollarToYuan));
        }

        else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.euro && toRadioGroup.getCheckedRadioButtonId() == R.id.toDollar) {
            Double euroToDollar = amountDbl * 1.18;
            convertedTextView.setText("$" + String.format("%.2f", euroToDollar));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.euro && toRadioGroup.getCheckedRadioButtonId() == R.id.toEuro) {
//            convertedTextView.setText("€" + amountDbl.toString());
            convertedTextView.setText("€" + String.format("%.2f", amountDbl));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.euro && toRadioGroup.getCheckedRadioButtonId() == R.id.toPeso) {
            Double euroToPeso = amountDbl * 22.30;
            convertedTextView.setText("Mex$" + String.format("%.2f", euroToPeso));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.euro && toRadioGroup.getCheckedRadioButtonId() == R.id.toPound) {
            Double euroToPound = amountDbl * 0.90;
            convertedTextView.setText("£" + String.format("%.2f", euroToPound));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.euro && toRadioGroup.getCheckedRadioButtonId() == R.id.toReal) {
            Double euroToReal = amountDbl * 3.76;
            convertedTextView.setText("R$" + String.format("%.2f", euroToReal));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.euro && toRadioGroup.getCheckedRadioButtonId() == R.id.toRupee) {
            Double euroToRupee = amountDbl * 76.91;
            convertedTextView.setText("₹" + String.format("%.2f", euroToRupee));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.euro && toRadioGroup.getCheckedRadioButtonId() == R.id.toYuan) {
            Double euroToYuan = amountDbl * 7.84;
            convertedTextView.setText("¥" + String.format("%.2f", euroToYuan));
        }


        else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.peso && toRadioGroup.getCheckedRadioButtonId() == R.id.toDollar) {
            Double pesoToDollar = amountDbl * 0.053;
            convertedTextView.setText("$" + String.format("%.2f", pesoToDollar));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.peso && toRadioGroup.getCheckedRadioButtonId() == R.id.toEuro) {
            Double pesoToEuro = amountDbl * 0.045;
            convertedTextView.setText("€" + String.format("%.2f", pesoToEuro));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.peso && toRadioGroup.getCheckedRadioButtonId() == R.id.toPeso) {
            convertedTextView.setText("Mex$" + String.format("%.2f", amountDbl));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.peso && toRadioGroup.getCheckedRadioButtonId() == R.id.toPound) {
            Double pesoToPound = amountDbl * 0.040;
            convertedTextView.setText("£" + String.format("%.2f", pesoToPound));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.peso && toRadioGroup.getCheckedRadioButtonId() == R.id.toReal) {
            Double pesoToReal = amountDbl * 0.17;
            convertedTextView.setText("R$" + String.format("%.2f", pesoToReal));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.peso && toRadioGroup.getCheckedRadioButtonId() == R.id.toRupee) {
            Double pesoToRupee = amountDbl * 3.45;
            convertedTextView.setText("₹" + String.format("%.2f", pesoToRupee));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.peso && toRadioGroup.getCheckedRadioButtonId() == R.id.toYuan) {
            Double pesoToYuan = amountDbl * 0.35;
            convertedTextView.setText("¥" + String.format("%.2f", pesoToYuan));
        }



        else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.pound && toRadioGroup.getCheckedRadioButtonId() == R.id.toDollar) {
            Double poundToDollar = amountDbl * 1.32;
            convertedTextView.setText("$" + String.format("%.2f", poundToDollar));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.pound && toRadioGroup.getCheckedRadioButtonId() == R.id.toEuro) {
            Double poundToEuro = amountDbl * 1.11;
            convertedTextView.setText("€" + String.format("%.2f", poundToEuro));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.pound && toRadioGroup.getCheckedRadioButtonId() == R.id.toPeso) {
            Double poundToPeso = amountDbl * 24.76;
            convertedTextView.setText("Mex$" + String.format("%.2f", poundToPeso));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.pound && toRadioGroup.getCheckedRadioButtonId() == R.id.toPound) {
            convertedTextView.setText("£" + String.format("%.2f", amountDbl));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.pound && toRadioGroup.getCheckedRadioButtonId() == R.id.toReal) {
            Double poundToReal = amountDbl * 4.17;
            convertedTextView.setText("R$" + String.format("%.2f", poundToReal));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.pound && toRadioGroup.getCheckedRadioButtonId() == R.id.toRupee) {
            Double poundToRupee = amountDbl * 85.39;
            convertedTextView.setText("₹" + String.format("%.2f", poundToRupee));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.pound && toRadioGroup.getCheckedRadioButtonId() == R.id.toYuan) {
            Double poundToYuan = amountDbl * 8.70;
            convertedTextView.setText("¥" + String.format("%.2f", poundToYuan));
        }


          else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.real && toRadioGroup.getCheckedRadioButtonId() == R.id.toDollar) {
            Double realToDollar = amountDbl * 0.32;
            convertedTextView.setText("$" + String.format("%.2f", realToDollar));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.real && toRadioGroup.getCheckedRadioButtonId() == R.id.toEuro) {
            Double realToEuro = amountDbl * 0.27;
            convertedTextView.setText("€" + String.format("%.2f", realToEuro));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.real && toRadioGroup.getCheckedRadioButtonId() == R.id.toPeso) {
            Double realToPeso = amountDbl * 5.93;
            convertedTextView.setText("Mex$" + String.format("%.2f", realToPeso));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.real && toRadioGroup.getCheckedRadioButtonId() == R.id.toPound) {
            Double realToPound = amountDbl * 0.24;
            convertedTextView.setText("£" + String.format("%.2f", realToPound));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.real && toRadioGroup.getCheckedRadioButtonId() == R.id.toReal) {
            convertedTextView.setText("R$" + String.format("%.2f", amountDbl));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.real && toRadioGroup.getCheckedRadioButtonId() == R.id.toRupee) {
            Double realToRupee = amountDbl * 20.46;
            convertedTextView.setText("₹" + String.format("%.2f", realToRupee));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.real && toRadioGroup.getCheckedRadioButtonId() == R.id.toYuan) {
            Double realToYuan = amountDbl * 2.09;
            convertedTextView.setText("¥" + String.format("%.2f", realToYuan));
        }

          else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.rupee && toRadioGroup.getCheckedRadioButtonId() == R.id.toDollar) {
            Double rupeeToDollar = amountDbl * 0.015;
            convertedTextView.setText("$" + String.format("%.2f", rupeeToDollar));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.rupee && toRadioGroup.getCheckedRadioButtonId() == R.id.toEuro) {
            Double rupeeToEuro = amountDbl * 0.013;
            convertedTextView.setText("€" + String.format("%.2f", rupeeToEuro));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.rupee && toRadioGroup.getCheckedRadioButtonId() == R.id.toPeso) {
            Double rupeeToPeso = amountDbl * 0.29;
            convertedTextView.setText("Mex$" + String.format("%.2f", rupeeToPeso));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.rupee && toRadioGroup.getCheckedRadioButtonId() == R.id.toPound) {
            Double rupeeToPound = amountDbl * 0.012;
            convertedTextView.setText("£" + String.format("%.2f", rupeeToPound));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.rupee && toRadioGroup.getCheckedRadioButtonId() == R.id.toReal) {
            Double rupeeToReal = amountDbl * 0.049;
            convertedTextView.setText("R$" + String.format("%.2f", rupeeToReal));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.rupee && toRadioGroup.getCheckedRadioButtonId() == R.id.toRupee) {
            convertedTextView.setText("₹" + String.format("%.2f", amountDbl));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.rupee && toRadioGroup.getCheckedRadioButtonId() == R.id.toYuan) {
            Double rupeeToYuan = amountDbl * 0.10;
            convertedTextView.setText("¥" + String.format("%.2f", rupeeToYuan));
        }

          else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.yuan && toRadioGroup.getCheckedRadioButtonId() == R.id.toDollar) {
            Double yuanToDollar = amountDbl * 0.15;
            convertedTextView.setText("$" + String.format("%.2f", yuanToDollar));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.yuan && toRadioGroup.getCheckedRadioButtonId() == R.id.toEuro) {
            Double yuanToEuro = amountDbl * 0.13;
            convertedTextView.setText("€" + String.format("%.2f", yuanToEuro));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.yuan && toRadioGroup.getCheckedRadioButtonId() == R.id.toPeso) {
            Double yuanToPeso = amountDbl * 2.84;
            convertedTextView.setText("Mex$" + String.format("%.2f", yuanToPeso));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.yuan && toRadioGroup.getCheckedRadioButtonId() == R.id.toPound) {
            Double yuanToPound = amountDbl * 0.11;
            convertedTextView.setText("£" + String.format("%.2f", yuanToPound));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.yuan && toRadioGroup.getCheckedRadioButtonId() == R.id.toReal) {
            Double yuanToReal = amountDbl * 0.48;
            convertedTextView.setText("R$" + String.format("%.2f", yuanToReal));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.yuan && toRadioGroup.getCheckedRadioButtonId() == R.id.toRupee) {
            Double yuanToRupee = amountDbl * 9.79;
            convertedTextView.setText("₹" + String.format("%.2f", yuanToRupee));
        } else if(inputRadioGroup.getCheckedRadioButtonId() == R.id.yuan && toRadioGroup.getCheckedRadioButtonId() == R.id.toYuan) {
            convertedTextView.setText("¥" + String.format("%.2f", amountDbl));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
