/*
    Copyright © 2019, Christopher Alan Mosher, Shelton, CT, USA. <cmosher01@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY, without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package nu.mine.mosher.color;


import Jama.Matrix;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.Arrays;

import static nu.mine.mosher.color.SolarizedLab.*;



public class SolarizedStudy {
    public static void main(final String... args) throws FileNotFoundException {
        if (0 < args.length) {
            System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(args[0]))), true));
        }
        System.out.println("" +
            "<!doctype html>\n" +
            "<html>\n" +
            "<head>\n" +
            "<!-- generated "+ ZonedDateTime.now() +" -->\n" +
            "<link href=\"style.css\" rel=\"stylesheet\">" +
            "</head>\n" +
            "<body>\n" +
            "");





        // color wheel header
        System.out.println(div(BASE_3.toIrgb(D65_2D), BASE_00.toIrgb(D65_2D)));

        System.out.println("" +
            "<div class=\"wheel\">\n" +
            "");
        Arrays.stream(SolarizedLab.values()).filter(lab -> lab.tone == Tone.COLOR).forEach(lab -> System.out.println(div(lab.toIrgb(D65_2D))));
        System.out.println("" +
            "</div>" +
            "");
        System.out.println("" +
            "</div>" +
            "");

        System.out.println(div(BASE_3.toIrgb(D65_2D), BASE_00.toIrgb(D65_2D)));
        System.out.println("" +
            "<h1>Solarized</h1>\n" +
            "<div style=\"width:100%; text-align:right; padding-bottom:30px;\">" +
            "see <a href=\"https://ethanschoonover.com/solarized/\">https://ethanschoonover.com/solarized</a>&nbsp;&nbsp;" +
            "</div>" +
            "");

        System.out.println("" +
            "</div>" +
            "");





        // colors
        System.out.println("" +
            "<h2>colors</h2>\n" +
            "");

        System.out.println(div(BASE_3.toIrgb(D65_2D), BASE_00.toIrgb(D65_2D)));
        System.out.println("<pre>");
        System.out.println("XYZ and sRGB conversions (from L*a*b* with reference white-point of D65 @ 2 degrees)");
        System.out.println();
        System.out.println("SOLARIZED  L*   a*   b*  >     X       Y       Z    >     R        G        B     >  #RRGGBB  given");
        System.out.println("---------  ------------     ----------------------     -------------------------     ------- -------");
        Arrays.stream(SolarizedLab.values()).filter(lab -> lab.tone == Tone.COLOR).forEach(lab -> printColorComponentTableRow(lab, D65_2D));
        System.out.println("" +
            "</pre>\n" +
            "</div>\n" +
            "");
        System.out.println("The top row shows RGB values given on the Solarized web page.");
        System.out.println("The bottom row, for comparison, shows RGB values calculated dynamically from the canonical L*a*b* values.");
        System.out.println("" +
            "<div class=\"container\">\n" +
            "");
        Arrays.stream(SolarizedLab.values()).filter(lab -> lab.tone == Tone.COLOR).forEach(lab -> System.out.println(div(lab.original)));
        Arrays.stream(SolarizedLab.values()).filter(lab -> lab.tone == Tone.COLOR).forEach(lab -> System.out.println(div(lab.toIrgb(D65_2D))));
        System.out.println("" +
            "</div>" +
            "");





        // monotones
        System.out.println("" +
            "<h2>monotones</h2>\n" +
            "");

        System.out.println(div(BASE_3.toIrgb(D65_2D), BASE_00.toIrgb(D65_2D)));
        System.out.println("<pre>");
        System.out.println("XYZ and sRGB conversions (from L*a*b* with reference white-point of D65 @ 2 degrees)");
        System.out.println();
        System.out.println("SOLARIZED  L*   a*   b*  >     X       Y       Z    >     R        G        B     >  #RRGGBB  given");
        System.out.println("---------  ------------     ----------------------     -------------------------     ------- -------");
        Arrays.stream(SolarizedLab.values()).filter(lab -> lab.tone == Tone.MONOTONE).forEach(lab -> printColorComponentTableRow(lab, D65_2D));
        System.out.println("" +
            "</pre>\n" +
            "</div>\n" +
            "");

        System.out.println("The top row shows RGB values given on the Solarized web page.");
        System.out.println("The bottom row, for comparison, shows RGB values calculated dynamically from the canonical L*a*b* values.");

        System.out.println("" +
            "<div class=\"container\">\n" +
            "");
        Arrays.stream(SolarizedLab.values()).filter(lab -> lab.tone == Tone.MONOTONE).forEach(lab -> System.out.println(div(lab.original)));
        Arrays.stream(SolarizedLab.values()).filter(lab -> lab.tone == Tone.MONOTONE).forEach(lab -> System.out.println(div(lab.toIrgb(D65_2D))));
        System.out.println("" +
            "</div>" +
            "");





        // text
        System.out.println("" +
            "<h2>monotone combinations</h2>\n" +
            "");
        System.out.println(div(BASE_3.toIrgb(D65_2D), BASE_00.toIrgb(D65_2D)));
        System.out.println("<pre>");
        System.out.println("Monotone lightness deltas, (Lbg-Lfg)/5   (every combination)");
        System.out.print("   BASE_3  BASE_2  BASE_1  BASE_0  BASE_00 BASE_01 BASE_02 BASE_03");
        final double[] rL = new double[]{BASE_3.canonical.L, BASE_2.canonical.L, BASE_1.canonical.L, BASE_0.canonical.L, BASE_00.canonical.L, BASE_01.canonical.L, BASE_02.canonical.L, BASE_03.canonical.L};
        final Matrix A = new Matrix(new double[][]{rL,rL,rL,rL,rL,rL,rL,rL});
        final Matrix B = A.transpose().minus(A).times(1.0 /5.0);
        B.print(6,1);

        System.out.println("same as above, but only the sanctioned FG/BG combinations");
        final double[] bg = new double[]{BASE_3.canonical.L, BASE_2.canonical.L, BASE_02.canonical.L, BASE_03.canonical.L};
        final Matrix BG = new Matrix(new double[][]{bg,bg,bg,bg});
        final double[] fg = new double[]{BASE_1.canonical.L, BASE_0.canonical.L, BASE_00.canonical.L, BASE_01.canonical.L};
        final Matrix FG = new Matrix(new double[][]{fg,fg,fg,fg});
        BG.transpose().minus(FG).times(1.0/5.0).print(6,1);

        System.out.println("" +
            "</pre>\n" +
            "</div>\n" +
            "");

        System.out.println("" +
            "<div class=\"text\">\n" +
            "");
        final String msg = "<br>\n" +
            "Ðaém eafera wæs æfter cenned\n" +
            "geong in geardum þone god sende\n" +
            "folce tó frófre· fyrenðearfe ongeat·\n" +
            "þæt híe aér drugon aldorléase\n" +
            "lange hwíle· him þæs líffréä\n" +
            "wuldres wealdend woroldáre forgeaf:\n" +
            "Béowulf wæs bréme—blaéd wíde\nsprang—" +
            "Scyldes eafera Scedelandum in.\n";

        System.out.println(div(BASE_3.toIrgb(D65_2D), BASE_1.toIrgb(D65_2D))+"\nbase3:base1"+msg+"</div>");
        System.out.println(div(BASE_3.toIrgb(D65_2D), BASE_0.toIrgb(D65_2D))+"\nbase3:base0"+msg+"</div>");
        System.out.println(div(BASE_3.toIrgb(D65_2D), BASE_00.toIrgb(D65_2D))+"\nbase3:base00"+msg+"</div>");
        System.out.println(div(BASE_3.toIrgb(D65_2D), BASE_01.toIrgb(D65_2D))+"\nbase3:base01"+msg+"</div>");

        System.out.println(div(BASE_2.toIrgb(D65_2D), BASE_1.toIrgb(D65_2D))+"\nbase2:base1"+msg+"</div>");
        System.out.println(div(BASE_2.toIrgb(D65_2D), BASE_0.toIrgb(D65_2D))+"\nbase2:base0"+msg+"</div>");
        System.out.println(div(BASE_2.toIrgb(D65_2D), BASE_00.toIrgb(D65_2D))+"\nbase2:base00"+msg+"</div>");
        System.out.println(div(BASE_2.toIrgb(D65_2D), BASE_01.toIrgb(D65_2D))+"\nbase2:base01"+msg+"</div>");

        System.out.println(div(BASE_02.toIrgb(D65_2D), BASE_1.toIrgb(D65_2D))+"\nbase02:base1"+msg+"</div>");
        System.out.println(div(BASE_02.toIrgb(D65_2D), BASE_0.toIrgb(D65_2D))+"\nbase02:base0"+msg+"</div>");
        System.out.println(div(BASE_02.toIrgb(D65_2D), BASE_00.toIrgb(D65_2D))+"\nbase02:base00"+msg+"</div>");
        System.out.println(div(BASE_02.toIrgb(D65_2D), BASE_01.toIrgb(D65_2D))+"\nbase02:base01"+msg+"</div>");

        System.out.println(div(BASE_03.toIrgb(D65_2D), BASE_1.toIrgb(D65_2D))+"\nbase03:base1"+msg+"</div>");
        System.out.println(div(BASE_03.toIrgb(D65_2D), BASE_0.toIrgb(D65_2D))+"\nbase03:base0"+msg+"</div>");
        System.out.println(div(BASE_03.toIrgb(D65_2D), BASE_00.toIrgb(D65_2D))+"\nbase03:base00"+msg+"</div>");
        System.out.println(div(BASE_03.toIrgb(D65_2D), BASE_01.toIrgb(D65_2D))+"\nbase03:base01"+msg+"</div>");

        System.out.println("" +
            "</div>" +
            "");





        // css
        System.out.println("<p>example CSS usage</p>");

        System.out.println(div(BASE_3.toIrgb(D65_2D), BASE_00.toIrgb(D65_2D)));
        System.out.println("<pre>");

        System.out.println(":root {");
        Arrays.stream(SolarizedLab.values()).forEach(lab -> System.out.println(String.format(
            "    --sol-%s: %s;", lab.toString().toLowerCase().replaceAll("_",""), crgb(lab.toIrgb(D65_2D))
        )));
        System.out.println("}");
        System.out.println("body {");
        System.out.println("    color: var(--sol-base00);");
        System.out.println("    background-color: var(--sol-base3);");
        System.out.println("}");

        System.out.println("" +
            "</pre>\n" +
            "</div>\n" +
            "");





        System.out.println("" +
            "</body>\n" +
            "</html>\n" +
            "");

        System.out.flush();
        System.err.flush();
    }






    private static void printColorComponentTableRow(final SolarizedLab lab, final Matrix whitepoint) {
        System.out.print(String.format("%-9s", lab));

        // canonical L*a*b* values
        System.out.print(" ");
        System.out.print(String.format("%3d  %+03d  %+03d", lab.canonical.L, lab.canonical.a, lab.canonical.b));

        final XYZ xyz = lab.toXyz(whitepoint);
        System.out.print("     ");
        System.out.print(String.format("%06.4f  %06.4f  %06.4f", xyz.X, xyz.Y, xyz.Z));

        final sRGB rgb = lab.toSrgb(whitepoint);
        System.out.print("     ");
        System.out.print(String.format("%+07.4f  %+07.4f  %+07.4f  ", rgb.R, rgb.G, rgb.B));

        final iRGB irgb = lab.toIrgb(whitepoint);
        System.out.print("   ");
        System.out.print(String.format("#%02x%02x%02x", irgb.R, irgb.G, irgb.B));

        // Ethan's original RGB values
        System.out.print(" ");
        System.out.print(String.format("#%02x%02x%02x", lab.original.R, lab.original.G, lab.original.B));

        System.out.println();
    }

    private static String div(final iRGB bg) {
        return String.format("<div style=\"background-color:%s;\"></div>", crgb(bg));
    }

    private static String div(final iRGB bg, final iRGB fg) {
        return String.format("<div style=\"color:%s; background-color:%s;\">", crgb(fg), crgb(bg));
    }

    private static String crgb(final iRGB c) {
        return String.format("#%02x%02x%02x", c.R, c.G, c.B);
    }
}
