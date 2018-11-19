package com.szczepanski.taskapplication;


import android.database.Cursor;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, packageName = "com.szczepanski.taskapplication")
public class DatabaseHelperTest {

    private DatabaseHelper databaseHelper;

    @Before
    public void setUp(){
        databaseHelper = new DatabaseHelper(RuntimeEnvironment.application);
    }

    @Test
    public void addDataTest() {
        assertTrue(databaseHelper.addData(new Task(1,"Name", "Staus")));
    }

    @Test
    public void getDataTest() {
        Cursor cursor = databaseHelper.getData();
        while (cursor.moveToNext()){
            assertEquals(1, cursor.getInt(0));
            assertEquals("Name", cursor.getString(1));
            assertEquals("Status", cursor.getString(2));
        }
    }

    @After
    public void tearDown(){
        databaseHelper = null;
    }
}