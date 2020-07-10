package org.stonecutters.adventure.type;

import java.util.Set;


/**
 * @author giuseppe marino
 */
public class NPC extends AdvObjectContainer {
    public NPC(int id) {
        super(id);
    }

    public NPC(int id, String name) {
        super(id, name);
    }

    public NPC(int id, String name, String description) {
        super(id, name, description);
    }

    public NPC(int id, String name, String description, Set<String> alias) {
        super(id, name, description, alias);
    }

    public NPC(int id, String name, String description, String[] alias) {
        super(id, name, description, alias);
    }

    @Override
    public boolean isPickupable() {
        return false;
    }
}
