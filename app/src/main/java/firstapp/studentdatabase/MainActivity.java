package firstapp.studentdatabase;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    DataBaseHelper myDb;
    EditText editText1, editText2, editText3,editText4;
    Button button1, button2,button3,button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DataBaseHelper(this);
        //edit texts
        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);

        //buttons
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        AddClick();
        ShowClick();
        UpdateData();
        DeleteData();

    }

    public void AddClick() {
        button1.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                        boolean isInserted = myDb.insertData(editText1.getText().toString(),
                                editText2.getText().toString(),
                                editText3.getText().toString());
                        if (isInserted = true) {
                            Toast.makeText(MainActivity.this, "Data is inserted ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Data isnot inserted ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void ShowClick() {
        button2.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {

                        Cursor res = myDb.GetAllData();
                        if (res.getCount() == 0){
                            // show message
                            showMessage("Error", "Nothing found ");
                        return;
                    }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID : " + res.getString(0) + "\n");
                            buffer.append("Name : " + res.getString(1) + "\n");
                            buffer.append("Surname : " + res.getString(2) + "\n");
                            buffer.append("Marks : " + res.getString(3) + "\n\n");

                        }
                        // show all data
                        showMessage("Data " , buffer.toString());
                    }

                });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
      public void UpdateData() {
          button3.setOnClickListener(
                  new View.OnClickListener() {
                      public void onClick(View v) {
                      boolean isUpdated = myDb.updateData(editText4.getText().toString(),
                              editText1.getText().toString(),
                              editText2.getText().toString(),
                              editText3.getText().toString());
                          if (isUpdated = true) {
                              Toast.makeText(MainActivity.this, "Data is updated ", Toast.LENGTH_SHORT).show();
                          } else {
                              Toast.makeText(MainActivity.this, "Data isnot updated ", Toast.LENGTH_SHORT).show();
                          }
                      }
                      }

          );
      }
    public void DeleteData() {
        button4.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        Integer deleteRows = myDb.deleteData(editText4.getText().toString());
                        if(deleteRows > 0 ) // or deleteRows != 0
                        {
                            Toast.makeText(MainActivity.this, "Data is deleted ", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Data isnot deleted ", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
        );
    }





}
