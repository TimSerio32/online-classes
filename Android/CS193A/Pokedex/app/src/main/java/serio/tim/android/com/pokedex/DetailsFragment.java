package serio.tim.android.com.pokedex;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import stanford.androidlib.SimpleActivity;
import stanford.androidlib.SimpleFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends SimpleFragment {


    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SimpleActivity activity = getSimpleActivity();
        String pokemonName = activity.getStringExtra( "pokemon_name", "Pikachu" );
        setPokemonName( pokemonName );
    }

    public void setPokemonName(String pokemonName) {
        SimpleActivity activity = getSimpleActivity();
        int imageID = activity.getResourceId( pokemonName.toLowerCase(), "drawable" );
        int fileID = activity.getResourceId( pokemonName.toLowerCase(), "raw" );
        String fileText = activity.readFileText( fileID );
        activity.$TV( R.id.pokemon_name ).setText( pokemonName );
        activity.$IV( R.id.pokemon_image ).setImageResource( imageID );
        activity.$TV( R.id.pokemon_details ).setText( fileText );
    }
}
