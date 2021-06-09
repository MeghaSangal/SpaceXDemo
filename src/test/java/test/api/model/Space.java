package test.api.model;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "static_fire_date_utc",
        "static_fire_date_unix",
        "tbd",
        "net",
        "success",
        "details",
        "ships",
        "launchpad",
        "failures",
        "flight_number",
        "name",
        "date_utc",
        "date_unix",
        "date_local",
        "date_precision"
})

@Generated("jsonschema2pojo")
public class Space {

    @JsonProperty("static_fire_date_utc")
    private String staticFireDateUtc;
    @JsonProperty("static_fire_date_unix")
    private Integer staticFireDateUnix;
    @JsonProperty("tbd")
    private Boolean tbd;
    @JsonProperty("net")
    private Boolean net;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("details")
    private String details;
    @JsonProperty("ships")
    private List<String> ships = null;
    @JsonProperty("launchpad")
    private String launchpad;
    @JsonProperty("failures")
    private List<Object> failures = null;
    @JsonProperty("flight_number")
    private Integer flightNumber;
    @JsonProperty("name")
    private String name;
    @JsonProperty("date_utc")
    private String dateUtc;
    @JsonProperty("date_unix")
    private Integer dateUnix;
    @JsonProperty("date_local")
    private String dateLocal;
    @JsonProperty("date_precision")
    private String datePrecision;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("static_fire_date_utc")
    public String getStaticFireDateUtc() {
        return staticFireDateUtc;
    }

    @JsonProperty("static_fire_date_utc")
    public void setStaticFireDateUtc(String staticFireDateUtc) {
        this.staticFireDateUtc = staticFireDateUtc;
    }

    @JsonProperty("static_fire_date_unix")
    public Integer getStaticFireDateUnix() {
        return staticFireDateUnix;
    }

    @JsonProperty("static_fire_date_unix")
    public void setStaticFireDateUnix(Integer staticFireDateUnix) {
        this.staticFireDateUnix = staticFireDateUnix;
    }

    @JsonProperty("tbd")
    public Boolean getTbd() {
        return tbd;
    }

    @JsonProperty("tbd")
    public void setTbd(Boolean tbd) {
        this.tbd = tbd;
    }

    @JsonProperty("net")
    public Boolean getNet() {
        return net;
    }

    @JsonProperty("net")
    public void setNet(Boolean net) {
        this.net = net;
    }

    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonProperty("details")
    public String getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(String details) {
        this.details = details;
    }

    @JsonProperty("ships")
    public List<String> getShips() {
        return ships;
    }

    @JsonProperty("ships")
    public void setShips(List<String> ships) {
        this.ships = ships;
    }

    @JsonProperty("launchpad")
    public String getLaunchpad() {
        return launchpad;
    }

    @JsonProperty("launchpad")
    public void setLaunchpad(String launchpad) {
        this.launchpad = launchpad;
    }

    @JsonProperty("failures")
    public List<Object> getFailures() {
        return failures;
    }

    @JsonProperty("failures")
    public void setFailures(List<Object> failures) {
        this.failures = failures;
    }

    @JsonProperty("flight_number")
    public Integer getFlightNumber() {
        return flightNumber;
    }

    @JsonProperty("flight_number")
    public void setFlightNumber(Integer flightNumber) {
        this.flightNumber = flightNumber;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("date_utc")
    public String getDateUtc() {
        return dateUtc;
    }

    @JsonProperty("date_utc")
    public void setDateUtc(String dateUtc) {
        this.dateUtc = dateUtc;
    }

    @JsonProperty("date_unix")
    public Integer getDateUnix() {
        return dateUnix;
    }

    @JsonProperty("date_unix")
    public void setDateUnix(Integer dateUnix) {
        this.dateUnix = dateUnix;
    }

    @JsonProperty("date_local")
    public String getDateLocal() {
        return dateLocal;
    }

    @JsonProperty("date_local")
    public void setDateLocal(String dateLocal) {
        this.dateLocal = dateLocal;
    }

    @JsonProperty("date_precision")
    public String getDatePrecision() {
        return datePrecision;
    }

    @JsonProperty("date_precision")
    public void setDatePrecision(String datePrecision) {
        this.datePrecision = datePrecision;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
