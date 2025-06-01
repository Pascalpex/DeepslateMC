package de.pascalpex.deepslatemc;

import org.galemc.gale.version.AbstractPaperVersionFetcher;

public class DeepslateVersionFetcher extends AbstractPaperVersionFetcher {
    public DeepslateVersionFetcher() {
        super(
            "https://pascalpex.de/deepslate/",
            "Pascalpex",
            "DeepslateMC",
            "Pascalpex",
            "DeepslateMC"
        );
    }
}
