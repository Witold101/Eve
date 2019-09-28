package by.vistal.services.intervaces;

import java.sql.SQLException;

public interface DaoServiceImpl<Key,Entity> {
    Boolean add(Entity entity);
    void dell (Key id);
    Entity edit(Entity entity);
    Entity get (Key id);
}
