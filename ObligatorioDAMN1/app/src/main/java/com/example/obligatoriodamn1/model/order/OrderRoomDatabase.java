package com.example.obligatoriodamn1.model.order;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Order.class}, version = 1, exportSchema = false)
public abstract class OrderRoomDatabase extends RoomDatabase {

    public abstract OrderDAO orderDao();

    private static volatile OrderRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static OrderRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (OrderRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            OrderRoomDatabase.class, "order_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                OrderDAO dao = INSTANCE.orderDao();
                dao.deleteAll();

                Order order = new Order("Hello", "Local", "fecha", "aclaraciones");
                dao.insert(order);

            });
        }
    };


}
