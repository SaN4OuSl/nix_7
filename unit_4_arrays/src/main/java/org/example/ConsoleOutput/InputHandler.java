package org.example.ConsoleOutput;

import org.example.Chess.FigurePosition;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    private final static Pattern validMove = Pattern.compile("([a-hA-H][1-8])([-])([a-hA-H][1-8])", Pattern.CASE_INSENSITIVE);
    private final ParseChessSymbols mapper;

    public InputHandler() {
        mapper = new ParseChessSymbols();
    }

    public FigurePosition parse(String val) {
        int x = mapper.map(val.charAt(0));
        int y = mapper.map(Integer.parseInt(String.valueOf(val.charAt(1))));
        return new FigurePosition(x, y);
    }

    public FigurePosition getFrom(String val) {
        Matcher matcher = validMove.matcher(val);
        matcher.matches();
        String coords = matcher.group(1);
        return parse(coords);
    }

    public FigurePosition getTo(String val) {
        Matcher matcher = validMove.matcher(val);
        matcher.matches();
        String coords = matcher.group(3);
        return parse(coords);
    }

    public boolean isValid(String val) {
        Matcher matcher = validMove.matcher(val);
        return matcher.matches();
    }
}
