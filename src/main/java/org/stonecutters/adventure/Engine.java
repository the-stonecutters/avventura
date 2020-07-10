/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.stonecutters.adventure;

import org.stonecutters.adventure.games.WeirdCastel.WeirdCastel;
import org.stonecutters.adventure.parser.Parser;
import org.stonecutters.adventure.parser.ParserOutput;
import org.stonecutters.adventure.type.CommandType;
import java.util.Scanner;

/**
 * ATTENZIONE: l'Engine è molto spartano, in realtà demanda la logica alla
 * classe che implementa GameDescription e si occupa di gestire I/O sul
 * terminale.
 *
 * @author pierpaolo
 */
public class Engine {

    private final GameDescription game;

    private final Parser parser;

    public Engine(GameDescription game) {
        this.game = game;
        try {
            this.game.init(System.out);
        } catch (Exception ex) {
            System.err.println(ex);
        }
        parser = new Parser();
    }

    public void run() {
        System.out.println(game.getCurrentRoom().getName());
        System.out.println("================================================");
        System.out.println(game.getCurrentRoom().getDescription());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
            if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                System.out.println("Addio!");
                break;
            } else {
                game.nextMove(p, System.out);
                System.out.println("================================================");
            }
        }
    }

}
