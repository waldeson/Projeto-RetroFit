package com.example.projetoretrofit2.activity;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.projetoretrofit2.R;
import com.example.projetoretrofit2.bootstrap.APIAlbum;
import com.example.projetoretrofit2.models.UserId;
import com.example.projetoretrofit2.resource.UserResource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumActivity extends AppCompatActivity {

    UserResource apiUserResouce;
    EditText txtId;
    EditText txtUserTitle;
    ListView listViewAlbum;

    List<UserId> listUser;
    List<HashMap<String,String>> colecao =
            new ArrayList<HashMap<String,String>>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        ///////////////////////////////////////////////////////////////////////////////////////////

        apiUserResouce = APIAlbum.getAlbum().create(UserResource.class);

        Call<List<UserId>> get = apiUserResouce.get();

        get.enqueue(new Callback<List<UserId>>() {
            @Override
            public void onResponse(Call<List<UserId>> call, Response<List<UserId>> response) {

                listViewAlbum = findViewById(R.id.listViewUser);
                listUser = response.body();
                listUser.forEach (new Consumer<UserId>() {
                                      @Override
                                      public void accept(UserId u) {

                                          HashMap<String, String> mapUser = new HashMap<>();
                                          mapUser.put("id", String.valueOf(u.getId()));
                                          mapUser.put("title", u.getTitle());
                                          colecao.add(mapUser);
                                      }
                                  }
                );
                String[] from = {"id", "title"};
                int[] to = {R.id.textViewId, R.id.textViewTitles};

                SimpleAdapter simpleAdapter =
                        new SimpleAdapter(
                                getApplicationContext(),
                                colecao,
                                R.layout.layout_list,
                                from,
                                to);
                listViewAlbum.setAdapter(simpleAdapter);
            }

            @Override
            public void onFailure(Call<List<UserId>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(),Toast.LENGTH_LONG).show();

            }
        });
    }

    public void addAlbum(View view) {
        String title;
        Integer id;
        id = Integer.parseInt(txtId.getText().toString());
        title = txtUserTitle.getText().toString();


        final  UserId user = new UserId(id, title, null);

        Call<UserId> post = apiUserResouce.post(user);
        post.enqueue(new Callback<UserId>() {
            @Override
            public void onResponse(Call<UserId> call, Response<UserId> response) {
                UserId u = response.body();
                Toast.makeText(getApplicationContext(),
                        u.toString(),
                        Toast.LENGTH_LONG).show();

            }



            @Override
            public void onFailure(Call<UserId> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });

        Call<UserId> put = apiUserResouce.put(id, user);
        put.enqueue(new Callback<UserId>() {
            @Override
            public void onResponse(Call<UserId> call, Response<UserId> response) {

            }

            @Override
            public void onFailure(Call<UserId> call, Throwable t) {

            }
        });

        Call<Void> delete = apiUserResouce.delete(id);
        delete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}

