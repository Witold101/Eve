package by.vistal.entity;

public class Alliance {
    private Integer id;
    private String name;

    private Integer creatorCorporationId;
    private Integer creatorId;
    private String dateFounded;
    private Integer executorCorporationId;
    private Integer factionId;
    private String ticker;
    private String icon64;
    private String icon128;

    public String getIcon64() {
        return icon64;
    }

    public String getIcon128() {
        return icon128;
    }

    public void setIcon128(String icon128) {
        this.icon128 = icon128;
    }

    public void setIcon64(String icon64) {
        this.icon64 = icon64;
    }

    public Integer getCreatorCorporationId() {
        return creatorCorporationId;
    }

    public void setCreatorCorporationId(Integer creatorCorporationId) {
        this.creatorCorporationId = creatorCorporationId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getDateFounded() {
        return dateFounded;
    }

    public void setDateFounded(String dateFounded) {
        this.dateFounded = dateFounded;
    }

    public Integer getExecutorCorporationId() {
        return executorCorporationId;
    }

    public void setExecutorCorporationId(Integer executorCorporationId) {
        this.executorCorporationId = executorCorporationId;
    }

    public Integer getFactionId() {
        return factionId;
    }

    public void setFactionId(Integer factionId) {
        this.factionId = factionId;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
