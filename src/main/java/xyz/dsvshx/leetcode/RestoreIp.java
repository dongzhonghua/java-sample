package xyz.dsvshx.leetcode;


public class RestoreIp {

    public float calculateDistance(float[] point, float[][] triangle) {
        return solution(point[0], point[1], triangle[0][0], triangle[0][1], triangle[1][01], triangle[1][1],
                triangle[2][0], triangle[2][1]);
    }

    public static float solution(float x0, float y0, float x1, float y1, float x2, float y2, float x3, float y3) {
        float d1 = pointToLine(x0, y0, x1, y1, x2, y2);
        float d2 = pointToLine(x0, y0, x1, y1, x3, y3);
        float d3 = pointToLine(x0, y0, x2, y2, x3, y3);
        float min = Math.min(d1, d2);
        min = Math.min(min, d3);
        return min;
    }

    public static float pointToLine(float x1, float y1, float x2, float y2, float x0, float y0) {
        float space = 0;
        float a, b, c;
        a = lineSpace(x1, y1, x2, y2);
        b = lineSpace(x1, y1, x0, y0);
        c = lineSpace(x2, y2, x0, y0);
        if (c + b == a) {
            space = 0;
            return space;
        }
        if (a <= 0.000001) {
            space = b;
            return space;
        }
        if (c * c >= a * a + b * b) {
            space = b;
            return space;
        }
        if (b * b >= a * a + c * c) {
            space = c;
            return space;
        }
        float p = (a + b + c) / 2;
        float s = (float) Math.sqrt(p * (p - a) * (p - b) * (p - c));
        space = 2 * s / a;
        return space;
    }

    public static float lineSpace(float x1, float y1, float x2, float y2) {
        float lineLength = 0;
        lineLength = (float) Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
        return lineLength;
    }



}

