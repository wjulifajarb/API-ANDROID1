package newcom.example.userlistappjava;

// 🚨 IMPORTS NECESARIOS
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import newcom.example.userlistappjava.ApiService; // Importar tu propia interfaz

// Clase que configura Retrofit para toda la app
public class RetrofitClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static RetrofitClient instance = null;
    private ApiService apiService;

    // Constructor privado para Singleton
    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    // Método para obtener la instancia Singleton
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    // Método para obtener el ApiService configurado
    public ApiService getApiService() {
        return apiService;
    }
}
