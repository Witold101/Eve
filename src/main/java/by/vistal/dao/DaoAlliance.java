package by.vistal.dao;

import by.vistal.dao.interfaces.InterfaceDao;
import by.vistal.entity.Alliance;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.ArrayList;
import java.util.List;

public class DaoAlliance extends ConfigReadServer implements InterfaceDao<Alliance> {

    public List<Alliance> getAll() {
        return null;
    }

    public Alliance getByName(String name) {
        return null;
    }

    public Alliance getById(Integer id) {
        return null;
    }

    public Boolean dellAll() {
        return false;
    }

    public Boolean dellByName(String name) {
        return false;
    }

    public Boolean dellById(Integer id) {
        return false;
    }

    public Boolean add(Alliance date) {
        return false;
    }

    public Boolean edit(Alliance date) {
        return false;
    }

    public List<Alliance> getAlliancesByEve() throws InterruptedException {
        JSONArray array = readJSONArray(ConstantEve.GET_ALLIANCES);
        List alliances = new ArrayList<Alliance>();
        for (int i = 0; i < array.size(); i++, Thread.sleep(10)) {
            Alliance alliance =getAllianceByEve(Integer.valueOf(array.toArray()[i].toString()));
            alliances.add(alliance);
        }
        return alliances;
    }

    public Alliance getAllianceByEve(Integer id){
        JSONObject ob = readJSONObject(ConstantEve.GET_ALLIANCE,id);
        Alliance alliance = new Alliance();
        alliance.setId(id);
        alliance.setCreatorCorporationId(Integer.valueOf(ob.get("creator_corporation_id").toString()));
        alliance.setCreatorId(Integer.valueOf(ob.get("creator_id").toString()));
        alliance.setDateFounded(ob.get("date_founded").toString());
        alliance.setExecutorCorporationId(Integer.valueOf(ob.get("executor_corporation_id").toString()));
        alliance.setName(ob.get("name").toString());
        alliance.setTicker(ob.get("ticker").toString());

        ob = readJSONObject(ConstantEve.GET_ALLIANCE_ICON,id);
        alliance.setIcon64(ob.get("px64x64").toString());
        alliance.setIcon128(ob.get("px128x128").toString());

        return alliance;
    }
}
