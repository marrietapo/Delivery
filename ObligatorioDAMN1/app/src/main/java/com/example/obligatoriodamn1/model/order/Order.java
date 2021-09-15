package com.example.obligatoriodamn1.model.order;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Date;

@Entity(tableName = "order_table")
public class Order {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @ColumnInfo(name = "_id")
    private String _id;

    @NonNull
    @ColumnInfo(name = "local")
    private String local;

    @NonNull
    @ColumnInfo(name = "date")
    private String fecha;

    @NonNull
    @ColumnInfo(name = "annotations")
    private String aclaraciones;

    public Order(@NonNull String _id, @NonNull String local, @NonNull String fecha, @NonNull String aclaraciones) {
        this._id = _id;
        this.local = local;
        this.fecha = fecha;
        this.aclaraciones = aclaraciones;
    }

    public String getLocal() {
        return this.local;
    }

    public String getFecha(){
        return this.fecha;
    }

    public String getAclaraciones(){
        return this.aclaraciones;
    }

    public String getId(){
        return this._id;
    }
}
