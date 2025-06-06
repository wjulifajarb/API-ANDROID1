package newcom.example.userlistappjava;

// Clase modelo que representa un Usuario
public class User {
    private int id;
    private String name;
    private String email;

    // Constructor vacío (importante para Retrofit/Gson)
    public User() {
    }

    // Getters para acceder a las propiedades privadas
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Setters si necesitas (opcional, pero útiles si editas datos)
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
