package com.kurt.brs.utility;

import java.awt.BorderLayout;

public class BorderLayoutPositionFactory {
    public static String create(String type) {
        if (type != null) {
            if (type.equalsIgnoreCase("north")) {
                return BorderLayout.NORTH;
            } else if (type.equalsIgnoreCase("east")) {
                return BorderLayout.EAST;
            } else if (type.equalsIgnoreCase("south")) {
                return BorderLayout.SOUTH;
            } else if (type.equalsIgnoreCase("west")) {
                return BorderLayout.WEST;
            } else if (type.equalsIgnoreCase("center")) {
                return BorderLayout.CENTER;
            }
        }
        return null;
    }
}

