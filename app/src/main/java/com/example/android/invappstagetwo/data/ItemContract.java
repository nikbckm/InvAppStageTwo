package com.example.android.invappstagetwo.data;
import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


public final class ItemContract {

    private ItemContract() {}

    public static final String CONTENT_AUTHORITY = "com.example.android.invappstagetwo";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ITEMS = "items";

    public static final class ItemEntry implements BaseColumns {

        /** The content URI to access the item data in the provider */
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ITEMS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ITEMS;

        /**
         * The MIME type of the {@link #CONTENT_URI} for a single item.
         */
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ITEMS;

        /** Name of database table for items */
        public final static String TABLE_NAME = "items";

        /**
         * Unique ID number for the item (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the product.
         *
         * Type: TEXT
         */
        public final static String COLUMN_NAME ="name";

        /**
         * Price of the product.
         *
         * Type: INT
         */
        public final static String COLUMN_PRICE = "price";

        /**
         * quantity of the product.
         *
         * Type: INT
         */
        public final static String COLUMN_QUANTITY = "quantity";

        /**
         * supplier name.
         *
         * Type: STRING
         */
        public final static String COLUMN_SUPPLIER = "supplier";

        /**
         * supplier nr.
         *
         * Type: STRING
         */
        public final static String COLUMN_SUPPLIER_NR = "suppliernr";

    }

}

