package fr.eni.locakar.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.eni.locakar.R;
import fr.eni.locakar.bo.Vehicule;

public class VehiculeAdapter extends RecyclerView.Adapter<VehiculeAdapter.ViewHolder>{


        List<Vehicule> vehicules = null;
        private View.OnClickListener monClickListener;

        /**
         * Constructeur
         * @param vehicules Données à afficher.
         */
    public VehiculeAdapter(List<Vehicule> vehicules, View.OnClickListener monClickListener)
        {
            this.vehicules = vehicules;
            this.monClickListener = monClickListener;
        }
        /**
         * Décompresse le fichier my_article_view.xml et créé un ViewHolder qui le représente.
         * @param parent
         * @param viewType
         * @return Un objet représentant my_article_view.xml
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_cards, parent, false);

            ViewHolder vh = new ViewHolder(v,monClickListener);
            return vh;
        }


        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {
            holder.marque.setText(vehicules.get(position).getMarques());
            holder.immat.setText(vehicules.get(position).getImmatriculation());
            holder.itemView.setTag(position);
        }

        @Override
        public int getItemCount() {
        return vehicules.size();
    }

        /**
         * Classe interne
         */
        public static class ViewHolder extends RecyclerView.ViewHolder
        {
            public TextView marque;
            public TextView immat;

            public ViewHolder(View v, View.OnClickListener monClickListener)
            {
                super(v);
                marque = v.findViewById(R.id.marque_vehicule);
                immat = v.findViewById(R.id.immat_vehicule);

                v.setOnClickListener(monClickListener);
            }
        }



    }

