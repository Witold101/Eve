package by.vistal.db;

import static by.vistal.db.DbInitTable.*;

public class DbConstants {

    // -------------------------------------  EVE SYSTEM TABLE ----------------------------------------------------------
    public static final String MYSQL_ADD_SYSTEM = "INSERT INTO " + TABLE_NAME_SYSTEMS +
            " (`id`,`name`) VALUE (?,?)";
    public static final String MYSQL_GET_SYSTEM_BY_ID = "SELECT * FROM " + TABLE_NAME_SYSTEMS + " WHERE id=?;";
    public static final String MYSQL_GET_SYSTEM_BY_NAME = "SELECT * FROM " + TABLE_NAME_SYSTEMS + " WHERE name=?;";
//    public static final String MYSQL_DELL_PARTY = "DELETE FROM " + TABLE_NAME_PARTYS + " WHERE id=?;";
//    public static final String MYSQL_EDIT_PARTY = "UPDATE " + TABLE_NAME_PARTYS +
//            " SET name=?, note=? WHERE id=?;";

//----------------------------------------------------------------------------------------------------------------
// ------------------------------------- EVE MATERIAL TABLE ----------------------------------------------------------
    public static final String MYSQL_ADD_MATERIAL = "INSERT INTO " + TABLE_NAME_MATERIALS +
        " (`id`,`name`,`image`) VALUE (?,?,?)";
    public static final String MYSQL_GET_MATERIAL_BY_ID = "SELECT * FROM " + TABLE_NAME_MATERIALS + " WHERE id=?;";
    public static final String MYSQL_GET_MATERIAL_BY_NAME = "SELECT * FROM " + TABLE_NAME_MATERIALS + " WHERE name=?;";
    //public static final String MYSQL_DELL_USER = "DELETE FROM " + TABLE_NAME_USERS + " WHERE id=?;";
    public static final String MYSQL_EDIT_MATERIAL_BY_ID = "UPDATE " + TABLE_NAME_MATERIALS +
            " SET name=?, image=? WHERE id=?;";

//----------------------------------------------------------------------------------------------------------------
// ------------------------------------- EVE STATUS MATERIAL TABLE ----------------------------------------------------------
    public static final String MYSQL_ADD_STATUS_MATERIAL = "INSERT INTO " + TABLE_NAME_STATUS_MATERIALS +
    " (`date`,`id_material`, `id_system`, `price_max_bay`, `price_min_sell`) VALUE (?,?,?,?,?)";
//public static final String MYSQL_GET_MATERIAL_BY_ID = "SELECT * FROM " + TABLE_NAME_MATERIALS + " WHERE id=?;";
//    public static final String MYSQL_GET_MATERIAL_BY_NAME = "SELECT * FROM " + TABLE_NAME_MATERIALS + " WHERE name=?;";
    //public static final String MYSQL_DELL_USER = "DELETE FROM " + TABLE_NAME_USERS + " WHERE id=?;";
    //public static final String MYSQL_EDIT_MATERIAL_BY_ID = "UPDATE " + TABLE_NAME_MATERIALS +
    //        " SET name=?, image=? WHERE id=?;";

    public static final String MYSQL_GET_STATUS_MATERIAL_BY_ID_SYSTEMID =
            "SELECT * FROM "+TABLE_NAME_STATUS_MATERIALS+" WHERE id_material=? AND id_system=? AND date=?;";
//----------------------------------------------------------------------------------------------------------------
// ------------------------------------- EVE MATERIAL GROUP TABLE ----------------------------------------------------------
public static final String MYSQL_ADD_MATERIAL_GROUP = "INSERT INTO " + TABLE_NAME_MATERIALS_GROUP +
        " (`id`,`name`,`description`,`parent_group_id`) VALUE (?,?,?,?)";
//    public static final String MYSQL_GET_MATERIAL_BY_ID = "SELECT * FROM " + TABLE_NAME_MATERIALS + " WHERE id=?;";
//    public static final String MYSQL_GET_MATERIAL_BY_NAME = "SELECT * FROM " + TABLE_NAME_MATERIALS + " WHERE name=?;";
//    //public static final String MYSQL_DELL_USER = "DELETE FROM " + TABLE_NAME_USERS + " WHERE id=?;";
//    public static final String MYSQL_EDIT_MATERIAL_BY_ID = "UPDATE " + TABLE_NAME_MATERIALS +
//            " SET name=?, image=? WHERE id=?;";

//----------------------------------------------------------------------------------------------------------------

}
