📚 UserListAppJava — Consumo de API REST en Android
📖 Índice
Introducción

¿Qué es JSONPlaceholder?

Tecnologías Usadas

Objetivos del Proyecto

¿Qué Hace la App?

Vista de la App

Estructura del Proyecto

Explicación de Cada Archivo

¿De Dónde se Consume la API?

¿Por Qué Trae Esta Información?

Flujo de Datos

✨ Introducción
UserListAppJava es una aplicación móvil desarrollada en Java para Android que demuestra cómo consumir un API REST de manera sencilla y eficiente usando tecnologías modernas como Retrofit y Gson.

La aplicación conecta a un servicio web de prueba (JSONPlaceholder) para descargar y mostrar en tiempo real una lista de usuarios ficticios en un formato de lista desplazable utilizando RecyclerView.

🌐 ¿Qué es JSONPlaceholder?
JSONPlaceholder es un API pública falsa (mock API) muy usada por desarrolladores para:

Simular peticiones reales sin necesidad de tener un servidor propio.

Hacer peticiones HTTP como GET, POST, PUT, DELETE.

🔗 En este proyecto usamos el endpoint:

arduino
Copiar
Editar
https://jsonplaceholder.typicode.com/users
que devuelve un listado de usuarios ficticios en formato JSON como:

json
Copiar
Editar
[
  {
    "id": 1,
    "name": "Leanne Graham",
    "email": "Sincere@april.biz"
  },
  {
    "id": 2,
    "name": "Ervin Howell",
    "email": "Shanna@melissa.tv"
  }
]
🛠️ Tecnologías Usadas
Tecnología	Uso
Java	Lenguaje de programación principal
Android SDK	Framework para construir apps Android
Retrofit	Cliente HTTP para Android (consumo de APIs REST)
Gson	Conversión automática de JSON a objetos Java
RecyclerView	Componente eficiente para mostrar listas dinámicas
JSONPlaceholder	API gratuita para pruebas de consumo de usuarios

🎯 Objetivos del Proyecto
Entender cómo consumir una API REST desde una app Android.

Aprender a convertir respuestas JSON en clases de modelo Java.

Mostrar información en una lista interactiva (RecyclerView).

Practicar el uso de herramientas modernas como Retrofit y Gson.

Construir una arquitectura limpia, modular y escalable.

🔍 ¿Qué Hace Exactamente la App?
Se conecta automáticamente a la API REST al abrirse.

Descarga una lista de usuarios (nombre y correo).

Muestra estos usuarios en una lista desplazable usando RecyclerView.

Los datos son cargados dinámicamente desde Internet.

Funciona en cualquier emulador o dispositivo Android real.

🖼️ Vista de la App
📱 Lo que verás en pantalla:

Nombre	Email
Leanne Graham	Sincere@april.biz
Ervin Howell	Shanna@melissa.tv
Clementine Bauch	Nathan@yesenia.net

Cada ítem muestra el nombre y el correo de un usuario.

🗂️ Estructura del Proyecto
css
Copiar
Editar
app/src/main/java/newcom/example/userlistappjava/
├── ApiService.java
├── MainActivity.java
├── RetrofitClient.java
├── User.java
└── UserAdapter.java

app/src/main/res/layout/
├── activity_main.xml
└── item_user.xml

app/src/main/AndroidManifest.xml
📜 Explicación de Cada Archivo
MainActivity.java
java
Copiar
Editar
package newcom.example.userlistappjava;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

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
                t.printStackTrace();
            }
        });
    }
}
User.java
java
Copiar
Editar
package newcom.example.userlistappjava;

public class User {
    private int id;
    private String name;
    private String email;

    public User() {}

    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
}
UserAdapter.java
java
Copiar
Editar
package newcom.example.userlistappjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tvName);
            emailTextView = itemView.findViewById(R.id.tvEmail);
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.nameTextView.setText(user.getName());
        holder.emailTextView.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
ApiService.java
java
Copiar
Editar
package newcom.example.userlistappjava;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("users")
    Call<List<User>> getUsers();
}
RetrofitClient.java
java
Copiar
Editar
package newcom.example.userlistappjava;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static RetrofitClient instance = null;
    private ApiService apiService;

    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }
        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }
}
activity_main.xml
xml
Copiar
Editar
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerView"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"/>
</androidx.constraintlayout.widget.ConstraintLayout>
item_user.xml
xml
Copiar
Editar
<androidx.cardview.widget.CardView 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:card_view="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
    card_view:cardCornerRadius="8dp">

    <LinearLayout
        android:orientation="vertical"
        android:padding="16dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:id="@+id/tvName"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Name"
            android:textSize="18sp"
            android:textStyle="bold" />

        <TextView
            android:id="@+id/tvEmail"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Email"
            android:textSize="16sp" />
    </LinearLayout>
</androidx.cardview.widget.CardView>
🌐 ¿De Dónde se Consume la API?
Usamos:

arduino
Copiar
Editar
https://jsonplaceholder.typicode.com/users
que devuelve un JSON con usuarios ficticios (ID, nombre y email).

🤔 ¿Por Qué Trae Esta Información?
JSONPlaceholder es una API gratuita de prueba.

Permite obtener datos simulados sin tener que montar un backend real.

Ideal para desarrollo y pruebas de consumo de APIs.

🔥 ¿Cómo Fluyen los Datos?
plaintext
Copiar
Editar
MainActivity → RetrofitClient → ApiService → API /users → JSON → List<User> → UserAdapter → RecyclerView
MainActivity llama a getUsers().

RetrofitClient crea una instancia de Retrofit.

ApiService define el endpoint GET /users.

Retrofit hace la petición y recibe un JSON.

Gson convierte ese JSON en List<User>.

UserAdapter toma la lista y la pasa al RecyclerView.

RecyclerView muestra la lista en pantalla.

✅ Resumen
UserListAppJava:

Conecta a una API REST externa.

Descarga datos en formato JSON.

Convierte JSON a objetos Java.

Muestra dinámicamente en un RecyclerView.

Perfecto como proyecto de aprendizaje, demo de consumo de API, o base para apps más complejas 🚀.
