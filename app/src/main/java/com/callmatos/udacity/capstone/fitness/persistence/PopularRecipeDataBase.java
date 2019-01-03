package study.callmatos.org.bakingapp.persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.provider.MediaStore;
import android.util.Log;

import study.callmatos.org.bakingapp.model.Recipe;

@Database(entities = {Recipe.class}, version = 1,exportSchema = false)
public abstract class PopularRecipeDataBase extends RoomDatabase {

    private static final String LOG_TAG = PopularRecipeDataBase.class.getSimpleName();
    private static final Object LOCK = new Object();

    //Variable with name of database.
    public static String DATABASE_NAME = "popularmovie";

    //Single object
    private static PopularRecipeDataBase INSTANCE;

    //Object to use the movies
    public abstract RecipeDAO recipeDao();

    //AppDataBase with single class to return the reference.
    public static PopularRecipeDataBase getInstance(final Context ct) {

        synchronized (LOCK) {
            if (INSTANCE == null) {
                Log.d(LOG_TAG, "Creating new database instance");
                INSTANCE = Room.databaseBuilder(ct.getApplicationContext(),
                        PopularRecipeDataBase.class, DATABASE_NAME)
                        .build();
            }
        }
        Log.d(LOG_TAG, "Getting the database instance");
        return INSTANCE;
    }
}
