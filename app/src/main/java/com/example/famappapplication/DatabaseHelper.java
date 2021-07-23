package com.example.famappapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context contxt;

    public DatabaseHelper(Context context) {
        super(context, "child.db", null, 1);
        contxt = context;
    }
    // for tables addfamily and users.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tbl_family (id INTEGER PRIMARY KEY AUTOINCREMENT, fName TEXT, mName TEXT, cName TEXT, cAge TEXT, mobile TEXT, comment TEXT)");
        db.execSQL("CREATE TABLE app_user (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT,email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tbl_family");
        onCreate(db);

    }
    //insertion of received info into database
    public long addChildData(Child c) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("fName", c.getnFather());
        cv.put("mName", c.getnMother());
        cv.put("cName", c.getnChild());
        cv.put("cAge", c.getChildAge());
        cv.put("mobile", c.getMobile());
        cv.put("comment", c.getCaseR());

        return db.insert("tbl_family", null, cv);
    }

    public ArrayList<Child> getAllChildren() {

        ArrayList<Child> children = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_family", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String fName = cursor.getString(1);
                String mName = cursor.getString(2);
                String cName = cursor.getString(3);
                String childAge = cursor.getString(4);
                String mobile = cursor.getString(5);
                String caseR = cursor.getString(6);

                Child c = new Child(id, fName, mName, cName, childAge, mobile, caseR);

                children.add(c);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return children;
    }

    public int UpdateChild(Child c) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("fName", c.getnFather());
        cv.put("mName", c.getnMother());
        cv.put("cName", c.getnChild());
        cv.put("cAge", c.getChildAge());
        cv.put("mobile", c.getMobile());
        cv.put("comment", c.getCaseR());

        return db.update("tbl_family", cv, "id=?", new String[]{String.valueOf(c.getId())});

    }

    public int deleteChild(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("tbl_family", "id=?", new String[]{String.valueOf(id)});

    }

    public boolean registerUser(String username, String password, String emailAddress) {
        if (isUserAlreadyExisting(username, emailAddress)) {
            Toast t;
            t = Toast.makeText(contxt, "User already exist. Please use a different email or username", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        boolean userCreated = true;
        String newPassword = generateMD5Password(password);
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("username", username);
            cv.put("password", newPassword);
            cv.put("email", emailAddress);
            userCreated = db.insert("app_user", null, cv) > 0;
        } catch (Exception e) {
            userCreated = false;
            System.out.printf("Could not create user because: " + e.getMessage());
            Toast t = Toast.makeText(contxt, "Something went wrong, please uninstall and reinstall application", Toast.LENGTH_SHORT);
            t.show();
            e.printStackTrace();
        }
        return userCreated;
    }

    public boolean login(String username, String password) {
        boolean userLoggedIn = false;
        try {
            String newPassword = generateMD5Password(password);

            String passwordFromDB = null;

            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM app_user WHERE username='" + username + "'", null);

            while (cursor.moveToNext()) {
                passwordFromDB = cursor.getString(2); ///password is stored on index 2 according to table creation mapping
                System.out.printf("Stored password is: " + passwordFromDB);
                //get password stored in the database and compare with provided password
            }
            cursor.close();
            if (passwordFromDB != null) {
                if (passwordFromDB.contentEquals(newPassword)) {
                    Toast t = Toast.makeText(contxt, "Login Successful.", Toast.LENGTH_SHORT);
                    t.show();
                    userLoggedIn = true;
                }
            } else {
                Toast t = Toast.makeText(contxt, "User does not exist, please register", Toast.LENGTH_SHORT);
                t.show();
            }
        } catch (Exception e) {
            System.out.println("Could not login because: " + e.getMessage());
            Toast t = Toast.makeText(contxt, "Something went wrong, please uninstall and reinstall application", Toast.LENGTH_SHORT);
            t.show();
            e.printStackTrace();
        }
        return userLoggedIn;
    }

    public boolean isUserAlreadyExisting(String username, String email) {
        boolean userExist = false;
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM app_user WHERE username='" + username + "' OR email='" + email + "'", null);

            while (cursor.moveToNext()) {
                userExist = true;
                break;
            }
            cursor.close();

        } catch (Exception e) {
            System.out.printf("Could not check that user exist because: " + e.getMessage());
            Toast t = Toast.makeText(contxt, "Something went wrong, please uninstall and reinstall application", Toast.LENGTH_SHORT);
            t.show();
            e.printStackTrace();
        }
        return userExist;
    }

    public String generateMD5Password(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException ex) {
            System.out.println("Could not generate MD5 String");
            ex.printStackTrace();
        }
        return null;
    }
}
