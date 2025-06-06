package newcom.example.userlistappjava;

// 🚨 IMPORTS NECESARIOS
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

// Importa tu clase User
import newcom.example.userlistappjava.User;

// Definición de la Interfaz para Retrofit
public interface ApiService {

    // Definimos el endpoint GET /users
    @GET("users")
    Call<List<User>> getUsers();
}
