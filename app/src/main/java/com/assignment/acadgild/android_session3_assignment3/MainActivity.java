package com.assignment.acadgild.android_session3_assignment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.Float;


public class MainActivity extends AppCompatActivity {

    EditText txtCardBalance, txtYearlyInt, txtMinPayment, txtFinalBalance,
            txtMonthRemain, txtIntPaid;
    Button btnCompute, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtCardBalance = (EditText) findViewById(R.id.editText_CardBalance);
        txtYearlyInt = (EditText) findViewById(R.id.editTextYearlyInt);
        txtMinPayment = (EditText) findViewById(R.id.editTextMinPmnt);
        txtFinalBalance = (EditText) findViewById(R.id.editText_FinalBalance);
        txtMonthRemain = (EditText) findViewById(R.id.editText_MonthRemain);
        txtIntPaid = (EditText) findViewById(R.id.editTexInterestPaid);

        btnCompute = (Button) findViewById(R.id.cmdCompute);
        btnClear = (Button) findViewById(R.id.cmdClear);

        btnCompute.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Float principal = Float.parseFloat(txtCardBalance.getText().toString());
                        Float rate = Float.parseFloat(txtYearlyInt.getText().toString());
                        Float minimum_payment = Float.parseFloat(txtMinPayment.getText().toString());
                        Float totalIntPaid = 0f;
                        Integer totalMonth = 0;
                        Float finalCardBalance;
                        Float monthlyPrinciple = 0f, totMonPrinciple = 0f;
                        Float monthlyBalance = principal;


                        while ( monthlyBalance>= minimum_payment) {
                        //for (int a = 1; a <= 10; a++) {

                            try {
                                Integer monthlyfloatInterestPaid = Math.round((principal * (rate / (100 * 12))));
                                monthlyPrinciple = minimum_payment - monthlyfloatInterestPaid;
                                monthlyBalance = principal - monthlyPrinciple;
                                principal = monthlyBalance;
                                totalIntPaid = totalIntPaid + monthlyfloatInterestPaid;
                                totMonPrinciple = totMonPrinciple + monthlyPrinciple;
                                totalMonth++;
                                Log.e("ERROR", "Monthly Interest is : " + monthlyfloatInterestPaid + " and the total is " + totalIntPaid);
                                Log.e("ERROR", "Monthly Principle is : " + monthlyPrinciple + " and the total is : "+ totMonPrinciple);
                                Log.e("ERROR", "Monthly Balance is : " + monthlyBalance);
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, "" + e.getStackTrace(), Toast.LENGTH_SHORT).show();
                            }

                        }
                        finalCardBalance = Float.parseFloat(txtCardBalance.getText().toString())-(totMonPrinciple + totalIntPaid);
                        txtFinalBalance.setText("" + finalCardBalance);
                        txtMonthRemain.setText("" + totalMonth);
                        txtIntPaid.setText("" + totalIntPaid);

                    }
                }
        );

    }
}
