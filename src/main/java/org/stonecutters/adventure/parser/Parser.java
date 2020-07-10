/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stonecutters.adventure.parser;

import org.stonecutters.adventure.type.AdvObject;
import org.stonecutters.adventure.type.Command;

import java.util.*;

/**
 *
 * @author pierpaolo
 * @author giuseppe
 */
public class Parser {
    private ArrayList<String> tokens;

    private void removeUsedTokens(int n) {
        for (int i = 0; i <= n; i++) {
            tokens.remove(0);
        }
    }

    private int checkForCommand(List<Command> commands) {
        String token = "";
        for (int j = 0; j < tokens.size(); j++) {
            if (token.length() > 0) token = token.concat(" ");
            token = token.concat(tokens.get(j));
            for (int i = 0; i < commands.size(); i++) {
                if (commands.get(i).getName().equalsIgnoreCase(token) || commands.get(i).getAlias().contains(token)) {
                    removeUsedTokens(j);
                    return i;
                }
            }
        }
        return -1;
    }

    private int checkForObject(List<AdvObject> objects) {
        String token = "";
        for (int j = 0; j < tokens.size(); j++) {
            if (token.length() > 0) token = token.concat(" ");
            token = token.concat(tokens.get(j));
            for (int i = 0; i < objects.size(); i++) {
                if (objects.get(i).getName().equalsIgnoreCase(token) || (objects.get(i).getAlias() != null && objects.get(i).getAlias().contains(token))) {
                    removeUsedTokens(j);
                    return i;
                }
            }
        }
        return -1;
    }

    /* ATTENZIONE: il parser è implementato in modo abbastanza indipendete dalla lingua mi riconosce solo
    * frasi semplici del tipo <azione> <oggetto> <oggetto> non permette di utilizzare articoli o preposizioni.
    * L'utilizzo di articoli o preporsizioni lo renderebbero dipendente dalla lingua, o meglio bisognerebbe
    * realizzare un parser per ogni lingua, prevedendo un'iterfaccia/classe astratta Parser e diverse
    * implementazioni per ogni lingua.
    */
    public ParserOutput parse(String command, List<Command> commands, List<AdvObject> objects, List<AdvObject> inventory) {
        String cmd = command.toLowerCase().trim();
        tokens = new ArrayList<>(Arrays.asList(cmd.split("\\s+")));
        if (tokens.size() > 0) {
            int ic = checkForCommand(commands);
            if (ic > -1) {
                if (tokens.size() > 0) {
                    int io = checkForObject(objects);
                    int ioinv = -1;
                    if (io < 0 && tokens.size() > 2) {
                        io = checkForObject(objects);
                    }
                    if (io < 0) {
                        ioinv = checkForObject(inventory);
                        if (ioinv < 0 && tokens.size() > 2) {
                            ioinv = checkForObject(inventory);
                        }
                    }
                    if (io > -1 && ioinv > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), inventory.get(ioinv));
                    } else if (io > -1) {
                        return new ParserOutput(commands.get(ic), objects.get(io), null);
                    } else if (ioinv > -1) {
                        return new ParserOutput(commands.get(ic), null, inventory.get(ioinv));
                    } else {
                        return new ParserOutput(commands.get(ic), null, null);
                    }
                } else {
                    return new ParserOutput(commands.get(ic), null);
                }
            } else {
                return new ParserOutput(null, null);
            }
        } else {
            return null;
        }
    }

}
