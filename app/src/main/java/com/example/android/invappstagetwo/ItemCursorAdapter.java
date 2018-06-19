package com.example.android.invappstagetwo;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.invappstagetwo.data.ItemContract.ItemEntry;

/**
 * Created by NiklasBöckmann on 18.06.2018.
 */


public class ItemCursorAdapter extends CursorAdapter {

    Context mContext;
    /**
     * Constructs a new {@link ItemCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public ItemCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    /**
     * This method binds the item data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current item can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        mContext = context;
        // Find individual views that we want to modify in the list item layout
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        final TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);

        // Find the columns of item attributes that we're interested in
        int idColumnIndex = cursor.getColumnIndex(ItemEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_PRICE);
        final int quantityColumnIndex = cursor.getColumnIndex(ItemEntry.COLUMN_QUANTITY);

        // Read the item attributes from the Cursor for the current item
        String name = cursor.getString(nameColumnIndex);
        String price = cursor.getString(priceColumnIndex);
        final String itemQuantity = cursor.getString(quantityColumnIndex);
        final int[] quantity = {Integer.parseInt(itemQuantity)};
        final String id = cursor.getString(idColumnIndex);

        // Update the TextViews with the attributes for the current item
        nameTextView.setText(name);
        priceTextView.setText("Price: "+price+"$");
        quantityTextView.setText("Quantity: "+itemQuantity);


        TextView sold_button = (TextView) view.findViewById(R.id.sold_button);
        sold_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity[0] == 0){
                    Toast.makeText(mContext, (R.string.sell_product_failed), Toast.LENGTH_SHORT).show();
                } else {
                    quantity[0] = quantity[0] - 1;
                    ContentValues values = new ContentValues();
                    values.put(ItemEntry.COLUMN_QUANTITY, quantity[0]);
                    quantityTextView.setText(quantity[0] + "");
                    Uri currentItemUri = Uri.withAppendedPath(ItemEntry.CONTENT_URI, id);
                    mContext.getContentResolver().update(currentItemUri,
                            values,null,null);
                }
            }
        });
    }
}
