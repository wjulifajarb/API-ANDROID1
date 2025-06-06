package newcom.example.userlistappjava;

// 🚨 IMPORTS NECESARIOS
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

// MainActivity que extiende de AppCompatActivity
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        getUsers();
    }

    private void getUsers() {
        Call<List<User>> call = RetrofitClient.getInstance().getApiService().getUsers();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> userList = response.body();
                    recyclerView.setAdapter(new UserAdapter(userList));
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // Manejo de error
                t.printStackTrace();
            }
        });
    }
}
