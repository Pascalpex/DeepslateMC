package de.pascalpex.deepslatemc.util;

public class StartupLogo {

    private static final String ASCII_LOGO = """
            ██████╗ ███████╗███████╗██████╗ ███████╗██╗      █████╗ ████████╗███████╗███╗   ███╗ ██████╗
            ██╔══██╗██╔════╝██╔════╝██╔══██╗██╔════╝██║     ██╔══██╗╚══██╔══╝██╔════╝████╗ ████║██╔════╝
            ██║  ██║█████╗  █████╗  ██████╔╝███████╗██║     ███████║   ██║   █████╗  ██╔████╔██║██║     
            ██║  ██║██╔══╝  ██╔══╝  ██╔═══╝ ╚════██║██║     ██╔══██║   ██║   ██╔══╝  ██║╚██╔╝██║██║     
            ██████╔╝███████╗███████╗██║     ███████║███████╗██║  ██║   ██║   ███████╗██║ ╚═╝ ██║╚██████╗
            ╚═════╝ ╚══════╝╚══════╝╚═╝     ╚══════╝╚══════╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝     ╚═╝ ╚═════╝
            """;

    private static void printWithGradient(String text) {
        boolean useTrueColor = supportsTrueColor();
        String[] lines = text.split("\n");
        int maxLength = 0;

        for (String line : lines) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }

        int startR = 0, startG = 255, startB = 200;
        int endR = 0, endG = 40, endB = 255;

        System.out.println();

        for (String line : lines) {
            if (line.trim().isEmpty()) {
                continue;
            }

            StringBuilder gradientLine = new StringBuilder();

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);

                if (c == ' ' || c == '\u00A0') {
                    gradientLine.append(c);
                    continue;
                }

                double ratio = (double) i / (maxLength - 1);
                int r = (int) (startR + ratio * (endR - startR));
                int g = (int) (startG + ratio * (endG - startG));
                int b = (int) (startB + ratio * (endB - startB));

                if (useTrueColor) {
                    // True Color (24-Bit)
                    gradientLine.append(String.format("\033[38;2;%d;%d;%dm%c", r, g, b, c));
                } else {
                    // Fallback (256 colors)
                    gradientLine.append(String.format("\033[38;5;%dm%c", rgbToAnsi256(r, g, b), c));
                }
            }

            gradientLine.append("\033[0m");
            System.out.println(gradientLine);
        }
    }

    private static int rgbToAnsi256(int r, int g, int b) {
        int rIdx = (int) Math.round(r / 255.0 * 5.0);
        int gIdx = (int) Math.round(g / 255.0 * 5.0);
        int bIdx = (int) Math.round(b / 255.0 * 5.0);
        return 16 + (36 * rIdx) + (6 * gIdx) + bIdx;
    }

    private static boolean supportsTrueColor() {
        String term = System.getenv("TERM");
        if (term != null) {
            String t = term.toLowerCase();
            if (t.startsWith("screen") || t.startsWith("tmux") || t.equals("linux") || t.equals("dumb")) {
                return false;
            }
        }

        // Most modern terminals should support true color
        return true;
    }

    public static void printLogo() {
        printWithGradient(ASCII_LOGO);
        System.out.print("\n");
    }

}
