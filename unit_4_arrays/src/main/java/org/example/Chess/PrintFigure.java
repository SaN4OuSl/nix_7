package org.example.Chess;


public class PrintFigure {

    public static final String ANSI_RED = "\u001b[33m";
    public static final String ANSI_BOLD = "\033[1m";
    public static final String ANSI_RESET = "\u001B[0m";
    private AbstractFigure figure;
    private final FigureColor color;

    public enum FigureColor {
        White, Black
    }

    public PrintFigure(FigureColor color) {
        this.color = color;
    }

    public void setFigure(AbstractFigure figure) {
        this.figure = figure;
    }

    public AbstractFigure getFigure() {
        return this.figure;
    }

    public String getValue() {
        String setPicture;
        if (figure != null) {
            if (figure.getColor() == AbstractFigure.FigureColor.White) {
                switch (figure.getName()) {
                    case "Pawn":
                        setPicture = ANSI_BOLD + "\u2659" + ANSI_RESET;
                        break;
                    case "Castle":
                        setPicture = ANSI_BOLD + "\u2656" + ANSI_RESET;
                        break;
                    case "Horse":
                        setPicture = ANSI_BOLD  + "\u2658" + ANSI_RESET;
                        break;
                    case "Bishop":
                        setPicture = ANSI_BOLD  + "\u2657" + ANSI_RESET;
                        break;
                    case "Queen":
                        setPicture = ANSI_BOLD  + "\u2655" + ANSI_RESET;
                        break;
                    case "King":
                        setPicture = ANSI_BOLD  + "\u2654" + ANSI_RESET;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + figure.getCharValue());
                }
            } else {
                switch (figure.getName()) {
                    case "Pawn":
                        setPicture = ANSI_BOLD + ANSI_RED + "\u265F" + ANSI_RESET;
                        break;
                    case "Castle":
                        setPicture = ANSI_BOLD + ANSI_RED + "\u265C" + ANSI_RESET;
                        break;
                    case "Horse":
                        setPicture = ANSI_BOLD + ANSI_RED + "\u265E" + ANSI_RESET;
                        break;
                    case "Bishop":
                        setPicture = ANSI_BOLD + ANSI_RED + "\u265D" + ANSI_RESET;
                        break;
                    case "Queen":
                        setPicture = ANSI_BOLD + ANSI_RED + "\u265B" + ANSI_RESET;
                        break;
                    case "King":
                        setPicture = ANSI_BOLD + ANSI_RED + "\u265A" + ANSI_RESET;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + figure.getCharValue());
                }
            }
            return "\t" + setPicture;
        } else {
            return "\t" + "[]";
        }
    }

    public boolean isEmpty() {
        return figure == null;
    }

    public void empty() {
        figure = null;
    }
}
