package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int whipprice = 0;
        int chocprice = 0;

        CheckBox whippedCream = (CheckBox) findViewById(R.id.whippedcream_checkbox);
        boolean haswhippedCream = whippedCream.isChecked();
        if (haswhippedCream == true) {
            whipprice = 1;
        }


        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean haschocolate = chocolate.isChecked();
        if (haschocolate == true) {
            chocprice = 2;
        }


        EditText textinbox = (EditText) findViewById(R.id.edittext);
        String textbox = textinbox.getText().toString();

        int price = calculatePrice(whipprice, chocprice);

        String summarycon = createOrdersummary(textbox, price, haswhippedCream, whipprice, haschocolate, chocprice);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this

        intent.putExtra(Intent.EXTRA_SUBJECT, "COFFEE ORDER SUMMARY FROM CENTRAL PERK TO  " + textbox);
        intent.putExtra(Intent.EXTRA_TEXT, summarycon);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }


    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int whipprice, int chocprice) {

        return (quantity * (5 + whipprice + chocprice));

    }

    private String createOrdersummary(String textbox, int price, boolean haswhippedCream, int whipprice, boolean haschocolate, int chocprice) {
        String summary = "Name:" + textbox + "\n" + "Add whipped cream?" + haswhippedCream + "     Rs" + whipprice * quantity + "\n" + "Add Chocolate?" + haschocolate + "       Rs." + chocprice * quantity + "\n" + "Quantity=" + quantity + "\n" + "Total= Rs." + price + "\n" + "\nTHANK YOU!" + "\nHave a nice day :)";

        return summary;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {

        quantity = quantity + 1;
        if (quantity >= 100) {
            quantity = 100;
        }
        display(quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void decrement(View view) {

        quantity = quantity - 1;
        if (quantity <= 1) {
            quantity = 1;
        }
        display(quantity);

    }

    /**
     * This method displays the given price on the screen.
     */
   /* private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }*/

    /**
     * This method displays the given text on the screen.
     */

}
