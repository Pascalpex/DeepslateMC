package de.pascalpex.deepslatemc;

import org.galemc.gale.version.AbstractPaperVersionFetcher;

public class DeepslateVersionFetcher extends AbstractPaperVersionFetcher {
    public DeepslateVersionFetcher() {
        super(
            "https://deepslatemc.de/download/",
            "Pascalpex",
            "DeepslateMC",
            "Pascalpex",
            "DeepslateMC",
            ApiType.GITHUB
        );
    }
}
