package by.vistal.db;

public class DbInitTable {
    public static final String TABLE_NAME_ALLIANCES = "eve_alliances";
    public static final String TABLE_NAME_SYSTEMS = "eve_systems";
    public static final String TABLE_NAME_MATERIALS = "eve_material";
    public static final String TABLE_NAME_STATUS_MATERIALS = "eve_status_material";
    public static final String TABLE_NAME_MATERIALS_GROUP = "eve_material_group";
    public static final String TABLE_NAME_BLUE_PRINTS = "eve_blue_prints";
    public static final String TABLE_NAME_BLUE_PRINT_MATERIAL = "eve_blue_print_material";

    public static final Integer MAX_NAME = 100;
    public static final Integer MAX_STRING = 250;

    public static final String MYSQL_INIT_ALLIANCES_TABLE = "CREATE TABLE IF NOT EXISTS `"+TABLE_NAME_ALLIANCES+
            "` (`id` INT(11) NOT NULL AUTO_INCREMENT,`name` VARCHAR("+MAX_NAME+") NOT NULL, PRIMARY KEY (`id`)) " +
            "ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";

    public static final String MYSQL_INIT_REGION_TABLE = "CREATE TABLE IF NOT EXISTS `"+TABLE_NAME_SYSTEMS+
            "` (`id` INT(11) NOT NULL ,`name` VARCHAR("+MAX_NAME+") NOT NULL, PRIMARY KEY (`id`)) " +
            "ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";

    public static final String MYSQL_INIT_MATERIALS_TABLE = "CREATE TABLE IF NOT EXISTS `"+TABLE_NAME_MATERIALS+
            "` (`id` INT(11) NOT NULL,`name` VARCHAR("+MAX_NAME+") NOT NULL" +
            ", `image` VARCHAR("+MAX_STRING+") NULL DEFAULT NULL,`parent_id` int(11) not null" +
            " default '0',PRIMARY KEY (`id`)) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";

    public static final String MYSQL_INIT_STATUS_MATERIAL_TABLE = "CREATE TABLE IF NOT EXISTS `"
            +TABLE_NAME_STATUS_MATERIALS+"` (`id` INT NOT NULL AUTO_INCREMENT, `date` DATETIME NOT NULL,"+
            "`id_material` INT NOT NULL,`id_system` INT NOT NULL,`price_max_bay` INT NULL,`price_min_sell` INT NULL,"+
            " PRIMARY KEY (`id`), CONSTRAINT `status_material_material`  FOREIGN KEY (`id_material`)"+
            " REFERENCES `"+TABLE_NAME_MATERIALS+"` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION, CONSTRAINT "+
            "`status_material_system` FOREIGN KEY (`id_system`) REFERENCES `"+TABLE_NAME_SYSTEMS+"` (`id`) "+
            " ON DELETE NO ACTION  ON UPDATE NO ACTION);";

    public static final String MYSQL_INIT_MATERIALS_GROUP_TABLE = "CREATE TABLE IF NOT EXISTS `"+TABLE_NAME_MATERIALS_GROUP+
            "` (`id` INT(11) NOT NULL ,`name` VARCHAR("+MAX_NAME+") NOT NULL, `description` VARCHAR("+MAX_STRING+") " +
            "NOT NULL, `parent_group_id` INT NULL, PRIMARY KEY (`id`)) " +
            "ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;";

    public static final String MYSQL_INIT_BLUE_PRINT_TABLE = "CREATE TABLE IF NOT EXISTS `"+TABLE_NAME_BLUE_PRINTS+
            "` (`id` INT NOT NULL AUTO_INCREMENT, `material_id` INT NOT NULL,`economy_materials` INT NOT NULL DEFAULT 0,"+
             "`economy_time` INT NOT NULL DEFAULT 0,`max_runs` INT NOT NULL DEFAULT -1, `quantity_res` INT NOT NULL " +
            " DEFAULT 1, PRIMARY KEY (`id`), CONSTRAINT `blue_print_material` FOREIGN KEY (`material_id`) REFERENCES `" +
            TABLE_NAME_MATERIALS +"` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION);";

    public static final String MYSQL_INIT_BLUE_PRINT_MATERIAL_TABLE ="CREATE TABLE IF NOT EXISTS `"+
            TABLE_NAME_BLUE_PRINT_MATERIAL+ "` (`id` INT NOT NULL AUTO_INCREMENT, `material_id` INT NOT NULL,"+
            "`quantity` INT NOT NULL DEFAULT 0,`blue_print_id` INT NOT NULL, PRIMARY KEY (`id`), CONSTRAINT `bp_material`"+
            " FOREIGN KEY (`material_id`) REFERENCES `"+TABLE_NAME_MATERIALS+"` (`id`) ON DELETE NO ACTION"+
            " ON UPDATE NO ACTION, CONSTRAINT `bp_bp` FOREIGN KEY (`blue_print_id`) REFERENCES `"+
            TABLE_NAME_BLUE_PRINTS+ "` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION);";
}
