package newcom.example.userlistappjava;

// Importaciones necesarias
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView; // 🚨 IMPORTANTE: RecyclerView de AndroidX

import java.util.List;

// Adaptador para RecyclerView que mostrará la lista de usuarios
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList; // Lista de usuarios a mostrar

    // Constructor que recibe la lista de usuarios
    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    // Clase interna (ViewHolder) que representa cada ítem de la lista
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, emailTextView;

        public UserViewHolder(View itemView) {
            super(itemView);
            // Referencias a los TextView del layout item_user.xml
            nameTextView = itemView.findViewById(R.id.tvName);
            emailTextView = itemView.findViewById(R.id.tvEmail);
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflar el diseño de cada ítem (item_user.xml)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        // Vincular los datos de un usuario con los componentes visuales
        User user = userList.get(position);
        holder.nameTextView.setText(user.getName());
        holder.emailTextView.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        // Retorna la cantidad de elementos en la lista
        return userList.size();
    }
}
