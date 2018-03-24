package com.example.android.cafecoffeeday;

import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.data;
import static android.R.attr.id;
import static android.R.attr.name;
import static android.R.attr.value;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int noc = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();


        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedcreamcheckbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();


        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolatecheckbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();


        int price = calculatePrice(hasChocolate,hasWhippedCream);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "CCD Order for "+name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        //displayMessage(priceMessage);
    }

    /*public void Order(View view){


        EditText text = (EditText) findViewById(R.id.name_field);
        String name = text.getText().toString();


        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedcreamcheckbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();


        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolatecheckbox);
        boolean hasChocolate = ChocolateCheckBox.isChecked();


        int price = calculatePrice(hasChocolate,hasWhippedCream);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        Intent i = new Intent(this,NumbersActivity.class);
        i.putExtra("priceMessage",priceMessage);
        startActivity(i);



        //displayMessage(priceMessage);
    }*/




    private int calculatePrice(boolean hasChocolate,boolean hasWhippedCream)

    {
        int price=0;
        price = noc * 5;
        if(hasChocolate==true)
        {
            price=price+2;
        }
        if(hasWhippedCream==true)
        {
            price=price+2;
        }

        return price;
    }

    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage = priceMessage + "\nWhipped Cream: " + hasWhippedCream;
        priceMessage = priceMessage + "\nChocolate: " + hasChocolate;
        priceMessage = priceMessage + "\nQuantity: " + noc + "\nTotal=" + price;
        priceMessage = priceMessage + "\n"+ getString(R.string.thank_you);

        return priceMessage;

    }

    /**
     * This method is called to subtract quantity.
     */
    public void sub(View view)

    {
        if(noc>1) {
            noc--;
            display(noc);
        }
        else
        {
            Toast.makeText(this,"You cannot have less than one cup of coffee",
                    Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This method is called to add quantity.
     */
    public void add(View view)


    {
        if(noc<100) {
            noc++;
        }
        else
        {
            Toast.makeText(this,"You cannot have more than hundred cups of coffee",
                    Toast.LENGTH_SHORT).show();
        }
        display(noc);

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
         quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     *
     private void displayMessage(String priceMessage) {
     TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
     priceTextView.setText(priceMessage);
     }
     */
}
