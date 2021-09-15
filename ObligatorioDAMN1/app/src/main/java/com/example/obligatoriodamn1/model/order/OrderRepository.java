package com.example.obligatoriodamn1.model.order;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class OrderRepository {
    private OrderDAO mOrderDAO;
    private LiveData<List<Order>> mAllOrders;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public OrderRepository(Application application) {
        OrderRoomDatabase db = OrderRoomDatabase.getDatabase(application);
        mOrderDAO = db.orderDao();
        mAllOrders = mOrderDAO.getOrders();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Order>> getAllOrders() {
        return mAllOrders;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Order order) {
        //OrderRoomDatabase.databaseWriteExecutor.execute(() -> {
        //    mOrderDAO.insert(order);
        //});
        new insertAsyncTask(mOrderDAO).execute(order);
    }

    private static class insertAsyncTask extends AsyncTask<Order, Void, Void> {
        private OrderDAO orderDatoAsyncTask;

        public insertAsyncTask(OrderDAO dao){
            orderDatoAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDatoAsyncTask.insert(orders[0]);
            return null;
        }
    }

}
