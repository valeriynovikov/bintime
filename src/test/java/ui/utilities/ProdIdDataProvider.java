package ui.utilities;

import org.testng.annotations.DataProvider;

public class ProdIdDataProvider {
    @DataProvider(name = "prodId")
    public Object[][] prodId() {
        return new Object[][]{
                {"J153289"},
                {"MQ3D2ZD/A"},
                {"L36852-H2436-M101"},
                {"1WZ03EA#ABH"},
                {"875839-425"},
                {"C5J91A#B19"},
                {"FM32SD45B/10"},
                {"204446-101"},
                {"GV-N710D3-1GL"},
                {"02G-P4-6150-KR"}
        };
    }

}
