package by.vistal.dao;

public class ConstantEve {
    public static final String GET_ALLIANCES =
            "https://esi.evetech.net/latest/alliances/?datasource=tranquility";
    public static final String GET_ALLIANCE =
            "https://esi.evetech.net/latest/alliances/%d/?datasource=tranquility";
    public static final String GET_ALLIANCE_ICON =
            "https://esi.evetech.net/latest/alliances/%d/icons/?datasource=tranquility";
    public static final String GET_SYSTEMS =
            "https://esi.evetech.net/latest/universe/systems/?datasource=tranquility";
    public static final String GET_SYSTEM =
            "https://esi.evetech.net/latest/universe/systems/%d/?datasource=tranquility&language=en-us";
    public static final String GET_MATERIAL_BY_SYSTEM =
            "https://api.evemarketer.com/ec/marketstat?usesystem=%d&typeid=%d";
    public static final String GET_STATUS_EVE =
            "https://esi.evetech.net/latest/status/?datasource=tranquility";
    public static final String GET_FILE_EVE_ITEMS =
            "https://www.fuzzwork.co.uk/resources/typeids.csv";
    public static final String GET_EVE_ITEMS_GROUP =
            "https://esi.evetech.net/latest/markets/groups/?datasource=tranquility";
    public static final String GET_EVE_ITEMS_GROUP_BY_ID =
            "https://esi.evetech.net/latest/markets/groups/%d/?datasource=tranquility&language=en-us";

}
