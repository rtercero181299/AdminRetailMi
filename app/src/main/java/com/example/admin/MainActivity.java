package com.example.admin;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {
    EditText email,password;
    Button login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(EditText) findViewById(R.id.editTextTextPersonName);
        password=(EditText) findViewById(R.id.editTextTextPassword);
        login=(Button) findViewById(R.id.button);
        btn_register = (Button) findViewById(R.id.btn_register);
    }

    public Connection conexionBD(){
        Connection conexion=null;
        try{
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion= DriverManager.getConnection("jdbc:jtds:sqlserver://svr.ardabytec.vip;databaseName=rtm;user=sa;password=Primus90.$@;");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }
    public void loginUsuario(){
        try{
        PreparedStatement pst=conexionBD().prepareCall("{call ConsultarUsuario (?,?)}");
        pst.setString(1,email.getText().toString());
        pst.setString(2,password.getText().toString());
        pst.execute();
            Toast.makeText(getApplicationContext(),"Login con exito",Toast.LENGTH_SHORT).show();
        }catch (SQLException e){
            Toast.makeText(getApplicationContext(),"Problemas",Toast.LENGTH_SHORT).show();
        }
    }

    //Metodo para cambiar a la pantalla de registro
    public void register(View view){
        Intent i = new Intent(this, Register.class);
        startActivity(i);
    }
}