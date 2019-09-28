package by.vistal.dao;

import by.vistal.db.DbConnection;
import by.vistal.entity.Alliance;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.crypto.dsig.XMLObject;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ConfigReadServer {

    private Connection connection;

    public ConfigReadServer() {
        setConnection(DbConnection.getConnection());
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public JSONArray readJSONArray(String url) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri(url).build());
        JSONArray array = (JSONArray) JSONValue.parse(service.accept(MediaType.APPLICATION_JSON)
                .get(String.class));
        return array;
    }

    public JSONObject readJSONObject(String url) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        WebResource service = client.resource(UriBuilder.fromUri(url).build());
        JSONObject ob = (JSONObject) JSONValue.parse(service.accept(MediaType.APPLICATION_JSON)
                .get(String.class));
        return ob;
    }

    public JSONObject readJSONObject(String url, Integer id)  {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        url = String.format(url, id);
        WebResource service = null;
        try {
            service = client.resource(UriBuilder.fromUri(url).build());
        }catch (UniformInterfaceException e){
            System.out.println("Status - "+service.head().getStatus());
        }
        JSONObject ob = (JSONObject) JSONValue.parse(service.accept(MediaType.APPLICATION_JSON)
                .get(String.class));
        return ob;
    }

    public JSONObject readJSONObject(String url, Integer system, Integer id) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);
        url = String.format(url,system,id);
        WebResource service = client.resource(UriBuilder.fromUri(url).build());
        XMLObject ob = (XMLObject) JSONValue.parse(service.accept(MediaType.APPLICATION_XML)
                .get(String.class));
        return null;
    }
}
