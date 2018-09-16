package ar.com.smartcart.smartcart.communication;

import org.json.JSONException;
import java.io.IOException;
import java.util.List;
import ar.com.smartcart.smartcart.modelo.Producto;

/**
 * Created by jonesb on 4/24/2015.
 */
public class JsonHelper {

    public List<Producto> getCartContent(String cartID) throws IOException, JSONException {
//        // the string is the result of our request.
//        String uri = "localhost:3000/changos/" + cartID + ".json";
//        String request = httpHelper.request(uri);
//
//        // Create a variable to hold our return data.
//        List<Producto> products = new ArrayList<Producto>();
//
//        // Parse the entire JSON String
//        JSONObject root = new JSONObject(request);
//        // get the array of plants from JSON
//        JSONArray tags = root.getJSONArray("tags");
//
//        for (int i = 0; i < tags.length(); i++) {
//
//            // parse the JSON object into its fields and values.
//            JSONObject jsonPlant = plants.getJSONObject(i);
//            int guid = jsonPlant.getInt("id");
//            String genus = jsonPlant.getString("genus");
//            String species = jsonPlant.getString("species");
//            String cultivar = jsonPlant.getString("cultivar");
//            String common = jsonPlant.getString("common");
//
//            // create a PLantDTO object that we will populate with JSON Data.
//            PlantDTO plant = new PlantDTO();
//            plant.setGuid(guid);
//            plant.setGenus(genus);
//            plant.setSpecies(species);
//            plant.setCultivar(cultivar);
//            plant.setCommon(common);
//
//            // add our newly created Plant DTO to our collection.
//            allPlants.add(plant);
//        }
//        // return our collection of planst.
//        return allPlants;
        return null;
    }
}
