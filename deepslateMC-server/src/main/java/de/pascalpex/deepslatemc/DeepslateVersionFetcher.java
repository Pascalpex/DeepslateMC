package de.pascalpex.deepslatemc;

import org.galemc.gale.version.AbstractPaperVersionFetcher;

public class DeepslateVersionFetcher extends AbstractPaperVersionFetcher {
    public DeepslateVersionFetcher() {
        super(
            "ver/1.21.5",
            "https://pascalpex.de/deepslate/",
            "Pascalpex",
            "DeepslateMC",
            "Pascalpex",
            "DeepslateMC"
        );
    }
}
