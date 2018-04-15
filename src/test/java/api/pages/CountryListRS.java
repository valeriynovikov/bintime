package api.pages;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Data;

import java.util.List;

@Data
public class CountryListRS {
    private List<CountryRS> countryList;

    @JsonCreator
    public CountryListRS(List<CountryRS> countryList) {
        this.countryList = countryList;
    }
}
