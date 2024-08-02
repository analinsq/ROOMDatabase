package com.example.atvroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.google.android.material.snackbar.Snackbar;


public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private final LayoutInflater mInflater;
    private List<User> mUsers;
    private UserViewModel mUserViewModel;

    class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView userNameView;
        private final TextView userCourseView;
        private final TextView userAgeView;
        private final Button deleteButton;

        private UserViewHolder(View itemView) {
            super(itemView);
            userNameView = itemView.findViewById(R.id.textViewName);
            userCourseView = itemView.findViewById(R.id.textViewCourse);
            userAgeView = itemView.findViewById(R.id.textViewAge);
            deleteButton = itemView.findViewById(R.id.bt_delete);
        }
    }

    UserListAdapter(Context context, UserViewModel userViewModel) {
        mInflater = LayoutInflater.from(context);
        mUserViewModel = userViewModel;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (mUsers != null) {
            User current = mUsers.get(position);
            holder.userNameView.setText(current.getName());
            holder.userCourseView.setText(current.getCourse());
            holder.userAgeView.setText(String.valueOf(current.getAge()));
            holder.deleteButton.setOnClickListener(v -> {
                mUserViewModel.delete(current);
                Snackbar.make(v, "Usuário excluído com sucesso :)", Snackbar.LENGTH_LONG).show();
            });
        }
    }

    void setUsers(List<User> users) {
        mUsers = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mUsers != null)
            return mUsers.size();
        else return 0;
    }
}







