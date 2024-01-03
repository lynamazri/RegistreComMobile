package com.example.registrecommobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydb";
    private static final String TABLE_USERS = "users";
    private static final String TABLE_REQUESTS = "requests";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_COMPANY_NAME = "company_name";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_ACTIVITY_TYPE = "activity_type";
    private static final String KEY_PERSONAL_INFO = "personal_info";
    private static final String KEY_USER_ID = "user_id";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERNAME + " TEXT,"
                + KEY_PASS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_REQUESTS_TABLE = "CREATE TABLE " + TABLE_REQUESTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_COMPANY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_ACTIVITY_TYPE + " TEXT,"
                + KEY_PERSONAL_INFO + " TEXT,"
                + KEY_USER_ID + " INTEGER,"
                + " FOREIGN KEY (" + KEY_USER_ID + ") REFERENCES " + TABLE_USERS + "(" + KEY_ID + "))";
        db.execSQL(CREATE_REQUESTS_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REQUESTS);

        onCreate(db);

    }

    void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASS, user.getPassword());

        db.insert(TABLE_USERS, null, values);

        db.close();
    }

    User getUserByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        User user = null;

        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_ID, KEY_USERNAME, KEY_PASS},
                KEY_USERNAME + "=?", new String[]{username}, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                user = new User(
                        Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2)
                );
            }
            cursor.close();
        }

        return user;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<User>();

        String selectQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setID(Integer.parseInt(cursor.getString(0)));
                user.setUsername(cursor.getString(1));
                user.setPassword(cursor.getString(2));

                userList.add(user);
            } while (cursor.moveToNext());
        }


        return userList;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASS, user.getPassword());

        return db.update(TABLE_USERS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getID()) });
    }
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?",
                new String[] { String.valueOf(user.getID()) });
        db.close();
    }

    void addRequest(Request request) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COMPANY_NAME, request.getCompanyName());
        values.put(KEY_ADDRESS, request.getAddress());
        values.put(KEY_ACTIVITY_TYPE, request.getActivityType());
        values.put(KEY_PERSONAL_INFO, request.getPersonalInfo());
        values.put(KEY_USER_ID, request.getUserId());

        db.insert(TABLE_REQUESTS, null, values);
        db.close();
    }

    public int updateRequest(Request request) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COMPANY_NAME, request.getCompanyName());
        values.put(KEY_ADDRESS, request.getAddress());
        values.put(KEY_ACTIVITY_TYPE, request.getActivityType());
        values.put(KEY_PERSONAL_INFO, request.getPersonalInfo());
        values.put(KEY_USER_ID, request.getUserId());

        return db.update(TABLE_REQUESTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(request.getId())});
    }

    public List<Request> getAllRequests() {
        List<Request> requestList = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_REQUESTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Request request = new Request();
                request.setId(Integer.parseInt(cursor.getString(0)));
                request.setCompanyName(cursor.getString(1));
                request.setAddress(cursor.getString(2));
                request.setActivityType(cursor.getString(3));
                request.setPersonalInfo(cursor.getString(4));
                request.setUserId(Integer.parseInt(cursor.getString(5)));

                requestList.add(request);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return requestList;
    }

    public List<Request> getRequestsByUserId(int userId) {
        List<Request> requestList = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_REQUESTS, null, KEY_USER_ID + "=?",
                new String[]{String.valueOf(userId)}, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Request request = new Request();
                request.setId(Integer.parseInt(cursor.getString(0)));
                request.setCompanyName(cursor.getString(1));
                request.setAddress(cursor.getString(2));
                request.setActivityType(cursor.getString(3));
                request.setPersonalInfo(cursor.getString(4));
                request.setUserId(Integer.parseInt(cursor.getString(5)));

                requestList.add(request);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return requestList;
    }



}