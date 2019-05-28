package com.example.a19134355_7.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.os.LocaleListCompat.create;

public class MainActivity extends AppCompatActivity {

    private Button btnCreate;
    private Button btnDelete;
    private carsSQLiteHelper carsHelper;
    private SQLiteDatabase db;
    private ListView listView;
    private MyAdapter adapter;
    private List<car> cars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        cars = new ArrayList<>();
        btnCreate = findViewById(R.id.buttonCreate);
        btnDelete = findViewById(R.id.buttonDelete);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                create();
                update();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAll();
                update();
            }
        });

        //abrimos la DB 'DBtest' en modo escritura
        carsHelper = new carsSQLiteHelper(this,"DBTest",null,1);
        db = carsHelper.getWritableDatabase();
        adapter = new  MyAdapter(this,cars,R.layout.itemdb);
        listView.setAdapter(adapter);
        update();
    }
    private List<car> getAllCars() {
        //seleccionamos todos los registros de la tabla cars
        Cursor cursor = db.rawQuery("select * from Cars", null);
        List<car> list = new ArrayList<car>();
        if (cursor.moveToFirst())
        //iteramos sobre el cursor de resultados
        //y vamos rellenando el array que posterior,emte devolveremos
        {
            while (cursor.isAfterLast() == false) {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                Log.i("variable = ", String.valueOf(id) );
                String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                String color = cursor.getString(cursor.getColumnIndex("color"));
                list.add(new car(id, nombre, color));
                cursor.moveToNext();

            }

        }
        return list;
    }
        public void create()
        {
            //si hemos creado la BD correctamente
            if(db != null)
            {
                ContentValues nuevoRegistro= new ContentValues();
                //el ID es autoincremental por eso no se declara
                nuevoRegistro.put("nombre","chevy");
                nuevoRegistro.put("color","negro");

                db.insert("Cars",null,nuevoRegistro);

            }
        }
        public void removeAll()
        {

            Cursor cursor = db.query("Cars", null, null, null, null, null, null);

            if(cursor.moveToLast()) {
                String rowId = cursor.getString(cursor.getColumnIndex("id"));

                db.delete("Cars", "id" + "=?",  new String[]{rowId});
            }


        }
        private void update()
        {
            //borramos todos los elementos
            cars.clear();
            //cargamos todos los elementos
            cars.addAll(getAllCars());
            //refrescamos el adaptador
            adapter.notifyDataSetChanged();
        }

   @Override
    protected void onDestroy()
   {
       db.close();
       super.onDestroy();
   }


}
