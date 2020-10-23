package ca.sitkalabs.radar.Models.store;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppDetails {
    private String appDeveloper;
    private String appName;
    private String packageName;
    private int versionNumber;
    private String version;
}
