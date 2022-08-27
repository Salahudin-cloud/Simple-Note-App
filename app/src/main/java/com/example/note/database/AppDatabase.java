package com.example.note.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.note.database.dao.NoteDao;
import com.example.note.database.entity.Note;

@Database(entities = {Note.class}, version = 1)
public abstract  class AppDatabase extends RoomDatabase {

    private static AppDatabase mInstance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    //membuat nama database
    public static final  String DATABASE_NAME = "NoteApp";

    public abstract NoteDao noteDao();

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    private void updateDatabaseCreated(final Context context ) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    public static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context , AppDatabase.class , DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        AppDatabase database = AppDatabase.getInstance(context);
                        database.setDatabaseCreated();
                    }
                }).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        
    }

    public static AppDatabase getInstance(final Context context) {
        if (mInstance == null){
            synchronized (AppDatabase.class){
                if (mInstance == null){
                    mInstance = buildDatabase(context);
                    mInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return  mInstance;
    }

}
