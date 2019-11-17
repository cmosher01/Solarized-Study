package nu.mine.mosher.color;



import Jama.Matrix;

import java.util.Arrays;
import java.util.function.DoubleUnaryOperator;



public enum SolarizedLab {
    /*
    The values of the colors are held under this copyright by Ethan Schoonover:

    Copyright © 2011 Ethan Schoonover

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
    */
    BASE_03(new Lab(15, -12, -12), new iRGB(0, 43, 54), Tone.MONOTONE),
    BASE_02(new Lab(20, -12, -12), new iRGB( 7, 54, 66), Tone.MONOTONE),
    BASE_01(new Lab(45, -7, -7), new iRGB( 88, 110, 117), Tone.MONOTONE),
    BASE_00(new Lab(50, -7, -7), new iRGB( 101, 123, 131), Tone.MONOTONE),

    BASE_0(new Lab(60, -6, -3), new iRGB( 131, 148, 150), Tone.MONOTONE),
    BASE_1(new Lab(65, -5, -2), new iRGB( 147, 161, 161), Tone.MONOTONE),
    BASE_2(new Lab(92, 0, 10), new iRGB( 238, 232, 213), Tone.MONOTONE),
    BASE_3(new Lab(97, 0, 10), new iRGB( 253, 246, 227), Tone.MONOTONE),



    YELLOW(new Lab(60, 10, 65), new iRGB( 181, 137, 0), Tone.COLOR),
    ORANGE(new Lab(50, 50, 55), new iRGB( 203, 75, 22), Tone.COLOR),
    RED(new Lab(50, 65, 45), new iRGB( 220, 50, 47), Tone.COLOR),
    MAGENTA(new Lab(50, 65, -5), new iRGB( 211, 54, 130), Tone.COLOR),

    VIOLET(new Lab(50, 15, -45), new iRGB( 108, 113, 196), Tone.COLOR),
    BLUE(new Lab(55, -10, -45), new iRGB( 38, 139, 210), Tone.COLOR),
    CYAN(new Lab(60, -35, -5), new iRGB( 42, 161, 152), Tone.COLOR),
    GREEN(new Lab(60, -20, 65), new iRGB( 133, 153, 0), Tone.COLOR);



    /*
        The remainder of this file's copyright is held by Christopher Alan Mosher:

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

    // CIE Standard Illuminants, from https://en.wikipedia.org/wiki/Standard_illuminant
    public static final Matrix D50_10D = new Matrix(new double[][] {{0.34773}, {0.35952}}); // @ 10 degrees
    public static final Matrix D50_2D = new Matrix(new double[][] {{0.34567}, {0.35850}}); // @ 2 degrees
    public static final Matrix D55_10D = new Matrix(new double[][] {{0.33411}, {0.34877}}); // @ 10 degrees
    public static final Matrix D55_2D = new Matrix(new double[][] {{0.33242}, {0.34743}}); // @ 2 degrees
    public static final Matrix D65_10D = new Matrix(new double[][] {{0.31382}, {0.33100}}); // @ 10 degrees
    public static final Matrix D65_2D = new Matrix(new double[][] {{0.31271}, {0.32902}}); // @ 2 degrees *********
    public static final Matrix D75_10D = new Matrix(new double[][] {{0.29968}, {0.31740}}); // @ 10 degrees
    public static final Matrix D75_2D = new Matrix(new double[][] {{0.29902}, {0.31485}}); // @ 2 degrees
    public static final Matrix F5_10D = new Matrix(new double[][] {{0.31975}, {0.34246}}); // @ 10 degrees
    public static final Matrix F5_2D = new Matrix(new double[][] {{0.31379}, {0.34531}}); // @ 2 degrees
    public static final Matrix F1_10D = new Matrix(new double[][] {{0.31811}, {0.33559}}); // @ 10 degrees
    public static final Matrix F1_2D = new Matrix(new double[][] {{0.31310}, {0.33727}}); // @ 2 degrees

    // see Chromaticity table here: https://en.wikipedia.org/wiki/SRGB
    // "sRGB standard": IEC 61966-2-1:1999 https://webstore.iec.ch/publication/6169
    public static final Matrix XY_R = new Matrix(new double[][] {{0.6400}, {0.3300}});
    public static final Matrix XY_G = new Matrix(new double[][] {{0.3000},{0.6000}});
    public static final Matrix XY_B = new Matrix(new double[][] {{0.1500}, {0.0600}});



    public enum Tone {
        MONOTONE, COLOR;
    }

    // color data structures

    public static class Lab {
        public final int L; // [0,+100]
        public final int a; // [-100,+100]
        public final int b; // [-100,+100]
        public Lab(int vL, int va, int vb) {L=vL; a=va; b=vb;}
    }

    public static class XYZ {
        public final double X; // [0,1]
        public final double Y; // [0,1]
        public final double Z; // [0,1]
        public XYZ(double x, double y, double z) {X=x; Y=y; Z=z;}
    }

    public static class sRGB {
        // values nominally in range [0,1]
        // but can extend beyond
        public final double R;
        public final double G;
        public final double B;
        public sRGB(double r, double g, double b) {R=r; G=g; B=b;}
    }

    public static class iRGB {
        // [0,256)
        public final int R;
        public final int G;
        public final int B;
        public iRGB(int r, int g, int b) {R=r; G=g; B=b;}
    }





    // "canonical" L*a*b* Solarized colors
    public final Lab canonical;
    // original RGB provided by Ethan
    public final iRGB original;
    public final Tone tone;





    SolarizedLab(final Lab canonical, final iRGB given, final Tone tone) {
        this.canonical = canonical;
        this.original = given;
        this.tone = tone;
    }


    public XYZ toXyz(final Matrix whitepoint) {
        final Matrix xyz = CIELab2CIEXYZ(whitepoint, lab());
        final double[] rxyz = Arrays.stream(xyz.getColumnPackedCopy()).toArray();
        return new XYZ(rxyz[0], rxyz[1],rxyz[2]);
    }

    public sRGB toSrgb(final Matrix whitepoint) {
        final Matrix xyz = CIELab2CIEXYZ(whitepoint, lab());
        final Matrix rgb = matApply(SolarizedLab::gamma, matrixXyzToRgb(whitepoint).times(xyz));
        final double[] rrgb = Arrays.stream(rgb.getColumnPackedCopy()).toArray();
        return new sRGB(rrgb[0],rrgb[1],rrgb[2]);
    }

    public iRGB toIrgb(final Matrix whitepoint) {
        final Matrix xyz = CIELab2CIEXYZ(whitepoint, lab());
        final Matrix rgb = matApply(SolarizedLab::gamma, matrixXyzToRgb(whitepoint).times(xyz));
        final Matrix rounded = matApply(SolarizedLab::rint,rgb);
        final int[] c = Arrays.stream(rounded.getColumnPackedCopy()).mapToInt(SolarizedLab::clip).toArray();
        return new iRGB(c[0],c[1],c[2]);
    }




    private Matrix lab() {
        return new Matrix(new double[][]{{1.0*canonical.L},{1.0*canonical.a},{1.0*canonical.b}});
    }

    private static Matrix matrixXyzToRgb(final Matrix Wxy) {
        final Matrix Rxyz = calcz(XY_R);
        final Matrix Gxyz = calcz(XY_G);
        final Matrix Bxyz = calcz(XY_B);
        final Matrix Wxyz = calcz(Wxy);

        Wxyz.timesEquals(1.0/Wxy.get(1,0));

        final Matrix N = new Matrix(3,3);
        N.setMatrix(0,2,0,0, Rxyz);
        N.setMatrix(0,2,1,1, Gxyz);
        N.setMatrix(0,2,2,2, Bxyz);

        final Matrix Sv = N.inverse().times(Wxyz);
        final Matrix S = Sv.times(new Matrix(1, 3, 1.0)).arrayTimes(Matrix.identity(3,3));

        return N.times(S).inverse();
    }



    // see https://colorcalculations.wordpress.com/xyz-to-lab/#Lab2XYZ
    private static Matrix CIELab2CIEXYZ(final Matrix Wxy, final Matrix Lab) {
        final Matrix Wxyz = calcz(Wxy);
        Wxyz.timesEquals(1.0/Wxyz.get(1,0));
        final Matrix fym = new Matrix(3, 1, (Lab.get(0,0) + 16.0) / 116.0);
        final Matrix d = new Matrix(new double[][]{{Lab.get(1,0)/500.0}, {0.0}, {-Lab.get(2,0)/200.0}});
        return Wxyz.arrayTimes(matApply(SolarizedLab::xyzr, fym.plus(d)));
    }



    private static Matrix calcz(final Matrix rxy) {
        Matrix x = new Matrix(3, 1);
        x.setMatrix(0, 1, 0, 0, rxy);
        x.set(2,0,1.0-(rxy.get(0,0)+ rxy.get(1,0)));
        return x;
    }

    private static Matrix matApply(final DoubleUnaryOperator fn, final Matrix A) {
        return new Matrix(Arrays.stream(A.getColumnPackedCopy()).map(fn).toArray(), A.getRowDimension());
    }

    private static double xyzr(double v) {
        final double KE = 6.0 / 29.0;
        double r;
        if (KE < v) {
            r = v*v*v;
        } else {
            r = 3*KE*KE * (v - 4.0/29.0);
        }
        return r;
    }

    private static double gamma(final double v) {
        final double r;
        if (0.003131 < v) {
            r = 1.055 * Math.pow(v, 1.0/2.4) - 0.05500;
        } else {
            r = 12.92 * v;
        }
        return r;
    }

    private static int clip(double v) {
        final long b = Math.round(v);
        return (int)(b < 0 ? 0 : 255 < b ? 255 : b);
    }

    private static double rint(double v) {
        // This is the normal method of converting float [0,1] to int [0,256), which I think is wrong:
        //        return (int)(v*255f+0.5f);

        // This, I believe, is the correct process:
        return Math.rint(v * 256.0d);

        // Also of note is that the component values coming out of the
        // conversion equations do go outside the [0,1] range.
        // so after this rint conversion, you need to clip to [0,256)
    }}
