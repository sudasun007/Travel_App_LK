package com.example.travel_app_srilanka.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";


    // Table name and columns for accommodation details
    private static final String TABLE_ACCOMMODATIONS = "accommodations";
    private static final String COLUMN_ACCOM_ID = "id";
    private static final String COLUMN_ACCOM_NAME = "name";
    private static final String COLUMN_ACCOM_LOCATION = "location";
    private static final String COLUMN_ACCOM_DESCRIPTION = "description";
    private static final String COLUMN_ACCOM_PRICE = "price";

    // Create table SQL query
    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASSWORD + " TEXT"
            + ")";

    // Create table SQL query
    private static final String CREATE_ACCOMMODATIONS_TABLE = "CREATE TABLE " + TABLE_ACCOMMODATIONS + "("
            + COLUMN_ACCOM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ACCOM_NAME + " TEXT,"
            + COLUMN_ACCOM_LOCATION + " TEXT,"
            + COLUMN_ACCOM_DESCRIPTION + " TEXT,"
            + COLUMN_ACCOM_PRICE + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("DatabaseHelper", "Creating database tables");
        // Create users table
        db.execSQL(CREATE_USERS_TABLE);
        // Create accommodations table
        db.execSQL(CREATE_ACCOMMODATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DatabaseHelper", "Upgrading database, dropping existing table");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public void addUser(String name, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, hashPassword(password)); // Hash the password

        long result = db.insert(TABLE_USERS, null, values);
        Log.d("DatabaseHelper", "Inserted new user: " + result);
        db.close();
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] columns = {COLUMN_ID};
        String selection = COLUMN_EMAIL + " = ?" + " AND " + COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {email, hashPassword(password)}; // Hash the password

        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();

        return count > 0;
    }

    public User getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{COLUMN_NAME, COLUMN_EMAIL, COLUMN_PASSWORD},
                COLUMN_EMAIL + "=?", new String[]{email}, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            User user = new User(
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD))
            );
            cursor.close();
            db.close();
            return user;
        } else {
            if (cursor != null) {
                cursor.close();
            }
            db.close();
            return null; // or handle it accordingly
        }
    }

    public boolean updateUserInfo(String email, String newName, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, newName);
        values.put(COLUMN_PASSWORD, hashPassword(newPassword)); // Hash the new password

        int rowsAffected = db.update(TABLE_USERS, values, COLUMN_EMAIL + " = ?", new String[]{email});
        db.close();
        return rowsAffected > 0;
    }

    public boolean deleteUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_USERS, COLUMN_EMAIL + " = ?", new String[]{email});
        db.close();
        return rowsAffected > 0;
    }

    public boolean updateUserEmail(String oldEmail, String newEmail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL, newEmail);

        int rowsAffected = db.update(TABLE_USERS, values, COLUMN_EMAIL + " = ?", new String[]{oldEmail});
        db.close();
        return rowsAffected > 0;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.e("DatabaseHelper", "NoSuchAlgorithmException: " + e.getMessage());
            return null;
        }
    }

    public Cursor getAllAccommodations() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_ACCOMMODATIONS, null, null, null, null, null, null);
    }

}
