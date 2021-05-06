package gameLogic;

import worldObjects.destructibleObject.DestructibleObject;
import worldObjects.destructibleObject.Resources;

import java.io.Serializable;
import java.util.HashMap;

public class Inventory implements Serializable {
    public HashMap<Resources, Integer> getContainer() {
        return container;
    }
    private static final long serialVersionUID = 1L;

    private final HashMap<Resources, Integer> container;

    public Inventory() {
        this.container = new HashMap<>();
    }

    public void addResources(DestructibleObject ob) {
        var resource = ob.getResourceName();
        var resourceCount = ob.getResourcesCount();
        if (container.containsKey(resource))
            container.put(resource, container.get(resource) + resourceCount);
        else
            container.put(resource, resourceCount);
    }

    public void removeResources(DestructibleObject ob, Integer value) {
        var resource = ob.getResourceName();
        if (container.containsKey(resource)) {
            var newCount = container.get(resource) - value;
            if (newCount <= 0)
                container.remove(resource);
            else
                container.put(resource, newCount);

        }
    }
}
