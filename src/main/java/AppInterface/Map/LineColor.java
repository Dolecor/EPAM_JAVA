package AppInterface.Map;

import java.awt.*;
import java.util.ArrayList;

public class LineColor {
    public static ArrayList<Color> lineColors;
    private static int alpha = 127;

    public LineColor(){
        lineColors = new ArrayList<Color>();
        lineColors.add(Color.RED);
        lineColors.add(new Color(2,153,85));
        lineColors.add(Color.BLUE);
        lineColors.add(new Color(1,158,224));
        lineColors.add(new Color(116,92,47));   //кольцевая
        lineColors.add(Color.ORANGE);
        lineColors.add(new Color(182,29,142));
        lineColors.add(Color.YELLOW);
        lineColors.add(Color.GRAY);
        lineColors.add(new Color(177,211,50));
        lineColors.add(new Color(91,190,187));
        lineColors.add(Color.CYAN);
        lineColors.add(new Color(255,168,175)); //МЦК
    }

    public static Color getColor(int colorID, boolean isVisible){
        if (colorID < 0 || colorID >= lineColors.size())
            return null;
        if (isVisible)
            return lineColors.get(colorID);
        else
            return new Color(
                    lineColors.get(colorID).getRed(),
                    lineColors.get(colorID).getGreen(),
                    lineColors.get(colorID).getBlue(),
                    alpha);
    }
}
