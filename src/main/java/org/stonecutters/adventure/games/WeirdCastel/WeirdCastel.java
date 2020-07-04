package org.stonecutters.adventure.games.WeirdCastel;

import org.stonecutters.adventure.GameDescription;
import org.stonecutters.adventure.parser.ParserOutput;
import org.stonecutters.adventure.type.*;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

public class WeirdCastel extends GameDescription {

    private void initCommandList() {
        List<Command> commands = getCommands();
        commands.add(new Command(CommandType.NORTH, "nord", new String[]{"north", "n", "N", "Nord", "North"}));
        commands.add(new Command(CommandType.EAST, "est", new String[]{"east", "e", "E", "Est", "East"}));
        commands.add(new Command(CommandType.SOUTH, "sud", new String[]{"south", "s", "S", "Sud", "South"}));
        commands.add(new Command(CommandType.WEST, "ovest", new String[]{"west", "o", "O", "Ovest", "West", "w", "W"}));
        commands.add(new Command(CommandType.GAG, "avanti", new String[]{"indietro", "destra", "sinistra", "su", "giu", "forward", "backward", "right", "left", "up", "down"}));
        commands.add(new Command(CommandType.LOOK_AT, "osserva", new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"}));
    }


    private void initRooms() {
        List<Room> rooms = getRooms();
        Room room;
        //stanze
        rooms.add(new Room(0, "Atrio", "Stanza molto spaziosa e illuminata, salta subito all’occhio una cassa nell’angolo, c’è un lampadario sfarzoso in cristallo che monopolizza la CPU emmm... la stanza pressoché vuota."));
        rooms.add(new Room(1, "Sala torture", "Una stanza molto cupa, chiusa a chiave e con una porta di ferro con delle spesse sbarre."));
        rooms.add(new Room(2, "Sala pranzo", "Una sala da pranzo con al centro della stanza una grande tavola in legno di forma ovale, circondata da 10 sedie in legno stranamente leggere, sul tavolo è posto un candelabro a 3 braccia ma contiene solo una candela spenta."));
        rooms.add(new Room(3, "Camera da letto", "Camera molto buia si intravede un letto."));
        rooms.add(new Room(4, "Cucine", "Vedi una piccola stanza illuminata con un mobiletto, un angolo cottura, delle pentole e dei piatti, probabilmente si potrebbe cucinare qualcosa di buono."));
        rooms.add(new Room(5, "Torre", "Non riesci a vedere molto tranne che una scala a chioccia che porta verso l’alto e degli scalini in legno che portano verso il basso."));
        rooms.add(new Room(6, "Cima della torre", "Una piccola stanza ben illuminata da un lampadario, entrando sulla destra vedi un mappamondo, sulla sinistra una grande libreria con numerosi titoli al suo interno e in fondo c’è una scrivania in legno con sopra dei fogli scritti a mano."));
        rooms.add(new Room(7, "Sotterraneo", "Non si riesce a vedere niente è troppo buio."));
        rooms.add(new Room(8, "Sala del generatore", "Stanza con un simbolo della corrente sul portone vedi chiaramente sul quadro dei comandi un pulsante rosso e un pulsante blu."));

        //connessioni
        //atrio
        room = rooms.get(0);
        room.setNorth(rooms.get(2));
        room.setEast(rooms.get(1));
        room.setSouth(null);
        room.setWest(rooms.get(5));
        room.getObjects().add(new AdvObjectContainer(0, "Cassa", "Una cassa molto spaziosa, potresti metterci qualcosa dentro"));
        room.setVisible(true);

        //sala torture
        room = rooms.get(1);
        room.setNorth(null);
        room.setEast(null);
        room.setSouth(null);
        room.setWest(rooms.get(0));
        room.getObjects().add(new NPC(1, "Saggio","", new String[]{"Vecchio"}));
        room.setVisible(true);

        //sala pranzo
        room = rooms.get(2);
        room.setNorth(null);
        room.setEast(rooms.get(4));
        room.setSouth(rooms.get(0));
        room.setWest(rooms.get(3));
        room.getObjects().add(new AdvObject(2, "Candela"));
        room.getObjects().add(new AdvObject(3, "Candelabro"));
        room.setVisible(true);

        //camera da letto
        room = rooms.get(3);
        room.setNorth(null);
        room.setEast(rooms.get(2));
        room.setSouth(null);
        room.setWest(null);
        room.getObjects().add(new AdvObject(4, "Candela"));
        room.getObjects().add(new AdvObject(5, "Armadio"));
        room.setVisible(false);

        //cucine
        room = rooms.get(4);
        room.setNorth(null);
        room.setEast(null);
        room.setSouth(null);
        room.setWest(rooms.get(2));
        room.getObjects().add(new AdvObject(6, "Cibo", "Cibo probabilmente commestibile", new String[]{"piatto", "cibo", "Piatto"}));
        room.setVisible(false);

        //torre
        room = rooms.get(5);
        room.setNorth(rooms.get(6));
        room.setEast(null);
        room.setSouth(rooms.get(7));
        room.setWest(null);
        room.getObjects().add(new AdvObject(7, "Candela"));
        room.setVisible(false);


        //cima della torre
        room = rooms.get(6);
        room.setNorth(null);
        room.setEast(null);
        room.setSouth(rooms.get(5));
        room.setWest(null);
        room.getObjects().add(new AdvObjectContainer(8, "Libreria"));
        ((AdvObjectContainer) (room.getObjects().get(0))).add(new AdvObject(9, "Libro"));
        room.getObjects().add(new AdvObject(10, "Mappamondo"));
        room.getObjects().add(new AdvObject(11, "Fogli"));
        room.setVisible(true);

        //sotterraneo
        room = rooms.get(7);
        room.setNorth(null);
        room.setEast(null);
        room.setSouth(null);
        room.setWest(rooms.get(8));
        room.getObjects().add(new AdvObject(12, "Scopa"));
        room.setVisible(false);

        //stanza del generatore
        room = rooms.get(8);
        room.setNorth(null);
        room.setEast(rooms.get(7));
        room.setSouth(null);
        room.setWest(null);
        room.getObjects().add(new AdvObject(13, "Pulsante blu"));
        room.getObjects().add(new AdvObject(14, "Pulsante rosso"));
        room.setVisible(true);

        setCurrentRoom(rooms.get(0));
    }

    @Override
    public void init() throws Exception {
        initCommandList();
        initRooms();
    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean noroom = false;
            boolean move = false;

            if (p.getCommand().getType() == CommandType.NORTH) {
                if (possoEntrare(getCurrentRoom().getNorth())) {
                    setCurrentRoom(getCurrentRoom().getNorth());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.SOUTH) {
                if (possoEntrare(getCurrentRoom().getSouth())) {
                    setCurrentRoom(getCurrentRoom().getSouth());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.EAST) {
                if (possoEntrare(getCurrentRoom().getEast())) {
                    setCurrentRoom(getCurrentRoom().getEast());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.WEST) {
                if (possoEntrare(getCurrentRoom().getWest())) {
                    setCurrentRoom(getCurrentRoom().getWest());
                    move = true;
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.GAG) {
                out.println("Avremmo potuto mettere questo comando come alias ma non ne avevamo voglia quindi abbiamo messo questo easter egg, usa i comandi nord, sud, est, ovest per muoverti");
            } else if (p.getCommand().getType() == CommandType.LOOK_AT) {
                if (!getCurrentRoom().isVisible()) {
                    out.println("Non c'è abbastanza luce, prova ad accendere qualche candela");
                } else {
                    List<AdvObject> objs = getCurrentRoom().getObjects();
                    if (objs.isEmpty()) {
                        out.println("Non c'è niente di interessante qui.");
                    } else {
                        for (AdvObject obj : objs) {
                            if (obj.getDescription() == null) {
                                out.println(obj.getName());
                            } else {
                                out.printf("%s: %s\n", obj.getName(), obj.getDescription());
                            }
                        }
                    }
                }
            }

            if (noroom) {
                out.println("Hai battuto la testa contro il muro");
            } else if (move) {
                out.println(getCurrentRoom().getName());
                out.println("================================================");
                if (!getCurrentRoom().isVisible()) {
                    out.println("Non c'è abbastanza luce, prova ad accendere qualche candela");
                } else {
                    out.println(getCurrentRoom().getDescription());
                }
            }
        }
    }

    private boolean possoEntrare(Room room) {
        return room != null;
    }

    private void end(PrintStream out) {
        out.println("PA PA PAAAAAAA PA PA PAAAAAAAAAA");
        System.exit(0);
    }
}
