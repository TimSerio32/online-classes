package serio.tim.android.com.pokedex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleFragment;


public class PokedexFragment extends SimpleFragment {


    public PokedexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokedex, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SimpleActivity activity = getSimpleActivity();
        activity.$IB(R.id.blastoise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pokemonClick(view);
            }
        });
    }

    public void pokemonClick(View view) {
        ImageButton butt = (ImageButton) view;
        String tag = butt.getTag().toString();

        SimpleActivity activity = getSimpleActivity();
        if(activity.isPortrait()) {
            activity.startActivity(DetailsActivity.class, "pokemon_name", tag);
        } else {
            DetailsFragment frag = (DetailsFragment) activity.getFragmentManager().findFragmentById(R.id.frag_details);
            frag.setPokemonName(tag);
        }

    }
}
