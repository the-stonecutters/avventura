package org.stonecutters.adventure.games.WeirdCastel;

import org.stonecutters.adventure.GameDescription;
import org.stonecutters.adventure.parser.ParserOutput;
import org.stonecutters.adventure.type.*;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;


/*
 *  ATTENZIONE:
 *  LEGGERE QUESTO CODICE COMPORTA SPOILER
 *  E MAL DI TESTA, CONSIGLIAMO UN PIATTO DI SPAGHETTI PER QUELLO
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * NON CI CREDI? OK, PEGGIO PER TE
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 * ULTIMA CANCHE
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */





public class WeirdCastel extends GameDescription {
    //vecchia maniera facile e veloce
    AdvObjectContainer oggettoAperto = null;
    private int statoSaggio = 0;
    private int minisotterraneo = 0;

    private class roomsID {
        public static final int ATRIO = 0;
        public static final int SALA_TORTURE = 1;
        public static final int SALA_PRANZO = 2;
        public static final int CAMERA_LETTO = 3;
        public static final int CUCINE = 4;
        public static final int TORRE = 5;
        public static final int CIMA_TORRE = 6;
        public static final int SOTTERRANEO = 7;
        public static final int SALA_GENERATORE = 8;
    }

    private class objID {
        public static final int CASSA = 0;
        public static final int SAGGIO = 1;
        public static final int CANDELA = 2;
        public static final int CANDELABRO = 3;
        public static final int ACCENDINO = 4;
        public static final int ARMADIO = 5;
        public static final int LIBRERIA = 6;
        public static final int LIBRO = 7;
        public static final int MAPPAMONDO = 8;
        public static final int FOGLI = 9;
        public static final int CHIAVE = 10;
        public static final int SCOPA = 11;
        public static final int PULSANTE_BLU = 12;
        public static final int PULSANTE_ROSSO = 13;
        public static final int CIBO = 14;
        public static final int SALVEENEEE = 15;
        public static final int DEEEMAJOOO = 16;


    }

    private void initCommandList() {
        List<Command> commands = getCommands();
        commands.add(new Command(CommandType.NORTH, "nord", new String[]{"north", "n", "N", "Nord", "North"}));
        commands.add(new Command(CommandType.EAST, "est", new String[]{"east", "e", "E", "Est", "East"}));
        commands.add(new Command(CommandType.SOUTH, "sud", new String[]{"south", "s", "S", "Sud", "South"}));
        commands.add(new Command(CommandType.WEST, "ovest", new String[]{"west", "o", "O", "Ovest", "West", "w", "W"}));
        commands.add(new Command(CommandType.GAG, "avanti", new String[]{"indietro", "destra", "sinistra", "su", "giu", "forward", "backward", "right", "left", "up", "down"}));
        commands.add(new Command(CommandType.LOOK_AT, "osserva", new String[]{"guarda", "vedi", "trova", "cerca", "descrivi", "esamina", "look"}));
        commands.add(new Command(CommandType.OPEN, "apri", new String[]{"Apri", "open", "Open"}));
        commands.add(new Command(CommandType.CLOSE, "chiudi", new String[]{"Chiudi", "close", "Close"}));
        commands.add(new Command(CommandType.PICK_UP, "prendi", new String[]{"Prendi", "pick", "Pick", "take", "Take", "raccogli", "Raccogli"}));
        commands.add(new Command(CommandType.USE, "usa", new String[]{"Usa", "use", "Use"}));
        commands.add(new Command(CommandType.TALK_TO, "parla", new String[]{"parla con", "parla a", "Parla a", "Parla", "Parla con", "Parla al", "Talk", "Talk to", "Talk with", "talk", "talk to", "talk with"}));
        commands.add(new Command(CommandType.PUSH, "premi", new String[]{"press", "spingi", "push", "schiaccia"}));
        commands.add(new Command(CommandType.HELP, "aiuto", new String[]{"help"}));

    }


    private void initRooms() {
        List<Room> rooms = getRooms();
        Room room;
        AdvObject obj;
        //stanze
        rooms.add(new Room(roomsID.ATRIO, "Atrio", "Stanza molto spaziosa e illuminata, salta subito all’occhio una cassa nell’angolo, c’è un lampadario sfarzoso in cristallo che monopolizza la CPU emmm... la stanza pressoché vuota."));
        rooms.add(new Room(roomsID.SALA_TORTURE, "Sala torture", "Segui dei gradini in pietra che ti portano in una stanza e vedi la figura di un vecchietto stesa a terra proprio in fondo alla stanza, si intravedono anche degli strumenti di tortura medievali, ah sembra doloroso quello!"));
        rooms.add(new Room(roomsID.SALA_PRANZO, "Sala pranzo", "Una sala da pranzo con al centro della stanza una grande tavola in legno di forma ovale, circondata da 10 sedie in legno stranamente leggere, sul tavolo è posto un candelabro a 3 braccia ma contiene solo una candela spenta."));
        rooms.add(new Room(roomsID.CAMERA_LETTO, "Camera da letto", "Camera molto buia si intravede un letto."));
        rooms.add(new Room(roomsID.CUCINE, "Cucine", "Vedi una piccola stanza illuminata con un mobiletto, un angolo cottura, delle pentole e dei piatti, probabilmente si potrebbe cucinare qualcosa di buono."));
        rooms.add(new Room(roomsID.TORRE, "Torre", "Non riesci a vedere molto tranne che una scala a chioccia che porta verso l’alto e degli scalini in legno che portano verso il basso."));
        rooms.add(new Room(roomsID.CIMA_TORRE, "Cima della torre", "Una piccola stanza ben illuminata da un lampadario, entrando sulla destra vedi un mappamondo, sulla sinistra una grande libreria con numerosi titoli al suo interno e in fondo c’è una scrivania in legno con sopra dei fogli scritti a mano."));
        rooms.add(new Room(roomsID.SOTTERRANEO, "Sotterraneo", "La luce di una candela è troppo debole non illumina abbastanza. "));
        rooms.add(new Room(roomsID.SALA_GENERATORE, "Sala del generatore", "Stanza con un simbolo della corrente sul portone vedi chiaramente sul quadro dei comandi un pulsante rosso e un pulsante blu."));

        //connessioni
        //atrio
        room = rooms.get(roomsID.ATRIO);
        room.setNorth(rooms.get(roomsID.SALA_PRANZO));
        room.setEast(rooms.get(roomsID.TORRE));
        room.setSouth(null);
        room.setWest(rooms.get(roomsID.SALA_TORTURE));
        obj = new AdvObject(0, "Cassa", "potresti metterci qualcosa dentro", new String[]{"cassa", "box"});
        obj.setOpenable(true);
        room.getObjects().add(obj);
        room.setVisible(true);

        //sala torture
        room = rooms.get(roomsID.SALA_TORTURE);
        room.setNorth(null);
        room.setEast(rooms.get(roomsID.ATRIO));
        room.setSouth(null);
        room.setWest(null);
        room.getObjects().add(new NPC(objID.SAGGIO, "Saggio","", new String[]{"Vecchio", "saggio", "vecchio"}));
        room.setVisible(true);

        //sala pranzo
        room = rooms.get(roomsID.SALA_PRANZO);
        room.setNorth(null);
        room.setEast(rooms.get(roomsID.CAMERA_LETTO));
        room.setSouth(rooms.get(roomsID.ATRIO));
        room.setWest(rooms.get(roomsID.CUCINE));
        room.getObjects().add(new AdvObject(objID.CANDELA, "Candela",  "Una piccola candela, non fa molta luce ma meglio di niente", new String[]{"candela"}));
        room.getObjects().add(new AdvObject(objID.CANDELABRO, "Candelabro", "Utile per portare più candele", new String[]{"candelabro"}));
        room.setVisible(true);

        //camera da letto
        room = rooms.get(roomsID.CAMERA_LETTO);
        room.setNorth(null);
        room.setEast(null);
        room.setSouth(null);
        room.setWest(rooms.get(roomsID.SALA_PRANZO));
        room.getObjects().add(new AdvObject(objID.CANDELA, "Candela",  "Una piccola candela, non fa molta luce ma meglio di niente", new String[]{"candela"}));
        obj = new AdvObject(objID.ARMADIO, "Armadio", "Ci sono dei rumori, sembra che qualcuno lo tenga chiuso dall’interno, non si apre...", new String[]{"armadio"});
        obj.setPickupable(false);
        obj.setOpenable(true);
        //obj.setOpen(true);
        room.getObjects().add(obj);
        room.setVisible(false);

        //cucine
        room = rooms.get(roomsID.CUCINE);
        room.setNorth(null);
        room.setEast(rooms.get(roomsID.SALA_PRANZO));
        room.setSouth(null);
        room.setWest(null);
        room.getObjects().add(new AdvObject(objID.CIBO, "Cibo", "Cibo probabilmente commestibile", new String[]{"piatto", "cibo", "Piatto"}));
        room.setVisible(false);

        //torre
        room = rooms.get(roomsID.TORRE);
        room.setNorth(rooms.get(roomsID.CIMA_TORRE));
        room.setEast(null);
        room.setSouth(rooms.get(roomsID.SOTTERRANEO));
        room.setWest(rooms.get(roomsID.ATRIO));
        room.getObjects().add(new AdvObject(objID.CANDELA, "Candela",  "Una piccola candela, non fa molta luce ma meglio di niente", new String[]{"candela"}));
        room.setVisible(false);
        room.setSemiVisible(true);


        //cima della torre
        room = rooms.get(roomsID.CIMA_TORRE);
        room.setNorth(null);
        room.setEast(null);
        room.setSouth(rooms.get(roomsID.TORRE));
        room.setWest(null);
        obj = new AdvObjectContainer(objID.LIBRERIA, "Libreria", "Forse al suo interno c'è qualche bel libro", new String[]{"libreria"});
        obj.setPickupable(false);
        obj.setOpenable(true);
        ((AdvObjectContainer) (obj)).add(new AdvObject(objID.LIBRO, "Libro", "", new String[]{"libro"}));
        room.getObjects().add(obj);
        obj = new AdvObject(objID.MAPPAMONDO, "Mappamondo","Non mi ricordavo che un mappamondo avesse due grandi orecchie rotonde", new String[]{"mappamondo"});
        obj.setPickupable(false);
        room.getObjects().add(obj);
        obj = new AdvObject(objID.FOGLI, "Fogli", "Attenzione libri HOT", new String[]{"fogli"});
        obj.setPickupable(false);
        room.getObjects().add(obj);
        room.setVisible(true);

        //sotterraneo
        room = rooms.get(roomsID.SOTTERRANEO);
        room.setNorth(rooms.get(roomsID.TORRE));
        room.setEast(rooms.get(roomsID.SALA_GENERATORE));
        room.setSouth(null);
        room.setWest(null);
        obj = new AdvObject(objID.SCOPA, "Scopa", "", new String[]{"scopa"});
        obj.setUsable(true);
        room.getObjects().add(obj);
        room.setVisible(false);
        room.setSemiVisible(false);


        //stanza del generatore
        room = rooms.get(roomsID.SALA_GENERATORE);
        room.setNorth(null);
        room.setEast(null);
        room.setSouth(null);
        room.setWest(rooms.get(roomsID.SOTTERRANEO));
        obj = new AdvObject(objID.PULSANTE_BLU, "Pulsante blu", "", new String[]{"rosso"});
        obj.setPickupable(false);
        obj.setPushable(true);
        room.getObjects().add(obj);
        obj = new AdvObject(objID.PULSANTE_ROSSO, "Pulsante rosso", "", new String[]{"rosso"});
        obj.setPickupable(false);
        obj.setPushable(true);
        room.getObjects().add(obj);
        room.setVisible(true);

        setCurrentRoom(rooms.get(roomsID.ATRIO));
    }

    @Override
    public void init(PrintStream out) throws Exception {
        initCommandList();
        initRooms();
        getInventory().add(new AdvObject(objID.SALVEENEEE, "salvini", "è per una gag ignorami", new String[]{"salvini", "salveenee"}));
        getInventory().add(new AdvObject(objID.DEEEMAJOOO, "dimaio", "è per una gag ignorami", new String[]{"di maio", "di majonese", "deemajooo", "dimajonese"}));

    }

    @Override
    public void nextMove(ParserOutput p, PrintStream out) {
        if (p.getCommand() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean noroom = false;
            boolean move = false;
            Room currentRoom = getCurrentRoom();
            if (p.getCommand().getType() == CommandType.NORTH) {
                if (getCurrentRoom().getNorth() != null) {
                    move = entra(currentRoom.getNorth(), out);
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.SOUTH) {
                if (getCurrentRoom().getSouth() != null) {
                    move = entra(currentRoom.getSouth(), out);
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.EAST) {
                if (getCurrentRoom().getEast() != null) {
                    move = entra(currentRoom.getEast(), out);
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.WEST) {
                if (getCurrentRoom().getWest() != null) {
                    move = entra(currentRoom.getWest(), out);
                } else {
                    noroom = true;
                }
            } else if (p.getCommand().getType() == CommandType.GAG) {
                if (p.getInvObject() != null && p.getInvObject().getId() == objID.SALVEENEEE) {
                    //TODO: SCRIVERE GAG
                    out.println("GAG DI SALVINI");
                } else if (p.getInvObject() != null && p.getInvObject().getId() == objID.DEEEMAJOOO) {
                    //TODO: SCRIVERE GAG
                    out.println("GAG DI DIMAIO");
                } else {
                    out.println("Avremmo potuto mettere questo comando come alias ma non ne avevamo voglia quindi abbiamo messo questo easter egg, usa i comandi nord, sud, est, ovest per muoverti");
                }
            } else if (p.getCommand().getType() == CommandType.LOOK_AT) {
                if (!currentRoom.isVisible()) {
                    out.println("Non c'è abbastanza luce, prova ad accendere qualche candela");
                } else {
                    List<AdvObject> objs = currentRoom.getObjects();
                    if (objs.isEmpty()) {
                        out.println("Non c'è niente di interessante qui.");
                    } else {
                        for (AdvObject obj : objs) {
                            if (obj.getDescription() == null || obj.getDescription().equals("")) {
                                out.println(obj.getName());
                            } else {
                                out.printf("%s: %s\n", obj.getName(), obj.getDescription());
                            }
                        }
                    }
                }
            } else if (p.getCommand().getType() == CommandType.PICK_UP) {
                if (p.getObject() != null) {
                    pijatelo(p.getObject(), out);
                  } else {
                    out.println("Non ho capito cosa devo prendere.");
                }
            } else if (p.getCommand().getType() == CommandType.OPEN) {
                if (p.getObject() == null && p.getInvObject() == null) {
                    out.println("Non ho capito cosa devo aprire");
                } else {
                    if (p.getObject() != null) {
                        if (p.getObject().isOpen()) {
                            out.println("È già aperto");
                        } else {
                            if (p.getObject().isOpenable()) {
                                if (p.getObject() instanceof AdvObjectContainer) {
                                    out.println("Hai aperto: " + p.getObject().getName());
                                    oggettoAperto = (AdvObjectContainer) p.getObject();
                                    if (!oggettoAperto.getList().isEmpty()) {
                                        out.print(oggettoAperto.getName() + " contiene:");
                                        Iterator<AdvObject> it = oggettoAperto.getList().iterator();
                                        while (it.hasNext()) {
                                            AdvObject next = it.next();
                                            out.print(" " + next.getName());
                                            currentRoom.getObjects().add(next);
                                        }
                                        out.println();
                                    }
                                } else {
                                    fstop(p.getObject(), out);
                                }
                            } else {
                                out.println("Non puoi aprire questo oggetto.");
                            }
                        }
                    }
                }
            } else if (p.getCommand().getType() == CommandType.CLOSE) {
                AdvObject tmp = p.getObject();
                if (tmp != null) {
                    if (oggettoAperto != tmp) {
                        if (tmp.isOpenable() && tmp.isOpen()) {
                            portal(tmp, out);
                        }
                        return;
                    }
                }
                if (oggettoAperto != null) {
                    out.println("Hai chiuso " + oggettoAperto.getName());
                    chiudiOggetto();
                } else {
                    out.println("Non c'è niente da chiudere qui");
                }
            } else if (p.getCommand().getType() == CommandType.TALK_TO) {
                if (p.getObject() == null) {
                    out.println("Non ho capito con chi devo parlare");
                } else if (currentRoom.getObjects().contains(p.getObject())) {
                    chiacchiera(p.getObject(), out);
                } else {
                    out.println("Non c'è nessuno con cui parlare");
                }
            } else if (p.getCommand().getType() == CommandType.USE) {
                if (p.getInvObject() == null) {
                    out.println("Non hai questo oggetto");
                } else if (!p.getInvObject().isUsable()) {
                    out.println("Non puoi usare questo oggetto");
                } else {
                    UnitedStatesofAmerica(p.getInvObject(), out);
                }
            } else if (p.getCommand().getType() == CommandType.PUSH) {
                if (p.getObject() == null) {
                    out.println("Non ho capito cosa devo premere");
                } else if (p.getObject().isPushable()) {
                    //TODO: trovare un nome più stupido
                    premi(p.getObject(), out);
                } else {
                    out.println("Non c'è niente da premere qui.");
                }
            } else if (p.getCommand().getType() == CommandType.HELP) {
                out.println("Prega o leggi il manuale (manuale non incluso nel gioco (╯°□°)╯︵ ┻━┻)");
                out.println("Potresti dover scrivere qualcosa dopo qualche altra cosa, magari apri, prendi, usa, premi, destra, sinistra, salvini, di maio, non lo so");
            }

            if (noroom) {
                out.println("Hai battuto la testa contro il muro");
            } else if (move) {
                currentRoom = getCurrentRoom();
                out.println(currentRoom.getName());
                out.println("================================================");
                if (!currentRoom.isSemiVisible()) {
                    out.println("Non c'è abbastanza luce, prova ad accendere qualche candela");
                } else {
                    out.println(getCurrentRoom().getDescription());
                }
            }
        }
    }

    private void chiudiOggetto() {
        if (oggettoAperto != null && !oggettoAperto.getList().isEmpty()) {
            Iterator<AdvObject> it = oggettoAperto.getList().iterator();
            while (it.hasNext()) {
                AdvObject next = it.next();
                if (!getCurrentRoom().getObjects().remove(next)) it.remove();
            }
        }
        oggettoAperto = null;
    }

    private void premi(AdvObject object, PrintStream out) {
        if (object.getId() == objID.PULSANTE_BLU) {
            out.println("Hai fatto partire un razzo a forma di topo della televisione, credo che si chiami Dobbolino (non abbiamo i diritti) o qualcosa del genere.");
            finedellastoria(out);
        } else if (object.getId() == objID.PULSANTE_ROSSO) {
            out.println("Hai premuto un pulsante e aperto il ponte, o forse questo era quello per lanciare l'attacco missilistico? ah no quello era blu... credo.");
            bianconiglio(out);
        }
    }

    private void portal(AdvObject object, PrintStream out) {
        if (object.getId() == objID.ARMADIO) {
            out.println("Si sono rotte le ante....");
        }
    }

    private void fstop(AdvObject object, PrintStream out) {
        if (object.getId() == objID.ARMADIO) {
            object.setOpen(true);
            out.println("Apri l’armadio una donna e un uomo ti colpiscono e scappano\n" +
                    "Probabilmente quella era la regina ma me la ricordavo meno culona.\n" +
                    "Trovi un piccolo oggetto appiccicoso a terra e delle mutande ma non ti servirebbero a niente e li lasci lì.");
        } else if (object.getId() == objID.CASSA) {
            //TODO: SCRIVERE GAG
            out.println("GAG della cassa");
        }
    }

    private void UnitedStatesofAmerica(AdvObject object, PrintStream out) {
        /*
        if (object.getId() == objID.ACCENDINO) {
            if (getCurrentRoom().getId() == roomsID.CUCINE) {
                if (getCurrentRoom().getObjects().contains(new AdvObject(objID.CANDELA))) {
                    out.println("Dalla piccola fiamma riesci a vedere una candela, la prendi e la accendi");
                    getInventory().add(new AdvObject(objID.CANDELA, "Candela", "Una piccola candela, non fa molta luce ma meglio di niente"));
                    getCurrentRoom().getObjects().remove(new AdvObject(objID.CANDELA));
                }
            } else {
                out.println("Una piccola fiamma compare fra le tue mani, non illumina molto...");
                out.println("Ti stai bruciando la mano, la metti via");
            }
        }
         */
        if (object.getId() == objID.SCOPA) {
            out.printf("Scopa usata stato %d\n", minisotterraneo);
            if (getCurrentRoom().getId() == roomsID.SOTTERRANEO && minisotterraneo == 2) {
                out.println("*Hai colpito il ragno in testa con la scopa ed è scoppiato in mille pezzi.*");
                out.println("Bleah che schifo, ci sono budella di ragno ovunque, per fortuna che che non toccherà a me pulire!");
                minisotterraneo = 3;
            }
        }
    }

    private void chiacchiera(AdvObject object, PrintStream out) {
        if (object.getId() == objID.SAGGIO) {

            switch (statoSaggio) {
                case 0:
                    if (!getInventory().contains(new AdvObject(objID.CIBO))) {
                        out.println("Saggio: Che c’è! Che vuoi? Non ti vendo niente! Ah, non vuoi niente? E allora fammi un favore? Portami da mangiare, dovrebbe esserci qualcosa qua in giro, io non posso proprio alzarmi ma ho una fame colossale. Cough! Cough!");
                        if (!getInventory().contains(new AdvObject(objID.ACCENDINO))) {
                            out.println("Saggio: Tieni prendi questo ti potrebbe servire.");
                            getInventory().add(new AdvObject(objID.ACCENDINO, "Magia del fuoco", "Strana magia che produce una piccola fiamma", new String[]{"magia del fuoco", "fuoco", "magia", "accendino"}));
                            out.println("Hai ottenuto *musica del baule di zelda* Master Sww emmm.... Magia del fuoco!");
                        }
                        break;
                    }
                    statoSaggio++;
                case 1:
                    getInventory().remove(new AdvObject(objID.CIBO));
                    out.println("Saggio: Oh, finalmente qualcosa da mangiare! Grazie giovanotto. Come? Vuoi uscire? Vista la tua gentilezza ti dirò come fare:");
                    out.println("Saggio: scendi nelle segrete della torre e da lì apri il ponte levatoio, ma attento perché di notte si avvertono strani rumori e più volte ho visto oscure figure nelle stanze di sopra.");
                    if (!getInventory().contains(new AdvObject(objID.ACCENDINO))) {
                        out.println("Saggio: Tieni prendi questo ti potrebbe servire.");
                        getInventory().add(new AdvObject(objID.ACCENDINO, "Magia del fuoco", "Strana magia che produce una piccola fiamma", new String[]{"magia del fuoco", "fuoco", "magia", "accendino"}));
                        out.println("Hai ottenuto *musica del baule di zelda* Master Sww emmm.... Magia del fuoco!");
                    }
                    statoSaggio++;
                    break;
                case 2:
                    out.println("Il vecchietto sembra molto stanco, ma non sembra essere ammalato");
                    out.println("Saggio: Quella vergine di ferro mi ricorda la mia band preferita.");
                    break;
                default:
                    out.println("Ho preso il muro fratellì");
            }

        }
    }

    private void pijatelo(AdvObject object, PrintStream out) {
        switch (object.getId()) {
            case objID.ARMADIO:
                out.println("Pensi forse di riuscire a infilarti un'armadio in tasca? Prova ad aprirlo");
                return;
            case objID.LIBRERIA:
                out.println("Forse pesa un po' troppo, non credo farà bene alla tua ernia, magari prova a prendere quello che contiene");
                return;
            case objID.LIBRO:
                out.println("Il libro ha preso fuoco, non farti domande, dalle ceneri vedi una chiave");
                getCurrentRoom().getObjects().remove(object);
                getCurrentRoom().getObjects().add(new AdvObject(objID.CHIAVE, "Chiave", "dall'aspetto sembra molto vecchia", new String[]{"chiave", "key", "Key"}));
                return;
            case objID.CANDELA:
                if (!getInventory().contains(new AdvObject(objID.ACCENDINO))) {
                    out.println("Non te ne fai niente di una candela se non la puoi accendere");
                    return;
                }
                if (getInventory().contains(object) && ! getInventory().contains(new AdvObject(objID.CANDELABRO))) {
                    out.println("Hai già una candela in mano, potrebbe servirti un candelabro per tenerne più di una");
                    return;
                }
                break;
        }
        if (object.isPickupable()) {
            getInventory().add(object);
            getCurrentRoom().getObjects().remove(object);
            out.println("Hai raccolto: " + object.getName());
        } else {
            out.println("Non puoi raccogliere questo oggetto.");
        }
    }

    private void updateRoom(Room room) {
        switch (room.getId()) {
            case roomsID.SOTTERRANEO:
                int count = 0;
                for (AdvObject o : getInventory()) {
                    if (o.getId() == objID.CANDELA) count++;
                }
                if (count == 1) {
                    room.setSemiVisible(true);
                }
                if (count == 2) {
                    room.setDescription("Devi costruire altri pilon... ehm accedere altre candele.");
                }
                if (count == 3) {
                    room.setDescription("Un sotterraneo spazioso stranamente pieno di scatoloni attrezzi per pulire e una scopa, in fondo un cancello con un segnale sopra anticipa uno strano portone.\n" +
                            "Si sentono strani suoni e un cattivo odore, strano! Il barbone è di sopra.");
                    if (minisotterraneo == 0) minisotterraneo = 1;
                    room.setVisible(true);
                }
                break;
            default:
                if (getInventory().contains(new AdvObject(objID.CANDELA))) {
                    room.setVisible(true);
                }
        }
    }

    public Room getCurrentRoom() {
        Room room = super.getCurrentRoom();
        updateRoom(room);
        return room;
    }

    private boolean entra(Room room, PrintStream out) {

        switch (room.getId()) {
            case roomsID.SALA_TORTURE:
                if (!getInventory().contains(new AdvObject(objID.CHIAVE))) {
                    out.println("È chiusa a chiave");
                    return false;
                }
                break;
            case roomsID.SALA_GENERATORE:
                if (minisotterraneo == 0) {
                    out.println("Qualcosa la blocca ma non riesci a vedere cosa, se ti avvicini senti degli strani rumori");
                    return false;
                } else if (minisotterraneo == 1) {
                    // inizializza minigioco
                    out.println("All’improvviso salta fuori un ragno enorme che ti non ti fa passare.\n" +
                            "Uh un ragno gigante, sicuramente il creatore di questa avventura testuale non sarà aracnofobico... ah no wait.\n" +
                            "Ah ma ora che ci penso non dovrei rompere la quarta parete, vabbè pazienza.");
                    minisotterraneo = 2;
                    return false;
                } else if (minisotterraneo == 2) {
                    // minigioco in corso
                    out.println("Inizi a colpire con la mazza della scopa il ragno gigante finché non stramazza al suolo");
                    return false;
                }
        }

        if (minisotterraneo == 2) {
            out.println("Non puoi scappare da un minigioco fifone");
            return false;
        }

        chiudiOggetto();
        setCurrentRoom(room);
        return true;
    }

    private void bianconiglio(PrintStream out) {
        boolean salvi = getRooms().get(roomsID.CAMERA_LETTO).getObjects().get(0).isOpen();

        if (salvi) {
            out.println("Ti dirigi verso il ponte levatoio ma subito dopo averlo superato perdi i sensi e ti risvegli il giorno dopo.\n" +
                    "Svegliandoti la mattina il sole negli occhi e il vociferare delle persone ti provocano un mal di testa terribile,\n" +
                    "tutte quelle persone accerchiate sono lì che ti osservano ed inizi a realizzare che tutto quello che ti era successo la sera prima era solo a causa di una sbronza.\n" +
                    "A piano a piano ti tornano in mente le cose successe e capisci cosa è davvero accaduto:\n" +
                    "Il castello in cui ti trovavi era solo un’attrazione del parco di Dobbolino, il vecchio con cui hai parlato era un tossico,\n" +
                    "i due che erano scappati via una coppia di adolescenti andati lì per fare cose e ti torna in mente un flashback,\n" +
                    "quel povero innocente ragnetto aveva solo costruito una ragnatela sulla porta del generatore e tu l’hai ridotto in poltiglia con il manico di una scopa,\n" +
                    "poi hai attivato il ponte finto che ti ha fatto uscire.\n" +
                    "Mentre stai lì a fantasticare arrivano due uomini in divisa, due guardie del parco divertimenti che ti guardano con uno sguardo severo e dicono:\n" +
                    "\"Il solito ubriaco che fa quello che vuole, ehi tu adesso ti arrestiamo per violazione di proprietà privata\".\n" +
                    "E ti rendi conto che hai toccato il fondo ma come ti ha insegnato tuo nonno quando tocchi il fondo è il momento di aprire un’altra bottiglia di birra.\n" +
                    "(ATTENZIONE QUESTA STORIA POTREBBE REALMENTE ESSERE SUCCESSA A MIO CUGINO)");
        } else {
            out.println("Ti dirigi verso il ponte levatoio ma subito dopo averlo superato perdi i sensi e ti risvegli il giorno dopo.\n" +
                    "Svegliandoti la mattina il sole negli occhi e il vociferare delle persone ti provocano un mal di testa terribile,\n" +
                    "tutte quelle persone accerchiate sono lì che ti osservano ed inizi a realizzare che tutto quello che ti era successo la sera prima era solo a causa di una sbronza.\n" +
                    "A piano a piano ti tornano in mente le cose successe e capisci cosa è davvero accaduto:\n" +
                    "Il castello in cui ti trovavi era solo un’attrazione del parco di Dobbolino, il vecchio con cui hai parlato era un tossico,\n" +
                    "ti torna in mente un flashback, quel povero innocente ragnetto aveva solo costruito una ragnatela sulla porta del generatore e tu l’hai ridotto in poltiglia con il manico di una scopa,\n" +
                    "poi hai attivato il ponte finto che ti ha fatto uscire.\n" +
                    "(Solo anni dopo si verrà a scoprire che nell’armadio c’era una coppia di adolescenti che per paura di essere scoperti si era chiusa dentro ed erano rimasti bloccati lì,\n" +
                    "si ritroveranno poi solo i famosi scheletri nell’armadio!)\n" +
                    "Mentre stai lì a fantasticare arrivano due uomini in divisa, due guardie del parco divertimenti che ti guardano con uno sguardo severo e dicono:\n" +
                    "\n" +
                    "\"Il solito ubriaco che fa quello che vuole, ehi tu adesso ti arrestiamo per violazione di proprietà privata\".\n" +
                    "E ti rendi conto che hai toccato il fondo ma come ti ha insegnato tuo nonno quando tocchi il fondo è il momento di aprire un’altra bottiglia di birra.\n" +
                    "(ATTENZIONE QUESTA STORIA POTREBBE REALMENTE ESSERE SUCCESSA A MIO CUGINO)");
        }
        System.exit(0);
    }

    private void finedellastoria(PrintStream out) {
        boolean salvi = getRooms().get(roomsID.CAMERA_LETTO).getObjects().get(0).isOpen();

        if (salvi) {
            out.println("Ti dirigi verso il ponte levatoio e senti un rumore molto forte alle spalle, ma subito dopo averlo superato perdi i sensi e ti risvegli il giorno dopo.\n" +
                    "Svegliandoti la mattina il sole negli occhi e il vociferare delle persone ti provocano un mal di testa terribile, tutte quelle persone accerchiate sono lì che ti osservano,\n" +
                    "stranamente ci sono anche dei giornalisti.\n" +
                    "Inizi a realizzare che tutto quello che ti era successo la sera prima era solo a causa di una sbronza.\n" +
                    "A piano a piano ti tornano in mente le cose successe e capisci cosa è davvero accaduto:\n" +
                    "Il castello in cui ti trovavi era solo un’attrazione del parco di Dobbolino, il vecchio con cui hai parlato era un tossico,\n" +
                    "i due che erano scappati via una coppia di adolescenti andati lì per fare cose e ti torna in mente un flashback,\n" +
                    "quel povero innocente ragnetto aveva solo costruito una ragnatela sulla porta del generatore e tu l’hai ridotto in poltiglia con il manico di una scopa,\n" +
                    "poi hai attivato il ponte finto che ti ha fatto uscire.\n" +
                    "Mentre stai lì a fantasticare arrivano tanti uomini in divisa, sembrerebbero agenti federali, ti guardano con uno sguardo severo e dicono:\n" +
                    "\"Signore, è in arresto per violazione di proprietà privata e terrorismo ai danni di Dusneyland Paris\".\n" +
                    "(ATTENZIONE QUESTA STORIA POTREBBE REALMENTE ESSERE SUCCESSA A MIO CUGINO)");
        } else {
            out.println("Ti dirigi verso il ponte levatoio e senti un rumore molto forte alle spalle, ma subito dopo averlo superato perdi i sensi e ti risvegli il giorno dopo.\n" +
                    "Svegliandoti la mattina il sole negli occhi e il vociferare delle persone ti provocano un mal di testa terribile, tutte quelle persone accerchiate sono lì che ti osservano,\n" +
                    "stranamente ci sono anche dei giornalisti.\n" +
                    "Inizi a realizzare che tutto quello che ti era successo la sera prima era solo a causa di una sbronza.\n" +
                    "A piano a piano ti tornano in mente le cose successe e capisci cosa è davvero accaduto:\n" +
                    "Il castello in cui ti trovavi era solo un’attrazione del parco di Dobbolino, il vecchio con cui hai parlato era un tossico,ti torna in mente un flashback,\n" +
                    "quel povero innocente ragnetto aveva solo costruito una ragnatela sulla porta del generatore e tu l’hai ridotto in poltiglia con il manico di una scopa,\n" +
                    "poi hai attivato il ponte finto che ti ha fatto uscire.\n" +
                    "(Solo anni dopo si verrà a scoprire che nell’armadio c’era una coppia di adolescenti che per paura di essere scoperti si era chiusa dentro ed erano rimasti bloccati lì,\n" +
                    "si ritroveranno poi solo i famosi scheletri nell’armadio!)\n" +
                    "Mentre stai lì a fantasticare arrivano tanti uomini in divisa, sembrerebbero agenti federali, ti guardano con uno sguardo severo e dicono:\n" +
                    "\"Signore, è in arresto per violazione di proprietà privata e terrorismo ai danni di Dusneyland Paris\".\n" +
                    "(ATTENZIONE QUESTA STORIA POTREBBE REALMENTE ESSERE SUCCESSA A MIO CUGINO)\n");
        }

        System.exit(0);
    }
}
