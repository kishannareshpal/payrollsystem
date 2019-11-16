package com.kishannareshpal.utils;

import java.util.*;
import java.util.List;


class Row {
    private String title, description;
    private boolean isSeparator;

    public Row(boolean isSeparator) {
        this.isSeparator = isSeparator;
    }

    public Row(String title, String description) {
        this.isSeparator = false;
        this.title = title;
        this.description = description;
    }

    public boolean isSeparator() {
        return isSeparator;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
class Dimension {
    // Unit: Chars
    private int titleWidth, descWidth;
    private int width; // full width;
    private int height; // full height;

    public Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Dimension(int titleWidth, int descWidth, int height) {
        this.width = titleWidth + descWidth;
        this.titleWidth = titleWidth;
        this.descWidth = descWidth;
        this.height = height;
    }

    public int getTitleWidth() {
        return titleWidth;
    }

    public int getDescWidth() {
        return descWidth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}

public class VerticalComandLineTable {
    // Statics
    private static String WHITESPACE = " ";
    private static String TABLE_UI_HORIZONTAL_SEP = "-";
    private static String TABLE_UI_CORNER = "+";
    private static String TABLE_UI_VERTICAL_SEP = "|";

    // Variables
    List<Row> rowsList;
    int gap = 3; // used to add some whitespace at the end of the description column on every row before closing the table with TABLE_UI_HORIZONTAL_SEP.

    // Constructor
    public VerticalComandLineTable() {
        this.rowsList = new ArrayList<>();
    }

    // Methods
    public VerticalComandLineTable addRow(String title, String description) {
        Row row = new Row(title, description);
        rowsList.add(row);
        return this;
    }

    public VerticalComandLineTable addSeparator() {
        Row row = new Row(true);
        rowsList.add(row);
        return this;
    }

    public VerticalComandLineTable show() {
        Dimension tableDimen = getTableDimensions();
        int tableWidth = tableDimen.getWidth();
        String table_ui_line = TABLE_UI_CORNER + repeater(tableWidth - 2, TABLE_UI_HORIZONTAL_SEP) + TABLE_UI_CORNER; // -2 vertical seps: at the start and at the end of the row, to be replaced with the TABLE_UI_CORNER.

        Dimension rawDimen = calculateTableDimension();
        int titleWidth = rawDimen.getTitleWidth();
        int descWidth  = rawDimen.getDescWidth();

        System.out.println(table_ui_line);
        for (Row row: rowsList) {
            // print the row
            if (row.isSeparator()) {
                System.out.println(table_ui_line);
                continue;
            }

            String title = row.getTitle();
            String desc  = row.getDescription();

            String titleCol = " " + title + repeater((titleWidth) - title.length(), WHITESPACE) + " ";
            String descCol  = " " + desc + repeater((descWidth+gap) - desc.length(), WHITESPACE) + " ";
            String theRow = TABLE_UI_VERTICAL_SEP + titleCol + TABLE_UI_VERTICAL_SEP + descCol + TABLE_UI_VERTICAL_SEP;
            System.out.println(theRow);
        }
        System.out.println(table_ui_line);
        return this;
    }

    /**
     * Returns the final table dimens (eif
     *
     * @return the dimensions of the final table.
     */
    public Dimension getTableDimensions() {
        Dimension rawDimen = calculateTableDimension();
        int titleColWidth = 1 + rawDimen.getTitleWidth() + 1; // +1 whitespaces, at the start and in the end of the column. ignores the vertical sep.
        int descColWidth  = 1 + rawDimen.getDescWidth() + gap + 1; // +1 whitespaces: at the start at in the end; +gap whitespace: at the end of the desc column. ignores the vertical sep.

        int width = 1 + titleColWidth + 1 + descColWidth + 1; // +1 vertical seps: at the end, at the middle, and at the end of the row.
        int height = rowsList.size();

        return new Dimension(width, height);
    }


    /**
     * ONLY TO BE USED INTERNALLY, FOR CREATING ROWS.
     * Calculates the raw Dimension of the table's columns width, and its height.
     * Unit: Chars.
     *
     * @return
     */
    private Dimension calculateTableDimension() {
        int titleWidth = 0;
        int descWidth = 0;

        for (Row row: rowsList) {
            if (row.isSeparator()) continue;

            int newTitleWidth = row.getTitle().length();
            int newDescWidth  = row.getDescription().length();

            if (newTitleWidth > titleWidth) {
                // if this title has more chars than the last maximum, update the number of chars.
                titleWidth = newTitleWidth;
            }

            if (newDescWidth > descWidth) {
                // if this title has more chars than the last maximum, update the number of chars.
                descWidth = newDescWidth;
            }
        }

        int height = rowsList.size();
        return new Dimension(titleWidth, descWidth, height);
    }

    /**
     * Generates n number of String.
     *
     * @param numOfRepetitions number of times to repeat
     * @param strToRepeat the string to be repeated
     * @return repeated string.
     */
    private static String repeater(int numOfRepetitions, String strToRepeat) {
        StringBuilder chars = new StringBuilder();
        for (int i = 0; i < numOfRepetitions; i++) {
            chars.append(strToRepeat);
        }
        return chars.toString();
    }

}
